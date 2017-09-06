package com.ysn.cataloguemovie.ui.activities.main;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.ysn.cataloguemovie.R;
import com.ysn.cataloguemovie.ui.fragments.SearchMovieFragment;

public class MainActivity extends AppCompatActivity implements MainView{

    private final String TAG = getClass().getSimpleName();
    private MainPresenter mainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initPresenter();
        onAttachView();
        loadView();
    }

    private void loadView() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frame_layout_container_content_activity_main, new SearchMovieFragment())
                .commit();
    }

    private void initPresenter() {
        mainPresenter = new MainPresenter();
    }

    @Override
    public void onAttachView() {
        mainPresenter.onAttach(this);
    }

    @Override
    public void onDetachView() {
        mainPresenter.onDetach();
    }
}
