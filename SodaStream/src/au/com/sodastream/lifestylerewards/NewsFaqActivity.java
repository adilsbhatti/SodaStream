package au.com.sodastream.lifestylerewards;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;
import au.com.sodastream.lifestylerewards.Util.Fonts;
import au.com.sodastream.lifestylerewards.asynctask.FAQAsyncTask;
import au.com.sodastream.lifestylerewards.asynctask.NewsAsyncTask;


public class NewsFaqActivity extends Activity  implements OnClickListener{


	//UI Elements
	ImageButton ibNewsProducts,ibNewsVideos,ibNewsNews,ibNewsFaq;
	TextView tv21;



	//Variables
	Activity activity;
	FAQAsyncTask faqAsyncTask;
	NewsAsyncTask newsAsyncTask;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.newsfaq_page);
		activity =  this;

		initUI();

	}


	private void initUI() {
		// TODO Auto-generated method stub

		ibNewsFaq = (ImageButton) findViewById(R.id.ibNewsFaq);
		ibNewsNews = (ImageButton) findViewById(R.id.ibNewsNews);
		ibNewsProducts = (ImageButton) findViewById(R.id.ibNewsProducts);
		ibNewsVideos = (ImageButton) findViewById(R.id.ibNewsVideos);

		ibNewsFaq.setOnClickListener(this);
		ibNewsNews.setOnClickListener(this);
		ibNewsProducts.setOnClickListener(this);
		ibNewsVideos.setOnClickListener(this);

		tv21 = (TextView) findViewById(R.id.tv21);
		tv21.setTypeface(Fonts.getHelvatica(activity));

	}


	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

		int ID = v.getId();
		Intent  intent;

		@SuppressWarnings("rawtypes")
		Class c = null;


		switch (ID) {
		case R.id.ibNewsFaq:
			//			c = FAQActivity.class;
			faqAsyncTask =  new FAQAsyncTask(activity);
			faqAsyncTask.execute();
			break;

		case R.id.ibNewsNews:


			newsAsyncTask = new NewsAsyncTask(activity);
			newsAsyncTask.execute();
			//			c = NewsActivity.class;

			break;


		case R.id.ibNewsProducts:

			c = NewProductActivity.class;
			intent = new Intent(activity, c);
			startActivity(intent);
			break;


		case R.id.ibNewsVideos:

			c =  VideosActivity.class;
			intent = new Intent(activity, c);
			startActivity(intent);
			break;

		default:

			c=null;

			break;

		}



	}

}
