package com.sodastream.android;

import android.app.ActionBar;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;

import com.sodastream.android.fragments.SigninTabPageAdapter;

public class SigninActivity extends FragmentActivity implements OnTabChangeListener {


	//UI Elements
	ViewPager siginViewPager;
	SigninTabPageAdapter signinTabPageAdapter;
	ActionBar signinActionBar;
	TabHost tabHost;


	//Variables
	Activity activity;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.signin_page);
		activity = this;

		// Setup UI
		initUI();

		//Set up Fragments
		initFragments();
	}


	private void initFragments() {
		// TODO Auto-generated method stub

		signinTabPageAdapter = new SigninTabPageAdapter(getSupportFragmentManager());

		signinActionBar = getActionBar();
		getActionBar().setIcon(android.R.color.transparent);
		getActionBar().setTitle("");
				

//		getActionBar().setDisplayShowHomeEnabled(false);  // hides action bar icon
//		getActionBar().setDisplayShowTitleEnabled(false);

		siginViewPager.setOnPageChangeListener(
				new ViewPager.SimpleOnPageChangeListener() {
					@Override
					public void onPageSelected(int position) {

						signinActionBar.setSelectedNavigationItem(position);                   
					}
				});

		siginViewPager.setAdapter(signinTabPageAdapter);

		signinActionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);




		ActionBar.TabListener tabListener = new ActionBar.TabListener(){
			@Override
			public void onTabReselected(android.app.ActionBar.Tab tab,
					FragmentTransaction ft) {
				// TODO Auto-generated method stub
			}
			@Override
			public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {


				siginViewPager.setCurrentItem(tab.getPosition());
			}
			@Override
			public void onTabUnselected(android.app.ActionBar.Tab tab,
					FragmentTransaction ft) {
				// TODO Auto-generated method stub
			}};



			signinActionBar.addTab(signinActionBar.newTab().setText("Sign In").setTabListener(tabListener));
			signinActionBar.addTab(signinActionBar.newTab().setText("Sign Up").setTabListener(tabListener));

	}


	private void initUI() {
		// TODO Auto-generated method stub

		siginViewPager = (ViewPager) findViewById(R.id.pager);

	}


	@Override
	public void onTabChanged(String tabId) {
		// TODO Auto-generated method stub

		System.out.println("Tab id  : " + tabId);

	}

}
