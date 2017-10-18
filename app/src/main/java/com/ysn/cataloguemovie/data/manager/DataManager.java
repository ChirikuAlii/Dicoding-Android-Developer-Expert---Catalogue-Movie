package com.ysn.cataloguemovie.data.manager;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.ysn.cataloguemovie.data.db.DatabaseHelper;
import com.ysn.cataloguemovie.di.ApplicationContext;
import com.ysn.cataloguemovie.model.movie.detail.DetailMovie;

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

    public Long insertDataFavorite(Context context, DetailMovie detailMovie) throws Exception {
        return databaseHelper.insertDataFavorite(context, detailMovie);
    }

    public int deleteDataFavorite(Context context, long idMovie) throws Exception {
        return databaseHelper.deleteDataFavorite(context, idMovie);
    }

    public int getSizeItemDataFavorite(Context context) {
        return databaseHelper.itemCountDataFavorite(context);
    }

    public boolean isItemDataAlready(Context context, long idMovie) {
        return databaseHelper.itemDataAlreadyAdded(context, idMovie);
    }

    public List<DetailMovie> getAll(Context context) {
        return databaseHelper.getAll(context);
    }

}
