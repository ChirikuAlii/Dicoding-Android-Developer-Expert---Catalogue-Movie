package com.ysn.cataloguemovie.ui.fragments.nowplaying.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ysn.cataloguemovie.BuildConfig;
import com.ysn.cataloguemovie.R;
import com.ysn.cataloguemovie.model.movie.nowplaying.ResultNowPlaying;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by yudisetiawan on 9/16/17.
 */

public class AdapterNowPlayingMovie extends RecyclerView.Adapter<AdapterNowPlayingMovie.ViewHolderNowPlayingMovie> {

    private final String TAG = getClass().getSimpleName();
    private Context context;
    private List<ResultNowPlaying> resultNowPlayingList;
    private ListenerViewHolderNowPlayingMovie listenerViewHolderNowPlayingMovie;

    public AdapterNowPlayingMovie(Context context, List<ResultNowPlaying> resultNowPlayingList,
                                  ListenerViewHolderNowPlayingMovie listenerViewHolderNowPlayingMovie) {
        this.context = context;
        this.resultNowPlayingList = resultNowPlayingList;
        this.listenerViewHolderNowPlayingMovie = listenerViewHolderNowPlayingMovie;
    }

    @Override
    public ViewHolderNowPlayingMovie onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_now_playing, null);
        return new ViewHolderNowPlayingMovie(view);
    }

    @Override
    public void onBindViewHolder(ViewHolderNowPlayingMovie holder, int position) {
        ResultNowPlaying resultNowPlaying = resultNowPlayingList.get(position);
        Glide.with(context)
                .load(BuildConfig.BASE_URL_IMAGE + resultNowPlaying.getPosterPath())
                .placeholder(R.drawable.ic_image_black_24dp)
                .error(R.drawable.ic_broken_image_black_24dp)
                .into(holder.imageViewPosterItemNowPlaying);
        holder.textViewJudulFilmItemNowPlaying.setText(resultNowPlaying.getTitle());
        holder.textViewDeskripsiFilmItemNowPlaying.setText(resultNowPlaying.getOverview());
        holder.textViewTanggalReleaseItemNowPlaying.setText(resultNowPlaying.getReleaseDate());
    }

    @Override
    public int getItemCount() {
        return resultNowPlayingList.size();
    }

    class ViewHolderNowPlayingMovie extends RecyclerView.ViewHolder {

        @BindView(R.id.image_view_poster_item_now_playing)
        ImageView imageViewPosterItemNowPlaying;
        @BindView(R.id.text_view_judul_film_item_now_playing)
        TextView textViewJudulFilmItemNowPlaying;
        @BindView(R.id.text_view_deskripsi_film_item_now_playing)
        TextView textViewDeskripsiFilmItemNowPlaying;
        @BindView(R.id.text_view_tanggal_release_item_now_playing)
        TextView textViewTanggalReleaseItemNowPlaying;

        ViewHolderNowPlayingMovie(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @OnClick({
                R.id.button_detail_item_now_playing,
                R.id.button_share_item_now_playing
        })
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.button_detail_item_now_playing:
                    listenerViewHolderNowPlayingMovie
                            .onItemClickDetail(resultNowPlayingList.get(getAdapterPosition()));
                    break;
                case R.id.button_share_item_now_playing:
                    listenerViewHolderNowPlayingMovie
                            .onItemClickShare(resultNowPlayingList.get(getAdapterPosition()));
                    break;
            }
        }
    }

    public interface ListenerViewHolderNowPlayingMovie {

        void onItemClickDetail(ResultNowPlaying resultNowPlaying);

        void onItemClickShare(ResultNowPlaying resultNowPlaying);

    }

}
