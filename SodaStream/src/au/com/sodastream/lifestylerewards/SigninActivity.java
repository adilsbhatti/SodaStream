package au.com.sodastream.lifestylerewards;

import android.app.ActionBar;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import au.com.sodastream.lifestylerewards.Util.AppPref;
import au.com.sodastream.lifestylerewards.asynctask.LoginAsyncTask;
import au.com.sodastream.lifestylerewards.fragments.SigninTabPageAdapter;

import com.facebook.Session;

public class SigninActivity extends FragmentActivity implements OnTabChangeListener {


	//UI Elements
	ViewPager siginViewPager;
	SigninTabPageAdapter signinTabPageAdapter;
	ActionBar signinActionBar;
	TabHost tabHost;


	LoginAsyncTask loginAsyncTask;

	//Variables
	Activity activity;
	AppPref accessTokenPref;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.signin_page);
		activity = this;

		accessTokenPref = new AppPref(activity);

		checkUserSession();







		// Setup UI
		initUI();

		//Set up Fragments
		initFragments();
	}


	


	private void checkUserSession() {
		// TODO Auto-generated method stub


		if(accessTokenPref.getAccessToken().length() > 1 && !accessTokenPref.getNewFacebookUser())
		{
			Intent intent = new Intent(activity, MenuActivity.class);
			startActivity(intent);
			activity.finish();
		}

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


	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		Session.getActiveSession().onActivityResult(activity, requestCode, resultCode, data);



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
