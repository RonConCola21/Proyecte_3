
package com.example.jukeapp.api;

import android.util.Log;
import android.view.View;

import androidx.lifecycle.MutableLiveData;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Song {

    @SerializedName("id")
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
    private Double sonDuration;
    @SerializedName("son_status")
    @Expose
    private String sonStatus;
    @SerializedName("son_album")
    @Expose
    private String sonAlbum;


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

    public Double getSonDuration() {
        return sonDuration;
    }

    public void setSonDuration(Double sonDuration) {
        this.sonDuration = sonDuration;
    }

    public String getSonStatus() {
        return sonStatus;
    }

    public void setSonStatus(String sonStatus) {
        this.sonStatus = sonStatus;
    }

    public String getSonAlbum() {
        return sonAlbum;
    }

    public void setSonAlbum(String sonAlbum) {
        this.sonAlbum = sonAlbum;
    }

    public static void getSong(MutableLiveData<List<Song>> mSongs, String type){
        ApiManager.getInstance().getSong(type, new Callback<WSGetSongs>() {
            @Override
            public void onResponse(Call<WSGetSongs> call, Response<WSGetSongs> response) {
                if (response.isSuccessful()) {
                    WSGetSongs wsGetSongs = response.body();
                    List<Song> songs = wsGetSongs.getData();
                    mSongs.postValue(songs);
                }
            }

            @Override
            public void onFailure(Call<WSGetSongs> call, Throwable t) {
                Log.d("Error", t.getMessage());
            }
        });
    }

    public static void updateSong(int son_id, String son_status) {
        ApiManager.getInstance().updateSong(son_id, son_status, new Callback<WSUpdateSong>() {
            @Override
            public void onResponse(Call<WSUpdateSong> call, Response<WSUpdateSong> response) {
                if (response.isSuccessful()){
                    WSUpdateSong result = response.body();
                }
            }

            @Override
            public void onFailure(Call<WSUpdateSong> call, Throwable t) {
                Log.d("Error", t.getMessage());
            }
        });
    }

    public static void searchSong(MutableLiveData<List<Song>> mSongs, String songName){
        ApiManager.getInstance().searchSong(songName, new Callback<WSGetSongs>() {
            @Override
            public void onResponse(Call<WSGetSongs> call, Response<WSGetSongs> response) {
                if (response.isSuccessful()) {
                    WSGetSongs wsGetSongs = response.body();
                    List<Song> songs = wsGetSongs.getData();
                    Log.i("Response searchSong", "onResponse: " + songs);
                    mSongs.postValue(songs);
                }
            }

            @Override
            public void onFailure(Call<WSGetSongs> call, Throwable t) {
                Log.d("Error searchSong", t.getMessage());
            }
        });
    }

    public static void getQueue(MutableLiveData<List<Song>> mSongs){
        ApiManager.getInstance().getQueue(new Callback<WSGetSongs>() {
            @Override
            public void onResponse(Call<WSGetSongs> call, Response<WSGetSongs> response) {
                if (response.isSuccessful()) {
                    WSGetSongs wsGetSongs = response.body();
                    List<Song> songs = wsGetSongs.getData();
                    mSongs.postValue(songs);
                }
            }

            @Override
            public void onFailure(Call<WSGetSongs> call, Throwable t) {
                Log.d("Error", t.getMessage());
            }
        });
    }

    public static void getHistory(MutableLiveData<List<Song>> mSongs){
        ApiManager.getInstance().getHistory(new Callback<WSGetSongs>() {
            @Override
            public void onResponse(Call<WSGetSongs> call, Response<WSGetSongs> response) {
                if (response.isSuccessful()) {
                    WSGetSongs wsGetSongs = response.body();
                    List<Song> songs = wsGetSongs.getData();
                    mSongs.postValue(songs);
                }
            }

            @Override
            public void onFailure(Call<WSGetSongs> call, Throwable t) {
                Log.d("Error", t.getMessage());
            }
        });
    }

}
