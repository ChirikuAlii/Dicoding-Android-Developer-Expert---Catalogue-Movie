package com.ysn.cataloguemovie.ui.fragments.favorite;

import com.ysn.cataloguemovie.data.manager.DataManager;
import com.ysn.cataloguemovie.ui.base.MvpPresenter;

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

    void onLoadData(DataManager dataManager) {
        // TODO: 10/16/17 do something in here
        dataManager.getAll();
    }
}
