package com.sodastream.android;

import android.app.Activity;
import android.os.Bundle;

public class NewProductActivity extends Activity {
	
	
	
	
	//UI Elements
	
	//Variables
	Activity activity;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.newproduct_page);
		activity = this;
	}

}
