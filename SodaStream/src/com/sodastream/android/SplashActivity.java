package com.sodastream.android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import com.sodastream.android.services.LocationService;

public class SplashActivity extends Activity {


	//UI Memebers
	ImageView ivSplash;

	//Variables
	private static int SPLASH_TIME_OUT = 2000;
	Handler splashHandler;
	Runnable splashRunnable;
	Activity context;
	LocationService locationService;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash_page);
		context = this;

		//initialize UI elements
		initUI();


		//		locationService = new LocationService(context);
		//
		//		// check if GPS enabled
		//		if (locationService.isLocationAvailable()) {
		//
		//			Double latitude = locationService.getLatitude();
		//			Double longitude = locationService.getLongitude();
		//
		//			System.out.println("-- Lat and long  : " + latitude  + " -- " + longitude);
		//
		//			//			latitudeTv.setText(latitude.toString());
		//			//			longitudeTv.setText(longitude.toString());
		//		} else {
		//			// can't get location
		//			// GPS or Network is not enabled
		//			// Ask user to enable GPS/network in settings
		//			locationService.showSettingsAlert();
		//		}

		//Splash Screen Method
		showSplash();



	}

	private void initUI() {
		// TODO Auto-generated method stub
		ivSplash = (ImageView) findViewById(R.id.ivSplash);
	}

	public void showSplash()
	{
		splashHandler = new Handler();


		splashRunnable = new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub

				Intent i = new Intent(context, SigninActivity.class);
				startActivity(i);

				// close this activity
				finish();

			}
		};

		splashHandler.postDelayed(splashRunnable, SPLASH_TIME_OUT);
	}


	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		// Stop Splash Handler in case user press back button
		splashHandler.removeCallbacks(splashRunnable);
	}
}
