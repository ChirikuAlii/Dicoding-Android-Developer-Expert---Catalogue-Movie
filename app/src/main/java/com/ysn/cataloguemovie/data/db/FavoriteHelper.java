package com.ysn.cataloguemovie.data.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
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

    public Long insertDataFavorite(ContentValues contentValues) {
        return databaseHelper.insertDataFavoriteProvider(contentValues);
    }

    public int deleteDataFavorite(long idMovie) {
        return databaseHelper.deleteDataFavoriteProvider(idMovie);
    }

    public int getSizeItemDataFavorite() {
        return databaseHelper.itemCountDataFavoriteProvider();
    }

    public boolean isItemDataAlready(long idMovie) {
        return databaseHelper.itemDataAlreadyAddedProvider(idMovie);
    }

    public Cursor getAll() {
        return databaseHelper.getAllProvider();
    }

}
