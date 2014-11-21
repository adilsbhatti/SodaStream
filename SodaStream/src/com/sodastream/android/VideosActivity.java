package com.sodastream.android;

import android.app.Activity;
import android.os.Bundle;
import android.widget.GridView;
import android.widget.TextView;

import com.sodastream.android.Util.DATA;
import com.sodastream.android.Util.Fonts;
import com.sodastream.android.Util.Toasts;
import com.sodastream.android.adapters.AdapterVideos;
import com.sodastream.android.asynctask.VideosAsyncTask;

public class VideosActivity extends Activity {


	//UI Elements

	GridView gvVideos;
	TextView tv3;
	//SwipeRefreshLayout swipeLayout;

	//Variables
	Activity activity;
	VideosAsyncTask videosAsyncTask;
	AdapterVideos adapterVideos;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.videos_page);
		activity = this;

		initUI();


	}


	private void initUI() {
		// TODO Auto-generated method stub
		gvVideos = (GridView) findViewById(R.id.gvVideos);

		tv3 = (TextView) findViewById(R.id.tv3);
		tv3.setTypeface(Fonts.getHelvatica(activity));


	}


	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();

		activity = this;
		videosAsyncTask = new VideosAsyncTask(activity);
		videosAsyncTask.execute();

	}


	public void loadVideosGrid()
	{

		if(DATA.arrlstVideosModules!=null)
		{
			adapterVideos = new AdapterVideos(activity);
			gvVideos.setAdapter(adapterVideos);
		}
		else
		{
			Toasts.pop(activity, "Error fetching new products");
		}

	}


}
