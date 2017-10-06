package com.ysn.cataloguemovie.data.manager;

import android.content.Context;

import com.ysn.cataloguemovie.data.db.DatabaseHelper;
import com.ysn.cataloguemovie.di.ApplicationContext;
import com.ysn.cataloguemovie.model.movie.nowplaying.ResultNowPlaying;
import com.ysn.cataloguemovie.model.movie.upcoming.ResultUpcomingMovie;

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

    public Long insertDataFavorite(ResultNowPlaying resultNowPlaying) throws Exception {
        return databaseHelper.insertDataFavorite(resultNowPlaying);
    }

    public Long insertDataFavorite(ResultUpcomingMovie resultUpcomingMovie) throws Exception {
        return databaseHelper.insertDataFavorite(resultUpcomingMovie);
    }

    public int deleteDataFavorite(long idMovie) throws Exception {
        return databaseHelper.deleteDataFavorite(idMovie);
    }

    public int getSizeItemDataFavorite() {
        return databaseHelper.itemCountDataFavorite();
    }

}
