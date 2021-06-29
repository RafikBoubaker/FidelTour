package com.cbw.fideltour.adapter;

import com.cbw.fideltour.fragment.DisponibiliteReservationChambreFragment;
import com.cbw.fideltour.fragment.ReservationChambreFragment;
import com.cbw.fideltour.fragment.ValidationReservationChambreFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class ViewPagerAdapterChambre extends FragmentPagerAdapter {

	final int PAGE_COUNT = 3;

	public ViewPagerAdapterChambre(FragmentManager fm) {
		super(fm);
	}

	@Override
	public Fragment getItem(int position) {
		switch (position) {

		case 0:
			DisponibiliteReservationChambreFragment dispo = new DisponibiliteReservationChambreFragment();
			return dispo;
		case 1:
			ReservationChambreFragment reserv = new ReservationChambreFragment();
			return reserv;
		case 2:
			ValidationReservationChambreFragment valid = new ValidationReservationChambreFragment();
			return valid;
		}
		return null;
	}

	@Override
	public int getCount() {
		return PAGE_COUNT;
	}

}
