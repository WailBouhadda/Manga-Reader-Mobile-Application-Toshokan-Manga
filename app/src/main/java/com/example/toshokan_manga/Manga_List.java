package com.example.toshokan_manga;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Adapter;

import com.example.toshokan_manga.Model.CustomAdapter;
import com.example.toshokan_manga.Model.Manga;
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


        //makes the manga layout transparent
        Window w =getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        //setup the recycleview with the adapter
        RecyclerView recyclerView= findViewById(R.id.rv_list);
        List<Manga> mlist = new ArrayList<>();
        mlist.add(new Manga(R.drawable.onepiece,"One Piece","Eichiro Oda"));
        mlist.add(new Manga(R.drawable.bluelock,"Blue Lock","Muneyuki Kaneshiro"));

        mlist.add(new Manga(R.drawable.kingdom,"Kingdom","Yasuhisa Hara"));

        CustomAdapter adapter = new CustomAdapter(this,mlist);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }
}
