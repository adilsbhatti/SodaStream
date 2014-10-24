package com.sodastream.android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

public class VouchersActivity extends Activity {
	
	//UI Elements
	ImageButton ibVoucher1,ibVoucher2;
	
	//Varaibles
	Activity activity;
	OnClickListener onClickListener;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.voucher_page);
		activity =  this;
		
		initUI();
		
		
		onClickListener = new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(activity, VoucherDetailsActivity.class);
				startActivity(intent);
			}
		};
		
		
		ibVoucher1.setOnClickListener(onClickListener);
		ibVoucher2.setOnClickListener(onClickListener);
	}

	private void initUI() {
		// TODO Auto-generated method stub
		
		ibVoucher2 = (ImageButton) findViewById(R.id.ibVoucher2);
		ibVoucher1 = (ImageButton) findViewById(R.id.ibVoucher1);
		
	}

}
