package fr.xebia.devoxx.hadoop.occurence;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.mapreduce.Job;

import java.io.IOException;

public class WordOccurenceJob extends Job {
    public WordOccurenceJob(Configuration conf) throws IOException {
        super(conf);

        // TODO

        this.setJarByClass(WordOccurenceJob.class);
    }
}
