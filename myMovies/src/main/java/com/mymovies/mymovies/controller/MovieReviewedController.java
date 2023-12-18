package com.mymovies.mymovies.controller;

import com.mymovies.mymovies.dto.MovieReviewedInput;
import com.mymovies.mymovies.model.Movie;
import com.mymovies.mymovies.model.MovieReviewed;
import com.mymovies.mymovies.repository.MovieRepository;
import com.mymovies.mymovies.service.MovieService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import com.mymovies.mymovies.repository.MovieReviewedRepository;
import org.json.JSONObject;
import com.mymovies.mymovies.repository.ProfileRepository;
import com.mymovies.mymovies.model.Profile;

import java.util.ArrayList;
import java.util.List;

import java.util.Optional;

@RestController
public class MovieReviewedController {

    private final MovieReviewedRepository movieReviewedRepository;
    private final MovieRepository movieRepository;
    private final MovieService movieService;

    private final ProfileRepository profileRepository;

    public MovieReviewedController(MovieReviewedRepository movieReviewedRepository,
                                   MovieService movieService, ProfileRepository profileRepository,
                                   MovieRepository movieRepository) {
        this.movieReviewedRepository = movieReviewedRepository;
        this.movieService = movieService;
        this.profileRepository = profileRepository;
        this.movieRepository = movieRepository;
    }

    @PostMapping("/review")
    public String addReview(@RequestBody MovieReviewedInput newReview) {

        MovieReviewed movieReviewed = newReview.toMovieReviewed();
        Profile profile = profileRepository.findById(movieReviewed.getUserID()).orElse(null);
        if (profile == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User not found");
        }

        // Check if movie is already in watchList
        MovieReviewed[] userMovies = movieReviewedRepository.findByUserID(movieReviewed.getUserID());
        for (MovieReviewed m : userMovies) {
            if (m.getMovieID().equals(movieReviewed.getMovieID())) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Movie already reviewed");
            }
        }

        profile.addReviewed(movieReviewed);
        movieReviewedRepository.save(movieReviewed);

        Movie movie = (Movie) movieRepository.findByIMDBid(movieReviewed.getMovieID()).orElse(null);
        profile.deleteMovie(movie);
        profileRepository.save(profile);
        throw new ResponseStatusException(HttpStatus.OK, "Review added");
    }
    @DeleteMapping("/delreview/{userID}/{imdbID}")
    public String deleteReview(@PathVariable("userID") String userID, @PathVariable("imdbID") String imdbID) {

        Profile profile = profileRepository.findById(userID).orElse(null);
        if (profile == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User not found");
        }

        MovieReviewed[] userMovies = movieReviewedRepository.findByUserID(userID);
        for(MovieReviewed movieRev : userMovies){
            if(imdbID.equals(movieRev.getMovieID())){
                System.out.println("I got here");
                profile.deleteReview(movieRev);
                profileRepository.save(profile);
                movieReviewedRepository.delete(movieRev);
                throw new ResponseStatusException(HttpStatus.OK, "Review added");
            }
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Review not found");
    }

    @GetMapping("/review")
    public String getUserReviews(@RequestParam String userID) {
        // Get user's reviews from movieReviewedRepository
        Profile profile = profileRepository.findById(userID).orElse(null);
        if (profile == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User not found");
        }

        // For each movie reviewed, get the movie from MovieRepository
        // and add it to the JSONObject
        JSONObject reviews = new JSONObject();
        List<Movie> movies = new ArrayList<>();
        //JSONObject reviews = new JSONObject();
        for (MovieReviewed movieReviewed : profile.getReviewed()) {
            Optional<Object> movie = movieRepository.findByIMDBid(movieReviewed.getMovieID());

            // If movie is not null, cast object to Movie and add to JSONObject
            if (movie.isPresent()) {
                Movie movieCast = (Movie) movie.get();
                movieCast.setReview(movieReviewed.getReview());
                movies.add(movieCast);
            }
        }
        return reviews.put("reviews", movies).toString();

    }
}
