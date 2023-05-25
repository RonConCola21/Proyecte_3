package com.example.jukeapp.fragment.NewSong;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.jukeapp.api.Song;
import com.example.jukeapp.api.WSCreate;

import java.util.List;

public class NewSongViewModel extends ViewModel {
    public MutableLiveData<List<Song>> mSongs = new MutableLiveData<>();

    public void searchSongs(String text) {
        Song.searchSong(mSongs, text);
    }

    public MutableLiveData<WSCreate> mCreatedSong = new MutableLiveData<>();

    public void createSong(String son_spotify_id, String son_status, String son_name, String son_artist1, String son_artist2, Double son_duration, String son_img, String son_album, int user_id) {
        WSCreate.createSong(son_spotify_id, son_status, son_name, son_artist1, son_artist2, son_duration, son_img, son_album, user_id, mCreatedSong);
    }
}
