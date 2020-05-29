package com.example.toshokan_manga.Model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.toshokan_manga.R;

import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.myViewHolder> {

    Context mContext;
    List<Manga> mData;

    public CustomAdapter(Context mContext, List<Manga> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(mContext);
        View v = inflater.inflate(R.layout.manga_layout,parent,false);
        return new myViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {
        holder.background_img.setImageResource(mData.get(position).getBackground());
        holder.manga_title.setText(mData.get(position).getMangaName());
        holder.mangaka_name.setText(mData.get(position).getMangaka());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class myViewHolder extends RecyclerView.ViewHolder{

        ImageView background_img;
        TextView manga_title, mangaka_name;

        public myViewHolder(View itemView) {
            super(itemView);
            background_img = itemView.findViewById(R.id.manga_background);
            manga_title = itemView.findViewById(R.id.manga_title);
            mangaka_name = itemView.findViewById(R.id.mangaka_name);
        }
    }
}
