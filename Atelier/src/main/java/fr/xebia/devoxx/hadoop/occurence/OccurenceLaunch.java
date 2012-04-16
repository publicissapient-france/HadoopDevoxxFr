package fr.xebia.devoxx.hadoop.occurence;


import fr.xebia.devoxx.hadoop.common.CommandOption;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

public class OccurenceLaunch extends Configured implements Tool {

    private static final Logger LOG = LoggerFactory.getLogger(OccurenceLaunch.class);
    public static final String USE_CASE = "occurence";

    public int run(String[] args) throws Exception {
		
		// TODO
		
        return 0;
    }
}