package fr.xebia.devoxx.hadoop.mostRt;

import fr.xebia.devoxx.hadoop.fr.xebia.devoxx.common.output.PushServerFormat;
import fr.xebia.devoxx.hadoop.mostRt.model.TwitterStream;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.output.SequenceFileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

import java.io.IOException;

public class CountRtJob extends Job {
    public CountRtJob(Configuration conf) throws IOException {

        super(conf);

        this.setMapperClass(IdentifyRtMapper.class);
        this.setMapOutputKeyClass(TwitterStream.class);
        this.setMapOutputValueClass(Text.class);

        this.setReducerClass(SumReducer.class);
        this.setOutputKeyClass(TwitterStream.class);
        this.setOutputValueClass(LongWritable.class);

        this.setOutputFormatClass(SequenceFileOutputFormat.class);

        this.setJarByClass(CountRtJob.class);
    }
}
