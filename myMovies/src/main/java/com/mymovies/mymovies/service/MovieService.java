package com.mymovies.mymovies.service;

import org.springframework.stereotype.Service;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import com.mymovies.mymovies.model.Movie;
import java.util.HashMap;
import com.mymovies.mymovies.dto.MovieInput;

@Service
public class MovieService {

    private HashMap<String, Movie> movies = new HashMap<>();

    public JSONObject getMovie(String name) {

        // Check if movie is already in the hashmap by matching name with title
        for (Movie movie : movies.values()) {
            if (movie.getTitle().equals(name)) {
                return movie.toJSON();
            }
        }
        try {
            // Construct the URL
            String apiUrl = "http://www.omdbapi.com/?apikey=37db8055&t=" + name + "&plot=full"; // Replace with your API key
            URL url = new URL(apiUrl);

            // Open the connection
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            // Get the response code
            int responseCode = connection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                // Read the response
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line;
                StringBuilder response = new StringBuilder();

                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }

                reader.close();

                connection.disconnect();
                JSONObject jsonObject = new JSONObject(response.toString());

                return jsonObject;
            } else {
                connection.disconnect();
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Movie not found");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Movie not found");
    }

    public Movie getMovieByID(String id) {
        return movies.get(id);
    }

}
