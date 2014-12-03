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
import android.os.AsyncTask;
import au.com.sodastream.lifestylerewards.MenuActivity;
import au.com.sodastream.lifestylerewards.Util.AppPref;
import au.com.sodastream.lifestylerewards.Util.DATA;
import au.com.sodastream.lifestylerewards.Util.Toasts;
import au.com.sodastream.lifestylerewards.Util.URLS;


public class CodeRegistrationTask extends AsyncTask<String, String, Boolean> {


	Activity activity;
	ProgressDialog progressDialog;


	HttpClient httpClient;
	HttpPost httpPost = null;
	HttpResponse httpResponse;
	StringEntity stringEntity;
	JSONObject jsonObject ;
	String content = "";
	String Error= "";

	final Class c;

	AppPref appPref;

	public CodeRegistrationTask(Activity _activity,final Class _class) {
		// TODO Auto-generated constructor stub
		activity = _activity;

		c =_class;

		appPref =  new AppPref(activity);
	}



	@Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		super.onPreExecute();

		progressDialog = new ProgressDialog(activity);
		progressDialog.setTitle("Please Wait");
		progressDialog.setMessage("Registering Your Code");
		progressDialog.setCanceledOnTouchOutside(false);
		progressDialog.show();

		progressDialog.setOnCancelListener(new OnCancelListener() {

			@Override
			public void onCancel(DialogInterface dialog) {
				// TODO Auto-generated method stub

				System.out.println("-- I am called from code task");


				CodeRegistrationTask.this.cancel(true);
			}
		});

	}


	@Override
	protected Boolean doInBackground(String... params) {
		// TODO Auto-generated method stub
		try 
		{



			httpClient = new DefaultHttpClient();

			httpPost = new HttpPost(URLS.ACTIVATE_CODE_URL);
			jsonObject = new JSONObject();




			jsonObject.put("activation_code", DATA.GAS_MACHINE_CODE);			 



			System.out.println("JSON Object : " + jsonObject.toString());

			stringEntity =  new StringEntity(jsonObject.toString());
			stringEntity.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
			stringEntity.setContentType(new BasicHeader("token",  appPref.getAccessToken()));

			httpPost.setEntity(stringEntity);

			httpResponse = httpClient.execute(httpPost);



			content = EntityUtils.toString(httpResponse.getEntity());

			JSONObject jsonCheckResponse = new JSONObject(content);

			if(jsonCheckResponse.has("error"))
			{
				Error =  jsonCheckResponse.getString("error");

				System.out.println("-- error  : " + Error);



				return false;

			}
			else
			{


				JSONObject jsonContent = new JSONObject(content);
				if (jsonContent.getInt("activated") == 1) {
					
					return true;
				}
				else
				{
					Error = "Failed to register activiation code, please try again";
					return false;
				}

			}

			//	System.out.println("-- Header : "+ httpResponse.getFirstHeader("token").getValue());

			//	System.out.println("-- JSON Data : " + content + " header : " + Arrays.deepToString(httpPost.getHeaders("token")) );



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

			appPref.setActivationCode(DATA.GAS_MACHINE_CODE);

			
			/*
			 * openn get address dialog
			 */
			
			((MenuActivity)activity).getWelcomeRewardAddress(c);
			
			//			Intent intent = new Intent(activity, c);
			//			activity.startActivity(intent);
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
