package com.ysn.cataloguemovie.data.db;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.util.Log;

import com.ysn.cataloguemovie.di.ApplicationContext;
import com.ysn.cataloguemovie.di.DatabaseInfo;
import com.ysn.cataloguemovie.model.movie.detail.DetailMovie;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import static com.ysn.cataloguemovie.data.db.DatabaseContract.*;

/**
 * Created by yudisetiawan on 10/6/17.
 */

@Singleton
public class DatabaseHelper extends SQLiteOpenHelper {

    private final String TAG = getClass().getSimpleName();

    // Favorite
    public static final String FAVORITE_TABLE_NAME = "favorite";
    public static final String FAVORITE_COLUMN_ID = "_id";
    public static final String FAVORITE_COLUMN_ADULT = "adult";
    public static final String FAVORITE_COLUMN_BACKDROP_PATH = "backdrop_path";
    public static final String FAVORITE_COLUMN_GENRES = "genres";
    public static final String FAVORITE_COLUMN_ID_MOVIE = "id_movie";
    public static final String FAVORITE_COLUMN_ORIGINAL_LANGUAGE = "original_language";
    public static final String FAVORITE_COLUMN_ORIGINAL_TITLE = "original_title";
    public static final String FAVORITE_COLUMN_OVERVIEW = "overview";
    public static final String FAVORITE_COLUMN_POPULARITY = "popularity";
    public static final String FAVORITE_COLUMN_POSTER_PATH = "poster_path";
    public static final String FAVORITE_COLUMN_RELEASE_DATE = "release_date";
    public static final String FAVORITE_COLUMN_TITLE = "title";
    public static final String FAVORITE_COLUMN_VIDEO = "video";
    public static final String FAVORITE_COLUMN_VOTE_AVERAGE = "vote_average";
    public static final String FAVORITE_COLUMN_VOTE_COUNT = "vote_count";
    private List<DetailMovie> all;

    public DatabaseHelper(Context context) {
        super(context, "movie.db", null, 1);
    }

    @Inject
    public DatabaseHelper(@ApplicationContext Context context,
                          @DatabaseInfo String databaseName,
                          @DatabaseInfo int version) {
        super(context, databaseName, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        tableFavoriteCreateStatements(sqLiteDatabase);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + FAVORITE_TABLE_NAME);
    }

    private void tableFavoriteCreateStatements(SQLiteDatabase sqLiteDatabase) {
        try {
            sqLiteDatabase.execSQL(
                    "CREATE TABLE IF NOT EXISTS " + FAVORITE_TABLE_NAME + " "
                            + "("
                            + FAVORITE_COLUMN_ID + " INTEGER PRIMARY KEY, "
                            + FAVORITE_COLUMN_ADULT + " BOOLEAN, "
                            + FAVORITE_COLUMN_BACKDROP_PATH + " TEXT, "
                            + FAVORITE_COLUMN_GENRES + " TEXT, "
                            + FAVORITE_COLUMN_ID_MOVIE + " TEXT, "
                            + FAVORITE_COLUMN_ORIGINAL_LANGUAGE + " TEXT, "
                            + FAVORITE_COLUMN_ORIGINAL_TITLE + " TEXT, "
                            + FAVORITE_COLUMN_OVERVIEW + " TEXT, "
                            + FAVORITE_COLUMN_POPULARITY + " TEXT, "
                            + FAVORITE_COLUMN_POSTER_PATH + " TEXT, "
                            + FAVORITE_COLUMN_RELEASE_DATE + " TEXT, "
                            + FAVORITE_COLUMN_TITLE + " TEXT, "
                            + FAVORITE_COLUMN_VIDEO + " BOOLEAN, "
                            + FAVORITE_COLUMN_VOTE_AVERAGE + " DOUBLE, "
                            + FAVORITE_COLUMN_VOTE_COUNT + " LONG"
                            + ")"
            );
        } catch (SQLiteException sqle) {
            sqle.printStackTrace();
        }
    }

