package com.example.toshokan_manga.ui.favorite;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.toshokan_manga.MangaC;
import com.example.toshokan_manga.MyAdapter;
import com.example.toshokan_manga.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FavoriteFragment extends Fragment {
    View v;
    DatabaseReference reference1;


    public JsonArrayRequest request;
    public RequestQueue requestQueue;
    boolean scrolling = false;
    private RecyclerView recyclerView1;
    private RecyclerView.Adapter adapter1;
    private ProgressBar progressBar1;


    private List<MangaC> mangacs;

    private FavoriteViewModel mViewModel;

    public static FavoriteFragment newInstance() {
        return new FavoriteFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
       v = inflater.inflate(R.layout.favorite_fragment, container, false);


        recyclerView1 = v.findViewById(R.id.fav_recycler);
        recyclerView1.setHasFixedSize(true);
        recyclerView1.setLayoutManager(new LinearLayoutManager(getActivity()));
        reference1 = FirebaseDatabase.getInstance().getReference("Users")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .child("favourites");
        progressBar1 = v.findViewById(R.id.fav_progress);
        mangacs = new ArrayList<MangaC>();

        progressBar1.setVisibility(View.VISIBLE);
        reference1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                mangacs.clear();
                progressBar1.setVisibility(View.GONE);

                for (DataSnapshot dataSnapshot1:dataSnapshot.getChildren()){
                    MangaC manga = dataSnapshot1.getValue(MangaC.class);
                    mangacs.add(manga);

                }
                adapter1= new MyAdapter(mangacs,getActivity());
                recyclerView1.setAdapter(adapter1);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getActivity(), databaseError.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

    return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(FavoriteViewModel.class);
        // TODO: Use the ViewModel
    }

}