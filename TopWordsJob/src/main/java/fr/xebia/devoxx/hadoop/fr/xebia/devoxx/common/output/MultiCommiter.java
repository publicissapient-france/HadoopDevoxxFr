package fr.xebia.devoxx.hadoop.fr.xebia.devoxx.common.output;

import org.apache.hadoop.mapreduce.JobContext;
import org.apache.hadoop.mapreduce.OutputCommitter;
import org.apache.hadoop.mapreduce.TaskAttemptContext;

import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: pablo_lopez
 * Date: 04/04/12
 * Time: 09:16
 * To change this template use File | Settings | File Templates.
 */
public class MultiCommiter extends OutputCommitter {
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
