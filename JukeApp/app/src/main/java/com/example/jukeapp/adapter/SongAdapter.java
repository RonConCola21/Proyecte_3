package com.example.jukeapp.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jukeapp.R;
import com.example.jukeapp.fragments.api.Song;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class SongAdapter extends RecyclerView.Adapter<SongAdapter.ViewHolder>{
    public List<Song> mSongs = new ArrayList<>();
    public int mPosItemSeleccionat = -1;
    public int mIdSeleccionat = -1;
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
        int pos = 0;

        fila.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPosItemSeleccionat = vh.getAdapterPosition();
                mIdSeleccionat = mSongs.get(mPosItemSeleccionat).getSonId();

                notifyDataSetChanged();
            }
        });

        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Song s = mSongs.get(position);
        holder.txtSongTitle.setText(s.getSonName());
        if (s.getSonArtist2() != null){
            holder.txtSongArtists.setText(s.getSonArtist1() + " " + s.getSonArtist2());
        }
        else {
            holder.txtSongArtists.setText(s.getSonArtist1());
        }
        holder.txtSongAlbum.setText(s.getSonAlbum());
        int min = s.getSonDuration() / 60;
        int sec = s.getSonDuration() % 60;
        holder.txtSongDuration.setText(Integer.toString(min) + ":" + Integer.toString(sec));
        Picasso.get().load(s.getSonImg()).into(holder.imgCover);
        if (mPosItemSeleccionat == position) {
            holder.llBackground.setBackgroundColor(ContextCompat.getColor(mContext, R.color.selected));
        } else {
            holder.llBackground.setBackgroundColor(ContextCompat.getColor(mContext, androidx.cardview.R.color.cardview_dark_background));
        }
    }

    @Override
    public int getItemCount() {return mSongs.size();}

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtSongTitle;
        TextView txtSongArtists;
        TextView txtSongAlbum;
        TextView txtSongDuration;
        ImageView imgCover;
        LinearLayout llBackground;

        public ViewHolder(@NonNull View fila) {
            super(fila);
            txtSongTitle = fila.findViewById(R.id.txtSongTitle);
            txtSongArtists = fila.findViewById(R.id.txtSongArtists);
            txtSongAlbum = fila.findViewById(R.id.txtSongAlbum);
            txtSongDuration = fila.findViewById(R.id.txtSongDuration);
            imgCover = fila.findViewById(R.id.imgCover);
            llBackground = fila.findViewById(R.id.llBackground);
        }
    }

    interface onClickSong {

    }
}
