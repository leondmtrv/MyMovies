package com.mymovies.mymovies.dto;
import com.mymovies.mymovies.model.Movie;
public class MovieInput {

    private String IMDBid;
    private String title;
    private String rated;
    private String released;
    private String runtime;
    private String genre;
    private String posterURL;
    private String plot;
    private String actors;
    private String director;

    public String getIMDBid() {
        return IMDBid;
    }

    public void setIMDBid(String IMDBid) {
        this.IMDBid = IMDBid;
    }

    public String getTitle() {
        return title;
    }

    public String getRated() {
        return rated;
    }

    public String getReleased() {
        return released;
    }

    public String getRuntime() {
        return runtime;
    }

    public String getGenre() {
        return genre;
    }

    public String getPosterURL() {
        return posterURL;
    }

    public String getPlot() {
        return plot;
    }

    public String getActors() {
        return actors;
    }

    public String getDirector() {
        return director;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setRated(String rated) {
        this.rated = rated;
    }

    public void setReleased(String released) {
        this.released = released;
    }

    public void setRuntime(String runtime) {
        this.runtime = runtime;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setPosterURL(String posterURL) {
        this.posterURL = posterURL;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public Movie toMovie() {
        Movie movie = new Movie();
        movie.setIMDBid(IMDBid);
        movie.setTitle(title);
        movie.setRated(rated);
        movie.setReleased(released);
        movie.setRuntime(runtime);
        movie.setGenre(genre);
        movie.setPosterURL(posterURL);
        movie.setPlot(plot);
        movie.setActors(actors);
        movie.setDirector(director);
        return movie;
    }
}
