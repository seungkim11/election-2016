import twitter4j.FilterQuery;
import twitter4j.GeoLocation;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;
import twitter4j.auth.AccessToken;
import twitter4j.conf.ConfigurationBuilder;

import java.util.List;
import java.util.Map;

import acquisition.TwitterSource;
import auth.TwitterAuth;
import model.Tweet;
import model.User;

/**
 * Created by samskim on 5/8/16.
 */
public class TwitterDriver {

    public static void main(String[] args){


        TwitterSource source = new TwitterSource();
        String[] track = {"Bernie Sanders", "Hilary Clinton", "Donald Trump", "Ted Cruz"};
        source.listen(track, true);

    }


}
