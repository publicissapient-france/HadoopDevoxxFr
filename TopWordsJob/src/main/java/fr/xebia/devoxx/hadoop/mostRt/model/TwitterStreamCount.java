package fr.xebia.devoxx.hadoop.mostRt.model;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.hadoop.io.LongWritable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class TwitterStreamCount extends TwitterStream {
    private LongWritable count;

    public LongWritable getCount() {
        return count;
    }

    public void setCount(LongWritable count) {
        this.count = count;
    }

    public int compareTo(TwitterStreamCount o) {
        if (o != null) {
            int cmp = -this.count.compareTo(o.count);
            if (cmp != 0) {
                return cmp;
            } else {
                return super.compareTo(o);
            }
        } else {
            return 0;
        }
    }

    @Override
    public void readFields(DataInput dataInput) throws IOException {
        super.readFields(dataInput);
        count.readFields(dataInput);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("count", count).append("user", this.getUser()).append("message", this.getMessage()).toString();
    }

    public TwitterStreamCount() {
        super();
        count = new LongWritable(0);
    }

    @Override
    public void write(DataOutput dataOutput) throws IOException {
        super.write(dataOutput);
        count.write(dataOutput);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TwitterStreamCount that = (TwitterStreamCount) o;

        if (count != null ? !count.equals(that.count) : that.count != null) return false;

        return super.equals(o);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (this.count != null ? this.count.hashCode() : 0);
        return result;
    }
}
