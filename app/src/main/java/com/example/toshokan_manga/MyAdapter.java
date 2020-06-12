package com.example.toshokan_manga;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
   private List<MangaC> mangaCS;
   private Context context;
   DatabaseReference reference;

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
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        final MangaC mangaC = mangaCS.get(position);
        reference = FirebaseDatabase.getInstance().getReference().child("Manga_list");


        holder.textViewtitle.setText(mangaC.getT());
        holder.textViewmangaka.setText(mangaC.getA());
        Glide.with(context)
                .load(mangaC.getIm())
                .placeholder(R.drawable.background_img)
                .into(holder.imageViewbg);

        holder.constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext().getApplicationContext(),MangaDetails.class);
                String p = mangaC.getK().toString();
                intent.putExtra("pi" ,p);
                Toast.makeText(context, "This is : " + p, Toast.LENGTH_LONG).show();
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mangaCS.size()   ;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView textViewtitle;
        public TextView textViewmangaka;
        public ImageView imageViewbg;
        public ConstraintLayout constraintLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewtitle = (TextView) itemView.findViewById(R.id.manga_title);
            textViewmangaka = (TextView) itemView.findViewById(R.id.mangaka_name);
            imageViewbg = (ImageView) itemView.findViewById(R.id.manga_background);
            constraintLayout = itemView.findViewById(R.id.constarinl);
        }
    }
}
