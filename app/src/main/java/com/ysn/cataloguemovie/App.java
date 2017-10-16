package com.ysn.cataloguemovie;

import android.app.Application;
import android.content.Context;

import com.ysn.cataloguemovie.di.component.AppComponent;
import com.ysn.cataloguemovie.di.component.DaggerAppComponent;
import com.ysn.cataloguemovie.di.module.AppModule;

/**
 * Created by yudisetiawan on 10/6/17.
 */

public class App extends Application {

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
