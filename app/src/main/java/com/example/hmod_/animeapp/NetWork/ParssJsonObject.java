package com.example.hmod_.animeapp.NetWork;


import android.util.Log;

import com.example.hmod_.animeapp.DataEntity.Anime;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ParssJsonObject {
    private final String json;
    private static final String TAG = "ParssJsonObject";

    public ParssJsonObject(String jsonObject) {
        this.json = jsonObject;
    }

    public List<Anime> extractFromJSON() {
        ArrayList<Anime> animes = new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray resultsJsonObject = jsonObject.getJSONArray("data");
            for (int i = 0; i < resultsJsonObject.length(); i++) {
                Anime anime = new Anime();
                JSONObject animeJsonObj = resultsJsonObject.getJSONObject(i);
                JSONObject attributes = animeJsonObj.getJSONObject("attributes");
                anime.setCanonicalTitle(attributes.get("canonicalTitle").toString());


//                JSONArray attributes = jsonObject.getJSONArray("attributes");
//                JSONObject animeJson = attributes.getJSONObject(i);
//                String attributes = animeJsonObj.get("attributes").toString();
//                anime.setCanonicalTitle(animeJson.get("attributes").toString());
//                anime.setVoteAverage(Double.valueOf(movieJsonObj.get("canonicalTitle").toString()));
//                anime.setTitle(movieJsonObj.get("title").toString());
//                anime.setReleaseDate(movieJsonObj.get("release_date").toString());
//                anime.setPosterPath("https://image.tmdb.org/t/p/w500" + movieJsonObj.get("poster_path").toString());
//                anime.setOverview(movieJsonObj.get("overview").toString());
//                anime.setid(movieJsonObj.get("id").toString());

                animes.add(anime);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return animes;
    }
}
