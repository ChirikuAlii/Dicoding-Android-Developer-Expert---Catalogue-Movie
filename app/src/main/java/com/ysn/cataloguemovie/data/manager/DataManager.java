/*
 * Created by Yudi Setiawan on 1/23/18 10:29 PM
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 1/23/18 10:14 PM
 */

package com.ysn.cataloguemovie.data.manager;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.ysn.cataloguemovie.data.db.DatabaseHelper;
import com.ysn.cataloguemovie.di.ApplicationContext;
import com.ysn.cataloguemovie.model.movie.detail.DetailMovie;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class DataManager {

    private Context context;
    private DatabaseHelper databaseHelper;

    @Inject
    public DataManager(@ApplicationContext Context context,
                       DatabaseHelper databaseHelper) {
        this.context = context;
        this.databaseHelper = databaseHelper;
    }

    public SQLiteDatabase getWritableDatabase() {
        return databaseHelper.getWritableDatabase();
    }

    public Long insertDataFavorite(DetailMovie detailMovie) throws Exception {
        return databaseHelper.insertDataFavorite(detailMovie);
    }

    public int deleteDataFavorite(long idMovie) throws Exception {
        return databaseHelper.deleteDataFavorite(idMovie);
    }

    public int getSizeItemDataFavorite() {
        return databaseHelper.itemCountDataFavorite();
    }

    public boolean isItemDataAlready(Context context, long idMovie) {
        return databaseHelper.itemDataAlreadyAdded(idMovie);
    }

    public List<DetailMovie> getAll() {
        return databaseHelper.getAll();
    }

}
