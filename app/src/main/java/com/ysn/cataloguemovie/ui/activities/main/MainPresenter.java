package com.ysn.cataloguemovie.ui.activities.main;

import com.ysn.cataloguemovie.ui.base.MvpPresenter;

/**
 * Created by root on 30/08/17.
 */

public class MainPresenter implements MvpPresenter<MainView> {

    private final String TAG = getClass().getSimpleName();
    private MainView mainView;

    @Override
    public void onAttach(MainView mvpView) {
        mainView = mvpView;
    }

    @Override
    public void onDetach() {
        mainView = null;
    }
}
