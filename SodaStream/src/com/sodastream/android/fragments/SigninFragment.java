package com.sodastream.android.fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;

import com.sodastream.android.ForgotPassActivity;
import com.sodastream.android.MenuActivity;
import com.sodastream.android.R;

public class SigninFragment extends Fragment {

	//UI Elements
	ImageButton ibSignin,ibSigninForgotPass,ibSigninFb;
	EditText etSigninEmail,etSigninPassword;

	//Variables
	Activity activity;


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


		//FB sign in click
		ibSigninFb.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

			}
		});


		return signinView;
	}


	private void initUI(View signinView) {
		ibSignin = (ImageButton) signinView.findViewById(R.id.ibSignin);
		ibSigninForgotPass = (ImageButton) signinView.findViewById(R.id.ibSigninForgotPass);
		ibSigninFb = (ImageButton) signinView.findViewById(R.id.ibSigninFb);

		etSigninEmail = (EditText) signinView.findViewById(R.id.etSigninEmail);
		etSigninPassword = (EditText) signinView.findViewById(R.id.etSigninPassword);
	}






}
