package com.mymovies.mymovies.controller;
import com.mymovies.mymovies.dto.MovieInput;
import com.mymovies.mymovies.model.Profile;
import com.mymovies.mymovies.repository.ProfileRepository;
import com.mymovies.mymovies.service.ProfileService;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import com.mymovies.mymovies.model.Movie;
import com.mymovies.mymovies.dto.ProfileInput;
import java.util.List;
import com.mymovies.mymovies.repository.MovieRepository;
@RestController
public class ProfileController {
    private final ProfileService profileService;
    private final ProfileRepository profileRepository;

    private final MovieRepository movieRepository;
    public ProfileController(ProfileService profileService, ProfileRepository profileRepository,
                             MovieRepository movieRepository) {
        this.profileService = profileService;
        this.profileRepository = profileRepository;
        this.movieRepository = movieRepository;
    }

    @GetMapping("/user/{username}/{password}")
    public Profile getUser(@PathVariable("username") String username, @PathVariable("password") String password) {
        Profile profile = (Profile) profileRepository.findByUsername(username).orElse(null);
        if (profile == null) {
            return null;

        }
        if (!profile.getPassword().equals(password)) {
            return null;

        }
        return profile;
    }

    @GetMapping("/user/watchlist")
    public String watchList(@RequestParam(value = "id") String id) {
        // Get user's watchList from profileRepositry
        Profile profile = profileRepository.findById(id).orElse(null);
        if (profile == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }
        // Convert watchList to JSON\
        JSONObject json = new JSONObject();
        json.put("watchList", profile.getWatchList());
        return json.toString();
    }

    @PostMapping("/user")
    @ResponseStatus(HttpStatus.CREATED)
    public Profile addUser(@RequestBody ProfileInput user) {
        // Check if username already exists
        if (profileRepository.findByUsername(user.getUsername()).isPresent()) {
            return null;
        }
        Profile newProfile = user.toUser();
        profileRepository.save(newProfile);
        return newProfile;
    }

    @PostMapping("/user/watchlist/{userID}/{imdbID}")
    public String addToWatchList(@PathVariable("userID") String userID , @PathVariable("imdbID") String id) {
        // Get movie from movieRepository using imdbID
        Movie newMovie = (Movie) movieRepository.findByIMDBid(id).orElse(null);
        if (newMovie == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Movie not found");
        }

        Profile profile = profileRepository.findById(userID).orElse(null);
        if (profile == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }

        // Check if movie is already in watchList
        if (profile.getWatchList().contains(newMovie)) {
            return null;
        }

        profile.addMovie(newMovie);
        profileRepository.save(profile);
        return newMovie.getTitle();
    }

    @GetMapping("/user")
    public List<Profile> getUsers() {
        // Get all users from database
        return profileRepository.findAll();
    }

    @GetMapping("/user/{id}/watchtime")
    public String getWatchTime(@PathVariable("id") String id) {
        Profile profile = profileRepository.findById(id).orElse(null);
        if (profile == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }
        return profile.getWatchTime().toString();
    }

    @DeleteMapping("/user/watchlist/{userID}/{movieID}")
    public String deleteFromWatchList(@PathVariable("userID") String id, @PathVariable("movieID") String movieID) {
        Profile profile = profileRepository.findById(id).orElse(null);
        if (profile == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }
        Movie movie = (Movie) movieRepository.findByIMDBid(movieID).orElse(null);
        if (movie == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Movie not found");
        }

        profile.deleteMovie(movie);
        profileRepository.save(profile);
        throw new ResponseStatusException(HttpStatus.OK, "Movie removed from watchlist");
    }
    @GetMapping({"/user/rating/highest/{userID}"})
    public String highestRating(@PathVariable("userID") String userID) {
        //try {
            JSONObject jsonObject = this.profileService.getHighestRating(userID);
            System.out.println(jsonObject);
            return jsonObject.toString();
        //}
        //catch(NullPointerException e){
        //    return "[]";
        //}
    }



}
