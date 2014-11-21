package com.sodastream.android.adapters;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.koushikdutta.urlimageviewhelper.UrlImageViewHelper;
import com.sodastream.android.R;
import com.sodastream.android.Util.DATA;
import com.sodastream.android.Util.Fonts;
import com.sodastream.android.fragments.RecipeDetailsFragment;
import com.sodastream.android.modules.RecipeModel;

public class AdapterRecipes extends ArrayAdapter<RecipeModel> {

	//Variables
	Activity activity;
	
	FragmentManager fragmentManager;
	FragmentTransaction fragmentTransaction;
	RecipeDetailsFragment recipeDetailsFragment;

	public AdapterRecipes(Activity _activity) {
		super(_activity, R.layout.cell_row	, DATA.arrlstRecipeModels);
		// TODO Auto-generated constructor stub

		activity = _activity;
	}


	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolderClass viewHolder;


		if(convertView == null)
		{
			LayoutInflater layoutInflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = layoutInflater.inflate(R.layout.cell_row, parent, false);

			viewHolder = new ViewHolderClass();
			initUI(convertView, viewHolder);

			convertView.setTag(viewHolder);

		}
		else
		{
			viewHolder = (ViewHolderClass) convertView.getTag();
		}



		viewHolder.tvIconTitle.setText(DATA.arrlstRecipeModels.get(position).title);

		UrlImageViewHelper.setUrlDrawable(viewHolder.ivIconImg, DATA.arrlstRecipeModels.get(position).thumbnail_url,R.drawable.icon);



		convertView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub


				//ViewBrowser.openURL(activity, DATA.arrlstRewardsModules.get(position).site_url);	
				
				DATA.selectedRecipe =  DATA.arrlstRecipeModels.get(position);
				
				recipeDetailsFragment =  new RecipeDetailsFragment(activity);
				fragmentManager = activity.getFragmentManager();
				fragmentTransaction =  fragmentManager.beginTransaction();
				
				fragmentTransaction.replace(R.id.layBodyRecipes, recipeDetailsFragment);
				fragmentTransaction.addToBackStack(null);
				
				fragmentTransaction.commit();

			}
		});



		return convertView;
	}



	private void initUI(View convertView, ViewHolderClass viewHolder) {
		// TODO Auto-generated method stub

		viewHolder.ivIconImg = (ImageView) convertView.findViewById(R.id.ivIconImg);


		viewHolder.tvIconTitle = (TextView) convertView.findViewById(R.id.tvIconTitle);
		viewHolder.tvIconTitle.setTypeface(Fonts.getHelvatica(activity));

	}



	static class ViewHolderClass
	{
		ImageView ivIconImg ;
		TextView tvIconTitle;
	}

}
