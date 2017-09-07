package com.ysn.cataloguemovie.ui.activities.reminder.daily;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import com.ysn.cataloguemovie.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


public class DailyReminderActivity extends AppCompatActivity implements DailyReminderView, View.OnClickListener {

    private final String TAG = getClass().getSimpleName();
    private DailyReminderPresenter dailyReminderPresenter;

    private TextView textViewValueTimeDailyReminderActivity;
    private Button buttonSetTimeDailyReminderActivity;

    private Calendar calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_reminder);
        iniPresenter();
        onAttachView();
        initViews();
        initListeners();
        doLoadData();
    }

    private void doLoadData() {
        dailyReminderPresenter.onLoadData(this);
    }

    private void initListeners() {
        buttonSetTimeDailyReminderActivity.setOnClickListener(this);
    }

    private void initViews() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        textViewValueTimeDailyReminderActivity = (TextView) findViewById(R.id.text_view_value_time_activity_daily_reminder);
        buttonSetTimeDailyReminderActivity = (Button) findViewById(R.id.button_set_time_activity_daily_reminder);
    }

    private void iniPresenter() {
        dailyReminderPresenter = new DailyReminderPresenter();
    }

    @Override
    public void onAttachView() {
        dailyReminderPresenter.onAttach(this);
    }

    @Override
    public void onDetachView() {
        dailyReminderPresenter.onDetach();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_daily_reminder_activity, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            case R.id.menu_item_save_menu_daily_reminder:
                String strTime = textViewValueTimeDailyReminderActivity.getText().toString();
                dailyReminderPresenter.onSave(this, strTime);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_set_time_activity_daily_reminder:
                calendar = Calendar.getInstance();
                TimePickerDialog timePickerDialog = new TimePickerDialog(
                        DailyReminderActivity.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker timePicker, int hour, int minute) {
                                calendar.set(Calendar.HOUR_OF_DAY, hour);
                                calendar.set(Calendar.MINUTE, minute);
                                Date dateSelected = calendar.getTime();
                                textViewValueTimeDailyReminderActivity.setText(
                                        new SimpleDateFormat("HH:mm", Locale.US)
                                                .format(dateSelected)
                                );
                            }
                        },
                        calendar.get(Calendar.HOUR_OF_DAY),
                        calendar.get(Calendar.MINUTE),
                        true
                );
                timePickerDialog.show();
                break;
        }
    }

    @Override
    public void save() {
        /** nothing to do in here */
    }

    @Override
    public void loadData(String time) {
        textViewValueTimeDailyReminderActivity.setText(time);
    }
}
