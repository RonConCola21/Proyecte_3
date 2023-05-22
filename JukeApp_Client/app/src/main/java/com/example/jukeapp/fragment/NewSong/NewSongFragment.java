package com.example.jukeapp.fragment.NewSong;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jukeapp.R;
import com.example.jukeapp.adapter.SongAdapter;
import com.example.jukeapp.api.ApiManager;
import com.example.jukeapp.api.SongApi;
import com.example.jukeapp.databinding.FragmentNewSongBinding;
import com.example.jukeapp.api.Song;
import com.example.jukeapp.fragment.Whitelist.WhitelistFragment;

import java.util.ArrayList;
import java.util.List;


public class NewSongFragment extends Fragment implements View.OnClickListener, SongAdapter.SongSelectedListener {
    private NavController navController;
    private FragmentNewSongBinding binding;
    private SongAdapter adapter;

    private NewSongViewModel viewModel;
    public NewSongFragment() {
        // Required empty public constructor
    }


    public static NewSongFragment newInstance(String param1, String param2) {
        NewSongFragment fragment = new NewSongFragment();
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
        binding = FragmentNewSongBinding.inflate(inflater, container, false);
        binding.btnCancel.setOnClickListener(this);
        binding.btnSearch.setOnClickListener(this);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        return binding.getRoot();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnCancel:
                navController = NavHostFragment.findNavController(NewSongFragment.this);
                navController.navigate(R.id.action_newSongFragment_to_whitelistFragment);
                break;
            case R.id.btnSearch:
                ArrayList<Song> song = new ArrayList<>();
                String text = binding.edtSearch.getText().toString();
                if (text.equals("")){
                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                    builder.setTitle("Error");
                    builder.setMessage("Necessites introduir un text per a la cerca");
                    builder.create().show();
                } else {
                    binding.pgrdownload.setVisibility(View.VISIBLE);
                    //buscar canóns a Spotify, no les locals
                    viewModel = new ViewModelProvider(this).get(NewSongViewModel.class);
                    viewModel.mSongs.observe(getViewLifecycleOwner(), new Observer<List<Song>>() {
                        @Override
                        public void onChanged(List<Song> songs) {
                            binding.recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
                            adapter = new SongAdapter(songs, NewSongFragment.this);
                            binding.recyclerView.setAdapter(adapter);
                            binding.pgrdownload.setVisibility(View.GONE);
                        }

                    });
                    viewModel.searchSongs(text);
                }
                binding.recyclerView.setAdapter(adapter);
        }
    }

    @Override
    public void onSongSelected(Song seleccionat) {
        View v =this.getView().findViewById(R.id.nav_host_fragment);
        new AlertDialog.Builder(this.getContext())
                .setTitle("Demanar afegir cançó a la llista de reproducció")
                .setMessage("Aquesta acció consumirà 100 crèdits. Vols continuar?")

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