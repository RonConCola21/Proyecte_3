package com.example.probajukeapp.model;

import java.util.ArrayList;
import java.util.List;

public class Song {
    private int id;
    private String spotifyId;
    private String name;
    private String artist;
    private String album;
    private int duration;
    private String cover;
    private boolean isPlaying;
    private char status;

    public Song(int id, String spotifyId, String name, String artist, String album, int duration, String cover, boolean isPlaying, char status) {
        this.id = id;
        this.spotifyId = spotifyId;
        this.name = name;
        this.artist = artist;
        this.album = album;
        this.duration = duration;
        this.cover = cover;
        this.isPlaying = isPlaying;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSpotifyId() {
        return spotifyId;
    }

    public void setSpotifyId(String  spotifyId) {
        this.spotifyId = spotifyId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public int getDuration() {return duration;}

    public void setDuration(int duration) {this.duration = duration;}

    public String getCover() {return cover;}

    public void setCover(String cover) {this.cover = cover;}

    public boolean isPlaying() {
        return isPlaying;
    }

    public void setPlaying(boolean playing) {
        isPlaying = playing;
    }

    public char getStatus() {
        return status;
    }

    public void setStatus(char status) {
        this.status = status;
    }

    public static List<Song> getSongs(){
        List<Song> songs = new ArrayList<>();

        songs.add(new Song(1, "spotifyId", "Torero", "Chayanne", "album", 100, "https://i.scdn.co/image/ab67616d0000b273002377d5a43602fcb14058ea", false, statusTypes.WHITELIST.getStatus()));
        songs.add(new Song(2, "spotifyId", "Minero", "ElRubiusOMG", "album", 100, "https://i.scdn.co/image/ab67616d0000b2733635261f6c083b54a3cfec6b", false, statusTypes.WHITELIST.getStatus()));

        return songs;
    }
}
