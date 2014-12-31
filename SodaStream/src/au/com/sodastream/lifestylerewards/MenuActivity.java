package au.com.sodastream.lifestylerewards;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ScrollView;
import android.widget.TextView;
import au.com.sodastream.lifestylerewards.Util.AlphaNumericCodeListener;
import au.com.sodastream.lifestylerewards.Util.AppPref;
import au.com.sodastream.lifestylerewards.Util.DATA;
import au.com.sodastream.lifestylerewards.Util.Fonts;
import au.com.sodastream.lifestylerewards.Util.URLS;
import au.com.sodastream.lifestylerewards.asynctask.CodeRegistrationTask;
import au.com.sodastream.lifestylerewards.asynctask.GCMResgisterTask;
import au.com.sodastream.lifestylerewards.asynctask.LogoutAsyncTask;
import au.com.sodastream.lifestylerewards.asynctask.UserAddressUpdateTask;
import au.com.sodastream.lifestylerewards.gcm.WakeLocker;
import au.com.sodastream.lifestylerewards.modules.StatesModule;

import com.google.android.gcm.GCMRegistrar;

public class MenuActivity extends Activity implements OnClickListener {



	//UI Elemnts
	ImageButton ibMenuVouchers,ibLogout,ibMenuRewards,ibMenuNewsFaq,ibMenuRecipes,ibMenuStoreLocator,ibMenuReferFriend;
	ImageButton ibMenuTwitter,ibMenuFacebook,ibMenuPinterest,ibMenuInstagram,ibMenuYoutube;


