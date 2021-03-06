package com.mohammed.hmod_.animeapp.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.mohammed.hmod_.animeapp.DataBase.FavoritesMoviesDatabase;
import com.mohammed.hmod_.animeapp.DataBase.FavoritesaAnimeEntity;
import com.mohammed.hmod_.animeapp.R;
import com.mohammed.hmod_.animeapp.fragment.DetailsFragment;
import com.mohammed.hmod_.animeapp.fragment.ImageFragment;
import com.mohammed.hmod_.animeapp.fragment.VideoFragment;
import com.squareup.picasso.Picasso;

public class detail_activity extends AppCompatActivity {
    public static final String IMAGE_POSTER = "image_poster";
    public static final String CANONICAL_TITLE = "canonical_title";
    public static final String MOVIE_ID = "movie_id";
    public static final String SYNOSIS = "synopsis";
    public static final String CREATED_AT = "created_at";
    public static final String USER_FAV = "user_fav";
    public static final String USER_COUNT = "user_count";
    private boolean isButtonClicked = true; // You should add a boolean flag to record the button on/off state
    private FavoritesMoviesDatabase mDb;


    private String image_poster;
    private String title, synopsis, createdAt;
    private Integer userFav, userCount;
    private String movieID;
    private Context context;
    private FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        mDb = FavoritesMoviesDatabase.getsInstance(getApplicationContext());
        Intent intentThatStartedThisActivity = getIntent();

        Toolbar toolbar = findViewById(R.id.toolbar);
        CollapsingToolbarLayout collapsingToolbarLayout = findViewById(R.id.toolbar_layout);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        ImageView imageview_poster = findViewById(R.id.imageView);
        floatingActionButton = findViewById(R.id.floatingActionButoom);

        if (intentThatStartedThisActivity != null) {
            title = intentThatStartedThisActivity.getStringExtra(CANONICAL_TITLE);
            toolbar.setTitle(title);
            collapsingToolbarLayout.setTitle(intentThatStartedThisActivity.getStringExtra(CANONICAL_TITLE));
            collapsingToolbarLayout.setExpandedTitleColor(getResources().getColor(android.R.color.transparent));
            movieID = intentThatStartedThisActivity.getStringExtra(MOVIE_ID);
            synopsis = intentThatStartedThisActivity.getStringExtra(SYNOSIS);
            createdAt = intentThatStartedThisActivity.getStringExtra(CREATED_AT);
            userFav = intentThatStartedThisActivity.getIntExtra(USER_FAV, 0);
            userCount = intentThatStartedThisActivity.getIntExtra(USER_COUNT, 0);

            image_poster = intentThatStartedThisActivity.getStringExtra(IMAGE_POSTER);
            Picasso.with(context).load(image_poster).into(imageview_poster);
        }
        ViewPager viewPager = findViewById(R.id.viewPager);
        PagerAdapter pagerAdapter = new ShowsFragmentPagerAdapter(getSupportFragmentManager(), this);
        viewPager.setAdapter(pagerAdapter);

        TabLayout tabLayout = findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);

        if (mDb.favoritesMovieDao().loadMovieById(Integer.parseInt(movieID)) == null) {
            floatingActionButton.setImageResource(R.drawable.ic_heart);
            isButtonClicked = false;
        } else {
            floatingActionButton.setImageResource(R.drawable.ic_heartf);
            isButtonClicked = true;

        }

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

    public void favImageClicked(View view) {

        if (isButtonClicked) {
            mDb.favoritesMovieDao().deleteMovie(mDb.favoritesMovieDao().loadMovieById(Integer.parseInt(movieID)));
            floatingActionButton.setImageResource(R.drawable.ic_heart);
            isButtonClicked = true;


        } else {
            mDb.favoritesMovieDao().insertMovie(new FavoritesaAnimeEntity(movieID, image_poster,title, synopsis,createdAt, userCount, userFav ));
            floatingActionButton.setImageResource(R.drawable.ic_heartf);
            isButtonClicked = false;
        }
        finish();

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
                    return new DetailsFragment();
                case 1:
                    return new VideoFragment();
                case 2:
                    return new ImageFragment();
                default:
                    return new DetailsFragment();
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
