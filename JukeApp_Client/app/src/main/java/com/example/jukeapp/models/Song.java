package com.example.jukeapp.models;

import android.media.Image;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Song implements Parcelable, Serializable {
    private String image;
    private int song_id;//id_bd
    private String spotify_song_id;//string
    private String artist1;
    private String artist2;
    private String name;
    private char status;//whitelist, blacklist, pendent
    private int duration;//pot ser que sigui millor time
    private String album;
    public static ArrayList<Song> _songs;

    public static ArrayList<Song> _songs2;

    public static ArrayList<Song> _songs3;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setStatus(char status) {
        this.status = status;
    }

    public Song(String image, int song_id, String spotify_song_id, String artist1, String artist2, String name, char status, int duration, String album) {
        this.image = image;
        this.song_id = song_id;
        this.spotify_song_id = spotify_song_id;
        this.artist1 = artist1;
        this.artist2 = artist2;
        this.name = name;
        this.status = status;
        this.duration = duration;
        this.album = album;
    }

    protected Song(Parcel in) {
        song_id = in.readInt();
        spotify_song_id = in.readString();
        artist1 = in.readString();
        artist2 = in.readString();
        name = in.readString();
        int tmpStatus = in.readInt();
        status = tmpStatus != Integer.MAX_VALUE ? (char) tmpStatus : null;
        duration = in.readInt();
        album = in.readString();
    }

    public static final Creator<Song> CREATOR = new Creator<Song>() {
        @Override
        public Song createFromParcel(Parcel in) {
            return new Song(in);
        }

        @Override
        public Song[] newArray(int size) {
            return new Song[size];
        }
    };

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public int getSong_id() {
        return song_id;
    }

    public void setSong_id(int song_id) {
        this.song_id = song_id;
    }

    public String getSpotify_song_id() {
        return spotify_song_id;
    }

    public void setSpotify_song_id(String spotify_song_id) {
        this.spotify_song_id = spotify_song_id;
    }

    public String getArtist1() {
        return artist1;
    }

    public void setArtist1(String artist1) {
        this.artist1 = artist1;
    }

    public String getArtist2() {
        return artist2;
    }

    public void setArtist2(String artist2) {
        this.artist2 = artist2;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Character getStatus() {
        return status;
    }

    public void setStatus(Character status) {
        this.status = status;
    }

    public static List<Song> getSongs(){
        if (_songs==null){
            _songs = new ArrayList<>();
            _songs.add(new Song("https://rtvc.es/archivos/2023/01/Quevedo-1-1024x1024.jpg",1,"1","artist1","artist2","name1",'A',1,"album"));
            _songs.add(new Song("https://rtvc.es/archivos/2023/01/Quevedo-1-1024x1024.jpg",2,"2","artist1","artist2","name2",'A',1,"album"));
            _songs.add(new Song("https://rtvc.es/archivos/2023/01/Quevedo-1-1024x1024.jpg",3,"3","artist1","artist2","name3",'A',1,"album"));
            _songs.add(new Song("https://rtvc.es/archivos/2023/01/Quevedo-1-1024x1024.jpg",4,"4","artist1","artist2","name4",'A',1,"album"));
            _songs.add(new Song("https://rtvc.es/archivos/2023/01/Quevedo-1-1024x1024.jpg",5,"5","artist1","artist2","name5",'A',1,"album"));
            _songs.add(new Song("https://rtvc.es/archivos/2023/01/Quevedo-1-1024x1024.jpg",6,"6","artist1","artist2","name6",'A',1,"album"));
            _songs.add(new Song("https://rtvc.es/archivos/2023/01/Quevedo-1-1024x1024.jpg",7,"7","artist1","artist2","name7",'A',1,"album"));
            _songs.add(new Song("https://rtvc.es/archivos/2023/01/Quevedo-1-1024x1024.jpg",8,"8","artist1","artist2","name8",'A',1,"album"));
            _songs.add(new Song("https://rtvc.es/archivos/2023/01/Quevedo-1-1024x1024.jpg",9,"9","artist1","artist2","name9",'A',1,"album"));
        }
        return _songs;
    }

    public static List<Song> getSongs2(){
        if (_songs2==null){
            _songs2 = new ArrayList<>();
            _songs2.add(new Song("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRkzHtKG8T9ciWkMcyttoEJA00uyyZu2cO8ow&usqp=CAU",1,"1","artist21","artist22","name1",'A',1,"album"));
            _songs2.add(new Song("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRkzHtKG8T9ciWkMcyttoEJA00uyyZu2cO8ow&usqp=CAU",2,"2","artist21","artist22","name2",'A',1,"album"));
            _songs2.add(new Song("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRkzHtKG8T9ciWkMcyttoEJA00uyyZu2cO8ow&usqp=CAU",3,"3","artist21","artist22","name3",'A',1,"album"));
            _songs2.add(new Song("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRkzHtKG8T9ciWkMcyttoEJA00uyyZu2cO8ow&usqp=CAU",4,"4","artist21","artist22","name4",'A',1,"album"));
            _songs2.add(new Song("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRkzHtKG8T9ciWkMcyttoEJA00uyyZu2cO8ow&usqp=CAU",5,"5","artist21","artist22","name5",'A',1,"album"));
            _songs2.add(new Song("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRkzHtKG8T9ciWkMcyttoEJA00uyyZu2cO8ow&usqp=CAU",6,"6","artist21","artist22","name6",'A',1,"album"));
            _songs2.add(new Song("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRkzHtKG8T9ciWkMcyttoEJA00uyyZu2cO8ow&usqp=CAU",7,"7","artist21","artist22","name7",'A',1,"album"));
            _songs2.add(new Song("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRkzHtKG8T9ciWkMcyttoEJA00uyyZu2cO8ow&usqp=CAU",8,"8","artist21","artist22","name8",'A',1,"album"));
            _songs2.add(new Song("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRkzHtKG8T9ciWkMcyttoEJA00uyyZu2cO8ow&usqp=CAU",9,"9","artist21","artist22","name9",'A',1,"album"));
        }
        return _songs2;
    }

    public static List<Song> getSongs3(){
        if (_songs3==null){
            _songs3 = new ArrayList<>();
            _songs3.add(new Song("31",1,"1","artist31","artist32","name1",'A',1,"album"));
            _songs3.add(new Song("32",2,"2","artist31","artist32","name2",'A',1,"album"));
            _songs3.add(new Song("33",3,"3","artist31","artist32","name3",'A',1,"album"));
            _songs3.add(new Song("34",4,"4","artist31","artist32","name4",'A',1,"album"));
            _songs3.add(new Song("35",5,"5","artist31","artist32","name5",'A',1,"album"));
            _songs3.add(new Song("36",6,"6","artist31","artist32","name6",'A',1,"album"));
            _songs3.add(new Song("37",7,"7","artist31","artist32","name7",'A',1,"album"));
            _songs3.add(new Song("38",8,"8","artist31","artist32","name8",'A',1,"album"));
            _songs3.add(new Song("39",9,"9","artist31","artist32","name9",'A',1,"album"));
        }
        return _songs3;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeInt(song_id);
        parcel.writeString(spotify_song_id);
        parcel.writeString(artist1);
        parcel.writeString(artist2);
        parcel.writeString(name);
        parcel.writeString(status+"");
        parcel.writeInt(duration);
        parcel.writeString(album);
    }
}
