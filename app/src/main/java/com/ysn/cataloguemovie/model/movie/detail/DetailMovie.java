
package com.ysn.cataloguemovie.model.movie.detail;

import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Generated;

import static com.ysn.cataloguemovie.data.db.DatabaseContract.*;
import static com.ysn.cataloguemovie.data.db.DatabaseContract.FavoriteColumns.*;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class DetailMovie implements Parcelable {

    @SerializedName("adult")
    private Boolean mAdult;
    @SerializedName("backdrop_path")
    private String mBackdropPath;
    @SerializedName("belongs_to_collection")
    private Object mBelongsToCollection;
    @SerializedName("budget")
    private Long mBudget;
    @SerializedName("genres")
    private List<Genre> mGenres;
    @SerializedName("homepage")
    private String mHomepage;
    @SerializedName("id")
    private Long mId;
    @SerializedName("imdb_id")
    private String mImdbId;
    @SerializedName("original_language")
    private String mOriginalLanguage;
    @SerializedName("original_title")
    private String mOriginalTitle;
    @SerializedName("overview")
    private String mOverview;
    @SerializedName("popularity")
    private Double mPopularity;
    @SerializedName("poster_path")
    private String mPosterPath;
    @SerializedName("production_companies")
    private List<ProductionCompany> mProductionCompanies;
    @SerializedName("production_countries")
    private List<ProductionCountry> mProductionCountries;
    @SerializedName("release_date")
    private String mReleaseDate;
    @SerializedName("revenue")
    private Long mRevenue;
    @SerializedName("runtime")
    private Long mRuntime;
    @SerializedName("spoken_languages")
    private List<SpokenLanguage> mSpokenLanguages;
    @SerializedName("status")
    private String mStatus;
    @SerializedName("tagline")
    private String mTagline;
    @SerializedName("title")
    private String mTitle;
    @SerializedName("video")
    private Boolean mVideo;
    @SerializedName("vote_average")
    private Double mVoteAverage;
    @SerializedName("vote_count")
    private Long mVoteCount;

    public DetailMovie() {
    }

    public DetailMovie(Boolean mAdult, String mBackdropPath, Object mBelongsToCollection,
                       Long mBudget, List<Genre> mGenres, String mHomepage, Long mId, String mImdbId,
                       String mOriginalLanguage, String mOriginalTitle, String mOverview,
                       Double mPopularity, String mPosterPath,
                       List<ProductionCompany> mProductionCompanies,
                       List<ProductionCountry> mProductionCountries, String mReleaseDate,
                       Long mRevenue, Long mRuntime, List<SpokenLanguage> mSpokenLanguages,
                       String mStatus, String mTagline, String mTitle, Boolean mVideo,
                       Double mVoteAverage, Long mVoteCount) {
        this.mAdult = mAdult;
        this.mBackdropPath = mBackdropPath;
        this.mBelongsToCollection = mBelongsToCollection;
        this.mBudget = mBudget;
        this.mGenres = mGenres;
        this.mHomepage = mHomepage;
        this.mId = mId;
        this.mImdbId = mImdbId;
        this.mOriginalLanguage = mOriginalLanguage;
        this.mOriginalTitle = mOriginalTitle;
        this.mOverview = mOverview;
        this.mPopularity = mPopularity;
        this.mPosterPath = mPosterPath;
        this.mProductionCompanies = mProductionCompanies;
        this.mProductionCountries = mProductionCountries;
        this.mReleaseDate = mReleaseDate;
        this.mRevenue = mRevenue;
        this.mRuntime = mRuntime;
        this.mSpokenLanguages = mSpokenLanguages;
        this.mStatus = mStatus;
        this.mTagline = mTagline;
        this.mTitle = mTitle;
        this.mVideo = mVideo;
        this.mVoteAverage = mVoteAverage;
        this.mVoteCount = mVoteCount;
    }

    public Boolean getAdult() {
        return mAdult;
    }

    public void setAdult(Boolean adult) {
        mAdult = adult;
    }

    public String getBackdropPath() {
        return mBackdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        mBackdropPath = backdropPath;
    }

    public Object getBelongsToCollection() {
        return mBelongsToCollection;
    }

    public void setBelongsToCollection(Object belongsToCollection) {
        mBelongsToCollection = belongsToCollection;
    }

    public Long getBudget() {
        return mBudget;
    }

    public void setBudget(Long budget) {
        mBudget = budget;
    }

    public List<Genre> getGenres() {
        return mGenres;
    }

    public void setGenres(List<Genre> genres) {
        mGenres = genres;
    }

    public String getHomepage() {
        return mHomepage;
    }

    public void setHomepage(String homepage) {
        mHomepage = homepage;
    }

    public Long getId() {
        return mId;
    }

    public void setId(Long id) {
        mId = id;
    }

    public String getImdbId() {
        return mImdbId;
    }

    public void setImdbId(String imdbId) {
        mImdbId = imdbId;
    }

    public String getOriginalLanguage() {
        return mOriginalLanguage;
    }

    public void setOriginalLanguage(String originalLanguage) {
        mOriginalLanguage = originalLanguage;
    }

    public String getOriginalTitle() {
        return mOriginalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        mOriginalTitle = originalTitle;
    }

    public String getOverview() {
        return mOverview;
    }

    public void setOverview(String overview) {
        mOverview = overview;
    }

    public Double getPopularity() {
        return mPopularity;
    }

    public void setPopularity(Double popularity) {
        mPopularity = popularity;
    }

    public String getPosterPath() {
        return mPosterPath;
    }

    public void setPosterPath(String posterPath) {
        mPosterPath = posterPath;
    }

    public List<ProductionCompany> getProductionCompanies() {
        return mProductionCompanies;
    }

    public void setProductionCompanies(List<ProductionCompany> productionCompanies) {
        mProductionCompanies = productionCompanies;
    }

    public List<ProductionCountry> getProductionCountries() {
        return mProductionCountries;
    }

    public void setProductionCountries(List<ProductionCountry> productionCountries) {
        mProductionCountries = productionCountries;
    }

    public String getReleaseDate() {
        return mReleaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        mReleaseDate = releaseDate;
    }

    public Long getRevenue() {
        return mRevenue;
    }

    public void setRevenue(Long revenue) {
        mRevenue = revenue;
    }

    public Long getRuntime() {
        return mRuntime;
    }

    public void setRuntime(Long runtime) {
        mRuntime = runtime;
    }

    public List<SpokenLanguage> getSpokenLanguages() {
        return mSpokenLanguages;
    }

    public void setSpokenLanguages(List<SpokenLanguage> spokenLanguages) {
        mSpokenLanguages = spokenLanguages;
    }

    public String getStatus() {
        return mStatus;
    }

    public void setStatus(String status) {
        mStatus = status;
    }

    public String getTagline() {
        return mTagline;
    }

    public void setTagline(String tagline) {
        mTagline = tagline;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public Boolean getVideo() {
        return mVideo;
    }

    public void setVideo(Boolean video) {
        mVideo = video;
    }

    public Double getVoteAverage() {
        return mVoteAverage;
    }

    public void setVoteAverage(Double voteAverage) {
        mVoteAverage = voteAverage;
    }

    public Long getVoteCount() {
        return mVoteCount;
    }

    public void setVoteCount(Long voteCount) {
        mVoteCount = voteCount;
    }

    @Override
    public String toString() {
        return "DetailMovie{" +
                "mAdult=" + mAdult +
                ", mBackdropPath='" + mBackdropPath + '\'' +
                ", mBelongsToCollection=" + mBelongsToCollection +
                ", mBudget=" + mBudget +
                ", mGenres=" + mGenres +
                ", mHomepage='" + mHomepage + '\'' +
                ", mId=" + mId +
                ", mImdbId='" + mImdbId + '\'' +
                ", mOriginalLanguage='" + mOriginalLanguage + '\'' +
                ", mOriginalTitle='" + mOriginalTitle + '\'' +
                ", mOverview='" + mOverview + '\'' +
                ", mPopularity=" + mPopularity +
                ", mPosterPath='" + mPosterPath + '\'' +
                ", mProductionCompanies=" + mProductionCompanies +
                ", mProductionCountries=" + mProductionCountries +
                ", mReleaseDate='" + mReleaseDate + '\'' +
                ", mRevenue=" + mRevenue +
                ", mRuntime=" + mRuntime +
                ", mSpokenLanguages=" + mSpokenLanguages +
                ", mStatus='" + mStatus + '\'' +
                ", mTagline='" + mTagline + '\'' +
                ", mTitle='" + mTitle + '\'' +
                ", mVideo=" + mVideo +
                ", mVoteAverage=" + mVoteAverage +
                ", mVoteCount=" + mVoteCount +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public DetailMovie(Cursor cursor) {
        this.mId = getColumnLong(cursor, FAVORITE_COLUMN_ID_MOVIE);
        this.mTitle = getColumnString(cursor, FAVORITE_COLUMN_TITLE);
        this.mReleaseDate = getColumnString(cursor, FAVORITE_COLUMN_RELEASE_DATE);
        this.mOverview = getColumnString(cursor, FAVORITE_COLUMN_OVERVIEW);
        this.mPosterPath = getColumnString(cursor, FAVORITE_COLUMN_POSTER_PATH);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.mAdult);
        dest.writeString(this.mBackdropPath);
        dest.writeParcelable((Parcelable) this.mBelongsToCollection, flags);
        dest.writeValue(this.mBudget);
        dest.writeList(this.mGenres);
        dest.writeString(this.mHomepage);
        dest.writeValue(this.mId);
        dest.writeString(this.mImdbId);
        dest.writeString(this.mOriginalLanguage);
        dest.writeString(this.mOriginalTitle);
        dest.writeString(this.mOverview);
        dest.writeValue(this.mPopularity);
        dest.writeString(this.mPosterPath);
        dest.writeList(this.mProductionCompanies);
        dest.writeList(this.mProductionCountries);
        dest.writeString(this.mReleaseDate);
        dest.writeValue(this.mRevenue);
        dest.writeValue(this.mRuntime);
        dest.writeList(this.mSpokenLanguages);
        dest.writeString(this.mStatus);
        dest.writeString(this.mTagline);
        dest.writeString(this.mTitle);
        dest.writeValue(this.mVideo);
        dest.writeValue(this.mVoteAverage);
        dest.writeValue(this.mVoteCount);
    }

    protected DetailMovie(Parcel in) {
        this.mAdult = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.mBackdropPath = in.readString();
        this.mBelongsToCollection = in.readParcelable(Object.class.getClassLoader());
        this.mBudget = (Long) in.readValue(Long.class.getClassLoader());
        this.mGenres = new ArrayList<Genre>();
        in.readList(this.mGenres, Genre.class.getClassLoader());
        this.mHomepage = in.readString();
        this.mId = (Long) in.readValue(Long.class.getClassLoader());
        this.mImdbId = in.readString();
        this.mOriginalLanguage = in.readString();
        this.mOriginalTitle = in.readString();
        this.mOverview = in.readString();
        this.mPopularity = (Double) in.readValue(Double.class.getClassLoader());
        this.mPosterPath = in.readString();
        this.mProductionCompanies = new ArrayList<ProductionCompany>();
        in.readList(this.mProductionCompanies, ProductionCompany.class.getClassLoader());
        this.mProductionCountries = new ArrayList<ProductionCountry>();
        in.readList(this.mProductionCountries, ProductionCountry.class.getClassLoader());
        this.mReleaseDate = in.readString();
        this.mRevenue = (Long) in.readValue(Long.class.getClassLoader());
        this.mRuntime = (Long) in.readValue(Long.class.getClassLoader());
        this.mSpokenLanguages = new ArrayList<SpokenLanguage>();
        in.readList(this.mSpokenLanguages, SpokenLanguage.class.getClassLoader());
        this.mStatus = in.readString();
        this.mTagline = in.readString();
        this.mTitle = in.readString();
        this.mVideo = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.mVoteAverage = (Double) in.readValue(Double.class.getClassLoader());
        this.mVoteCount = (Long) in.readValue(Long.class.getClassLoader());
    }

    public static final Parcelable.Creator<DetailMovie> CREATOR = new Parcelable.Creator<DetailMovie>() {
        @Override
        public DetailMovie createFromParcel(Parcel source) {
            return new DetailMovie(source);
        }

        @Override
        public DetailMovie[] newArray(int size) {
            return new DetailMovie[size];
        }
    };

}
