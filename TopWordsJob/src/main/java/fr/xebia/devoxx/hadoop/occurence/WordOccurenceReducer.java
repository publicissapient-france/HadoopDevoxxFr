package fr.xebia.devoxx.hadoop.occurence;

import fr.xebia.devoxx.hadoop.occurence.model.TwitterWordCount;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;


public class WordOccurenceReducer extends Reducer<Text, LongWritable, TwitterWordCount, NullWritable> {
    private final static Logger LOG = LoggerFactory.getLogger(WordOccurenceReducer.class);

    @Override
    protected void reduce(Text word, Iterable<LongWritable> values, Context context) throws IOException, InterruptedException {
        int nbOccurence = 0;
        for (LongWritable value : values) {
            nbOccurence += value.get();
        }
        TwitterWordCount wordCount = new TwitterWordCount();
        wordCount.setCount(new LongWritable(nbOccurence));
        wordCount.setWord(word);

        context.write(wordCount, NullWritable.get());
    }
}
