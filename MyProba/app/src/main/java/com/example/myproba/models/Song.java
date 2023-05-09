package com.example.myproba.models;

public class Song {
    private int son_id;
    private String son_img;
    private String son_spotify_id;
    private String son_artist1;
    private String son_artist2;
    private String son_name;
    private int son_duration;
    private int son_status;

    public Song(int son_id, String son_img, String son_spotify_id, String son_artist1, String son_artist2, String son_name, int son_duration, int son_status) {
        this.son_id = son_id;
        this.son_img = son_img;
        this.son_spotify_id = son_spotify_id;
        this.son_artist1 = son_artist1;
        this.son_artist2 = son_artist2;
        this.son_name = son_name;
        this.son_duration = son_duration;
        this.son_status = son_status;
    }

    public int getSon_id() {
        return son_id;
    }

    public void setSon_id(int son_id) {
        this.son_id = son_id;
    }

    public String getSon_img() {
        return son_img;
    }

    public void setSon_img(String son_img) {
        this.son_img = son_img;
    }

    public String getSon_spotify_id() {
        return son_spotify_id;
    }

    public void setSon_spotify_id(String son_spotify_id) {
        this.son_spotify_id = son_spotify_id;
    }

    public String getSon_artist1() {
        return son_artist1;
    }

    public void setSon_artist1(String son_artist1) {
        this.son_artist1 = son_artist1;
    }

    public String getSon_artist2() {
        return son_artist2;
    }

    public void setSon_artist2(String son_artist2) {
        this.son_artist2 = son_artist2;
    }

    public String getSon_name() {
        return son_name;
    }

    public void setSon_name(String son_name) {
        this.son_name = son_name;
    }

    public int getSon_duration() {
        return son_duration;
    }

    public void setSon_duration(int son_duration) {
        this.son_duration = son_duration;
    }

    public int getSon_status() {
        return son_status;
    }

    public void setSon_status(int son_status) {
        this.son_status = son_status;
    }
}
