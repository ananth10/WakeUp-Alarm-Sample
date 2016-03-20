package com.ananth.example.wakeup;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.content.WakefulBroadcastReceiver;
import android.util.Log;
import android.widget.Toast;

import java.util.Calendar;

public class SampleAlarmReceiver extends WakefulBroadcastReceiver {

	public AlarmManager mAlarmManager;
	public static PendingIntent mPentIntent;

	@Override
	public void onReceive(Context context, Intent arg1) {
		// TODO Auto-generated method stub

		// Intent service = new Intent(context, SampleSchedulingService.class);
		//
		// // Start the service, keeping the device awake while it is launching.
		// startWakefulService(context, service);

		context.startActivity(new Intent(context, DialogActivity.class)
				.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));

	}

	public void setAlarm(Context context) {
		mAlarmManager = (AlarmManager) context
				.getSystemService(Context.ALARM_SERVICE);
		Intent intent = new Intent(context, SampleAlarmReceiver.class);
		mPentIntent = PendingIntent.getBroadcast(context, 0, intent, 0);

		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(System.currentTimeMillis());
		calendar.set(Calendar.YEAR, WakeUpActivity.myear);
		calendar.set(Calendar.MONTH, WakeUpActivity.mmonth);
		calendar.set(Calendar.DAY_OF_MONTH, WakeUpActivity.mday);
		calendar.set(Calendar.HOUR_OF_DAY, WakeUpActivity.Hour);
		calendar.set(Calendar.MINUTE, WakeUpActivity.Min);

		mAlarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP,
				calendar.getTimeInMillis(), 60 * 1000, mPentIntent);
		Toast.makeText(context, "Remainder set successfully",
				Toast.LENGTH_SHORT).show();

		ComponentName com = new ComponentName(context, SampleBootReceiver.class);
		PackageManager pm = context.getPackageManager();
		pm.setComponentEnabledSetting(com,
				PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
				PackageManager.DONT_KILL_APP);
	}

	public void cancelAlarm(Context context) {
		Log.i("canceld", "canceld11");
		// Toast.makeText(context, "Alarm canceled", Toast.LENGTH_SHORT).show();
		mAlarmManager = (AlarmManager) context
				.getSystemService(Context.ALARM_SERVICE);
		Intent intent = new Intent(context, SampleAlarmReceiver.class);
		mPentIntent = PendingIntent.getBroadcast(context, 0, intent, 0);

		if (mAlarmManager != null) {
			Toast.makeText(context, "Alarm canceled", Toast.LENGTH_SHORT)
					.show();
			mAlarmManager.cancel(mPentIntent);
		}
		// mPentIntent.cancel();

		ComponentName com = new ComponentName(context, SampleBootReceiver.class);
		PackageManager pm = context.getPackageManager();
		pm.setComponentEnabledSetting(com,
				PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
				PackageManager.DONT_KILL_APP);
	}

	public void call(Context context) {
		Toast.makeText(context, "Alarm canceled", Toast.LENGTH_SHORT).show();
	}
}
