package com.example.jukeapp.api;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WSGetUser {

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

}