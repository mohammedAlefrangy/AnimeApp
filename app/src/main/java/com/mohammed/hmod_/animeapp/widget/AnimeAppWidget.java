package com.mohammed.hmod_.animeapp.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import com.mohammed.hmod_.animeapp.Adapter.AdapterForAnime;
import com.mohammed.hmod_.animeapp.DataBase.FavoritesaAnimeEntity;
import com.mohammed.hmod_.animeapp.MainActivity;
import com.mohammed.hmod_.animeapp.R;

import java.util.List;

/**
 * Implementation of App Widget functionality.
 */
public class AnimeAppWidget extends AppWidgetProvider {
    List<FavoritesaAnimeEntity> favMovieEntitiy;
    private AdapterForAnime animeAdapter;
    Context context;
    public static String YOUR_ACTION = "YourAction";
    public RemoteViews remoteViews;

    void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                         int appWidgetId) {

        // Construct the RemoteViews object
        remoteViews = new RemoteViews(context.getPackageName(),
                R.layout.anime_app_widget);

        Intent intent = new Intent(context, MainActivity.class);
        PendingIntent pendingInetent = PendingIntent.getActivity(context, 0, intent, 0);

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, remoteViews);

    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }


}

