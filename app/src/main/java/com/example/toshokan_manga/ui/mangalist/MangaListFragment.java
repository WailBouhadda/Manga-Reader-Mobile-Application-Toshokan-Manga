package com.example.toshokan_manga.ui.mangalist;

import androidx.lifecycle.ViewModelProviders;

import android.app.LauncherActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.SearchView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.toshokan_manga.MainActivity;
import com.example.toshokan_manga.MangaC;
import com.example.toshokan_manga.MangaInfoActivity;
import com.example.toshokan_manga.MangaInfoTabFragment;
import com.example.toshokan_manga.MyAdapter;
import com.example.toshokan_manga.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;


public class MangaListFragment extends Fragment {

    View v;
    DatabaseReference reference;


    public JsonArrayRequest request;
    public RequestQueue requestQueue;
    boolean scrolling = false;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;

    private List<MangaC> mangaCS;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);





    }

    @Override
    public void onStart() {
        super.onStart();


    }

    private MangaListViewModel mViewModel;

    public static MangaListFragment newInstance() {
        return new MangaListFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.manga_list_fragment,container,false);
        recyclerView = v.findViewById(R.id.rv_list);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        reference = FirebaseDatabase.getInstance().getReference().child("Manga_list");
        mangaCS = new ArrayList<MangaC>();

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                mangaCS.clear();

                for (DataSnapshot dataSnapshot1:dataSnapshot.getChildren()){
                    MangaC manga = dataSnapshot1.getValue(MangaC.class);
                      mangaCS.add(manga);

                }
                adapter = new MyAdapter(mangaCS,getActivity());
                recyclerView.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getActivity(), databaseError.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
        /*  for (int i=0;i <=10;i++){
                MangaC mangaC = new MangaC(
                        "wailwailwail" + (i+1),
                        "one piece",
                        "wail"
                );
            mangaCS.add(mangaC);



        adapter =  new MyAdapter(mangaCS,getActivity());
        recyclerView.setAdapter(adapter);
    }*/




        return v;


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(MangaListViewModel.class);
        // TODO: Use the ViewModel


    }

}