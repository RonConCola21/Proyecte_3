package com.example.jukeapp.models;

public class User {
    private int user_id;
    private String nickname;
    private String email;
    private String password;
    private int tokens;

    public User(int user_id, String nickname, String email, String password) {
        this.user_id = user_id;
        this.nickname = nickname;
        this.email = email;
        this.password = password;
        this.tokens = 0;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getTokens() {
        return tokens;
    }

    public void setTokens(int tokens) {
        this.tokens = tokens;
    }
}
