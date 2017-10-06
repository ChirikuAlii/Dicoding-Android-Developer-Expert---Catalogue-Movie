package com.ysn.cataloguemovie.di.module;

import android.app.Activity;

import dagger.Module;
import dagger.Provides;

/**
 * Created by yudisetiawan on 10/6/17.
 */

@Module
public class ActivityModule {

    private Activity activity;

    public ActivityModule(Activity activity) {
        this.activity = activity;
    }

    @Provides
    Activity provideActivity() {
        return activity;
    }
}
