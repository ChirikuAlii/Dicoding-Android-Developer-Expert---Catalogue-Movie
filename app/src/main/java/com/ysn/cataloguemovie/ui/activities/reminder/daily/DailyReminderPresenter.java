package com.ysn.cataloguemovie.ui.activities.reminder.daily;

import android.content.Context;

import com.ysn.cataloguemovie.R;
import com.ysn.cataloguemovie.service.reminder.daily.DailyAlarmPreference;
import com.ysn.cataloguemovie.service.reminder.daily.DailyAlarmReceiver;
import com.ysn.cataloguemovie.ui.base.MvpPresenter;

/**
 * Created by root on 07/09/17.
 */

class DailyReminderPresenter implements MvpPresenter<DailyReminderView> {

    private final String TAG = getClass().getSimpleName();
    private DailyReminderView dailyReminderView;

    @Override
    public void onAttach(DailyReminderView mvpView) {
        dailyReminderView = mvpView;
    }

    @Override
    public void onDetach() {
        dailyReminderView = null;
    }

    void onSave(Context context, String strTime) {
        DailyAlarmPreference dailyAlarmPreference = new DailyAlarmPreference(context);
        dailyAlarmPreference.setRepeatingTime(strTime);
        dailyAlarmPreference.setRepeatingMessage(context.getString(R.string.message_daily_reminder));

        DailyAlarmReceiver dailyAlarmReceiver = new DailyAlarmReceiver();
        dailyAlarmReceiver.setRepeatingAlarm(
                context,
                dailyAlarmPreference.getRepeatingTime(),
                dailyAlarmPreference.getRepeatingMessage()
        );
        dailyReminderView.save();
    }

    void onLoadData(Context context) {
        DailyAlarmPreference dailyAlarmPreference = new DailyAlarmPreference(context);
        String time = dailyAlarmPreference.getRepeatingTime();
        dailyReminderView.loadData(time);
    }
}
