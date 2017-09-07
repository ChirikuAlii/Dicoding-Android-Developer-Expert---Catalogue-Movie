package com.ysn.cataloguemovie.service.reminder.daily;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by root on 07/09/17.
 */

public class DailyAlarmPreference {

    private final String PREF_NAME = "DailyAlarmPreference";
    private final String KEY_REPEATING_TIME = "repeatingTime";
    private final String KEY_REPEATING_MESSAGE = "repeatingMessage";
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    public DailyAlarmPreference(Context context) {
        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public void setRepeatingTime(String time) {
        editor.putString(KEY_REPEATING_TIME, time);
        editor.commit();
    }

    public String getRepeatingTime() {
        return sharedPreferences.getString(KEY_REPEATING_TIME, null);
    }

    public void setRepeatingMessage(String message) {
        editor.putString(KEY_REPEATING_MESSAGE, message);
        editor.commit();
    }

    public String getRepeatingMessage() {
        return sharedPreferences.getString(KEY_REPEATING_MESSAGE, null);
    }

    public void clear() {
        editor.clear();
        editor.commit();
    }
}
