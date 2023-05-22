package com.example.jukeapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.jukeapp.fragments.BlacklistFragment;
import com.example.jukeapp.fragments.PlaylistFragment;
import com.example.jukeapp.fragments.RequestlistFragment;
import com.example.jukeapp.fragments.WhitelistFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.spotify.sdk.android.auth.AuthorizationClient;
import com.spotify.sdk.android.auth.AuthorizationRequest;
import com.spotify.sdk.android.auth.AuthorizationResponse;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnItemSelectedListener {

    private static final String CLIENT_ID = "5a6d8dfb099949bea1428cc7795f42b9";
    private static final String REDIRECT_URI = "https://example.com/callback";
    private static final int REQUEST_CODE = 1337;

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

    private void solicitarAutorizacion() {
        AuthorizationRequest.Builder builder = new AuthorizationRequest.Builder(CLIENT_ID, AuthorizationResponse.Type.TOKEN, REDIRECT_URI);
        builder.setScopes(new String[]{"user-read-private", "user-read-email"});
        AuthorizationRequest request = builder.build();
        AuthorizationClient.openLoginActivity(this, REQUEST_CODE, request);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE) {
            AuthorizationResponse response = AuthorizationClient.getResponse(resultCode, data);
            if (response.getType() == AuthorizationResponse.Type.TOKEN) {
                String accessToken = response.getAccessToken();
                Log.i("TAG", "onActivityResult: " + accessToken);
            }
        }
    }

//    private void obtenerInformacionDePerfil(String accessToken) {
//        new AsyncTask<Void, Void, String>() {
//            @Override
//            protected String doInBackground(Void... voids) {
//                OkHttpClient client = new OkHttpClient();
//                Request request = new Request.Builder()
//                        .url("https://api.spotify.com/v1/me")
//                        .addHeader("Authorization", "Bearer " + accessToken)
//                        .build();
//                try {
//                    Response response = client.newCall(request).execute();
//                    String json = response.body().string();
//                    return json;
//                } catch (Exception e) {
//                    Log.e("MainActivity", "Error al obtener información del perfil", e);
//                    return null;
//                }
//            }
//
//
//            @Override
//            protected void onPostExecute(String json) {
//                super.onPostExecute(json);
//                if (json != null) {
//                    try {
//                        JSONObject data = new JSONObject(json);
//                        String nombreDeUsuario = data.getString("display_name");
//                        int numeroDeSeguidores = data.getJSONObject("followers").getInt("total");
//                        Log.i("XXX", "onPostExecute: " + nombreDeUsuario + " - " + numeroDeSeguidores);
//                    } catch (JSONException e) {
//                        Log.e("MainActivity", "Error al analizar la respuesta JSON", e);
//                    }
//                }
//            }
//        }.execute();
//    }
}