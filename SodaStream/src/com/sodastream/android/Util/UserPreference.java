package com.sodastream.android.Util;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.SharedPreferences;

import com.sodastream.android.modules.UserModel;

public class UserPreference {

	Activity _activity;

	public final String PREF_NAME = "";

	public final String EMAIL = "email";
	public final String LAT = "lat";
	public final String LONG = "long";
	public final String NAME = "name";
	public final String LOGIN_STATUS = "loginstatus";

	public final String USER = "user";
	
	

	SharedPreferences sharedPreferences;
	SharedPreferences.Editor editor;	


	public UserPreference(Activity activity) {
		// TODO Auto-generated constructor stub
		_activity = activity;
		sharedPreferences = _activity.getSharedPreferences(PREF_NAME, Activity.MODE_PRIVATE);
		editor = sharedPreferences.edit();
	}



	public void setUser(UserModel user)
	{

		JSONObject jsonObject = new JSONObject();

		try {
			jsonObject.put(NAME, user.name);
			jsonObject.put(LAT, user.Lat);
			jsonObject.put(LONG, user.Long);
			jsonObject.put(EMAIL, user.email);
			jsonObject.put(LOGIN_STATUS, user.loginStatus);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		editor.putString(USER, jsonObject.toString());
		editor.commit();
	}



	public UserModel getUser()
	{

		try {
			JSONObject jsonObject = new JSONObject(sharedPreferences.getString(USER, ""));
			UserModel user = new UserModel();

			user.name = jsonObject.getString(NAME);
			user.Lat = jsonObject.getString(LAT);
			user.Long = jsonObject.getString(LONG);
			user.email = jsonObject.getString(EMAIL);
			user.loginStatus = jsonObject.getInt(LOGIN_STATUS);
			return user;

		} catch (JSONException e) {
			// TODO Auto-generated catch block

			e.printStackTrace();
			return null;
		}

	}
	
	
	
	
	public void deleteUser()
	{
		editor.putString(USER, "");
		editor.commit();
	}
	
	
	public void setLoginStatus(int status)
	{
		editor.putInt(LOGIN_STATUS, status);
		editor.commit();
	}
	
	public int getLoginStatus()
	{
		return sharedPreferences.getInt(LOGIN_STATUS, 0);
	}

}
