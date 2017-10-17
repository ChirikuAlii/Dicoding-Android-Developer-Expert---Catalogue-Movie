package com.ysn.cataloguemovie.data.manager;

import android.content.Context;
import android.content.res.Resources;
import android.database.sqlite.SQLiteDatabase;

import com.ysn.cataloguemovie.data.db.DatabaseHelper;
import com.ysn.cataloguemovie.di.ApplicationContext;
import com.ysn.cataloguemovie.model.movie.detail.DetailMovie;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by yudisetiawan on 10/6/17.
 */

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

    public boolean isItemDataAlready(long idMovie) {
        return databaseHelper.itemDataAlreadyAdded(idMovie);
    }

    public List<DetailMovie> getAll() {
        return databaseHelper.getAll();
    }

}
