package com.example.toshokan_manga;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.toshokan_manga.ui.latestviews.LatestViewsFragment;

public class Latest_Views extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.latest__views_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, LatestViewsFragment.newInstance())
                    .commitNow();
        }
    }
}
