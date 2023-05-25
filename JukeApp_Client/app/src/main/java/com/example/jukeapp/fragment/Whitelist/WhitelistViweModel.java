package com.example.jukeapp.fragment.Whitelist;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.jukeapp.api.Song;
import com.example.jukeapp.api.WSCreate;

import java.util.List;

public class WhitelistViweModel extends ViewModel {
    public MutableLiveData<List<Song>> mSongs = new MutableLiveData<>();

    public void getSongs() {
        Song.getSong(mSongs,"w");
    }

    MutableLiveData<WSCreate> mSong2Queue = new MutableLiveData<>();
    public void addSongQueue(String song_id, int user_id){
        WSCreate.addSongQueue(song_id, user_id, mSong2Queue);
    }
}
