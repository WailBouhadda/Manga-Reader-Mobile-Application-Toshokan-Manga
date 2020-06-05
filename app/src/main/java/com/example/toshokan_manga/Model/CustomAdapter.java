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
import com.squareup.picasso.Picasso;

import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    Context mContext;
    List<Manga> mData;
    private OnMangaClickListener mListener;

    public interface OnMangaClickListener{
        void onMangaClick(int position);
    }

    public void setOnMangaClickListener(OnMangaClickListener listener){
        mListener = listener;
    }
    public CustomAdapter(Context mContext, List<Manga> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(mContext);
        View v = inflater.inflate(R.layout.manga_layout,parent,false);

        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Manga currentManga = mData.get(position);
        String imageURL = currentManga.getBackground();
        String mangaName = currentManga.getMangaName();
        holder.manga_title.setText(mangaName);
        Picasso.get().load(imageURL).fit().centerCrop().into(holder.background_img);

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView background_img;
        TextView manga_title, mangaka_name;

        public MyViewHolder(View itemView) {

            super(itemView);
            background_img = itemView.findViewById(R.id.manga_background);
            manga_title = itemView.findViewById(R.id.manga_title);
            mangaka_name = itemView.findViewById(R.id.mangaka_name);

            itemView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    if (mListener != null){
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            mListener.onMangaClick(position);
                        }
                    }
                }
            });
        }
    }




}
