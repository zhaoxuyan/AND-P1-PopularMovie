package com.example.zhaoxuyan.p1_popularmovie;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

/**
 * Created by zhaoxuyan on 2017/12/25.
 * MovieAdapter
 *
 * part[0]:movieTitle
 * part[1]:moviePoster
 * part[2]:movieOverview
 * part[3]:movieScore
 * part[4]:moviePopularity
 * part[5]:movieRelease
 */

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieAdapterViewHolder> {
    public String[] mMovieData;

    private final MovieAdapterOnClickHandler mClickHandler;

    private Context context;

    @Override
    public MovieAdapterViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        context = viewGroup.getContext();
        int layoutIdForListItem = R.layout.list_item;
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(layoutIdForListItem, viewGroup, false);
        return new MovieAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MovieAdapterViewHolder holder, int position) {
        String currentMovie = mMovieData[position];
        String[] parts = currentMovie.split("-");
        String moviePosterPath = parts[1];
        // Ex: https://image.tmdb.org/t/p/w185/nBNZadXqJSdt05SHLqgT0HuC5Gm.jpg
        Picasso.with(context)
                .load("https://image.tmdb.org/t/p/w342" + moviePosterPath)
                .into(holder.mMovieImageView);
    }

    @Override
    public int getItemCount() {
        if (null == mMovieData) return 0;
        return mMovieData.length;
    }

    public interface MovieAdapterOnClickHandler {
        void onClick(String movieDetail);
    }

    public MovieAdapter(MovieAdapterOnClickHandler clickHandler) {
        mClickHandler = clickHandler;
    }

    public class MovieAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        final ImageView mMovieImageView;

        public MovieAdapterViewHolder(View view) {
            super(view);
            mMovieImageView = (ImageView) view.findViewById(R.id.iv_poster);
            view.setOnClickListener(this);
        }

        /**
         * part[0]:movieTitle
         * part[1]:moviePoster
         * part[2]:movieOverview
         * part[3]:movieScore
         * part[4]:moviePopularity
         * part[5]:movieRelease
         */
        @Override
        public void onClick(View v) {
            int adapterPosition = getAdapterPosition();
            String currentMovie = mMovieData[adapterPosition];
            String[] parts = currentMovie.split("-");
            String movieDetail = parts[0] + "-"
                    + parts[1] + "-"
                    + parts[2] + "-"
                    + parts[3] + "-"
                    + parts[5] + "-";

            mClickHandler.onClick(movieDetail);
        }
    }
    public void setMovieData(String[] MovieData) {
        mMovieData = MovieData;
        notifyDataSetChanged();
    }
}
