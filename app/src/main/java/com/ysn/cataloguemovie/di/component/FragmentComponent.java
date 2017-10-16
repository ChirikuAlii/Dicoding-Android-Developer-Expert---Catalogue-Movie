package com.ysn.cataloguemovie.di.component;

import com.ysn.cataloguemovie.di.PerFragment;
import com.ysn.cataloguemovie.di.module.FragmentModule;
import com.ysn.cataloguemovie.ui.fragments.favorite.FavoriteMovieFragment;

import dagger.Component;

/**
 * Created by yudisetiawan on 10/16/17.
 */

@PerFragment
@Component(dependencies = AppComponent.class, modules = FragmentModule.class)
public interface FragmentComponent {

    void inject(FavoriteMovieFragment favoriteMovieFragment);

}
