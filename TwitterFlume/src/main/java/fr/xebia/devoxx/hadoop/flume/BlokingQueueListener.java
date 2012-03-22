package fr.xebia.devoxx.hadoop.flume;

import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;

import java.util.concurrent.BlockingQueue;

public class BlokingQueueListener implements StatusListener {

    private BlockingQueue<String> eventQueue;

    public void setEventQueue(BlockingQueue<String> eventQueue) {
        this.eventQueue = eventQueue;
    }


    public void onStatus(Status status) {
        try {
            eventQueue.put("@" + status.getUser().getScreenName() + " - " + status.getText());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {
        try {
            eventQueue.put("Got a status deletion notice id:" + statusDeletionNotice.getStatusId());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void onTrackLimitationNotice(int numberOfLimitedStatuses) {
        try {
            eventQueue.put("Got track limitation notice:" + numberOfLimitedStatuses);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void onScrubGeo(long userId, long upToStatusId) {
        try {
            eventQueue.put("Got scrub_geo event userId:" + userId + " upToStatusId:" + upToStatusId);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void onException(Exception ex) {
        ex.printStackTrace();
    }
}
