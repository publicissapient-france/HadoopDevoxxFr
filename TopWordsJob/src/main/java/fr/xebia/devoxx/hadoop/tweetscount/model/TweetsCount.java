package fr.xebia.devoxx.hadoop.tweetscount.model;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class TweetsCount implements WritableComparable<TweetsCount> {

    private Text hashtag;
    private LongWritable count;

    public TweetsCount() {
        this.hashtag = new Text();
        this.count = new LongWritable(0);
    }

    public TweetsCount(String hashtag, long count) {
        this.hashtag = new Text(hashtag);
        this.count = new LongWritable(count);
    }

    public Text getHashtag() {
        return hashtag;
    }

    public void setHashtag(Text hashtag) {
        this.hashtag = hashtag;
    }

    public LongWritable getCount() {
        return count;
    }

    public void setCount(LongWritable count) {
        this.count = count;
    }

    @Override
    public int compareTo(TweetsCount o) {
        if (o != null) {
            int cmp = this.hashtag.compareTo(o.hashtag);

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
        hashtag.write(dataOutput);
        count.write(dataOutput);
    }

    @Override
    public void readFields(DataInput dataInput) throws IOException {
        hashtag.readFields(dataInput);
        count.readFields(dataInput);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("hashtag", hashtag).append("count", count).toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TweetsCount that = (TweetsCount) o;

        if (count != null ? !count.equals(that.count) : that.count != null) return false;
        if (hashtag != null ? !hashtag.equals(that.hashtag) : that.hashtag != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = hashtag != null ? hashtag.hashCode() : 0;
        result = 31 * result + (count != null ? count.hashCode() : 0);
        return result;
    }
}
