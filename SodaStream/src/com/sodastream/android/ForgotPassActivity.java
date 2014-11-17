package com.sodastream.android;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageButton;

import com.sodastream.android.Util.DATA;
import com.sodastream.android.Util.Toasts;
import com.sodastream.android.Util.Validate;
import com.sodastream.android.asynctask.ForgetPasswordAsyncTask;

public class ForgotPassActivity extends Activity {
	
	
	//UI Elements
	ImageButton ibForgotPassSend;
	EditText etForgotPassEmail;
	
	
	//Variables
	Activity activity;
	ForgetPasswordAsyncTask forgetPasswordAsyncTask;
	
	
	
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
				
				if(Validate.isEmptyEditText(etForgotPassEmail))
				{
					Toasts.pop(activity, "Enter email address");
				}
				else if(Validate.isEmailValid(etForgotPassEmail.getText().toString()))
				{
					Toasts.pop(activity, "Invalid email address format");
				}
				
				else
				{
				DATA.forgot_pass_email =  etForgotPassEmail.getText().toString().trim();
				forgetPasswordAsyncTask =  new ForgetPasswordAsyncTask(activity);
				forgetPasswordAsyncTask.execute();
				}
				
			}
		});
		
		
	}



	private void initUI() {
		// TODO Auto-generated method stub
		
		ibForgotPassSend = (ImageButton) findViewById(R.id.ibForgotPassSend);
		etForgotPassEmail = (EditText) findViewById(R.id.etForgotPassEmail);
		
	}

}
