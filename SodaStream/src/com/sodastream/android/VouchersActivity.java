package com.sodastream.android;

import android.app.Activity;
import android.os.Bundle;
import android.view.View.OnClickListener;
import android.widget.GridView;

import com.sodastream.android.Util.DATA;
import com.sodastream.android.Util.Toasts;
import com.sodastream.android.adapters.AdapterVouchers;
import com.sodastream.android.asynctask.VouchersAsyncTask;

public class VouchersActivity extends Activity {

	//UI Elements
	//	ImageButton ibVoucher1,ibVoucher2;
	GridView gvVouchers;

	//Varaibles
	Activity activity;
	OnClickListener onClickListener;
	VouchersAsyncTask vouchersAsyncTask;
	AdapterVouchers adapterVouchers;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.voucher_page);
		activity =  this;

		initUI();


		vouchersAsyncTask = new VouchersAsyncTask(activity);
		vouchersAsyncTask.execute();

		//		onClickListener = new OnClickListener() {
		//			
		//			@Override
		//			public void onClick(View v) {
		//				// TODO Auto-generated method stub
		//				Intent intent = new Intent(activity, VoucherDetailsActivity.class);
		//				startActivity(intent);
		//			}
		//		};
		//		
		//		
		//		ibVoucher1.setOnClickListener(onClickListener);
		//		ibVoucher2.setOnClickListener(onClickListener);
	}




	private void initUI() {
		// TODO Auto-generated method stub

		//		ibVoucher2 = (ImageButton) findViewById(R.id.ibVoucher2);
		//		ibVoucher1 = (ImageButton) findViewById(R.id.ibVoucher1);

		gvVouchers =  (GridView) findViewById(R.id.gvVouchers);

	}


	public void loadVouchersGrid()
	{
		if(DATA.arrlstVoucherModules!=null)
		{
			adapterVouchers =  new AdapterVouchers(activity);
			gvVouchers.setAdapter(adapterVouchers);
		}
		else
		{
			Toasts.pop(activity, "No vouchers to display");
		}
	}

}
