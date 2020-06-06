package com.example.toshokan_manga;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import static com.example.toshokan_manga.ui.mangalist.MangaListFragment.EXTRA_IMG;
import static com.example.toshokan_manga.ui.mangalist.MangaListFragment.EXTRA_URL;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MangaInfoTabFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MangaInfoTabFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MangaInfoTabFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MangaInfoTabFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MangaInfoTabFragment newInstance(String param1, String param2) {
        MangaInfoTabFragment fragment = new MangaInfoTabFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.manga_info_layout, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        Intent intent = getActivity().getIntent();
        String imageUrl = intent.getStringExtra(EXTRA_IMG);
        String title = intent.getStringExtra(EXTRA_URL);

        ImageView imageView = getView().findViewById(R.id.imageViewdetail);
        TextView textViewTitle = getView().findViewById(R.id.textView8);

        Picasso.get().load(imageUrl).fit().centerCrop().into(imageView);
        textViewTitle.setText(title);
    }
}
