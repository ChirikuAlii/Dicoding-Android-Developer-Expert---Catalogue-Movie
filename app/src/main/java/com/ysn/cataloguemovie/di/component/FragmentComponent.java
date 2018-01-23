/*
 * Created by Yudi Setiawan on 1/23/18 10:29 PM
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 1/23/18 10:14 PM
 */

package com.ysn.cataloguemovie.di.component;

import com.ysn.cataloguemovie.di.PerFragment;
import com.ysn.cataloguemovie.di.module.FragmentModule;
import com.ysn.cataloguemovie.ui.fragments.favorite.FavoriteMovieFragment;

import dagger.Component;

@PerFragment
@Component(dependencies = AppComponent.class, modules = FragmentModule.class)
public interface FragmentComponent {

    void inject(FavoriteMovieFragment favoriteMovieFragment);

}
