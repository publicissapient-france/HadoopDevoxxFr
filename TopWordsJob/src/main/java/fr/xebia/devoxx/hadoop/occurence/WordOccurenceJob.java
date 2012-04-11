package fr.xebia.devoxx.hadoop.occurence;

import fr.xebia.devoxx.hadoop.fr.xebia.devoxx.common.output.PushServerFormat;
import fr.xebia.devoxx.hadoop.occurence.model.TwitterWordCount;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;

import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: mathieu
 * Date: 4/3/12
 * Time: 10:16 PM
 * To change this template use File | Settings | File Templates.
 */
public class WordOccurenceJob extends Job {
    public WordOccurenceJob(Configuration conf) throws IOException {
        super(conf);

        this.setMapperClass(WordOccurenceMapper.class);
        this.setMapOutputKeyClass(Text.class);
        this.setMapOutputValueClass(LongWritable.class);

        this.setReducerClass(WordOccurenceReducer.class);
        this.setOutputKeyClass(TwitterWordCount.class);
        this.setOutputValueClass(NullWritable.class);

        this.setOutputFormatClass(PushServerFormat.class);

        this.setJarByClass(WordOccurenceJob.class);
    }
}
