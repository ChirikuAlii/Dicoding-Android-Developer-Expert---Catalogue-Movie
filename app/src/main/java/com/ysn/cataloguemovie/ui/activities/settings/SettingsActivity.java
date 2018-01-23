/*
 * Created by Yudi Setiawan on 1/23/18 10:29 PM
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 1/23/18 10:21 PM
 */

package com.ysn.cataloguemovie.ui.activities.settings;

import android.content.Intent;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SwitchCompat;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CompoundButton;

import com.ysn.cataloguemovie.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class SettingsActivity extends AppCompatActivity
        implements SettingsView, CompoundButton.OnCheckedChangeListener {

    private final String TAG = getClass().getSimpleName();
    private SettingsPresenter settingsPresenter;

    @BindView(R.id.switch_compat_daily_reminder_notification_activity_settings)
    SwitchCompat switchCompatDailyReminderNotificationActivitySettings;
    @BindView(R.id.switch_compat_upcoming_reminder_notification_actviity_settings)
    SwitchCompat switchCompatUpcomingReminderNotificationActivitySettings;

    private boolean isDailyReminderNotificationActive;
    private boolean isUpcomingReminderNotificationActive;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        ButterKnife.bind(this);
        initPresenter();
        onAttachView();
        doLoadData();
        initToolbar();
        initListeners();
    }

    private void initPresenter() {
        settingsPresenter = new SettingsPresenter();
    }

    private void initToolbar() {
        getSupportActionBar().setTitle(getString(R.string.settings));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void initListeners() {
        switchCompatDailyReminderNotificationActivitySettings.setOnCheckedChangeListener(this);
        switchCompatUpcomingReminderNotificationActivitySettings.setOnCheckedChangeListener(this);
    }

    private void doLoadData() {
        settingsPresenter.onLoadData(this);
    }

    @Override
    public void onAttachView() {
        settingsPresenter.onAttach(this);
    }

    @Override
    public void onDetachView() {
        settingsPresenter.onDetach();
    }

    @OnClick(R.id.relative_layout_container_settings_locale_activity_settings)
    public void onClick() {
        Intent intentSettingsLocale = new Intent(Settings.ACTION_LOCALE_SETTINGS);
        startActivity(intentSettingsLocale);
    }

    @Override
    public void loadData(boolean isDailyReminderNotificationActive,
                         boolean isUpcomingReminderNotificationActive) {
        this.isDailyReminderNotificationActive = isDailyReminderNotificationActive;
        this.isUpcomingReminderNotificationActive = isUpcomingReminderNotificationActive;

        switchCompatDailyReminderNotificationActivitySettings
                .setChecked(this.isDailyReminderNotificationActive);
        switchCompatUpcomingReminderNotificationActivitySettings
                .setChecked(this.isUpcomingReminderNotificationActive);
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        switch (compoundButton.getId()) {
            case R.id.switch_compat_daily_reminder_notification_activity_settings:
                isDailyReminderNotificationActive = switchCompatDailyReminderNotificationActivitySettings
                        .isChecked();
                settingsPresenter.onUpdateData(
                        isDailyReminderNotificationActive,
                        isUpcomingReminderNotificationActive
                );
                break;
            case R.id.switch_compat_upcoming_reminder_notification_actviity_settings:
                isUpcomingReminderNotificationActive = switchCompatUpcomingReminderNotificationActivitySettings
                        .isChecked();
                settingsPresenter.onUpdateData(
                        isDailyReminderNotificationActive,
                        isUpcomingReminderNotificationActive
                );
                break;
        }
    }

    @Override
    public void updateData() {
        /** nothing to do in here */
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
