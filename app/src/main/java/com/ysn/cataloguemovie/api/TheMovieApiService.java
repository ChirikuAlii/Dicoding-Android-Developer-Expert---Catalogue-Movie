/*
 * Created by Yudi Setiawan on 1/23/18 10:29 PM
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 1/23/18 10:12 PM
 */

package com.ysn.cataloguemovie.api;

import com.ysn.cataloguemovie.model.movie.detail.DetailMovie;
import com.ysn.cataloguemovie.model.movie.nowplaying.NowPlaying;
import com.ysn.cataloguemovie.model.movie.search.SearchMovie;
import com.ysn.cataloguemovie.model.movie.upcoming.UpcomingMovie;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface TheMovieApiService {

    @GET("search/movie")
    Observable<SearchMovie> searchMovie(
            @Query("api_key") String apiKey,
            @Query("language") String language,
            @Query("query") String query
    );

    @GET("movie/{MovieId}")
    Observable<DetailMovie> getDetailMovie(
            @Path("MovieId") String movieId,
            @Query("api_key") String apiKey,
            @Query("language") String language
    );

    @GET("movie/upcoming")
    Observable<UpcomingMovie> getUpcomingMovie(
            @Query("api_key") String apiKey,
            @Query("language") String language
    );

    @GET("movie/now_playing")
    Observable<NowPlaying> getNowPlayingMovie(
            @Query("api_key") String apiKey,
            @Query("language") String language
    );

}
