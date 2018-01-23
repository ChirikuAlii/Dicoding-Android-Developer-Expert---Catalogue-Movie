/*
 * Created by Yudi Setiawan on 1/23/18 10:29 PM
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 1/23/18 10:21 PM
 */

package com.ysn.cataloguemovie.service.reminder.upcoming;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import com.google.android.gms.gcm.GcmNetworkManager;
import com.google.android.gms.gcm.GcmTaskService;
import com.google.android.gms.gcm.TaskParams;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.ysn.cataloguemovie.BuildConfig;
import com.ysn.cataloguemovie.R;
import com.ysn.cataloguemovie.api.TheMovieApiService;
import com.ysn.cataloguemovie.model.movie.upcoming.ResultUpcomingMovie;
import com.ysn.cataloguemovie.model.movie.upcoming.UpcomingMovie;
import com.ysn.cataloguemovie.ui.activities.detail.DetailMovieActivity;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by yudisetiawan on 9/9/17.
 */

public class UpcomingMoviesJobService extends GcmTaskService {

    private final String TAG = getClass().getSimpleName();
    private int notifId;

    @Override
    public int onRunTask(TaskParams taskParams) {
        int result = 0;
        if (taskParams.getTag().equalsIgnoreCase("UpcomingMovies")) {
            getUpcomingMovies();
            result = GcmNetworkManager.RESULT_SUCCESS;
        }
        return result;
    }

    @Override
    public void onInitializeTasks() {
        super.onInitializeTasks();
        SchedulerTask schedulersTask = new SchedulerTask(this);
        schedulersTask.createPeriodicTask();
    }

    private void getUpcomingMovies() {
        notifId = 200;
        Log.d(TAG, "getUpcomingMovies");
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        TheMovieApiService theMovieApiService = retrofit.create(TheMovieApiService.class);
        theMovieApiService.getUpcomingMovie(BuildConfig.API_KEY, BuildConfig.LANGUAGE)
                .map(new Function<UpcomingMovie, List<ResultUpcomingMovie>>() {
                    @Override
                    public List<ResultUpcomingMovie> apply(@NonNull UpcomingMovie upcomingMovie) throws Exception {
                        return upcomingMovie.getResults();
                    }
                })
                .flatMap(new Function<List<ResultUpcomingMovie>, Observable<ResultUpcomingMovie>>() {
                    @Override
                    public Observable<ResultUpcomingMovie> apply(@NonNull List<ResultUpcomingMovie> resultUpcomingMovies) throws Exception {
                        return Observable.fromIterable(resultUpcomingMovies);
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResultUpcomingMovie>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        /** nothing to do in here */
                    }

                    @Override
                    public void onNext(@NonNull ResultUpcomingMovie resultUpcomingMovie) {
                        Log.d(TAG, "onNext");
                        long id = resultUpcomingMovie.getId();
                        String originalTitle = resultUpcomingMovie.getOriginalTitle();
                        showNotificationUpcomigMovie(notifId, id, originalTitle);
                        notifId += 1;
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        e.printStackTrace();
                        Log.d(TAG, "getUpcomingMoviesJobService onError: " + e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "getUpcomingMoviesJobService onComplete");
                    }
                });

    }

    private void showNotificationUpcomigMovie(int notifId, long id, String originalTitle) {
        Context context = getApplicationContext();
        NotificationManager notificationManagerCompat = (NotificationManager) context
                .getSystemService(Context.NOTIFICATION_SERVICE);
        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        Intent intentDetailMovieActivity = new Intent(context, DetailMovieActivity.class);
        intentDetailMovieActivity.putExtra("idMovie", id);
        PendingIntent pendingIntentDetailMovieActivity = PendingIntent.getActivity(
                context,
                100,
                intentDetailMovieActivity,
                PendingIntent.FLAG_UPDATE_CURRENT
        );

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                .setContentTitle(originalTitle)
                .setSmallIcon(R.drawable.ic_access_alarm_black_24dp)
                .setContentText("Hari ini " + originalTitle + " release")
                .setContentIntent(pendingIntentDetailMovieActivity)
                .setColor(ContextCompat.getColor(context, android.R.color.white))
                .setVibrate(new long[]{1000, 1000, 1000, 1000, 1000})
                .setSound(alarmSound);
        notificationManagerCompat.notify(notifId, builder.build());
    }
}
