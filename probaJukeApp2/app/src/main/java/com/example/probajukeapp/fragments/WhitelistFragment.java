package com.example.probajukeapp.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.probajukeapp.R;
import com.example.probajukeapp.adapter.SongAdapter;
import com.example.probajukeapp.api.Song;
import com.example.probajukeapp.viewmodels.WhiteListViewModel;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link WhitelistFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class WhitelistFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public WhitelistFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment WhitelistFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static WhitelistFragment newInstance(String param1, String param2) {
        WhitelistFragment fragment = new WhitelistFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    private WhiteListViewModel viewModel;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_whitelist, container, false);

        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel = new ViewModelProvider(this).get(WhiteListViewModel.class);

        viewModel.mSongs.observe(getViewLifecycleOwner(), new Observer<List<Song>>() {
            @Override
            public void onChanged(List<Song> songs) {
                RecyclerView rcyPlaylist = view.findViewById(R.id.rcyWhitelist);
                SongAdapter songAdapter = new SongAdapter(songs, getContext());

                rcyPlaylist.setLayoutManager(new LinearLayoutManager(getContext()));
                rcyPlaylist.setAdapter(songAdapter);
                rcyPlaylist.setHasFixedSize(true);
            }
        });
        viewModel.getMSongs();
    }
}