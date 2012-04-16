package fr.xebia.devoxx.hadoop.tweetscount;

import fr.xebia.devoxx.hadoop.common.output.websocket.PushServerFormat;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;

import java.io.IOException;

public class TweetsCountJob extends Job {
    public TweetsCountJob(Configuration conf) throws IOException {
        super(conf);

        // TODO

        this.setJarByClass(TweetsCountJob.class);
    }
}
