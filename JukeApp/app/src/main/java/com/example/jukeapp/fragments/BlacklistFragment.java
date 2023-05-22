package com.example.jukeapp.fragments;

import static java.lang.Thread.sleep;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jukeapp.R;
import com.example.jukeapp.adapter.SongAdapter;
import com.example.jukeapp.databinding.FragmentBlacklistBinding;
import com.example.jukeapp.databinding.FragmentWhitelistBinding;
import com.example.jukeapp.fragments.api.Song;
import com.example.jukeapp.viewmodels.BlackListViewModel;

import java.util.List;

public class BlacklistFragment extends Fragment {

    FragmentBlacklistBinding binding;

    private BlackListViewModel viewModel;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentBlacklistBinding.inflate(inflater, container, false);

        return binding.getRoot();
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

                binding.rcyBlacklist.setLayoutManager(new LinearLayoutManager(getContext()));
                binding.rcyBlacklist.setAdapter(songAdapter);
                binding.rcyBlacklist.setHasFixedSize(true);
                binding.progressBar.setVisibility(View.GONE);
                binding.btnGo2WhiteList.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (songAdapter.mIdSeleccionat != -1) {
                            Song.updateSong(songAdapter.mIdSeleccionat, "w");
                            try {
                                sleep(1000);
                                viewModel.getMSongs();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });
            }
        });
        viewModel.getMSongs();
    }
}