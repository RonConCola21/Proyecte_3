package com.example.jukeapp.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.jukeapp.R;


public class NewSongFragment extends Fragment implements View.OnClickListener {
    private Button btnCancel;
    private NavController navController;

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
        View view = inflater.inflate(R.layout.fragment_new_song, container, false);
        btnCancel = view.findViewById(R.id.btnCancel);
        btnCancel.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnCancel:
                navController = NavHostFragment.findNavController(NewSongFragment.this);
                navController.navigate(R.id.action_newSongFragment_to_whitelistFragment);
                break;
        }
    }
}