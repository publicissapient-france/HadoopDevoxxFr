package fr.xebia.devoxx.hadoop.flume;

import com.cloudera.flume.conf.Context;
import com.cloudera.flume.conf.SourceFactory.SourceBuilder;
import com.cloudera.flume.core.Event;
import com.cloudera.flume.core.EventImpl;
import com.cloudera.flume.core.EventSource;
import com.cloudera.util.Pair;
import com.google.common.base.Preconditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import twitter4j.FilterQuery;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;
import twitter4j.conf.ConfigurationBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;


/**
 * Input source for flume
 */
public class TwitterSource extends EventSource.Base {
    private static final Logger LOG = LoggerFactory.getLogger(TwitterSource.class);

    private static BlockingQueue<String> eventQueue = new ArrayBlockingQueue<String>(10000);
    private long[] followArray;
    private String[] trackArray;
    private TwitterStream twitterStream;


    @Override
    public void open() throws IOException {
        twitterStream = initializeTwitterStream();
        twitterStream.filter(new FilterQuery(0, followArray, trackArray));
    }

    @Override
    public Event next() throws IOException {

        try {
            return new EventImpl(eventQueue.take().getBytes());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void close() throws IOException {
        eventQueue.clear();
        twitterStream.cleanUp();
        twitterStream = null;
    }

    public static SourceBuilder builder() {
        return new SourceBuilder() {
            @Override
            public EventSource build(Context ctx, String... argv) {
                Preconditions.checkArgument(argv.length > 0, "usage: twitterSource(ids tracks ...");
                TwitterSource twitterSource = new TwitterSource();
                ArrayList<Long> follow = new ArrayList<Long>();
                ArrayList<String> track = new ArrayList<String>();
                for (String arg : argv) {
                    if (isNumericalArgument(arg)) {
                        for (String id : arg.split(",")) {
                            follow.add(Long.parseLong(id));
                        }
                    } else {
                        track.addAll(Arrays.asList(arg.split(",")));
                    }
                }
                long[] followArray = new long[follow.size()];
                for (int i = 0; i < follow.size(); i++) {
                    followArray[i] = follow.get(i);
                }
                String[] trackArray = track.toArray(new String[track.size()]);

                twitterSource.setFollowArray(followArray);
                twitterSource.setTrackArray(trackArray);

                return twitterSource;
            }
        };
    }

    /**
     * This is a special function used by the SourceFactory to pull in this
     * class as a plugin source.
     *
     * @return builders
     */
    public static List<Pair<String, SourceBuilder>> getSourceBuilders() {
        List<Pair<String, SourceBuilder>> builders = new ArrayList<Pair<String, SourceBuilder>>();
        builders.add(new Pair<String, SourceBuilder>("twitterSource", builder()));
        return builders;
    }

    /**
     * Initialize the stream with credentials and listener
     *
     * @return TwitterStream read to use
     */
    public TwitterStream initializeTwitterStream() {
        BlokingQueueListener listener = new BlokingQueueListener();
        listener.setEventQueue(eventQueue);

        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setOAuthConsumerKey("Y8EkAcIWMSMv1CnUZbyg")
                .setOAuthConsumerSecret("QmxOeZOrMZi4NYI7pTVqNVKLvjzLFOzu4lc133lixaM")
                .setOAuthAccessToken("18357527-LZGlcZkYzL2pUSExqeJh9xMUOWv67gkiBiGuCvTNs")
                .setOAuthAccessTokenSecret("bdEpIYefk7BgQCTKpdW0vWe6Q2dIJZ9nIGBCcZ90WNc");


        TwitterStream twitterStream = new TwitterStreamFactory(cb.build()).getInstance();
        twitterStream.addListener(listener);

        return twitterStream;
    }

    private static boolean isNumericalArgument(String argument) {
        String args[] = argument.split(",");
        boolean isNumericalArgument = true;
        for (String arg : args) {
            try {
                Integer.parseInt(arg);
            } catch (NumberFormatException nfe) {
                isNumericalArgument = false;
                break;
            }
        }
        return isNumericalArgument;
    }

    public void setFollowArray(long[] followArray) {
        this.followArray = followArray;
    }

    public void setTrackArray(String[] trackArray) {
        this.trackArray = trackArray;
    }
}
