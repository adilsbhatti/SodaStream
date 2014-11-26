package au.com.sodastream.lifestylerewards.adapters;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import au.com.sodastream.lifestylerewards.R;
import au.com.sodastream.lifestylerewards.Util.Fonts;
import au.com.sodastream.lifestylerewards.modules.VoucherModule;


public class VoucherDetailAdapter extends ArrayAdapter<VoucherModule> {

	//UI Elements



	//Variables
	Activity activity;

	public VoucherDetailAdapter(Activity _activity,
			List<VoucherModule> objects) {
		super(_activity, R.layout.voucher_cell, objects);
		// TODO Auto-generated constructor stub
		activity = _activity;

	}


	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub

		ViewHolderClass viewHolder;


		if(convertView == null)
		{
			LayoutInflater layoutInflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = layoutInflater.inflate(R.layout.voucher_cell, parent, false);

			viewHolder = new ViewHolderClass();
			initUI(convertView, viewHolder);

			convertView.setTag(viewHolder);

		}
		else
		{
			viewHolder = (ViewHolderClass) convertView.getTag();
		}

		viewHolder.tvVoucherCode.setText("Code");
		viewHolder.tvVoucherDesc.setText("Desc");
		viewHolder.tvVoucherTitle.setText("Title");
		//		viewHolder.ivVoucherBarCode.setImageResource(0);
		//		viewHolder.ivVoucherImage.setImageResource(0);




		return convertView;
	}


	private void initUI(View convertView, ViewHolderClass viewHolder) {
		viewHolder.ivVoucherBarCode = (ImageView) convertView.findViewById(R.id.ivVoucherBarCode);
		viewHolder.ivVoucherImage = (ImageView) convertView.findViewById(R.id.ivVoucherImage);

		viewHolder.tvVoucherCode = (TextView) convertView.findViewById(R.id.tvVoucherCode);
		viewHolder.tvVoucherDesc = (TextView) convertView.findViewById(R.id.tvVoucherDesc);
		viewHolder.tvVoucherTitle = (TextView) convertView.findViewById(R.id.tvVoucherTitle);
		
		viewHolder.tvVoucherCode.setTypeface(Fonts.getHelvatica(activity));
		viewHolder.tvVoucherDesc.setTypeface(Fonts.getHelvatica(activity));
		viewHolder.tvVoucherTitle.setTypeface(Fonts.getHelvatica(activity));
		
	}




	static class ViewHolderClass
	{
		ImageView ivVoucherImage,ivVoucherBarCode ;
		TextView tvVoucherTitle,tvVoucherDesc, tvVoucherCode;
	}


}
