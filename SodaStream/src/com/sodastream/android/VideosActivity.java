package com.sodastream.android;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.widget.GridView;

import com.sodastream.android.Util.DATA;
import com.sodastream.android.Util.Toasts;
import com.sodastream.android.adapters.AdapterVideos;
import com.sodastream.android.asynctask.VideosAsyncTask;

public class VideosActivity extends Activity {


	//UI Elements

	GridView gvVideos;
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
