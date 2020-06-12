package com.example.toshokan_manga;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MangaDetails extends AppCompatActivity implements View.OnClickListener {
    private TextView textViewtitle;
    private TextView textViewmangaka;
    private TextView textViewdate;
    private  TextView descrep;
    private ImageView imageView;

    DatabaseReference ref ;
    DatabaseReference reference;
    ListView listView;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manga_details);


        textViewtitle = findViewById(R.id.title_manga);
        textViewmangaka = findViewById(R.id.mangaka_manga);
        textViewdate = findViewById(R.id.release_dateTexview);
        imageView =findViewById(R.id.manga_image);
        descrep = findViewById(R.id.desc_text);
        listView = findViewById(R.id.listview1);
        ref = FirebaseDatabase.getInstance().getReference().child("Manga");
        reference = ref.child("categories");
        String po = getIntent().getStringExtra("pi");




        ref.child(po).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Object object = dataSnapshot.child("title").getValue();
                String name = dataSnapshot.child("artist").getValue().toString();
                String date = dataSnapshot.child("released").getValue().toString();
                String descr = dataSnapshot.child("description").getValue().toString();
                String img = dataSnapshot.child("image").getValue().toString();



                textViewtitle.setText(""+object);
                textViewmangaka.setText(""+name);
                textViewdate.setText(""+date);
                descrep.setText(""+descr);
                Glide.with(getApplicationContext())
                        .load(img)
                        .placeholder(R.drawable.background_img)
                        .into(imageView);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                Toast.makeText(MangaDetails.this, databaseError.getMessage(), Toast.LENGTH_LONG).show();
            }
        });





    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.backto_bttn:
                this.finish();
                break;
            case  R.id.details_bttn:
                break;
            case  R.id.chapters_bttn:
                break;
        }
    }
}
