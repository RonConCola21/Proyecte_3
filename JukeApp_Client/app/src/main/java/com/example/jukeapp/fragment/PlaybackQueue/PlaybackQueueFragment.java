package com.example.jukeapp.fragment.PlaybackQueue;

import android.content.pm.ActivityInfo;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import com.example.jukeapp.adapter.SongAdapter;
import com.example.jukeapp.databinding.FragmentPlaybackQueueBinding;
import com.example.jukeapp.api.Song;

import java.util.List;


public class PlaybackQueueFragment extends Fragment implements CompoundButton.OnCheckedChangeListener, SongAdapter.SongSelectedListener {
    private FragmentPlaybackQueueBinding binding;
    private SongAdapter adapter;

    private PlayBackQueueViewModel viewModel;
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
        viewModel = new ViewModelProvider(this).get(PlayBackQueueViewModel.class);
        viewModel.mQueue.observe(getViewLifecycleOwner(), getQueue());
        viewModel.getQueue();
//        adapter = new SongAdapter(Song.getSongs2(),this);
//        binding.recycler.setAdapter(adapter);
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        if (b) {
            binding.progressBar.setVisibility(View.VISIBLE);
            binding.switchPQ.setText("Historial");
            adapter.clearData();
            viewModel.mHistory.observe(getViewLifecycleOwner( ), getHistory());
            viewModel.getHistory();
        } else {
            binding.progressBar.setVisibility(View.VISIBLE);
            binding.switchPQ.setText("En cua");
            adapter.clearData();
            viewModel.mQueue.observe(getViewLifecycleOwner(), getQueue());
            viewModel.getQueue();
        }
        binding.recycler.setAdapter(adapter);
    }

    public Observer<List<Song>> getQueue(){
        return new Observer<List<Song>>( ) {
            @Override
            public void onChanged(List<Song> songs) {
                adapter = new SongAdapter(songs, PlaybackQueueFragment.this);
                binding.recycler.setAdapter(adapter);
                binding.progressBar.setVisibility(View.GONE);
            }
        };
    }

    public Observer<List<Song>> getHistory(){
        return new Observer<List<Song>>( ) {
            @Override
            public void onChanged(List<Song> songs) {
                adapter = new SongAdapter(songs, PlaybackQueueFragment.this);
                binding.recycler.setAdapter(adapter);
                binding.progressBar.setVisibility(View.GONE);
            }
        };
    }

    @Override
    public void onSongSelected(Song seleccionat) {

    }
}