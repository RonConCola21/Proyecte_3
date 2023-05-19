package com.example.jukeapp.fragment.PlaybackQueue;

import android.content.pm.ActivityInfo;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import com.example.jukeapp.adapter.SongAdapter;
import com.example.jukeapp.databinding.FragmentPlaybackQueueBinding;
import com.example.jukeapp.api.Song;


public class PlaybackQueueFragment extends Fragment implements CompoundButton.OnCheckedChangeListener, SongAdapter.SongSelectedListener {
    private FragmentPlaybackQueueBinding binding;
    private SongAdapter adapter;
    public PlaybackQueueFragment() {
        // Required empty public constructor
    }

    public static PlaybackQueueFragment newInstance(String param1, String param2) {
        PlaybackQueueFragment fragment = new PlaybackQueueFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        getActivity().setRequestedOrientation(
                ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        binding = FragmentPlaybackQueueBinding.inflate(inflater, container, false);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.switchPQ.setOnCheckedChangeListener(this);
        binding.recycler.setLayoutManager(new LinearLayoutManager(getContext()));
//        adapter = new SongAdapter(Song.getSongs2(),this);
//        binding.recycler.setAdapter(adapter);
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        if (b) {
            binding.switchPQ.setText("Historial");
//            adapter = new SongAdapter(Song.getSongs3(),this);
        } else {
            binding.switchPQ.setText("En cua");
//            adapter = new SongAdapter(Song.getSongs2(),this);
        }
        binding.recycler.setAdapter(adapter);
    }

    @Override
    public void onSongSelected(Song seleccionat) {

    }
}