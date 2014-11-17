package com.sodastream.android;

import com.sodastream.android.Util.DATA;
import com.sodastream.android.Util.Toasts;
import com.sodastream.android.adapters.AdapterNewProducts;
import com.sodastream.android.asynctask.NewProductsTask;

import android.app.Activity;
import android.os.Bundle;
import android.widget.GridView;

public class NewProductActivity extends Activity {
	
	
	
	
	//UI Elements
	
	GridView gvNewProducts;
	
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
