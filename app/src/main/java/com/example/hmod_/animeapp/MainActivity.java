package com.example.hmod_.animeapp;

import android.app.AlertDialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hmod_.animeapp.Activity.detail_activity;
import com.example.hmod_.animeapp.Activity.dialog_sort;
import com.example.hmod_.animeapp.Adapter.AdapterForAnime;
import com.example.hmod_.animeapp.DataEntity.Anime;
import com.example.hmod_.animeapp.NetWork.NetworkUtils;
import com.example.hmod_.animeapp.NetWork.ParssJsonObject;
import com.example.hmod_.animeapp.fragment.fragment_detail;
import com.example.hmod_.animeapp.fragment.fragment_image;
import com.example.hmod_.animeapp.fragment.fragment_video;
import com.facebook.drawee.backends.pipeline.Fresco;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, AdapterForAnime.OnItemClickListener {
    private DrawerLayout drawer;
    ActionBarDrawerToggle toggle;
    private static final String TAG = "MainActivity";
    ImageView imageView;
    ConnectivityManager connMgr;
    private static final String DEBUG_TAG = "NetworkStatusExample";
    public boolean isWifiConn;
    NetworkInfo networkInfo;
    private NetworkUtils networkHandler;
    public static URL urlAnime;
    private FetchAnimesTask fetchAnimeTask;

    private AdapterForAnime.OnItemClickListener onItemClickListener;
    private RecyclerView recyclerView;
    GridLayoutManager layoutManager;
    private AdapterForAnime animeAdapter;
    private ArrayList<Anime> animes;
    TextView movieTitle;

    SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        onItemClickListener = this;
        sharedPreferences = this.getSharedPreferences("MY_Data", MODE_PRIVATE);

        recyclerView = findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        animes = new ArrayList<>();
        animeAdapter = new AdapterForAnime(animes, getApplicationContext(), onItemClickListener);
        recyclerView.setAdapter(animeAdapter);

        String mSortSettings = sharedPreferences.getString("Sort", "ascending");
        if (mSortSettings.equals("ascending")) {
            Collections.sort(animes, Anime.BY_TITLE_ASCENDING);
        } else if (mSortSettings.equals("descending")) {
            Collections.sort(animes, Anime.BY_TITLE_DESCENDING);
        }

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(getResources().getString(R.string.app_name));
        getSupportActionBar().setHomeButtonEnabled(true);

        //Definition of  the drawerLayout
        drawer = findViewById(R.id.drawerLayout);
        movieTitle = findViewById(R.id.tv_movie_title);

        //Definition of  the ImageView
        imageView = findViewById(R.id.imageView);


        //Definition of  the ActionBarDrawerToggle you can Press it to show drawerLayout
        toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener((NavigationView.OnNavigationItemSelectedListener) this);
        Fresco.initialize(this);

        isNetworkConnected();
        if (isWifiConn == true) {
            getManga();
        } else {
            Toast.makeText(MainActivity.this, "You Should check the internt connection", Toast.LENGTH_SHORT).show();
        }
    }


    private void openMovieDetailsView(int pos) {
        Bundle bundle = null;
        Context context = MainActivity.this;
        Class detailsMovieActivtiy = detail_activity.class;
        Intent intent = new Intent(context, detailsMovieActivtiy);


//        ActivityOptionsCompat  options  = ActivityOptionsCompat.makeSceneTransitionAnimation(this, imageView, imageView.getTransitionName());

        intent.putExtra(detail_activity.IMAGE_POSTER, (String) animes.get(pos).getPosterImage());
//        bundle = ActivityOptionsCompat.makeSceneTransitionAnimation(this).toBundle();
//        context.startActivity(intent, options.toBundle());

        intent.putExtra(detail_activity.CANONICAL_TITLE, animes.get(pos).getCanonicalTitle());

        intent.putExtra(fragment_detail.CANONICAL_TITLE, animes.get(pos).getCanonicalTitle());
        intent.putExtra(fragment_detail.SYNOSIS, animes.get(pos).getSynopsis());
        intent.putExtra(String.valueOf(fragment_detail.USER_FAV), animes.get(pos).getFavoritesCount());
        intent.putExtra(String.valueOf(fragment_detail.USER_COUNT), animes.get(pos).getUserCount());

        intent.putExtra(fragment_video.CANONICAL_TITLE, animes.get(pos).getCanonicalTitle());
        intent.putExtra(fragment_video.VIDEO_ID, animes.get(pos).getYoutubeVideoId());
//        intent.putExtra(DetailsMovieActivtiy.MOVIE_ID, movies.get(pos).getid());

        intent.putExtra(fragment_image.IMAGE_POSTER, (String) animes.get(pos).getPosterImage());
//        ActivityOptionsCompat options = ActivityOptionsCompat.
//                makeSceneTransitionAnimation(this, (View)ivProfile, "profile");
//        startActivity(intent, options.toBundle());

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Log.d(TAG, "openMovieDetailsView: " + "mohavdfsgvzdfbgvzdfmmmed");
            startActivity(intent);
        } else {
            startActivity(intent);
        }

    }

    @Override
    public void onBackPressed() {

        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);

        // Associate searchable configuration with the SearchView
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                animeAdapter.getFilter().filter(newText);
                Log.d(TAG, "onQueryTextChange: " + newText);
                return false;
            }
        });


        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.home) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        } else if (id == R.id.sort) {
            String[] options = {"Ascending", "Descending"};
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Sort By");
            builder.setIcon(R.drawable.ic_sort);
            builder.setItems(options, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    if (i == 0) {
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("Sort", "ascending");
                        editor.apply();
                        recyclerView.setLayoutManager(layoutManager);
                        animeAdapter = new AdapterForAnime(animes, getApplicationContext(), onItemClickListener);
                        recyclerView.setAdapter(animeAdapter);
                    }
                    if (i == 1) {
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("Sort", "descending");
                        editor.apply();
                        recyclerView.setLayoutManager(layoutManager);
                        animeAdapter = new AdapterForAnime(animes, getApplicationContext(), onItemClickListener);
                        recyclerView.setAdapter(animeAdapter);
                    }
                }
            });
            builder.create().show();
            return true;
        }
