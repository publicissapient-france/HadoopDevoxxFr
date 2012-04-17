package fr.xebia.devoxx.hadoop.tweetscount.model;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;

public class TweetsCount {

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
    public String toString() {
        return new ToStringBuilder(this).append("hashtag", hashtag).append("count", count).toString();
    }
}
