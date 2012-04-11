package fr.xebia.devoxx.hadoop.occurence;


import fr.xebia.devoxx.hadoop.occurence.model.TwitterWordCount;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mrunit.mapreduce.MapReduceDriver;
import org.junit.Before;
import org.junit.Test;

public class OccurenceJobTest {
    private WordOccurenceMapper mapper;
    private WordOccurenceReducer reducer;
    private MapReduceDriver driver;


    @Before
    public void setUp() {
        mapper = new WordOccurenceMapper();
        reducer = new WordOccurenceReducer();
        driver = new MapReduceDriver(mapper, reducer);
    }

    @Test
    public void testMapReduce() {
        driver.withInput(new LongWritable(1), new Text("192.168.0.15 [INFO Mon Apr 02 22:45:48 CEST 2012] RT mot3 mot1 mot1 mot2"));
        driver.withOutput(new TwitterWordCount("mot1", 2), NullWritable.get());
        driver.withOutput(new TwitterWordCount("mot2", 1), NullWritable.get());
        driver.withOutput(new TwitterWordCount("mot3", 1), NullWritable.get());

        driver.runTest();
    }
}
