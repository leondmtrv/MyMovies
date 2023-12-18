package com.mymovies.mymovies.model;

import jakarta.persistence.*;
import org.json.JSONObject;


@Entity
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String IMDBid;
    private String title;
    private String rated;
    private String released;
    private String runtime;
    private String genre;
    private String posterURL;

    @Column(length = 1000)
    private String plot;
    private String actors;
    private String director;

    private Integer review;


    public void setIMDBid(String IMDBid) {
        this.IMDBid = IMDBid;
    }

    public String getIMDBid() {
        return IMDBid;
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

    public Integer getReview() {
        return review;
    }

    public void setReview(Integer review) {
        this.review = review;
    }

    public JSONObject toJSON() {
        JSONObject json = new JSONObject();
        json.put("id", id);
        json.put("title", title);
        json.put("rated", rated);
        json.put("released", released);
        json.put("runtime", runtime);
        json.put("genre", genre);
        json.put("posterURL", posterURL);
        json.put("plot", plot);
        json.put("actors", actors);
        json.put("director", director);
        return json;
    }

    @Override
    public String toString() {
        return "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", rated='" + rated + '\'' +
                ", released='" + released + '\'' +
                ", runtime='" + runtime + '\'' +
                ", genre='" + genre + '\'' +
                ", posterURL='" + posterURL + '\'' +
                ", plot='" + plot + '\'' +
                ", actors='" + actors + '\'' +
                ", director='" + director + '\'' +
                '}';
    }
}
