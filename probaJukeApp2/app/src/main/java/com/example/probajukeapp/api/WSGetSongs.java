
package com.example.probajukeapp.api;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

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
