package au.com.sodastream.lifestylerewards.Util;

import android.text.InputType;
import android.text.method.NumberKeyListener;



public class AlphabetListener extends NumberKeyListener {

	@Override
	public int getInputType() {

		return InputType.TYPE_CLASS_TEXT;
	}

	@Override
	protected char[] getAcceptedChars() {

		return new char [] { 
				'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 
				'k', 'l', 'm', 'n', 'o', 'p', 'q', 'e', 's', 't', 
				'u', 'v', 'w', 'x', 'y', 'z', 
				'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 
				'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'E', 'S', 'T', 
				'U', 'V', 'W', 'X', 'Y', 'Z'};
	}

}
