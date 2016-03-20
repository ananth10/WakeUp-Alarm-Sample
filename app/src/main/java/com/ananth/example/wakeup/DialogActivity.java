package com.ananth.example.wakeup;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;

public class DialogActivity extends Activity {
	
	SampleAlarmReceiver alarm = new SampleAlarmReceiver();
	Ringtone r;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
		  r = RingtoneManager.getRingtone(getApplicationContext(), notification);
		r.play();
		dialog();
		
	}

	private void dialog()

	{
		AlertDialog.Builder alertDialog = new AlertDialog.Builder(
				DialogActivity.this);

		// Setting Dialog Title
		alertDialog.setTitle("Remainder");
		alertDialog.setMessage(Utils.getData1("remaind_word",DialogActivity.this));
		alertDialog.setIcon(R.drawable.ic_launcher);

		// Setting Positive "Yes" Button
		alertDialog.setPositiveButton("Repeat",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						// Write your code here to invoke YES event
					r.stop();
					finish();
					}
				});

		// Setting Negative "NO" Button
		alertDialog.setNegativeButton("Cancel",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						// Write your code here to invoke NO event

						 r.stop();
						 alarm.cancelAlarm(DialogActivity.this);
						
						finish();

					}
				});

		// Showing Alert Message
		alertDialog.show();
	}
}
