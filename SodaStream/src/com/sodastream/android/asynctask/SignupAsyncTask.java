package com.sodastream.android.asynctask;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;

public class SignupAsyncTask extends AsyncTask<String, String, Boolean> {
	
	
	Activity activity;
	ProgressDialog progressDialog;

	public SignupAsyncTask(Activity _activity) {
		// TODO Auto-generated constructor stub
		activity = _activity;
	}



	@Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		super.onPreExecute();

		progressDialog = new ProgressDialog(activity);
		progressDialog.setTitle("Please Wait");
		progressDialog.setMessage("Registering details");
		progressDialog.setCanceledOnTouchOutside(false);
		progressDialog.show();

	}


	@Override
	protected Boolean doInBackground(String... params) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	protected void onPostExecute(Boolean result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
		if(result)
		{

		}
		else
		{

		}

		progressDialog.dismiss();
	}


	@Override
	protected void onCancelled(Boolean result) {
		// TODO Auto-generated method stub
		super.onCancelled(result);



		progressDialog.dismiss();
	}

}
