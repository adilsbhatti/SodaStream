package au.com.sodastream.lifestylerewards.Util;

import android.app.Activity;
import android.graphics.Typeface;

public class Fonts {

	static String path  = "fonts/h.ttf";
	
	public static Typeface getHelvatica(Activity activity)
	{
		return Typeface.createFromAsset(activity.getAssets(), path);
	}
}
