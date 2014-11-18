package com.sodastream.android;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;

import com.sodastream.android.asynctask.RecipesAsyncTask;
import com.sodastream.android.fragments.RecipesFragment;

public class RecipesActivity extends Activity {
	
	
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
		
		
		recipesAsyncTask = new RecipesAsyncTask(activity);
		recipesAsyncTask.execute();
		
		
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
