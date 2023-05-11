package com.example.probajukeapp.viewmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.probajukeapp.api.Song;

import java.util.List;
import java.util.Observable;

public class BlackListViewModel extends ViewModel {
    public MutableLiveData<List<Song>> mSongs = new MutableLiveData<>();


    public void getMSongs() {
        Song.getPost(mSongs);
    }
}
