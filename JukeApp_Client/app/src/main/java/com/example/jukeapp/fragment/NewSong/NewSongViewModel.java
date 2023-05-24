package com.example.jukeapp.fragment.NewSong;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.jukeapp.api.Song;

import java.util.List;

public class NewSongViewModel extends ViewModel {
    public MutableLiveData<List<Song>> mSongs = new MutableLiveData<>();

    public void searchSongs(String text) {
        Song.searchSong(mSongs, text);
    }
}
