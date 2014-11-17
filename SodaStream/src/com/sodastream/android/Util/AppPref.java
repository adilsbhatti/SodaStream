package com.sodastream.android.Util;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class AppPref {
	
	
	public final String PREF_NAME = "ACCESS_TOKEN_PREF";
	
	public final String FIELD_ACCESS_TOKEN = "access_token";
	
	
	public final String FIELD_ACTIVATE_CODE = "code";
	
	SharedPreferences sharedPreferences;
	
	Editor editor;
	
	Activity activity;
	
	public AppPref(Activity _activity) {
		// TODO Auto-generated constructor stub
		activity = _activity;
		sharedPreferences = activity.getSharedPreferences(PREF_NAME, Activity.MODE_PRIVATE);
		editor =  sharedPreferences.edit();
		
	}
	
	
	
	public void setAccessToken(String at)
	{
		editor.putString(FIELD_ACCESS_TOKEN, at);
		editor.commit();
	}
	
	
	public String getAccessToken()
	{
		return sharedPreferences.getString(FIELD_ACCESS_TOKEN, "");
	}
	
	
	public void setActivationCode(String ac)
	{
		editor.putString(FIELD_ACTIVATE_CODE, ac);
		editor.commit();
	}
	
	
	public String getActivationCode()
	{
		return sharedPreferences.getString(FIELD_ACTIVATE_CODE, "");
	}
	

}
