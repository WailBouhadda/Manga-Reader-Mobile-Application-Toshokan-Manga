package com.example.toshokan_manga;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import static com.example.toshokan_manga.ui.mangalist.MangaListFragment.EXTRA_IMG;
import static com.example.toshokan_manga.ui.mangalist.MangaListFragment.EXTRA_URL;
import com.example.toshokan_manga.R;
import com.squareup.picasso.Picasso;

public class MangaInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manga_info_layout);

        Intent intent = getIntent();
        String imageUrl = intent.getStringExtra(EXTRA_IMG);
        String title = intent.getStringExtra(EXTRA_URL);

        ImageView imageView = findViewById(R.id.imageViewdetail);
        TextView textViewTitle = findViewById(R.id.textView8);

        Picasso.get().load(imageUrl).fit().centerCrop().into(imageView);
        textViewTitle.setText(title);

    }
}
