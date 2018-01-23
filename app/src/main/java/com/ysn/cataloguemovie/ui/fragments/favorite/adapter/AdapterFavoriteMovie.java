/*
 * Created by Yudi Setiawan on 1/23/18 10:29 PM
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 1/23/18 10:21 PM
 */

package com.ysn.cataloguemovie.ui.fragments.favorite.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ysn.cataloguemovie.BuildConfig;
import com.ysn.cataloguemovie.R;
import com.ysn.cataloguemovie.model.movie.detail.DetailMovie;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by yudisetiawan on 10/16/17.
 */

public class AdapterFavoriteMovie extends RecyclerView.Adapter<AdapterFavoriteMovie.ViewHolderItemFavoriteMovie> {

    private final String TAG = getClass().getSimpleName();
    private Context context;
    private List<DetailMovie> detailMovieList;
    private ListenerAdapterFavoriteMovie listenerAdapterFavoriteMovie;

    public AdapterFavoriteMovie(Context context, List<DetailMovie> detailMovieList,
                                ListenerAdapterFavoriteMovie listenerAdapterFavoriteMovie) {
        this.context = context;
        this.detailMovieList = detailMovieList;
        this.listenerAdapterFavoriteMovie = listenerAdapterFavoriteMovie;
    }

    @Override
    public ViewHolderItemFavoriteMovie onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_favorite_movie, null);
        return new ViewHolderItemFavoriteMovie(view);
    }

    @Override
    public void onBindViewHolder(ViewHolderItemFavoriteMovie holder, int position) {
        DetailMovie detailMovie = detailMovieList.get(position);
        Glide.with(context)
                .load(BuildConfig.BASE_URL_IMAGE + detailMovie.getPosterPath())
                .placeholder(R.drawable.ic_image_black_24dp)
                .error(R.drawable.ic_broken_image_black_24dp)
                .into(holder.imageViewPosterItemFavoriteMovie);
        holder.textViewJudulFilmItemFavoriteMovie.setText(detailMovie.getTitle());
        holder.textViewDekripsiFilmItemFavoriteMovie.setText(detailMovie.getOverview());
        holder.textViewTanggalReleaseItemFavoriteMovie.setText(detailMovie.getReleaseDate());
    }

    @Override
    public int getItemCount() {
        return detailMovieList.size();
    }

    class ViewHolderItemFavoriteMovie extends RecyclerView.ViewHolder {

        @BindView(R.id.image_view_poster_item_favorite_movie)
        ImageView imageViewPosterItemFavoriteMovie;
        @BindView(R.id.text_view_judul_film_item_favorite_movie)
        TextView textViewJudulFilmItemFavoriteMovie;
        @BindView(R.id.text_view_deskripsi_film_item_favorite_movie)
        TextView textViewDekripsiFilmItemFavoriteMovie;
        @BindView(R.id.text_view_tanggal_release_item_favorite_movie)
        TextView textViewTanggalReleaseItemFavoriteMovie;

        ViewHolderItemFavoriteMovie(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @OnClick({
                R.id.button_detail_item_favorite_movie,
                R.id.button_share_item_favorite_movie
        })
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.button_detail_item_favorite_movie:
                    listenerAdapterFavoriteMovie
                            .onItemClickDetail(detailMovieList.get(getAdapterPosition()));
                    break;
                case R.id.button_share_item_favorite_movie:
                    listenerAdapterFavoriteMovie
                            .onItemClickShare(detailMovieList.get(getAdapterPosition()));
                    break;
            }
        }
    }

    public interface ListenerAdapterFavoriteMovie {

        void onItemClickDetail(DetailMovie detailMovie);

        void onItemClickShare(DetailMovie detailMovie);

    }

}
