package com.ananth.example.wakeup;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class Utils {
	
	public static String mMes="";
	public static final String WAKEUP = "wakeup";

	public static void saveData1(String key, String value, Context context) {
		SharedPreferences.Editor editor = context.getSharedPreferences(WAKEUP, Activity.MODE_PRIVATE).edit();
		editor.putString(key, value);
		editor.commit();
	}

	public static String getData1(String key, Context context) {
		SharedPreferences prefs = context.getSharedPreferences(WAKEUP, Activity.MODE_PRIVATE);
		return prefs.getString(key, "");
	}

}
