package com.ysn.cataloguemovie.ui.activities.main;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.ysn.cataloguemovie.R;
import com.ysn.cataloguemovie.ui.activities.settings.SettingsActivity;
import com.ysn.cataloguemovie.ui.fragments.favorite.FavoriteMovieFragment;
import com.ysn.cataloguemovie.ui.fragments.nowplaying.NowPlayingFragment;
import com.ysn.cataloguemovie.ui.fragments.search.SearchMovieFragment;
import com.ysn.cataloguemovie.ui.fragments.upcoming.UpcomingMovieFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity
        implements MainView, NavigationView.OnNavigationItemSelectedListener {

    private final String TAG = getClass().getSimpleName();
    private MainPresenter mainPresenter;

    @BindView(R.id.toolbar_app_bar_main)
    Toolbar toolbar;
    @BindView(R.id.drawer_layout_activity_main)
    DrawerLayout drawerLayout;
    @BindView(R.id.nav_view_activity_main)
    NavigationView navigationView;
    @BindView(R.id.tab_layout_content_main)
    TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initPresenter();
        onAttachView();
        initViews();
        doLoadData();
    }

    private void initViews() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_app_bar_main);
        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this,
                drawerLayout,
                toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close
        );
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frame_layout_content_main, new NowPlayingFragment())
                .commit();

        tabLayout.addTab(
                tabLayout.newTab().setText(getString(R.string.now_playing))
        );
        tabLayout.addTab(
                tabLayout.newTab().setText(getString(R.string.upcoming))
        );
        tabLayout.addTab(
                tabLayout.newTab().setText(getString(R.string.search))
        );
        tabLayout.addTab(
                tabLayout.newTab().setText("Favorite")
        );
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Fragment fragmentSelected = null;
                switch (tab.getPosition()) {
                    case 0:
                        fragmentSelected = new NowPlayingFragment();
                        break;
                    case 1:
                        fragmentSelected = new UpcomingMovieFragment();
                        break;
                    case 2:
                        fragmentSelected = new SearchMovieFragment();
                        break;
                    case 3:
                        fragmentSelected = new FavoriteMovieFragment();
                        break;
                }
                if (fragmentSelected != null) {
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.frame_layout_content_main, fragmentSelected)
                            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                            .commit();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                /** nothing to do in here */
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                /** nothing to do in here */
            }
        });

    }

    private void doLoadData() {
        mainPresenter.onLoadData(this);
    }

    /**
     * unused
     */
    private void loadView() {
        /*getSupportFragmentManager().beginTransaction()
                .replace(R.id.frame_layout_container_content_activity_main, new SearchMovieFragment())
                .commit();*/
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
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void loadData() {
        /** nothing to do in here */
        Log.d(TAG, "loadData Success");
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        boolean selectedItem = false;
        switch (item.getItemId()) {
            case R.id.nav_item_now_playing_activity_main_nav_drawer:
                selectedItem = true;
                tabLayout.getTabAt(0).select();
                break;
            case R.id.nav_item_upcoming_activity_main_nav_drawer:
                tabLayout.getTabAt(1).select();
                break;
            case R.id.nav_item_search_movie_activity_main_nav_drawer:
                tabLayout.getTabAt(2).select();
                break;
            case R.id.nav_item_setting_activity_main_nav_drawer:
                Intent intentSettingsActivity = new Intent(this, SettingsActivity.class);
                startActivity(intentSettingsActivity);
                break;
        }

        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout_activity_main);
        drawerLayout.closeDrawer(GravityCompat.START);
        return selectedItem;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout_activity_main);
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

}
