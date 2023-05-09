package com.example.myproba;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.myproba.api.Song;
import com.example.myproba.api.WSGetSongs;
import com.example.myproba.interfaz.SongApi;

import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    TextView txtResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        txtResult = findViewById(R.id.txtResult);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getPost();

    }

    private void getPost(){
        final OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(120, TimeUnit.SECONDS)
                .connectTimeout(120, TimeUnit.SECONDS)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.2.181.69/jukeapp/public/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
        SongApi songApi = retrofit.create(SongApi.class);
        Call<WSGetSongs> call = songApi.getSongs();
        call.enqueue(new Callback<WSGetSongs>() {
            @Override
            public void onResponse(Call<WSGetSongs> call, Response<WSGetSongs> response) {
                if(response.isSuccessful()){
                    WSGetSongs respuesta = response.body();
                    for (Song song : respuesta.getData()){
                        Log.i("Song_id", Integer.toString(song.getSonId()));
                        Log.i("Song_img", song.getSonImg());
                        Log.i("Song_spotify_id", song.getSonSpotifyId());
                        Log.i("Song_artist1", song.getSonArtist1());
                        if (song.getSonArtist2() != null){
                            Log.i("Song_artist2", song.getSonArtist2());
                        }
                        Log.i("Song_name", song.getSonName());
                        Log.i("Song_duration", Integer.toString(song.getSonDuration()));
                        Log.i("Song_status", song.getSonStatus());
                    }

                }
                else {
                    Log.i("Error", "Error");
                }
            }

            @Override
            public void onFailure(Call<WSGetSongs> call, Throwable t) {
                Log.e("Error Failure", t.getMessage(),t);
            }
        });

    }

}