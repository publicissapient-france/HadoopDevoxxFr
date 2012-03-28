package fr.xebia.devoxx.hadoop.mostRt;

import fr.xebia.devoxx.hadoop.mostRt.model.TwitterStream;
import fr.xebia.devoxx.hadoop.mostRt.model.TwitterStreamCount;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class CountRtMapper extends Mapper<TwitterStream, LongWritable, TwitterStreamCount, NullWritable> {
    private final static Logger LOG = LoggerFactory.getLogger(CountRtMapper.class);

    @Override
    protected void map(TwitterStream key, LongWritable value, Context context) throws IOException, InterruptedException {
        TwitterStreamCount twitterStreamCount = new TwitterStreamCount();
        twitterStreamCount.setUser(key.getUser());
        twitterStreamCount.setMessage(key.getMessage());
        twitterStreamCount.setCount(value);
        context.write(twitterStreamCount, NullWritable.get());
    }
}
