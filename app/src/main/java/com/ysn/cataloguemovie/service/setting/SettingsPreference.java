package com.ysn.cataloguemovie.service.setting;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by yudisetiawan on 9/17/17.
 */

public class SettingsPreference {

    private final String PREF_NAME = "SettingsPreference";
    private final String KEY_DAILY_REMINDER_ACTIVE = "dailyReminderActive";
    private final String KEY_UPCOMING_REMINDER_ACTIVE = "upcomingReminderActive";
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    public SettingsPreference(Context context) {
        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public void setDailyReminderActive(boolean isDailyReminderActive) {
        editor.putBoolean(KEY_DAILY_REMINDER_ACTIVE, isDailyReminderActive);
        editor.commit();
    }

    public boolean getDailyReminderActive() {
        return sharedPreferences.getBoolean(KEY_DAILY_REMINDER_ACTIVE, false);
    }

    public void setUpcomingReminderActive(boolean isUpcomingReminderActive) {
        editor.putBoolean(KEY_UPCOMING_REMINDER_ACTIVE, isUpcomingReminderActive);
        editor.commit();
    }

    public boolean getUpcomingReminderActive() {
        return sharedPreferences.getBoolean(KEY_UPCOMING_REMINDER_ACTIVE, false);
    }

    public void clear() {
        editor.clear();
        editor.commit();
    }

}
