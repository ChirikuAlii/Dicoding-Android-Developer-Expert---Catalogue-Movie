package com.ysn.cataloguemovie.ui.activities.settings;

import com.ysn.cataloguemovie.ui.base.MvpView;

/**
 * Created by yudisetiawan on 9/17/17.
 */

interface SettingsView extends MvpView {

    void loadData(boolean isDailyReminderNotificationActive, boolean isUpcomingReminderNotificationActive);

    void updateData();

}
