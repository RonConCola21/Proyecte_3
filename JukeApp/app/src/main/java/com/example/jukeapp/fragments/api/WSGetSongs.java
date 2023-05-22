
package com.example.jukeapp.fragments.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class WSGetSongs {

    @SerializedName("data")
    @Expose
    private List<Song> data;

    public List<Song> getData() {
        return data;
    }

    public void setData(List<Song> data) {
        this.data = data;
    }

}
