package com.example.jukeapp.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.example.jukeapp.R;


public class PlaybackQueueFragment extends Fragment implements CompoundButton.OnCheckedChangeListener {
    private Switch mSwitch;
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
        View view = inflater.inflate(R.layout.fragment_playback_queue, container, false);
        mSwitch = view.findViewById(R.id.switchPQ);
        mSwitch.setOnCheckedChangeListener(this);
        return view;
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        if (b) {
            mSwitch.setText("Historial");
        } else {
            mSwitch.setText("En cua");
        }
    }
}