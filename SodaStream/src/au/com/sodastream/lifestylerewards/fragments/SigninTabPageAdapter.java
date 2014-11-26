package au.com.sodastream.lifestylerewards.fragments;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class SigninTabPageAdapter extends FragmentStatePagerAdapter {

	public SigninTabPageAdapter(FragmentManager fm) {
		super(fm);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Fragment getItem(int frag) {
		// TODO Auto-generated method stub

		switch (frag) {
		case 0:
			return new SigninFragment();
			
			
		case 1:

			return new SignupFragment();
		}
		return null;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 2;
	}

}
