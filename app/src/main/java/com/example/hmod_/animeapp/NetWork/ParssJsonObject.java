package com.example.hmod_.animeapp.NetWork;


import android.util.Log;

import com.example.hmod_.animeapp.DataEntity.Anime;
import com.example.hmod_.animeapp.MainActivity;

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
                anime.setId((String) animeJsonObj.get("id"));


                //get all attributes object
                JSONObject attributes = animeJsonObj.getJSONObject("attributes");
                anime.setSynopsis(attributes.get("synopsis").toString());
                anime.setCanonicalTitle(attributes.get("canonicalTitle").toString());
                anime.setUserCount((Integer) attributes.get("userCount"));
                anime.setFavoritesCount((Integer) attributes.get("favoritesCount"));
                anime.setCreatedAt(attributes.get("createdAt").toString());

                // get posterImage object to get image to show in imageView
                JSONObject posterImage = attributes.getJSONObject("posterImage");
                anime.setPosterImage(String.valueOf(posterImage.get("original")));


                if (MainActivity.urlAnime.equals(NetworkUtils.getAnimeULR())) {
                    anime.setYoutubeVideoId(attributes.get("youtubeVideoId").toString());
//                    anime.setEpisodeCount(attributes.get("episodeCount"));
//                    anime.setEpisodeLength((Integer) attributes.get("episodeLength"));
//                    anime.setTotalLength((Integer) attributes.get("totalLength"));
                    Log.d(TAG, "extractFromJSON: " + attributes.get("youtubeVideoId").toString());

                } else {
                    Log.d(TAG, "extractFromJSON: " + attributes.get("mangaType").toString());
                }

                animes.add(anime);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return animes;
    }
}
