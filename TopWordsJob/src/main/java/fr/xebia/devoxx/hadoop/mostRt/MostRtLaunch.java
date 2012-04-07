package fr.xebia.devoxx.hadoop.mostRt;

import fr.xebia.devoxx.hadoop.fr.xebia.devoxx.common.CommandOption;
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

public class MostRtLaunch extends Configured implements Tool {

    private static final Logger LOG = LoggerFactory.getLogger(MostRtLaunch.class);
    public static final String USE_CASE = "mostRt";

    public int run(String[] args) throws Exception {
        // Configuration processed by ToolRunner
        Configuration conf = getConf();

        // Initiliaze state
        FileSystem dfs = FileSystem.get(conf);
        dfs.delete(new Path(conf.get(CommandOption.OUTPUT_PATH)), true);

        Properties props = new Properties();
        props.load(MostRtLaunch.class.getResourceAsStream("/common.properties"));
        conf.set("pushServerUrl", props.getProperty("pushServerUrl"));

        conf.set("mongo.host", props.getProperty("mongo.host"));
        conf.set("mongo.port", props.getProperty("mongo.port"));
        conf.set("mongo.database", props.getProperty("mongo.database"));
        conf.set("mongo.collection", props.getProperty("mongo.collection"));
        conf.set("mongo.user", props.getProperty("mongo.user"));
        conf.set("mongo.password", props.getProperty("mongo.password"));


        Job job = new CountRtJob(conf);

        job.setJobName("Most RT job");

        FileInputFormat.addInputPath(job, new Path(conf.get(CommandOption.INPUT_PATH)));
        FileOutputFormat.setOutputPath(job, new Path(conf.get(CommandOption.OUTPUT_PATH)));

        job.waitForCompletion(true);

        Job secondJob = new MaxRtJob(conf);

        secondJob.setJobName("Max RT job");

        FileInputFormat.addInputPath(secondJob, new Path(conf.get(CommandOption.OUTPUT_PATH)));
        FileOutputFormat.setOutputPath(secondJob, new Path(conf.get(CommandOption.OUTPUT_PATH)));

        secondJob.waitForCompletion(Boolean.parseBoolean(conf.get(CommandOption.WAIT_FOR_COMPLETION)));


        return 0;
    }
}
