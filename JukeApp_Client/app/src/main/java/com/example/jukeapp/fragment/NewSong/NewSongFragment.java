package com.example.jukeapp.fragment.NewSong;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jukeapp.R;
import com.example.jukeapp.adapter.SongAdapter;
import com.example.jukeapp.api.ApiManager;
import com.example.jukeapp.api.GetUserSuccess;
import com.example.jukeapp.api.SongApi;
import com.example.jukeapp.api.WSCreate;
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

    public static final String USER = "user";
    private GetUserSuccess user;


    public static NewSongFragment newInstance(GetUserSuccess user) {
        NewSongFragment fragment = new NewSongFragment();
        Bundle args = new Bundle();
        args.putParcelable(USER, user);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null){
            user = getArguments().getParcelable(USER);
        }
        Log.i("Valor id_user", "El valor de id user es: " + user.getUserId());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentNewSongBinding.inflate(inflater, container, false);
        binding.btnCancel.setOnClickListener(this);
        binding.btnSearch.setOnClickListener(this);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        viewModel = new ViewModelProvider(this).get(NewSongViewModel.class);
        return binding.getRoot();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnCancel:
                Bundle args = new Bundle();
                args.putParcelable(NewSongFragment.USER, user);
                navController = NavHostFragment.findNavController(NewSongFragment.this);
                navController.navigate(R.id.action_newSongFragment_to_whitelistFragment, args);
                break;
            case R.id.btnSearch:
                String text = binding.edtSearch.getText().toString();
                if (text.equals("")){
                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                    builder.setTitle("Error");
                    builder.setMessage("Necessites introduir un text per a la cerca");
                    builder.create().show();
                } else {
                    binding.pgrdownload.setVisibility(View.VISIBLE);
                    //buscar canóns a Spotify, no les locals
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
                        viewModel.mCreatedSong.observe(getViewLifecycleOwner( ), new Observer<WSCreate>( ) {
                            @Override
                            public void onChanged(WSCreate wsCreate) {
                                if(wsCreate != null){
                                    if(wsCreate.getSuccess().equals("ok")){
                                        goToWhiteList();
                                    }
                                    else {
                                        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                                        builder.setTitle("Error");
                                        builder.setMessage("No s'ha pogut afegir la cançó");
                                        builder.create().show();
                                    }
                                }
                            }
                        });
                        //Esto me da dolor de ojos
                        viewModel.createSong(seleccionat.getSonSpotifyId(), seleccionat.getSonStatus(), seleccionat.getSonName(),
                                seleccionat.getSonArtist1(), seleccionat.getSonArtist2(), seleccionat.getSonDuration(), seleccionat.getSonImg(),
                                seleccionat.getSonAlbum(), user.getUserId());
                    }
                })

                // A null listener allows the button to dismiss the dialog and take no further action.
                .setNegativeButton(android.R.string.no, null)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    public void goToWhiteList(){
        Bundle args = new Bundle();
        args.putParcelable(NewSongFragment.USER, user);
        navController = NavHostFragment.findNavController(NewSongFragment.this);
        navController.navigate(R.id.action_newSongFragment_to_whitelistFragment, args);
    }
}