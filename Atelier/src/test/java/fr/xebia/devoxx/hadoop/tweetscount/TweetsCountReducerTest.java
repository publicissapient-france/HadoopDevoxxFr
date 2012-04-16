package fr.xebia.devoxx.hadoop.tweetscount;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mrunit.mapreduce.ReduceDriver;
import org.junit.Before;
import org.junit.Test;

import static com.google.common.collect.Lists.newArrayList;

public class TweetsCountReducerTest {
    private TweetsCountReducer reducer;
    private ReduceDriver driver;

    @Before
    public void setUp() {
        reducer = new TweetsCountReducer();
        driver = new ReduceDriver(reducer);
    }

    @Test
    public void should_sum_tweets() {
        driver.withInput(new Text("tAg"), newArrayList(new LongWritable(1), new LongWritable(1), new LongWritable(0)));
        driver.withOutput(new TweetsCount("tAg", 2), NullWritable.get());

        driver.runTest();
    }
}
