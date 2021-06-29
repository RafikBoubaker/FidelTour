package com.cbw.fideltour.adapter;

import java.util.ArrayList;
import java.util.List;
import com.cbw.fideltour.fragment.FragmentChambre;
import com.cbw.fideltour.fragment.FragmentPresentation;
import com.cbw.fideltour.fragment.FragmentRechercher;
import com.cbw.fideltour.fragment.FragmentRestaurant;
import com.cbw.fideltour.fragment.FragmentSalle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class MyFragmentPagerAdapter extends FragmentPagerAdapter {

	final int PAGE_COUNT = 5;
	private List<Fragment> fragments;

	public MyFragmentPagerAdapter(FragmentManager fm) {
		super(fm);
		this.fragments = new ArrayList<Fragment>();

		fragments.add(new FragmentRechercher());
		fragments.add(new FragmentPresentation());
		fragments.add(new FragmentChambre());
		fragments.add(new FragmentSalle());
		fragments.add(new FragmentRestaurant());

	}

	@Override
	public Fragment getItem(int position) {

		return fragments.get(position);
	}

	@Override
	public int getCount() {
		return fragments.size();
	}

	@Override
	public CharSequence getPageTitle(int position) {
		switch (position) {
		case 0:
			return "Rechercher";
		case 1:
			return "Présentation";
		case 2:
			return "Chambre";
		case 3:
			return "Salle de conférence";
		case 4:
			return "Restaurant";

		}
		return super.getPageTitle(position);
	}

}
