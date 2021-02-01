package com.example.flixter.models;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcel;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Plain old java object,
 * encapsulates the idea of a movie
 */
@Parcel
public class Movie {

    String posterPath ;
    String title ;
    String overview;
    int movieID;
    double rating;

    // Empty constructor needed by the Parceler library
    public Movie() {}

    public Movie(JSONObject jsonObject) throws JSONException {

        posterPath = jsonObject.getString("poster_path");
        title = jsonObject.getString("title");
        overview = jsonObject.getString("overview");
        rating = jsonObject.getDouble("vote_average");
        movieID = jsonObject.getInt("id");
    }

    // returns a list of movie objects
    // grabs all the movies returned from the json response, and parse each
    // iterates through each element in the JSON array and constructing a movie object
    // at each iteration
    @NotNull
    public static List<Movie> fromJsonArray (JSONArray movieJsonArray) throws JSONException {

        List<Movie> movies = new ArrayList<>();

        for (int i = 0; i < movieJsonArray.length(); i++){
            movies.add(new Movie(movieJsonArray.getJSONObject(i)));

        }
        return movies ;
    }

    public String getPosterPath() {

        return String.format("https://image.tmdb.org/t/p/w342/%s", posterPath);
    }

    public String getTitle() {
        return title;
    }

    public String getOverview() {
        return overview;
    }

    public double getRating() {
        return rating;
    }

    public int getMovieID() {
        return movieID;
    }
}
