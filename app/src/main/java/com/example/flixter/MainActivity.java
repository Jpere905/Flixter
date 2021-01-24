package com.example.flixter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.codepath.asynchttpclient.AsyncHttpClient;
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler;
import com.example.flixter.models.Movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import okhttp3.Headers;

public class MainActivity extends AppCompatActivity {

    public static final String NOW_PLAYING_URL =
            "https://api.themoviedb.org/3/movie/now_playing?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed" ;
    public static final String TAG = "MainActivity";

    // a member variable movies, will be easier to use in the recyclerview since it's a member var
    List<Movie> movies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // we implemented this class in the build.gradle (:app) file so now we can use it to
        // allow asynchronous processing
        AsyncHttpClient client = new AsyncHttpClient();

        // making a get request on the URL we constructed above
        // Using JSON as that is what the movie API is returning as data
        client.get(NOW_PLAYING_URL, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int i, Headers headers, JSON json) {
                Log.d(TAG, "onSuccess");
                // if it reaches this point, the phone was able to grab data from the internet

                // grab the returned jsonObject
                JSONObject jsonObject = json.jsonObject;

                // extract the results object from our jsonObject
                // extracts the VALUES within KEY results
                // since there is a possibility that a 'results' key will not exist, we need to
                // implement a try,catch exception
                try {
                    // jsonObject is a... JSON object... so,
                    // from jsonObject, create a JSON array from the key 'results' of which its
                    // value is an array
                    JSONArray results = jsonObject.getJSONArray("results");
                    // use a log statement to give us insight on if results came back successfully
                    // or not
                    Log.i(TAG, "Results: " + results.toString());

                    // initialize the movies array with movies
                    movies = Movie.fromJsonArray(results);
                    Log.i(TAG, "Movies:" + movies.size());


                } catch (JSONException e) {
                    Log.e(TAG, "Hit json exception", e);
                    e.printStackTrace();
                }


            }

            @Override
            public void onFailure(int i, Headers headers, String s, Throwable throwable) {
                Log.d(TAG, "onFailure");
            }
        });

    }
}