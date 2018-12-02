package com.example.hmod_.animeapp.DataBase;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import android.os.Parcel;
import android.os.Parcelable;

@Entity(tableName = "favoritesAnime")

public class FavoritesaAnimeEntity implements Parcelable {
    private String id;
    private String synopsis;        //done
    private String posterImage;     //done
    private String canonicalTitle;  //done
    @PrimaryKey
    private Integer userCount;      //done
    private Integer favoritesCount; //done
    private String createdAt;
    private String youtubeVideoId;   //done



    public FavoritesaAnimeEntity(String id, String posterImage, String canonicalTitle) {
        this.id = id;

        this.posterImage = posterImage;
        this.canonicalTitle = canonicalTitle;

    }

    public String getId() {
        return id;
    }

    public void setId(String idForMovie) {
        this.id = idForMovie;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public String getPosterImage() {
        return posterImage;
    }

    public void setPosterImage(String posterImage) {
        this.posterImage = posterImage;
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

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getYoutubeVideoId() {
        return youtubeVideoId;
    }

    public void setYoutubeVideoId(String youtubeVideoId) {
        this.youtubeVideoId = youtubeVideoId;
    }

    public static Creator<FavoritesaAnimeEntity> getCREATOR() {
        return CREATOR;
    }

    @Override
    public String toString() {
        return "FavoritesaAnimeEntity{" +
                "idForMovie=" + id +
                ", synopsis='" + synopsis + '\'' +
                ", posterImage=" + posterImage +
                ", canonicalTitle='" + canonicalTitle + '\'' +
                ", userCount=" + userCount +
                ", favoritesCount=" + favoritesCount +
                ", createdAt='" + createdAt + '\'' +
                ", youtubeVideoId='" + youtubeVideoId + '\'' +
                '}';
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(String.valueOf(this.id));
        parcel.writeString(this.canonicalTitle);
        parcel.writeString(String.valueOf(this.posterImage));
        parcel.writeString(this.synopsis);
        parcel.writeString(this.createdAt);
        parcel.writeString(this.youtubeVideoId);
        parcel.writeString(String.valueOf(this.userCount));
        parcel.writeString(String.valueOf(this.favoritesCount));

    }

    protected FavoritesaAnimeEntity(Parcel in) {
        this.id = in.readString();
        this.canonicalTitle = in.readString();
        this.posterImage = in.readString();
        this.synopsis = in.readString();
        this.createdAt = in.readString();
        this.youtubeVideoId = in.readString();
        this.userCount = in.readInt();
        this.favoritesCount = in.readInt();

    }

    public static final Creator<FavoritesaAnimeEntity> CREATOR = new Creator<FavoritesaAnimeEntity>() {
        @Override
        public FavoritesaAnimeEntity createFromParcel(Parcel source) {
            return new FavoritesaAnimeEntity(source);
        }

        @Override
        public FavoritesaAnimeEntity[] newArray(int size) {
            return new FavoritesaAnimeEntity[size];
        }
    };
}
