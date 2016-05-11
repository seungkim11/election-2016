package acquisition;

import com.google.common.collect.Lists;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.Tweet;
import model.User;

/**
 * Created by samskim on 5/3/16.
 */
public class Aggregate {

    private String dataFolderName;
    private Map<String, Tweet> map;

    public static void main(String[] args) {


    }

    public Aggregate() {
        this.map = new HashMap<>();
    }

    public Aggregate(String dataFolderName, String outputFileName) throws IOException {
        this.dataFolderName = dataFolderName;
        this.map = new HashMap<>();
    }


    public void readFiles(String dirname){
        File dir = new File(dirname);
        File[] directoryListing = dir.listFiles();
        if (directoryListing != null){
            for (File child: directoryListing){
                System.out.println("visiting " + child.getName());
                parseJSONFile(child);
            }
        } else {

        }
    }

    public void parseJSONFile(File child) {
        JSONParser parser = new JSONParser();
        JSONArray tweetArray;
        try {
            Object obj = parser.parse(new BufferedReader(new FileReader(child)));
            tweetArray = (JSONArray) obj;
            List<Tweet> tweets = Lists.newArrayList();

            for (Object o : tweetArray) {
                JSONObject tweetObj = (JSONObject) o;


                if (tweetObj.get("id_str") == null) continue;
                Tweet tweet = parseTweet(tweetObj);


                if (map.containsKey(tweet.getId())){
                    Tweet originalTweet = map.get(tweet.getId());
                    List<Tweet> originalRetweets = originalTweet.getRetweets();

                    for (Tweet t: tweet.getRetweets()){
                        originalRetweets.add(t);
                    }

                    originalTweet.setRetweets(originalRetweets);

                    map.put(originalTweet.getId(), originalTweet);

                }else{
                    map.put(tweet.getId(), tweet);
                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    public Tweet parseTweet(JSONObject t) {

        String id = (String) t.get("id_str");
        Date date = new Date((String) t.get("created_at"));
        String text = (String) t.get("text");
        int retweet_count = ((Long) t.get("retweet_count")).intValue();
        List<Tweet> tweets = new ArrayList<Tweet>();
        int favorite_count = ((Long)  t.get("favorite_count")).intValue();
        Double[] coordinates = null;
        if (t.get("coordinates") != null){
            coordinates = new Double[2];
            JSONObject coordObj = (JSONObject) t.get("coordinates");
            JSONArray array = (JSONArray) coordObj.get("coordinates");
            coordinates[0] = (Double) array.get(0);
            coordinates[1] = (Double) array.get(1);
        }
        User user = parseUser(t.get("user"));

        Tweet tweet = new Tweet(id, date, user, text, retweet_count, tweets, favorite_count
                , coordinates);

        if (t.get("retweeted_status") != null){
            Tweet parent = parseTweet((JSONObject) t.get("retweeted_status"));
            List<Tweet> retweets = parent.getRetweets();
            retweets.add(tweet);
            parent.setRetweets(retweets);
            return parent;
        }else{
            return tweet;
        }

    }

    private User parseUser(Object user) {
        JSONObject o = (JSONObject) user;
        String screen_name = (String) o.get("screen_name");
        String location = (String) o.get("location");
        String language = (String) o.get("lang");
        Date created_at = new Date((String) o.get("created_at"));
        int followers_count = ((Long) o.get("followers_count")).intValue();
        int friends_count = ((Long) o.get("friends_count")).intValue();
        String description = (String) o.get("description");
        int listed_count = ((Long) o.get("listed_count")).intValue();
        int favorites_count = ((Long) o.get("favourites_count")).intValue();
        int statuses_count = ((Long) o.get("statuses_count")).intValue();
        User u = new User(screen_name, location, language, created_at, followers_count
        , friends_count, description, listed_count, favorites_count, statuses_count);

        return u;
    }

    public Map<String, Tweet> getMap(){
        return this.map;
    }


}
