package fr.xebia.devoxx.hadoop.common.output.websocket;

import fr.xebia.devoxx.hadoop.common.output.utils.TwitterStreamCountSerializer;
import fr.xebia.devoxx.hadoop.common.output.utils.TwitterWordCountSerializer;
import fr.xebia.devoxx.hadoop.mostRt.model.TwitterStreamCount;
import fr.xebia.devoxx.hadoop.occurence.model.TwitterWordCount;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.mapreduce.RecordWriter;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.codehaus.jackson.Version;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.module.SimpleModule;

import java.io.IOException;
import java.io.StringWriter;


public class PushServerRecordWriter extends RecordWriter<Object, Object> {
    private String pushServerUrl;

    public PushServerRecordWriter(Configuration configuration) {
        pushServerUrl = configuration.get("pushServerUrl");
    }


    @Override
    public void write(Object key, Object value) throws IOException, InterruptedException {
        DefaultHttpClient httpClient = new DefaultHttpClient();
        HttpPost postRequest = new HttpPost(pushServerUrl);


        ObjectMapper mapper = new ObjectMapper();
        SimpleModule hadoopModule = new SimpleModule("HadoopModule", new Version(1, 0, 0, null));
        hadoopModule.addSerializer(TwitterStreamCount.class, new TwitterStreamCountSerializer());
        hadoopModule.addSerializer(TwitterWordCount.class, new TwitterWordCountSerializer());
        mapper.registerModule(hadoopModule);
        StringWriter out = new StringWriter();
        mapper.writeValue(out, key);

        System.out.println(out.toString());

        StringEntity input = new StringEntity(out.toString());

        input.setContentType("application/json");
        postRequest.setEntity(input);

        HttpResponse response = httpClient.execute(postRequest);

        if (response.getStatusLine().getStatusCode() != 200) {
            // Do something
        }
    }

    @Override
    public void close(TaskAttemptContext taskAttemptContext) throws IOException, InterruptedException {
        // TODO
    }
}
