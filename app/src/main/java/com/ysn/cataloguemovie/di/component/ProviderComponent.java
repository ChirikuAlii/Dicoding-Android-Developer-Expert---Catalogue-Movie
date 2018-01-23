/*
 * Created by Yudi Setiawan on 1/23/18 10:29 PM
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 1/23/18 10:15 PM
 */

package com.ysn.cataloguemovie.di.component;

import com.ysn.cataloguemovie.di.PerProvider;
import com.ysn.cataloguemovie.di.module.ProviderModule;
import com.ysn.cataloguemovie.provider.FavoriteMovieProvider;

import dagger.Component;

@PerProvider
@Component(dependencies = AppComponent.class, modules = ProviderModule.class)
public interface ProviderComponent {

    void inject(FavoriteMovieProvider favoriteMovieProvider);

}
