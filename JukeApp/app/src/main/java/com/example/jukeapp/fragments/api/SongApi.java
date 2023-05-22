package com.example.jukeapp.fragments.api;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface SongApi {

    @GET("song/{status}")
    Call<WSGetSongs> getSongs(@Path("status") String status);


    @POST("updateSong")
    Call<WSUpdateSong> updateSong(@Query("son_id") int son_id, @Query("son_status") String son_status);
}
