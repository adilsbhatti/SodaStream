package au.com.sodastream.lifestylerewards.Util;

import java.util.ArrayList;

import android.app.ProgressDialog;
import au.com.sodastream.lifestylerewards.modules.FAQModule;
import au.com.sodastream.lifestylerewards.modules.LoginModule;
import au.com.sodastream.lifestylerewards.modules.NewProductsModule;
import au.com.sodastream.lifestylerewards.modules.NewsModule;
import au.com.sodastream.lifestylerewards.modules.RecipeModel;
import au.com.sodastream.lifestylerewards.modules.ReferFriendModule;
import au.com.sodastream.lifestylerewards.modules.RewardsModule;
import au.com.sodastream.lifestylerewards.modules.SignupModule;
import au.com.sodastream.lifestylerewards.modules.StoresModule;
import au.com.sodastream.lifestylerewards.modules.VideosModule;
import au.com.sodastream.lifestylerewards.modules.VoucherModule;


public class DATA {


	public  static String GAS_MACHINE_CODE;


	public static boolean fromRegistration  =  false;

	// give your server registration url here
	public static final String SERVER_URL = "http://192.168.1.136/gcm_server_php/register.php"; 

	// Google project id for Sodastream
	public static final String SENDER_ID = "1084117720906"; 


	public static final String DISPLAY_MESSAGE_ACTION =
			"com.sodastream.android.DISPLAY_MESSAGE";


	//User Signin Values

	public static String USER_EMAIL = "";
	public static String USER_PASSWORD = "";



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
	
	public static ArrayList<NewsModule> arrlstNewsModules;

	public static ArrayList<VideosModule> arrlstVideosModules;
	
	public static ArrayList<FAQModule> arrlstFAQModules;

	public static ArrayList<VoucherModule> arrlstVoucherModules;


	public static VoucherModule selectedVoucher;

	public static ArrayList<RewardsModule> arrlstRewardsModules;

	public static ArrayList<RecipeModel> arrlstRecipeModels;

	public static RecipeModel selectedRecipe;

	public static ArrayList<StoresModule> arrlstStoresModules;


	public static StoresModule selectedStore;

	public static  int MENU_IMAGES_SIZE;
	public static int RECIPE_IMAGES_SIZE;
	
	
	
	
	public static String USER_STREET = "";
	public static String USER_SUBURB = "";
	public static String USER_STATE = "";
	public static String USER_POSTCODE = "";
	
	
	
	
	
}
