package au.com.sodastream.lifestylerewards.Util;

import android.app.Activity;

public class DataPrefrences  {
	
	
	final String PREF_NAME = "data";
	
	final String VOUCHERS = "vouchers";
	final String REWARDS = "rewards";
	final String NEW_PRODUCTS = "newproducts";
	final String VIDEOS = "videos";
	final String NEWS = "news";
	final String FAQ = "faq";
	final String RECIPES = "recipes";
	final String STORE_LOCATIONS = "store_loc";
	
	
	Activity activity;
	
	public DataPrefrences(Activity _activity) {
		// TODO Auto-generated constructor stub
		activity = _activity;
	}

}
