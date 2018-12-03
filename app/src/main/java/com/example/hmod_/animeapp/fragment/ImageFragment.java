package com.example.hmod_.animeapp.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.hmod_.animeapp.Activity.detail_activity;
import com.example.hmod_.animeapp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Objects;

public class ImageFragment extends Fragment {

    ArrayList<String> values;
    Intent intentThatStartedThisActivity;
    public static final String IMAGE_POSTER = "imageview_poster";
    private CollapsingToolbarLayout collapsingToolbar;
    private View cordinatorLayout;
    String image_poster;
    private Context context;
    ImageView imageView;

    public static ImageFragment newInstance(ArrayList<String> values) {
        Bundle args = new Bundle();
        args.putStringArrayList("images", values);
        ImageFragment fragment = new ImageFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_fragment_image, container, false);
        intentThatStartedThisActivity = Objects.requireNonNull(getActivity()).getIntent();

        Objects.requireNonNull(((detail_activity) getActivity()).getSupportActionBar()).setHomeButtonEnabled(true);
        Objects.requireNonNull(((detail_activity) getActivity()).getSupportActionBar()).setDisplayHomeAsUpEnabled(true);


        collapsingToolbar = getActivity().findViewById(R.id.toolbar_layout);
        LinearLayout detailLayout = view.findViewById(R.id.detail_layout);
        CardView infoCardView = view.findViewById(R.id.info_card_view);
        TextView title = view.findViewById(R.id.title);
        cordinatorLayout = getActivity().findViewById(R.id.app_bar);

        imageView = view.findViewById(R.id.image);

        if (intentThatStartedThisActivity != null) {
            loadMovieDetails();
        }
        return view;
    }

    public void loadMovieDetails() {

        try {
            cordinatorLayout.setVisibility(View.VISIBLE);
            image_poster = intentThatStartedThisActivity.getStringExtra(IMAGE_POSTER);
            Picasso.with(context).load(image_poster).into(imageView);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
