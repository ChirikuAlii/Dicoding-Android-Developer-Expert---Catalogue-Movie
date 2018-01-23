/*
 * Created by Yudi Setiawan on 1/23/18 10:29 PM
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 1/23/18 10:21 PM
 */

package com.ysn.cataloguemovie.ui.fragments.search;

import android.content.Context;
import android.content.Intent;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.ysn.cataloguemovie.BuildConfig;
import com.ysn.cataloguemovie.api.TheMovieApiService;
import com.ysn.cataloguemovie.model.movie.search.ResultSearchMovie;
import com.ysn.cataloguemovie.model.movie.search.SearchMovie;
import com.ysn.cataloguemovie.ui.activities.detail.DetailMovieActivity;
import com.ysn.cataloguemovie.ui.base.MvpPresenter;
import com.ysn.cataloguemovie.ui.fragments.search.adapter.AdapterSearchMovie;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by root on 06/09/17.
 */

public class SearchMoviePresenter implements MvpPresenter<SearchMovieView> {

    private String TAG = getClass().getSimpleName();
    private SearchMovieView searchMovieView;
    private AdapterSearchMovie adapterSearchMovie;
    private List<ResultSearchMovie> listResultSearchMovies;
    private Context context;

    @Override
    public void onAttach(SearchMovieView mvpView) {
        searchMovieView = mvpView;
    }

    @Override
    public void onDetach() {
        searchMovieView = null;
    }

    void onSearchMovie(final Context context, String keyword) {
        this.context = context;
        if (listResultSearchMovies == null) {
            listResultSearchMovies = new ArrayList<>();
        }
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        TheMovieApiService theMovieApiService = retrofit.create(TheMovieApiService.class);
        theMovieApiService.searchMovie(BuildConfig.API_KEY, BuildConfig.LANGUAGE, keyword)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<SearchMovie>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        /** nothing to do in here */
                    }

                    @Override
                    public void onNext(@NonNull SearchMovie searchMovie) {
                        listResultSearchMovies = searchMovie.getResults();
                        if (adapterSearchMovie == null) {
                            adapterSearchMovie = new AdapterSearchMovie(
                                    context,
                                    listResultSearchMovies,
                                    new AdapterSearchMovie.ListenerAdapterSearchMovie() {
                                        @Override
                                        public void onClick(ResultSearchMovie resultSearchMovie) {
                                            Intent intentDetailMovieActivity = new Intent(
                                                    context,
                                                    DetailMovieActivity.class
                                            );
                                            intentDetailMovieActivity.putExtra("idMovie", resultSearchMovie.getId());
                                            searchMovieView.onClickItem(intentDetailMovieActivity);
                                        }
                                    }
                            );
                        } else {
                            adapterSearchMovie.refreshData(listResultSearchMovies);
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        e.printStackTrace();
                        searchMovieView.searchMovieFailed();
                    }

                    @Override
                    public void onComplete() {
                        searchMovieView.searchMovie(adapterSearchMovie);
                    }
                });
    }
}
