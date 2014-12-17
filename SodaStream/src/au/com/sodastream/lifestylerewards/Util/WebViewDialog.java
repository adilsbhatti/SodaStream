package au.com.sodastream.lifestylerewards.Util;

import android.app.Activity;
import android.app.Dialog;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import au.com.sodastream.lifestylerewards.R;

public class WebViewDialog {

static WebViewPopup webViewPopup;

	public static void showWebviewDialog(final Activity activity, String url, String title)
	{
		webViewPopup = new WebViewPopup(activity, url, title);
		webViewPopup.show();
	}






	//	public static void showWebviewDialog(final Activity activity, String url, String title)
	//	{
	//		AlertDialog.Builder builder = new Builder(activity);
	//		LayoutInflater layoutInflater = LayoutInflater.from(activity);
	//		View v = layoutInflater.inflate(R.layout.webview_dialog, null);
	//		builder.setTitle(title);
	//
	//
	//		LinearLayout linearLayout = new LinearLayout(activity);
	//		linearLayout.setOrientation(LinearLayout.VERTICAL);
	//
	//
	//
	//		final ProgressBar progressBar = new ProgressBar(activity);
	//
	////		final WebView webView = new WebView(activity);
	//		final WebView webView = (WebView) v.findViewById(R.id.webView);
	//		webView.setVisibility(View.GONE);
	//
	//		webView.setWebViewClient(new WebViewClient()
	//		{
	//			public void onPageFinished(WebView view, String url) {
	//				progressBar.setVisibility(View.GONE);
	//				webView.setVisibility(View.VISIBLE);
	//
	//			};
	//
	//
	//			public void onPageStarted(WebView view, String url, android.graphics.Bitmap favicon) {
	//
	//				Toasts.pop(activity, "Loading..");
	//				progressBar.setVisibility(View.VISIBLE);
	//				webView.setVisibility(View.GONE);
	//			};
	//
	//
	//
	//		}
	//
	//
	//
	//				);
	//
	//		webView.getSettings().setJavaScriptEnabled(true);
	//
	//
	//		webView.loadUrl(url);
	//
	//		
	//
	//		linearLayout.addView(webView);
	//		linearLayout.addView(progressBar);
	//
	//		builder.setView(linearLayout);
	//
	//
	//
	//		builder.setPositiveButton("DONE", new DialogInterface.OnClickListener() {
	//
	//			@Override
	//			public void onClick(DialogInterface dialog, int which) {
	//				// TODO Auto-generated method stub
	//				dialog.dismiss();
	//
	//			}
	//		});
	//
	//
	//		AlertDialog alertDialog = builder.create();
	//		alertDialog.setCanceledOnTouchOutside(false);
	//		
	//		alertDialog.show();
	//	}
}


class WebViewPopup extends Dialog 
{

	WebView webView;
	ProgressBar progressBar; 

	public WebViewPopup(final Activity activity,String url,String title) {
		super(activity);
		// TODO Auto-generated constructor stub
		setTitle(title);
		setContentView(R.layout.webview_dialog);
		setCanceledOnTouchOutside(false);

		webView =  (WebView) findViewById(R.id.webView);
		progressBar = (ProgressBar) findViewById(R.id.progressBar);
		

		webView.setWebViewClient(new WebViewClient()
		{
			public void onPageFinished(WebView view, String url) {
				progressBar.setVisibility(View.GONE);
				webView.setVisibility(View.VISIBLE);

			};


			public void onPageStarted(WebView view, String url, android.graphics.Bitmap favicon) {

//				Toasts.pop(activity, "Loading..");
				progressBar.setVisibility(View.VISIBLE);
				webView.setVisibility(View.GONE);
			};



		});


		webView.getSettings().setJavaScriptEnabled(true);
		webView.getSettings().setLoadWithOverviewMode(true);
		webView.getSettings().setUseWideViewPort(true);

		webView.loadUrl(url);


	}

}
