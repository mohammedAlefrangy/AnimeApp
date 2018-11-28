package com.example.hmod_.animeapp.NetWork;


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

                //get all attributes object
                JSONObject attributes = animeJsonObj.getJSONObject("attributes");
                anime.setSynopsis(attributes.get("synopsis").toString());
                anime.setCanonicalTitle(attributes.get("canonicalTitle").toString());
                anime.setAverageRating(attributes.get("averageRating").toString());
                anime.setUserCount((Integer) attributes.get("userCount"));
                anime.setFavoritesCount((Integer) attributes.get("favoritesCount"));
                anime.setAgeRatingGuide(attributes.get("ageRatingGuide").toString());
                anime.setStatus(attributes.get("status").toString());
                anime.setEpisodeCount((Integer) attributes.get("episodeCount"));
                anime.setTotalLength((Integer) attributes.get("totalLength"));
                anime.setYoutubeVideoId(attributes.get("youtubeVideoId").toString());

                // get posterImage object to get image to show in imageView
                JSONObject posterImage = attributes.getJSONObject("posterImage");
                anime.setPosterImage(posterImage.get("original"));

                animes.add(anime);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return animes;
    }
}
