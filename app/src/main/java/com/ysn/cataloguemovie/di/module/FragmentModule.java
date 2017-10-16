package com.ysn.cataloguemovie.di.module;

import android.content.Context;
import android.support.v4.app.Fragment;

import dagger.Module;
import dagger.Provides;

/**
 * Created by yudisetiawan on 10/16/17.
 */

@Module
public class FragmentModule {

    private Fragment fragment;

    public FragmentModule(Fragment fragment) {
        this.fragment = fragment;
    }

    @Provides
    Fragment provideFragment() {
        return fragment;
    }
}
