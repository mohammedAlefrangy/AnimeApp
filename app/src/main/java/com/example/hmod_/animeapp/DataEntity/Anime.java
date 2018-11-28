package com.example.hmod_.animeapp.DataEntity;

import android.os.Parcel;
import android.os.Parcelable;

public class Anime implements Parcelable {

        private String createdAt;
        private String updatedAt;
        private String synopsis;

    public Object getPosterImage() {
        return posterImage;
    }

    public void setPosterImage(Object posterImage) {
        this.posterImage = posterImage;
    }

    private Object posterImage;
        private String canonicalTitle; //done
        private String averageRating;
        private Integer userCount;
        private Integer favoritesCount;
        private String startDate;
        private String endDate;
        private Object nextRelease;
        private String ageRatingGuide;
        private String status;
        private Integer episodeCount;
        private Integer episodeLength;
        private Integer totalLength;
        private String youtubeVideoId;


        public Anime(String createdAt, String updatedAt, String synopsis, Integer posterImage, String canonicalTitle, String averageRating, Integer userCount, Integer favoritesCount, String startDate, String endDate, Object nextRelease, String ageRatingGuide, String status, Integer episodeCount, Integer episodeLength, Integer totalLength, String youtubeVideoId) {
            super();
            this.createdAt = createdAt;
            this.updatedAt = updatedAt;
            this.synopsis = synopsis;
            this.posterImage = posterImage;
            this.canonicalTitle = canonicalTitle;
            this.averageRating = averageRating;
            this.userCount = userCount;
            this.favoritesCount = favoritesCount;
            this.startDate = startDate;
            this.endDate = endDate;
            this.nextRelease = nextRelease;
            this.ageRatingGuide = ageRatingGuide;
            this.status = status;
            this.episodeCount = episodeCount;
            this.episodeLength = episodeLength;
            this.totalLength = totalLength;
            this.youtubeVideoId = youtubeVideoId;
        }

    public Anime(){

    }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getUpdatedAt() {
            return updatedAt;
        }

        public void setUpdatedAt(String updatedAt) {
            this.updatedAt = updatedAt;
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

        public String getAverageRating() {
            return averageRating;
        }

        public void setAverageRating(String averageRating) {
            this.averageRating = averageRating;
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

        public String getStartDate() {
            return startDate;
        }

        public void setStartDate(String startDate) {
            this.startDate = startDate;
        }

        public String getEndDate() {
            return endDate;
        }

        public void setEndDate(String endDate) {
            this.endDate = endDate;
        }

        public Object getNextRelease() {
            return nextRelease;
        }

        public void setNextRelease(Object nextRelease) {
            this.nextRelease = nextRelease;
        }

        public String getAgeRatingGuide() {
            return ageRatingGuide;
        }

        public void setAgeRatingGuide(String ageRatingGuide) {
            this.ageRatingGuide = ageRatingGuide;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public Integer getEpisodeCount() {
            return episodeCount;
        }

        public void setEpisodeCount(Integer episodeCount) {
            this.episodeCount = episodeCount;
        }

        public Integer getEpisodeLength() {
            return episodeLength;
        }

        public void setEpisodeLength(Integer episodeLength) {
            this.episodeLength = episodeLength;
        }

        public Integer getTotalLength() {
            return totalLength;
        }

        public void setTotalLength(Integer totalLength) {
            this.totalLength = totalLength;
        }

        public String getYoutubeVideoId() {
            return youtubeVideoId;
        }

        public void setYoutubeVideoId(String youtubeVideoId) {
            this.youtubeVideoId = youtubeVideoId;
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
        parcel.writeString(this.updatedAt);
        parcel.writeString(this.synopsis);
        parcel.writeString(this.canonicalTitle);
        parcel.writeString(this.averageRating);
        parcel.writeString(this.startDate);
        parcel.writeString(this.endDate);
        parcel.writeString(this.ageRatingGuide);
        parcel.writeString(this.status);
        parcel.writeString(this.youtubeVideoId);


//        parcel.writeValue(this.posterImage);/
        parcel.writeValue(this.favoritesCount);
        parcel.writeValue(this.userCount);
        parcel.writeValue(this.episodeCount);
        parcel.writeValue(this.episodeLength);
        parcel.writeValue(this.totalLength);
    }


    protected Anime(Parcel in) {
        this.createdAt = in.readString();
        this.updatedAt = in.readString();
        this.synopsis = in.readString();
        this.canonicalTitle = in.readString();
        this.averageRating = in.readString();
        this.startDate = in.readString();
        this.endDate = in.readString();
        this.ageRatingGuide = in.readString();
        this.status = in.readString();
        this.youtubeVideoId = in.readString();


//        this.posterImage = in.readValue(Integer.class.getClassLoader());
        this.favoritesCount =(Integer) in.readValue(Integer.class.getClassLoader());
        this.userCount =(Integer) in.readValue(Integer.class.getClassLoader());
        this.episodeCount =(Integer) in.readValue(Integer.class.getClassLoader());
        this.episodeLength =(Integer) in.readValue(Integer.class.getClassLoader());
        this.totalLength =(Integer) in.readValue(Integer.class.getClassLoader());

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


}
