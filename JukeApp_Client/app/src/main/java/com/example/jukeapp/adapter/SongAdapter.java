package com.example.jukeapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jukeapp.R;
import com.example.jukeapp.api.Song;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SongAdapter extends RecyclerView.Adapter<SongAdapter.ViewHolder> {

    private List<Song> mSongs;
    private List<Song> filteredSongs;
    private int mPosItemSeleccionat = -1;
    private SongSelectedListener mListener;

    @NonNull
    @Override
    public SongAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View fila = LayoutInflater.from(parent.getContext()).inflate(R.layout.fila, parent, false);
        ViewHolder vh = new ViewHolder(fila);
        fila.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int pos = vh.getAdapterPosition();
                Song s = mSongs.get(pos);
                if(mListener!=null) mListener.onSongSelected(s);
                notifyItemChanged(pos);
            }
        });
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull SongAdapter.ViewHolder holder, int position) {
        Song s = filteredSongs.get(position);
        holder.txvName.setText(s.getSonName());
        holder.txvArtist1.setText(s.getSonArtist1());
        holder.txvArtist2.setText(s.getSonArtist2());
        int result = (int) Math.floor(s.getSonDuration());
        String duracio = result/60 + ":" + result%60;
        holder.txvDuration.setText(duracio);
        holder.txvAlbum.setText(s.getSonAlbum());
        Picasso.get().load(s.getSonImg()).into(holder.imvImage);
    }

    @Override
    public int getItemCount() {
        return filteredSongs.size();
    }

    public void filtrer(String query){
        int longitud = query.length();
        if(longitud==0) {
            filteredSongs.clear( );
            filteredSongs.addAll(mSongs);
        }else {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                List<Song> col = filteredSongs.stream().filter(s -> s.getSonName().toLowerCase().contains(query.toLowerCase())).collect(Collectors.toList());
                filteredSongs.clear();
                filteredSongs.addAll(col);
            }
            else {
                for (Song s : mSongs) {
                    if (s.getSonName().toLowerCase().contains(query.toLowerCase())) {
                        filteredSongs.add(s);
                    }
                }
            }
        }
        notifyDataSetChanged();
    }

    public interface SongSelectedListener {
        public void onSongSelected(Song seleccionat);
    }

    public SongAdapter(List<Song> pSongs, SongSelectedListener listener) {
        mListener = listener;
        mSongs = pSongs;
        filteredSongs = new ArrayList<>();
        filteredSongs.addAll(mSongs);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imvImage;
        TextView txvName;
        TextView txvArtist1;
        TextView txvArtist2;
        TextView txvDuration;
        TextView txvAlbum;
        public ViewHolder(@NonNull View fila) {
            super(fila);
            txvName = fila.findViewById(R.id.txvName);
            txvArtist1 = fila.findViewById(R.id.txvArtist1);
            txvArtist2 = fila.findViewById(R.id.txvArtist2);
            txvDuration = fila.findViewById(R.id.txvDuration);
            txvAlbum = fila.findViewById(R.id.txvAlbum);
            imvImage = fila.findViewById(R.id.imgSong);
        }
    }

    public void clearData(){
        mSongs.clear();
        notifyDataSetChanged();
    }
}
