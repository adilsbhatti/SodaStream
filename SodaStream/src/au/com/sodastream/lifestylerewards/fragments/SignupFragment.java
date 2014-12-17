package au.com.sodastream.lifestylerewards.fragments;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;
import au.com.sodastream.lifestylerewards.QuestionsActivity;
import au.com.sodastream.lifestylerewards.R;
import au.com.sodastream.lifestylerewards.Util.CheckLocationProviders;
import au.com.sodastream.lifestylerewards.Util.DATA;
import au.com.sodastream.lifestylerewards.Util.Fonts;
import au.com.sodastream.lifestylerewards.Util.Toasts;
import au.com.sodastream.lifestylerewards.Util.Validate;
import au.com.sodastream.lifestylerewards.modules.SignupModule;


public class SignupFragment extends Fragment {


	//UI Elements


	EditText etSignupFirstName,etSignupEmail,etSignupPass,etSignupConfPass,etSignupSurName;
	ImageButton ibSignup;
	TextView tvSignupTerms,tv5,tv6;
	RadioGroup rgSignupGender;


	//Variables
	Activity activity;
	int selectedGender  = 1;  // Male = 1, Female = 2

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View signupView =inflater.inflate(R.layout.singnup_frag, container,false);
		activity = getActivity();

		initUI(signupView);


		//Sign up click

		ibSignup.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				//Comment For testing 
				// TODO Auto-generated method stub
				//				SignupAsyncTask signupAsyncTask = new SignupAsyncTask(activity);
				//				signupAsyncTask.execute("");


				EditText[] arrEditTexts = {etSignupConfPass,etSignupEmail,etSignupFirstName,etSignupPass,etSignupSurName};

				Validate.checkEmptyEditText(arrEditTexts);
				if(Validate.isEmptyEditText(etSignupConfPass) || Validate.isEmptyEditText(etSignupEmail) || Validate.isEmptyEditText(etSignupFirstName) || Validate.isEmptyEditText(etSignupPass)   ||   Validate.isEmptyEditText(etSignupSurName))
				{
					Toasts.pop(activity, "Please complete all fields (marked red)");
				}
				else if (Validate.isEmailValid(etSignupEmail))
				{
					Toasts.pop(activity, "Invalid email format");
				}
				else if (etSignupPass.getText().toString().length() < 6 || etSignupConfPass.getText().toString().length() < 6)
				{
					Toasts.pop(activity, "Passwords should be atleast 6 characters");
				}
				else if (etSignupPass.getText().toString().compareTo(etSignupConfPass.getText().toString()) != 0)
				{
					Toasts.pop(activity, "Passwords should match");
				}
				else if(!CheckLocationProviders.isGPSOrNetworkAvailable(activity))
				{
					CheckLocationProviders.showGPSDialog(activity);
				}
				else
				{


					
					//Get values for sign up here

					DATA.signupModule = new SignupModule();

					DATA.signupModule.firstname = etSignupFirstName.getText().toString().trim();
					DATA.signupModule.surname = etSignupSurName.getText().toString().trim();
					DATA.signupModule.email =  etSignupEmail.getText().toString().trim();
					DATA.signupModule.password =  etSignupPass.getText().toString().trim();
					DATA.signupModule.gender =  selectedGender;

//					Toasts.pop(activity, "Selected gender : " + DATA.signupModule.gender);
					DATA.fromRegistration = true;
					Intent intent = new Intent(activity, QuestionsActivity.class);
					startActivity(intent);
				}


			}
		});


		tvSignupTerms.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				AlertDialog.Builder builder = new Builder(activity);
				builder.setTitle("SodaStream - Terms & Conditions");

				WebView webView = new WebView(activity);
				webView.loadUrl("file:///android_asset/termsconditions.html");


				builder.setView(webView);

				builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						dialog.dismiss();

					}
				});


				AlertDialog alertDialog = builder.create();
				alertDialog.setCanceledOnTouchOutside(false);
				alertDialog.show();

			}
		});



		rgSignupGender.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				// TODO Auto-generated method stub
				switch (checkedId) {
				case R.id.rMale:
					selectedGender = 1;
					break;

				case R.id.rFemale:
					selectedGender = 2;

				default:
					break;
				}
			}
		});

		return signupView;
	}

	private void initUI(View signupView) {

		etSignupConfPass = (EditText) signupView.findViewById(R.id.etSignupConfPass);
		etSignupFirstName = (EditText) signupView.findViewById(R.id.etSignupFirstName);
		etSignupSurName = (EditText) signupView.findViewById(R.id.etSignupSurName);
		etSignupEmail = (EditText) signupView.findViewById(R.id.etSignupEmail);
		etSignupPass = (EditText) signupView.findViewById(R.id.etSignupPass);


		etSignupConfPass.setTypeface(Fonts.getHelvatica(activity));
		etSignupFirstName.setTypeface(Fonts.getHelvatica(activity));
		etSignupSurName.setTypeface(Fonts.getHelvatica(activity));
		etSignupEmail.setTypeface(Fonts.getHelvatica(activity));
		etSignupPass.setTypeface(Fonts.getHelvatica(activity));


		tvSignupTerms = (TextView) signupView.findViewById(R.id.tvSignupTerms);
		tvSignupTerms.setTypeface(Fonts.getHelvatica(activity));
		
		tv5 = (TextView) signupView.findViewById(R.id.tv5);
		tv5.setTypeface(Fonts.getHelvatica(activity));
		
		tv6 = (TextView) signupView.findViewById(R.id.tv6);
		tv6.setTypeface(Fonts.getHelvatica(activity));

		ibSignup = (ImageButton) signupView.findViewById(R.id.ibSignup);

		rgSignupGender = (RadioGroup) signupView.findViewById(R.id.rgSignupGender);

		tvSignupTerms.setText(Html.fromHtml("By signing up, you agree that you have read, understood, and accept the <u><b>terms and conditions</b></u> described."));


	}

}
