package com.sodastream.android;

import android.app.Activity;
import android.os.Bundle;
import android.view.View.OnClickListener;
import android.widget.GridView;

import com.sodastream.android.Util.DATA;
import com.sodastream.android.Util.Toasts;
import com.sodastream.android.adapters.AdapterRewards;
import com.sodastream.android.asynctask.RewardsAsyncTask;

public class RewardsActivity extends Activity {



	//UI Elements

	GridView gvRewards;


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
