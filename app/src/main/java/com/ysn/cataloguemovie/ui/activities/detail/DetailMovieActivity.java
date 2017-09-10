package com.ysn.cataloguemovie.ui.activities.detail;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.ysn.cataloguemovie.BuildConfig;
import com.ysn.cataloguemovie.R;
import com.ysn.cataloguemovie.model.movie.detail.DetailMovie;

public class DetailMovieActivity extends AppCompatActivity implements DetailMovieView {

    private final String TAG = getClass().getSimpleName();
    private DetailMoviePresenter detailMoviePresenter;

    private ImageView imageViewPosterDetailMovieActivity;
    private TextView textViewJudulDetailMovieActivity;
    private TextView textViewDeskripsiDetailMovieActivity;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movie);
        initPresenter();
        onAttachView();
        initViews();
        onLoadData();
    }

    private void initViews() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        imageViewPosterDetailMovieActivity = (ImageView) findViewById(R.id.image_view_poster_activity_detail_movie);
        textViewJudulDetailMovieActivity = (TextView) findViewById(R.id.text_view_judul_film_activity_detail_movie);
        textViewDeskripsiDetailMovieActivity = (TextView) findViewById(R.id.text_view_overview_activity_detail_movie);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void onLoadData() {
        Bundle bundle = getIntent().getExtras();
        long idMovie = bundle.getLong("idMovie");

        if (progressDialog == null) {
            progressDialog = new ProgressDialog(this);
        }
        progressDialog.setMessage("Please wait");
        progressDialog.setCancelable(false);
        progressDialog.show();
        detailMoviePresenter.onLoadData(idMovie);
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
    public void loadData(DetailMovie detailMovie) {
        progressDialog.dismiss();
        textViewJudulDetailMovieActivity.setText(detailMovie.getOriginalTitle());
        textViewDeskripsiDetailMovieActivity.setText(detailMovie.getOverview());
        Glide.with(this)
                .load(BuildConfig.BASE_URL_IMAGE + "" + detailMovie.getPosterPath())
                .placeholder(R.drawable.ic_image_black_24dp)
                .error(R.drawable.ic_broken_image_black_24dp)
                .into(imageViewPosterDetailMovieActivity);
    }

    @Override
    public void loadDataFailed() {
        Toast.makeText(this, "Load data failed", Toast.LENGTH_LONG)
                .show();
    }
}
