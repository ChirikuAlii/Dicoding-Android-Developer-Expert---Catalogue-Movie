/*
 * Created by Yudi Setiawan on 1/23/18 10:29 PM
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 1/23/18 10:21 PM
 */

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
