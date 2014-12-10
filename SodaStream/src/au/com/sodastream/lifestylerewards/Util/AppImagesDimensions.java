package au.com.sodastream.lifestylerewards.Util;

import android.app.Activity;

public class AppImagesDimensions {

	
	public static  void setScreenUnits(Activity activity) {
		// TODO Auto-generated method stub


		switch (ScreenSize.getScreenDensity(activity)) {
		case ScreenSize.MDPI:


			DATA.MENU_IMAGES_SIZE  = 159;
			DATA.RECIPE_IMAGES_SIZE = 200;

			break;


		case ScreenSize.HDPI:


			DATA.MENU_IMAGES_SIZE  = 238;
			DATA.RECIPE_IMAGES_SIZE = 250;

			break;

		case ScreenSize.XHDPI:


			DATA.MENU_IMAGES_SIZE  = 317;
			DATA.RECIPE_IMAGES_SIZE = 350;

			break;

		case ScreenSize.XXHDPI:


			DATA.MENU_IMAGES_SIZE  = 476;
			DATA.RECIPE_IMAGES_SIZE = 500;

			break;

		default:
			break;
		}

	}
}
