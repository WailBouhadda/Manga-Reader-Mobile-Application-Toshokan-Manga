package com.example.toshokan_manga;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.toshokan_manga.ui.mangalist.MangaListFragment;

public class Manga_List extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manga__list_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, MangaListFragment.newInstance())
                    .commitNow();
        }
    }
}
