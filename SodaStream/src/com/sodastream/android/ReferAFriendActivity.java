package com.sodastream.android;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioGroup;

public class ReferAFriendActivity extends Activity {
	
	//UI Elements
	EditText etReferName,etReferEmail;
	RadioGroup rgReferCustomer;
	ImageButton ibReferSend;
	
	//Variables
	Context  context;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.referafriend_page);
		
		context = this;
		
		initUI();
		
		
		ibReferSend.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			}
		});
		
	}

	private void initUI() {
		// TODO Auto-generated method stub
		
		etReferEmail = (EditText) findViewById(R.id.etReferEmail);
		etReferName = (EditText) findViewById(R.id.etReferName);
		
		rgReferCustomer = (RadioGroup) findViewById(R.id.rgReferCustomer);
		
		ibReferSend = (ImageButton) findViewById(R.id.ibReferSend);
		
	}

}
