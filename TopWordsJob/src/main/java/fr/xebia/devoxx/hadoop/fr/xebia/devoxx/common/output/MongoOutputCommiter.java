package fr.xebia.devoxx.hadoop.fr.xebia.devoxx.common.output;

import org.apache.hadoop.mapreduce.JobContext;
import org.apache.hadoop.mapreduce.OutputCommitter;
import org.apache.hadoop.mapreduce.TaskAttemptContext;

import java.io.IOException;

class MongoOutputCommiter extends OutputCommitter {
    @Override
    public void setupJob(JobContext jobContext) throws IOException {
        // Not implemented
    }

    @Override
    public void setupTask(TaskAttemptContext taskContext) throws IOException {
        // Not implemented
    }

    @Override
    public boolean needsTaskCommit(TaskAttemptContext taskContext) throws IOException {
        return false;
    }

    @Override
    public void commitTask(TaskAttemptContext taskContext) throws IOException {
        // Not implemented
    }

    @Override
    public void abortTask(TaskAttemptContext taskContext) throws IOException {
        // Not implemented
    }

    @Override
    public void cleanupJob(JobContext context) throws IOException {
        // Do nothing ?
        // Not deprecated before 1.0
    }
}
