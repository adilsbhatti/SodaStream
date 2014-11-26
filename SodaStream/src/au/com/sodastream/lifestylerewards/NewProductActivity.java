package au.com.sodastream.lifestylerewards;

import android.app.Activity;
import android.os.Bundle;
import android.widget.GridView;
import android.widget.TextView;
import au.com.sodastream.lifestylerewards.Util.DATA;
import au.com.sodastream.lifestylerewards.Util.Fonts;
import au.com.sodastream.lifestylerewards.Util.Toasts;
import au.com.sodastream.lifestylerewards.adapters.AdapterNewProducts;
import au.com.sodastream.lifestylerewards.asynctask.NewProductsTask;


public class NewProductActivity extends Activity {
	
	
	
	
	//UI Elements
	
	GridView gvNewProducts;
	TextView tv23;
	
	//Variables
	Activity activity;
	NewProductsTask newProductsTask;
	AdapterNewProducts adapterNewProducts;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.newproduct_page);
		activity = this;
		
		initUI();
		
		
		
	}
	
	
	private void initUI() {
		// TODO Auto-generated method stub
		gvNewProducts = (GridView) findViewById(R.id.gvNewProducts);
		
		tv23 = (TextView) findViewById(R.id.tv23);
		tv23.setTypeface(Fonts.getHelvatica(activity));
	}


	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		
		activity = this;
		newProductsTask = new NewProductsTask(activity);
		newProductsTask.execute();
		
	}
	
	
	public void loadNewProductsGrid()
	{
		
		if(DATA.arrlstNewProductsModules!=null)
		{
			adapterNewProducts = new AdapterNewProducts(activity);
			gvNewProducts.setAdapter(adapterNewProducts);
		}
		else
		{
			Toasts.pop(activity, "Error fetching new products");
		}
		
	}

}
