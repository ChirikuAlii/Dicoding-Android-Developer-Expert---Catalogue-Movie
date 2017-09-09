package com.ysn.cataloguemovie.ui.activities.main;

import android.content.Context;

import com.ysn.cataloguemovie.R;
import com.ysn.cataloguemovie.service.reminder.daily.DailyAlarmPreference;
import com.ysn.cataloguemovie.service.reminder.daily.DailyAlarmReceiver;
import com.ysn.cataloguemovie.service.reminder.upcoming.SchedulerTask;
import com.ysn.cataloguemovie.service.reminder.upcoming.UpcomingMoviesJobService;
import com.ysn.cataloguemovie.ui.base.MvpPresenter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by root on 30/08/17.
 */

public class MainPresenter implements MvpPresenter<MainView> {

    private final String TAG = getClass().getSimpleName();
    private MainView mainView;

    @Override
    public void onAttach(MainView mvpView) {
        mainView = mvpView;
    }

    @Override
    public void onDetach() {
        mainView = null;
    }

    void onLoadData(Context context) {
        /** Daily Reminder */
        DailyAlarmPreference dailyAlarmPreference = new DailyAlarmPreference(context);
        String time = dailyAlarmPreference.getRepeatingTime();
        if (time == null) {
            dailyAlarmPreference.setRepeatingTime(
                    new SimpleDateFormat("HH:mm", Locale.US)
                            .format(new Date())
            );
            dailyAlarmPreference.setRepeatingMessage(
                    context.getString(R.string.message_daily_reminder)
            );
            time = dailyAlarmPreference.getRepeatingTime();
        }

        DailyAlarmReceiver dailyAlarmReceiver = new DailyAlarmReceiver();
        dailyAlarmReceiver.setRepeatingAlarm(
                context,
                time,
                dailyAlarmPreference.getRepeatingMessage()
        );

        /** Upcoming Movies */
        SchedulerTask schedulerTask = new SchedulerTask(context);
        schedulerTask.createPeriodicTask();

        mainView.loadData();
    }
}
