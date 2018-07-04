package com.example.sezgink.materialschat;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

//Adapter class for test with Recycler View
public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MyViewHolder> {


    private List<Movie> movieList;
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView movieYear,movieName;
        public MyViewHolder(View itemView) {
            super(itemView);
            movieName = itemView.findViewById(R.id.movieName);
            movieYear = itemView.findViewById(R.id.movieYear);
        }
    }

    public MovieAdapter(List<Movie> list) {
        movieList = list;
    }

    @Override
    public MovieAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movie_list_row, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieAdapter.MyViewHolder holder, int position) {
        Movie movie = movieList.get(position);
        holder.movieName.setText(movie.name);
        holder.movieYear.setText(movie.year);
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }
}
