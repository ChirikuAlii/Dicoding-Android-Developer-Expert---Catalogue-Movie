package com.ysn.cataloguemovie.widgets;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.Toast;

import com.ysn.cataloguemovie.R;
import com.ysn.cataloguemovie.model.movie.detail.DetailMovie;
import com.ysn.cataloguemovie.ui.activities.detail.DetailMovieActivity;

import java.util.Arrays;

/**
 * Implementation of App Widget functionality.
 */
public class FavoriteMovieWidget extends AppWidgetProvider {

    private final String TAG = getClass().getSimpleName();
    public static final String TOAST_ACTION = "TOAST_ACTION";
    public static final String EXTRA_ITEM = "EXTRA_ITEM";

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {
        Log.d("FavoriteMovieWidget", "updateAppWidget");
        Intent intent = new Intent(context, StackWidgetService.class);
        intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
        intent.setData(Uri.parse(intent.toUri(Intent.URI_INTENT_SCHEME)));

        RemoteViews remoteViews = new RemoteViews(
                context.getPackageName(),
                R.layout.favorite_movie_widget
        );
        remoteViews.setRemoteAdapter(R.id.stack_view, intent);
        remoteViews.setEmptyView(R.id.stack_view, R.id.text_view_empty_view_favorite_movie_wdiget);

        Intent toastIntent = new Intent(context, FavoriteMovieWidget.class);
        toastIntent.setAction(FavoriteMovieWidget.TOAST_ACTION);
        toastIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);

        intent.setData(Uri.parse(intent.toUri(Intent.URI_INTENT_SCHEME)));
        PendingIntent toastPendingIntent = PendingIntent.getBroadcast(
                context,
                0,
                toastIntent,
                PendingIntent.FLAG_UPDATE_CURRENT
        );
        remoteViews.setPendingIntentTemplate(
                R.id.stack_view,
                toastPendingIntent
        );
        appWidgetManager.notifyAppWidgetViewDataChanged(
                appWidgetId,
                R.id.stack_view
        );
        appWidgetManager.updateAppWidget(appWidgetId, remoteViews);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        Log.d(TAG, "appWidgetIds: " + Arrays.toString(appWidgetIds));
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

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "onReceive");
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
        if (intent.getAction().equals(TOAST_ACTION)) {
            int appWidgetId = intent.getIntExtra(
                    AppWidgetManager.EXTRA_APPWIDGET_ID,
                    AppWidgetManager.INVALID_APPWIDGET_ID
            );
            /*int viewIndex = intent.getIntExtra(EXTRA_ITEM, 0);
            Toast.makeText(
                    context,
                    "Touched view " + viewIndex,
                    Toast.LENGTH_LONG
            ).show();*/

            Intent detailMovieActivity = new Intent(context, DetailMovieActivity.class);
            long idMovie = intent.getLongExtra(EXTRA_ITEM, 0L);
            detailMovieActivity.putExtra("idMovie", idMovie);
            context.startActivity(detailMovieActivity);

        } else if (intent.getAction().equals(AppWidgetManager.ACTION_APPWIDGET_UPDATE)){
            ComponentName thisAppWidget = new ComponentName(
                    context.getPackageName(),
                    FavoriteMovieWidget.class.getName()
            );
            int[] appWidgetIds = appWidgetManager.getAppWidgetIds(thisAppWidget);
            appWidgetManager.notifyAppWidgetViewDataChanged(
                    appWidgetIds,
                    R.id.stack_view
            );
        }
        super.onReceive(context, intent);
    }
}

