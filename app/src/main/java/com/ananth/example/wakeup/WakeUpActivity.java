package com.ananth.example.wakeup;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Calendar;

public class WakeUpActivity extends FragmentActivity {

    private EditText mRemainder;
    private Button mTimePicker;
    private TextView mTimeSet;
    private Button mSetAlarm;
    public static int Hour = 0;
    public static int Min = 0;
    public static int mday = 0;
    public static int mmonth = 0;
    public static int myear = 0;
    SampleAlarmReceiver alarm = new SampleAlarmReceiver();
    private Button mDatePicker;
    private TextView mDateSet;
    public static String mReaminderVal = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wake_up);

        mRemainder = (EditText) findViewById(R.id.remaindedt);
        mTimePicker = (Button) findViewById(R.id.timepicker);
        mDatePicker = (Button) findViewById(R.id.datepicker);
        mTimeSet = (TextView) findViewById(R.id.timetext);
        mDateSet = (TextView) findViewById(R.id.datetext);
        mSetAlarm = (Button) findViewById(R.id.setAlarm);
        mSetAlarm.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                mReaminderVal = mRemainder.getText().toString().trim();

                if (TextUtils.isEmpty(mReaminderVal)) {
                    Toast.makeText(WakeUpActivity.this, "Write your remainder!", Toast.LENGTH_SHORT).show();
                } else {
                    Utils.mMes = mReaminderVal;
                    Utils.saveData1("remaind_word", Utils.mMes,WakeUpActivity.this);
                    alarm.setAlarm(WakeUpActivity.this);
                    finish();
                }

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.wake_up, menu);
        return true;
    }

    public void showTimePickerDialog(View v) {
        DialogFragment newFragment = new TimePicker();
        newFragment.show(getSupportFragmentManager(), "TimePicker");
    }

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();

    }

    public class TimePicker extends DialogFragment implements
            TimePickerDialog.OnTimeSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current time as the default values for the picker
            final Calendar c = Calendar.getInstance();
            int hour = c.get(Calendar.HOUR_OF_DAY);
            int minute = c.get(Calendar.MINUTE);

            // Create a new instance of TimePickerDialog and return it
            return new TimePickerDialog(getActivity(), this, hour, minute,
                    DateFormat.is24HourFormat(getActivity()));
        }

        @Override
        public void onTimeSet(android.widget.TimePicker view, int hourofday,
                              int minute) {
            // TODO Auto-generated method stub
            Hour = hourofday;
            Min = minute;
            mTimeSet.setText("" + Hour + " : " + Min);

        }

    }

    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePicker();
        newFragment.show(getSupportFragmentManager(), "DatePicker");
    }

    public class DatePicker extends DialogFragment implements
            DatePickerDialog.OnDateSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // TODO Auto-generated method stub

            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);
            return new DatePickerDialog(getActivity(), this, year, month, day);
        }

        @Override
        public void onDateSet(android.widget.DatePicker view, int year,
                              int month, int day) {
            // TODO Auto-generated method stub
            mday = day;
            mmonth = month;
            myear = year;
            month = month + 1;
            mDateSet.setText(mday + "-" + month + "-" + myear);

        }

    }

}
