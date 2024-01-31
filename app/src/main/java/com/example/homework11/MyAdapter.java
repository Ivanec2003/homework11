package com.example.homework11;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private final ArrayList<Song> data;
    private OnClickListener onClickListener;

    public MyAdapter(ArrayList<Song> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_song, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Song song = data.get(position);
        holder.bind(song);
        holder.itemView.setOnClickListener(v -> {
            if (onClickListener != null) {
                if (position != RecyclerView.NO_POSITION) {
                    onClickListener.onItemClick(position, song);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        private final ImageView imageViewSong;
        private final TextView textViewNameSong;
        MyViewHolder(View view) {
            super(view);
            imageViewSong = view.findViewById(R.id.imageViewSong);
            textViewNameSong = view.findViewById(R.id.textViewNameSong);
        }
        void bind(Song song){
            imageViewSong.setImageResource(song.getImage());
            textViewNameSong.setText(song.getName());
        }
    }
    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public interface OnClickListener {
        void onItemClick(int position, Song song);
    }
}
