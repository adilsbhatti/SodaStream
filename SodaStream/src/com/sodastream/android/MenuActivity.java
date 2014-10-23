package com.sodastream.android;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageButton;

import com.google.android.gms.internal.bu;
import com.sodastream.android.Util.DATA;
import com.sodastream.android.Util.URLS;

public class MenuActivity extends Activity implements OnClickListener {


	//This is test
	//UI Elemnts
	ImageButton ibMenuVouchers,ibLogout,ibMenuRewards,ibMenuNewsFaq,ibMenuRecipes,ibMenuStoreLocator,ibMenuReferFriend;
	ImageButton ibMenuTwitter,ibMenuFacebook,ibMenuPinterest,ibMenuInstagram,ibMenuYoutube;


	//Variables
	Context context;
	Intent intent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.menu_page);
		context = this;


		initUI();

	}


	private void initUI() {
		// TODO Auto-generated method stub

		ibLogout = (ImageButton) findViewById(R.id.ibLogout);
		ibMenuRewards = (ImageButton) findViewById(R.id.ibMenuRewards);
		ibMenuNewsFaq = (ImageButton) findViewById(R.id.ibMenuNewsFaq);
		ibMenuRecipes = (ImageButton) findViewById(R.id.ibMenuRecipes);
		ibMenuStoreLocator = (ImageButton) findViewById(R.id.ibMenuStoreLocator);
		ibMenuReferFriend = (ImageButton) findViewById(R.id.ibMenuReferFriend);
		ibMenuTwitter = (ImageButton) findViewById(R.id.ibMenuTwitter);
		ibMenuPinterest = (ImageButton) findViewById(R.id.ibMenuPinterest);
		ibMenuYoutube = (ImageButton) findViewById(R.id.ibMenuYoutube);
		ibMenuFacebook = (ImageButton) findViewById(R.id.ibMenuFacebook);
		ibMenuInstagram = (ImageButton) findViewById(R.id.ibMenuInstagram);		
		ibMenuVouchers = (ImageButton) findViewById(R.id.ibMenuVouchers);

		ibLogout.setOnClickListener(this);
		ibMenuFacebook.setOnClickListener(this);
		ibMenuInstagram.setOnClickListener(this);
		ibMenuNewsFaq.setOnClickListener(this);
		ibMenuPinterest.setOnClickListener(this);
		ibMenuRecipes.setOnClickListener(this);
		ibMenuReferFriend.setOnClickListener(this);
		ibMenuRewards.setOnClickListener(this);
		ibMenuStoreLocator.setOnClickListener(this);
		ibMenuTwitter.setOnClickListener(this);
		ibMenuYoutube.setOnClickListener(this);
		ibMenuVouchers.setOnClickListener(this);

	}


	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

		int buttonId = v.getId();



		switch (buttonId) {
		case R.id.ibMenuFacebook:


			openSocialPage(URLS.facebookURL);
			break;

		case R.id.ibMenuInstagram:

			openSocialPage(URLS.instagramURL);

			break;

		case R.id.ibMenuTwitter:

			openSocialPage(URLS.twitterURL);

			break;

		case R.id.ibMenuPinterest:

			openSocialPage(URLS.pinterestURL);

			break;

		case R.id.ibMenuYoutube:

			openSocialPage(URLS.youtubeURL);

			break;

		case R.id.ibMenuNewsFaq:


			intent = new Intent(context, NewsFaqActivity.class);
			startActivity(intent);

			break;

		case R.id.ibMenuRecipes:

			intent = new Intent(context, RecipesActivity.class);
			startActivity(intent);
			break;


		case R.id.ibMenuReferFriend:

			intent = new Intent(context, ReferAFriendActivity.class);
			startActivity(intent);
			break;


		case R.id.ibMenuRewards:
			
			
			getCodeDetails(RewardsActivity.class);
			
			

			break;


		case R.id.ibMenuStoreLocator:
			intent = new Intent(context, StoreLocatorActivity.class);
			startActivity(intent);
			break;


		case R.id.ibMenuVouchers:



			getCodeDetails(VouchersActivity.class);





			break;


		case R.id.ibLogout:

			AlertDialog.Builder builder = new Builder(context);
			builder.setTitle("Warning");
			builder.setMessage("Are you sure you want to logout?");

			builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub

				}
			});

			builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					dialog.dismiss();

				}
			});

			builder.show();


			break;




		}

	}


	private void getCodeDetails(final Class c) {
		AlertDialog.Builder alert = new AlertDialog.Builder(this);

		alert.setTitle("Sodastream");
		alert.setMessage("Enter Machine or Gas Code");

		// Set an EditText view to get user input 
		final EditText code = new EditText(this);
		alert.setView(code);

		alert.setPositiveButton("Submit", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int whichButton) {
				DATA.GAS_MACHINE_CODE = code.getText().toString();
				// Do something with value!
				intent = new Intent(context, c);
				startActivity(intent);
			}
		});

		alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int whichButton) {
				// Canceled.
			}
		});

		alert.show();
	}


	private void openSocialPage(String url) {
		Intent intent;
		intent = new Intent(Intent.ACTION_VIEW);
		intent.setData(Uri.parse(url));
		startActivity(intent);
	}

}
