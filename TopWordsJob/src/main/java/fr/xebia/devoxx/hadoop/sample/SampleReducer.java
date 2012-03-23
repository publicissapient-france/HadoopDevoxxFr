package fr.xebia.devoxx.hadoop.sample;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Iterator;


public class SampleReducer extends Reducer<LongWritable, Text, LongWritable, Text> {
    private final static Logger LOG = LoggerFactory.getLogger(SampleReducer.class);

    @Override
    protected void reduce(LongWritable key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
        Iterator<Text> iterator = values.iterator();
        while (iterator.hasNext()) {
            Text next = iterator.next();
            context.write(key, next);
        }
    }
}
