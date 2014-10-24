package com.sodastream.android.fragments;

import java.util.Arrays;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;

import com.facebook.Request;
import com.facebook.Response;
import com.facebook.Session;
import com.facebook.SessionState;
import com.facebook.model.GraphUser;
import com.sodastream.android.ForgotPassActivity;
import com.sodastream.android.MenuActivity;
import com.sodastream.android.R;

public class SigninFragment extends Fragment {

	//UI Elements
	ImageButton ibSignin,ibSigninForgotPass,ibSigninFb;
	EditText etSigninEmail,etSigninPassword;
	ProgressDialog progressDialog;

	//Variables
	Activity activity;

	//Facebook Variables
	//	private FacebookHandle handle;
	//	private final int ACTIVITY_SSO = 1000;
	//	private static String PERMISSIONS = "public_profile,email";


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View signinView =inflater.inflate(R.layout.signin_frag, container,false);


		initUI(signinView);


		activity = getActivity();


		// Sign in Click
		ibSignin.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(activity, MenuActivity.class);
				activity.startActivity(intent);
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

				openFacebookSession();




				//				try {
				//			        PackageInfo info = activity.getPackageManager().getPackageInfo(
				//			                "com.sodastream.android", 
				//			                PackageManager.GET_SIGNATURES);
				//			        for (Signature signature : info.signatures) {
				//			            MessageDigest md = MessageDigest.getInstance("SHA");
				//			            md.update(signature.toByteArray());
				//			            Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
				//			            }
				//			    } catch (NameNotFoundException e) {
				//
				//			    } catch (NoSuchAlgorithmException e) {
				//
				//			    }

			}
		});


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

	private void initUI(View signinView) {
		ibSignin = (ImageButton) signinView.findViewById(R.id.ibSignin);
		ibSigninForgotPass = (ImageButton) signinView.findViewById(R.id.ibSigninForgotPass);
		ibSigninFb = (ImageButton) signinView.findViewById(R.id.ibSigninFb);

		etSigninEmail = (EditText) signinView.findViewById(R.id.etSigninEmail);
		etSigninPassword = (EditText) signinView.findViewById(R.id.etSigninPassword);
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
									
									
									
									try {
										System.out.println("--" + user.asMap().get("email").toString()); // Aftab Saraz
										System.out.println("--" + user.getFirstName() + user.getLastName() ); // Aftab Saraz
										System.out.println("--" + user.asMap().get("gender").toString()); // Aftab Saraz
										System.out.println("--" + user.getId() ); // Aftab Saraz

										Intent intent = new Intent(activity, MenuActivity.class);
										startActivity(intent);
									} catch (Exception e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}



									progressDialog.dismiss();


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
