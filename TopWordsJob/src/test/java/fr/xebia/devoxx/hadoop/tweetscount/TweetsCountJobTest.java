package fr.xebia.devoxx.hadoop.tweetscount;

import fr.xebia.devoxx.hadoop.tweetscount.model.TweetsCount;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mrunit.mapreduce.MapReduceDriver;
import org.junit.Before;
import org.junit.Test;

public class TweetsCountJobTest {

    private TweetsCountMapper mapper;
    private TweetsCountReducer reducer;
    private MapReduceDriver driver;


    @Before
    public void setUp() {
        mapper = new TweetsCountMapper();
        reducer = new TweetsCountReducer();
        driver = new MapReduceDriver(mapper, reducer);
    }

    @Test
    public void testMapReduce() {
        Configuration conf = new Configuration();
        conf.set("hashtag", "TaG");
        driver.withConfiguration(conf);

        driver.withInput(new LongWritable(1), new Text("192.168.0.15 [INFO Mon Apr 02 22:45:48 CEST 2012] RT mot3 mot1 mot1 #tag"));
        driver.withInput(new LongWritable(2), new Text("192.168.0.15 [INFO Mon Apr 02 22:45:48 CEST 2012] RT mot4 #tag"));
        driver.withOutput(new TweetsCount("TaG", 2), NullWritable.get());

        driver.runTest();
    }

}
