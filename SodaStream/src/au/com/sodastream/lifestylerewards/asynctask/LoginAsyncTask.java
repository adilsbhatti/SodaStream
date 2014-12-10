package au.com.sodastream.lifestylerewards.asynctask;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.Intent;
import android.os.AsyncTask;
import au.com.sodastream.lifestylerewards.MenuActivity;
import au.com.sodastream.lifestylerewards.Util.AppPref;
import au.com.sodastream.lifestylerewards.Util.DATA;
import au.com.sodastream.lifestylerewards.Util.Toasts;
import au.com.sodastream.lifestylerewards.Util.URLS;
import au.com.sodastream.lifestylerewards.modules.LoginModule;


public class LoginAsyncTask extends AsyncTask<String, String, Boolean>  implements DialogInterface.OnDismissListener{


	Activity activity;
	ProgressDialog progressDialog;


	HttpClient httpClient;
	HttpPost httpPost = null;
	HttpResponse httpResponse;
	StringEntity stringEntity;
	JSONObject jsonObject ;
	String content = "";
	String Error= "";

	AppPref appPref;

	public LoginAsyncTask(Activity _activity, LoginModule loginModule) {
		// TODO Auto-generated constructor stub
		activity = _activity;
		//DATA.currLoginModule =  loginModule;

		DATA.currLoginModule = new LoginModule();

		//Sample login data

		DATA.currLoginModule.email =  DATA.USER_EMAIL;
		DATA.currLoginModule.password = DATA.USER_PASSWORD;
		DATA.currLoginModule.latitude = "" + DATA.Latitude;
		DATA.currLoginModule.longitude = "" + DATA.Longitude;


		DATA.progressDialog.setOnCancelListener(new OnCancelListener() {

			@Override
			public void onCancel(DialogInterface dialog) {
				// TODO Auto-generated method stub

				System.out.println("-- I am called from login task");


				LoginAsyncTask.this.cancel(true);
			}
		});

	}



	@Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		super.onPreExecute();

		//		progressDialog = new ProgressDialog(activity);
		//		progressDialog.setTitle("Please Wait");
		//		progressDialog.setMessage("Signing in");
		//		progressDialog.setCanceledOnTouchOutside(false);
		//		progressDialog.show();



	}


	@Override
	protected Boolean doInBackground(String... params) {
		// TODO Auto-generated method stub


		try 
		{



			httpClient = new DefaultHttpClient();

			httpPost = new HttpPost(URLS.LOGIN_URL);
			jsonObject = new JSONObject();


			if(DATA.isFacebook)
			{
				jsonObject.put("facebook", DATA.currLoginModule.facebookID);		
			}
			else
			{
				jsonObject.put("password", DATA.currLoginModule.password);
			}

			jsonObject.put("email", DATA.currLoginModule.email);			 
			jsonObject.put("isFacebook", DATA.isFacebook);				 
			jsonObject.put("latitude", DATA.currLoginModule.latitude);
			jsonObject.put("longitude", DATA.currLoginModule.longitude);


			System.out.println("JSON Object : " + jsonObject.toString());

			stringEntity =  new StringEntity(jsonObject.toString());
			stringEntity.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));

			httpPost.setEntity(stringEntity);

			httpResponse = httpClient.execute(httpPost);



			content = EntityUtils.toString(httpResponse.getEntity());

			JSONObject jsonCheckResponse = new JSONObject(content);

			if(jsonCheckResponse.has("error"))
			{
				Error =  jsonCheckResponse.getString("error");



				return false;

			}
			else
			{

				JSONObject jsonContent = new JSONObject(content);

				// load data here
				appPref =  new AppPref(activity);
				
				
				try {
					appPref.setAccessToken(jsonContent.getString("token"));
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					jsonContent.getString("");
					return false;
				}
				
				try {
					appPref.setActivationCode(jsonContent.getString("activation_code"));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					appPref.setActivationCode("");
				}


				System.out.println("-- token : " + appPref.getAccessToken());
				System.out.println("-- Data receieved : " + content);
				return true;
			}


			//	System.out.println("-- Header : "+ httpResponse.getFirstHeader("token").getValue());




		} 
		catch(JSONException e)
		{
			System.out.println("--1 JSON Data : " + content + "header" + httpPost.getAllHeaders()  );
			return false;
		}
		catch(UnsupportedEncodingException e)
		{
			System.out.println("--2 JSON Data : " + content + "header" + httpPost.getAllHeaders()  );
			return false;
		}
		catch(ClientProtocolException e)
		{
			System.out.println("--3 JSON Data : " + content + "header" + httpPost.getAllHeaders()  );
			return false;
		}
		catch(ParseException e)
		{
			System.out.println("--4 JSON Data : " + content + "header" + httpPost.getAllHeaders()  );
			return false;
		}
		catch(IOException e)
		{
			System.out.println("--5 JSON Data : " + content + "header" + httpPost.getAllHeaders()  );
			return false;
		}
		catch (Exception e) 
		{
			System.out.println("Exception : " + e.getMessage() );
			// TODO: handle exception
			System.out.println("--6 JSON Data : " + content + "header" + httpPost.getAllHeaders()  );
			e.printStackTrace();
			return false;
		}


	}


	@Override
	protected void onPostExecute(Boolean result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
		if(result)
		{	

			//			if(appPref.getUserEmail().equals(""))
			//			{
			//				appPref.setUserEmail(DATA.signupModule.email);
			//			}



			Intent intent = new Intent(activity, MenuActivity.class);
			activity.startActivity(intent);
			this.cancel(true);
			activity.finish();
		}
		else
		{


			Toasts.pop(activity, "Error  : " + Error);
		}


		httpClient = null;
		httpPost=null;
		httpResponse = null;


		try {
			DATA.progressDialog.dismiss();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



	@Override
	public void onDismiss(DialogInterface dialog) {
		// TODO Auto-generated method stub
		System.out.println("-- I am called from login async task");
		this.cancel(true);

	}




}
