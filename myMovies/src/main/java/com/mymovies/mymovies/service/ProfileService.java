package com.mymovies.mymovies.service;
import java.util.*;

import com.mymovies.mymovies.model.MovieReviewed;
import com.mymovies.mymovies.model.Profile;
import com.mymovies.mymovies.repository.MovieRepository;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.mymovies.mymovies.model.Movie;
import com.mymovies.mymovies.repository.ProfileRepository;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ProfileService {

    private MovieService movieService;
    private ProfileRepository profileRepository;

    private MovieRepository movieRepository;

    public ProfileService(ProfileRepository profileRepository, MovieRepository movieRepository) {
        this.profileRepository = profileRepository;
        this.movieRepository = movieRepository;
    }

    public HashMap<String, JSONObject> getWatchList(String id) {
        // Get a users watchlist
        Profile profile = profileRepository.findById(id).orElse(null);
        HashMap<String, JSONObject> watchList = new HashMap<>();
        for (Movie movie : profile.getWatchList()) {
            watchList.put(movie.getIMDBid(), movie.toJSON());
        }
        return watchList;
    }
    public Integer getWatchTime(String id) {
        Profile profile = profileRepository.findById(id).orElse(null);
        return profile.getWatchTime();
    }


    public void addReview(String id, String movieID, Double rating) {
        // Find user using id in profileRepositry
    }


    private static int extractRuntime(String runtimeString) {
        // Remove any non-digit characters from the runtime string
        String digitsOnly = runtimeString.replaceAll("\\D+", "");

        // Parse the remaining digits as an integer
        int runtime = Integer.parseInt(digitsOnly);

        return runtime;
    }
    public JSONObject getHighestRating(String userID) throws NullPointerException{
            Profile profile = profileRepository.findById(userID).orElse(null);
            if (profile == null) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User not found");
            }
            double highestNumber = -1;
            JSONObject reviews = new JSONObject();
            for (MovieReviewed movieReviewed : profile.getReviewed()) {
                Optional<Object> movie = movieRepository.findByIMDBid(movieReviewed.getMovieID());

                // If movie is not null, cast object to Movie and add to JSONObject
                if (movie.isPresent()) {
                    Movie movieCast = (Movie) movie.get();
                    if(movieReviewed.getReview() != null){
                        Integer a = movieReviewed.getReview();
                        if(a>highestNumber) {
                            reviews.clear();
                            reviews.put(movieCast.toString(), movieReviewed.getReview());
                            highestNumber = a;
                        }
                    }
                }
            }
            return reviews;


    }

}
