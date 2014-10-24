package com.sodastream.android.asynctask;

import android.app.Activity;
import android.os.AsyncTask;

import com.google.android.gcm.ServerUtilities;
import com.sodastream.android.Util.DATA;

public class GCMResgisterTask extends AsyncTask<Void, Void, Boolean> {


	Activity activity;

	public GCMResgisterTask(Activity _activity) {
		// TODO Auto-generated constructor stub
		activity = _activity;
	}




	@Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		super.onPreExecute();

		
	}




	@Override
	protected Boolean doInBackground(Void... params) {
		// TODO Auto-generated method stub
		
		ServerUtilities.register(activity, DATA.name, DATA.email, DATA.regId);
		return null;
	}




	@Override
	protected void onPostExecute(Boolean result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
	}
	
	
}
