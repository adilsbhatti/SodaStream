package au.com.sodastream.lifestylerewards.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import au.com.sodastream.lifestylerewards.R;
import au.com.sodastream.lifestylerewards.Util.AppImagesDimensions;
import au.com.sodastream.lifestylerewards.Util.DATA;
import au.com.sodastream.lifestylerewards.Util.Fonts;

import com.koushikdutta.urlimageviewhelper.UrlImageViewHelper;

public class RecipeDetailsFragment extends Fragment {

	TextView tvRecipeTitle,tvRecipeNote,tvRecipeInstructions,tvRecipeIngredients,tv15,tv17;
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



		initUI();


		for(int i = 0; i < DATA.selectedRecipe.ingredients.length;i++)
		{


			//			ingredients  =ingredients +  " \u2022" +  DATA.selectedRecipe.ingredients[i] + System.getProperty("line.separator");
			ingredients  =ingredients +    DATA.selectedRecipe.ingredients[i] + System.getProperty("line.separator");
		}




		tvRecipeInstructions.setMaxLines(DATA.selectedRecipe.instructions.length);
		tvRecipeInstructions.setSingleLine(false );


		for(int i = 0; i < DATA.selectedRecipe.instructions.length;i++)
		{


			//			instructions  =instructions +  " \u2022" +  DATA.selectedRecipe.instructions[i] + System.getProperty("line.separator");
			instructions  =instructions +    DATA.selectedRecipe.instructions[i] + System.getProperty("line.separator");
		}



		tvRecipeTitle.setText(DATA.selectedRecipe.title);
		tvRecipeIngredients.setText( ingredients);
		tvRecipeInstructions.setText( instructions);
		tvRecipeNote.setText(DATA.selectedRecipe.note);
		UrlImageViewHelper.setUrlDrawable(ivRecipeImage, DATA.selectedRecipe.image_url, R.drawable.transparent);
		AppImagesDimensions.setScreenUnits(activity);

		ivRecipeImage.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT, DATA.RECIPE_IMAGES_SIZE));

	}


	private void initUI() {


		tvRecipeTitle =  (TextView) activity.findViewById(R.id.tvRecipeTitle);
		tvRecipeIngredients =  (TextView) activity.findViewById(R.id.tvRecipeIngredients);
		tvRecipeInstructions =  (TextView) activity.findViewById(R.id.tvRecipeInstructions);
		tvRecipeNote =  (TextView) activity.findViewById(R.id.tvRecipeNote);

		tvRecipeTitle.setTypeface(Fonts.getHelvatica(activity));
		tvRecipeIngredients.setTypeface(Fonts.getHelvatica(activity));
		tvRecipeInstructions.setTypeface(Fonts.getHelvatica(activity));
		tvRecipeNote.setTypeface(Fonts.getHelvatica(activity));
		
		


		tv15 =  (TextView) activity.findViewById(R.id.tv15);
//		tv15.setTypeface(Fonts.getHelvatica(activity));
		
		tv15.setTypeface(Fonts.getHelvatica(activity), Typeface.BOLD);

//		tv16 =  (TextView) activity.findViewById(R.id.tv16);
//		tv16.setTypeface(Fonts.getHelvatica(activity));
//		tv16.setTypeface(Fonts.getHelvatica(activity), Typeface.BOLD);

		tv17 =  (TextView) activity.findViewById(R.id.tv17);
		tv17.setTypeface(Fonts.getHelvatica(activity));
		tv17.setTypeface(Fonts.getHelvatica(activity), Typeface.BOLD);




		ivRecipeImage = (ImageView) activity.findViewById(R.id.ivRecipeImage);
		AppImagesDimensions.setScreenUnits(activity);
		System.out.println("-- reciped image : " + DATA.RECIPE_IMAGES_SIZE);
		ivRecipeImage.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT, DATA.RECIPE_IMAGES_SIZE));


		tvRecipeIngredients.setMaxLines(DATA.selectedRecipe.ingredients.length);
		tvRecipeIngredients.setSingleLine(false );
	}

}
