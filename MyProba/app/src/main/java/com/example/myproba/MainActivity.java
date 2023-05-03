package com.example.myproba;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myproba.interfaz.songApi;
import com.example.myproba.models.posts;

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
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.2.181.69/jukeapp/public/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        songApi songApi = retrofit.create(songApi.class);
        Call<posts> call = songApi.getPost();
        call.enqueue(new Callback<posts>() {
            @Override
            public void onResponse(Call<posts> call, Response<posts> response) {
                if(!response.isSuccessful()){
                    txtResult.setText("Code: " + response.code());
                    return;
                }
                posts posts = response.body();
                String content = "";
                content += "Song ID: " + posts.getSong_id() + "\n";
                content += "Song Image: " + posts.getSong_img() + "\n";
                content += "Artist: " + posts.getArtist() + "\n\n";
                txtResult.setText(content);
            }

            @Override
            public void onFailure(Call<posts> call, Throwable t) {
                Log.i("Error", t.getMessage());
            }
        });

    }

}