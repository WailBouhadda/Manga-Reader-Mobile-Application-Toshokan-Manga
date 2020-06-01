package com.example.toshokan_manga.ui.mangalist;

import androidx.lifecycle.ViewModelProviders;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.toshokan_manga.Model.CustomAdapter;
import com.example.toshokan_manga.Model.Manga;
import com.example.toshokan_manga.R;

import java.util.ArrayList;
import java.util.List;


public class MangaListFragment extends Fragment{
    View v;
    private RecyclerView recyclerView;
    private List<Manga> mangas;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mangas = new ArrayList<>();
        mangas.add(new Manga(R.drawable.onepiece,"One Piece","Eichiro Oda"));
        mangas.add(new Manga(R.drawable.bluelock,"Blue Lock","Muneyuki Kaneshiro"));
        mangas.add(new Manga(R.drawable.boruto,"Boruto","Masashi Kishimoto"));
        mangas.add(new Manga(R.drawable.kingdom,"Kingdom","Yasuhisa Hara"));
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
        CustomAdapter adapter = new CustomAdapter(getContext(),mangas);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);

        return v;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(MangaListViewModel.class);
        // TODO: Use the ViewModel


    }




}
