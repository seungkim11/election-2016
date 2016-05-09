package model;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by samskim on 5/3/16.
 */
public class Tweet {

    private String id;
    private Date date;
    private User user;
    private String text;
    private int retweet_count;
    private List<Tweet> retweets;
    private int favorite_count;
    private Double[] coordinates;

    public Tweet(){

    }

    public Tweet(String id, Date date, User user, String text, int retweet_count, List<Tweet> retweets, int favorite_count, Double[] coordinates) {
        this.id = id;
        this.date = date;
        this.user = user;
        this.text = text;
        this.retweet_count = retweet_count;
        this.retweets = retweets;
        this.favorite_count = favorite_count;
        this.coordinates = coordinates;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getRetweet_count() {
        return retweet_count;
    }

    public void setRetweet_count(int retweet_count) {
        this.retweet_count = retweet_count;
    }

    public List<Tweet> getRetweets() {
        return retweets;
    }

    public void setRetweets(List<Tweet> retweets) {
        this.retweets = retweets;
    }

    public int getFavorite_count() {
        return favorite_count;
    }

    public void setFavorite_count(int favorite_count) {
        this.favorite_count = favorite_count;
    }

    public Double[] getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Double[] coordinates) {
        this.coordinates = coordinates;
    }

    @Override
    public String toString() {
        return "model.Tweet{" +
                "id='" + id + '\'' +
                ", date=" + date +
                ", user=" + user +
                ", text='" + text + '\'' +
                ", retweet_count=" + retweet_count +
                ", retweets=" + retweets +
                ", favorite_count=" + favorite_count +
                ", coordinates=" + Arrays.toString(coordinates) +
                '}';
    }
}
