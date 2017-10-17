package com.ysn.cataloguemovie.ui.fragments.favorite;

import com.ysn.cataloguemovie.model.movie.detail.DetailMovie;
import com.ysn.cataloguemovie.ui.base.MvpView;
import com.ysn.cataloguemovie.ui.fragments.favorite.adapter.AdapterFavoriteMovie;

/**
 * Created by yudisetiawan on 10/6/17.
 */

interface FavoriteMovieView extends MvpView {

    void loadData(AdapterFavoriteMovie adapterFavoriteMovie);

    void itemClickDetail(DetailMovie detailMovie);

    void itemClickShare(DetailMovie detailMovie);

}
