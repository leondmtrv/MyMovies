package com.mymovies.mymovies.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String username;
    private String password;

    @ManyToMany
    private List<MovieReviewed> reviewed = new ArrayList<>();

    @ManyToMany
    private List<Movie> watchList = new ArrayList<>();

    private Integer watchTime;
    


    public String getId() {
        return id;
    }

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

    public void addMovie(Movie movie) {
        watchList.add(movie);
    }

    public void addReviewed(MovieReviewed movie) {
        reviewed.add(movie);
    }

    public Integer getWatchTime() {
        return watchTime;
    }

    public void updateWatchTime(Integer watchTime) {
        this.watchTime += watchTime;
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

    public void deleteMovie(Movie movie) {
        watchList.remove(movie);
    }

    public void deleteReview(MovieReviewed movie) {
        reviewed.remove(movie);
    }

    @Override
    public String toString() {
        return "id='" + id + '\'' +
                ", username=" + username +
                ", password=" + password +
                ", reviewed=" + reviewed +
                ", watchList=" + watchList;
    }
}
