package com.example.hmod_.animeapp.fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.hmod_.animeapp.Activity.detail_activity;
import com.example.hmod_.animeapp.R;

public class fragment_detail extends Fragment {
    public static final String PREFERENCE_NAME = "com.example.hmod_.animeapp";
    private CollapsingToolbarLayout collapsingToolbar;
    private TextView cononical_title, synopsis, created_at, user_fav, user_count;
    private View cordinatorLayout;
    private SharedPreferences prefs;
    Intent intentThatStartedThisActivity ;

    public static final String SYNOSIS = "synosis";
    public static final String CANONICAL_TITLE = "cononical_title";
    public static final String CREATED_AT = "created_at";
    public static final Integer USER_FAV = 1;
    public static final Integer USER_COUNT = 1;


    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_fragment_detail, container, false);
        intentThatStartedThisActivity = getActivity().getIntent();

        ((detail_activity) getActivity()).getSupportActionBar().setHomeButtonEnabled(true);
        ((detail_activity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        collapsingToolbar = getActivity().findViewById(R.id.toolbar_layout);
        LinearLayout detailLayout = rootView.findViewById(R.id.detail_layout);
        CardView infoCardView = rootView.findViewById(R.id.info_card_view);
        TextView title = rootView.findViewById(R.id.title);

        cononical_title = rootView.findViewById(R.id.cononical_title);
        synopsis = rootView.findViewById(R.id.synopsis);
//        created_at = rootView.findViewById(R.id.created_at);
        user_fav = rootView.findViewById(R.id.user_fav);
        user_count = rootView.findViewById(R.id.user_count);

        cordinatorLayout = getActivity().findViewById(R.id.app_bar);
//        prefs = getActivity().getSharedPreferences(
//                PREFERENCE_NAME, Context.MODE_PRIVATE);


        if (intentThatStartedThisActivity != null) {
            loadMovieDetails();
        }

        return rootView;
    }


    public void loadMovieDetails() {

        try {
            cordinatorLayout.setVisibility(View.VISIBLE);
            boolean threadAlreadyRunning = false;
//            collapsingToolbar.setTitle(intentThatStartedThisActivity.getStringExtra(CONONICAL_TITLE));

            cononical_title.setText(getString(R.string.cononical_title) + ": " + intentThatStartedThisActivity.getStringExtra(CANONICAL_TITLE));
            synopsis.setText(getString(R.string.synopsis)+":\n\n" + intentThatStartedThisActivity.getStringExtra(SYNOSIS));
//            created_at.setText(getString(R.string.created_at) + DateTimeHelper.parseDate(intentThatStartedThisActivity.getStringExtra(CREATED_AT)) + "");
            user_fav.setText(getString(R.string.user_fav) + ": " + intentThatStartedThisActivity.getIntExtra(String.valueOf(USER_FAV), 0));
            user_count.setText(getString(R.string.user_count) + ": " + intentThatStartedThisActivity.getIntExtra(String.valueOf(USER_COUNT), 0));

        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
