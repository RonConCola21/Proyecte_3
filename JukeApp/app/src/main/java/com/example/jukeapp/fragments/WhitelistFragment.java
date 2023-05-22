package com.example.jukeapp.fragments;

import static java.lang.Thread.sleep;

import android.os.Bundle;
import android.util.Log;
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
import com.example.jukeapp.databinding.FragmentWhitelistBinding;
import com.example.jukeapp.fragments.api.Song;
import com.example.jukeapp.viewmodels.WhiteListViewModel;

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

    private FragmentWhitelistBinding binding;

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
        binding = FragmentWhitelistBinding.inflate(inflater, container, false);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel = new ViewModelProvider(this).get(WhiteListViewModel.class);

        viewModel.mSongs.observe(getViewLifecycleOwner(), new Observer<List<Song>>() {
            @Override
            public void onChanged(List<Song> songs) {
                SongAdapter songAdapter = new SongAdapter(songs, getContext());

                binding.rcyWhitelist.setLayoutManager(new LinearLayoutManager(getContext()));
                binding.rcyWhitelist.setAdapter(songAdapter);
                binding.rcyWhitelist.setHasFixedSize(true);
                binding.progressBar.setVisibility(View.GONE);
                binding.btnGo2BlackList.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (songAdapter.mIdSeleccionat != -1){
                            Song.updateSong(songAdapter.mIdSeleccionat, "b");
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