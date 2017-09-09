package com.ysn.cataloguemovie.api;

import com.ysn.cataloguemovie.model.movie.search.SearchMovie;
import com.ysn.cataloguemovie.model.movie.upcoming.ResultUpcomingMovie;
import com.ysn.cataloguemovie.model.movie.upcoming.UpcomingMovie;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by root on 06/09/17.
 */

public interface TheMovieApiService {

    @GET("search/movie")
    Observable<SearchMovie> searchMovie(
            @Query("api_key") String apiKey,
            @Query("language") String language,
            @Query("query") String query
    );

    @GET("movie/{MovieId}")
    void getDetailMovie(
            @Path("MovieId") String movieId,
            @Query("api_key") String apiKey,
            @Query("language") String language
    );

    @GET("movie/upcoming")
    Observable<UpcomingMovie> getUpcomingMovie(
            @Query("api_key") String apiKey,
            @Query("language") String language
    );

}
