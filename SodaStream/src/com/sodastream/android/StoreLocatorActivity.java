package com.sodastream.android;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.sodastream.android.Util.DATA;
import com.sodastream.android.Util.Fonts;
import com.sodastream.android.asynctask.StoreLocationAsyncTask;

public class StoreLocatorActivity extends Activity {

	//UI Elements
	GoogleMap  googleMap;
	TextView tv4;


	//Variables
	Activity	activity;
	CameraUpdate  cameraUpdate;
	GooglePlayServicesUtil googlePlayServicesUtil;
	StoreLocationAsyncTask storeLocationAsyncTask;
	ArrayList<LatLng> arrlstLatLngs;
	ArrayList<MarkerOptions> arrlstMarkersOptions;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.storelocator_page);
		activity = this;

		initUI();

		storeLocationAsyncTask = new StoreLocationAsyncTask(activity);
		storeLocationAsyncTask.execute();



		System.out.println("-- play service  " + GooglePlayServicesUtil.isGooglePlayServicesAvailable(activity));
		System.out.println("-- play service code  " + GooglePlayServicesUtil.GOOGLE_PLAY_SERVICES_VERSION_CODE);


	}

	private void initUI() {
		// TODO Auto-generated method stub
		tv4 = (TextView) findViewById(R.id.tv4);
		tv4.setTypeface(Fonts.getHelvatica(activity));
	}

	public void initMAP() {
		// TODO Auto-generated method stub

		googleMap  =  ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
		googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
		//
		//		cameraUpdate = CameraUpdateFactory.zoomBy(15);
		//
		//				final LatLng Karachi = new LatLng(24.8508, 67.0181);
		//
		//				Marker khi = googleMap.addMarker(new MarkerOptions()
		//				.position(Karachi)
		//				.title("Karachi")
		//				.snippet("City of lights")
		//				.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));

		//		googleMap.setInfoWindowAdapter(new InfoWindowAdapter() {
		//
		//			@Override
		//			public View getInfoWindow(Marker arg0) {
		//				// TODO Auto-generated method stub
		//				return null;
		//			}
		//
		//			@Override
		//			public View getInfoContents(Marker arg0) {
		//				// TODO Auto-generated method stub
		//				return null;
		//			}
		//		});

		//		//		
		//		//
		//		cameraUpdate = CameraUpdateFactory.newLatLng(Karachi);
		//		googleMap.animateCamera(cameraUpdate);
		//
		//		
		//		googleMap.clear();

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

		//		initMAP();


	}


	public void setMarkersOnMap()
	{

		LatLng tempLatLng;
		MarkerOptions tempMarkerOptions;

		int icon;

		if(DATA.arrlstStoresModules!=null)
		{
			arrlstLatLngs =  new ArrayList<LatLng>();
			arrlstMarkersOptions = new ArrayList<MarkerOptions>();



			for(int i = 0 ; i < 50; i++)
			{





				//				tempLatLng =  new LatLng( Double.parseDouble(DATA.arrlstStoresModules.get(i).latitude) +1, Double.parseDouble(DATA.arrlstStoresModules.get(i).longitude)+1);
				tempLatLng =  new LatLng(-37.811395+i, 144.957347+i);


				System.out.println("-- " + Double.parseDouble(DATA.arrlstStoresModules.get(i).latitude) + "        " + Double.parseDouble(DATA.arrlstStoresModules.get(i).longitude));


				System.out.println("stores type  : " + DATA.arrlstStoresModules.get(i).type + " equal status  : " + DATA.arrlstStoresModules.get(i).type.equals("1"));
				icon = (DATA.arrlstStoresModules.get(i).type.equals("1") ? R.drawable.gasgps : R.drawable.machinegps);

				tempMarkerOptions = new MarkerOptions().position(tempLatLng).title(DATA.arrlstStoresModules.get(i).name).snippet(DATA.arrlstStoresModules.get(i).address + "\n" + DATA.arrlstStoresModules.get(i).phone).icon(BitmapDescriptorFactory.fromResource(icon));


				//				cameraUpdate = CameraUpdateFactory.zoomIn();

				arrlstMarkersOptions.add(tempMarkerOptions);

				tempMarkerOptions =  null;
				tempLatLng =  null;



			}
			for (MarkerOptions mo : arrlstMarkersOptions)
			{
				googleMap.addMarker(mo);
			}
			tempLatLng =  new LatLng(-37.811395, 144.957347);
			cameraUpdate = CameraUpdateFactory.newLatLng(tempLatLng);
			googleMap.animateCamera(cameraUpdate);
			//			cameraUpdate = CameraUpdateFactory.newCameraPosition(new CameraPosition(tempLatLng, zoom, tilt, bearing))







		}
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
