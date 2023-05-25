package com.example.jukeapp.api;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface SongApi {

    @GET("song/{status}")
    Call<WSGetSongs> getSongs(@Path("status") String status);

    @GET("searchSong/{song_name}")
    Call<WSGetSongs> searchSong(@Path("song_name") String song_name);

    @POST("updateSong")
    Call<WSUpdateSong> updateSong(@Query("son_id") int son_id, @Query("son_status") String son_status);

    @POST("user")
    Call<GetUserSuccess> getUser(@Query("user_nick") String user_nick, @Query("user_password") String user_password);

    @POST("createUser")
    Call<GetUserSuccess> createUser(@Query("user_nick")String user_nick, @Query("user_email")String user_email, @Query("user_password")String user_password);

    @POST("createSong")
    Call<WSCreate> createSong(@Query("son_spotify_id")String son_spotify_id, @Query("son_status")String son_status,
                              @Query("son_name")String son_name, @Query("son_artist1")String son_artist1,
                              @Query("son_artist2")String son_artist2, @Query("son_duration")Double son_duration,
                              @Query("son_img")String son_img, @Query("son_album")String son_album,@Query("user_id")int user_id);

    @GET("getQueue")
    Call<WSGetSongs> getQueue();

    @GET("getHistory")
    Call<WSGetSongs> getHistory();

    @POST("adddSongQueue")
    Call<WSCreate> addSongQueue(@Query("song_id")String song_id, @Query("user_id")int user_id);
}
