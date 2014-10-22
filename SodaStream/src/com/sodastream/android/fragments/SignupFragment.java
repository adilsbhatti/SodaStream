package com.sodastream.android.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioGroup;

import com.sodastream.android.R;

public class SignupFragment extends Fragment {


	//UI Elements


	EditText etSignupFirstName,etSignupEmail,etSignupPass,etSignupConfPass,etSignupWhySodastream,etSignupState,etSignupRelationStatus;
	ImageButton ibSignup;
	RadioGroup rgSignupGender;


	//Variables
	Activity activity;

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
				// TODO Auto-generated method stub

			}
		});


		return signupView;
	}

	private void initUI(View signupView) {
		etSignupConfPass = (EditText) signupView.findViewById(R.id.etSignupConfPass);
		etSignupFirstName = (EditText) signupView.findViewById(R.id.etSignupFirstName);
		etSignupEmail = (EditText) signupView.findViewById(R.id.etSignupEmail);
		etSignupPass = (EditText) signupView.findViewById(R.id.etSignupPass);
		etSignupWhySodastream = (EditText) signupView.findViewById(R.id.etSignupWhySodastream);
		etSignupState = (EditText) signupView.findViewById(R.id.etSignupState);
		etSignupRelationStatus = (EditText) signupView.findViewById(R.id.etSignupRelationStatus);

		ibSignup = (ImageButton) signupView.findViewById(R.id.ibSignup);

		rgSignupGender = (RadioGroup) signupView.findViewById(R.id.rgSignupGender);


	}

}
