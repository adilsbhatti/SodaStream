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
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.Intent;
import android.os.AsyncTask;
import au.com.sodastream.lifestylerewards.MenuActivity;
import au.com.sodastream.lifestylerewards.R;
import au.com.sodastream.lifestylerewards.Util.AppPref;
import au.com.sodastream.lifestylerewards.Util.DATA;
import au.com.sodastream.lifestylerewards.Util.Toasts;
import au.com.sodastream.lifestylerewards.Util.URLS;


public class SignupAsyncTask extends AsyncTask<String, String, Boolean> {


	Activity activity;



	AppPref appPref;

	HttpClient httpClient;
	HttpPost httpPost = null;
	HttpResponse httpResponse;
	StringEntity stringEntity;
	JSONObject jsonObject ;
	String content = "";
	String Error= "";


	public SignupAsyncTask(Activity _activity) {
		// TODO Auto-generated constructor stub
		activity = _activity;
		appPref =  new AppPref(activity);
		
		
		DATA.progressDialog.setOnCancelListener(new OnCancelListener() {

			@Override
			public void onCancel(DialogInterface dialog) {
				// TODO Auto-generated method stub

				System.out.println("-- I am called from login task");


				SignupAsyncTask.this.cancel(true);
			}
		});
	}



	@Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		super.onPreExecute();



	}


	@Override
	protected Boolean doInBackground(String... params) {
		// TODO Auto-generated method stub

		jsonObject = new JSONObject();
		try
		{

			httpClient = new DefaultHttpClient();

			httpPost = new HttpPost(URLS.REGISTER_URL);

			JSONArray jsonArray = new JSONArray();


			for(int i = 0 ; i < DATA.arrlstUses.size() ; i++)
			{
				jsonArray.put(i, DATA.arrlstUses.get(i).toLowerCase().replace(" ", "-").replace("/", "-"));
			}



			jsonObject.put("email", DATA.signupModule.email);
			jsonObject.put("password", DATA.signupModule.password);
			jsonObject.put("isFacebook", false);
			jsonObject.put("facebook", "");
			jsonObject.put("firstname", DATA.signupModule.firstname);
			jsonObject.put("surname", DATA.signupModule.surname);
			//gender error
			jsonObject.put("gender", DATA.signupModule.gender);
			jsonObject.put("state", DATA.signupModule.state);
			// status error
			jsonObject.put("status", DATA.signupModule.status.toLowerCase().replace(" ", "-"));
			jsonObject.put("use", (Object)jsonArray);
			jsonObject.put("app-secret", "some secret");
			jsonObject.put("latitude", DATA.Latitude);
			jsonObject.put("longitude", DATA.Longitude);


			System.out.println("JSON Object : " + jsonObject.toString());

			stringEntity =  new StringEntity(jsonObject.toString());
			//			 stringEntity = new StringEntity("");
			stringEntity.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
			//			 stringEntity.setContentType(new BasicHeader("token", "ck9k4wDLqW0QODBxCMRtelIPuDbFM3jhros5tVmSN6GlCr2E9LaadPpfVbyF"));
			httpPost.setEntity(stringEntity);

			httpResponse = httpClient.execute(httpPost);

			//			 

			content = EntityUtils.toString(httpResponse.getEntity());
			
			System.out.println("-- signup : " + content);


			JSONObject jsonCheckResponse = new JSONObject(content);

			if(jsonCheckResponse.has("error"))
			{
				Error =  jsonCheckResponse.getString("error");

				if(Error.contains("The email address already "))
				{
//					Error = "This email address is already in use. Please enter a different email address or use the forgotten password feature to reset your password.";
					Error =  activity.getString(R.string.ERROR_EMAIL_ALREADY_USE);
				}

				return false;

			}
			else
			{

				JSONObject jsonContent  = new JSONObject(content);
				
				// load data here
				appPref =  new AppPref(activity);
				appPref.setAccessToken(jsonContent.getString("token"));

				System.out.println("-- header : " + appPref.getAccessToken());
				System.out.println("-- Data receieved : " + content);
				return true;
			}



		}
		catch(JSONException e)
		{
			System.out.println("--1 JSON Data : " + content + "header" + httpPost.getAllHeaders()  );
			
			Error =  activity.getString(R.string.ERROR_SIGNUP_ERROR);
			
			return false;
		}
		catch(UnsupportedEncodingException e)
		{
			System.out.println("--2 JSON Data : " + content + "header" + httpPost.getAllHeaders()  );
			Error =  activity.getString(R.string.ERROR_SIGNUP_ERROR);
			return false;
		}
		catch(ClientProtocolException e)
		{
			System.out.println("--3 JSON Data : " + content + "header" + httpPost.getAllHeaders()  );
			Error =  activity.getString(R.string.ERROR_SIGNUP_ERROR);
			return false;
		}
		catch(ParseException e)
		{
			System.out.println("--4 JSON Data : " + content + "header" + httpPost.getAllHeaders()  );
			Error =  activity.getString(R.string.ERROR_SIGNUP_ERROR);
			return false;
		}
		catch(IOException e)
		{
			System.out.println("--5 JSON Data : " + content + "header" + httpPost.getAllHeaders()  );
			Error =  activity.getString(R.string.ERROR_INTERNET);
			return false;
		}
		catch (Exception e) 
		{
			System.out.println("Exception : " + e.getMessage() );
			// TODO: handle exception
			System.out.println("--6 JSON Data : " + content + "header" + httpPost.getAllHeaders()  );
			Error =  activity.getString(R.string.ERROR_SIGNUP_ERROR);
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
			Intent intent = new Intent(activity, MenuActivity.class);
			intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
			intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			activity.startActivity(intent);
			this.cancel(true);
			DATA.fromRegistration = true;
			activity.finish();
		}
		else
		{
			Toasts.pop(activity, Error);
		}

		httpClient = null;
		httpPost=null;
		httpResponse = null;
		DATA.progressDialog.dismiss();
	}


	@Override
	protected void onCancelled(Boolean result) {
		// TODO Auto-generated method stub
		super.onCancelled(result);



		DATA.progressDialog.dismiss();
	}

}
