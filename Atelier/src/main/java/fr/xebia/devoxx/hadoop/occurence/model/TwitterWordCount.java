package fr.xebia.devoxx.hadoop.occurence.model;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;

public class TwitterWordCount {

    private Text word;
    private LongWritable count;

    public TwitterWordCount() {
        this.word = new Text();
        this.count = new LongWritable(0);
    }

    public TwitterWordCount(String word, long count) {
        this.word = new Text(word);
        this.count = new LongWritable(count);
    }

    public Text getWord() {
        return word;
    }

    public void setWord(Text word) {
        this.word = word;
    }

    public LongWritable getCount() {
        return count;
    }

    public void setCount(LongWritable count) {
        this.count = count;
    }

    public int compareTo(TwitterWordCount o) {
        if (o != null) {
            int cmp = this.word.compareTo(o.word);

            if (cmp != 0) {
                return cmp;
            } else {
                return this.count.compareTo(o.count);
            }
        } else {
            return 0;
        }
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("word", word).append("count", count).toString();
    }

}
