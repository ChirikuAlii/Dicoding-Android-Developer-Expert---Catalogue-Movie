package com.ysn.cataloguemovie.ui.activities.main;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.ysn.cataloguemovie.R;
import com.ysn.cataloguemovie.ui.activities.reminder.daily.DailyReminderActivity;
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
        doLoadData();
    }

    private void doLoadData() {
        mainPresenter.onLoadData(this);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main_activity, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_item_daily_reminder_menu_main_activity:
                startActivity(new Intent(this, DailyReminderActivity.class));
                return true;
            case R.id.menu_item_upcoming_reminder_menu_main_activity:
                // todo: do something in here
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void loadData() {
        /** nothing to do in here */
        Log.d(TAG, "loadData Success");
    }
}
