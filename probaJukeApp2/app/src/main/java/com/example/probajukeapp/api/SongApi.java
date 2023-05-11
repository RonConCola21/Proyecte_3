package com.example.probajukeapp.api;

import retrofit2.Call;
import retrofit2.http.GET;

public interface SongApi {

    @GET("song")
    Call<WSGetSongs> getSongs();
}
