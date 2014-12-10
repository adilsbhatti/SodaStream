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

public class FBUserUpdateTask extends AsyncTask<String, String, Boolean> {


	Activity activity;



	AppPref appPref;

	HttpClient httpClient;
	HttpPost httpPost = null;
	HttpResponse httpResponse;
	StringEntity stringEntity;
	JSONObject jsonObject ;
	String content = "";
	String Error= "";
	ProgressDialog progressDialog;


	public FBUserUpdateTask(Activity _activity) {
		// TODO Auto-generated constructor stub
		activity = _activity;
		appPref =  new AppPref(activity);



		progressDialog = new ProgressDialog(activity);

		progressDialog.setOnCancelListener(new OnCancelListener() {

			@Override
			public void onCancel(DialogInterface dialog) {
				// TODO Auto-generated method stub

				System.out.println("-- I am called from update facebook");


				FBUserUpdateTask.this.cancel(true);
			}
		});
	}



	@Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		super.onPreExecute();

		progressDialog = new ProgressDialog(activity);
		progressDialog.setTitle("Please Wait");
		progressDialog.setMessage("Updating your details");
		progressDialog.setCanceledOnTouchOutside(false);
		progressDialog.show();

	}


	@Override
	protected Boolean doInBackground(String... params) {
		// TODO Auto-generated method stub

		jsonObject = new JSONObject();
		try
		{

			httpClient = new DefaultHttpClient();

			httpPost = new HttpPost(URLS.FACEBOOK_UPDATE_USER_URL);

			JSONArray jsonArray = new JSONArray();


			for(int i = 0 ; i < DATA.arrlstUses.size() ; i++)
			{
				jsonArray.put(i, DATA.arrlstUses.get(i).toLowerCase().replace(" ", "-").replace("/", "-"));
			}




			jsonObject.put("isFacebook", true);
			jsonObject.put("facebook", DATA.signupModule.facebookID);

			//gender error
			jsonObject.put("gender", DATA.signupModule.gender);
			jsonObject.put("state", DATA.signupModule.state);
			// status error
			jsonObject.put("status", DATA.signupModule.status.toLowerCase().replace(" ", "-"));
			jsonObject.put("use", (Object)jsonArray);



			System.out.println("JSON Object : " + jsonObject.toString());

			stringEntity =  new StringEntity(jsonObject.toString());
			//			 stringEntity = new StringEntity("");
			stringEntity.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
			//			 stringEntity.setContentType(new BasicHeader("token", "ck9k4wDLqW0QODBxCMRtelIPuDbFM3jhros5tVmSN6GlCr2E9LaadPpfVbyF"));
			httpPost.setEntity(stringEntity);

			httpResponse = httpClient.execute(httpPost);

			//			 

			content = EntityUtils.toString(httpResponse.getEntity());


			JSONObject jsonCheckResponse;

			try {
				jsonCheckResponse = new JSONObject(content);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				//				e.printStackTrace();
				jsonCheckResponse = null;
			}

			if(jsonCheckResponse!=null && jsonCheckResponse.has("error"))
			{
				Error =  jsonCheckResponse.getString("error");



				return false;

			}
			else
			{

				JSONObject jsonContent  = new JSONObject(content);

				// load data here
				//				appPref =  new AppPref(activity);
				//				appPref.setAccessToken(jsonContent.getString("token"));

				//				System.out.println("-- header : " + appPref.getAccessToken());
				System.out.println("-- Data receieved : " + content);

				if(jsonContent.getString("status").equalsIgnoreCase("Success"))
				{
					appPref.setNewFacebookUser(false);
				}
				else
				{
					appPref.setNewFacebookUser(true);
				}


				return true;
			}



		}
		catch(JSONException e)
		{
			System.out.println("--1 JSON Data : " + content + "header" + httpPost.getAllHeaders()  );
			e.printStackTrace();
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
			Intent intent = new Intent(activity, MenuActivity.class);
			intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
			intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			activity.startActivity(intent);
			this.cancel(true);
			DATA.fromRegistration = false;
			activity.finish();
		}
		else
		{
			Toasts.pop(activity, "Error  : " + Error);
		}

		httpClient = null;
		httpPost=null;
		httpResponse = null;
		progressDialog.dismiss();
	}


	@Override
	protected void onCancelled(Boolean result) {
		// TODO Auto-generated method stub
		super.onCancelled(result);



		progressDialog.dismiss();
	}
}
