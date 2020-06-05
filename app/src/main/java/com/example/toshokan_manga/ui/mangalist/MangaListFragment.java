package com.example.toshokan_manga.ui.mangalist;

import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.toshokan_manga.MainActivity;
import com.example.toshokan_manga.MangaInfoActivity;
import com.example.toshokan_manga.Model.CustomAdapter;
import com.example.toshokan_manga.Model.Manga;
import com.example.toshokan_manga.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class MangaListFragment extends Fragment implements CustomAdapter.OnMangaClickListener {

    View v;
    public static final String EXTRA_URL ="title";
    public static final  String EXTRA_IMG = "imageUrl";

    private RecyclerView recyclerView;
    public JsonArrayRequest request;
    public RequestQueue requestQueue;
    boolean scrolling = false;
    private List<Manga> mangas;



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mangas = new ArrayList<>();

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
        recyclerView=(RecyclerView) v.findViewById(R.id.rv_list);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
        parseJson();

        return v;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(MangaListViewModel.class);
        // TODO: Use the ViewModel


    }
    private void parseJson(){
        final String JSON_URL = "https://www.mangaeden.com/api/list/0/";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, JSON_URL, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("manga");
                            for (int i=0; i<response.length(); i++){
                                JSONObject mg = jsonArray.getJSONObject(i);
                                Manga manga = new Manga();
                                manga.setMangaName(mg.getString("t"));
                                manga.setBackground(mg.getString("im"));
                                mangas.add(manga);
                            }

                            CustomAdapter adapter = new CustomAdapter(getContext(),mangas);
                            recyclerView.setAdapter(adapter);
                            adapter.setOnMangaClickListener(MangaListFragment.this);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        requestQueue.add(request);
    }



    @Override
    public void onMangaClick(int position) {

        Intent detailIntent = new Intent(getActivity().getApplicationContext(), MangaInfoActivity.class);
        Manga clickedManga = mangas.get(position);
        detailIntent.putExtra(EXTRA_URL,clickedManga.getMangaName());
        detailIntent.putExtra(EXTRA_IMG,clickedManga.getBackground());

        startActivity(detailIntent);
    }
}