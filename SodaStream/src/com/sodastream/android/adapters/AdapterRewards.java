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
import com.sodastream.android.Util.Fonts;
import com.sodastream.android.Util.Toasts;
import com.sodastream.android.Util.ViewBrowser;
import com.sodastream.android.modules.RewardsModule;

public class AdapterRewards extends ArrayAdapter<RewardsModule> {


	//Variables
	Activity activity;

	public AdapterRewards(Activity _activity) {
		super(_activity, R.layout.cell_row	, DATA.arrlstRewardsModules);
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



		viewHolder.tvIconTitle.setText(DATA.arrlstRewardsModules.get(position).title);

		UrlImageViewHelper.setUrlDrawable(viewHolder.ivIconImg, DATA.arrlstRewardsModules.get(position).thumbnail_image_url,R.drawable.icon);



		convertView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				if(DATA.arrlstRewardsModules.get(position).site_url.length() > 1)
				{

					ViewBrowser.openURL(activity, DATA.arrlstRewardsModules.get(position).site_url);	
				}
				else
				{
					Toasts.pop(activity, "Unable to load reward");
				}

			}
		});



		return convertView;
	}



	private void initUI(View convertView, ViewHolderClass viewHolder) {
		// TODO Auto-generated method stub

		viewHolder.ivIconImg = (ImageView) convertView.findViewById(R.id.ivIconImg);


		viewHolder.tvIconTitle = (TextView) convertView.findViewById(R.id.tvIconTitle);

		viewHolder.tvIconTitle.setTypeface(Fonts.getHelvatica(activity));

	}



	static class ViewHolderClass
	{
		ImageView ivIconImg ;
		TextView tvIconTitle;
	}



}
