
package com.example.probajukeapp.api;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

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

    public static void getPost(MutableLiveData<List<Song>> mSongs){
        final OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(120, TimeUnit.SECONDS)
                .connectTimeout(120, TimeUnit.SECONDS)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.2.181.69/jukeapp/public/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
        SongApi songApi = retrofit.create(SongApi.class);
        Call<WSGetSongs> call = songApi.getSongs();
        List<Song> songs = new ArrayList<>();
        call.enqueue(new Callback<WSGetSongs>() {
            @Override
            public void onResponse(Call<WSGetSongs> call, Response<WSGetSongs> response) {
                if(response.isSuccessful()){
                    WSGetSongs respuesta = response.body();
                    for (Song song : respuesta.getData()){
                        Log.i("Song_id", Integer.toString(song.getSonId()));
                        Log.i("Song_img", song.getSonImg());
                        Log.i("Song_spotify_id", song.getSonSpotifyId());
                        Log.i("Song_artist1", song.getSonArtist1());
                        if (song.getSonArtist2() != null){
                            Log.i("Song_artist2", song.getSonArtist2());
                        }
                        Log.i("Song_name", song.getSonName());
                        Log.i("Song_duration", Integer.toString(song.getSonDuration()));
                        Log.i("Song_status", song.getSonStatus());

                        songs.add(song);

                    }
                    mSongs.postValue(songs);

                }
                else {
                    Log.i("Error", "Error");
                }
            }

            @Override
            public void onFailure(Call<WSGetSongs> call, Throwable t) {
                Log.e("Error Failure", t.getMessage(),t);
            }
        });
    }

}
