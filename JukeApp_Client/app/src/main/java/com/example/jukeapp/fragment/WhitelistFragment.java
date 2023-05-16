package com.example.jukeapp.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.jukeapp.R;
import com.example.jukeapp.adapter.SongAdapter;
import com.example.jukeapp.databinding.FragmentWhitelistBinding;
import com.example.jukeapp.models.Song;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class WhitelistFragment extends Fragment implements View.OnClickListener, SongAdapter.SongSelectedListener {
    private BottomNavigationView bottomNav;
    private FragmentWhitelistBinding binding;
    private SongAdapter adapter;

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
        bottomNav = getActivity().findViewById(R.id.menu_nav);
        bottomNav.setVisibility(View.VISIBLE);
        binding.btnNewSong.setOnClickListener(this);
        binding.btnSearch.setOnClickListener(this);
        //Recycler
        binding.recycler.setLayoutManager(new LinearLayoutManager(requireContext()));
        adapter = new SongAdapter(Song.getSongs(),this);
        binding.recycler.setAdapter(adapter);
        return binding.getRoot();
    }


    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.btnNewSong){
            navController = NavHostFragment.findNavController(WhitelistFragment.this);
            navController.navigate(R.id.action_whitelistFragment_to_newSongFragment);
        } else if (view.getId() == R.id.btnSearch){
            String text = binding.edtSearch.getText().toString();
            if(!text.isEmpty()){
                ArrayList<Song> songs = new ArrayList<>();
                for (Song s: Song.getSongs()) {
                    if(s.getName().toLowerCase().contains(text.toLowerCase())){
                        songs.add(s);
                    }
                }
                adapter = new SongAdapter(songs, this);
            } else {
                adapter = new SongAdapter(Song.getSongs(), this);
                binding.edtSearch.setHint("Cerca una cançó");
            }
            binding.recycler.setAdapter(adapter);
        }
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