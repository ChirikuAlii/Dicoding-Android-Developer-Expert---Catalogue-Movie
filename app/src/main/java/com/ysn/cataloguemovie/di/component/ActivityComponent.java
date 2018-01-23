/*
 * Created by Yudi Setiawan on 1/23/18 10:29 PM
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 1/23/18 10:14 PM
 */

package com.ysn.cataloguemovie.di.component;

import com.ysn.cataloguemovie.di.PerActivity;
import com.ysn.cataloguemovie.di.module.ActivityModule;
import com.ysn.cataloguemovie.ui.activities.detail.DetailMovieActivity;
import com.ysn.cataloguemovie.ui.activities.main.MainActivity;

import dagger.Component;

@PerActivity
@Component(dependencies = AppComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(DetailMovieActivity detailMovieActivity);

}
