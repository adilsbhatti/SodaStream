package au.com.sodastream.lifestylerewards;

import android.app.Activity;
import android.graphics.Point;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.Display;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import au.com.sodastream.lifestylerewards.Util.BarCodeGenerator;
import au.com.sodastream.lifestylerewards.Util.DATA;
import au.com.sodastream.lifestylerewards.Util.Fonts;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.koushikdutta.urlimageviewhelper.UrlImageViewHelper;

public class VoucherDetailsActivity extends Activity {

	//UI Elements

	ImageView ivVoucherBarCode,ivVoucherImage;

	TextView tvVoucherTitle , tvVoucherDesc, tvVoucherCode,tv1, tvVoucherCodeValue;  

	LinearLayout layVouchersType;

	//Variables
	Activity activity;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.voucherdetails_page);
		activity = this;


		initUI();



		loadVoucher();
		//Remove this code
		//		String sampleEAN = "0075678164125";
		//		try {
		//			ivVoucherBarCode.setImageBitmap(BarCodeGenerator.encodeAsBitmap(sampleEAN, BarcodeFormat.EAN_13, 1000, 600));
		//		} catch (WriterException e) {
		//			// TODO Auto-generated catch block
		//			e.printStackTrace();
		//		}
	}



	public void loadVoucher()
	{
		tvVoucherCode.setText(DATA.selectedVoucher.code);
		tvVoucherDesc.setText(DATA.selectedVoucher.description);
//		tvVoucherDesc.setText("lksjdljasfdjlksdjjkadfsn jfsadjflsljdfsjdf fjsladjflkasjdflkjsadffkl sadfjsadlfjaskldjflksadjf sdjflkasdjflksadjfsad fjsdfjsdlkfjlksdjfsdflkjsdf sdjflksdjflksjdflkjsdkflsdjfklasdjflkjasd fsadjflksdjflksadfjsadfsadjhfkjsadhf asdhfkjsdhafkhsadfjkhsdjkhfsjkdhfsad fhsadjkfhsadkjfhsdakjhfksdjfhsakjdf sadhfkjasdhfksadhfkjsahdfkjsdahfkjsad fsdahfjkhsadfkjhsdisudfhksda fhskdfhksdahfksdhfkjs dfhskjdfhksadjhfsadkjfh sadfhsadjkhfksadjhfsakjdhfs dafhskajdhfksjdhfkjasdhfkjashdfa sdhfjklashdfkjhasdkfhsdkfhaskdjhfas fhskjdfhsakdjfhkasjdhfkjsadhfkjsahdfkjshksadhf");
		tvVoucherTitle.setText(DATA.selectedVoucher.title);

		UrlImageViewHelper.setUrlDrawable(ivVoucherImage, DATA.selectedVoucher.image_url, R.drawable.transparent);
		setVoucherType();
	}	



	public void setVoucherType()
	{
		if(DATA.selectedVoucher.voucher_type.equalsIgnoreCase("barcode"))
		{
			showBarcode();
		}
		else
		{
			showCode();
		}
	}

	private void showCode() {
		// TODO Auto-generated method stub

		ivVoucherBarCode.setVisibility(View.GONE);
		tvVoucherCodeValue.setVisibility(View.GONE);
		tvVoucherCode.setVisibility(View.VISIBLE);
		tvVoucherCode.setText("CODE:\n"+DATA.selectedVoucher.code);

	}



	private void showBarcode() {
		// TODO Auto-generated method stub

		try {

			Display display = getWindowManager().getDefaultDisplay();
			Point size = new Point();
			display.getSize(size);
			int width = size.x;
			int height = size.y;

			ivVoucherBarCode.setImageBitmap(BarCodeGenerator.encodeAsBitmap(DATA.selectedVoucher.code, BarcodeFormat.EAN_13,  (int)(width/1.5), height/4));
			tvVoucherCodeValue.setText(DATA.selectedVoucher.code);
			ivVoucherBarCode.setVisibility(View.VISIBLE);
			tvVoucherCodeValue.setVisibility(View.VISIBLE);
			tvVoucherCode.setVisibility(View.GONE);



		} catch (WriterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}



	private void initUI() {
		// TODO Auto-generated method stub

		ivVoucherBarCode  = (ImageView) findViewById(R.id.ivVoucherBarCode);

		ivVoucherImage = (ImageView) findViewById(R.id.ivVoucherImage);

		tvVoucherCode = (TextView) findViewById(R.id.tvVoucherCode);

		tvVoucherDesc = (TextView) findViewById(R.id.tvVoucherDesc);

		tvVoucherTitle = (TextView) findViewById(R.id.tvVoucherTitle);

		tvVoucherCode.setTypeface(Fonts.getHelvatica(activity));

		tvVoucherDesc.setTypeface(Fonts.getHelvatica(activity));

		tvVoucherTitle.setTypeface(Fonts.getHelvatica(activity), Typeface.BOLD);

		layVouchersType =  (LinearLayout) findViewById(R.id.layVouchersType);

		tvVoucherCodeValue = (TextView) findViewById(R.id.tvVoucherCodeValue);

		
		tvVoucherDesc.setMovementMethod(new ScrollingMovementMethod());

		tv1 = (TextView) findViewById(R.id.tv1);

		tv1.setTypeface(Fonts.getHelvatica(activity));

		tvVoucherCodeValue.setTypeface(Fonts.getHelvatica(activity));

	}

}
