/*
 * Created by Yudi Setiawan on 1/23/18 10:29 PM
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 1/23/18 10:15 PM
 */

package com.ysn.cataloguemovie.di.module;

import android.content.ContentProvider;

import dagger.Module;
import dagger.Provides;

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
