package fr.xebia.devoxx.hadoop.sample;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class SampleMapper extends Mapper<LongWritable, Text, LongWritable, Text> {
    private final static Logger LOG = LoggerFactory.getLogger(SampleMapper.class);

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        context.write(new LongWritable(value.toString().length()), value);
    }
}
