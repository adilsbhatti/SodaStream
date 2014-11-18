package com.sodastream.android.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.koushikdutta.urlimageviewhelper.UrlImageViewHelper;
import com.sodastream.android.R;
import com.sodastream.android.Util.DATA;

public class RecipeDetailsFragment extends Fragment {

	TextView tvRecipeTitle,tvRecipeNote,tvRecipeInstructions,tvRecipeIngredients;
	ImageView ivRecipeImage;
	Activity activity;

	String ingredients = "", instructions = "";



	public RecipeDetailsFragment(Activity _activity) {
		// TODO Auto-generated constructor stub
		activity = _activity;
	}


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub

		return inflater.inflate(R.layout.recipedetail_frag, container, false);

	}



	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);



		tvRecipeTitle =  (TextView) activity.findViewById(R.id.tvRecipeTitle);
		tvRecipeIngredients =  (TextView) activity.findViewById(R.id.tvRecipeIngredients);
		tvRecipeInstructions =  (TextView) activity.findViewById(R.id.tvRecipeInstructions);
		tvRecipeNote =  (TextView) activity.findViewById(R.id.tvRecipeNote);

		ivRecipeImage = (ImageView) activity.findViewById(R.id.ivRecipeImage);

		tvRecipeIngredients.setMaxLines(DATA.selectedRecipe.ingredients.length);
		tvRecipeIngredients.setSingleLine(false );


		for(int i = 0; i < DATA.selectedRecipe.ingredients.length;i++)
		{
			

			ingredients  =ingredients +  " \u2022" +  DATA.selectedRecipe.ingredients[i] + System.getProperty("line.separator");
		}



		
		tvRecipeInstructions.setMaxLines(DATA.selectedRecipe.instructions.length);
		tvRecipeInstructions.setSingleLine(false );


		for(int i = 0; i < DATA.selectedRecipe.instructions.length;i++)
		{
			

			instructions  =instructions +  " \u2022" +  DATA.selectedRecipe.instructions[i] + System.getProperty("line.separator");
		}



		tvRecipeTitle.setText(DATA.selectedRecipe.title);
		tvRecipeIngredients.setText( ingredients);
		tvRecipeInstructions.setText( instructions);
		tvRecipeNote.setText(DATA.selectedRecipe.note);
		UrlImageViewHelper.setUrlDrawable(ivRecipeImage, DATA.selectedRecipe.image_url, R.drawable.icon);

	}

}
