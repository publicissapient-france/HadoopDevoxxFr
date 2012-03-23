package fr.xebia.devoxx.hadoop.sample.output;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.RecordWriter;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.IOException;


public class PushServerRecordWriter extends RecordWriter<LongWritable, Text> {
    private String pushServerUrl;

    public PushServerRecordWriter(Configuration configuration) {
        pushServerUrl = configuration.get("pushServerUrl");
    }


    @Override
    public void write(LongWritable key, Text value) throws IOException, InterruptedException {
        DefaultHttpClient httpClient = new DefaultHttpClient();
        HttpPost postRequest = new HttpPost(pushServerUrl);

        StringEntity input = new StringEntity("{\"value\":\"" + value.toString() + "\"}");

        input.setContentType("application/json");
        postRequest.setEntity(input);

        HttpResponse response = httpClient.execute(postRequest);

        if (response.getStatusLine().getStatusCode() != 200) {
            // Do something
        }
    }

    @Override
    public void close(TaskAttemptContext taskAttemptContext) throws IOException, InterruptedException {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
