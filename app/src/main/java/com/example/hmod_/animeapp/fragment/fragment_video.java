package com.example.hmod_.animeapp.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hmod_.animeapp.Activity.detail_activity;
import com.example.hmod_.animeapp.MainActivity;
import com.example.hmod_.animeapp.R;

import java.util.Objects;

public class fragment_video extends Fragment {
    Intent intentThatStartedThisActivity;
    public static final String VIDEO_ID = "videoID";
    public static final String CANONICAL_TITLE = "cononical_title";
    private TextView videoName;
    private CollapsingToolbarLayout collapsingToolbar;
    private View cordinatorLayout;
    private static final String TRAILER_URL = "https://www.youtube.com/watch?v=";
    MainActivity mainActivity;
    String videoId;

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
        View view = inflater.inflate(R.layout.activity_fragment_video, container, false);
        intentThatStartedThisActivity = Objects.requireNonNull(getActivity()).getIntent();

        Objects.requireNonNull(((detail_activity) getActivity()).getSupportActionBar()).setHomeButtonEnabled(true);
        Objects.requireNonNull(((detail_activity) getActivity()).getSupportActionBar()).setDisplayHomeAsUpEnabled(true);


        collapsingToolbar = getActivity().findViewById(R.id.toolbar_layout);
        LinearLayout detailLayout = view.findViewById(R.id.detail_layout);
        CardView infoCardView = view.findViewById(R.id.info_card_view);
        TextView title = view.findViewById(R.id.title);
        cordinatorLayout = getActivity().findViewById(R.id.app_bar);

        videoName = view.findViewById(R.id.video_name);


        if (intentThatStartedThisActivity != null) {
            cordinatorLayout.setVisibility(View.VISIBLE);
            boolean threadAlreadyRunning = false;
            videoName.setText(intentThatStartedThisActivity.getStringExtra(CANONICAL_TITLE));
            videoId = intentThatStartedThisActivity.getStringExtra(VIDEO_ID);

            videoName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (videoId != null) {
                        openVedio();
                    } else {
                        Toast.makeText(getContext(), "This element does not contain video because it is manga ", Toast.LENGTH_SHORT).show();
                    }

                }
            });
        }


        return view;
    }

    private void openVedio() {
//
        final String trailerURL = TRAILER_URL + videoId;
        Intent youtubeIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(trailerURL));
        if (youtubeIntent.resolveActivity(Objects.requireNonNull(getActivity()).getPackageManager()) != null) {
            startActivity(youtubeIntent);
        }
    }


}
