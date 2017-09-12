package com.ysn.cataloguemovie.ui.fragments.search;

import android.content.Intent;

import com.ysn.cataloguemovie.ui.base.MvpView;
import com.ysn.cataloguemovie.ui.fragments.search.adapter.AdapterSearchMovie;

/**
 * Created by root on 06/09/17.
 */

interface SearchMovieView extends MvpView {

    void searchMovie(AdapterSearchMovie adapterSearchMovie);

    void searchMovieFailed();

    void onClickItem(Intent intentDetailMovieActivity);

}
