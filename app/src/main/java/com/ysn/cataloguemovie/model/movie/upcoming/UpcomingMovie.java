
/*
 * Created by Yudi Setiawan on 1/23/18 10:29 PM
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 1/23/18 10:21 PM
 */

package com.ysn.cataloguemovie.model.movie.upcoming;

import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class UpcomingMovie {

    @SerializedName("dates")
    private DatesUpcomingMovie mDatesUpcomingMovie;
    @SerializedName("page")
    private Long mPage;
    @SerializedName("results")
    private List<ResultUpcomingMovie> mResultUpcomingMovies;
    @SerializedName("total_pages")
    private Long mTotalPages;
    @SerializedName("total_results")
    private Long mTotalResults;

    public DatesUpcomingMovie getDates() {
        return mDatesUpcomingMovie;
    }

    public void setDates(DatesUpcomingMovie datesUpcomingMovie) {
        mDatesUpcomingMovie = datesUpcomingMovie;
    }

    public Long getPage() {
        return mPage;
    }

    public void setPage(Long page) {
        mPage = page;
    }

    public List<ResultUpcomingMovie> getResults() {
        return mResultUpcomingMovies;
    }

    public void setResults(List<ResultUpcomingMovie> resultUpcomingMovies) {
        mResultUpcomingMovies = resultUpcomingMovies;
    }

    public Long getTotalPages() {
        return mTotalPages;
    }

    public void setTotalPages(Long totalPages) {
        mTotalPages = totalPages;
    }

    public Long getTotalResults() {
        return mTotalResults;
    }

    public void setTotalResults(Long totalResults) {
        mTotalResults = totalResults;
    }

}
