package fr.xebia.devoxx.hadoop.common.output.mongo;

import org.apache.hadoop.mapreduce.*;

import java.io.IOException;


public class MongoOutputFormat extends OutputFormat {

    @Override
    public RecordWriter getRecordWriter(TaskAttemptContext context) throws IOException, InterruptedException {
        return new MongoRecordWriter(context.getConfiguration());
    }

    @Override
    public void checkOutputSpecs(JobContext context) throws IOException, InterruptedException {
        // Not implemented yet
    }

    @Override
    public OutputCommitter getOutputCommitter(TaskAttemptContext context) throws IOException, InterruptedException {
        return new MongoOutputCommiter();
    }

}
