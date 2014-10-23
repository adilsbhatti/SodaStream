package com.sodastream.android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

public class NewsFaqActivity extends Activity  implements OnClickListener{


	//UI Elements
	ImageButton ibNewsProducts,ibNewsVideos,ibNewsNews,ibNewsFaq;



	//Variables
	Activity activity;


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

	}


	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

		int ID = v.getId();

		@SuppressWarnings("rawtypes")
		Class c = null;


		switch (ID) {
		case R.id.ibNewsFaq:
			c = FAQActivity.class;
			break;

		case R.id.ibNewsNews:


			c = NewsActivity.class;
			break;


		case R.id.ibNewsProducts:

			c = NewProductActivity.class;

			break;


		case R.id.ibNewsVideos:

			c =  VideosActivity.class;

			break;

		default:

			c=null;

			break;

		}

		Intent  intent = new Intent(activity, c);
		startActivity(intent);

	}

}
