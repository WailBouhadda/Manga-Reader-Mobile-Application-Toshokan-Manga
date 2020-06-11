package com.example.toshokan_manga;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Adapter;


import com.example.toshokan_manga.ui.mangalist.MangaListFragment;

import java.util.ArrayList;
import java.util.List;

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
