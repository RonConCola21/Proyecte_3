package com.example.myproba.interfaz;

import com.example.myproba.models.Song;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface SongApi {

    @GET("song")
    Call<List<Song>> getSongs();
}
