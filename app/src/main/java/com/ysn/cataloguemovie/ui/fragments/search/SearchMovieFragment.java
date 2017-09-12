package com.ysn.cataloguemovie.ui.fragments.search;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchMovieFragment extends Fragment implements SearchMovieView, View.OnClickListener {

    private final String TAG = getClass().getSimpleName();
    private SearchMoviePresenter searchMoviePresenter;

    private EditText editTextKeywordSearchMovieFragment;
    private Button buttonCariSearchMovieFragment;
    private RecyclerView recyclerViewDataSearchMovieFragment;
    private ProgressDialog progressDialog;
    private View view;

    public SearchMovieFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_search_movie, container, false);
        initViews(view);
        initListener();
        initPresenter();
        onAttachView();
        return view;
    }

    private void initPresenter() {
        searchMoviePresenter = new SearchMoviePresenter();
    }

    private void initListener() {
        buttonCariSearchMovieFragment.setOnClickListener(this);
    }

    private void initViews(View view) {
        editTextKeywordSearchMovieFragment = (EditText) view.findViewById(R.id.edit_text_kata_kunci_fragment_search_movie);
        buttonCariSearchMovieFragment = (Button) view.findViewById(R.id.button_cari_fragment_search_movie);
        recyclerViewDataSearchMovieFragment = (RecyclerView) view.findViewById(R.id.recycler_view_movie_fragment_search_movie);
        recyclerViewDataSearchMovieFragment.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerViewDataSearchMovieFragment.addItemDecoration(
                new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL)
        );
    }

    @Override
    public void onAttachView() {
        searchMoviePresenter.onAttach(this);
    }

    @Override
    public void onDetachView() {
        searchMoviePresenter.onDetach();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_cari_fragment_search_movie:
                String keyword = editTextKeywordSearchMovieFragment.getText().toString().trim();
                if (keyword.isEmpty()) {
                    Toast.makeText(
                            getContext(),
                            getString(R.string.kata_kunci_belum_di_isi),
                            Toast.LENGTH_SHORT
                    ).show();
                } else {
                    initProgressDialog();
                    searchMoviePresenter.onSearchMovie(getContext(), keyword);
                }
                break;
        }
    }

    private void initProgressDialog() {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(getContext());
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
        Toast.makeText(getContext(), "Search movie failed!", Toast.LENGTH_SHORT)
                .show();
    }

    @Override
    public void onClickItem(Intent intentDetailMovieActivity) {
        startActivity(intentDetailMovieActivity);
    }
}
