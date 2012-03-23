package fr.xebia.devoxx.hadoop.sample;

import fr.xebia.devoxx.hadoop.sample.output.PushServerFormat;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;

import java.io.IOException;

public class SampleJob extends Job {
    public SampleJob(Configuration conf) throws IOException {

        super(conf);

        this.setMapperClass(SampleMapper.class);
        this.setMapOutputKeyClass(LongWritable.class);
        this.setMapOutputValueClass(Text.class);

        this.setReducerClass(SampleReducer.class);
        this.setOutputKeyClass(LongWritable.class);
        this.setOutputValueClass(Text.class);

        this.setOutputFormatClass(PushServerFormat.class);

        this.setJarByClass(SampleJob.class);
    }
}
