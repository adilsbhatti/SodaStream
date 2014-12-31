package au.com.sodastream.lifestylerewards;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;
import au.com.sodastream.lifestylerewards.Util.AppPref;
import au.com.sodastream.lifestylerewards.Util.DATA;
import au.com.sodastream.lifestylerewards.Util.Fonts;
import au.com.sodastream.lifestylerewards.asynctask.GetLocationAsyncTask;
import au.com.sodastream.lifestylerewards.modules.IdFrom;

import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class StoreLocatorActivity extends Activity {

	//UI Elements
	GoogleMap  googleMap;
	TextView tv4;
	ImageButton ibRefershLocation;


	//Variables
	Activity	activity;
	CameraUpdate  cameraUpdate;
	GooglePlayServicesUtil googlePlayServicesUtil;
	//	StoreLocationAsyncTask storeLocationAsyncTask;
	GetLocationAsyncTask getLocationAsyncTask;


	ArrayList<LatLng> arrlstLatLngs;
	ArrayList<MarkerOptions> arrlstMarkersOptions;
	AppPref appPref;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.storelocator_page);
		activity = this;

		appPref =  new AppPref(activity);

		initUI();

		//		storeLocationAsyncTask = new StoreLocationAsyncTask(activity);
		//		storeLocationAsyncTask.execute();

		getLocationAsyncTask =  new GetLocationAsyncTask(activity, IdFrom.STORES, "Fetching Store Locations");
		getLocationAsyncTask.execute();



		//		System.out.println("-- play service  " + GooglePlayServicesUtil.isGooglePlayServicesAvailable(activity));
		//		System.out.println("-- play service code  " + GooglePlayServicesUtil.GOOGLE_PLAY_SERVICES_VERSION_CODE);


		ibRefershLocation.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				setMyLocationView();
			}
		});


	}

	private void initUI() {
		// TODO Auto-generated method stub
		tv4 = (TextView) findViewById(R.id.tv4);
		tv4.setTypeface(Fonts.getHelvatica(activity));

		ibRefershLocation = (ImageButton) findViewById(R.id.ibRefershLocation);

	}

	public void initMAP() {
		// TODO Auto-generated method stub

		googleMap  =  ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
		googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);



		setMarkersOnMap();

	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();


	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();

		initMAP();


	}


	public void setMarkersOnMap()
	{

		LatLng tempLatLng;
		MarkerOptions tempMarkerOptions;

		int icon;

		if(DATA.arrlstStoresModules !=null && DATA.arrlstStoresModules.size() > 0)
		{
			arrlstLatLngs =  new ArrayList<LatLng>();
			arrlstMarkersOptions = new ArrayList<MarkerOptions>();



			for(int i = 0 ; i < DATA.arrlstStoresModules.size(); i++)
			{





				tempLatLng =  new LatLng( Double.parseDouble(DATA.arrlstStoresModules.get(i).latitude), Double.parseDouble(DATA.arrlstStoresModules.get(i).longitude));



				icon = (DATA.arrlstStoresModules.get(i).type.equals("1") ? R.drawable.gasgps : R.drawable.machinegps);

				tempMarkerOptions = new MarkerOptions().position(tempLatLng).title(DATA.arrlstStoresModules.get(i).name).snippet(DATA.arrlstStoresModules.get(i).address).icon(BitmapDescriptorFactory.fromResource(icon));	




				arrlstMarkersOptions.add(tempMarkerOptions);

				tempMarkerOptions =  null;
				tempLatLng =  null;



			}



			for (MarkerOptions mo : arrlstMarkersOptions)
			{
				googleMap.addMarker(mo);
			}


			setMyLocationView();







		}
	}

	private void setMyLocationView() {
		LatLng tempLatLng;
		tempLatLng =  new LatLng(Double.parseDouble(appPref.getLatitude()) , Double.parseDouble(appPref.getLongitude()));
		//			cameraUpdate = CameraUpdateFactory.newLatLng(tempLatLng);
		cameraUpdate = CameraUpdateFactory.newLatLngZoom(tempLatLng, 14);
		//			cameraUpdate = CameraUpdateFactory.newCameraPosition(new CameraPosition(tempLatLng, zoom, tilt, bearing))
		googleMap.animateCamera(cameraUpdate);
		//			cameraUpdate = CameraUpdateFactory.newCameraPosition(new CameraPosition(tempLatLng, zoom, tilt, bearing))
	}


	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();

		try {
			googleMap.clear();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
