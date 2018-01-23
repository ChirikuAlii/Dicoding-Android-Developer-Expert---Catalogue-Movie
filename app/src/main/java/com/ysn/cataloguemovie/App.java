/*
 * Created by Yudi Setiawan on 1/23/18 10:29 PM
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 1/23/18 10:21 PM
 */

package com.ysn.cataloguemovie;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.ysn.cataloguemovie.di.component.AppComponent;
import com.ysn.cataloguemovie.di.component.DaggerAppComponent;
import com.ysn.cataloguemovie.di.module.AppModule;

public class App extends Application {

    private final String TAG = getClass().getSimpleName();
    protected AppComponent component;

    public static App get(Context context) {
        return (App) context.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        component = DaggerAppComponent
                .builder()
                .appModule(new AppModule(this))
                .build();
        component.inject(this);
    }

    public AppComponent getAppComponent() {
        return component;
    }
}
