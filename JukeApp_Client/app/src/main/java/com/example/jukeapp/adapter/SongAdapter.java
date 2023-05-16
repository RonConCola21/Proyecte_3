package com.example.jukeapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jukeapp.R;
import com.example.jukeapp.models.Song;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SongAdapter extends RecyclerView.Adapter<SongAdapter.ViewHolder> {

    private List<Song> mSongs;
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
        Song s = mSongs.get(position);
        holder.txvName.setText(s.getName());
        holder.txvArtist1.setText(s.getArtist1());
        holder.txvArtist2.setText(s.getArtist2());
        holder.txvDuration.setText(s.getDuration()+"");
        holder.txvAlbum.setText(s.getAlbum());
        Picasso.get().load(s.getImage()).into(holder.imvImage);
    }

    @Override
    public int getItemCount() {
        return mSongs.size();
    }

    public interface SongSelectedListener {
        public void onSongSelected(Song seleccionat);
    }

    public SongAdapter(List<Song> pSongs, SongSelectedListener listener) {
        mListener = listener;
        mSongs = pSongs;
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
}
