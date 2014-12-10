package au.com.sodastream.lifestylerewards.Util;

import android.app.Activity;
import android.util.DisplayMetrics;

public class ScreenSize {

	public static DisplayMetrics metrics;

	public static final int LDPI = 1;
	public static final int MDPI = 2;
	public static final int HDPI = 3;
	public static final int XHDPI = 4;
	public static final int XXHDPI = 5;

	public static int getScreenDensity(Activity activity) {

		int density;
		metrics = new DisplayMetrics();
		activity.getWindowManager().getDefaultDisplay().getMetrics(metrics);

	
		density = activity.getResources().getDisplayMetrics().densityDpi;
		System.out.println("-- density : " + density);

		switch (density) {
	 	case 120:

			return LDPI;

		case 160:

			return MDPI;

		case 240:

			return HDPI;

		case 320:

			return XHDPI;

		case 480:

			return XXHDPI;

		}

		return 0;
	}

}
