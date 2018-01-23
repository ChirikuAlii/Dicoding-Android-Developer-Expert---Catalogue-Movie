/*
 * Created by Yudi Setiawan on 1/23/18 10:29 PM
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 1/23/18 10:21 PM
 */

package com.ysn.cataloguemovie.ui.activities.detail;

import com.ysn.cataloguemovie.model.movie.detail.DetailMovie;
import com.ysn.cataloguemovie.ui.base.MvpView;

/**
 * Created by yudisetiawan on 9/10/17.
 */

public interface DetailMovieView extends MvpView {

    void loadData(DetailMovie detailMovie, boolean isFavoriteMovie);

    void loadDataFailed();

    void addToFavoriteMovie();

    void addToFavoriteMovieFailed(String message);

    void deleteFromFavoriteMovie();

    void deleteFromFavoriteMovieFailed(String message);

}
