
package com.ysn.cataloguemovie.model.movie.nowplaying;

import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class NowPlaying {

    @SerializedName("dates")
    private Dates mDates;
    @SerializedName("page")
    private Long mPage;
    @SerializedName("results")
    private List<ResultNowPlaying> mResultNowPlayings;
    @SerializedName("total_pages")
    private Long mTotalPages;
    @SerializedName("total_results")
    private Long mTotalResults;

    public Dates getDates() {
        return mDates;
    }

    public void setDates(Dates dates) {
        mDates = dates;
    }

    public Long getPage() {
        return mPage;
    }

    public void setPage(Long page) {
        mPage = page;
    }

    public List<ResultNowPlaying> getResults() {
        return mResultNowPlayings;
    }

    public void setResults(List<ResultNowPlaying> resultNowPlayings) {
        mResultNowPlayings = resultNowPlayings;
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
