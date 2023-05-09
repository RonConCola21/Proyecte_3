
package com.example.myproba.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Song {

    @SerializedName("son_id")
    @Expose
    private Integer sonId;
    @SerializedName("son_img")
    @Expose
    private String sonImg;
    @SerializedName("son_spotify_id")
    @Expose
    private String sonSpotifyId;
    @SerializedName("son_artist1")
    @Expose
    private String sonArtist1;
    @SerializedName("son_artist2")
    @Expose
    private String sonArtist2;
    @SerializedName("son_name")
    @Expose
    private String sonName;
    @SerializedName("son_duration")
    @Expose
    private Integer sonDuration;
    @SerializedName("son_status")
    @Expose
    private String sonStatus;

    public Integer getSonId() {
        return sonId;
    }

    public void setSonId(Integer sonId) {
        this.sonId = sonId;
    }

    public String getSonImg() {
        return sonImg;
    }

    public void setSonImg(String sonImg) {
        this.sonImg = sonImg;
    }

    public String getSonSpotifyId() {
        return sonSpotifyId;
    }

    public void setSonSpotifyId(String sonSpotifyId) {
        this.sonSpotifyId = sonSpotifyId;
    }

    public String getSonArtist1() {
        return sonArtist1;
    }

    public void setSonArtist1(String sonArtist1) {
        this.sonArtist1 = sonArtist1;
    }

    public String getSonArtist2() {
        return sonArtist2;
    }

    public void setSonArtist2(String sonArtist2) {
        this.sonArtist2 = sonArtist2;
    }

    public String getSonName() {
        return sonName;
    }

    public void setSonName(String sonName) {
        this.sonName = sonName;
    }

    public Integer getSonDuration() {
        return sonDuration;
    }

    public void setSonDuration(Integer sonDuration) {
        this.sonDuration = sonDuration;
    }

    public String getSonStatus() {
        return sonStatus;
    }

    public void setSonStatus(String sonStatus) {
        this.sonStatus = sonStatus;
    }

}
