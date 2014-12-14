package au.com.sodastream.lifestylerewards.asynctask;



import java.util.concurrent.Executors;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import au.com.sodastream.lifestylerewards.Util.AppPref;
import au.com.sodastream.lifestylerewards.Util.DATA;
import au.com.sodastream.lifestylerewards.modules.IdFrom;
import au.com.sodastream.lifestylerewards.modules.LoginModule;




public class GetLocationAsyncTask extends AsyncTask<String, Boolean, Boolean> implements DialogInterface.OnDismissListener {

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

	AppPref appPref;

	private LoginAsyncTask loginAsyncTask;
	SignupAsyncTask signupAsyncTask;
	FBRegisterTask fbRegisterTask;
	StoreLocationAsyncTask storeLocationAsyncTask;



	public GetLocationAsyncTask(Activity activity, int _idFrom, String _dialogTitle)
	{
		this.activity = activity;

		idFromPage = _idFrom;
		dialogTitle = _dialogTitle;

		DATA.progressDialog = new ProgressDialog(activity);
		DATA.progressDialog.setMessage(dialogTitle);
		DATA.progressDialog.setCanceledOnTouchOutside(false);



		DATA.progressDialog.setOnCancelListener(new OnCancelListener() {

			@Override
			public void onCancel(DialogInterface dialog) {
				// TODO Auto-generated method stub

				System.out.println("-- I am called from location task");


				GetLocationAsyncTask.this.cancel(true);
			}
		});

		appPref =  new AppPref(activity);
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



			appPref.setLatitude("" +DATA.Latitude);
			appPref.setLongitude("" +DATA.Longitude);

			switch (idFromPage) {
			case IdFrom.LOGIN:
				loginAsyncTask =  new LoginAsyncTask(activity, new LoginModule());
				loginAsyncTask.execute();
				break;


			case IdFrom.SIGN_UP:
				signupAsyncTask =  new SignupAsyncTask(activity);
				signupAsyncTask.executeOnExecutor(Executors.newSingleThreadExecutor(), "");
				break;
				
			case IdFrom.FACEBOOK_LOGIN : 
				fbRegisterTask = new FBRegisterTask(activity);
				fbRegisterTask.executeOnExecutor(Executors.newSingleThreadExecutor(), "");
				
				break;
				
			case IdFrom.STORES:
				
				storeLocationAsyncTask = new StoreLocationAsyncTask(activity);
				storeLocationAsyncTask.execute();
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


	@Override
	protected void onCancelled() {
		// TODO Auto-generated method stub
		super.onCancelled();
		locationManager.removeUpdates(locationListenerGPS);
		locationListenerGPS = null;
		locationManager = null;
		this.cancel(true);
	}

	@Override
	public void onDismiss(DialogInterface dialog) {
		// TODO Auto-generated method stub

		//		System.out.println("-- I am called from location task");
		this.cancel(true);

	}




}

