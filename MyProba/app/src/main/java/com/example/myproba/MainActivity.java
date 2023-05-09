package com.example.myproba;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.myproba.interfaz.SongApi;
import com.example.myproba.models.Song;

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
        Call<List<Song>> call = songApi.getSongs();
        call.enqueue(new Callback<List<Song>>() {
            @Override
            public void onResponse(Call<List<Song>> call, Response<List<Song>> response) {
                if(response.isSuccessful()){
                    List<Song> respuesta = response.body();
                    for (Song song : respuesta){
                        Log.i("Song_id", Integer.toString(song.getSon_id()));
                        Log.i("Song_img", song.getSon_img());
                        Log.i("Song_spotify_id", song.getSon_spotify_id());
                        Log.i("Song_artist1", song.getSon_artist1());
                        Log.i("Song_artist2", song.getSon_artist2());
                        Log.i("Song_name", song.getSon_name());
                        Log.i("Song_duration", Integer.toString(song.getSon_duration()));
                        Log.i("Song_status", Integer.toString(song.getSon_status()));
                    }

                }
                else {
                    Log.i("Error", "Error");
                }
            }

            @Override
            public void onFailure(Call<List<Song>> call, Throwable t) {
                Log.i("Error", t.getMessage());
            }
        });

    }

}