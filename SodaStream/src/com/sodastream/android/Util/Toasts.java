package com.sodastream.android.Util;

import android.content.Context;
import android.widget.Toast;

public class Toasts {




	public static void pop(Context context,String string)
	{
		Toast.makeText(context , string, 0).show();
	}

	public static void pop(Context context,String string,Boolean isLong)
	{
		Toast.makeText(context , string, 1).show();
	}

}
