package com.example.myproba.models;

public class posts {
    String song_id;
    String song_img;
    String Artist;

    public posts(String song_id, String song_img, String Artist) {
        this.song_id = song_id;
        this.song_img = song_img;
        this.Artist = Artist;
    }

    public String getSong_id() {
        return song_id;
    }

    public void setSong_id(String song_id) {
        this.song_id = song_id;
    }

    public String getSong_img() {
        return song_img;
    }

    public void setSong_img(String song_img) {
        this.song_img = song_img;
    }

    public String getArtist() {
        return Artist;
    }

    public void setArtist(String artist) {
        this.Artist = artist;
    }
}
