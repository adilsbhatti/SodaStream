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

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.os.AsyncTask;
import au.com.sodastream.lifestylerewards.Util.AppPref;
import au.com.sodastream.lifestylerewards.Util.URLS;

public class GCMResgisterTask extends AsyncTask<String, String, Boolean>   implements DialogInterface.OnDismissListener {


	Context context;
	ProgressDialog progressDialog;


	HttpClient httpClient;
	HttpPost httpPost = null;
	HttpResponse httpResponse;
	StringEntity stringEntity;
	JSONObject jsonObject ;
	String content = "";
	String Error= "";

	AppPref appPref;

	String deviceID;




	public GCMResgisterTask(Context _context, String _deviceID) {
		// TODO Auto-generated constructor stub
		context = _context;
		deviceID = _deviceID;

		appPref = new AppPref( context);
		appPref.setDeviceID(deviceID);

		progressDialog = new ProgressDialog(context);
		
		progressDialog.setOnCancelListener(new OnCancelListener() {

			@Override
			public void onCancel(DialogInterface dialog) {
				// TODO Auto-generated method stub

				System.out.println("-- I am called from gcm task");


				GCMResgisterTask.this.cancel(true);
			}
		});
	}




	@Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		super.onPreExecute();
		
		progressDialog.setTitle("Please Wait");
		progressDialog.setMessage("Signing in");
		progressDialog.setCanceledOnTouchOutside(false);
		//				progressDialog.show();


	}




	@Override
	protected Boolean doInBackground(String... params) {
		try 
		{



			httpClient = new DefaultHttpClient();

			httpPost = new HttpPost(URLS.REGISTER_DEVICE);
			jsonObject = new JSONObject();




			jsonObject.put("device_id", deviceID);			 
			jsonObject.put("device_type", "Android");				 



			System.out.println("JSON Object : " + jsonObject.toString());

			stringEntity =  new StringEntity(jsonObject.toString());
			stringEntity.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
			stringEntity.setContentType(new BasicHeader("token",  appPref.getAccessToken()));

			httpPost.setEntity(stringEntity);

			httpResponse = httpClient.execute(httpPost);



			content = EntityUtils.toString(httpResponse.getEntity());
			
			System.out.println("-- reply from gcm task: " + content);

			JSONObject jsonCheckResponse = new JSONObject(content);

			if(jsonCheckResponse.has("error"))
			{
				Error =  jsonCheckResponse.getString("error");





				return false;

			}
			else
			{

				JSONObject jsonContent = new JSONObject(content);

				if(jsonContent.has("status"))
				{
					if(jsonContent.get("status").equals("success"))
					{

						return true;
					}
					else

					{
						return false;
					}
				}
				else
				{
					return false;
				}
			}


			//	System.out.println("-- Header : "+ httpResponse.getFirstHeader("token").getValue());




		} 
		catch(JSONException e)
		{
			System.out.println("--1 JSON Data : " + content + "header" + httpPost.getAllHeaders()  );
			Error = "We're unable to sign you up at this time. Please try again shortly.";
			return false;
		}
		catch(UnsupportedEncodingException e)
		{
			System.out.println("--2 JSON Data : " + content + "header" + httpPost.getAllHeaders()  );
			Error = "We're unable to sign you up at this time. Please try again shortly.";
			return false;
		}
		catch(ClientProtocolException e)
		{
			System.out.println("--3 JSON Data : " + content + "header" + httpPost.getAllHeaders()  );
			Error = "We're unable to sign you up at this time. Please try again shortly.";
			return false;
		}
		catch(ParseException e)
		{
			System.out.println("--4 JSON Data : " + content + "header" + httpPost.getAllHeaders()  );
			Error = "We're unable to sign you up at this time. Please try again shortly.";
			return false;
		}
		catch(IOException e)
		{
			System.out.println("--5 JSON Data : " + content + "header" + httpPost.getAllHeaders()  );
			Error = "Please check that you are connected to the internet and try again";
			return false;
		}
		catch (Exception e) 
		{
			System.out.println("Exception : " + e.getMessage() );
			// TODO: handle exception
			System.out.println("--6 JSON Data : " + content + "header" + httpPost.getAllHeaders()  );
			Error = "We're unable to sign you up at this time. Please try again shortly.";
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

		}
		else
		{
			appPref.setDeviceID("");
		}
	}


	@Override
	protected void onCancelled() {
		// TODO Auto-generated method stub
		super.onCancelled();


		appPref.setDeviceID("");
	}


	@Override
	public void onDismiss(DialogInterface dialog) {
		// TODO Auto-generated method stub

	}


}
