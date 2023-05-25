package com.example.jukeapp.api;

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

    public void searchSong(String song_name, Callback <WSGetSongs> callback) {
        Call<WSGetSongs> call = mSongApi.searchSong(song_name);
        call.enqueue(callback);
    }

    public void getUser(String user_nick, String user_password, Callback <GetUserSuccess> callback) {
        Call<GetUserSuccess> call = mSongApi.getUser(user_nick, user_password);
        call.enqueue(callback);
    }

    public void createUser(String user_nick, String user_email, String user_password, Callback <WSCreate> callback) {
        Call<WSCreate> call = mSongApi.createUser(user_nick, user_email, user_password);
        call.enqueue(callback);
    }

    public void createSong(String son_spotify_id, String son_status, String son_name, String son_artist1, String son_artist2, Double son_duration, String son_img, String son_album, int user_id, Callback <WSCreate> callback) {
        Call<WSCreate> call = mSongApi.createSong(son_spotify_id, son_status, son_name, son_artist1, son_artist2, son_duration, son_img, son_album, user_id);
        call.enqueue(callback);
    }

    public void getQueue(Callback <WSGetSongs> callback) {
        Call<WSGetSongs> call = mSongApi.getQueue();
        call.enqueue(callback);
    }

    public void getHistory(Callback <WSGetSongs> callback) {
        Call<WSGetSongs> call = mSongApi.getHistory();
        call.enqueue(callback);
    }
}
