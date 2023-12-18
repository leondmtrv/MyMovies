package com.mymovies.mymovies.repository;
import com.mymovies.mymovies.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MovieRepository extends JpaRepository<Movie, String>{

    Optional<Object> findByIMDBid(String imdBid);
}
