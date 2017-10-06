package com.ysn.cataloguemovie.di.component;

import com.ysn.cataloguemovie.di.PerActivity;
import com.ysn.cataloguemovie.di.module.ActivityModule;
import com.ysn.cataloguemovie.ui.activities.main.MainActivity;

import dagger.Component;

/**
 * Created by yudisetiawan on 10/6/17.
 */

@PerActivity
@Component(dependencies = AppComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(MainActivity mainActivity);

}
