package com.ysn.favoritemovie;

import android.database.Cursor;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.ysn.favoritemovie.adapter.AdapterFavoriteMovie;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.ysn.favoritemovie.DatabaseContract.*;

public class MainActivity extends AppCompatActivity
        implements LoaderManager.LoaderCallbacks<Cursor> {

    private final String TAG = getClass().getSimpleName();

    @BindView(R.id.recycler_view_favorite_movie_activity_main)
    RecyclerView recyclerViewFavoriteMovieActivityMain;
    @BindView(R.id.progress_bar_activity_main)
    ProgressBar progressBarActivityMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        showLoading();
        getSupportLoaderManager().initLoader(110, null, this);
    }

    private void showLoading() {
        progressBarActivityMain.setVisibility(View.VISIBLE);
        recyclerViewFavoriteMovieActivityMain.setVisibility(View.GONE);
    }

    @Override
    protected void onResume() {
        super.onResume();
        getSupportLoaderManager().restartLoader(110, null, this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        getSupportLoaderManager().destroyLoader(110);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(this, CONTENT_URI, null, null, null, null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        hideLoading();
        List<FavoriteMovie> favoriteMovies = new ArrayList<>();
        if (data != null && data.getCount() > 0) {
            while (data.moveToNext()) {
                FavoriteMovie favoriteMovie = new FavoriteMovie(data);
                favoriteMovies.add(favoriteMovie);
            }
        }
        AdapterFavoriteMovie adapterFavoriteMovie = new AdapterFavoriteMovie(
                this,
                favoriteMovies,
                new AdapterFavoriteMovie.ListenerAdapterFavoriteMovie() {
                    @Override
                    public void onItemClickDetail(FavoriteMovie favoriteMovie) {
                        // TODO: 10/24/17 do something in here
                    }

                    @Override
                    public void onItemClickShare(FavoriteMovie favoriteMovie) {
                        // TODO: 10/24/17 do something in here
                    }
                }
        );
        recyclerViewFavoriteMovieActivityMain.setLayoutManager(new LinearLayoutManager(this));
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(
                this,
                DividerItemDecoration.VERTICAL
        );
        recyclerViewFavoriteMovieActivityMain.addItemDecoration(dividerItemDecoration);
        recyclerViewFavoriteMovieActivityMain.setAdapter(adapterFavoriteMovie);
    }

    private void hideLoading() {
        recyclerViewFavoriteMovieActivityMain.setVisibility(View.VISIBLE);
        progressBarActivityMain.setVisibility(View.GONE);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        /** nothing to do in here */
    }
}
