package fr.xebia.devoxx.hadoop.tweetscount;

import com.google.common.base.Splitter;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class TweetsCountMapper extends Mapper<LongWritable, Text, Text, LongWritable> {

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String line = value.toString();
        String hashtag = context.getConfiguration().get("hashtag");
        if (line.toLowerCase().contains("#" + hashtag.toLowerCase())) {
            context.write(new Text(hashtag), new LongWritable(1));
        } else {
            context.write(new Text(hashtag), new LongWritable(0));
        }
    }
}
