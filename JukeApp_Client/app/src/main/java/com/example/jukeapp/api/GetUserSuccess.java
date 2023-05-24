package com.example.jukeapp.api;


import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetUserSuccess implements Parcelable {

    @SerializedName("user_id")
    @Expose
    private Integer userId;
    @SerializedName("success")
    @Expose
    private String success;
    @SerializedName("user_tokens")
    @Expose
    private int tokens;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public int getTokens() {
        return tokens;
    }

    public void setTokens(int tokens) {
        this.tokens = tokens;
    }

    public static void getUser(String user_nick, String user_password, MutableLiveData<GetUserSuccess> mUser){
        ApiManager.getInstance().getUser(user_nick, user_password, new Callback<GetUserSuccess>() {
            @Override
            public void onResponse(Call<GetUserSuccess> call, Response<GetUserSuccess> response) {
                if (response.isSuccessful()) {
                    Log.d("Success", "Usuario encontrado");
                    GetUserSuccess getUserSuccess = response.body();
                    mUser.postValue(getUserSuccess);
                }
                else {
                    Log.d("Error", "Usuario no encontrado");
                    mUser.postValue(null);
                }
            }

            @Override
            public void onFailure(Call<GetUserSuccess> call, Throwable t) {
                Log.d("Error", t.getMessage());
            }
        });
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {

    }
}