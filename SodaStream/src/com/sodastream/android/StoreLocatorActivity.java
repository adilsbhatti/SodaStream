package com.sodastream.android;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;

public class StoreLocatorActivity extends Activity {

	//UI Elements
	GoogleMap  googleMap;


	//Variables
	Context context;
	CameraUpdate  cameraUpdate;
	GooglePlayServicesUtil googlePlayServicesUtil;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.storelocator_page);
		context = this;


		System.out.println("-- play service  " + GooglePlayServicesUtil.isGooglePlayServicesAvailable(context));
		System.out.println("-- play service code  " + GooglePlayServicesUtil.GOOGLE_PLAY_SERVICES_VERSION_CODE);


	}

	private void initMAP() {
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
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();

		initMAP();
	}
	
	
	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		
		googleMap.clear();
		
	}

}
