package com.example.hmod_.animeapp;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
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
import android.widget.Toast;

import com.example.hmod_.animeapp.Activity.dialog_sort;
import com.example.hmod_.animeapp.Adapter.AdapterForAnime;
import com.example.hmod_.animeapp.DataEntity.Anime;
import com.example.hmod_.animeapp.NetWork.NetworkUtils;
import com.example.hmod_.animeapp.NetWork.ParssJsonObject;
import com.facebook.drawee.backends.pipeline.Fresco;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, AdapterForAnime.OnItemClickListener {
    private DrawerLayout drawer;
    ActionBarDrawerToggle toggle;
    private static final String TAG = "MainActivity";

    ConnectivityManager connMgr;
    private static final String DEBUG_TAG = "NetworkStatusExample";
    private boolean isWifiConn;
    NetworkInfo networkInfo;
    private NetworkUtils networkHandler;
    private URL url;
    private FetchAnimesTask fetchAnimeTask;

    private AdapterForAnime.OnItemClickListener onItemClickListener;
    private RecyclerView recyclerView;
    GridLayoutManager layoutManager;
    private AdapterForAnime animeAdapter;
    private ArrayList<Anime> animes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        onItemClickListener = this;

        recyclerView = findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        animes = new ArrayList<>();
        animeAdapter = new AdapterForAnime(animes, getApplicationContext(), onItemClickListener);
        recyclerView.setAdapter(animeAdapter);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(getResources().getString(R.string.app_name));
        getSupportActionBar().setHomeButtonEnabled(true);

        //Definition of  the drawerLayout
        drawer = findViewById(R.id.drawerLayout);

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
            getAnime();
        } else {
            Toast.makeText(MainActivity.this, "You Should check the internt connection", Toast.LENGTH_SHORT).show();
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
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setIconifiedByDefault(false);


//        searchView.setOnQueryTextListener(queryTextListener);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.home) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        } else if (id == R.id.sort) {
            dialog_sort dialogSort = new dialog_sort();
            dialogSort.show(getSupportFragmentManager(), "Sort For Anime");
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
        int id = item.getItemId();

        if (id == R.id.about_me) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private boolean isNetworkConnected() {

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
//        openMovieDetailsView(clickedItemIndex);
    }

    private void getAnime() {

        isNetworkConnected();
        if (isWifiConn == true) {
            networkHandler = new NetworkUtils();
            url = networkHandler.getAnimeULR();
            Log.d(TAG, "getAnime: " + url);
            fetchAnimeTask = new FetchAnimesTask();
            fetchAnimeTask.execute();
        } else {
            Toast.makeText(MainActivity.this, "You Should check the internt connection", Toast.LENGTH_SHORT).show();
        }
    }



    class FetchAnimesTask extends AsyncTask<String, Void, ArrayList<Anime>>{
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
                jsonResponse = NetworkUtils.getResponseFromHttpUrl(url);
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
