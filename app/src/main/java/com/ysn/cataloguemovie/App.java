package com.ysn.cataloguemovie;

import android.app.Application;
import android.content.Context;

import com.ysn.cataloguemovie.di.component.AppComponent;

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
    }
}
