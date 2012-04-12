package fr.xebia.devoxx.hadoop.tweetscount;

import fr.xebia.devoxx.hadoop.common.output.websocket.PushServerFormat;
import fr.xebia.devoxx.hadoop.tweetscount.model.TweetsCount;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;

import java.io.IOException;

public class TweetsCountJob extends Job {
    public TweetsCountJob(Configuration conf) throws IOException {
        super(conf);

        this.setMapperClass(TweetsCountMapper.class);
        this.setMapOutputKeyClass(Text.class);
        this.setMapOutputValueClass(LongWritable.class);

        this.setReducerClass(TweetsCountReducer.class);
        this.setOutputKeyClass(TweetsCount.class);
        this.setOutputValueClass(NullWritable.class);

        this.setOutputFormatClass(PushServerFormat.class);

        this.setJarByClass(TweetsCountJob.class);
    }
}
