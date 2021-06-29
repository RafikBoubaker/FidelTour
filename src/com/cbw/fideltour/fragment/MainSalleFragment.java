package com.cbw.fideltour.fragment;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.actionbarsherlock.app.SherlockFragment;
import com.cbw.fideltour.R;
import com.cbw.fideltour.adapter.ViewPagerAdapterSalle;
import com.cbw.fideltour.entity.InfoReservSalle;

public class MainSalleFragment extends SherlockFragment {
	public static InfoReservSalle infSl;

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		infSl = new InfoReservSalle();
		final View view = inflater.inflate(R.layout.viewpager_main, container,
				false);

		ViewPager mViewPager = (ViewPager) view.findViewById(R.id.viewPager);

		mViewPager.setAdapter(new ViewPagerAdapterSalle(
				getChildFragmentManager()));
		mViewPager.setCurrentItem(0);
		mViewPager.setOnPageChangeListener(new OnPageChangeListener() {
			@Override
			public void onPageScrollStateChanged(int position) {
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
			}

			@Override
			public void onPageSelected(int position) {
				// TODO Auto-generated method stub
				switch (position) {
				case 0:
					view.findViewById(R.id.first_tab).setVisibility(
							View.VISIBLE);
					view.findViewById(R.id.second_tab).setVisibility(
							View.INVISIBLE);
					view.findViewById(R.id.third_tab).setVisibility(
							View.INVISIBLE);

					break;

				case 1:
					view.findViewById(R.id.first_tab).setVisibility(
							View.INVISIBLE);
					view.findViewById(R.id.second_tab).setVisibility(
							View.VISIBLE);
					view.findViewById(R.id.third_tab).setVisibility(
							View.INVISIBLE);

					break;
				case 2:
					view.findViewById(R.id.first_tab).setVisibility(
							View.INVISIBLE);
					view.findViewById(R.id.second_tab).setVisibility(
							View.INVISIBLE);
					view.findViewById(R.id.third_tab).setVisibility(
							View.VISIBLE);

					break;

				}
			}

		});
		return view;
	}

}
