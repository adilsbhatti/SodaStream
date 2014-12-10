package au.com.sodastream.lifestylerewards.fragments;

import java.util.Arrays;

import android.app.Activity;
import android.app.ProgressDialog;
import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import au.com.sodastream.lifestylerewards.ForgotPassActivity;
import au.com.sodastream.lifestylerewards.R;
import au.com.sodastream.lifestylerewards.Util.AppPref;
import au.com.sodastream.lifestylerewards.Util.CheckLocationProviders;
import au.com.sodastream.lifestylerewards.Util.ConnectionChecker;
import au.com.sodastream.lifestylerewards.Util.DATA;
import au.com.sodastream.lifestylerewards.Util.FBKeyHashGenerator;
import au.com.sodastream.lifestylerewards.Util.Fonts;
import au.com.sodastream.lifestylerewards.Util.SoftKeyboard;
import au.com.sodastream.lifestylerewards.Util.Toasts;
import au.com.sodastream.lifestylerewards.Util.Validate;
import au.com.sodastream.lifestylerewards.asynctask.GetLocationAsyncTask;
import au.com.sodastream.lifestylerewards.modules.IdFrom;
import au.com.sodastream.lifestylerewards.modules.SignupModule;

import com.facebook.Request;
import com.facebook.Response;
import com.facebook.Session;
import com.facebook.SessionState;
import com.facebook.model.GraphUser;


public class SigninFragment extends Fragment {

	//UI Elements
	ImageButton ibSignin,ibSigninForgotPass,ibSigninFb;
	EditText etSigninEmail,etSigninPassword;
	ProgressDialog progressDialog;
	TextView tv7,tv8,tv9;
	View signinView;

	//Variables
	Activity activity;
	//	LoginAsyncTask loginAsyncTask;
	GetLocationAsyncTask getLocationAsyncTask;
	AppPref appPref;

	//Facebook Variables
	//	private FacebookHandle handle;
	//	private final int ACTIVITY_SSO = 1000;
	//	private static String PERMISSIONS = "public_profile,email";




	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		signinView =inflater.inflate(R.layout.signin_frag, container,false);


		activity = getActivity();

		appPref = new AppPref(activity);
		//		System.out.println("-- I am called !!");
		initUI(signinView);







		// Sign in Click
		ibSignin.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub



				//				loginAsyncTask =  new LoginAsyncTask(activity, new LoginModule());
				//				loginAsyncTask.execute("");



				// Commented for testing

				EditText[] arrEditTexts = {etSigninEmail,etSigninPassword};
				Validate.checkEmptyEditText(arrEditTexts);

				if(Validate.isEmptyEditText(etSigninEmail) || Validate.isEmptyEditText(etSigninPassword))
				{
					Toasts.pop(activity, "Please fill in fields marked red");
				}
				else if (Validate.isEmailValid(etSigninEmail))
				{
					Toasts.pop(activity, "Please enter valied email address");
				}

