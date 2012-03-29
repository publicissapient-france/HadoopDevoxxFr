package fr.xebia.devoxx.hadoop.mostRt.model;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

// Sort keys
public class SortComparator extends WritableComparator {

    protected SortComparator() {
        super(TwitterStreamCount.class, true);
    }

    @Override
    public int compare(WritableComparable w1, WritableComparable w2) {
        return ((TwitterStreamCount) w1).compareTo((TwitterStreamCount) w2);
    }
}
