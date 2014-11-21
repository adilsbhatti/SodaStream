package com.sodastream.android;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.widget.TextView;

import com.sodastream.android.Util.Fonts;
import com.sodastream.android.asynctask.RecipesAsyncTask;
import com.sodastream.android.fragments.RecipesFragment;

public class RecipesActivity extends Activity {


	//UI Variables

	TextView tv13;


	//Variables
	Activity activity;


	RecipesFragment recipesFragment;
	FragmentManager fragmentManager;
	FragmentTransaction fragmentTransaction;

	RecipesAsyncTask recipesAsyncTask;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.recipes_page);
		activity =  this;


		initUI();

		recipesAsyncTask = new RecipesAsyncTask(activity);
		recipesAsyncTask.execute();


	}



	private void initUI() {
		// TODO Auto-generated method stub

		tv13 = (TextView) findViewById(R.id.tv13);
		tv13.setTypeface(Fonts.getHelvatica(activity));
	}



	public void loadRecipesFragment()
	{
		recipesFragment =  new RecipesFragment();

		fragmentManager  =  getFragmentManager();
		fragmentTransaction =  fragmentManager.beginTransaction();

		fragmentTransaction.replace(R.id.layBodyRecipes, recipesFragment);

		fragmentTransaction.commit();
	}

}
