package com.ysn.cataloguemovie.ui.base;

/**
 * Created by root on 30/08/17.
 */

public interface MvpPresenter<T extends MvpView> {

    void onAttach(T mvpView);

    void onDetach();

}
