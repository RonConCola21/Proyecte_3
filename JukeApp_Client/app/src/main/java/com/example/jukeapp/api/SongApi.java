package com.example.jukeapp.api;

import com.google.gson.JsonElement;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface SongApi {

    @GET("song/{status}")
    Call<WSGetSongs> getSongs(@Path("status") String status);

    @POST("updateSong")
    Call<WSUpdateSong> updateSong(@Query("son_id") int son_id, @Query("son_status") String son_status);

    @POST("user")
    Call<GetUserSuccess> getUser(@Query("user_nick") String user_nick, @Query("user_password") String user_password);

    @POST("createUser")
    Call<WSCreateUser> createUser(@Query("user_nick") String user_nick, @Query("user_email") String user_email, @Query("user_password") String user_password);

    @GET("searchSong/{song_name}")
    Call<WSGetSongs> searchSong(@Path("song_name") String song_name);
}
