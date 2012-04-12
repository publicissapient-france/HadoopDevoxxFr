package fr.xebia.devoxx.hadoop.occurence;

import fr.xebia.devoxx.hadoop.occurence.model.TwitterWordCount;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mrunit.mapreduce.ReduceDriver;
import org.junit.Before;
import org.junit.Test;

import static com.google.common.collect.Lists.newArrayList;

public class WordOccurenceReducerTest {
    private WordOccurenceReducer reducer;
    private ReduceDriver driver;

    @Before
    public void setUp() {
        reducer = new WordOccurenceReducer();
        driver = new ReduceDriver(reducer);
    }

    @Test
    public void should_sum_word_occurence() {
        driver.withInput(new Text("mot1"), newArrayList(new LongWritable(1), new LongWritable(1)));
        driver.withOutput(new TwitterWordCount("mot1", 2), NullWritable.get());

        driver.runTest();
    }
}
