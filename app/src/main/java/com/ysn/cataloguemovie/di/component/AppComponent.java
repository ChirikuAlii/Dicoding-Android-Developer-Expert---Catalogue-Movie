package com.ysn.cataloguemovie.di.component;

import android.app.Application;
import android.content.Context;

import com.ysn.cataloguemovie.App;
import com.ysn.cataloguemovie.data.db.DatabaseHelper;
import com.ysn.cataloguemovie.data.manager.DataManager;
import com.ysn.cataloguemovie.di.ApplicationContext;
import com.ysn.cataloguemovie.di.module.AppModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by yudisetiawan on 10/6/17.
 */

@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {

    void inject(App app);

    @ApplicationContext
    Context getContext();

    Application getApplication();

    DatabaseHelper getDatabaseHelper();

    DataManager getDataManager();

}
