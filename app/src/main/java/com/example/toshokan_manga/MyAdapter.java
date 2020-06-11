package com.example.toshokan_manga;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
   private List<MangaC> mangaCS;
   private Context context;

    public MyAdapter(List<MangaC> mangaCS, Context context) {
        this.mangaCS = mangaCS;
        this.context = context;
        }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.manga_layout, parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MangaC mangaC = mangaCS.get(position);

        holder.textViewtitle.setText(mangaC.getT());
        holder.textViewmangaka.setText(mangaC.getA());
        Picasso.get().load(mangaC.getIm()).into(holder.imageViewbg);
    }

    @Override
    public int getItemCount() {
        return mangaCS.size()   ;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView textViewtitle;
        public TextView textViewmangaka;
        public ImageView imageViewbg;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewtitle = (TextView) itemView.findViewById(R.id.manga_title);
            textViewmangaka = (TextView) itemView.findViewById(R.id.mangaka_name);
            imageViewbg = (ImageView) itemView.findViewById(R.id.manga_background);
        }
    }
}
