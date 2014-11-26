package au.com.sodastream.lifestylerewards;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;
import au.com.sodastream.lifestylerewards.Util.DATA;
import au.com.sodastream.lifestylerewards.Util.Fonts;
import au.com.sodastream.lifestylerewards.Util.Toasts;
import au.com.sodastream.lifestylerewards.Util.Validate;
import au.com.sodastream.lifestylerewards.asynctask.ReferAFriendAsyncTask;
import au.com.sodastream.lifestylerewards.modules.ReferFriendModule;


public class ReferAFriendActivity extends Activity {

	//UI Elements
	EditText etReferName,etReferEmail;
	RadioGroup rgReferCustomer;
	ImageButton ibReferSend;
	TextView tv11,tv12;
	RadioButton rNewUser, rExistingUser, rExistingNonUser;

	//Variables
	Activity  activity;
	String selectedUserType;
	ReferAFriendAsyncTask referAFriendAsyncTask;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.referafriend_page);

		activity = this;
		selectedUserType =  "new-user";

		initUI();


		ibReferSend.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				EditText[] arrEditTexts =  {etReferEmail,etReferName};
				Validate.checkEmptyEditText(arrEditTexts);

				if(Validate.isEmptyEditText(etReferName) || Validate.isEmptyEditText(etReferEmail))
				{
					Toasts.pop(activity	, "Fields in red are required");
				}
				else if (selectedUserType.length() < 1)
				{

				}
				else if (Validate.isEmailValid(etReferEmail))
				{
					Toasts.pop(activity, "Invalid email format");
				}
				else
				{
					DATA.referFriendModule =  new ReferFriendModule();

					DATA.referFriendModule.email = etReferEmail.getText().toString();
					DATA.referFriendModule.name =  etReferName.getText().toString();
					DATA.referFriendModule.userType =  selectedUserType;

					referAFriendAsyncTask = new ReferAFriendAsyncTask(activity);
					referAFriendAsyncTask.execute();
				}

			}
		});



		rgReferCustomer.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				// TODO Auto-generated method stub
				switch (checkedId) {

				case R.id.rNewUser:
					selectedUserType = "new-user";
					break;



				case R.id.rExistingUser:
					selectedUserType = "existing-user";
					break;


				case R.id.rExistingNonUser:
					selectedUserType = "existing-non-user";
					break;
				}
			}
		});

	}

	private void initUI() {
		// TODO Auto-generated method stub

		etReferEmail = (EditText) findViewById(R.id.etReferEmail);
		etReferName = (EditText) findViewById(R.id.etReferName);

		etReferEmail.setTypeface(Fonts.getHelvatica(activity));
		etReferName.setTypeface(Fonts.getHelvatica(activity));

		tv11 = (TextView) findViewById(R.id.tv11);
		tv11.setTypeface(Fonts.getHelvatica(activity));

		tv12 = (TextView) findViewById(R.id.tv12);
		tv12.setTypeface(Fonts.getHelvatica(activity));

		rgReferCustomer = (RadioGroup) findViewById(R.id.rgReferCustomer);

		ibReferSend = (ImageButton) findViewById(R.id.ibReferSend);

		rgReferCustomer.check(R.id.rNewUser);
		
		rExistingNonUser = (RadioButton) findViewById(R.id.rExistingNonUser);
		rExistingNonUser.setTypeface(Fonts.getHelvatica(activity));
		
		rNewUser = (RadioButton) findViewById(R.id.rNewUser);
		rNewUser.setTypeface(Fonts.getHelvatica(activity));
		
		rExistingUser = (RadioButton) findViewById(R.id.rExistingUser);
		rExistingUser.setTypeface(Fonts.getHelvatica(activity));

	}

	public void clearFields()
	{
		etReferEmail.setText("");
		etReferName.setText("");
		selectedUserType = "new-user";
		rgReferCustomer.check(R.id.rNewUser);
		etReferName.requestFocus();

	}

}
