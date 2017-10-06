package com.ysn.cataloguemovie.di.module;

import android.app.Application;
import android.content.Context;

import com.ysn.cataloguemovie.di.ApplicationContext;
import com.ysn.cataloguemovie.di.DatabaseInfo;

import dagger.Module;
import dagger.Provides;

/**
 * Created by yudisetiawan on 10/6/17.
 */

@Module
public class AppModule {

    private final Application application;

    public AppModule(Application application) {
        this.application = application;
    }

    @Provides
    @ApplicationContext
    Context provideContext() {
        return application;
    }

    @Provides
    Application provideApplication() {
        return application;
    }

    @Provides
    @DatabaseInfo
    String provideDatabaseName() {
        return "movie.db";
    }

    @Provides
    @DatabaseInfo
    Integer provideDatabaseVersion() {
        return 1;
    }

}
