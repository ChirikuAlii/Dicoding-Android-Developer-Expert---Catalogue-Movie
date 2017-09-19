package com.ysn.cataloguemovie.ui.fragments.search;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ysn.cataloguemovie.R;
import com.ysn.cataloguemovie.ui.fragments.search.adapter.AdapterSearchMovie;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchMovieFragment extends Fragment implements SearchMovieView {

    private final String TAG = getClass().getSimpleName();
    private SearchMoviePresenter searchMoviePresenter;

    @BindView(R.id.edit_text_kata_kunci_fragment_search_movie)
    EditText editTextKeywordSearchMovieFragment;
    @BindView(R.id.recycler_view_movie_fragment_search_movie)
    RecyclerView recyclerViewDataSearchMovieFragment;

    private ProgressDialog progressDialog;
    private View view;
    private Context context;

    public SearchMovieFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_search_movie, container, false);
        ButterKnife.bind(this, view);
        initPresenter();
        onAttachView();
        doLoadData();
        return view;
    }

    private void initPresenter() {
        searchMoviePresenter = new SearchMoviePresenter();
    }

    private void doLoadData() {
        if (context == null) {
            context = getActivity();
        }
        recyclerViewDataSearchMovieFragment.setLayoutManager(new LinearLayoutManager(context));
        recyclerViewDataSearchMovieFragment.addItemDecoration(
                new DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        );
    }

    @OnClick(R.id.button_cari_fragment_search_movie)
    public void onClick() {
        String keyword = editTextKeywordSearchMovieFragment.getText().toString().trim();
        if (keyword.isEmpty()) {
            Toast.makeText(
                    context,
                    getString(R.string.keyword_validation_message),
                    Toast.LENGTH_SHORT
            ).show();
        } else {
            initProgressDialog();
            searchMoviePresenter.onSearchMovie(context, keyword);
        }
    }

    @Override
    public void onAttachView() {
        searchMoviePresenter.onAttach(this);
    }

    @Override
    public void onDetachView() {
        searchMoviePresenter.onDetach();
    }

    private void initProgressDialog() {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(context);
        }
        progressDialog.setMessage("Please wait");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    @Override
    public void searchMovie(AdapterSearchMovie adapterSearchMovie) {
        dismissProgressDialog();
        recyclerViewDataSearchMovieFragment.setAdapter(adapterSearchMovie);
    }

    private void dismissProgressDialog() {
        progressDialog.dismiss();
    }

    @Override
    public void searchMovieFailed() {
        dismissProgressDialog();
        Toast.makeText(context, "Search movie failed!", Toast.LENGTH_SHORT)
                .show();
    }

    @Override
    public void onClickItem(Intent intentDetailMovieActivity) {
        startActivity(intentDetailMovieActivity);
    }
}
