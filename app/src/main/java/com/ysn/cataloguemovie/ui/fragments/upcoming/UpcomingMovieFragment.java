package com.ysn.cataloguemovie.ui.fragments.upcoming;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.ysn.cataloguemovie.R;
import com.ysn.cataloguemovie.model.movie.upcoming.ResultUpcomingMovie;
import com.ysn.cataloguemovie.ui.activities.detail.DetailMovieActivity;
import com.ysn.cataloguemovie.ui.fragments.upcoming.adapter.AdapterUpcomingMovie;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 */
public class UpcomingMovieFragment extends Fragment implements UpcomingMovieView {

    private final String TAG = getClass().getSimpleName();
    private UpcomingMoviePresenter upcomingMoviePresenter;

    @BindView(R.id.progress_bar_loading_fragment_upcoming_movie)
    ProgressBar progressBarLoadingFragmentUpcomingMovie;
    @BindView(R.id.recycler_view_data_fragment_upcoming_movie)
    RecyclerView recyclerViewDataFragmentUpcomingMovie;
    private Context context;

    public UpcomingMovieFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View viewRoot = inflater.inflate(R.layout.fragment_upcoming_movie, container, false);
        ButterKnife.bind(this, viewRoot);
        initPresenter();
        onAttachView();
        doLoadData();
        return viewRoot;
    }

    private void doLoadData() {
        if (context == null) {
            context = getActivity();
        }
        progressBarLoadingFragmentUpcomingMovie.setVisibility(View.VISIBLE);
        recyclerViewDataFragmentUpcomingMovie.setVisibility(View.GONE);
        upcomingMoviePresenter.onLoadData(context);
    }

    private void initPresenter() {
        upcomingMoviePresenter = new UpcomingMoviePresenter();
    }

    @Override
    public void onAttachView() {
        upcomingMoviePresenter.onAttach(this);
    }

    @Override
    public void onDetachView() {
        upcomingMoviePresenter.onDetach();
    }

    @Override
    public void itemClickDetail(ResultUpcomingMovie resultUpcomingMovie) {
        Intent intentDetailMovieActivity = new Intent(context, DetailMovieActivity.class);
        intentDetailMovieActivity.putExtra("idMovie", resultUpcomingMovie.getId());
        startActivity(intentDetailMovieActivity);
    }

    @Override
    public void itemClickShare(ResultUpcomingMovie resultUpcomingMovie) {
        Intent intentSharingMovie = new Intent(Intent.ACTION_SEND);
        intentSharingMovie.setType("text/plain");
        String bodyMessage = "Upcoming movie: " + resultUpcomingMovie.getTitle();
        intentSharingMovie.putExtra(Intent.EXTRA_SUBJECT, "Upcoming Movie");
        intentSharingMovie.putExtra(Intent.EXTRA_TEXT, bodyMessage);
        startActivity(intentSharingMovie);
    }

    @Override
    public void loadData(AdapterUpcomingMovie adapterUpcomingMovie) {
        progressBarLoadingFragmentUpcomingMovie.setVisibility(View.GONE);
        recyclerViewDataFragmentUpcomingMovie.setVisibility(View.VISIBLE);

        recyclerViewDataFragmentUpcomingMovie.setLayoutManager(new LinearLayoutManager(context));
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(context, DividerItemDecoration.VERTICAL);
        recyclerViewDataFragmentUpcomingMovie.addItemDecoration(dividerItemDecoration);
        recyclerViewDataFragmentUpcomingMovie.setAdapter(adapterUpcomingMovie);
    }

    @Override
    public void loadDataFailed(String message) {
        progressBarLoadingFragmentUpcomingMovie.setVisibility(View.GONE);
        recyclerViewDataFragmentUpcomingMovie.setVisibility(View.VISIBLE);
        Toast.makeText(
                context,
                message,
                Toast.LENGTH_LONG
        ).show();
    }
}
