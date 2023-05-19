package com.example.jukeapp.fragment.Whitelist;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jukeapp.R;
import com.example.jukeapp.adapter.SongAdapter;
import com.example.jukeapp.databinding.FragmentWhitelistBinding;
import com.example.jukeapp.api.Song;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class WhitelistFragment extends Fragment implements SongAdapter.SongSelectedListener {
    private BottomNavigationView bottomNav;
    private FragmentWhitelistBinding binding;
    private SongAdapter adapter;
    private WhitelistViweModel viewModel;
    private NavController navController;

    public WhitelistFragment() {
        // Required empty public constructor
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
        binding = FragmentWhitelistBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(this).get(WhitelistViweModel.class);
        viewModel.mSongs.observe(getViewLifecycleOwner(), new Observer<List<Song>>() {
            @Override
            public void onChanged(List<Song> songs) {
                bottomNav = getActivity().findViewById(R.id.menu_nav);
                bottomNav.setVisibility(View.VISIBLE);
                binding.btnNewSong.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        navController = NavHostFragment.findNavController(WhitelistFragment.this);
                        navController.navigate(R.id.action_whitelistFragment_to_newSongFragment);
                    }
                });
                binding.btnSearch.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String text = binding.edtSearch.getText().toString();
//                        if (!text.isEmpty()) {
//                            for (Song s : Song.getSong(songs, "w")) {
//                                if (s.getSonName().toLowerCase().contains(text.toLowerCase())) {
//                                    songs.add(s);
//                                }
//                            }
//                            adapter.setSongs(songs);
//                        } else {
//                            adapter.setSongs(Song.getSong(songs, "w"));
//                        }
                    }
                });
                //Recycler
                binding.recycler.setLayoutManager(new LinearLayoutManager(requireContext()));
                adapter = new SongAdapter(songs, WhitelistFragment.this);
                binding.recycler.setAdapter(adapter);
                binding.pgrdownload.setVisibility(View.GONE);
            }
        });
        viewModel.getSongs();
    }

    @Override
    public void onSongSelected(Song seleccionat) {
        View v =this.getView().findViewById(R.id.nav_host_fragment);
        new AlertDialog.Builder(this.getContext())
                .setTitle("Reproduïr cançó")
                .setMessage("Aquesta acció consumirà 50 crèdits. Vols continuar?")

                // Specifying a listener allows you to take an action before dismissing the dialog.
                // The dialog is automatically dismissed when a dialog button is clicked.
                .setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // Afegir cançó a la llista de reproducció
                    }
                })

                // A null listener allows the button to dismiss the dialog and take no further action.
                .setNegativeButton(android.R.string.no, null)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }
}