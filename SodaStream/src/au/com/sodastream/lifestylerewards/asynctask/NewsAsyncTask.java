package au.com.sodastream.lifestylerewards.asynctask;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;

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
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.os.AsyncTask;
import au.com.sodastream.lifestylerewards.NewsActivity;
import au.com.sodastream.lifestylerewards.Util.AppPref;
import au.com.sodastream.lifestylerewards.Util.DATA;
import au.com.sodastream.lifestylerewards.Util.Toasts;
import au.com.sodastream.lifestylerewards.Util.URLS;
import au.com.sodastream.lifestylerewards.modules.NewsModule;

import com.google.gson.Gson;


public class NewsAsyncTask extends AsyncTask<String, String, Boolean> {

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

	public NewsAsyncTask(Activity _activity) {
		// TODO Auto-generated constructor stub
		activity = _activity;

		appPref = new AppPref(activity);
	}



	@Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		super.onPreExecute();

		progressDialog = new ProgressDialog(activity);
		progressDialog.setTitle("Please Wait");
		progressDialog.setMessage("Fetching News");
		progressDialog.setCanceledOnTouchOutside(false);
		progressDialog.show();

		progressDialog.setOnCancelListener(new OnCancelListener() {

			@Override
			public void onCancel(DialogInterface dialog) {
				// TODO Auto-generated method stub

				System.out.println("-- I am called from news task");


				NewsAsyncTask.this.cancel(true);
			}
		});

	}


	@Override
	protected Boolean doInBackground(String... params) {



		try 
		{



			httpClient = new DefaultHttpClient();

			/*
			 * change this url to new one
			 */
			httpPost = new HttpPost(URLS.NEWS_URL);
			
			
			
			jsonObject = new JSONObject();




			//			jsonObject.put("email", DATA.forgot_pass_email);			 



			System.out.println("JSON Object : " + jsonObject.toString());

			System.out.println("-- token : " + appPref.getAccessToken());

			stringEntity =  new StringEntity("");
			stringEntity.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
			stringEntity.setContentType(new BasicHeader("token",  appPref.getAccessToken()));

			httpPost.setEntity(stringEntity);

			httpResponse = httpClient.execute(httpPost);



			content = EntityUtils.toString(httpResponse.getEntity());


			/*
			 * Uncomment when access token issue fixed
			 */

			if(content.length() >3)
			{


				//				System.out.println("-- header : " + httpPost.`);
				System.out.println("-- Data receieved : " + content);

				Gson gson = new Gson();

				//				JSONObject jsonProduct = new JSONObject(content);
				NewsModule[] arrTemp =  gson.fromJson(content, NewsModule[].class);

				DATA.arrlstNewsModules = new ArrayList<NewsModule>(Arrays.asList(arrTemp));

				return true;
			}
			else
			{
				Error = "No News Found";
				return false;
			}






		} 
		//		catch(JSONException e)
		//		{
		//			System.out.println("--1 JSON Data : " + content + "header" + httpPost.getAllHeaders()  );
		//			return false;
		//		}
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
			((NewsActivity)activity).loadNewGrid();
		}
		else
		{
			Toasts.pop(activity, "Error : "  + Error);
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
