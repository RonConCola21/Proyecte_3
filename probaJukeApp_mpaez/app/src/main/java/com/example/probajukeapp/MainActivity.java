package com.example.probajukeapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;
import com.example.probajukeapp.adapter.SongAdapter;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.example.probajukeapp.fragments.BlacklistFragment;
import com.example.probajukeapp.fragments.PlaylistFragment;
import com.example.probajukeapp.fragments.RequestlistFragment;
import com.example.probajukeapp.fragments.WhitelistFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnItemSelectedListener {

    private BottomNavigationView bottomNavigationView;

    PlaylistFragment playlistFragment = new PlaylistFragment();
    WhitelistFragment whitelistFragment = new WhitelistFragment();
    BlacklistFragment blacklistFragment = new BlacklistFragment();
    RequestlistFragment requestlistFragment = new RequestlistFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);

        bottomNavigationView.setOnItemSelectedListener(this);

    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int result = item.getItemId();
        if (result == R.id.fragmentPlayList){
            Log.d("TAG", "onNavigationItemSelected: QR");
            loadFragment(playlistFragment);
            return true;
        } else if (result == R.id.fragmentWhiteList){
            Log.d("TAG", "onNavigationItemSelected: White List");
            loadFragment(whitelistFragment);
            return true;
        } else if (result == R.id.fragmentBlackList){
            Log.d("TAG", "onNavigationItemSelected: Profile");
            loadFragment(blacklistFragment);
            return true;
        } else if (result == R.id.fragmentRequestList){
            Log.d("TAG", "onNavigationItemSelected: Request List");
            loadFragment(requestlistFragment);
            return true;
        }
        return false;
    }

    private void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragmentContainerView, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}