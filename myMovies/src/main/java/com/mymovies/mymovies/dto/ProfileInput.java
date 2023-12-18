package com.mymovies.mymovies.dto;

import com.mymovies.mymovies.model.Movie;
import com.mymovies.mymovies.model.MovieReviewed;
import com.mymovies.mymovies.model.Profile;

import java.util.ArrayList;
import java.util.List;

public class ProfileInput {

    private String username;
    private String password;

    private List<MovieReviewed> reviewed = new ArrayList<>();

    private List<Movie> watchList = new ArrayList<>();

    private Integer watchTime = 0;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public List<MovieReviewed> getReviewed() {
        return reviewed;
    }

    public List<Movie> getWatchList() {
        return watchList;
    }

    public Integer getWatchTime() {
        return watchTime;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password= password;
    }

    public void setReviewed(List<MovieReviewed> reviewed) {
        this.reviewed = reviewed;
    }

    public void setWatchList(List<Movie> watchList) {
        this.watchList = watchList;
    }

    public void setWatchTime(Integer watchTime) {
        this.watchTime = watchTime;
    }

    public Profile toUser() {
        Profile profile = new Profile();
        profile.setUsername(this.username);
        profile.setPassword(this.password);
        profile.setWatchList(this.watchList);
        profile.setReviewed(this.reviewed);
        profile.setWatchTime(this.watchTime);
        return profile;
    }

}
