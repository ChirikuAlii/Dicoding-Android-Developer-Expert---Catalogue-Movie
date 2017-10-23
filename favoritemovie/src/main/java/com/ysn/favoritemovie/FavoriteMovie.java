package com.ysn.favoritemovie;

import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;

import static com.ysn.favoritemovie.DatabaseContract.FavoriteColumns.FAVORITE_COLUMN_ID;
import static com.ysn.favoritemovie.DatabaseContract.FavoriteColumns.FAVORITE_COLUMN_OVERVIEW;
import static com.ysn.favoritemovie.DatabaseContract.FavoriteColumns.FAVORITE_COLUMN_POSTER_PATH;
import static com.ysn.favoritemovie.DatabaseContract.FavoriteColumns.FAVORITE_COLUMN_RELEASE_DATE;
import static com.ysn.favoritemovie.DatabaseContract.FavoriteColumns.FAVORITE_COLUMN_TITLE;
import static com.ysn.favoritemovie.DatabaseContract.getColumnLong;
import static com.ysn.favoritemovie.DatabaseContract.getColumnString;

/**
 * Created by yudisetiawan on 10/23/17.
 */

public class FavoriteMovie implements Parcelable {

    private Long mId;
    private String mTitle;
    private String mReleaseDate;
    private String mOverview;
    private String mPosterPath;

    public FavoriteMovie() {

    }

    public FavoriteMovie(Cursor cursor) {
        this.mId = getColumnLong(cursor, FAVORITE_COLUMN_ID);
        this.mTitle = getColumnString(cursor, FAVORITE_COLUMN_TITLE);
        this.mReleaseDate = getColumnString(cursor, FAVORITE_COLUMN_RELEASE_DATE);
        this.mOverview = getColumnString(cursor, FAVORITE_COLUMN_OVERVIEW);
        this.mPosterPath = getColumnString(cursor, FAVORITE_COLUMN_POSTER_PATH);
    }

    protected FavoriteMovie(Parcel in) {
        mId = in.readLong();
        mTitle = in.readString();
        mReleaseDate = in.readString();
        mOverview = in.readString();
        mPosterPath = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(mId);
        dest.writeString(mTitle);
        dest.writeString(mReleaseDate);
        dest.writeString(mOverview);
        dest.writeString(mPosterPath);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<FavoriteMovie> CREATOR = new Creator<FavoriteMovie>() {
        @Override
        public FavoriteMovie createFromParcel(Parcel in) {
            return new FavoriteMovie(in);
        }

        @Override
        public FavoriteMovie[] newArray(int size) {
            return new FavoriteMovie[size];
        }
    };

    public Long getmId() {
        return mId;
    }

    public void setmId(Long mId) {
        this.mId = mId;
    }

    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getmReleaseDate() {
        return mReleaseDate;
    }

    public void setmReleaseDate(String mReleaseDate) {
        this.mReleaseDate = mReleaseDate;
    }

    public String getmOverview() {
        return mOverview;
    }

    public void setmOverview(String mOverview) {
        this.mOverview = mOverview;
    }

    public String getmPosterPath() {
        return mPosterPath;
    }

    public void setmPosterPath(String mPosterPath) {
        this.mPosterPath = mPosterPath;
    }

    @Override
    public String toString() {
        return "FavoriteMovie{" +
                "mId=" + mId +
                ", mTitle='" + mTitle + '\'' +
                ", mReleaseDate='" + mReleaseDate + '\'' +
                ", mOverview='" + mOverview + '\'' +
                ", mPosterPath='" + mPosterPath + '\'' +
                '}';
    }
}
