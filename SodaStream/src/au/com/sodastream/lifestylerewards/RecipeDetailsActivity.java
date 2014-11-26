package au.com.sodastream.lifestylerewards;

import android.app.Activity;
import android.os.Bundle;

public class RecipeDetailsActivity extends Activity {
	
	//UI Elements
	
	//Variables
	Activity activity;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.recipedetails_page);
		activity = this;
	}

}
