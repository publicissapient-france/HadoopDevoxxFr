package fr.xebia.devoxx.hadoop.tweetscount;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.mapreduce.Job;

import java.io.IOException;

public class TweetsCountJob extends Job {
    public TweetsCountJob(Configuration conf) throws IOException {
        super(conf);

        // TODO

        this.setJarByClass(TweetsCountJob.class);
    }
}
