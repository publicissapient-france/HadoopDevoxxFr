package fr.xebia.devoxx.hadoop.common;

import fr.xebia.devoxx.hadoop.mostRt.MostRtLaunch;
import fr.xebia.devoxx.hadoop.occurence.OccurenceLaunch;
import fr.xebia.devoxx.hadoop.sample.SampleLaunch;
import fr.xebia.devoxx.hadoop.tweetscount.TweetsCountLaunch;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;
import org.kohsuke.args4j.CmdLineParser;


public class JobLauncher extends Configured implements Tool {
    public int run(String[] otherArgs) throws Exception {
        // Configuration processed by ToolRunner
        Configuration configuration = getConf();

        // Gestion des arguments
        CommandOption cmdLineOptions = new CommandOption();
        CmdLineParser parser = new CmdLineParser(cmdLineOptions);
        parser.parseArgument(otherArgs);

        // Transfert commandLine -> configuration
        configuration.set(CommandOption.USE_CASE, cmdLineOptions.getUseCase());
        configuration.set(CommandOption.WAIT_FOR_COMPLETION, String.valueOf(cmdLineOptions.isWaitForCompletion()));

        if (cmdLineOptions.getDateFrom() != null) {
            configuration.set(CommandOption.DATE_FROM, cmdLineOptions.getDateFrom());
        }
        if (cmdLineOptions.getDateTo() != null) {

            configuration.set(CommandOption.DATE_TO, cmdLineOptions.getDateTo());
        }

        configuration.set(CommandOption.INPUT_PATH, cmdLineOptions.getInputPath());
        configuration.set(CommandOption.OUTPUT_PATH, cmdLineOptions.getOutputPath());
        configuration.set(CommandOption.HASHTAG, cmdLineOptions.getHashtag());

        Tool tool = null;
        if (cmdLineOptions.getUseCase().equals(SampleLaunch.USE_CASE)) {
            tool = new SampleLaunch();
        } else if (cmdLineOptions.getUseCase().equals(MostRtLaunch.USE_CASE)) {
            tool = new MostRtLaunch();
        } else if (cmdLineOptions.getUseCase().equals(OccurenceLaunch.USE_CASE)) {
            tool = new OccurenceLaunch();
        } else if (cmdLineOptions.getUseCase().equals(TweetsCountLaunch.USE_CASE)) {
            tool = new TweetsCountLaunch();
        } else {
            throw new IllegalArgumentException("No map/reduce for use case " + cmdLineOptions.getUseCase());
        }

        ToolRunner.run(configuration, tool, otherArgs);

        return 0;
    }

    public static void main(String[] args) throws Exception {
        ToolRunner.run(new Configuration(), new JobLauncher(), args);
    }
}
