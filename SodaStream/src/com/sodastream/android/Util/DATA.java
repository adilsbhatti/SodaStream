package com.sodastream.android.Util;

import java.util.ArrayList;

import android.app.ProgressDialog;

import com.sodastream.android.modules.LoginModule;
import com.sodastream.android.modules.NewProductsModule;
import com.sodastream.android.modules.RecipeModel;
import com.sodastream.android.modules.ReferFriendModule;
import com.sodastream.android.modules.RewardsModule;
import com.sodastream.android.modules.SignupModule;
import com.sodastream.android.modules.StoresModule;
import com.sodastream.android.modules.VideosModule;
import com.sodastream.android.modules.VoucherModule;

public class DATA {


	public  static String GAS_MACHINE_CODE;

	// give your server registration url here
	public static final String SERVER_URL = "http://192.168.1.136/gcm_server_php/register.php"; 

	// Google project id
	public static final String SENDER_ID = "787225803737"; 


	public static final String DISPLAY_MESSAGE_ACTION =
			"com.sodastream.android.DISPLAY_MESSAGE";



	public static String name = "Tarqi";

	public static String email  = "Tariq@gmail.com";

	public static String regId = "";


	public static boolean isFacebook = false;

	public static LoginModule currLoginModule;


	public static String forgot_pass_email  = "";


	public static SignupModule signupModule;

	public static ReferFriendModule referFriendModule;

	public static double Latitude;

	public static double Longitude;
	
	public static ProgressDialog progressDialog;
	
	public static ArrayList<String> arrlstUses;
	
	public static ArrayList<NewProductsModule> arrlstNewProductsModules;
	
	public static ArrayList<VideosModule> arrlstVideosModules;
	
	public static ArrayList<VoucherModule> arrlstVoucherModules;
	
	
	public static VoucherModule selectedVoucher;
	
	public static ArrayList<RewardsModule> arrlstRewardsModules;
	
	public static ArrayList<RecipeModel> arrlstRecipeModels;
	
	public static RecipeModel selectedRecipe;
	
	public static ArrayList<StoresModule> arrlstStoresModules;
	
	
	public static StoresModule selectedStore;
}
