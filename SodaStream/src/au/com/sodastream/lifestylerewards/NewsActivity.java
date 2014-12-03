package au.com.sodastream.lifestylerewards;

import android.app.Activity;
import android.os.Bundle;
import android.widget.GridView;
import android.widget.TextView;
import au.com.sodastream.lifestylerewards.Util.DATA;
import au.com.sodastream.lifestylerewards.Util.Fonts;
import au.com.sodastream.lifestylerewards.Util.Toasts;
import au.com.sodastream.lifestylerewards.adapters.AdapterNews;
import au.com.sodastream.lifestylerewards.asynctask.NewsAsyncTask;

public class NewsActivity extends Activity {
	
	
	//UI Elements

		GridView gvNews;
		TextView tv230;

		//Variables
		Activity activity;
		NewsAsyncTask newsAsyncTask;
		AdapterNews adapterNews;


		@Override
		protected void onCreate(Bundle savedInstanceState) {
			// TODO Auto-generated method stub
			super.onCreate(savedInstanceState);
			setContentView(R.layout.news_page);
			activity = this;

			initUI();


			newsAsyncTask = new NewsAsyncTask(activity);
			newsAsyncTask.execute();

		}


		private void initUI() {
			// TODO Auto-generated method stub
			gvNews = (GridView) findViewById(R.id.gvNews);

			tv230 = (TextView) findViewById(R.id.tv230);
			tv230.setTypeface(Fonts.getHelvatica(activity));
		}


		@Override
		protected void onStart() {
			// TODO Auto-generated method stub
			super.onStart();



		}


		public void loadNewGrid()
		{

			if(DATA.arrlstNewsModules!=null)
			{
				adapterNews = new AdapterNews(activity);
				gvNews.setAdapter(adapterNews);
			}
			else
			{
				Toasts.pop(activity, "Error fetching news ");
			}

		}

}
