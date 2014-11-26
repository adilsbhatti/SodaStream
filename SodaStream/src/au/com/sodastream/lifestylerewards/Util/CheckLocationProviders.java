package au.com.sodastream.lifestylerewards.Util;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.LocationManager;
import android.provider.Settings;

public class CheckLocationProviders {



	public static boolean isGPSOrNetworkAvailable(Activity activity)
	{





		LocationManager L = (LocationManager) activity.getSystemService(Context.LOCATION_SERVICE); 

		if (!L.isProviderEnabled(LocationManager.GPS_PROVIDER) && !L.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {

			return false;
		}
		else
		{
			return true;
		}
	}



	public static void showGPSDialog(final Activity activity)
	{
		//		Intent I = new Intent( 
		//				android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);  
		//		activity.startActivity(I);  
		AlertDialog.Builder alertDialog = new AlertDialog.Builder(activity);

		alertDialog.setTitle("Information");

		// Setting Dialog Message
		alertDialog
		.setMessage("GPS/Location is needed to perform the process");

		// On pressing Settings button
		alertDialog.setPositiveButton("Settings",
				new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				Intent intent = new Intent(
						Settings.ACTION_LOCATION_SOURCE_SETTINGS);
				activity.startActivity(intent);
			}
		});

		// on pressing cancel button
		alertDialog.setNegativeButton("Cancel",
				new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				dialog.cancel();
			}
		});

		// Showing Alert Message
		alertDialog.show();
	}


}
