package fr.xebia.devoxx.hadoop.sample.output;

import org.apache.hadoop.mapreduce.JobContext;
import org.apache.hadoop.mapreduce.OutputCommitter;
import org.apache.hadoop.mapreduce.TaskAttemptContext;

import java.io.IOException;


public class PushServerOutputCommiter extends OutputCommitter {
    @Override
    public void setupJob(JobContext jobContext) throws IOException {
        // Not implemented
    }

    @Override
    public void setupTask(TaskAttemptContext taskAttemptContext) throws IOException {
        // Not implemented
    }

    @Override
    public boolean needsTaskCommit(TaskAttemptContext taskAttemptContext) throws IOException {
        return false;
    }

    @Override
    public void commitTask(TaskAttemptContext taskAttemptContext) throws IOException {
        // Not implemented
    }

    @Override
    public void abortTask(TaskAttemptContext taskAttemptContext) throws IOException {
        // Not implemented
    }
}
