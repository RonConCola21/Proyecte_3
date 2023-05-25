package com.example.jukeapp.fragment.PlaybackQueue;

import androidx.lifecycle.MutableLiveData;

import com.example.jukeapp.api.Song;

import java.util.List;

public class PlayBackQueueViewModel extends androidx.lifecycle.ViewModel {
    MutableLiveData<List<Song>> mQueue = new MutableLiveData<>();

    public void getQueue(){
        Song.getQueue(mQueue);
    }

    MutableLiveData<List<Song>> mHistory = new MutableLiveData<>();
    public void getHistory(){
        Song.getHistory(mHistory);
    }
}
