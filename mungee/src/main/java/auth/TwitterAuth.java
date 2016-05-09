package auth;

import twitter4j.TwitterStreamFactory;
import twitter4j.conf.ConfigurationBuilder;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by samskim on 5/9/16.
 */
public class TwitterAuth {

    private final String TWITTER_CONSUMER_KEY = "TWITTER_CONSUMER_KEY";
    private final String TWITTER_CONSUMER_SECRET = "TWITTER_CONSUMER_SECRET";
    private final String TWITTER_ACCESS_TOKEN = "TWITTER_ACCESS_TOKEN";
    private final String TWITTER_ACCESS_TOKEN_SECRET = "TWITTER_ACCESS_TOKEN_SECRET";

    private Map<String, String> envMap;

    public TwitterAuth(){
        envMap = new HashMap<>();

    }

    public TwitterStreamFactory getStreamFactory(){
        getEnvMap();

        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey(envMap.get(TWITTER_CONSUMER_KEY))
                .setOAuthConsumerSecret(envMap.get(TWITTER_CONSUMER_SECRET))
                .setOAuthAccessToken(envMap.get(TWITTER_ACCESS_TOKEN))
                .setOAuthAccessTokenSecret(envMap.get(TWITTER_ACCESS_TOKEN_SECRET));

        TwitterStreamFactory tf = new TwitterStreamFactory(cb.build());
        return tf;
    }

    public Map<String, String> getEnvMap(){
        envMap.put(TWITTER_CONSUMER_KEY, System.getenv(TWITTER_CONSUMER_KEY));
        envMap.put(TWITTER_CONSUMER_SECRET, System.getenv(TWITTER_CONSUMER_SECRET));
        envMap.put(TWITTER_ACCESS_TOKEN, System.getenv(TWITTER_ACCESS_TOKEN));
        envMap.put(TWITTER_ACCESS_TOKEN_SECRET, System.getenv(TWITTER_ACCESS_TOKEN_SECRET));

        return envMap;
    }


}
