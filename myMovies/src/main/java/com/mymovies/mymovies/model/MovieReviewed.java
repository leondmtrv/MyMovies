package com.mymovies.mymovies.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.json.JSONObject;

@Entity
public class MovieReviewed {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String userID;

    private String movieID;

    private Integer review;

    private String posterURL;

    private String title;


    public String getId() {
        return id;
    }

    public String getUserID() {
        return userID;
    }

    public String getMovieID() {
        return movieID;
    }

    public Integer getReview() {
        return review;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public void setMovieID(String movieID) {
        this.movieID = movieID;
    }

    public void setReview(Integer review) {
        this.review = review;
    }

    public String getPosterURL() {
        return posterURL;
    }

    public void setPosterURL(String posterURL) {
        this.posterURL = posterURL;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public JSONObject toJSON() {
        JSONObject json = new JSONObject();
        json.put("id", id);
        json.put("userID", userID);
        json.put("movieID", movieID);
        json.put("review", review);
        json.put("posterURL", posterURL);
        json.put("title", title);
        return json;
    }
}
