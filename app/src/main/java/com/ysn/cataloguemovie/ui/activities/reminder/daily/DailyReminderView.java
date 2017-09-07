package com.ysn.cataloguemovie.ui.activities.reminder.daily;

import com.ysn.cataloguemovie.ui.base.MvpView;

/**
 * Created by root on 07/09/17.
 */

interface DailyReminderView extends MvpView {

    void save();

    void loadData(String time);
}
