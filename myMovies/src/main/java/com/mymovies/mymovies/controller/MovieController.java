package com.mymovies.mymovies.controller;

import com.mymovies.mymovies.dto.MovieInput;
import com.mymovies.mymovies.repository.MovieRepository;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.RestController;
import com.mymovies.mymovies.service.MovieService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.mymovies.mymovies.model.Movie;
import java.util.List;

@RestController
public class MovieController {
    private final MovieService movieService;
    private final MovieRepository movieRepository;
    public MovieController(MovieService movieService, MovieRepository movieRepository) {
        this.movieService = movieService;
        this.movieRepository = movieRepository;
    }

    @GetMapping("/movie")
    public String movie(@RequestParam(value = "name", defaultValue = "Batman") String name) {

        JSONObject jsonObject = movieService.getMovie(name);
        MovieInput movieInput = new MovieInput();
        movieInput.setIMDBid(jsonObject.getString("imdbID"));
        movieInput.setTitle(jsonObject.getString("Title"));
        movieInput.setRated(jsonObject.getString("Rated"));
        movieInput.setReleased(jsonObject.getString("Released"));
        movieInput.setRuntime(jsonObject.getString("Runtime"));
        movieInput.setGenre(jsonObject.getString("Genre"));
        movieInput.setPosterURL(jsonObject.getString("Poster"));
        movieInput.setPlot(jsonObject.getString("Plot"));
        movieInput.setActors(jsonObject.getString("Actors"));
        movieInput.setDirector(jsonObject.getString("Director"));

        // Check if movie exists in database using IMDBid
        if (movieRepository.findByIMDBid(movieInput.getIMDBid()).isPresent()) {
            return jsonObject.toString();
        }

        Movie movie = movieInput.toMovie();
        movieRepository.save(movie);
        return jsonObject.toString();
    }

    @GetMapping("/movies")
    public List<Movie> movies() {
        return movieRepository.findAll();
    }

    @GetMapping("/movie/trending")
    public String trending() {
        return "Trending Movies";
    }

}
