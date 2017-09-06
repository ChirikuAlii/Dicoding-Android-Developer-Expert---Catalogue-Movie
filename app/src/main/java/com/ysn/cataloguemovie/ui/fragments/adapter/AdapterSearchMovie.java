package com.ysn.cataloguemovie.ui.fragments.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ysn.cataloguemovie.R;
import com.ysn.cataloguemovie.model.movie.search.ResultSearchMovie;
import com.ysn.cataloguemovie.model.movie.search.SearchMovie;

import java.util.List;

/**
 * Created by root on 06/09/17.
 */

public class AdapterSearchMovie extends RecyclerView.Adapter<AdapterSearchMovie.ViewHolderSearchMovie> {

    private final String TAG = getClass().getSimpleName();
    private Context context;
    private List<ResultSearchMovie> listResultSearchMovies;

    public AdapterSearchMovie(Context context, List<ResultSearchMovie> listResultSearchMovies) {
        this.context = context;
        this.listResultSearchMovies = listResultSearchMovies;
    }

    @Override
    public ViewHolderSearchMovie onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_search_movie, null);
        return new ViewHolderSearchMovie(view);
    }

    @Override
    public void onBindViewHolder(ViewHolderSearchMovie holder, int position) {
        ResultSearchMovie resultSearchMovie = listResultSearchMovies.get(position);
        Glide.with(context)
                .load(resultSearchMovie.getPosterPath())
                .into(holder.imageViewPosterViewHolderSearchMovie);
        holder.textViewTitleMovieViewHolderSearchMovie.setText(resultSearchMovie.getTitle());
        holder.textViewDescriptionMovieViewHolderSearchMovie.setText(resultSearchMovie.getOverview());
        holder.textViewReleaseDateViewHolderSearchMovie.setText(resultSearchMovie.getReleaseDate());
    }

    @Override
    public int getItemCount() {
        return listResultSearchMovies.size();
    }

    public void refreshData(List<ResultSearchMovie> listResultSearchMovies) {
        this.listResultSearchMovies = listResultSearchMovies;
        notifyDataSetChanged();
    }

    class ViewHolderSearchMovie extends RecyclerView.ViewHolder {

        private ImageView imageViewPosterViewHolderSearchMovie;
        private TextView textViewTitleMovieViewHolderSearchMovie;
        private TextView textViewDescriptionMovieViewHolderSearchMovie;
        private TextView textViewReleaseDateViewHolderSearchMovie;

        public ViewHolderSearchMovie(View itemView) {
            super(itemView);
            initViews(itemView);
        }

        private void initViews(View itemView) {
            imageViewPosterViewHolderSearchMovie = (ImageView) itemView
                    .findViewById(R.id.image_view_poster_item_search_movie);
            textViewTitleMovieViewHolderSearchMovie = (TextView) itemView
                    .findViewById(R.id.text_view_juduL_film_item_search_movie);
            textViewDescriptionMovieViewHolderSearchMovie = (TextView) itemView
                    .findViewById(R.id.text_view_deskripsi_film_item_searc_movie);
            textViewReleaseDateViewHolderSearchMovie = (TextView) itemView
                    .findViewById(R.id.text_view_tanggal_release_item_search_movie);
        }
    }

}
