package fr.xebia.devoxx.hadoop.occurence.model;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class TwitterWordCount implements WritableComparable<TwitterWordCount> {

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

    @Override
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
    public void write(DataOutput dataOutput) throws IOException {
        word.write(dataOutput);
        count.write(dataOutput);
    }

    @Override
    public void readFields(DataInput dataInput) throws IOException {
        word.readFields(dataInput);
        count.readFields(dataInput);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("word", word).append("count", count).toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TwitterWordCount that = (TwitterWordCount) o;

        if (count != null ? !count.equals(that.count) : that.count != null) return false;
        if (word != null ? !word.equals(that.word) : that.word != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = word != null ? word.hashCode() : 0;
        result = 31 * result + (count != null ? count.hashCode() : 0);
        return result;
    }
}
