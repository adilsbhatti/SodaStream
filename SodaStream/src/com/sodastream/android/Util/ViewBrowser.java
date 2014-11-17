package com.sodastream.android.Util;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;

public class ViewBrowser {
	
	
	public static void openURL(Activity activity , String url) {
		Intent intent;
		intent = new Intent(Intent.ACTION_VIEW);
		intent.setData(Uri.parse(url));
		activity.startActivity(intent);
	}

}
