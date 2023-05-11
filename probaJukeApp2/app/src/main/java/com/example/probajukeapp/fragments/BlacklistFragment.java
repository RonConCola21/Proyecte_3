package com.example.probajukeapp.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.probajukeapp.R;
import com.example.probajukeapp.adapter.SongAdapter;
import com.example.probajukeapp.api.Song;
import com.example.probajukeapp.api.SongApi;
import com.example.probajukeapp.api.WSGetSongs;
import com.example.probajukeapp.model.statusTypes;
import com.example.probajukeapp.viewmodels.BlackListViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BlacklistFragment extends Fragment {

    private BlackListViewModel viewModel;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_blacklist, container, false);


        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel = new ViewModelProvider(this).get(BlackListViewModel.class);

        viewModel.mSongs.observe(getViewLifecycleOwner(), new Observer<List<Song>>() {
            @Override
            public void onChanged(List<Song> songs) {
                //AQUI TENDREMOS QUE HACER LO DE RECYCLER VIEW
                RecyclerView rcyPlaylist = view.findViewById(R.id.rcyBlacklist);
                SongAdapter songAdapter = new SongAdapter(songs, getContext());

                rcyPlaylist.setLayoutManager(new LinearLayoutManager(getContext()));
                rcyPlaylist.setAdapter(songAdapter);
                rcyPlaylist.setHasFixedSize(true);
            }
        });
        viewModel.getMSongs();
    }
}