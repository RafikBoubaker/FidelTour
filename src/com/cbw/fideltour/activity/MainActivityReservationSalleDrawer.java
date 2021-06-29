package com.cbw.fideltour.activity;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.MenuItem;
import com.cbw.fideltour.R;
import com.cbw.fideltour.adapter.MenuListAdapter;
import com.cbw.fideltour.fragment.EquipementFragmentItem;
import com.cbw.fideltour.fragment.MainSalleFragment;
import com.cbw.fideltour.fragment.PolitiqueFragmentItem;
import com.cbw.fideltour.fragment.QualiteFragmentItem;
import com.cbw.fideltour.fragment.TarifSalleFragmentItem;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class MainActivityReservationSalleDrawer extends
		SherlockFragmentActivity {
	DrawerLayout mDrawerLayout;
	ListView mDrawerList;
	ActionBarDrawerToggle mDrawerToggle;
	MenuListAdapter mMenuAdapter;
	String[] title;

	int[] icon;
	MainSalleFragment fragment1 = new MainSalleFragment();
	TarifSalleFragmentItem fragment2 = new TarifSalleFragmentItem();
	EquipementFragmentItem fragment3 = new EquipementFragmentItem();
	PolitiqueFragmentItem fragment4 = new PolitiqueFragmentItem();
	QualiteFragmentItem fragment5 = new QualiteFragmentItem();
	private CharSequence mDrawerTitle;
	private CharSequence mTitle;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.drawer_main_salle);
		mTitle = mDrawerTitle = getTitle();
		title = new String[] { "Réservation", "Tarifs", "Equipement",
				"Politique de confidentialité", "Qualité" };

		icon = new int[] { R.drawable.reservation, R.drawable.tarif,
				R.drawable.equip, R.drawable.politiq, R.drawable.qualite };

		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout_sl);
		mDrawerList = (ListView) findViewById(R.id.listview_drawer_sl);

		mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow,
				GravityCompat.START);
		mMenuAdapter = new MenuListAdapter(
				MainActivityReservationSalleDrawer.this, title, icon);
		mDrawerList.setAdapter(mMenuAdapter);
		mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

		getSupportActionBar().setHomeButtonEnabled(true);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
				R.drawable.ic_drawer, R.string.app_name, R.string.app_name) {

			public void onDrawerClosed(View view) {

				super.onDrawerClosed(view);
			}

			public void onDrawerOpened(View drawerView) {

				getSupportActionBar().setTitle(mDrawerTitle);
				super.onDrawerOpened(drawerView);
			}
		};

		mDrawerLayout.setDrawerListener(mDrawerToggle);

		if (savedInstanceState == null) {
			selectItem(0);
		}
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		if (item.getItemId() == android.R.id.home) {

			if (mDrawerLayout.isDrawerOpen(mDrawerList)) {
				mDrawerLayout.closeDrawer(mDrawerList);
			} else {
				mDrawerLayout.openDrawer(mDrawerList);
			}
		}

		return super.onOptionsItemSelected(item);
	}

	private class DrawerItemClickListener implements
			ListView.OnItemClickListener {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			selectItem(position);
		}
	}

	private void selectItem(int position) {

		FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

		switch (position) {
		case 0:

			ft.replace(R.id.content_frame_sl, fragment1);
			ft.attach(fragment1);
			ft.addToBackStack(null);
			break;
		case 1:
			ft.replace(R.id.content_frame_sl, fragment2);
			break;
		case 2:
			ft.replace(R.id.content_frame_sl, fragment3);
			break;
		case 3:
			ft.replace(R.id.content_frame_sl, fragment4);
			break;
		case 4:
			ft.replace(R.id.content_frame_sl, fragment5);
			break;
		}
		ft.commit();
		mDrawerList.setItemChecked(position, true);
		setTitle(title[position]);
		mDrawerLayout.closeDrawer(mDrawerList);
	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		mDrawerToggle.syncState();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);

		mDrawerToggle.onConfigurationChanged(newConfig);
	}

	@Override
	public void setTitle(CharSequence title) {
		mTitle = title;
		getSupportActionBar().setTitle(mTitle);
	}

	@Override
	public void onBackPressed() {

		FragmentManager manager = getSupportFragmentManager();
		if (manager.getBackStackEntryCount() > 0) {

			manager.popBackStack();

		} else {

			super.onBackPressed();
		}
	}
}
