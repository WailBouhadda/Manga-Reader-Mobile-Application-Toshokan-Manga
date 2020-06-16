package com.example.toshokan_manga;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class MyChapterAdapter extends RecyclerView.Adapter<MyChapterAdapter.ViewHolder> {

    private List<chapters> chaptersListA;
    private Context context;
    DatabaseReference reference;
    chapters chapterslist;
    public MyChapterAdapter(List<chapters> chaptersListA, Context context) {
        this.chaptersListA = chaptersListA;
        this.context = context;
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

        reference = FirebaseDatabase.getInstance().getReference().child("Manga").child("chapters");
        final chapters chapterslist = chaptersListA.get(position);

        holder.numtextview.setText(chapterslist.getNum());


        holder.chp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext().getApplicationContext(),WebView.class);
                String U = chapterslist.getUrl().toString();
                intent.putExtra("U" ,U);
                v.getContext().startActivity(intent);

            }
        });



    }

    @Override
    public int getItemCount() {
        return chaptersListA.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView numtextview;
        public ConstraintLayout chp;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            numtextview = itemView.findViewById(R.id.num);
            chp = itemView.findViewById(R.id.chp_layout);
        }
    }
}
