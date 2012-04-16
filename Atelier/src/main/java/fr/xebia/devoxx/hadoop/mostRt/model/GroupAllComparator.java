package fr.xebia.devoxx.hadoop.mostRt.model;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

// Group all keys on the same reducer.
public class GroupAllComparator extends WritableComparator {

    protected GroupAllComparator() {
        super(Text.class, true);
    }

    @Override
    public int compare(WritableComparable w1, WritableComparable w2) {
        return 0;
    }
}
