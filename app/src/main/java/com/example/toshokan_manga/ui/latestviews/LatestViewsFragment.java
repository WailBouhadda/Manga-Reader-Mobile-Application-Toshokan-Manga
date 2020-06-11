package com.example.toshokan_manga.ui.latestviews;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.android.volley.RequestQueue;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.toshokan_manga.R;
import com.squareup.picasso.Picasso;

public class LatestViewsFragment extends Fragment {

    private LatestViewsViewModel mViewModel;
    private ImageView imageView;
    private View v;

    public static LatestViewsFragment newInstance() {
        return new LatestViewsFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
       v = inflater.inflate(R.layout.latest_views_fragment, container, false);
       imageView = v.findViewById(R.id.imageView_i);
        RequestOptions requestOptions =new RequestOptions();
        requestOptions.placeholder(R.drawable.background_img);
        requestOptions.error(R.drawable.gray);
        Glide.with(getActivity())
                .load("https://firebasestorage.googleapis.com/v0/b/toshokanmanga1.appspot.com/o/62d5a03f9e0e4eaa5d153b55ba8ce467.jpg?alt=media&token=20e114b5-511d-4dc7-8d74-7b23fe8b8477")
                .apply(requestOptions)
                .into(imageView);
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(LatestViewsViewModel.class);
        // TODO: Use the ViewModel
    }

}
