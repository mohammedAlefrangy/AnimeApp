/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.hmod_.animeapp.NetWork;

import android.net.Uri;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;


public final class NetworkUtils {


    private final static String ANIME_BASE_URL = "https://kitsu.io/api/edge";
    private final static String MANGA = "/manga/";
    private final static String ANIME = "/anime/";

    private static final String API_KEY_PARAM = "api_key";
    //TODO (1) you should create account in https://apiary.io/ and get your api_ket and insert here
    //TODO if you don't need create account you can use this api "366cf8abb96a69210ca41107bcbb421e"
    private static final String KEY = "366cf8abb96a69210ca41107bcbb421e";

    public NetworkUtils() {
    }


    public static URL getAnimeULR() {
        Uri builtUri = Uri.parse(ANIME_BASE_URL + ANIME).buildUpon()
                .appendQueryParameter(API_KEY_PARAM, KEY)
                .build();

        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return url;
    }

    public URL getMangaULR() {
        Uri builtUri = Uri.parse(ANIME_BASE_URL + MANGA).buildUpon()
                .appendQueryParameter(API_KEY_PARAM, KEY)
                .build();

        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return url;
    }

    /**
     * This method returns the entire result from the HTTP response.
     *
     * @param url The URL to fetch the HTTP response from.
     * @return The contents of the HTTP response.
     * @throws IOException Related to network and stream reading
     */
    public static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }
}