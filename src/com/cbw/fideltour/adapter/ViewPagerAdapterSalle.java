package com.cbw.fideltour.adapter;

import com.cbw.fideltour.fragment.DisponibiliteReservationSalleFragment;
import com.cbw.fideltour.fragment.ReservationSalleFragment;
import com.cbw.fideltour.fragment.ValidationReservationSalleFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class ViewPagerAdapterSalle extends FragmentPagerAdapter {

	public ViewPagerAdapterSalle(FragmentManager fm) {
		super(fm);
	}

	@Override
	public Fragment getItem(int position) {

		switch (position) {
		case 0:
			DisponibiliteReservationSalleFragment dispo = new DisponibiliteReservationSalleFragment();
			return dispo;
		case 1:
			ReservationSalleFragment reserv = new ReservationSalleFragment();
			return reserv;
		case 2:
			ValidationReservationSalleFragment valid = new ValidationReservationSalleFragment();
			return valid;

		}
		return null;
	}

	@Override
	public int getCount() {
		return 3;
	}

}
