package com.example.jukeapp.viewmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.jukeapp.fragments.api.Song;
import com.example.jukeapp.fragments.api.WSSuccess;

import java.util.List;

public class PlaylistViewModel extends ViewModel {
    public MutableLiveData<List<Song>> mSongs = new MutableLiveData<>();
    public MutableLiveData<Song> mActualSong = new MutableLiveData<>();
    public void getQueue() {
        Song.getQueue(mSongs);
    }

    public void getActualSong() {
        Song.getCurrentlyPlayingSong(mActualSong);
    }

    public MutableLiveData<WSSuccess> mSuccess = new MutableLiveData<>();
    public void skipNext(){
        WSSuccess.skipNext(mSuccess);
    }

    public void skipPrevious(){
        WSSuccess.skipPrevious(mSuccess);
    }

}