	//Variables
	Activity activity;
	Intent intent;
	GCMResgisterTask gcmResgisterTask;
	AppPref appPref;
	LogoutAsyncTask logoutAsyncTask;
	CodeRegistrationTask codeRegistrationTask;
	UserAddressUpdateTask userAddressUpdateTask;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.menu_page);
		activity = this;



		//init global variables
		appPref  = new AppPref(activity);

		//		Toasts.pop(activity, accessTokenPref.getAccessToken());

		initUI();


		/*
		 * Enable later for PN
		 */
		enablePushNotifications();


		if(DATA.fromRegistration)
		{
			getCodeDetails(null);
			DATA.fromRegistration = false;
		}


	}


	private void initUI() {
		// TODO Auto-generated method stub

		ImageView imageView = new ImageView(activity) ;

		imageView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

			}
		});

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


			intent = new Intent(activity, NewsFaqActivity.class);
			startActivity(intent);

			break;

		case R.id.ibMenuRecipes:

			intent = new Intent(activity, RecipesActivity.class);
			startActivity(intent);
			break;


		case R.id.ibMenuReferFriend:

			intent = new Intent(activity, ReferAFriendActivity.class);
			startActivity(intent);
			break;


		case R.id.ibMenuRewards:

			if(appPref.getActivationCode().length()  < 1)
			{

				getCodeDetails(RewardsActivity.class);
			}
			else if (appPref.getPostcode().length() < 1)
			{
				getWelcomeRewardAddress(RewardsActivity.class);
			}
			else
			{
				intent = new Intent(activity, RewardsActivity.class);
				startActivity(intent);
			}



			break;


		case R.id.ibMenuStoreLocator:
			intent= new Intent(activity, StoreLocatorActivity.class);
			startActivity(intent);
			break;


		case R.id.ibMenuVouchers:



			if(appPref.getActivationCode().length()  < 1)
			{

				getCodeDetails(VouchersActivity.class);
			}
			else if (appPref.getPostcode().length() < 1)
			{
				getWelcomeRewardAddress(VouchersActivity.class);
			}
			else
			{
				intent = new Intent(activity, VouchersActivity.class);
				startActivity(intent);
			}





			break;


		case R.id.ibLogout:

			AlertDialog.Builder builder = new Builder(activity);
			builder.setTitle("Warning");
			builder.setMessage("Are you sure you want to Log out?");

			builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub

					logoutAsyncTask = new LogoutAsyncTask(activity);
					logoutAsyncTask.execute();


					//					Intent intent = new Intent(activity, SigninActivity.class);
					//					startActivity(intent);
					//					accessTokenPref.setAccessToken("");
					//					finish();


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


	public void getWelcomeRewardAddress(final Class c)
	{
		final AlertDialog alertDialog;
		Builder builder = new Builder(this);

		builder.setTitle("SodaStream");


		final EditText etStreet,etSuburb, etPostcode;
		final AutoCompleteTextView etState;
		final TextView tvSuccess, tvWelcome, tvDesc;

		etStreet =  new EditText(activity);
		etSuburb =  new EditText(activity);
		etState =  new AutoCompleteTextView(activity);
		etPostcode =  new EditText(activity);

		InputFilter[] filters = new InputFilter[1];
		filters[0] = new InputFilter.LengthFilter(4);
		etPostcode.setFilters(filters);

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, StatesModule.arrStatus);
		etState.setAdapter(adapter);

		etStreet.setHint("Street Address");
		etSuburb.setHint("Suburb");
		etState.setHint("State");
		etPostcode.setHint("Postcode");


		//		etStreet.setInputType(InputType.TYPE_TEXT_VARIATION_PERSON_NAME);
		//		etSuburb.setInputType(InputType.TYPE_TEXT_VARIATION_PERSON_NAME);
		//		etState.setInputType(InputType.TYPE_TEXT_VARIATION_PERSON_NAME);
		//		etPostcode.setInputType(InputType.TYPE_TEXT_VARIATION_PERSON_NAME);




		etStreet.setInputType(InputType.TYPE_TEXT_VARIATION_PERSON_NAME | InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_CAP_SENTENCES);
		etSuburb.setInputType(InputType.TYPE_TEXT_VARIATION_PERSON_NAME | InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_CAP_SENTENCES);
		etState.setInputType(InputType.TYPE_TEXT_VARIATION_PERSON_NAME | InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_CAP_SENTENCES);
		etPostcode.setInputType(InputType.TYPE_CLASS_NUMBER );
		//		
		//		etStreet.setSingleLine(true);
		//		etSuburb.setSingleLine(true);
		//		etState.setSingleLine(true);
		//		etPostcode.setSingleLine(true);
		//		
		//		
		//		
		//		etStreet.setImeOptions(EditorInfo.IME_ACTION_NEXT);
		//		etSuburb.setImeOptions(EditorInfo.IME_ACTION_NEXT);
		//		etState.setImeOptions(EditorInfo.IME_ACTION_NEXT);
		//		etPostcode.setImeOptions(EditorInfo.IME_ACTION_DONE);



		//		etPostcode.setInputType(InputType.TYPE_CLASS_NUMBER);
		//		etState.setKeyListener(new AlphabetListener());
		//		etSuburb.setKeyListener(new AlphabetListener());

		tvDesc =  new TextView(activity);
		tvSuccess =  new TextView(activity);
		tvWelcome =  new TextView(activity);


		final ScrollView scrollView = new ScrollView(activity);



		final LinearLayout layout = new LinearLayout(activity);
		layout.setOrientation(LinearLayout.VERTICAL);
		LayoutParams layoutParams =  new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		scrollView.setLayoutParams(layoutParams);
		layout.setLayoutParams(layoutParams);
		layout.setPadding(10, 30, 10, 60);


		tvDesc.setLayoutParams(layoutParams);
		tvSuccess.setLayoutParams(layoutParams);
		tvWelcome.setLayoutParams(layoutParams);
		tvDesc.setTypeface(Fonts.getHelvatica(activity));
		tvSuccess.setTypeface(Fonts.getHelvatica(activity));
		tvWelcome.setTypeface(Fonts.getHelvatica(activity));
		tvDesc.setTextSize(14f);
		tvSuccess.setTextSize(14f);
		tvWelcome.setTextSize(18f);
		tvDesc.setGravity(Gravity.CENTER_HORIZONTAL);
		tvSuccess.setGravity(Gravity.CENTER_HORIZONTAL);
		tvWelcome.setGravity(Gravity.CENTER_HORIZONTAL);

		//		tvWelcome.setText("Welcome to the SodaStream+ App!");
		//		tvSuccess.setText("Your rewards have been activated.");
		//		tvDesc.setText("To receive your welcome gift, please enter your preferred mailing address below.");

		tvWelcome.setText("Success!  Rewards Activated.");
		tvSuccess.setText("  Welcome to the SodaStream+ App!");
		tvDesc.setText("So we can send you a welcome gift please provide your street mailing address.");

		scrollView.addView(layout);
		layout.addView(tvWelcome);
		layout.addView(tvSuccess);

		layout.addView(tvDesc);
		layout.addView(etStreet);
		layout.addView(etSuburb);
		layout.addView(etState);
		layout.addView(etPostcode);

		builder.setView(scrollView);






		builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int whichButton) {


				DATA.USER_STATE =  etState.getText().toString();
				DATA.USER_POSTCODE =  etPostcode.getText().toString();
				DATA.USER_STREET =  etStreet.getText().toString();
				DATA.USER_SUBURB =  etSuburb.getText().toString();


				userAddressUpdateTask = new UserAddressUpdateTask(activity,c);
				userAddressUpdateTask.execute();







			}
		});

		builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int whichButton) {
				// Canceled.
			}
		});

		alertDialog = builder.create();

		alertDialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
		alertDialog.show();
		alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(false);

		TextWatcher textWatcher = new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				// TODO Auto-generated method stub




				if(etPostcode.getText().length() < 1 || etState.getText().length() < 1 || etStreet.getText().length() < 1 || etSuburb.getText().length() < 1)
				{
					alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(false);
				}
				else
				{
					alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(true);
				}

			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub

				if(etPostcode.getText().length() < 1 || etState.getText().length() < 1 || etStreet.getText().length() < 1 || etSuburb.getText().length() < 1)
				{
					alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(false);
				}
				else
				{
					alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(true);
				}

			}

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub

			}
		};

		etPostcode.addTextChangedListener(textWatcher);
		etState.addTextChangedListener(textWatcher);
		etStreet.addTextChangedListener(textWatcher);
		etSuburb.addTextChangedListener(textWatcher);


	}


	private void getCodeDetails(final Class c) {

		final AlertDialog alertDialog;
		Builder builder = new Builder(this);

		builder.setTitle("Enter Activation Code");
		builder.setMessage("Please enter a valid Machine or Gas Code. This can be found on your SodaStream product.");

		// Set an EditText view to get user input 
		final EditText etCode = new EditText(this);


		etCode.setKeyListener(new AlphaNumericCodeListener());
		InputFilter[] filters = new InputFilter[1];
		filters[0] = new InputFilter.LengthFilter(8);
		etCode.setFilters(filters);
		etCode.setHint("Enter Code");

		etCode.setFocusableInTouchMode(true);
		etCode.requestFocus();

		etCode.setInputType(InputType.TYPE_TEXT_VARIATION_PERSON_NAME | InputType.TYPE_TEXT_FLAG_CAP_WORDS);

		final InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

		imm.showSoftInput(etCode, InputMethodManager.SHOW_FORCED);

		builder.setView(etCode);

		builder.setPositiveButton("Submit", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int whichButton) {
				DATA.GAS_MACHINE_CODE = etCode.getText().toString();


				// Do something with value!
				//				intent = new Intent(activity, c);
				//				startActivity(intent);
				/*
				 * Un comment when done testing welcome reward
				 */
				codeRegistrationTask = new CodeRegistrationTask(activity, c);
				codeRegistrationTask.execute();

				//				getWelcomeRewardAddress(c);

				//				imm.hideSoftInputFromWindow(etCode.getWindowToken(),0);


			}
		});

		builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int whichButton) {
				// Canceled.
			}
		});

		alertDialog = builder.create();
		alertDialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
		alertDialog.show();


		TextWatcher textWatcher = new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				// TODO Auto-generated method stub


				if(etCode.getText().length() < 1)
				{
					alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(false);
				}
				else
				{
					alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(true);
				}

			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub

				if(etCode.getText().length() < 1)
				{
					alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(false);
				}
				else
				{
					alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(true);
				}

			}

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub

			}
		};

		etCode.addTextChangedListener(textWatcher);

	}


	private void openSocialPage(String url) {
		Intent intent;
		intent = new Intent(Intent.ACTION_VIEW);
		intent.setData(Uri.parse(url));
		startActivity(intent);
	}




	public void enablePushNotifications()
	{
		// Make sure the device has the proper dependencies.
		GCMRegistrar.checkDevice(activity);

		// Make sure the manifest was properly set - comment out this line
		// while developing the app, then uncomment it when it's ready.

		GCMRegistrar.checkManifest(activity);

		activity.registerReceiver(mHandleMessageReceiver, new IntentFilter(
				DATA.DISPLAY_MESSAGE_ACTION));

		// Get GCM registration id
		final String regId = GCMRegistrar.getRegistrationId(activity);
		DATA.regId = regId;


		System.out.println("-- 1 Device ID : " + DATA.regId);

		if (regId.equals("")) {
			// Registration is not present, register now with GCM           
			GCMRegistrar.register(activity, DATA.SENDER_ID);
			System.out.println("-- 2 Device ID : " + DATA.regId);
		} 

		else 
		{
			// Device is already registered on GCM
			if (GCMRegistrar.isRegisteredOnServer(activity))
			{
				// Skips registration.              
				//								Toast.makeText(getApplicationContext(), "Already registered with GCM", Toast.LENGTH_LONG).show();
				System.out.println("-- 3 Device ID : " + DATA.regId);
			}
			else
			{


				//*********************************************
				//Call own server registration task here to register data on server
				//*********************************************

				//				gcmResgisterTask = new GCMResgisterTask(activity);
				//				gcmResgisterTask.execute();



			}
		}
	}


	/**
	 * Receiving push messages
	 * */
	private final BroadcastReceiver mHandleMessageReceiver = new BroadcastReceiver() {
		@Override
		public void onReceive(Context context, Intent intent) {
			String newMessage = intent.getExtras().getString("New Message : " );
			// Waking up mobile if it is sleeping
			WakeLocker.acquire(activity.getApplicationContext());

			/**
			 * Take appropriate action on this message
			 * depending upon your app requirement
			 * For now i am just displaying it on the screen
			 * */

			// Showing received message
			//			lblMessage.append(newMessage + "\n");           
			//			Toast.makeText(getApplicationContext(), "New Message: " + newMessage, Toast.LENGTH_LONG).show();

			// Releasing wake lock
			WakeLocker.release();
		}
	};

	@Override
	protected void onDestroy() {

		try {
			unregisterReceiver(mHandleMessageReceiver);
			GCMRegistrar.onDestroy(activity);
		} catch (Exception e) {
			//			Log.e("UnRegister Receiver Error", "> " + e.getMessage());
		}
		super.onDestroy();
	}




	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		//		super.onBackPressed();

		AlertDialog.Builder  builder = new Builder(activity);
		builder.setTitle("Warning");
		builder.setMessage("Are you sure you want to exit SodaStream?");

		builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub

				//				intent = new Intent(activity, LoginActivity.class);
				//				startActivity(intent);
				//				System.exit(0);

				Intent intent = new Intent(Intent.ACTION_MAIN);
				intent.addCategory(Intent.CATEGORY_HOME);
				intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				startActivity(intent);
				//				activity.finish();
			}
		});


		builder.setNegativeButton("No", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				dialog.cancel();
			}
		});

		AlertDialog alertDialog = builder.create();
		alertDialog.setCanceledOnTouchOutside(false);
		alertDialog.show();





	}

}
