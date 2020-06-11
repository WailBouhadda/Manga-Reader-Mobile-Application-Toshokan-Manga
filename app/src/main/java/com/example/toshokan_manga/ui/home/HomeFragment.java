package com.example.toshokan_manga.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.toshokan_manga.MangaC;
import com.example.toshokan_manga.MangaInfo;
import com.example.toshokan_manga.MyAdapter;
import com.example.toshokan_manga.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HomeFragment extends Fragment {

    View v;
    DatabaseReference reference;


    public JsonArrayRequest request;
    public RequestQueue requestQueue;
    boolean scrolling = false;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private ProgressBar progressBar;


    private List<MangaC> mangaCS;

    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View v = inflater.inflate(R.layout.fragment_home, container, false);

        recyclerView = v.findViewById(R.id.rv_lu);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        reference = FirebaseDatabase.getInstance().getReference().child("Manga_list");
        progressBar = v.findViewById(R.id.p_b1);
        mangaCS = new ArrayList<MangaC>();


        progressBar.setVisibility(View.VISIBLE);
        reference.orderByChild("last_chapter_date").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                mangaCS.clear();
                progressBar.setVisibility(View.GONE);

                for (DataSnapshot dataSnapshot1:dataSnapshot.getChildren()){
                    MangaC manga = dataSnapshot1.getValue(MangaC.class);
                    mangaCS.add(manga);

                }
                Collections.reverse(mangaCS);
                adapter = new MyAdapter(mangaCS,getActivity());
                recyclerView.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getActivity(), databaseError.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
        return v;
    }
}
