package au.com.sodastream.lifestylerewards;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import au.com.sodastream.lifestylerewards.Util.AppPref;
import au.com.sodastream.lifestylerewards.Util.DATA;
import au.com.sodastream.lifestylerewards.Util.Fonts;
import au.com.sodastream.lifestylerewards.asynctask.GetLocationAsyncTask;
import au.com.sodastream.lifestylerewards.asynctask.StoreLocationAsyncTask;
import au.com.sodastream.lifestylerewards.modules.IdFrom;

import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class StoreLocatorActivity extends Activity {

	//UI Elements
	GoogleMap  googleMap;
	TextView tv4;


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
				//				tempLatLng =  new LatLng(-37.811395+i, 144.957347+i);


				//				System.out.println("-- " + Double.parseDouble(DATA.arrlstStoresModules.get(i).latitude) + "        " + Double.parseDouble(DATA.arrlstStoresModules.get(i).longitude));


				//				System.out.println("stores type  : " + DATA.arrlstStoresModules.get(i).type + " equal status  : " + DATA.arrlstStoresModules.get(i).type.equals("1"));
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


			tempLatLng =  new LatLng(Double.parseDouble(appPref.getLatitude()) , Double.parseDouble(appPref.getLongitude()));
			//			cameraUpdate = CameraUpdateFactory.newLatLng(tempLatLng);
			cameraUpdate = CameraUpdateFactory.newLatLngZoom(tempLatLng, 12);
			//			cameraUpdate = CameraUpdateFactory.newCameraPosition(new CameraPosition(tempLatLng, zoom, tilt, bearing))
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
