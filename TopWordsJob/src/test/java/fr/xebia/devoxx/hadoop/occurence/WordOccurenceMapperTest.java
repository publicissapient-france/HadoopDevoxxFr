package fr.xebia.devoxx.hadoop.occurence;


import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mrunit.mapreduce.MapDriver;
import org.junit.Before;
import org.junit.Test;

public class WordOccurenceMapperTest {

    private WordOccurenceMapper mapper;
    private MapDriver driver;

    @Before
    public void setUp() {
        mapper = new WordOccurenceMapper();
        driver = new MapDriver(mapper);
    }

    @Test
    public void should_count_relevant_words() {
        driver.withInput(new LongWritable(1), new Text("192.168.0.15 [INFO Mon Apr 02 22:45:48 CEST 2012] RT mot3 mot1 mot1 #tag no"));
        driver.withOutput(new Text("mot3"), new LongWritable(1));
        driver.withOutput(new Text("mot1"), new LongWritable(1));
        driver.withOutput(new Text("mot1"), new LongWritable(1));

        driver.runTest();
    }
}
