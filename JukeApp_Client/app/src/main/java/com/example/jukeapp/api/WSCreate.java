package com.example.jukeapp.api;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WSCreate {

    @SerializedName("success")
    @Expose
    private String success;

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public static void createUser(String user_nick, String user_email, String user_password, MutableLiveData<WSCreate> mUser){
        ApiManager.getInstance().createUser(user_nick, user_email, user_password, new Callback<WSCreate>() {
            @Override
            public void onResponse(Call<WSCreate> call, Response<WSCreate> response) {
                if (response.isSuccessful()) {
                    Log.d("Success", "Usuario creado");
                    WSCreate wsCreate = response.body();
                    mUser.postValue(wsCreate);
                }
            }

            @Override
            public void onFailure(Call<WSCreate> call, Throwable t) {
                Log.d("Error", t.getMessage());
            }
        });
    }

    public static void createSong(String son_spotify_id, String son_status, String son_name, String son_artist1, String son_artist2, Double son_duration, String son_img, String son_album, int user_id, MutableLiveData<WSCreate> mSong){
        ApiManager.getInstance().createSong(son_spotify_id, son_status, son_name, son_artist1, son_artist2, son_duration, son_img, son_album, user_id, new Callback<WSCreate>() {
            @Override
            public void onResponse(Call<WSCreate> call, Response<WSCreate> response) {
                if (response.isSuccessful()) {
                    Log.d("Success", "Canción creada");
                    WSCreate wsCreate = response.body();
                    mSong.postValue(wsCreate);
                }
            }

            @Override
            public void onFailure(Call<WSCreate> call, Throwable t) {
                Log.d("Error", t.getMessage());
            }
        });
    }


}