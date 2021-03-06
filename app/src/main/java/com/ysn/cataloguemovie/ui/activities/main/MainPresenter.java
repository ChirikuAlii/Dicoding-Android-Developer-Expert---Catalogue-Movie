/*
 * Created by Yudi Setiawan on 1/23/18 10:29 PM
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 1/23/18 10:21 PM
 */

package com.ysn.cataloguemovie.ui.activities.main;

import android.content.Context;

import com.ysn.cataloguemovie.R;
import com.ysn.cataloguemovie.service.reminder.daily.DailyAlarmPreference;
import com.ysn.cataloguemovie.service.reminder.daily.DailyAlarmReceiver;
import com.ysn.cataloguemovie.service.reminder.upcoming.SchedulerTask;
import com.ysn.cataloguemovie.service.setting.SettingsPreference;
import com.ysn.cataloguemovie.ui.base.MvpPresenter;

import java.text.ParseException;
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
        /** Settings configuration */
        SettingsPreference settingsPreference = new SettingsPreference(context);
        boolean isDailyReminderNotificationActive = settingsPreference.getDailyReminderActive();
        boolean isUpcomingReminderNotificationActive = settingsPreference.getUpcomingReminderActive();

        /** Daily Reminder */
        DailyAlarmPreference dailyAlarmPreference = new DailyAlarmPreference(context);
        String time = dailyAlarmPreference.getRepeatingTime();
        if (time == null) {
            Date dateDailyReminderDefault = new Date();
            try {
                dateDailyReminderDefault = new SimpleDateFormat("HH:mm", Locale.US)
                        .parse("12:00");
            } catch (ParseException e) {
                e.printStackTrace();
            }
            dailyAlarmPreference.setRepeatingTime(
                    new SimpleDateFormat("HH:mm", Locale.US)
                            .format(dateDailyReminderDefault)
            );
            dailyAlarmPreference.setRepeatingMessage(
                    context.getString(R.string.message_daily_reminder)
            );
            time = dailyAlarmPreference.getRepeatingTime();
        }

        if (isDailyReminderNotificationActive) {
            DailyAlarmReceiver dailyAlarmReceiver = new DailyAlarmReceiver();
            dailyAlarmReceiver.setRepeatingAlarm(
                    context,
                    time,
                    dailyAlarmPreference.getRepeatingMessage(),
                    false
            );
        }

        /** Upcoming Movies */
        if (isUpcomingReminderNotificationActive) {
            SchedulerTask schedulerTask = new SchedulerTask(context);
            schedulerTask.createPeriodicTask();
        }

        mainView.loadData();
    }
}
