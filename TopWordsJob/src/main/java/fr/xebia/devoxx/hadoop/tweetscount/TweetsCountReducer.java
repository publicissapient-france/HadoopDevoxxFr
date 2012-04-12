package fr.xebia.devoxx.hadoop.tweetscount;

import fr.xebia.devoxx.hadoop.tweetscount.model.TweetsCount;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;


public class TweetsCountReducer extends Reducer<Text, LongWritable, TweetsCount, NullWritable> {
    private final static Logger LOG = LoggerFactory.getLogger(TweetsCountReducer.class);

    @Override
    protected void reduce(Text hashtag, Iterable<LongWritable> values, Context context) throws IOException, InterruptedException {
        int nbTweets = 0;
        for (LongWritable value : values) {
            nbTweets += value.get();
        }
        TweetsCount tweetsCount = new TweetsCount();
        tweetsCount.setCount(new LongWritable(nbTweets));
        tweetsCount.setHashtag(hashtag);

        context.write(tweetsCount, NullWritable.get());
    }
}
