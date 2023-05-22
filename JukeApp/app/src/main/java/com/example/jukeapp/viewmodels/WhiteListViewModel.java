package com.example.jukeapp.viewmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.jukeapp.fragments.api.Song;

import java.util.List;

public class WhiteListViewModel extends ViewModel {
    public MutableLiveData<List<Song>> mSongs = new MutableLiveData<>();


    public void getMSongs() {
        Song.getSong(mSongs, "w");
    }


}
