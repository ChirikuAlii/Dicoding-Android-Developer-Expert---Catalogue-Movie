package com.ysn.cataloguemovie.ui.fragments.upcoming;

import android.content.Context;

import com.google.gson.Gson;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.ysn.cataloguemovie.BuildConfig;
import com.ysn.cataloguemovie.api.TheMovieApiService;
import com.ysn.cataloguemovie.model.movie.upcoming.ResultUpcomingMovie;
import com.ysn.cataloguemovie.model.movie.upcoming.UpcomingMovie;
import com.ysn.cataloguemovie.ui.base.MvpPresenter;
import com.ysn.cataloguemovie.ui.fragments.upcoming.adapter.AdapterUpcomingMovie;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by yudisetiawan on 9/17/17.
 */

public class UpcomingMoviePresenter implements MvpPresenter<UpcomingMovieView> {

    private final String TAG = getClass().getSimpleName();
    private UpcomingMovieView upcomingMovieView;

    @Override
    public void onAttach(UpcomingMovieView mvpView) {
        upcomingMovieView = mvpView;
    }

    @Override
    public void onDetach() {
        upcomingMovieView = null;
    }

    void onLoadData(final Context context) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        TheMovieApiService theMovieApiService = retrofit.create(TheMovieApiService.class);
        theMovieApiService.getUpcomingMovie(BuildConfig.API_KEY, BuildConfig.LANGUAGE)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<UpcomingMovie>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        /** nothing to do in here */
                    }

                    @Override
                    public void onNext(@NonNull UpcomingMovie upcomingMovie) {
                        AdapterUpcomingMovie adapterUpcomingMovie = new AdapterUpcomingMovie(
                                context,
                                upcomingMovie.getResults(),
                                new AdapterUpcomingMovie.ListenerAdapterUpcomingMovie() {
                                    @Override
                                    public void onItemClickDetail(ResultUpcomingMovie resultUpcomingMovie) {
                                        upcomingMovieView.itemClickDetail(resultUpcomingMovie);
                                    }

                                    @Override
                                    public void onItemClickShare(ResultUpcomingMovie resultUpcomingMovie) {
                                        upcomingMovieView.itemClickShare(resultUpcomingMovie);
                                    }
                                }
                        );
                        upcomingMovieView.loadData(adapterUpcomingMovie);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        e.printStackTrace();
                        upcomingMovieView.loadDataFailed(e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        /** nothing to do in here */
                    }
                });
    }
}
