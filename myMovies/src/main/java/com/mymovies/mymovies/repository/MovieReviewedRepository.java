package com.mymovies.mymovies.repository;
import com.mymovies.mymovies.model.MovieReviewed;
import org.springframework.data.jpa.repository.JpaRepository;
public interface MovieReviewedRepository extends JpaRepository<MovieReviewed, String> {
    MovieReviewed[] findByUserID(String userID);

}
