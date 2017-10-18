package com.ysn.cataloguemovie.data.db;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.ysn.cataloguemovie.model.movie.detail.DetailMovie;

import java.util.List;

import static com.ysn.cataloguemovie.data.db.DatabaseContract.*;

/**
 * Created by yudisetiawan on 10/19/17.
 */

public class FavoriteHelper {

    private final String TAG = getClass().getSimpleName();
    private static final String FAVORITE_TABLE_NAME = TABLE_FAVORITE;
    private Context context;
    private DatabaseHelper databaseHelper;
    private SQLiteDatabase sqLiteDatabase;

    public FavoriteHelper(Context context) {
        this.context = context;
    }

    public FavoriteHelper open() throws SQLException {
        databaseHelper = new DatabaseHelper(context);
        sqLiteDatabase = databaseHelper.getWritableDatabase();
        return this;
    }

    public SQLiteDatabase getWritableDatabase() {
        return databaseHelper.getWritableDatabase();
    }

    public Long insertDataFavorite(DetailMovie detailMovie) throws Exception {
        return databaseHelper.insertDataFavorite(context, detailMovie);
    }

    public int deleteDataFavorite(long idMovie) throws Exception {
        return databaseHelper.deleteDataFavorite(context, idMovie);
    }

    public int getSizeItemDataFavorite() {
        return databaseHelper.itemCountDataFavorite(context);
    }

    public boolean isItemDataAlready(long idMovie) {
        return databaseHelper.itemDataAlreadyAdded(context, idMovie);
    }

    public List<DetailMovie> getAll(Context context) {
        return databaseHelper.getAll(context);
    }

}
