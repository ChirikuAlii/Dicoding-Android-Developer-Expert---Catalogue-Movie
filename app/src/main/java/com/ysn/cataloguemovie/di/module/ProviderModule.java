package com.ysn.cataloguemovie.di.module;

import android.content.ContentProvider;

import dagger.Module;
import dagger.Provides;

/**
 * Created by yudisetiawan on 10/17/17.
 */

@Module
public class ProviderModule {

    private ContentProvider contentProvider;

    public ProviderModule(ContentProvider contentProvider) {
        this.contentProvider = contentProvider;
    }

    @Provides
    ContentProvider provideContentProvider() {
        return contentProvider;
    }
}
