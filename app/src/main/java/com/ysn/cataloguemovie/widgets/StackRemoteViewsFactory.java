/*
 * Created by Yudi Setiawan on 1/23/18 10:29 PM
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 1/23/18 10:21 PM
 */

package com.ysn.cataloguemovie.widgets;

import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.Target;
import com.ysn.cataloguemovie.BuildConfig;
import com.ysn.cataloguemovie.R;
import com.ysn.cataloguemovie.data.db.DatabaseHelper;
import com.ysn.cataloguemovie.model.movie.detail.DetailMovie;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class StackRemoteViewsFactory
        implements RemoteViewsService.RemoteViewsFactory {

    private final String TAG = getClass().getSimpleName();
    private Context context;
    private int mAppWidgetId;
    private List<DetailMovie> listDetailMovie;
    private DatabaseHelper databaseHelper;

    public StackRemoteViewsFactory(Context context, Intent intent) {
        this.context = context;
        mAppWidgetId = intent.getIntExtra(
                AppWidgetManager.EXTRA_APPWIDGET_ID,
                AppWidgetManager.INVALID_APPWIDGET_ID
        );
    }

    @Override
    public void onCreate() {
        /** nothing to do in here */
        databaseHelper = new DatabaseHelper(context);
    }

    @Override
    public void onDataSetChanged() {
        listDetailMovie = databaseHelper.getAll();
    }

    @Override
    public void onDestroy() {

    }

    @Override
    public int getCount() {
        return listDetailMovie.size();
    }

    @Override
    public RemoteViews getViewAt(int position) {
        Log.d(TAG, "position: " + position);
        Bitmap bitmap = null;
        String posterPath = listDetailMovie.get(position).getPosterPath();
        try {
            bitmap = Glide.with(context)
                    .load(BuildConfig.BASE_URL_IMAGE + posterPath)
                    .asBitmap()
                    .error(R.drawable.ic_broken_image_black_24dp)
                    .placeholder(R.drawable.ic_image_black_24dp)
                    .into(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
                    .get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        RemoteViews remoteViewsItem = new RemoteViews(
                context.getPackageName(),
                R.layout.favorite_movie_widget_item
        );
        remoteViewsItem.setImageViewBitmap(
                R.id.image_view_poster_favorite_movie_widget_item,
                bitmap
        );
        remoteViewsItem.setTextViewText(
                R.id.text_view_tanggal_favorite_movie_widget_item,
                listDetailMovie.get(position).getReleaseDate()
                );

        Bundle bundle = new Bundle();
        /*bundle.putInt(FavoriteMovieWidget.EXTRA_ITEM, position);*/
        bundle.putLong(FavoriteMovieWidget.EXTRA_ITEM, listDetailMovie.get(position).getId());
        Intent fillInIntent = new Intent();
        fillInIntent.putExtras(bundle);
        remoteViewsItem.setOnClickFillInIntent(
                R.id.image_view_poster_favorite_movie_widget_item,
                fillInIntent
        );
        return remoteViewsItem;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }
}
