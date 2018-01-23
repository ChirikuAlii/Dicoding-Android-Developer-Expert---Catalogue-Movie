/*
 * Created by Yudi Setiawan on 1/23/18 10:29 PM
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 1/23/18 10:21 PM
 */

package com.ysn.cataloguemovie.ui.activities.settings;

import com.ysn.cataloguemovie.ui.base.MvpView;

/**
 * Created by yudisetiawan on 9/17/17.
 */

interface SettingsView extends MvpView {

    void loadData(boolean isDailyReminderNotificationActive, boolean isUpcomingReminderNotificationActive);

    void updateData();

}
