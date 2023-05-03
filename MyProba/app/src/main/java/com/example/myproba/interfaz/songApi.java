package com.example.myproba.interfaz;

import com.example.myproba.models.posts;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface songApi {

    @GET("test")
    Call<posts> getPost();
}
