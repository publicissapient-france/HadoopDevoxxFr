package fr.xebia.devoxx.hadoop.common.output;

import org.apache.hadoop.mapreduce.*;

import java.io.IOException;

public class MultiFormat extends OutputFormat {
    @Override
    public RecordWriter getRecordWriter(TaskAttemptContext context) throws IOException, InterruptedException {
       return new MultiWriter(context.getConfiguration());
    }

    @Override
    public void checkOutputSpecs(JobContext context) throws IOException, InterruptedException {
        // Not implemented yet
    }

    @Override
    public OutputCommitter getOutputCommitter(TaskAttemptContext context) throws IOException, InterruptedException {
        return new MultiCommiter();
    }
}
