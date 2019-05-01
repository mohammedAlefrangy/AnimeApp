package com.mohammed.hmod_.animeapp.widget;

import android.app.IntentService;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;

import com.mohammed.hmod_.animeapp.DataBase.FavoritesaAnimeEntity;

import java.util.List;


public class AnimeWidgetServices extends IntentService {
    private static final String may = "ahhhhhhhhhhhhhh";

    public static final String ACTION_UPDATE = "com.mohammed.alex.alex_backingapp.action.update_widgets";
    private static List<FavoritesaAnimeEntity> mFav;

    public AnimeWidgetServices() {
        super("AnimeWidgetServices");
    }

    public static void qstartActionUpdateWidgets(Context context, List<FavoritesaAnimeEntity> fav) {
        Log.d(may, "qstartActionUpdateWidgets: " + fav.toString());
        Intent intent = new Intent(context, AnimeWidgetServices.class);
        intent.setAction(ACTION_UPDATE);
        mFav = fav;
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

        Log.d(may, "onHandleIntent: ");
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_UPDATE.equals(action)) {

                handleActionUpdateWidgets(this);

            }

        }


    }

    private void handleActionUpdateWidgets(Context context) {
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this);
        int[] appWidgetIds = appWidgetManager.getAppWidgetIds(new ComponentName(this, FavAppWidgetProvider.class));
        //Now update all widgets
        FavAppWidgetProvider.updateAppWidgetFav(context, appWidgetManager, mFav, appWidgetIds);

    }

}
