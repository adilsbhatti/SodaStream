package au.com.sodastream.lifestylerewards.Util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.graphics.Color;
import android.widget.EditText;

public class Validate {



	public static boolean isEmptyEditText(EditText editText)
	{
		if(editText.getText().toString().equals(""))
		{
			editText.setHintTextColor(Color.RED);
			return true;
		}
		else
		{
			editText.setHintTextColor(Color.GRAY);
			return false;
		}
	}



	public static void checkEmptyEditText(EditText[] arrET)
	{
		for(int i = 0; i < arrET.length ; i++)
		{
			if(arrET[i].getText().toString().equals(""))
			{
				arrET[i].setHintTextColor(Color.RED);
				//				return true;
			}
			else
			{
				arrET[i].setHintTextColor(Color.GRAY);
				//				return false;
			}
		}

	}

	public static boolean isEmpty(String s)
	{
		if(s.length() < 1 || s=="")
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public static boolean isEmailValid(String email) {
	    boolean isValid = true;

	    String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
	    CharSequence inputStr = email;

	    Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
	    Matcher matcher = pattern.matcher(inputStr);
	    if (matcher.matches()) {
	        isValid = false;
	    }
	    System.out.println("-- is valid value : " + isValid);
	    
	    return isValid;
	}
	
	public static boolean isEmailValid(EditText editText) 
	{
		return isEmailValid(editText.getText().toString().trim());
	    
	}
}