//        switch (item.getItemId()) {
//            case android.R.id.home:
//                drawer.openDrawer(GravityCompat.START);  // OPEN DRAWER
//                return true;
//        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        toggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        toggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int menuItemThatSelected = item.getItemId();

        switch (menuItemThatSelected) {
            //if click on anime item in menu the app show anime
            case R.id.anime:
                getAnime();
                break;

            //if click on manga item in menu the app show manga
            case R.id.manga:
                getManga();
                break;
            //if click on fav_anime item in menu the app show fav_anime
            case R.id.fav_anime:

                break;

            case R.id.about_me:

                break;
            default:
                Context context2 = MainActivity.this;
                String messagetop2 = "fav_anime clicked";
                Toast.makeText(context2, messagetop2, Toast.LENGTH_LONG).show();
        }

        drawer.closeDrawer(GravityCompat.START);
        return super.onOptionsItemSelected(item);
    }

    public boolean isNetworkConnected() {

        //I got this code from the site developer.android.com
        //use this link to show the wep site https://developer.android.com/training/basics/network-ops/managing
        connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        networkInfo = connMgr.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        isWifiConn = networkInfo.isConnected();
        Log.d(DEBUG_TAG, "Wifi connected: " + isWifiConn);

        return isWifiConn;
    }

    @Override
    public void onListItemClick(int clickedItemIndex) {
        Log.d(TAG, "onListItemClick: " + animes.get(clickedItemIndex).getYoutubeVideoId());
        openMovieDetailsView(clickedItemIndex);
    }

    private void getAnime() {

        isNetworkConnected();
        if (isWifiConn == true) {
            networkHandler = new NetworkUtils();
            urlAnime = networkHandler.getAnimeULR();
            Log.d(TAG, "get: " + urlAnime);
            fetchAnimeTask = new FetchAnimesTask();
            fetchAnimeTask.execute();
        } else {
            Toast.makeText(MainActivity.this, "You Should check the internt connection", Toast.LENGTH_SHORT).show();
        }
    }

    private void getManga() {

        isNetworkConnected();
        if (isWifiConn == true) {
            networkHandler = new NetworkUtils();
            urlAnime = networkHandler.getMangaULR();
            Log.d(TAG, "get: " + urlAnime);
            fetchAnimeTask = new FetchAnimesTask();
            fetchAnimeTask.execute();
        } else {
            Toast.makeText(MainActivity.this, "You Should check the internt connection", Toast.LENGTH_SHORT).show();
        }
    }


    class FetchAnimesTask extends AsyncTask<String, Void, ArrayList<Anime>> {
        ArrayList<Anime> arrayList;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            animeAdapter.clear();
        }

        @Override
        protected ArrayList<Anime> doInBackground(String... strings) {


            String jsonResponse = null;
            try {
                jsonResponse = NetworkUtils.getResponseFromHttpUrl(urlAnime);
                ParssJsonObject parssJsonObject = new ParssJsonObject(jsonResponse);
                arrayList = (ArrayList<Anime>) parssJsonObject.extractFromJSON();

            } catch (IOException e) {
                e.printStackTrace();
            }
            return arrayList;
        }

        @Override
        protected void onPostExecute(ArrayList<Anime> animeFilm) {
            Log.d(TAG, "onPostExecute: " + animeFilm);
            isNetworkConnected();
            if (isWifiConn == true) {
                super.onPostExecute(animeFilm);
                animes.addAll(animeFilm);
                Log.d(TAG, "onPostExecute: " + animes);
                if (animeFilm != null) {
                    animeAdapter.setTasks(animes);
//                    recyclerView.scrollToPosition(mCurrentPostion);
//                    movieAdapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(MainActivity.this, "You Should check the internt connection", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

}
