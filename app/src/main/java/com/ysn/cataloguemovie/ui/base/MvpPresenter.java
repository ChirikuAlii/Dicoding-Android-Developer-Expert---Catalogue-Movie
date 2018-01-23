/*
 * Created by Yudi Setiawan on 1/23/18 10:29 PM
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 1/23/18 10:21 PM
 */

package com.ysn.cataloguemovie.ui.base;

/**
 * Created by root on 30/08/17.
 */

public interface MvpPresenter<T extends MvpView> {

    void onAttach(T mvpView);

    void onDetach();

}
