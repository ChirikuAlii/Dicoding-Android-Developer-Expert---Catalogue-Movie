/*
 * Created by Yudi Setiawan on 1/23/18 10:29 PM
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 1/23/18 10:21 PM
 */

package com.ysn.cataloguemovie.ui.activities.settings;

import android.content.Context;

import com.ysn.cataloguemovie.service.reminder.daily.DailyAlarmPreference;
import com.ysn.cataloguemovie.service.reminder.daily.DailyAlarmReceiver;
import com.ysn.cataloguemovie.service.reminder.upcoming.SchedulerTask;
import com.ysn.cataloguemovie.service.setting.SettingsPreference;
import com.ysn.cataloguemovie.ui.base.MvpPresenter;

import butterknife.OnClick;

/**
 * Created by yudisetiawan on 9/17/17.
 */

public class SettingsPresenter implements MvpPresenter<SettingsView> {

    private final String TAG = getClass().getSimpleName();
    private SettingsView settingsView;
    private SettingsPreference settingsPreference;
    private Context context;

    @Override
    public void onAttach(SettingsView mvpView) {
        settingsView = mvpView;
    }

    @Override
    public void onDetach() {
        settingsView = null;
    }

    void onLoadData(Context context) {
        this.context = context;
        boolean isDailyReminderNotificationActive;
        boolean isUpcomingReminderNotificationActive;

        settingsPreference = new SettingsPreference(context);
        isDailyReminderNotificationActive = settingsPreference.getDailyReminderActive();
        isUpcomingReminderNotificationActive = settingsPreference.getUpcomingReminderActive();

        settingsView.loadData(
                isDailyReminderNotificationActive,
                isUpcomingReminderNotificationActive
        );
    }

    void onUpdateData(boolean isDailyReminderNotificationActive,
                      boolean isUpcomingReminderNotificationActive) {
        settingsPreference.setDailyReminderActive(isDailyReminderNotificationActive);
        settingsPreference.setUpcomingReminderActive(isUpcomingReminderNotificationActive);

        /** Daily Reminder Notification */
        DailyAlarmPreference dailyAlarmPreference = new DailyAlarmPreference(context);
        DailyAlarmReceiver dailyAlarmReceiver = new DailyAlarmReceiver();
        if (isDailyReminderNotificationActive) {
            String time = dailyAlarmPreference.getRepeatingTime();
            String message = dailyAlarmPreference.getRepeatingMessage();
            dailyAlarmReceiver.setRepeatingAlarm(
                    context,
                    time,
                    message,
                    true);
        } else {
            dailyAlarmReceiver.cancelAlarm(context);
        }

        /** Upcoming Reminder Notification */
        SchedulerTask schedulerTask = new SchedulerTask(context);
        if (isUpcomingReminderNotificationActive) {
            schedulerTask.cancelPeriodicTask();
            schedulerTask.createPeriodicTask();
        } else {
            schedulerTask.cancelPeriodicTask();
        }

        settingsView.updateData();
    }
}