				else if(!CheckLocationProviders.isGPSOrNetworkAvailable(activity))
				{
					CheckLocationProviders.showGPSDialog(activity);

				}
				else
				{

					DATA.USER_EMAIL =  etSigninEmail.getText().toString().trim();
					DATA.USER_PASSWORD = etSigninPassword.getText().toString().trim();
					getLocationAsyncTask = new  GetLocationAsyncTask(activity, IdFrom.LOGIN,"Signing in");
					getLocationAsyncTask.execute();

				}

			}
		});


		// Forgot Password Click
		ibSigninForgotPass.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub




				Intent intent = new Intent(activity, ForgotPassActivity.class);
				startActivity(intent);


			}
		});


		//Facebook sign in click
		ibSigninFb.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub




				if(CheckLocationProviders.isGPSOrNetworkAvailable(activity)  )
				{

					if(ConnectionChecker.isConnectingToInternet(activity))
					{
						openFacebookSession();
					}
				}
				else
				{
					CheckLocationProviders.showGPSDialog(activity);

				}

				System.out.println("-- fb key :  " + FBKeyHashGenerator.getHashKey(activity, "au.com.sodastream.lifestylerewards"));





			}
		});


		/*
		 * Testing only
		 */
		//		checkingSoftkeyboard();

		return signinView;
	}



	//	@Override
	//	public void onActivityResult(int requestCode, int resultCode, Intent data) {
	//		// TODO Auto-generated method stub
	//		super.onActivityResult(requestCode, resultCode, data);
	//
	//		
	//
	//
	//	}


	public void checkingSoftkeyboard()
	{
		//		LinearLayout mainLayout = (LinearLayout) getActivity().findViewById(R.id.laySign); // You must use the layout root
		InputMethodManager im = (InputMethodManager) getActivity().getSystemService(Service.INPUT_METHOD_SERVICE);

		/*
		Instantiate and pass a callback
		 */
		SoftKeyboard softKeyboard;
		softKeyboard = new SoftKeyboard((ViewGroup) signinView, im);
		softKeyboard.setSoftKeyboardCallback(new SoftKeyboard.SoftKeyboardChanged()
		{

			@Override
			public void onSoftKeyboardHide() 
			{
				// Code here
				System.out.println("-- key hidden");
			}

			@Override
			public void onSoftKeyboardShow() 
			{
				// Code here
				System.out.println("-- key shown	");
			}	
		});
	}

	private void initUI(View signinView) {
		ibSignin = (ImageButton) signinView.findViewById(R.id.ibSignin);
		ibSigninForgotPass = (ImageButton) signinView.findViewById(R.id.ibSigninForgotPass);
		ibSigninFb = (ImageButton) signinView.findViewById(R.id.ibSigninFb);

		etSigninEmail = (EditText) signinView.findViewById(R.id.etSigninEmail);
		etSigninPassword = (EditText) signinView.findViewById(R.id.etSigninPassword);

		etSigninEmail.setTypeface(Fonts.getHelvatica(activity));
		etSigninPassword.setTypeface(Fonts.getHelvatica(activity));

		tv7 = (TextView) signinView.findViewById(R.id.tv7);	
		tv7.setTypeface(Fonts.getHelvatica(activity));

		tv8 = (TextView) signinView.findViewById(R.id.tv8);	
		tv8.setTypeface(Fonts.getHelvatica(activity));

		tv9 = (TextView) signinView.findViewById(R.id.tv9);	
		tv9.setTypeface(Fonts.getHelvatica(activity));
	}


	private void openFacebookSession() {


		progressDialog = new ProgressDialog(activity);
		progressDialog.setCanceledOnTouchOutside(false);
		progressDialog.setTitle("Please wait");
		progressDialog.setMessage("Signing in with Facebook");
		progressDialog.show();


		Session.openActiveSession(
				activity,
				true,
				Arrays.asList(new String[] { "email", "user_birthday",
						"public_profile", "user_location" }),
						new Session.StatusCallback() {


					@Override
					public void call(Session session, SessionState state,
							Exception exception) {


						System.out.println("-- Sessions : " + session.isOpened());

						if(session.isOpened())
						{
							Request.newMeRequest(session, new Request.GraphUserCallback() {




								@Override
								public void onCompleted(GraphUser user, Response response) {
									// TODO Auto-generated method stub

									System.out.println("-- Fb Compelted : ");
									progressDialog.dismiss();

									try {

										DATA.signupModule = new SignupModule();

										DATA.signupModule.firstname = user.getFirstName() ;
										DATA.signupModule.surname =user.getLastName();
										DATA.signupModule.email =   "w" + user.asMap().get("email").toString();

										// Change it back in original build
										DATA.signupModule.facebookID =  user.getId() + "13";

										appPref.setFacebookID(user.getId());
										DATA.isFacebook = true;

										int gender = (user.asMap().get("gender").toString().equalsIgnoreCase("male")   ? 1: 2);
										DATA.signupModule.gender =  gender;



										getLocationAsyncTask = new  GetLocationAsyncTask(activity, IdFrom.FACEBOOK_LOGIN,"Signing in via Facebook");
										getLocationAsyncTask.execute();

										//										Intent intent = new Intent(activity, MenuActivity.class);
										//										startActivity(intent);


									} catch (Exception e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}






								}
							}).executeAsync();
						}

						Log.d("Facebook",
								"Session State: " + session.getState());
						// you can make request to the /me API or do other stuff
						// like post, etc. here
					}
				});



	}






}
