package fr.xebia.devoxx.hadoop.mostRt;

import fr.xebia.devoxx.hadoop.mostRt.model.TwitterStreamCount;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Reducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;


public class MaxReducer extends Reducer<TwitterStreamCount, NullWritable, TwitterStreamCount, NullWritable> {
    private final static Logger LOG = LoggerFactory.getLogger(MaxReducer.class);

    @Override
    protected void reduce(TwitterStreamCount key, Iterable<NullWritable> values, Context context) throws IOException, InterruptedException {
        context.write(key, NullWritable.get());
    }
}
