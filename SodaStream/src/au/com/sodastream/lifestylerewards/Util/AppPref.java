package au.com.sodastream.lifestylerewards.Util;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class AppPref {


	public final String PREF_NAME = "ACCESS_TOKEN_PREF";

	public final String FIELD_ACCESS_TOKEN = "access_token";

	public final String FIELD_ACTIVATE_CODE = "activation_code";

	public final String FIELD_LATITUDE = "latitude";

	public final String FIELD_LONGITUDE = "longitude";

	public final String FACEBOOK_ID = "facebookid";

	public final String NEW_FACEBOOK_USER = "newfbuser";

	public final String POST_CODE = "postcode";
	
	public final String USER_EMAIL = "useremail";
	
	public final String DEVICE_ID = "deviceid";

	SharedPreferences sharedPreferences;

	Editor editor;

	Activity activity;
	Context context;

	public AppPref(Activity _activity) {
		// TODO Auto-generated constructor stub
		activity = _activity;
		sharedPreferences = activity.getSharedPreferences(PREF_NAME, Activity.MODE_PRIVATE);
		editor =  sharedPreferences.edit();

	}

	
	public AppPref(Context _context) {
		// TODO Auto-generated constructor stub
		context = _context;
		sharedPreferences = context.getSharedPreferences(PREF_NAME, Activity.MODE_PRIVATE);
		editor =  sharedPreferences.edit();

	}

	
	
	public void setDeviceID(String id)
	{
		editor.putString(DEVICE_ID	,id);
		editor.commit();
	}


	public String getDeviceID()
	{
		return sharedPreferences.getString(DEVICE_ID, "");
	}

	
	public void setUserEmail(String email)
	{
		editor.putString(USER_EMAIL	,email);
		editor.commit();
	}


	public String getUserEmail()
	{
		return sharedPreferences.getString(USER_EMAIL, "");
	}
	


	public void setPostcode(String pc)
	{
		editor.putString(POST_CODE	,pc);
		editor.commit();
	}


	public String getPostcode()
	{
		return sharedPreferences.getString(POST_CODE, "");
	}




	public void setNewFacebookUser(boolean user)
	{
		editor.putBoolean(NEW_FACEBOOK_USER	, user);
		editor.commit();
	}


	public boolean getNewFacebookUser()
	{
		return sharedPreferences.getBoolean(NEW_FACEBOOK_USER, false);
	}

	public void setFacebookID(String fbid)
	{
		editor.putString(FACEBOOK_ID, fbid);
		editor.commit();
	}


	public String getFacebookID()
	{
		return sharedPreferences.getString(FACEBOOK_ID, "");
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




	public void setLatitude(String lat)
	{
		editor.putString(FIELD_LATITUDE, lat);
		editor.commit();
	}


	public String getLatitude()
	{
		return sharedPreferences.getString(FIELD_LATITUDE, "0.0");
	}



	public void setLongitude(String lon)
	{
		editor.putString(FIELD_LONGITUDE, lon);
		editor.commit();
	}


	public String getLongitude()
	{
		return sharedPreferences.getString(FIELD_LONGITUDE, "");
	}

}