    public Long insertDataFavorite(Context context, DetailMovie detailMovie) throws Exception {
        try {
            /* Without content provider */
            /*SQLiteDatabase sqLiteDatabase = getWritableDatabase();*/

            /* With content provider */
            ContentValues contentValues = new ContentValues();
            contentValues.put(
                    FAVORITE_COLUMN_ID, Integer.parseInt(detailMovie.getId().toString())
            );
            contentValues.put(
                    FAVORITE_COLUMN_ADULT, detailMovie.getAdult()
            );
            contentValues.put(
                    FAVORITE_COLUMN_BACKDROP_PATH, detailMovie.getBackdropPath()
            );
            contentValues.put(
                    FAVORITE_COLUMN_GENRES, detailMovie.getGenres().toString()
            );
            contentValues.put(
                    FAVORITE_COLUMN_ID_MOVIE, detailMovie.getId()
            );
            contentValues.put(
                    FAVORITE_COLUMN_ORIGINAL_LANGUAGE, detailMovie.getOriginalLanguage()
            );
            contentValues.put(
                    FAVORITE_COLUMN_ORIGINAL_TITLE, detailMovie.getOriginalTitle()
            );
            contentValues.put(
                    FAVORITE_COLUMN_OVERVIEW, detailMovie.getOverview()
            );
            contentValues.put(
                    FAVORITE_COLUMN_POPULARITY, detailMovie.getPopularity()
            );
            contentValues.put(
                    FAVORITE_COLUMN_POSTER_PATH, detailMovie.getPosterPath()
            );
            contentValues.put(
                    FAVORITE_COLUMN_RELEASE_DATE, detailMovie.getReleaseDate()
            );
            contentValues.put(
                    FAVORITE_COLUMN_TITLE, detailMovie.getTitle()
            );
            contentValues.put(
                    FAVORITE_COLUMN_VIDEO, String.valueOf(detailMovie.getVideo())
            );
            contentValues.put(
                    FAVORITE_COLUMN_VOTE_AVERAGE, String.valueOf(detailMovie.getVoteAverage())
            );
            contentValues.put(
                    FAVORITE_COLUMN_VOTE_COUNT, String.valueOf(detailMovie.getVoteCount())
            );
            context.getContentResolver()
                    .insert(CONTENT_URI, contentValues);

            /* Without content provider */
            /*return sqLiteDatabase.insert(
                    FAVORITE_TABLE_NAME,
                    null,
                    contentValues
            )*/

            return 1L;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public int deleteDataFavorite(Context context, long idMovie) throws Exception {
        try {
            /* Without content provider */
            /*SQLiteDatabase sqLiteDatabase = getWritableDatabase();
            return sqLiteDatabase.delete(
                    FAVORITE_TABLE_NAME,
                    FAVORITE_COLUMN_ID_MOVIE + " = ?",
                    new String[]{String.valueOf(idMovie)}
            );*/

            /* With content provider */
            context.getContentResolver()
                    .delete(
                            Uri.parse(CONTENT_URI + "/" + idMovie),
                            null,
                            null
                    );
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public int itemCountDataFavorite(Context context) throws Resources.NotFoundException, NullPointerException {
        int itemCount;
        try {
            /* Without content provider */
            /*SQLiteDatabase sqLiteDatabase = getWritableDatabase();
            Cursor cursor = sqLiteDatabase.rawQuery(
                    "SELECT * FROM " + FAVORITE_TABLE_NAME,
                    null,
                    null
            );*/

            /* With content provider */
            Cursor cursor = context.getContentResolver()
                    .query(CONTENT_URI, null, null, null, null);
            itemCount = cursor != null ? cursor.getCount() : 0;
            if (cursor != null) {
                cursor.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return itemCount;
    }

    public boolean itemDataAlreadyAdded(Context context, long idMovie) {
        boolean isDataAlreadyAdded;

        /* Without content provider */
        /*SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(
                "SELECT * FROM " + FAVORITE_TABLE_NAME
                        + " WHERE "
                        + FAVORITE_COLUMN_ID_MOVIE + " = ?",
                new String[]{String.valueOf(idMovie)},
                null
        );*/

        /* With content provider */
        Cursor cursor = context.getContentResolver()
                .query(
                        Uri.parse(CONTENT_URI + "/" + idMovie),
                        null,
                        null,
                        null,
                        null
                );
        isDataAlreadyAdded = cursor != null && cursor.getCount() > 0;
        if (cursor != null) {
            cursor.close();
        }
        return isDataAlreadyAdded;
    }

    public List<DetailMovie> getAll(Context context) throws Resources.NotFoundException {
        List<DetailMovie> listDataDetailMovie = new ArrayList<>();
        try {
            /* Without content provider */
            /*SQLiteDatabase sqLiteDatabase = getWritableDatabase();*/

            /* Without content provider */
            /*Cursor cursor = sqLiteDatabase.rawQuery(
                    "SELECT * FROM " + FAVORITE_TABLE_NAME,
                    null
            );*/

            /* With content provider */
            Uri uri = Uri.parse(CONTENT_URI + "/");
            Cursor cursor = context.getContentResolver()
                    .query(uri, null, null, null, null);

            if (cursor != null && cursor.getCount() > 0) {
                while (cursor.moveToNext()) {
                    /* Without content provider */
                    /*DetailMovie detailMovie = new DetailMovie();
                    detailMovie.setId(
                            Long.valueOf(cursor.getString(cursor.getColumnIndex(FAVORITE_COLUMN_ID_MOVIE)))
                    );
                    detailMovie.setPosterPath(
                            cursor.getString(cursor.getColumnIndex(FAVORITE_COLUMN_POSTER_PATH))
                    );
                    detailMovie.setTitle(
                            cursor.getString(cursor.getColumnIndex(FAVORITE_COLUMN_TITLE))
                    );
                    detailMovie.setOverview(
                            cursor.getString(cursor.getColumnIndex(FAVORITE_COLUMN_OVERVIEW))
                    );
                    detailMovie.setReleaseDate(
                            cursor.getString(cursor.getColumnIndex(FAVORITE_COLUMN_RELEASE_DATE))
                    );*/

                    /* With content provider */
                    DetailMovie detailMovie = new DetailMovie(cursor);
                    listDataDetailMovie.add(detailMovie);
                }
                cursor.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listDataDetailMovie;
    }
}
