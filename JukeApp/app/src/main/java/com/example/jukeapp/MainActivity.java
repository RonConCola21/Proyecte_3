package com.example.jukeapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity implements NavigationBarView.OnItemSelectedListener {
    private BottomNavigationView bottomNavigationView;
    private NavController navController;
    private boolean doubleBackToExitPressedOnce = false;
    private Handler mHandler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigationView = findViewById(R.id.menu_nav);
        bottomNavigationView.setSelectedItemId(R.id.navigation_bar_item_active_indicator_view2);
        bottomNavigationView.setOnItemSelectedListener(this);
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
        navController = navHostFragment.getNavController();
    }

    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            // if flag is true, exit app
            finish();
        }

        // set flag to true and show toast message
        doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Press back again to exit", Toast.LENGTH_SHORT).show();

        // reset flag after a short delay (2 seconds)
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 2000);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.navigation_bar_item_active_indicator_view:
                navController.navigate(R.id.action_global_QRFragment);
                return true;
            case R.id.navigation_bar_item_active_indicator_view2:
                navController.navigate(R.id.action_global_whitelistFragment);
                return true;
            case R.id.navigation_bar_item_active_indicator_view3:
                navController.navigate(R.id.action_global_playbackQueueFragment);
                return true;
            case R.id.navigation_bar_item_active_indicator_view4:
                navController.navigate(R.id.action_global_profileFragment);
                return true;
        }
        return false;
    }
}