package com.example.hmod_.animeapp.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;

import com.example.hmod_.animeapp.R;
import com.example.hmod_.animeapp.fragment.fragment_detail;
import com.example.hmod_.animeapp.fragment.fragment_image;
import com.example.hmod_.animeapp.fragment.fragment_video;
import com.squareup.picasso.Picasso;

public class detail_activity extends AppCompatActivity {
    public static final String IMAGE_POSTER = "imageview_poster";
    public static final String CANONICAL_TITLE = "canonical_title";


    String image_poster;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Intent intentThatStartedThisActivity = getIntent();

        Toolbar toolbar = findViewById(R.id.toolbar);
        CollapsingToolbarLayout collapsingToolbarLayout = findViewById(R.id.toolbar_layout);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        ImageView imageview_poster = findViewById(R.id.imageView);

        if (intentThatStartedThisActivity != null) {
            toolbar.setTitle(intentThatStartedThisActivity.getStringExtra(CANONICAL_TITLE));
            collapsingToolbarLayout.setTitle(intentThatStartedThisActivity.getStringExtra(CANONICAL_TITLE));
            collapsingToolbarLayout.setExpandedTitleColor(getResources().getColor(android.R.color.transparent));


            image_poster = intentThatStartedThisActivity.getStringExtra(IMAGE_POSTER);
            Picasso.with(context).load(image_poster).into(imageview_poster);
        }
        ViewPager viewPager = findViewById(R.id.viewPager);
        PagerAdapter pagerAdapter = new ShowsFragmentPagerAdapter(getSupportFragmentManager(), this);
        viewPager.setAdapter(pagerAdapter);

        TabLayout tabLayout = findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                supportFinishAfterTransition();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    private class ShowsFragmentPagerAdapter extends FragmentStatePagerAdapter {

        private Context context;

        public ShowsFragmentPagerAdapter(FragmentManager fm, Context context) {
            super(fm);
            this.context = context;
        }

        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new fragment_detail();
                case 1:
                    return new fragment_video();
                case 2:
                    return new fragment_image();
                default:
                    return new fragment_detail();
            }

        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return getString(R.string.detail);
                case 1:
                    return getString(R.string.videos);
                case 2:
                    return getString(R.string.images);
                default:
                    return getString(R.string.detail);
            }
        }
    }
}
