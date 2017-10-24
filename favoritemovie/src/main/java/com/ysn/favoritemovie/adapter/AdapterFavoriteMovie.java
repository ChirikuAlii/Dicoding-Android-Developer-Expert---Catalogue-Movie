package com.ysn.favoritemovie.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ysn.favoritemovie.BuildConfig;
import com.ysn.favoritemovie.FavoriteMovie;
import com.ysn.favoritemovie.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by yudisetiawan on 10/24/17.
 */

public class AdapterFavoriteMovie extends RecyclerView.Adapter<AdapterFavoriteMovie.ViewHolderFavoriteMovie> {

    private final String TAG = getClass().getSimpleName();
    private Context context;
    private List<FavoriteMovie> favoriteMovieList;
    private ListenerAdapterFavoriteMovie listenerAdapterFavoriteMovie;

    public AdapterFavoriteMovie(Context context, List<FavoriteMovie> favoriteMovieList,
                                ListenerAdapterFavoriteMovie listenerAdapterFavoriteMovie) {
        this.context = context;
        this.favoriteMovieList = favoriteMovieList;
        this.listenerAdapterFavoriteMovie = listenerAdapterFavoriteMovie;
    }

    @Override
    public ViewHolderFavoriteMovie onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_favorite_movie, null);
        return new ViewHolderFavoriteMovie(view);
    }

    @Override
    public void onBindViewHolder(ViewHolderFavoriteMovie holder, int position) {
        FavoriteMovie favoriteMovie = favoriteMovieList.get(position);
        Log.d(TAG, "favoriteMovie: " + favoriteMovie);
        Glide.with(context)
                .load(BuildConfig.BASE_URL_IMAGE + favoriteMovie.getmPosterPath())
                .placeholder(R.drawable.ic_image_black_24dp)
                .error(R.drawable.ic_broken_image_black_24dp)
                .into(holder.imageViewPosterItemFavoriteMovie);
        holder.textViewJudulFilmItemFavoriteMovie.setText(favoriteMovie.getmTitle());
        holder.textViewDeskripsiFilmItemFavoriteMovie.setText(favoriteMovie.getmOverview());
        holder.textViewTanggalReleaseItemFavoriteMovie.setText(favoriteMovie.getmReleaseDate());
    }

    @Override
    public int getItemCount() {
        return favoriteMovieList.size();
    }

    class ViewHolderFavoriteMovie extends RecyclerView.ViewHolder {

        @BindView(R.id.image_view_poster_item_favorite_movie)
        ImageView imageViewPosterItemFavoriteMovie;
        @BindView(R.id.text_view_judul_film_item_favorite_movie)
        TextView textViewJudulFilmItemFavoriteMovie;
        @BindView(R.id.text_view_deskripsi_film_item_favorite_movie)
        TextView textViewDeskripsiFilmItemFavoriteMovie;
        @BindView(R.id.text_view_tanggal_release_item_favorite_movie)
        TextView textViewTanggalReleaseItemFavoriteMovie;

        public ViewHolderFavoriteMovie(View itemView) {
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
                    // TODO: 10/24/17 do something in here
                    break;
                case R.id.button_share_item_favorite_movie:
                    // TODO: 10/24/17 do something in here
                    break;
            }
        }
    }

    public interface ListenerAdapterFavoriteMovie {

        void onItemClickDetail(FavoriteMovie favoriteMovie);

        void onItemClickShare(FavoriteMovie favoriteMovie);

    }

}
