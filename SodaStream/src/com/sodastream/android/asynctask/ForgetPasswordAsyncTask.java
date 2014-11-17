package com.sodastream.android.asynctask;

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
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;

import com.facebook.LoginActivity;
import com.sodastream.android.Util.AppPref;
import com.sodastream.android.Util.DATA;
import com.sodastream.android.Util.Toasts;
import com.sodastream.android.Util.URLS;

public class ForgetPasswordAsyncTask extends AsyncTask<String, String, Boolean> {


	Activity activity;
	ProgressDialog progressDialog;

	HttpClient httpClient;
	HttpPost httpPost = null;
	HttpResponse httpResponse;
	StringEntity stringEntity;
	JSONObject jsonObject ;
	String content = "";
	String Error= "";

	AppPref accessTokenPref;

	public ForgetPasswordAsyncTask(Activity _activity) {
		// TODO Auto-generated constructor stub
		activity = _activity;
		accessTokenPref = new AppPref(activity);
	}



	@Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		super.onPreExecute();

		progressDialog = new ProgressDialog(activity);
		progressDialog.setTitle("Please Wait");
		progressDialog.setMessage("Sending details");
		progressDialog.setCanceledOnTouchOutside(false);
		progressDialog.show();

	}


	@Override
	protected Boolean doInBackground(String... params) {
		// TODO Auto-generated method stub


		try 
		{



			httpClient = new DefaultHttpClient();

			httpPost = new HttpPost(URLS.FORGOT_PASSWORD_URL);
			jsonObject = new JSONObject();




			jsonObject.put("email", DATA.forgot_pass_email);			 



			System.out.println("JSON Object : " + jsonObject.toString());



			stringEntity =  new StringEntity(jsonObject.toString());
			stringEntity.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
			stringEntity.setContentType(new BasicHeader("token",  accessTokenPref.getAccessToken()));

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


				//				System.out.println("-- header : " + httpPost.`);
				System.out.println("-- Data receieved : " + content);
				return true;
			}




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
			AlertDialog.Builder builder  = new  Builder(activity);
			builder.setTitle("Infrmation");
			builder.setMessage("Password recovery email has been sent");
			builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {

				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub

					Intent intent = new Intent(activity, LoginActivity.class);
					activity.startActivity(intent);
					activity.finish();

					dialog.dismiss();	
				}
			});
			
			
			AlertDialog alertDialog = builder.create();
			alertDialog.setCanceledOnTouchOutside(false);
			alertDialog.show();

		}
		else
		{

			Toasts.pop(activity, "Error  : " + Error);

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
