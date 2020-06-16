package com.example.toshokan_manga;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class MyChapterAdapter extends RecyclerView.Adapter<MyChapterAdapter.ViewHolder> {


    public MyChapterAdapter(List<chapters> chaptersListA, Context context) {
        this.chaptersListA = chaptersListA;
        this.context = context;
    }

    private List<chapters> chaptersListA;
   private Context context;

    public MyChapterAdapter(List<chapters> chaptersList, ValueEventListener valueEventListener) {

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater .from(parent.getContext())
                .inflate(R.layout.cahpterlistlayout,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        chapters chapterslist = chaptersListA.get(position);

        holder.numtextview.setText(chapterslist.getNum());



    }

    @Override
    public int getItemCount() {
        return chaptersListA.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView numtextview;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            numtextview = itemView.findViewById(R.id.num);
        }
    }
}