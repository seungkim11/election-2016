package acquisition;

import com.google.common.collect.Lists;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import org.bson.Document;
import org.json.simple.JSONObject;

import twitter4j.FilterQuery;
import twitter4j.GeoLocation;
import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;
import twitter4j.conf.ConfigurationBuilder;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import acquisition.TwitterCollector;
import auth.TwitterAuth;
import model.Tweet;
import model.User;

/**
 * Created by samskim on 5/9/16.
 */
public class TwitterSource {
    TwitterStreamFactory tf;
    TwitterAuth auth;
    TwitterCollector collector;
    MongoClient mongoClient;
    MongoDatabase db;
    MongoCollection collection;
    long count;
    int skip;

    public TwitterSource(String skip) {
        auth = new TwitterAuth();
        tf = auth.getStreamFactory();
        collector = new TwitterCollector();
        mongoClient = new MongoClient();
        db = mongoClient.getDatabase("election-2016");
        count = 0;
        if (skip == null || skip.isEmpty()){
            this.skip = 10;
        }else{
            this.skip = Integer.parseInt(skip);
        }
    }

    public void listen(String[] track, boolean save){

        StatusListener listener = new StatusListener() {
            @Override
            public void onStatus(Status status) {
                if (count % skip == 0){
                    Document tweet = collector.parseTweet(status);
                    if (save)  save(tweet);
                    else  System.out.println(tweet);
                }
                count++;

            }

            @Override
            public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {

            }

            @Override
            public void onTrackLimitationNotice(int numberOfLimitedStatuses) {
                try {
                    System.out.println("Limitation Notice number: " + numberOfLimitedStatuses);
                    System.out.println("Sleeping 1 minute");
                    TimeUnit.MINUTES.sleep(1);
                } catch (InterruptedException e) {
                    System.out.println("Sleep failed");
                    e.printStackTrace();
                }
            }

            @Override
            public void onScrubGeo(long userId, long upToStatusId) {

            }

            @Override
            public void onStallWarning(StallWarning warning) {

            }

            @Override
            public void onException(Exception e) {
                e.printStackTrace();
            }
        };

        TwitterStream twitterStream = tf.getInstance();

        twitterStream.addListener(listener);

        FilterQuery fq = new FilterQuery();
        fq.track(track);
        fq.language("en");
        twitterStream.filter(fq);
    }

    private void save(Document tweet) {
        collection = db.getCollection(getCollectionName());
        collection.insertOne(tweet);
        System.out.println("Count: " + count + "Saved "  + tweet.get("id_str") + " at " + Calendar.getInstance().getTime());
    }

    private String getCollectionName(){
        Calendar cal = Calendar.getInstance();
        int month = cal.get(Calendar.MONTH) + 1;
        int day = cal.get(Calendar.DATE);

        return "t" + month + "_" + day;
    }
}
