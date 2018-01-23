/*
 * Created by Yudi Setiawan on 1/23/18 10:29 PM
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 1/23/18 10:21 PM
 */

package com.ysn.cataloguemovie.ui.fragments.nowplaying;

import com.ysn.cataloguemovie.model.movie.nowplaying.ResultNowPlaying;
import com.ysn.cataloguemovie.ui.base.MvpView;
import com.ysn.cataloguemovie.ui.fragments.nowplaying.adapter.AdapterNowPlayingMovie;

/**
 * Created by yudisetiawan on 9/16/17.
 */

interface NowPlayingView extends MvpView {

    void itemClickDetail(ResultNowPlaying resultNowPlaying);

    void itemClickShare(ResultNowPlaying resultNowPlaying);

    void loadData(AdapterNowPlayingMovie adapterNowPlayingMovie);

    void loadDataFailed(String message);

}
