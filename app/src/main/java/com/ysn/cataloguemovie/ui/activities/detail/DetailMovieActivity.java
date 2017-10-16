package com.ysn.cataloguemovie.ui.activities.detail;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.ysn.cataloguemovie.App;
import com.ysn.cataloguemovie.BuildConfig;
import com.ysn.cataloguemovie.R;
import com.ysn.cataloguemovie.data.manager.DataManager;
import com.ysn.cataloguemovie.di.component.ActivityComponent;
import com.ysn.cataloguemovie.di.component.DaggerActivityComponent;
import com.ysn.cataloguemovie.di.module.ActivityModule;
import com.ysn.cataloguemovie.model.movie.detail.DetailMovie;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DetailMovieActivity extends AppCompatActivity implements DetailMovieView {

    private final String TAG = getClass().getSimpleName();
    private DetailMoviePresenter detailMoviePresenter;

    @BindView(R.id.image_view_poster_activity_detail_movie)
    ImageView imageViewPosterDetailMovieActivity;
    @BindView(R.id.text_view_judul_film_activity_detail_movie)
    TextView textViewJudulDetailMovieActivity;
    @BindView(R.id.text_view_overview_activity_detail_movie)
    TextView textViewDeskripsiDetailMovieActivity;
    @BindView(R.id.image_view_add_to_favorite_movie_activity_detail_movie)
    ImageView imageViewAddToFavoriteMovieDetailMovieActivity;
    @BindView(R.id.text_view_value_release_date_activity_detail_movie)
    TextView textViewValueReleaseDateActivityDetailMovie;
    private ProgressDialog progressDialog;

    @Inject
    DataManager dataManager;
    private ActivityComponent activityComponent;
    private DetailMovie detailMovie;
    private long idMovie;

    public ActivityComponent getActivityComponent() {
        if (activityComponent == null) {
            activityComponent = DaggerActivityComponent
                    .builder()
                    .activityModule(new ActivityModule(this))
                    .appComponent(App.get(this).getAppComponent())
                    .build();
        }
        return activityComponent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movie);
        ButterKnife.bind(this);
        getActivityComponent().inject(this);
        initPresenter();
        onAttachView();
        setToolbar();
        onLoadData();
    }

    private void setToolbar() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_detail_movie_activity, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            case R.id.menu_item_refresh_menu_detail_movie_activity:
                doRefreshData();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @OnClick({
            R.id.image_view_add_to_favorite_movie_activity_detail_movie
    })
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.image_view_add_to_favorite_movie_activity_detail_movie:
                if (imageViewAddToFavoriteMovieDetailMovieActivity.getTag().equals("star border")) {
                    if (detailMovie == null) {
                        Toast.makeText(
                                this,
                                R.string.refresh_data,
                                Toast.LENGTH_LONG
                        ).show();
                    } else {
                        detailMoviePresenter.onAddToFavoriteMovie(detailMovie, dataManager);
                    }
                } else {
                    if (detailMovie == null) {
                        Toast.makeText(
                                this,
                                R.string.refresh_data,
                                Toast.LENGTH_LONG
                        ).show();
                    } else {
                        detailMoviePresenter.onDeleteFromFavoriteMovie(detailMovie, dataManager);
                    }
                }
                break;
        }
    }

    private void onLoadData() {
        Bundle bundle = getIntent().getExtras();
        idMovie = bundle.getLong("idMovie");

        if (progressDialog == null) {
            progressDialog = new ProgressDialog(this);
        }
        progressDialog.setMessage("Please wait");
        progressDialog.setCancelable(false);
        progressDialog.show();
        detailMoviePresenter.onLoadData(idMovie, dataManager);
    }

    private void doRefreshData() {
        progressDialog.show();
        detailMoviePresenter.onLoadData(idMovie, dataManager);
    }

    private void initPresenter() {
        detailMoviePresenter = new DetailMoviePresenter();
    }

    @Override
    public void onAttachView() {
        detailMoviePresenter.onAttach(this);
    }

    @Override
    public void onDetachView() {
        detailMoviePresenter.onDetach();
    }

    @Override
    public void loadData(DetailMovie detailMovie, boolean isFavoriteMovie) {
        this.detailMovie = detailMovie;
        progressDialog.dismiss();
        textViewJudulDetailMovieActivity.setText(detailMovie.getOriginalTitle());
        textViewDeskripsiDetailMovieActivity.setText(detailMovie.getOverview());
        textViewValueReleaseDateActivityDetailMovie.setText(detailMovie.getReleaseDate());
        Glide.with(this)
                .load(BuildConfig.BASE_URL_IMAGE + "" + detailMovie.getPosterPath())
                .placeholder(R.drawable.ic_image_black_24dp)
                .error(R.drawable.ic_broken_image_black_24dp)
                .into(imageViewPosterDetailMovieActivity);
        imageViewAddToFavoriteMovieDetailMovieActivity.setBackgroundResource(
                isFavoriteMovie
                        ? R.drawable.ic_star_black_24dp
                        : R.drawable.ic_star_border_black_24dp
        );
        imageViewAddToFavoriteMovieDetailMovieActivity.setTag(
                isFavoriteMovie
                        ? "star full"
                        : "star border"
        );
    }

    @Override
    public void loadDataFailed() {
        progressDialog.dismiss();
        Toast.makeText(this, "Load data failed", Toast.LENGTH_LONG)
                .show();
    }

    @Override
    public void addToFavoriteMovie() {
        imageViewAddToFavoriteMovieDetailMovieActivity.setBackgroundResource(R.drawable.ic_star_black_24dp);
        imageViewAddToFavoriteMovieDetailMovieActivity.setTag("star full");
    }

    @Override
    public void addToFavoriteMovieFailed(String message) {
        AlertDialog alertDialogError = new AlertDialog.Builder(this)
                .setTitle("Error")
                .setMessage(message)
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                })
                .create();
        alertDialogError.show();
    }

    @Override
    public void deleteFromFavoriteMovie() {
        imageViewAddToFavoriteMovieDetailMovieActivity.setBackgroundResource(R.drawable.ic_star_border_black_24dp);
        imageViewAddToFavoriteMovieDetailMovieActivity.setTag("star border");
    }

    @Override
    public void deleteFromFavoriteMovieFailed(String message) {
        AlertDialog alertDialogError = new AlertDialog.Builder(this)
                .setTitle("Error")
                .setMessage(message)
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                })
                .create();
        alertDialogError.show();
    }
}
