package fr.xebia.devoxx.hadoop.fr.xebia.devoxx.common.output;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.Mongo;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.mapreduce.RecordWriter;
import org.apache.hadoop.mapreduce.TaskAttemptContext;

import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.net.UnknownHostException;

public class MongoRecordWriter extends RecordWriter {

    private Mongo mongodb;
    private DB database;
    private PropertyDescriptor[] propertyDescriptorsKey;
    private PropertyDescriptor[] propertyDescriptorsValue;
    private DBCollection collection;
//    private Jongo jongo;
//    private MongoCollection collection;


    public MongoRecordWriter(Configuration configuration) throws IOException {
        init(configuration);
    }

    public void init(Configuration configuration) throws IOException {
        String host = configuration.get("mongo.host");
        int port = configuration.getInt("mongo.port", 27017);
        String user = configuration.get("mongo.user");
        String password = configuration.get("mongo.password");
        try {
            System.out.println("Logging to " + host + ":" + port + " with " + user);
            mongodb = new Mongo(host, port);
        } catch (UnknownHostException uhe) {
            cleanup();
            uhe.printStackTrace();
            throw uhe;
        }


        String databaseName = configuration.get("mongo.database");
        database = mongodb.getDB(databaseName);
        if (password != null) {
            database.authenticate(user, password.toCharArray());
        }
        collection = database.getCollection(configuration.get("mongo.collection"));
//        jongo = new Jongo(database);
//        collection = jongo.getCollection(configuration.get("mongo.collection"));

    }

    public void cleanup() {
        try {
            if (database != null) {
                database.cleanCursors(true);
                database = null;
            }
            if (mongodb != null) {
                mongodb.close();
                mongodb = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void write(Object key, Object value) throws IOException, InterruptedException {

        //Introspection
        try {
            BasicDBObject basicDBObject = new BasicDBObject();
            boolean nothingWrote = true;
            if (propertyDescriptorsKey == null) {
                propertyDescriptorsKey = Introspector.getBeanInfo(key.getClass()).getPropertyDescriptors();
            }
            for (PropertyDescriptor pd : propertyDescriptorsKey) {
                if (pd.getReadMethod() != null && !"class".equals(pd.getName())) {
                    nothingWrote = false;
                    basicDBObject.put(pd.getReadMethod().getName(), pd.getReadMethod().invoke(key).toString());
                }
            }
            if (nothingWrote) {
                basicDBObject.put("key", key.toString());
            }
            nothingWrote = true;

            if (propertyDescriptorsValue == null) {
                propertyDescriptorsValue = Introspector.getBeanInfo(value.getClass()).getPropertyDescriptors();
            }
            for (PropertyDescriptor pd : propertyDescriptorsValue) {
                if (pd.getReadMethod() != null && !"class".equals(pd.getName())) {
                    nothingWrote = false;
                    basicDBObject.put(pd.getReadMethod().getName(), pd.getReadMethod().invoke(value).toString());
                }
            }
            if (nothingWrote) {
                basicDBObject.put("value", value.toString());
            }

            collection.save(basicDBObject);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void close(TaskAttemptContext context) throws IOException, InterruptedException {
        cleanup();
    }
}
