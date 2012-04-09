package fr.xebia.devoxx.hadoop.occurence;

import com.google.common.base.Splitter;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class WordOccurenceMapper extends Mapper<LongWritable, Text, Text, LongWritable> {

    public static final String RELEVANT_WORD_PATTERN = "\\w{4,}";

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        Iterable<String> tokens = tokenizeInput(value);
        for (String word : tokens) {
            if (word.matches(RELEVANT_WORD_PATTERN)) {
                context.write(new Text(word), new LongWritable(1));
            }
        }
    }

    private Iterable<String> tokenizeInput(Text value) {
        String line = value.toString();
        line = line.substring(line.indexOf("]") + 1, line.length());

        return Splitter.onPattern("\\s")
                .omitEmptyStrings()
                .trimResults()
                .split(line);
    }

}
