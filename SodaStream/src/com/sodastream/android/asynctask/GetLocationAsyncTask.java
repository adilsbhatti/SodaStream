package com.sodastream.android.asynctask;



import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import com.sodastream.android.Util.DATA;
import com.sodastream.android.modules.IdFrom;
import com.sodastream.android.modules.LoginModule;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;



public class GetLocationAsyncTask extends AsyncTask<String, Boolean, Boolean> {

	Activity activity;

	ProgressDialog progressDialog;
	boolean hasLocation = false, noDevice = false;
	
String dialogTitle = "";
	LocationManager locationManager  = null;

	LocationListener locationListenerGPS = null,locationListener;
	DialogInterface dialog;
	int position;
	int type;
	int idFromPage;

	private LoginAsyncTask loginAsyncTask;
	SignupAsyncTask signupAsyncTask;



	public GetLocationAsyncTask(Activity activity, int _idFrom, String _dialogTitle)
	{
		this.activity = activity;
		
		idFromPage = _idFrom;
		dialogTitle = _dialogTitle;
		
		DATA.progressDialog = new ProgressDialog(activity);
		DATA.progressDialog.setMessage(dialogTitle);
		DATA.progressDialog.setCanceledOnTouchOutside(false);

	}

	


	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		DATA.progressDialog.show();
	}



	@Override
	protected Boolean doInBackground(String... params) {

		try
		{

			activity.runOnUiThread(new Runnable() {

				public void run() {
					locationManager = (LocationManager)activity.getSystemService(Context.LOCATION_SERVICE);
					locationListener = new LocationListener() {

						public void onStatusChanged(String provider, int status, Bundle extras) {
							// TODO Auto-generated method stub
							System.out.println("Provider status change WIFI/SIM" + provider.toString());

						}

						public void onProviderEnabled(String provider) {
							System.out.println("Provider Enabled WIFI/SIM" + provider.toString());

						}

						public void onProviderDisabled(String provider) {
							System.out.println("Provider Not working WIFI/SIM" + provider.toString());


						}

						public void onLocationChanged (Location location) {
							DATA.Latitude =  location.getLatitude();
							DATA.Longitude=  location.getLongitude();
							//							System.out.println("Lat : " + Locations.Lat + "Long" + Locations.Long);
							hasLocation = true;

						}
					};

					locationListenerGPS = new LocationListener() {

						public void onStatusChanged(String provider, int status, Bundle extras) {
						}

						public void onProviderEnabled(String provider) {
							System.out.println("-- Provider  working GPS");
						}

						public void onProviderDisabled(String provider) {
							System.out.println("-- Provider Not working GPS");
							//							hasLocation = true;
							//							noDevice= true;
						}

						public void onLocationChanged(Location location) {
							DATA.Latitude =  location.getLatitude();
							DATA.Longitude=  location.getLongitude();

							hasLocation = true;
						}
					};

					locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);
					locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0	, 0, locationListenerGPS);





				}
			});
			

			while (!hasLocation) {
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			};




			return true;
		}
		catch (Exception e) {

			return false;
		}

	}



	@Override
	protected void onPostExecute(Boolean result) {
		super.onPostExecute(result);

		if(!noDevice && result)
		{

			System.out.println("-- Your location  lat : " + DATA.Latitude + " and long : " + DATA.Longitude);

			
			switch (idFromPage) {
			case IdFrom.LOGIN:
				loginAsyncTask =  new LoginAsyncTask(activity, new LoginModule());
				loginAsyncTask.execute();
				break;

				
			case IdFrom.SIGN_UP:
				signupAsyncTask =  new SignupAsyncTask(activity);
				signupAsyncTask.executeOnExecutor(Executors.newSingleThreadExecutor(), "");
				break;
			default:
				break;
			}

		






		}
		else
		{
			System.out.println( "-- Please turn on GPS or insert SIM Card in device");
		}

		
		locationManager.removeUpdates(locationListenerGPS);
		locationListenerGPS = null;
		locationManager = null;
		this.cancel(true);
//		progressDialog.dismiss();
	}




}
