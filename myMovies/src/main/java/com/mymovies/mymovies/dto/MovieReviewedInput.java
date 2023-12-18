package com.mymovies.mymovies.dto;

import com.mymovies.mymovies.model.MovieReviewed;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


public class MovieReviewedInput {


        private String userID;

        private String movieID;

        private Integer review;

        public String getUserID() {
            return userID;
        }

        public String getMovieID() {
            return movieID;
        }

        public Integer getReview() {
            return review;
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

        public MovieReviewed toMovieReviewed() {
            MovieReviewed movieReviewed = new MovieReviewed();
            movieReviewed.setMovieID(this.movieID);
            movieReviewed.setReview(this.review);
            movieReviewed.setUserID(this.userID);
            return movieReviewed;
        }
}
