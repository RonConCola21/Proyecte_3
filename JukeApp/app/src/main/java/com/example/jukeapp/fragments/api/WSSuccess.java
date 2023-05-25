package com.example.jukeapp.fragments.api;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WSSuccess {

    @SerializedName("success")
    @Expose
    private String success;

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public static void skipNext(MutableLiveData<WSSuccess> mSuccess){

        ApiManager.getInstance().skipNext(new Callback<WSSuccess>( ) {
            @Override
            public void onResponse(Call<WSSuccess> call, Response<WSSuccess> response) {
                if(response.isSuccessful()){
                    WSSuccess wsSuccess = response.body();
                    mSuccess.postValue(wsSuccess);
                }
            }

            @Override
            public void onFailure(Call<WSSuccess> call, Throwable t) {
                Log.d("Error skipNext", t.getMessage());
            }
        });

    }

    public static void skipPrevious(MutableLiveData<WSSuccess> mSuccess){

        ApiManager.getInstance().skipPrevious(new Callback<WSSuccess>( ) {
            @Override
            public void onResponse(Call<WSSuccess> call, Response<WSSuccess> response) {
                if(response.isSuccessful()){
                    WSSuccess wsSuccess = response.body();
                    mSuccess.postValue(wsSuccess);
                }
            }

            @Override
            public void onFailure(Call<WSSuccess> call, Throwable t) {
                Log.d("Error skipNext", t.getMessage());
            }
        });

    }

}