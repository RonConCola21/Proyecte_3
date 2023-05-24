package com.example.jukeapp.api;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WSCreateUser {

    @SerializedName("success")
    @Expose
    private String success;

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public static void createUser(String user_nick, String user_email, String user_password, MutableLiveData<WSCreateUser> mUser){
        ApiManager.getInstance().createUser(user_nick, user_email, user_password, new Callback<WSCreateUser>() {
            @Override
            public void onResponse(Call<WSCreateUser> call, Response<WSCreateUser> response) {
                if (response.isSuccessful()) {
                    Log.d("Success", "Usuario creado");
                    WSCreateUser wsCreateUser = response.body();
                    mUser.postValue(wsCreateUser);
                }
            }

            @Override
            public void onFailure(Call<WSCreateUser> call, Throwable t) {
                Log.d("Error", t.getMessage());
            }
        });
    }

}