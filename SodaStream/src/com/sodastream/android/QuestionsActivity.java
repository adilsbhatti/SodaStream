package com.sodastream.android;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.CheckedTextView;
import android.widget.Spinner;
import android.widget.Toast;

import com.sodastream.android.modules.StatesModule;
import com.sodastream.android.modules.StatusModule;

public class QuestionsActivity extends Activity  implements OnClickListener {

	//UI Elements
	CheckedTextView ctvSparklingWater, ctvSoftDrinks,ctvCocktails,ctvOther;
	Spinner sStates,sStatus;

	//Variables
	Activity activity;
	OnClickListener checkboxtextClickListener;
	ArrayAdapter<String> adapStates,adapStatus;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.questions_page);
		activity = this;


		initUI();

	
		
		loadSpinners();




	}

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
		
		sStates =  (Spinner) findViewById(R.id.sStates);
		sStatus =  (Spinner) findViewById(R.id.sStatus);



	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

		CheckedTextView checkedTextView = (CheckedTextView) v.findViewById(v.getId());
		if (checkedTextView.isChecked())
		{
			checkedTextView.setChecked(false);
		}
		else
		{
			checkedTextView.setChecked(true);

		}
		Toast.makeText(activity, "checked : " + checkedTextView.isChecked(), 0).show();

	}

}
