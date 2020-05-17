package com.example.toshokan_manga;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.toshokan_manga.ui.favorite.FavoriteFragment;

public class Favorite extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.favorite_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, FavoriteFragment.newInstance())
                    .commitNow();
        }
    }
}
