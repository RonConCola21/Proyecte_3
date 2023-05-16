package com.example.jukeapp.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.jukeapp.R;
import com.example.jukeapp.adapter.SongAdapter;
import com.example.jukeapp.databinding.FragmentNewSongBinding;
import com.example.jukeapp.models.Song;

import java.util.ArrayList;


public class NewSongFragment extends Fragment implements View.OnClickListener, SongAdapter.SongSelectedListener {
    private NavController navController;
    private FragmentNewSongBinding binding;
    private SongAdapter adapter;
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
                    ArrayList<Song> songs = new ArrayList<>();
                    adapter = new SongAdapter(songs,this);
                    binding.edtSearch.setHint("Buscar cançons");
                } else {
                    //buscar canóns a Spotify, no les locals
                    for (Song s : Song.getSongs()) {
                        if(s.getName().toLowerCase().contains(text.toLowerCase())){
                            song.add(s);
                        }
                    }
                    adapter = new SongAdapter(song, this);
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