package com.example.toshokan_manga;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MangaDetails extends AppCompatActivity {
    private TextView textViewtitle;
    private TextView textViewmangaka;
    private TextView textViewdate;
    private  TextView descrep;
    private TextView titlet;
    private ImageView imageView;
    private Button backbttn;
    private Button chaptersb;
    private Button detaitlsb;
    private Button favoriteb;
    private ConstraintLayout detailslayout;
    private ConstraintLayout chapterslayout;
    DatabaseReference ref ;
    DatabaseReference reference1;
    ListView listView;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manga_details);


        textViewtitle = findViewById(R.id.title_manga);
        textViewmangaka = findViewById(R.id.mangaka_manga);
        textViewdate = findViewById(R.id.release_dateTexview);
        titlet = findViewById(R.id.titletxt);
        imageView =findViewById(R.id.manga_image);
        descrep = findViewById(R.id.desc_text);
        listView = findViewById(R.id.listview1);
        backbttn = findViewById(R.id.backto_bttn);
        chaptersb = findViewById(R.id.chapters_bttn);
        detaitlsb = findViewById(R.id.details_bttn);
        favoriteb = findViewById(R.id.fav_bttn);
        detailslayout = findViewById(R.id.details_layout);
        chapterslayout = findViewById(R.id.chapters_layout);

        ref = FirebaseDatabase.getInstance().getReference().child("Manga");
        reference1 = ref.child("categories");
        final String po = getIntent().getStringExtra("pi");


backbttn.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        finish();
    }
});

chaptersb.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        detailslayout.setVisibility(View.GONE);
        chapterslayout.setVisibility(View.VISIBLE);

    }
});

detaitlsb.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        detailslayout.setVisibility(View.VISIBLE);
        chapterslayout.setVisibility(View.GONE);
    }
});

favoriteb.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {

    }
});

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
                titlet.setText(""+object);
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
}
