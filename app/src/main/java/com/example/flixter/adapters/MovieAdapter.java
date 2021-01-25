package com.example.flixter.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.flixter.R;
import com.example.flixter.models.Movie;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {

    // member variables:
    // for inflating a view; a context of where a adapter is being constructed from
    Context context;
    // the actual data that the adapter will need
    List<Movie> movies;

    // constructor
    public MovieAdapter(Context context, List<Movie> movies) {
        this.context = context;
        this.movies = movies;
    }

    // Usually involves inflating a layout from XML and returning the holder
    // i.e. adds a movie and returns it inside a viewholder
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d("MovieAdapter", "onCreateViewHolder");
        // static method on the LayoutInflator class, inflate our custom created item_movie.xml
        View movieView = LayoutInflater.from(context).inflate(R.layout.item_movie, parent, false);

        return new ViewHolder(movieView);
    }

    // Involves populating data into the item through holder
    // i.e. take the position of the data and put it into the view contained inside the viewholder
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d("MovieAdapter", "onBindViewHolder" + position);
        // get the movie and the position
        Movie movie = movies.get(position);

        // bind movie data into viewholder
        holder.bind(movie);
    }

    // Returns the total count of items in the list
    @Override
    public int getItemCount() {
        return movies.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvTitle;
        TextView tvOverview;
        ImageView ivPoster;

        // constructor that defines where the text and image views will come from
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvOverview = itemView.findViewById(R.id.tvOverview);
            ivPoster = itemView.findViewById(R.id.ivPoster);

        }

        public void bind(Movie movie) {
            tvTitle.setText(movie.getTitle());
            tvOverview.setText(movie.getOverview());
            Glide.with(context).load(movie.getPosterPath()).into(ivPoster);

        }
    }
}
