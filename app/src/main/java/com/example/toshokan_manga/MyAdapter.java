package com.example.toshokan_manga;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>  {
   private List<MangaC> mangaCS;
   private Context context;
   DatabaseReference reference;
   private MangaC mangaC;


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
                v.getContext().startActivity(intent);
                if (FirebaseAuth.getInstance().getCurrentUser() == null) {
                DatabaseReference reference1 = FirebaseDatabase.getInstance().getReference("Users")
                        .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                        .child("latest view");
                reference1.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()){
                         Long  max = dataSnapshot.getChildrenCount() + 1;
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

                MangaC posit = mangaCS.get(position);
                String a = posit.getK().toString();
                reference1.child(a).setValue(posit);

                }
            }
        });


        ///Add Favourite

       holder.favo.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
           @Override
           public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
               if (FirebaseAuth.getInstance().getCurrentUser() == null) {
                   Toast.makeText(context, "Please login first ...", Toast.LENGTH_LONG).show();
                   buttonView.setChecked(false);
               }else {


               DatabaseReference myref = FirebaseDatabase.getInstance().getReference("Users")
                       .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                       .child("favourites");
               myref.addValueEventListener(new ValueEventListener() {
                   @Override
                   public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                       if (dataSnapshot.exists()) {
                           Long max = dataSnapshot.getChildrenCount() + 1;
                       }
                   }

                   @Override
                   public void onCancelled(@NonNull DatabaseError databaseError) {

                   }
               });

               if (isChecked) {

                   MangaC posit = mangaCS.get(position);
                   String a = posit.getK().toString();
                   myref.child(a).setValue(posit);

               } else {
                   MangaC posi = mangaCS.get(position);
                   String r = posi.getK().toString();
                   myref.child(r).setValue(null);
               }

           }

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
        public CheckBox favo;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewtitle = (TextView) itemView.findViewById(R.id.manga_title);
            textViewmangaka = (TextView) itemView.findViewById(R.id.mangaka_name);
            imageViewbg = (ImageView) itemView.findViewById(R.id.manga_background);
            favo = (CheckBox) itemView.findViewById(R.id.fav_chek);
            constraintLayout = itemView.findViewById(R.id.constarinl);


        }
    }


}
