import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.Map;
import java.util.Set;

import acquisition.Aggregate;
import model.Tweet;
import model.User;

/**
 * Created by samskim on 5/10/16.
 */
public class DataAggregateDriver {

    public static void main(String[] args){

        Aggregate source = new Aggregate();
        source.readFiles("data");
        Map<String, Tweet> map = source.getMap();
        write(map, "output.csv");
//        for (String s: map.keySet()){
//            Tweet tweet = map.get(s);
//            if (tweet.getId().contains("722517588136")){
//                String text = tweet.getText().replaceAll("[,|\\t\\n\\r]", " ");
//                String text = tweet.getText();
//                System.out.println(tweet);
//            }
//        }
    }

    public static void write(Map<String, Tweet> m, String filename){
        try {
            System.out.println("writing to csv file");
            FileWriter fw = new FileWriter(filename);

            fw.write("id");
            fw.append(',');
            fw.append("date");
            fw.append(',');
            fw.append("text");
            fw.append(',');
            fw.append("retweet_count");
            fw.append(',');
            fw.append("favorite_count");
            fw.append(',');
            fw.append("coordinates_lat");
            fw.append(',');
            fw.append("coordinates_long");
            fw.append(',');

            fw.append("user_screen_name");
            fw.append(',');
            fw.append("user_created_at");
            fw.append(',');
            fw.append("user_location");
            fw.append(',');
            fw.append("user_description");
            fw.append(',');
            fw.append("user_followers_count");
            fw.append(',');
            fw.append("user_friends_count");
            fw.append(',');
            fw.append("user_listed_count");
            fw.append(',');
            fw.append("user_favorites_count");
            fw.append(',');
            fw.append("user_statuses_count");


            fw.append('\n');

            for (String s: m.keySet()){
                Tweet t = m.get(s);
                User u = t.getUser();

                String id = t.getId();
                Date date = t.getDate();
                String text = cleanText(t.getText());
                String retweet_count = String.valueOf(t.getRetweet_count());
                String favorite_count = String.valueOf(t.getFavorite_count());
                Double[] coordinates = t.getCoordinates();
                String lat = "";
                String lon = "";
                if (coordinates != null){
                    lat = String.valueOf(coordinates[0]);
                    lon = String.valueOf(coordinates[1]);
                }


                String user_screenName = u.getScreen_name();
                Date user_createdAt = u.getCreated_at();
                String user_location = cleanText(cleanString(u.getLocation()));
                String user_description = cleanText(cleanString(u.getdescription()));
                String user_followers = String.valueOf(u.getFollowers_count());
                String user_friends = String.valueOf(u.getFriends_count());
                String user_listed = String.valueOf(u.getListed_count());
                String user_favorites = String.valueOf(u.getFavorites_count());
                String user_statuses = String.valueOf(u.getStatuses_count());

                // --- appending ---
                fw.write(id);
                fw.append(',');
                fw.write(date.toString());
                fw.append(',');
                fw.write("\"" + text +"\"");
                fw.append(',');
                fw.write(retweet_count);
                fw.append(',');
                fw.write(favorite_count);
                fw.append(',');
                fw.write(String.valueOf(lat));
                fw.append(',');
                fw.write(String.valueOf(lon));
                fw.append(',');

                fw.write(user_screenName);
                fw.append(',');
                fw.write(user_createdAt.toString());
                fw.append(',');
                fw.write(user_location);
                fw.append(',');
                fw.write(user_description);
                fw.append(',');
                fw.write(user_followers);
                fw.append(',');
                fw.write(user_friends);
                fw.append(',');
                fw.write(user_listed);
                fw.append(',');
                fw.write(user_favorites);
                fw.append(',');
                fw.write(user_statuses);

                fw.append('\n');
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String cleanString(String str){
        return (str == null) ? "" : str;
    }

    public static String cleanText(String str){
        return str.replaceAll("[,;\\t\\n\\r]", " ");
    }
}
