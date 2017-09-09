package com.ysn.cataloguemovie.service.reminder.upcoming;

import android.content.Context;

import com.google.android.gms.gcm.GcmNetworkManager;
import com.google.android.gms.gcm.PeriodicTask;
import com.google.android.gms.gcm.Task;

/**
 * Created by yudisetiawan on 9/10/17.
 */

public class SchedulerTask {

    private GcmNetworkManager gcmNetworkManager;

    public SchedulerTask(Context context) {
        gcmNetworkManager = GcmNetworkManager.getInstance(context);
    }

    public void createPeriodicTask() {
        Task periodicTask = new PeriodicTask.Builder()
                .setService(UpcomingMoviesJobService.class)
                .setPeriod(120)
                .setFlex(10)
                .setTag("UpcomingMovies")
                .setPersisted(true)
                .build();
        gcmNetworkManager.schedule(periodicTask);
    }

    public void cancelPeriodicTask() {
        if (gcmNetworkManager != null) {
            gcmNetworkManager.cancelTask(
                    "UpcomingMovies",
                    UpcomingMoviesJobService.class
            );
        }
    }

}
