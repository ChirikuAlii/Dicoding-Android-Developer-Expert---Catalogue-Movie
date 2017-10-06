package com.ysn.cataloguemovie.data.db;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import com.ysn.cataloguemovie.di.ApplicationContext;
import com.ysn.cataloguemovie.di.DatabaseInfo;
import com.ysn.cataloguemovie.model.movie.nowplaying.ResultNowPlaying;
import com.ysn.cataloguemovie.model.movie.upcoming.ResultUpcomingMovie;

import javax.inject.Inject;
import javax.inject.Singleton;

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
    public static final String FAVORITE_COLUMN_GENRE_IDS = "genre_ids";
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
                            + FAVORITE_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                            + FAVORITE_COLUMN_ADULT + " BOOLEAN, "
                            + FAVORITE_COLUMN_BACKDROP_PATH + " TEXT, "
                            + FAVORITE_COLUMN_GENRE_IDS + " TEXT, "
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

    public Long insertDataFavorite(ResultNowPlaying resultNowPlaying) throws Exception {
        try {
            SQLiteDatabase sqLiteDatabase = getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(
                    FAVORITE_COLUMN_ADULT, resultNowPlaying.getAdult()
            );
            contentValues.put(
                    FAVORITE_COLUMN_BACKDROP_PATH, resultNowPlaying.getBackdropPath()
            );
            contentValues.put(
                    FAVORITE_COLUMN_GENRE_IDS, resultNowPlaying.getGenreIds().toString()
            );
            contentValues.put(
                    FAVORITE_COLUMN_ID_MOVIE, resultNowPlaying.getId()
            );
            contentValues.put(
                    FAVORITE_COLUMN_ORIGINAL_LANGUAGE, resultNowPlaying.getOriginalLanguage()
            );
            contentValues.put(
                    FAVORITE_COLUMN_ORIGINAL_TITLE, resultNowPlaying.getOriginalTitle()
            );
            contentValues.put(
                    FAVORITE_COLUMN_OVERVIEW, resultNowPlaying.getOverview()
            );
            contentValues.put(
                    FAVORITE_COLUMN_POPULARITY, resultNowPlaying.getPopularity()
            );
            contentValues.put(
                    FAVORITE_COLUMN_POSTER_PATH, resultNowPlaying.getPosterPath()
            );
            contentValues.put(
                    FAVORITE_COLUMN_RELEASE_DATE, resultNowPlaying.getReleaseDate()
            );
            contentValues.put(
                    FAVORITE_COLUMN_TITLE, resultNowPlaying.getTitle()
            );
            contentValues.put(
                    FAVORITE_COLUMN_VIDEO, resultNowPlaying.getVideo()
            );
            contentValues.put(
                    FAVORITE_COLUMN_VOTE_AVERAGE, resultNowPlaying.getVoteAverage()
            );
            contentValues.put(
                    FAVORITE_COLUMN_VOTE_COUNT, resultNowPlaying.getVoteCount()
            );
            return sqLiteDatabase.insert(
                    FAVORITE_TABLE_NAME,
                    null,
                    contentValues
            );
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public long insertDataFavorite(ResultUpcomingMovie resultUpcomingMovie) throws Exception {
         try {
            SQLiteDatabase sqLiteDatabase = getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(
                    FAVORITE_COLUMN_ADULT, resultUpcomingMovie.getAdult()
            );
            contentValues.put(
                    FAVORITE_COLUMN_BACKDROP_PATH, resultUpcomingMovie.getBackdropPath()
            );
            contentValues.put(
                    FAVORITE_COLUMN_GENRE_IDS, resultUpcomingMovie.getGenreIds().toString()
            );
            contentValues.put(
                    FAVORITE_COLUMN_ID_MOVIE, resultUpcomingMovie.getId()
            );
            contentValues.put(
                    FAVORITE_COLUMN_ORIGINAL_LANGUAGE, resultUpcomingMovie.getOriginalLanguage()
            );
            contentValues.put(
                    FAVORITE_COLUMN_ORIGINAL_TITLE, resultUpcomingMovie.getOriginalTitle()
            );
            contentValues.put(
                    FAVORITE_COLUMN_OVERVIEW, resultUpcomingMovie.getOverview()
            );
            contentValues.put(
                    FAVORITE_COLUMN_POPULARITY, resultUpcomingMovie.getPopularity()
            );
            contentValues.put(
                    FAVORITE_COLUMN_POSTER_PATH, resultUpcomingMovie.getPosterPath()
            );
            contentValues.put(
                    FAVORITE_COLUMN_RELEASE_DATE, resultUpcomingMovie.getReleaseDate()
            );
            contentValues.put(
                    FAVORITE_COLUMN_TITLE, resultUpcomingMovie.getTitle()
            );
            contentValues.put(
                    FAVORITE_COLUMN_VIDEO, resultUpcomingMovie.getVideo()
            );
            contentValues.put(
                    FAVORITE_COLUMN_VOTE_AVERAGE, resultUpcomingMovie.getVoteAverage()
            );
            contentValues.put(
                    FAVORITE_COLUMN_VOTE_COUNT, resultUpcomingMovie.getVoteCount()
            );
            return sqLiteDatabase.insert(
                    FAVORITE_TABLE_NAME,
                    null,
                    contentValues
            );
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public int deleteDataFavorite(long idMovie) throws Exception {
        try {
            SQLiteDatabase sqLiteDatabase = getWritableDatabase();
            return sqLiteDatabase.delete(
                    FAVORITE_TABLE_NAME,
                    FAVORITE_COLUMN_ID_MOVIE + " = ?",
                    new String[]{String.valueOf(idMovie)}
            );
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public int itemCountDataFavorite() throws Resources.NotFoundException, NullPointerException {
        int itemCount;
        try {
            SQLiteDatabase sqLiteDatabase = getWritableDatabase();
            Cursor cursor = sqLiteDatabase.rawQuery(
                    "SELECT * FROM " + FAVORITE_TABLE_NAME,
                    null,
                    null
            );
            itemCount = cursor.getCount();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return itemCount;
    }
}
