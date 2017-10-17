package com.ysn.cataloguemovie.di.component;

import com.ysn.cataloguemovie.App;
import com.ysn.cataloguemovie.di.PerProvider;
import com.ysn.cataloguemovie.di.module.ProviderModule;
import com.ysn.cataloguemovie.provider.FavoriteMovieProvider;

import dagger.Component;

/**
 * Created by yudisetiawan on 10/17/17.
 */

@PerProvider
@Component(dependencies = AppComponent.class, modules = ProviderModule.class)
public interface ProviderComponent {

    void inject(FavoriteMovieProvider favoriteMovieProvider);

}
