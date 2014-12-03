package au.com.sodastream.lifestylerewards;

import android.app.Activity;
import android.os.Bundle;
import android.widget.GridView;
import android.widget.TextView;
import au.com.sodastream.lifestylerewards.Util.DATA;
import au.com.sodastream.lifestylerewards.Util.Fonts;
import au.com.sodastream.lifestylerewards.Util.Toasts;
import au.com.sodastream.lifestylerewards.adapters.AdapterFAQ;
import au.com.sodastream.lifestylerewards.asynctask.FAQAsyncTask;

public class FAQActivity extends Activity {
	
	//UI Elements

		GridView gvFAQ;
		TextView tv300;
		//SwipeRefreshLayout swipeLayout;

		//Variables
		Activity activity;
		FAQAsyncTask faqAsyncTask;
		AdapterFAQ adapterFAQ;


		@Override
		protected void onCreate(Bundle savedInstanceState) {
			// TODO Auto-generated method stub
			super.onCreate(savedInstanceState);
			setContentView(R.layout.faq_page);
			activity = this;

			initUI();
			
			activity = this;
			faqAsyncTask = new FAQAsyncTask(activity);
			faqAsyncTask.execute();


		}


		private void initUI() {
			// TODO Auto-generated method stub
			gvFAQ = (GridView) findViewById(R.id.gvFaq);

			tv300 = (TextView) findViewById(R.id.tv300);
			tv300.setTypeface(Fonts.getHelvatica(activity));


		}


		@Override
		protected void onStart() {
			// TODO Auto-generated method stub
			super.onStart();

		

		}


		public void loadFAQGrid()
		{

			if(DATA.arrlstFAQModules!=null)
			{
				adapterFAQ = new AdapterFAQ(activity);
				gvFAQ.setAdapter(adapterFAQ);
			}
			else
			{
				Toasts.pop(activity, "Error fetching new products");
			}

		}

}
