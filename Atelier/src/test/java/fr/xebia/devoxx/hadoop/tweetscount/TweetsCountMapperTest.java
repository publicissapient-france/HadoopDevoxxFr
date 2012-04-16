package fr.xebia.devoxx.hadoop.tweetscount;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mrunit.mapreduce.MapDriver;
import org.junit.Before;
import org.junit.Test;

public class TweetsCountMapperTest {

    private TweetsCountMapper mapper;
    private MapDriver driver;

    @Before
    public void setUp() {
        mapper = new TweetsCountMapper();
        driver = new MapDriver(mapper);
    }

    @Test
    public void should_emit_one_when_hashtag_found() {
        Configuration conf = new Configuration();
        conf.set("hashtag", "tag");
        driver.withConfiguration(conf);

        driver.withInput(new LongWritable(1), new Text("192.168.0.15 [INFO Mon Apr 02 22:45:48 CEST 2012] RT mot3 mot1 mot1 #tag"));
        driver.withOutput(new Text("tag"), new LongWritable(1));

        driver.runTest();
    }

    @Test
    public void should_be_case_insensitive() {
        Configuration conf = new Configuration();
        conf.set("hashtag", "tAg");
        driver.withConfiguration(conf);

        driver.withInput(new LongWritable(1), new Text("192.168.0.15 [INFO Mon Apr 02 22:45:48 CEST 2012] RT mot3 mot1 mot1 #tag"));
        driver.withOutput(new Text("tAg"), new LongWritable(1));

        driver.runTest();
    }

    @Test
    public void should_emit_zero_if_not_hashtag_found() {
        Configuration conf = new Configuration();
        conf.set("hashtag", "ABSENT");
        driver.withConfiguration(conf);

        driver.withInput(new LongWritable(1), new Text("192.168.0.15 [INFO Mon Apr 02 22:45:48 CEST 2012] RT mot3 mot1 mot1 #tag"));
        driver.withOutput(new Text("ABSENT"), new LongWritable(0));

        driver.runTest();
    }
}
