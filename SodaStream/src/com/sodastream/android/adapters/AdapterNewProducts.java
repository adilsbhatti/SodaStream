package com.sodastream.android.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.koushikdutta.urlimageviewhelper.UrlImageViewHelper;
import com.sodastream.android.R;
import com.sodastream.android.Util.DATA;
import com.sodastream.android.Util.WebViewDialog;
import com.sodastream.android.modules.NewProductsModule;

public class AdapterNewProducts extends ArrayAdapter<NewProductsModule> {

	
	Activity activity;
	
	public AdapterNewProducts(Activity _activity) {
		super(_activity, R.layout.cell_row	, DATA.arrlstNewProductsModules);
		// TODO Auto-generated constructor stub
		
		activity = _activity;
	}
	
	
	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolderClass viewHolder;


		if(convertView == null)
		{
			LayoutInflater layoutInflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = layoutInflater.inflate(R.layout.cell_row, parent, false);

			viewHolder = new ViewHolderClass();
			initUI(convertView, viewHolder);

			convertView.setTag(viewHolder);

		}
		else
		{
			viewHolder = (ViewHolderClass) convertView.getTag();
		}
		
		

		viewHolder.tvIconTitle.setText(DATA.arrlstNewProductsModules.get(position).title);
		
		UrlImageViewHelper.setUrlDrawable(viewHolder.ivIconImg, DATA.arrlstNewProductsModules.get(position).thumbnail_image_url,R.drawable.icon);
	
		

		convertView.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String url = "https://au.yahoo.com/?p=us";
				WebViewDialog.showWebviewDialog(activity, url, DATA.arrlstNewProductsModules.get(position).title);
			}
		});



		return convertView;
	}
	
	
	
	private void initUI(View convertView, ViewHolderClass viewHolder) {
		// TODO Auto-generated method stub
		
		viewHolder.ivIconImg = (ImageView) convertView.findViewById(R.id.ivIconImg);
		

		viewHolder.tvIconTitle = (TextView) convertView.findViewById(R.id.tvIconTitle);
		
	}



	static class ViewHolderClass
	{
		ImageView ivIconImg ;
		TextView tvIconTitle;
	}

}