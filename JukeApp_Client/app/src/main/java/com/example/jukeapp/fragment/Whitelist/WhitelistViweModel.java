package com.example.jukeapp.fragment.Whitelist;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.jukeapp.api.Song;

import java.util.List;

public class WhitelistViweModel extends ViewModel {
    public MutableLiveData<List<Song>> mSongs = new MutableLiveData<>();

    public void getSongs() {
        Song.getSong(mSongs,"w");
    }
}
