package com.ysn.cataloguemovie.ui.fragments.upcoming;

import com.ysn.cataloguemovie.model.movie.upcoming.ResultUpcomingMovie;
import com.ysn.cataloguemovie.ui.base.MvpView;
import com.ysn.cataloguemovie.ui.fragments.upcoming.adapter.AdapterUpcomingMovie;

/**
 * Created by yudisetiawan on 9/16/17.
 */

interface UpcomingMovieView extends MvpView {

    void itemClickDetail(ResultUpcomingMovie resultUpcomingMovie);

    void itemClickShare(ResultUpcomingMovie resultUpcomingMovie);

    void loadData(AdapterUpcomingMovie adapterUpcomingMovie);

    void loadDataFailed(String message);

}
