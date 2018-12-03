package com.example.hmod_.animeapp.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.RemoteViews;

import com.example.hmod_.animeapp.DataBase.FavoritesaAnimeEntity;
import com.example.hmod_.animeapp.MainActivity;
import com.example.hmod_.animeapp.R;

import java.util.List;

public class FavAppWidgetProvider extends AppWidgetProvider {
    private static final String TAG = "FavAppWidgetProvider";

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager, List<FavoritesaAnimeEntity> favss,
                                int appWidgetId) {
        StringBuilder s = new StringBuilder();


        for (FavoritesaAnimeEntity favs : favss) {

            s.append(favs.getCanonicalTitle() + "\n");
        }

        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.anime_app_widget);


        if (favss != null) {
            if (favss.isEmpty()) {
                views.setViewVisibility(R.id.empty, View.VISIBLE);
                views.setViewVisibility(R.id.listFav, View.INVISIBLE);

            } else {
                views.setViewVisibility(R.id.empty, View.INVISIBLE);
                views.setViewVisibility(R.id.listFav, View.VISIBLE);

                views.setTextViewText(R.id.listFav, s);


            }
        }

        Intent intent = new Intent(context, MainActivity.class);
        PendingIntent pendingInetent = PendingIntent.getActivity(context, 0, intent, 0);
        views.setOnClickPendingIntent(R.id.empty, pendingInetent);
        views.setOnClickPendingIntent(R.id.listFav, pendingInetent);


        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }


    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {

    }

    public static void updateAppWidgetFav(Context context, AppWidgetManager appWidgetManager, List<FavoritesaAnimeEntity> favoritesaAnimeEntities, int[] appWidgetIds) {

        for (int appWidgetId : appWidgetIds) {

            updateAppWidget(context, appWidgetManager, favoritesaAnimeEntities, appWidgetId);
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
