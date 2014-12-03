package au.com.sodastream.lifestylerewards;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.CheckedTextView;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import au.com.sodastream.lifestylerewards.Util.CheckLocationProviders;
import au.com.sodastream.lifestylerewards.Util.ConnectionChecker;
import au.com.sodastream.lifestylerewards.Util.DATA;
import au.com.sodastream.lifestylerewards.Util.Fonts;
import au.com.sodastream.lifestylerewards.Util.Toasts;
import au.com.sodastream.lifestylerewards.asynctask.FBUserUpdateTask;
import au.com.sodastream.lifestylerewards.asynctask.GetLocationAsyncTask;
import au.com.sodastream.lifestylerewards.modules.IdFrom;
import au.com.sodastream.lifestylerewards.modules.StatesModule;
import au.com.sodastream.lifestylerewards.modules.StatusModule;


public class QuestionsActivity extends Activity  implements OnClickListener {

	//UI Elements
	CheckedTextView ctvSparklingWater, ctvSoftDrinks,ctvCocktails,ctvOther;
	Spinner sStates,sStatus;
	ImageButton ibSignUpUser;
	TextView tv18, tv19, tv20;

	//Variables
	Activity activity;
	OnClickListener checkboxtextClickListener;
	ArrayAdapter<String> adapStates,adapStatus;
	//	ArrayList<String> arrlstUses;
	GetLocationAsyncTask getLocationAsyncTask;
	FBUserUpdateTask fbUserUpdateTask;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.questions_page);
		activity = this;
		DATA.arrlstUses = new ArrayList<String>();

		initUI();

		//		if(!DATA.fromRegistration)
		//		{
		//			Intent intent = new Intent(activity, SplashActivity.class);
		//			intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		//			startActivity(intent);
		//		}



		loadSpinners();

		ibSignUpUser.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub



				//				setUses();

				if(DATA.arrlstUses.size() < 1)
				{
					Toasts.pop(activity, "Please select atleast one use for Sodastream");
				}
				else if(!CheckLocationProviders.isGPSOrNetworkAvailable(activity))
				{
					CheckLocationProviders.showGPSDialog(activity);
				}
				else if (!ConnectionChecker.isConnectingToInternet(activity))
				{
					//					Toasts.pop(activity, "No internet connectivity available, please check your internet settings");
				}
				else
				{


					DATA.signupModule.state =  sStates.getSelectedItem().toString();
					DATA.signupModule.status = sStatus.getSelectedItem().toString();



					//					Toasts.pop(activity, "All is well : " + DATA.signupModule.state + " status  : " + DATA.signupModule.status);

					if(DATA.isFacebook)
					{
							Toasts.pop(activity, "call FB update task");
							fbUserUpdateTask = new FBUserUpdateTask(activity);
							fbUserUpdateTask.execute();
					}
					else
					{	

						getLocationAsyncTask =  new GetLocationAsyncTask(activity, IdFrom.SIGN_UP, "Registering details");
						getLocationAsyncTask.execute();
					}

				}

			}
		});






	}



	//	public void setUses()
	//	{
	//		//		DATA.signupModule.uses = Arrays.deepToString(arrlstUses.toArray()).replace("[", "").replace("]", "");
	//		//		Toasts.pop(activity, DATA.signupModule.uses, true);
	//	}

	private void loadSpinners() {
		// TODO Auto-generated method stub

		adapStates = new ArrayAdapter<String>(activity, android.R.layout.simple_spinner_item, StatesModule.arrStatus);
		adapStates.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		sStates.setAdapter(adapStates);



		adapStatus = new ArrayAdapter<String>(activity, android.R.layout.simple_spinner_item,StatusModule.arrStatus);
		adapStatus.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		sStatus.setAdapter(adapStatus);

	}

	private void initUI() {
		// TODO Auto-generated method stub
		ctvCocktails = (CheckedTextView) findViewById(R.id.ctvCocktails);
		ctvOther = (CheckedTextView) findViewById(R.id.ctvOther);
		ctvSoftDrinks = (CheckedTextView) findViewById(R.id.ctvSoftDrinks);
		ctvSparklingWater = (CheckedTextView) findViewById(R.id.ctvSparklingWater);

		ctvCocktails.setOnClickListener(this);
		ctvOther.setOnClickListener(this);
		ctvSoftDrinks.setOnClickListener(this);
		ctvSparklingWater.setOnClickListener(this);

		ctvCocktails.setTypeface(Fonts.getHelvatica(activity));
		ctvOther.setTypeface(Fonts.getHelvatica(activity));
		ctvSoftDrinks.setTypeface(Fonts.getHelvatica(activity));
		ctvSparklingWater.setTypeface(Fonts.getHelvatica(activity));

		sStates =  (Spinner) findViewById(R.id.sStates);
		sStatus =  (Spinner) findViewById(R.id.sStatus);

		ibSignUpUser  = (ImageButton) findViewById(R.id.ibSignUpUser);

		tv18 = (TextView) findViewById(R.id.tv18);
		tv18.setOnClickListener(this);

		tv19 = (TextView) findViewById(R.id.tv19);
		tv19.setOnClickListener(this);

		tv20 = (TextView) findViewById(R.id.tv20);
		tv20.setOnClickListener(this);



	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

		CheckedTextView checkedTextView = (CheckedTextView) v.findViewById(v.getId());
		if (checkedTextView.isChecked())
		{
			checkedTextView.setChecked(false);
			DATA.arrlstUses.remove(checkedTextView.getText().toString());

		}
		else
		{
			checkedTextView.setChecked(true);
			DATA.arrlstUses.add(checkedTextView.getText().toString());

		}
		//		Toast.makeText(activity, "checked : " + checkedTextView.isChecked(), 0).show();

	}


	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();

		finish();
	}

}
