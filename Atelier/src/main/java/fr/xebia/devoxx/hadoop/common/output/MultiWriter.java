package fr.xebia.devoxx.hadoop.common.output;

import fr.xebia.devoxx.hadoop.common.output.mongo.MongoRecordWriter;
import fr.xebia.devoxx.hadoop.common.output.websocket.PushServerRecordWriter;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.mapreduce.RecordWriter;
import org.apache.hadoop.mapreduce.TaskAttemptContext;

import java.io.IOException;


public class MultiWriter extends RecordWriter {

    private MongoRecordWriter mongoRecordWriter;
    private PushServerRecordWriter pushServerRecordWriter;

    @Override
    public void write(Object key, Object value) throws IOException, InterruptedException {
        mongoRecordWriter.write(key, value);
        pushServerRecordWriter.write(key, value);
    }

    @Override
    public void close(TaskAttemptContext context) throws IOException, InterruptedException {
        mongoRecordWriter.close(context);
        pushServerRecordWriter.close(context);
    }

    public MultiWriter(Configuration configuration) throws IOException {
        mongoRecordWriter = new MongoRecordWriter(configuration);
        pushServerRecordWriter = new PushServerRecordWriter(configuration);
    }
}
