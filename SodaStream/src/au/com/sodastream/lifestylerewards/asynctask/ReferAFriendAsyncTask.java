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
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.ProgressDialog;
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


public class ReferAFriendAsyncTask extends AsyncTask<String, String,Boolean> {


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

	public ReferAFriendAsyncTask(Activity _activity) {
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
		progressDialog.setMessage("Sending Details");
		progressDialog.setCanceledOnTouchOutside(false);
		progressDialog.show();


		progressDialog.setOnCancelListener(new OnCancelListener() {

			@Override
			public void onCancel(DialogInterface dialog) {
				// TODO Auto-generated method stub

				System.out.println("-- I am called from refer task");


				ReferAFriendAsyncTask.this.cancel(true);
			}
		});

	}


	@Override
	protected Boolean doInBackground(String... params) {
		// TODO Auto-generated method stub
		try 
		{



			httpClient = new DefaultHttpClient();

			httpPost = new HttpPost(URLS.REFER_URL);
			jsonObject = new JSONObject();




			jsonObject.put("email", DATA.referFriendModule.email);
			jsonObject.put("firstname", DATA.referFriendModule.name);
			jsonObject.put("surname", DATA.referFriendModule.lastName);
			jsonObject.put("type", DATA.referFriendModule.userType);



			System.out.println("JSON Object : " + jsonObject.toString());



			stringEntity =  new StringEntity(jsonObject.toString());
			stringEntity.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
			stringEntity.setContentType(new BasicHeader("token",  accessTokenPref.getAccessToken()));

			httpPost.setEntity(stringEntity);

			httpResponse = httpClient.execute(httpPost);



			content = EntityUtils.toString(httpResponse.getEntity());

			System.out.println("-- refer a friends response : " + content);

			JSONObject jsonCheckResponse;
			try {
				jsonCheckResponse = new JSONObject(content);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				jsonCheckResponse = null;
			}

			if(jsonCheckResponse !=null && jsonCheckResponse.has("error"))
			{
				Error =  jsonCheckResponse.getString("error");


				Error =  activity.getString(R.string.ERROR_API);
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
			Error =  activity.getString(R.string.ERROR_API);
			return true;
		}
		catch(UnsupportedEncodingException e)
		{
			System.out.println("--2 JSON Data : " + content + "header" + httpPost.getAllHeaders()  );
			Error =  activity.getString(R.string.ERROR_API);
			return false;
		}
		catch(ClientProtocolException e)
		{
			System.out.println("--3 JSON Data : " + content + "header" + httpPost.getAllHeaders()  );
			Error =  activity.getString(R.string.ERROR_API);
			return false;
		}
		catch(ParseException e)
		{
			System.out.println("--4 JSON Data : " + content + "header" + httpPost.getAllHeaders()  );
			Error =  activity.getString(R.string.ERROR_API);
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
			Error =  activity.getString(R.string.ERROR_API);
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
			Builder builder =  new Builder(activity);
			builder.setTitle("Referral successful");
			builder.setMessage("Thank you! We've successfully received your referral and will contact them shortly.");
			//			builder.setPositiveButton("Refer another friend", new DialogInterface.OnClickListener() {
			//
			//				@Override
			//				public void onClick(DialogInterface dialog, int which) {
			//					// TODO Auto-generated method stub
			//
			//					((ReferAFriendActivity)activity).clearFields();
			//
			//				}
			//			});

			builder.setNegativeButton("OK", new DialogInterface.OnClickListener() {

				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub

					Intent intent = new Intent(activity, MenuActivity.class);
					activity.startActivity(intent);
					activity.finish();

				}
			});


			//			builder.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
			//
			//				@Override
			//				public void onClick(DialogInterface dialog, int which) {
			//					// TODO Auto-generated method stub
			//					dialog.dismiss();
			//
			//				}
			//			});





			AlertDialog alertDialog =  builder.create();
			alertDialog.show();
			alertDialog.setCanceledOnTouchOutside(false);
		}
		else
		{	
			Toasts.pop(activity, Error);
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
