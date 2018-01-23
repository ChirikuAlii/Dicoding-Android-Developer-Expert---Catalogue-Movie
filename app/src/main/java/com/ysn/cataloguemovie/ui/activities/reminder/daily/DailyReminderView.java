/*
 * Created by Yudi Setiawan on 1/23/18 10:29 PM
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 1/23/18 10:21 PM
 */

package com.ysn.cataloguemovie.ui.activities.reminder.daily;

import com.ysn.cataloguemovie.ui.base.MvpView;

/**
 * Created by root on 07/09/17.
 */

interface DailyReminderView extends MvpView {

    void save();

    void loadData(String time);
}
