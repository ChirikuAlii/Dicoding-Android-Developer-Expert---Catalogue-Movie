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
