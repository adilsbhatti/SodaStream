package com.sodastream.android;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageButton;

public class ForgotPassActivity extends Activity {
	
	
	//UI Elements
	ImageButton ibForgotPassSend;
	EditText etForgotPassEmail;
	
	
	//Variables
	Activity activity;
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.forgotpass_page);
		activity = this;
		
		
		initUI();
		
		
		ibForgotPassSend.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			}
		});
		
		
	}



	private void initUI() {
		// TODO Auto-generated method stub
		
		ibForgotPassSend = (ImageButton) findViewById(R.id.ibForgotPassSend);
		etForgotPassEmail = (EditText) findViewById(R.id.etForgotPassEmail);
		
	}

}
