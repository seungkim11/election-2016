package acquisition;

import org.bson.Document;
import org.json.simple.JSONObject;

import twitter4j.GeoLocation;
import twitter4j.Status;

/**
 * Created by samskim on 5/9/16.
 */
public class TwitterCollector {

    public TwitterCollector(){

    }

    public Document parseUser(twitter4j.User tuser){

        Document user = new Document();
        user.append("screen_name", tuser.getScreenName()).
            append("location", tuser.getLocation()).
            append("created_at" , tuser.getCreatedAt()).
            append("description" , tuser.getDescription()).
            append("listed_count", tuser.getListedCount()).
            append("favourites_count" , tuser.getFavouritesCount()).
            append("flowers_count", tuser.getFollowersCount()).
            append("friends_count", tuser.getFriendsCount()).
            append("lang" , tuser.getLang()).
            append("statuses_count", tuser.getStatusesCount());

        return user;
    }

    public Document parseTweet(Status status){
        Document tweet = new Document();

        tweet.append("id_str", String.valueOf(status.getId())).
            append("created_at", status.getCreatedAt()).
            append("favorite_count" ,status.getFavoriteCount()).
            append("retweet_count", status.getRetweetCount()).
            append("text" , status.getText()).
            append("user" , parseUser(status.getUser()));


        if (status.getGeoLocation() != null){
            GeoLocation loc = status.getGeoLocation();
            Document location = new Document();
            location.append("latitude" , loc.getLatitude());
            location.append("longitude" , loc.getLongitude());
            tweet.append("locations", location);
        }else{
            tweet.append("locations", null);
        }

        if (status.getRetweetedStatus() != null){
            Document retweeted_status = parseTweet(status.getRetweetedStatus());
            tweet.append("retweeted_status", retweeted_status);
        }
        return tweet;
    }

}
