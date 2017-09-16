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
