/*
 * Created by Yudi Setiawan on 1/23/18 10:29 PM
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 1/23/18 10:21 PM
 */

package com.ysn.cataloguemovie.ui.fragments.nowplaying;

import android.content.Context;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.ysn.cataloguemovie.BuildConfig;
import com.ysn.cataloguemovie.api.TheMovieApiService;
import com.ysn.cataloguemovie.model.movie.nowplaying.NowPlaying;
import com.ysn.cataloguemovie.model.movie.nowplaying.ResultNowPlaying;
import com.ysn.cataloguemovie.ui.base.MvpPresenter;
import com.ysn.cataloguemovie.ui.fragments.nowplaying.adapter.AdapterNowPlayingMovie;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by yudisetiawan on 9/16/17.
 */

public class NowPlayingPresenter implements MvpPresenter<NowPlayingView> {

    private final String TAG = getClass().getSimpleName();
    private NowPlayingView nowPlayingView;

    @Override
    public void onAttach(NowPlayingView mvpView) {
        nowPlayingView = mvpView;
    }

    @Override
    public void onDetach() {
        nowPlayingView = null;
    }

    void onLoadData(final Context context) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        TheMovieApiService theMovieApiService = retrofit.create(TheMovieApiService.class);
        theMovieApiService.getNowPlayingMovie(BuildConfig.API_KEY, BuildConfig.LANGUAGE)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<NowPlaying>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        /** nothing to do in here */
                    }

                    @Override
                    public void onNext(@NonNull NowPlaying nowPlaying) {
                        AdapterNowPlayingMovie adapterNowPlayingMovie = new AdapterNowPlayingMovie(
                                context,
                                nowPlaying.getResults(),
                                new AdapterNowPlayingMovie.ListenerViewHolderNowPlayingMovie() {

                                    @Override
                                    public void onItemClickDetail(ResultNowPlaying resultNowPlaying) {
                                        nowPlayingView.itemClickDetail(resultNowPlaying);
                                    }

                                    @Override
                                    public void onItemClickShare(ResultNowPlaying resultNowPlaying) {
                                        nowPlayingView.itemClickShare(resultNowPlaying);
                                    }
                                }

                        );
                        nowPlayingView.loadData(adapterNowPlayingMovie);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        e.printStackTrace();
                        nowPlayingView.loadDataFailed(e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        /** nothing to do in here */
                    }
                });
    }
}
