package au.com.sodastream.lifestylerewards;

import android.app.Activity;
import android.os.Bundle;
import android.view.View.OnClickListener;
import android.widget.GridView;
import android.widget.TextView;
import au.com.sodastream.lifestylerewards.Util.DATA;
import au.com.sodastream.lifestylerewards.Util.Fonts;
import au.com.sodastream.lifestylerewards.Util.Toasts;
import au.com.sodastream.lifestylerewards.adapters.AdapterRewards;
import au.com.sodastream.lifestylerewards.asynctask.RewardsAsyncTask;


public class RewardsActivity extends Activity {



	//UI Elements

	GridView gvRewards;
	TextView tv10;


	//Varaibles
	Activity activity;
	OnClickListener onClickListener;
	RewardsAsyncTask rewardsAsyncTask;
	AdapterRewards adapterRewards;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.rewards_page);
		activity =  this;

		initUI();


		rewardsAsyncTask = new RewardsAsyncTask(activity);
		rewardsAsyncTask.execute();

	}

	private void initUI() {
		// TODO Auto-generated method stub


		gvRewards = (GridView) findViewById(R.id.gvRewards);

		tv10 = (TextView) findViewById(R.id.tv10);
		tv10.setTypeface(Fonts.getHelvatica(activity));
	}



	public void loadRewardsGrid()
	{
		if(DATA.arrlstRewardsModules!=null)
		{
			adapterRewards =  new AdapterRewards(activity);
			gvRewards.setAdapter(adapterRewards);
		}
		else
		{
			Toasts.pop(activity, "No Rewards to display");
		}
	}
}
