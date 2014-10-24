package com.sodastream.android;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.sodastream.android.Util.BarCodeGenerator;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;

public class VoucherDetailsActivity extends Activity {
	
	//UI Elements
	
	ImageView ivVoucherBarCode;
	
	//Variables
	Activity activity;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.voucherdetails_page);
		activity = this;
		
		
		initUI();
		
		
		//Remove this code
		String sampleEAN = "0075678164125";
		try {
			ivVoucherBarCode.setImageBitmap(BarCodeGenerator.encodeAsBitmap(sampleEAN, BarcodeFormat.EAN_13, 1000, 600));
		} catch (WriterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	private void initUI() {
		// TODO Auto-generated method stub
		
		ivVoucherBarCode  = (ImageView) findViewById(R.id.ivVoucherBarCode);
		
	}

}
