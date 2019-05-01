package com.mohammed.hmod_.animeapp.DataEntity;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Comparator;

public class Anime implements Parcelable {

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private String id;
    private String createdAt;
    private String synopsis;        //done
    private String posterImage;     //done
    private String canonicalTitle;  //done
    private Integer userCount;      //done
    private Integer favoritesCount; //done
    private String youtubeVideoId;   //done


    public Anime( String id,String createdAt, String synopsis, String posterImage, String canonicalTitle, Integer userCount, Integer favoritesCount, String youtubeVideoId) {
        super();
        this.id = id;
        this.createdAt = createdAt;
        this.synopsis = synopsis;
        this.posterImage = posterImage;
        this.canonicalTitle = canonicalTitle;
        this.userCount = userCount;
        this.favoritesCount = favoritesCount;
        this.youtubeVideoId = youtubeVideoId;


    }

    public Anime() {

    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }


    public String getCanonicalTitle() {
        return canonicalTitle;
    }

    public void setCanonicalTitle(String canonicalTitle) {
        this.canonicalTitle = canonicalTitle;
    }

    public Integer getUserCount() {
        return userCount;
    }

    public void setUserCount(Integer userCount) {
        this.userCount = userCount;
    }

    public Integer getFavoritesCount() {
        return favoritesCount;
    }

    public void setFavoritesCount(Integer favoritesCount) {
        this.favoritesCount = favoritesCount;
    }


    public String getYoutubeVideoId() {
        return youtubeVideoId;
    }

    public void setYoutubeVideoId(String youtubeVideoId) {
        this.youtubeVideoId = youtubeVideoId;
    }

    public String getPosterImage() {
        return posterImage;
    }

    public void setPosterImage(String posterImage) {
        this.posterImage = posterImage;
    }


    @Override
    public String toString() {
        return String.valueOf(this.getPosterImage());
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(this.createdAt);
        parcel.writeValue(this.id);

        parcel.writeString(this.synopsis);
        parcel.writeString(this.canonicalTitle);
        parcel.writeString(this.youtubeVideoId);

        parcel.writeValue(this.posterImage);
        parcel.writeValue(this.favoritesCount);
        parcel.writeValue(this.userCount);
    }


    protected Anime(Parcel in) {
        this.id = in.readString();
        this.createdAt = in.readString();
        this.synopsis = in.readString();
        this.canonicalTitle = in.readString();
        this.youtubeVideoId = in.readString();

        this.posterImage = in.readString();
        this.favoritesCount = (Integer) in.readValue(Integer.class.getClassLoader());
        this.userCount = (Integer) in.readValue(Integer.class.getClassLoader());

    }

    public static final Creator<Anime> CREATOR = new Creator<Anime>() {
        @Override
        public Anime createFromParcel(Parcel source) {
            return new Anime(source);
        }

        @Override
        public Anime[] newArray(int size) {
            return new Anime[size];
        }
    };

    public static final Comparator<Anime> BY_TITLE_ASCENDING = new Comparator<Anime>() {
        @Override
        public int compare(Anime anime, Anime t1) {
            return anime.getCanonicalTitle().compareTo(t1.getCanonicalTitle());
        }
    };

    public static final Comparator<Anime> BY_TITLE_DESCENDING = new Comparator<Anime>() {
        @Override
        public int compare(Anime anime, Anime t1) {
            return t1.getCanonicalTitle().compareTo(anime.getCanonicalTitle());
        }
    };


}
