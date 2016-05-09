package model;

import java.util.Date;

/**
 * Created by samskim on 5/3/16.
 */
public class User {

    private String screen_name;
    private String location;
    private String language;
    private Date created_at;
    private int followers_count; // The number of people following this user
    private int friends_count; // The number of people this user is following
    private String description;
    private int listed_count; // The number of public lists that this user is a member of.
    private int favorites_count; // The number of tweets that this user favorited
    private int statuses_count; // The number of tweets (including retweets) issued by the user.

    public User(){

    }

    public User(String screen_name, String location, String language, Date created_at, int followers_count, int friends_count, String desccription, int listed_count, int favorites_count, int statuses_count) {
        this.screen_name = screen_name;
        this.location = location;
        this.language = language;
        this.created_at = created_at;
        this.followers_count = followers_count;
        this.friends_count = friends_count;
        this.description = desccription;
        this.listed_count = listed_count;
        this.favorites_count = favorites_count;
        this.statuses_count = statuses_count;
    }

    public String getScreen_name() {
        return screen_name;
    }

    public void setScreen_name(String screen_name) {
        this.screen_name = screen_name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public int getFollowers_count() {
        return followers_count;
    }

    public void setFollowers_count(int followers_count) {
        this.followers_count = followers_count;
    }

    public int getFriends_count() {
        return friends_count;
    }

    public void setFriends_count(int friends_count) {
        this.friends_count = friends_count;
    }

    public String getdescription() {
        return description;
    }

    public void setdescription(String description) {
        this.description = description;
    }

    public int getListed_count() {
        return listed_count;
    }

    public void setListed_count(int listed_count) {
        this.listed_count = listed_count;
    }

    public int getFavorites_count() {
        return favorites_count;
    }

    public void setFavorites_count(int favorites_count) {
        this.favorites_count = favorites_count;
    }

    public int getStatuses_count() {
        return statuses_count;
    }

    public void setStatuses_count(int statuses_count) {
        this.statuses_count = statuses_count;
    }

    @Override
    public String toString() {
        return "model.User{" +
                "screen_name='" + screen_name + '\'' +
                ", location='" + location + '\'' +
                ", language='" + language + '\'' +
                ", created_at=" + created_at +
                ", followers_count=" + followers_count +
                ", friends_count=" + friends_count +
                ", description='" + description + '\'' +
                ", listed_count=" + listed_count +
                ", favorites_count=" + favorites_count +
                ", statuses_count=" + statuses_count +
                '}';
    }
}
