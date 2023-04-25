package com.example.probajukeapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.probajukeapp.R;
import com.example.probajukeapp.model.Song;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SongAdapter extends RecyclerView.Adapter<SongAdapter.ViewHolder>{
    private List<Song> mSongs;
    private int mPosItemSeleccionat = -1;
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

        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull SongAdapter.ViewHolder holder, int position) {
        Song s = mSongs.get(position);
        holder.txtSongTitle.setText(s.getName());
        holder.txtSongArtists.setText(s.getArtist());
        holder.txtSongAlbum.setText(s.getAlbum());
//        holder.txtSongDuration.setText(s.getDuration());
        Picasso.get().load(s.getCover()).into(holder.imgCover);


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
