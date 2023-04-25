package com.example.jukeapp.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.jukeapp.R;
import com.example.jukeapp.adapter.SongAdapter;
import com.example.jukeapp.models.Song;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class WhitelistFragment extends Fragment implements View.OnClickListener, SongAdapter.SongSelectedListener {
    private BottomNavigationView bottomNav;
    private ImageButton btnAdd;
    private NavController navController;
    private EditText edtSearch;
    private ImageButton btnSearch;
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
        View view = inflater.inflate(R.layout.fragment_whitelist, container, false);
        btnAdd = view.findViewById(R.id.btnNewSong);
        btnSearch = view.findViewById(R.id.btnSearch);
        edtSearch = view.findViewById(R.id.edtSearch);
        bottomNav = getActivity().findViewById(R.id.menu_nav);
        bottomNav.setVisibility(View.VISIBLE);
        btnAdd.setOnClickListener(this);
        btnSearch.setOnClickListener(this);
        //Recycler
        RecyclerView recyclerView = view.findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        SongAdapter adapter = new SongAdapter(Song.getSongs(),this);
        recyclerView.setAdapter(adapter);
        return view;
    }


    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.btnNewSong){
            navController = NavHostFragment.findNavController(WhitelistFragment.this);
            navController.navigate(R.id.action_whitelistFragment_to_newSongFragment);
        } else if (view.getId() == R.id.btnSearch){
            String text = edtSearch.getText().toString();
            if(!text.isEmpty()){

            }
        }
    }

    @Override
    public void onSongSelected(Song seleccionat) {
        View v =this.getView().findViewById(R.id.nav_host_fragment);
    }
}