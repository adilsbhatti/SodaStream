package au.com.sodastream.lifestylerewards;

import android.app.Activity;
import android.os.Bundle;
import android.widget.GridView;
import android.widget.TextView;
import au.com.sodastream.lifestylerewards.Util.DATA;
import au.com.sodastream.lifestylerewards.Util.Fonts;
import au.com.sodastream.lifestylerewards.Util.Toasts;
import au.com.sodastream.lifestylerewards.adapters.AdapterVideos;
import au.com.sodastream.lifestylerewards.asynctask.VideosAsyncTask;


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
		
		activity = this;
		videosAsyncTask = new VideosAsyncTask(activity);
		videosAsyncTask.execute();


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
