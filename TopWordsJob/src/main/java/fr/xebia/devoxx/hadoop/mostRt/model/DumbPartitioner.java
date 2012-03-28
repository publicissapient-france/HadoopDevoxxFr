package fr.xebia.devoxx.hadoop.mostRt.model;

import org.apache.hadoop.conf.Configurable;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Partitioner;


public class DumbPartitioner extends Partitioner<TwitterStreamCount, NullWritable> implements Configurable {
    @Override
    public int getPartition(TwitterStreamCount key, NullWritable nullWritable, int numPartitions) {
        return  32 % numPartitions;
    }

    @Override
    public void setConf(Configuration configuration) {

    }

    @Override
    public Configuration getConf() {
        return null;
    }
}
