package au.com.sodastream.lifestylerewards.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AbsListView.LayoutParams;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import au.com.sodastream.lifestylerewards.R;
import au.com.sodastream.lifestylerewards.Util.AppImagesDimensions;
import au.com.sodastream.lifestylerewards.Util.AspectRationImageView;
import au.com.sodastream.lifestylerewards.Util.DATA;
import au.com.sodastream.lifestylerewards.Util.Fonts;
import au.com.sodastream.lifestylerewards.Util.ViewBrowser;
import au.com.sodastream.lifestylerewards.modules.NewProductsModule;

import com.koushikdutta.urlimageviewhelper.UrlImageViewHelper;
//import android.widget.RelativeLayout.LayoutParams;


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


			AppImagesDimensions.setScreenUnits(activity);

			convertView.setLayoutParams(new LayoutParams(DATA.MENU_IMAGES_SIZE,DATA.MENU_IMAGES_SIZE));
			
	
			viewHolder = new ViewHolderClass();
			initUI(convertView, viewHolder);

			convertView.setTag(viewHolder);
			
//			viewHolder.ivIconImg.setLayoutParams(new android.widget.RelativeLayout.LayoutParams(DATA.MENU_IMAGES_SIZE,DATA.MENU_IMAGES_SIZE));
//			viewHolder.layMagenta.setLayoutParams(new android.widget.LinearLayout.LayoutParams(DATA.MENU_IMAGES_SIZE, LayoutParams.WRAP_CONTENT));

		}
		else
		{
			viewHolder = (ViewHolderClass) convertView.getTag();
		}



		viewHolder.tvIconTitle.setText(DATA.arrlstNewProductsModules.get(position).title.toUpperCase());

		System.out.println("-- products image : " + DATA.arrlstNewProductsModules.get(position).thumbnail_url);
		UrlImageViewHelper.setUrlDrawable(viewHolder.ivIconImg, DATA.arrlstNewProductsModules.get(position).thumbnail_url,R.drawable.transparent);
		



		convertView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String url = DATA.arrlstNewProductsModules.get(position).url;
	
				
				ViewBrowser.openURL(activity, url);
			}
		});



		return convertView;
	}



	private void initUI(View convertView, ViewHolderClass viewHolder) {
		// TODO Auto-generated method stub

		viewHolder.ivIconImg = (AspectRationImageView) convertView.findViewById(R.id.ivIconImg);
		//		viewHolder.ivIconImg.setLayoutParams(new LayoutParams(DATA.MENU_IMAGES_SIZE, DATA.MENU_IMAGES_SIZE));


		viewHolder.tvIconTitle = (TextView) convertView.findViewById(R.id.tvIconTitle);
		viewHolder.tvIconTitle.setTypeface(Fonts.getHelvatica(activity));
		viewHolder.layMagenta = (LinearLayout) convertView.findViewById(R.id.layMagenta);


		//		viewHolder.ivIconImg.setLayoutParams(new LayoutParams(DATA.MENU_IMAGES_SIZE, DATA.MENU_IMAGES_SIZE));

	}



	static class ViewHolderClass
	{
		AspectRationImageView ivIconImg ;
		TextView tvIconTitle;
		LinearLayout layMagenta;
	}

}
