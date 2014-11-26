package au.com.sodastream.lifestylerewards.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import au.com.sodastream.lifestylerewards.R;
import au.com.sodastream.lifestylerewards.Util.DATA;
import au.com.sodastream.lifestylerewards.Util.Toasts;
import au.com.sodastream.lifestylerewards.adapters.AdapterRecipes;


public class RecipesFragment extends Fragment {


	GridView gvRecipes;

	Activity activity;
	AdapterRecipes adapterRecipes;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub

		activity =  getActivity();

		return inflater.inflate(R.layout.recipe_frag, container, false);


	}
	
	
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		
	
		gvRecipes = (GridView) activity.findViewById(R.id.gvRecipes);
	}
	
	
	@Override
	public void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		
		loadRecipesGrid();
	}
	
	
	
	public void loadRecipesGrid()
	{
		
		if(DATA.arrlstRecipeModels!=null)
		{
			adapterRecipes =  new AdapterRecipes(activity);
			gvRecipes.setAdapter(adapterRecipes);
		}
		else
		{
			Toasts.pop(activity, "No Recipes to display");
		}
		
	}

}
