package com.example.jukeapp.fragments.api;

import android.util.Log;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiManager {
    private static final String BASE_URL = "http://";
    private static ApiManager mInstance;
    private SongApi mSongApi;

    public ApiManager() {
        final OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(120, TimeUnit.SECONDS)
                .connectTimeout(120, TimeUnit.SECONDS)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.2.181.69/jukeapp/public/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
         mSongApi = retrofit.create(SongApi.class);
    }

    public static ApiManager getInstance() {
        if (mInstance == null) {
            mInstance = new ApiManager();
        }
        return mInstance;
    }

    public void getSong(String status, Callback <WSGetSongs> callback) {
        Call<WSGetSongs> call = mSongApi.getSongs(status);
        call.enqueue(callback);
    }

    public void updateSong(int son_id, String son_status, Callback <WSUpdateSong> callback) {
        Call<WSUpdateSong> call = mSongApi.updateSong(son_id, son_status);
        Log.i("info", "updateSong: " + son_id + " " + son_status);
        call.enqueue(callback);
    }

    public void getQueue(Callback <WSGetSongs> callback) {
        Call<WSGetSongs> call = mSongApi.getQueue();
        call.enqueue(callback);
    }

    public void skipNext(Callback <WSSuccess> callback) {
        Call<WSSuccess> call = mSongApi.skipNext();
        call.enqueue(callback);
    }

    public void skipPrevious(Callback <WSSuccess> callback) {
        Call<WSSuccess> call = mSongApi.skipPrevious();
        call.enqueue(callback);
    }

    public void getCurrentlyPlayingSong(Callback <WSGetSongs> callback) {
        Call<WSGetSongs> call = mSongApi.getCurrentlyPlayingSong();
        call.enqueue(callback);
    }
}
