package au.com.sodastream.lifestylerewards.Util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import android.app.Activity;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.Signature;
import android.util.Base64;

public class FBKeyHashGenerator {


	public static String getHashKey(Activity activity, String packageName)
	{

		String key  = "";
		try {
			PackageInfo info = activity.getPackageManager().getPackageInfo(
					packageName, 
					PackageManager.GET_SIGNATURES);
			for (Signature signature : info.signatures) {
				MessageDigest md = MessageDigest.getInstance("SHA");
				md.update(signature.toByteArray());
				key  = Base64.encodeToString(md.digest(), Base64.DEFAULT);
			}
			
			return key;
		} 
		catch (NameNotFoundException e) {
			return e.getMessage();
		} 
		catch (NoSuchAlgorithmException e) {
			return e.getMessage();
		}
	}

}
