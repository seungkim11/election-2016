import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import acquisition.TweetSource;
import model.Tweet;

/**
 * Created by samskim on 5/3/16.
 */
public class practice {

    public static void main(String[] args){

        /*String datestr = "Mon Apr 25 21:09:54 +0000 2016";
        Date date = new Date(datestr);
        System.out.println(date.getMonth() + "/" + date.getDate());*/

        String retweet = "{  \n" +
                "   \"in_reply_to_status_id_str\":null,\n" +
                "   \"in_reply_to_status_id\":null,\n" +
                "   \"in_reply_to_user_id_str\":null,\n" +
                "   \"created_at\":\"Mon Apr 25 16:11:12 +0000 2016\",\n" +
                "   \"source\":\"<a href=\\\"http:\\/\\/dlvr.it\\\" rel=\\\"nofollow\\\">dlvr.it<\\/a>\",\n" +
                "   \"retweet_count\":0,\n" +
                "   \"retweeted\":false,\n" +
                "   \"geo\":{  \n" +
                "      \"coordinates\":[  \n" +
                "         49.26416679,\n" +
                "         -123.11445313\n" +
                "      ],\n" +
                "      \"type\":\"Point\"\n" +
                "   },\n" +
                "   \"filter_level\":\"low\",\n" +
                "   \"is_quote_status\":false,\n" +
                "   \"in_reply_to_screen_name\":null,\n" +
                "   \"id_str\":\"724631858013564929\",\n" +
                "   \"in_reply_to_user_id\":null,\n" +
                "   \"favorite_count\":0,\n" +
                "   \"text\":\"Despite New York Loss, Bernie Sanders Can't Stop Bashing Israel: Sanders said that poverty in the worst areas of\\u2026 https:\\/\\/t.co\\/9sAe0Hvdec\",\n" +
                "   \"id\":724631858013564929,\n" +
                "   \"place\":{  \n" +
                "      \"country_code\":\"CA\",\n" +
                "      \"country\":\"Canada\",\n" +
                "      \"full_name\":\"Vancouver, British Columbia\",\n" +
                "      \"bounding_box\":{  \n" +
                "         \"coordinates\":[  \n" +
                "            [  \n" +
                "               [  \n" +
                "                  -123.224215,\n" +
                "                  49.19854\n" +
                "               ],\n" +
                "               [  \n" +
                "                  -123.224215,\n" +
                "                  49.316738\n" +
                "               ],\n" +
                "               [  \n" +
                "                  -123.022947,\n" +
                "                  49.316738\n" +
                "               ],\n" +
                "               [  \n" +
                "                  -123.022947,\n" +
                "                  49.19854\n" +
                "               ]\n" +
                "            ]\n" +
                "         ],\n" +
                "         \"type\":\"Polygon\"\n" +
                "      },\n" +
                "      \"place_type\":\"city\",\n" +
                "      \"name\":\"Vancouver\",\n" +
                "      \"attributes\":{  \n" +
                "\n" +
                "      },\n" +
                "      \"id\":\"1e5cb4d0509db554\",\n" +
                "      \"url\":\"https:\\/\\/api.twitter.com\\/1.1\\/geo\\/id\\/1e5cb4d0509db554.json\"\n" +
                "   },\n" +
                "   \"lang\":\"en\",\n" +
                "   \"favorited\":false,\n" +
                "   \"possibly_sensitive\":false,\n" +
                "   \"coordinates\":{  \n" +
                "      \"coordinates\":[  \n" +
                "         -123.11445313,\n" +
                "         49.26416679\n" +
                "      ],\n" +
                "      \"type\":\"Point\"\n" +
                "   },\n" +
                "   \"truncated\":false,\n" +
                "   \"timestamp_ms\":\"1461600672616\",\n" +
                "   \"entities\":{  \n" +
                "      \"urls\":[  \n" +
                "         {  \n" +
                "            \"display_url\":\"dlvr.it\\/L8LxwV\",\n" +
                "            \"indices\":[  \n" +
                "               114,\n" +
                "               137\n" +
                "            ],\n" +
                "            \"expanded_url\":\"http:\\/\\/dlvr.it\\/L8LxwV\",\n" +
                "            \"url\":\"https:\\/\\/t.co\\/9sAe0Hvdec\"\n" +
                "         }\n" +
                "      ],\n" +
                "      \"hashtags\":[  \n" +
                "\n" +
                "      ],\n" +
                "      \"user_mentions\":[  \n" +
                "\n" +
                "      ],\n" +
                "      \"symbols\":[  \n" +
                "\n" +
                "      ]\n" +
                "   },\n" +
                "   \"contributors\":null,\n" +
                "   \"_id\":{  \n" +
                "      \"$oid\":\"571e41a3052cb3216bc38a04\"\n" +
                "   },\n" +
                "   \"user\":{  \n" +
                "      \"utc_offset\":-25200,\n" +
                "      \"friends_count\":4381,\n" +
                "      \"profile_image_url_https\":\"https:\\/\\/pbs.twimg.com\\/profile_images\\/378800000775244567\\/ba8aacb749d186534e40be8e2b73dafc_normal.png\",\n" +
                "      \"profile_background_image_url\":\"http:\\/\\/pbs.twimg.com\\/profile_background_images\\/378800000124908648\\/be9b6fc1c7e10340658b3857da89a6e0.png\",\n" +
                "      \"listed_count\":254,\n" +
                "      \"default_profile_image\":false,\n" +
                "      \"favourites_count\":0,\n" +
                "      \"is_translator\":false,\n" +
                "      \"description\":\"Keeping up-to-date on social exclusion worldwide | Poverty | Inequality | Education | Child -Teen Health | Mental disorders | LGBTQ | Not-for-Profit initiative\",\n" +
                "      \"created_at\":\"Thu Apr 29 13:56:48 +0000 2010\",\n" +
                "      \"profile_background_image_url_https\":\"https:\\/\\/pbs.twimg.com\\/profile_background_images\\/378800000124908648\\/be9b6fc1c7e10340658b3857da89a6e0.png\",\n" +
                "      \"protected\":false,\n" +
                "      \"screen_name\":\"Pairsonnalites\",\n" +
                "      \"id_str\":\"138418838\",\n" +
                "      \"profile_link_color\":\"0084B4\",\n" +
                "      \"id\":138418838,\n" +
                "      \"geo_enabled\":true,\n" +
                "      \"profile_background_color\":\"FFFFFF\",\n" +
                "      \"lang\":\"en\",\n" +
                "      \"profile_sidebar_border_color\":\"FFFFFF\",\n" +
                "      \"profile_text_color\":\"333333\",\n" +
                "      \"verified\":false,\n" +
                "      \"profile_image_url\":\"http:\\/\\/pbs.twimg.com\\/profile_images\\/378800000775244567\\/ba8aacb749d186534e40be8e2b73dafc_normal.png\",\n" +
                "      \"time_zone\":\"Pacific Time (US & Canada)\",\n" +
                "      \"url\":\"http:\\/\\/uk.pairsonnalites.org\",\n" +
                "      \"contributors_enabled\":false,\n" +
                "      \"profile_banner_url\":\"https:\\/\\/pbs.twimg.com\\/profile_banners\\/138418838\\/1385144474\",\n" +
                "      \"profile_background_tile\":false,\n" +
                "      \"follow_request_sent\":null,\n" +
                "      \"statuses_count\":1000868,\n" +
                "      \"profile_use_background_image\":true,\n" +
                "      \"followers_count\":4676,\n" +
                "      \"default_profile\":false,\n" +
                "      \"following\":null,\n" +
                "      \"name\":\"Stigmabase | ORG\",\n" +
                "      \"profile_sidebar_fill_color\":\"DDEEF6\",\n" +
                "      \"location\":\"Americas | UK | Ireland\",\n" +
                "      \"notifications\":null\n" +
                "   }\n" +
                "}";

        TweetSource source = new TweetSource();

        JSONParser parser = new JSONParser();

        try {
            JSONObject obj = (JSONObject) parser.parse(retweet);
            Tweet tweet = source.parseTweet(obj);

            System.out.println(tweet);

        } catch (ParseException e) {
            e.printStackTrace();
        }


    }
}
