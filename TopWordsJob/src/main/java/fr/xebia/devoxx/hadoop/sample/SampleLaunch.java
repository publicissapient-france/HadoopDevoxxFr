package fr.xebia.devoxx.hadoop.sample;

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

public class SampleLaunch extends Configured implements Tool {

    private static final Logger LOG = LoggerFactory.getLogger(SampleLaunch.class);

    public int run(String[] args) throws Exception {
        // Configuration processed by ToolRunner
        Configuration conf = getConf();

        // Initiliaze state
        FileSystem dfs = FileSystem.get(conf);
        dfs.delete(new Path(conf.get(CommandOption.OUTPUT_PATH)), true);

        Properties props = new Properties();
        props.load(SampleLaunch.class.getResourceAsStream("/sample.properties"));
        conf.set("pushServerUrl", props.getProperty("pushServerUrl"));

        Job job = new SampleJob(conf);

        job.setJobName("Sample job");

        FileInputFormat.addInputPath(job, new Path(conf.get(CommandOption.INPUT_PATH)));
        FileOutputFormat.setOutputPath(job, new Path(conf.get(CommandOption.OUTPUT_PATH)));

        job.waitForCompletion(Boolean.parseBoolean(conf.get(CommandOption.WAIT_FOR_COMPLETION)));

        return 0;
    }
}
