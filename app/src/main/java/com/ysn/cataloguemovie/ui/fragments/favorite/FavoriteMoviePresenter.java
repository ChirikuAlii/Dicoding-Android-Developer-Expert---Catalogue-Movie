package com.ysn.cataloguemovie.ui.fragments.favorite;

import android.content.Context;

import com.ysn.cataloguemovie.data.manager.DataManager;
import com.ysn.cataloguemovie.model.movie.detail.DetailMovie;
import com.ysn.cataloguemovie.ui.base.MvpPresenter;
import com.ysn.cataloguemovie.ui.fragments.favorite.adapter.AdapterFavoriteMovie;

import java.util.List;

/**
 * Created by yudisetiawan on 10/6/17.
 */

class FavoriteMoviePresenter implements MvpPresenter<FavoriteMovieView> {

    private final String TAG = getClass().getSimpleName();
    private FavoriteMovieView favoriteMovieView;

    @Override
    public void onAttach(FavoriteMovieView mvpView) {
        favoriteMovieView = mvpView;
    }

    @Override
    public void onDetach() {
        favoriteMovieView = null;
    }

    void onLoadData(Context context, DataManager dataManager) {
        List<DetailMovie> listFavoriteMovie = dataManager.getAll();
        AdapterFavoriteMovie adapterFavoriteMovie = new AdapterFavoriteMovie(
                context,
                listFavoriteMovie,
                new AdapterFavoriteMovie.ListenerAdapterFavoriteMovie() {
                    @Override
                    public void onItemClickDetail(DetailMovie detailMovie) {
                        favoriteMovieView.itemClickDetail(detailMovie);
                    }

                    @Override
                    public void onItemClickShare(DetailMovie detailMovie) {
                        favoriteMovieView.itemClickShare(detailMovie);
                    }
                }
        );
        favoriteMovieView.loadData(adapterFavoriteMovie);
    }
}
