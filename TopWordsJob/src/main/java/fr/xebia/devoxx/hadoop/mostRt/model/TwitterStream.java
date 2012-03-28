package fr.xebia.devoxx.hadoop.mostRt.model;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class TwitterStream implements WritableComparable<TwitterStream> {

    private Text user;
    private Text message;

    public Text getMessage() {
        return message;
    }

    public void setMessage(Text message) {
        this.message = message;
    }

    public Text getUser() {
        return user;
    }

    public void setUser(Text user) {
        this.user = user;
    }

    public TwitterStream() {
        this.message = new Text();
        this.user = new Text();
    }

    @Override
    public int compareTo(TwitterStream o) {
        if (o != null) {
            int cmp = this.user.compareTo(o.user);

            if (cmp != 0) {
                return cmp;
            } else {
                return this.message.compareTo(o.message);
            }
        } else {
            return 0;
        }
    }

    @Override
    public void write(DataOutput dataOutput) throws IOException {
        user.write(dataOutput);
        message.write(dataOutput);
    }

    @Override
    public void readFields(DataInput dataInput) throws IOException {
        user.readFields(dataInput);
        message.readFields(dataInput);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("user", user).append("message", message).toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TwitterStreamCount that = (TwitterStreamCount) o;

        if (this.getUser() != null ? !this.getUser().equals(that.getUser()) : that.getUser() != null) return false;
        if (this.getMessage() != null ? !this.getMessage().equals(that.getMessage()) : that.getMessage() != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = this.getUser() != null ? this.getUser().hashCode() : 0;
        result = 31 * result + (this.getMessage() != null ? this.getMessage().hashCode() : 0);
        return result;
    }
}
