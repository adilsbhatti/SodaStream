package au.com.sodastream.lifestylerewards;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import au.com.sodastream.lifestylerewards.Util.DATA;
import au.com.sodastream.lifestylerewards.Util.Fonts;
import au.com.sodastream.lifestylerewards.Util.Toasts;
import au.com.sodastream.lifestylerewards.Util.Validate;
import au.com.sodastream.lifestylerewards.asynctask.ForgetPasswordAsyncTask;


public class ForgotPassActivity extends Activity {


	//UI Elements
	ImageButton ibForgotPassSend;
	EditText etForgotPassEmail;
	TextView   tv24,tv25;


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
		etForgotPassEmail.setTypeface(Fonts.getHelvatica(activity));

		tv24 = (TextView) findViewById(R.id.tv24);
		tv24.setTypeface(Fonts.getHelvatica(activity));

		tv25 = (TextView) findViewById(R.id.tv25);
		tv25.setTypeface(Fonts.getHelvatica(activity));
		
		tv25.setText(tv25.getText().toString().toUpperCase());

	}

}
