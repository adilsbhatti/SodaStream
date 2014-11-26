package au.com.sodastream.lifestylerewards;

import android.app.Activity;
import android.os.Bundle;
import android.view.View.OnClickListener;
import android.widget.GridView;
import android.widget.TextView;
import au.com.sodastream.lifestylerewards.Util.DATA;
import au.com.sodastream.lifestylerewards.Util.Fonts;
import au.com.sodastream.lifestylerewards.Util.Toasts;
import au.com.sodastream.lifestylerewards.adapters.AdapterVouchers;
import au.com.sodastream.lifestylerewards.asynctask.VouchersAsyncTask;


public class VouchersActivity extends Activity {

	//UI Elements
	//	ImageButton ibVoucher1,ibVoucher2;
	GridView gvVouchers;

	//Varaibles
	Activity activity;
	OnClickListener onClickListener;
	VouchersAsyncTask vouchersAsyncTask;
	AdapterVouchers adapterVouchers;
	TextView tv2;

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

		tv2 = (TextView) findViewById(R.id.tv2);
		tv2.setTypeface(Fonts.getHelvatica(activity));

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
