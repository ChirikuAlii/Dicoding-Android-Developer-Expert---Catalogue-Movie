package com.ysn.cataloguemovie.provider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.ysn.cataloguemovie.data.db.DatabaseHelper;
import com.ysn.cataloguemovie.data.db.FavoriteHelper;

/**
 * Created by yudisetiawan on 10/17/17.
 */

public class FavoriteMovieProvider extends ContentProvider {

    private final String TAG = getClass().getSimpleName();
    private static final String AUTHORITY = "com.ysn.cataloguemovie";
    private static final String BASE_PATH = "favorite";
    public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/" + BASE_PATH);
    private static final int FAVORITE = 1;
    private static final int FAVORITE_ID_MOVIE = 2;
    private static final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    private SQLiteDatabase sqLiteDatabase;
    private FavoriteHelper favoriteHelper;

    static {
        uriMatcher.addURI(AUTHORITY, BASE_PATH, FAVORITE);
        uriMatcher.addURI(AUTHORITY, BASE_PATH + "/#", FAVORITE_ID_MOVIE);
    }

    @Override
    public boolean onCreate() {
        favoriteHelper = new FavoriteHelper(getContext());
        favoriteHelper.open();
        sqLiteDatabase = favoriteHelper.getWritableDatabase();
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] strings, @Nullable String s, @Nullable String[] strings1, @Nullable String s1) {
        Cursor cursor = null;
        if (uriMatcher.match(uri) == FAVORITE) {
            cursor = favoriteHelper.getAll();
        }
        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        long id = favoriteHelper.insertDataFavorite(contentValues);
        if (id > 0) {
            Uri mUri = ContentUris.withAppendedId(CONTENT_URI, id);
            getContext().getContentResolver()
                    .notifyChange(uri, null);
            return mUri;
        }
        throw new SQLException("Insertion Failed for URI: " + uri);
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String s, @Nullable String[] strings) {
        int deletedCount = 0;
        switch (uriMatcher.match(uri)) {
            case FAVORITE:
                deletedCount = sqLiteDatabase.delete(DatabaseHelper.FAVORITE_TABLE_NAME, s, strings);
                break;
            default:
                throw new IllegalArgumentException("This is an Unknown URI " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return deletedCount;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String s, @Nullable String[] strings) {
        /*int updatedCount = 0;
        switch (uriMatcher.match(uri)) {
            case FAVORITE:
                updatedCount = sqLiteDatabase.update(
                        DatabaseHelper.FAVORITE_TABLE_NAME,
                        contentValues,
                        s,
                        strings
                );
                break;
            default:
                throw new IllegalArgumentException("This is an Unknown URI " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return updatedCount;*/

        return 0;
    }
}
