package fr.xebia.devoxx.hadoop.mostRt;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.mapreduce.Reducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Iterator;


public class SumReducer extends Reducer<WritableComparable, Text, WritableComparable, LongWritable> {
    private final static Logger LOG = LoggerFactory.getLogger(SumReducer.class);

    @Override
    protected void reduce(WritableComparable key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
        Iterator<Text> iterator = values.iterator();
        int count = 0;
        while (iterator.hasNext()) {
            count++;
            Text next = iterator.next();
        }
        context.write(key, new LongWritable(count));
    }
}
