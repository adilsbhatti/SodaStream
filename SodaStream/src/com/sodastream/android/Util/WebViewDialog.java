package com.sodastream.android.Util;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

public class WebViewDialog {



	public static void showWebviewDialog(final Activity activity, String url, String title)
	{
		AlertDialog.Builder builder = new Builder(activity);
		builder.setTitle(title);


		LinearLayout linearLayout = new LinearLayout(activity);
		linearLayout.setOrientation(LinearLayout.VERTICAL);



		final ProgressBar progressBar = new ProgressBar(activity);

		final WebView webView = new WebView(activity);
		webView.setVisibility(View.GONE);

		webView.setWebViewClient(new WebViewClient()
		{
			public void onPageFinished(WebView view, String url) {
				progressBar.setVisibility(View.GONE);
				webView.setVisibility(View.VISIBLE);

			};


			public void onPageStarted(WebView view, String url, android.graphics.Bitmap favicon) {

				Toasts.pop(activity, "Loading..");
				progressBar.setVisibility(View.VISIBLE);
				webView.setVisibility(View.GONE);
			};



		}



				);

		webView.getSettings().setJavaScriptEnabled(true);


		webView.loadUrl(url);


		linearLayout.addView(webView);
		linearLayout.addView(progressBar);

		builder.setView(linearLayout);



		builder.setPositiveButton("DONE", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				dialog.dismiss();

			}
		});


		AlertDialog alertDialog = builder.create();
		alertDialog.setCanceledOnTouchOutside(false);
		alertDialog.show();
	}
}
