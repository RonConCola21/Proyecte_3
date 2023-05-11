package com.example.probajukeapp.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.probajukeapp.R;
import com.example.probajukeapp.api.Song;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class SongAdapter extends RecyclerView.Adapter<SongAdapter.ViewHolder>{
    public List<Song> mSongs = new ArrayList<>();
    public int mPosItemSeleccionat = -1;
    private Context mContext;

    public SongAdapter(List<Song> psongs, Context c) {
        mSongs = psongs;
        mContext = c;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View fila = LayoutInflater.from(parent.getContext()).inflate(R.layout.fila_song, parent, false);
        ViewHolder vh = new ViewHolder(fila);

        fila.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPosItemSeleccionat = vh.getAdapterPosition();
                Log.i("Canvi posicio", "Hem canviat la posicio seleccionada a "+mPosItemSeleccionat);
                notifyDataSetChanged();
            }
        });

        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull SongAdapter.ViewHolder holder, int position) {
        Song s = mSongs.get(position);
        holder.txtSongTitle.setText(s.getSonName());
        if (s.getSonArtist2() != null){
            holder.txtSongArtists.setText(s.getSonArtist1() + " " + s.getSonArtist2());
        }
        else {
            holder.txtSongArtists.setText(s.getSonArtist1());
        }
//        holder.txtSongAlbum.setText(s.getSonAlbum());
        holder.txtSongDuration.setText(Integer.toString(s.getSonDuration()));
        Picasso.get().load(s.getSonImg()).into(holder.imgCover);
    }

    @Override
    public int getItemCount() {return mSongs.size();}



    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtSongTitle;
        TextView txtSongArtists;
        TextView txtSongAlbum;
        TextView txtSongDuration;
        ImageView imgCover;

        public ViewHolder(@NonNull View fila) {
            super(fila);
            txtSongTitle = fila.findViewById(R.id.txtSongTitle);
            txtSongArtists = fila.findViewById(R.id.txtSongArtists);
            txtSongAlbum = fila.findViewById(R.id.txtSongAlbum);
            txtSongDuration = fila.findViewById(R.id.txtSongDuration);
            imgCover = fila.findViewById(R.id.imgCover);
        }
    }
}
