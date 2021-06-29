package com.cbw.fideltour.activity;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.MenuItem;
import com.cbw.fideltour.R;
import com.cbw.fideltour.adapter.MenuListAdapter;
import com.cbw.fideltour.fragment.ConditionChambreFragmentItem;
import com.cbw.fideltour.fragment.MainChambreFragment;
import com.cbw.fideltour.fragment.PolitiqueFragmentItem;
import com.cbw.fideltour.fragment.QualiteFragmentItem;
import com.cbw.fideltour.fragment.ServiceGratuitFragmentItem;
import com.cbw.fideltour.fragment.TarifChambreFragmentItem;
import android.support.v4.app.FragmentManager;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class MainActivityReservationChambreDrawer extends
		SherlockFragmentActivity {

	DrawerLayout mDrawerLayout;
	ListView mDrawerList;
	ActionBarDrawerToggle mDrawerToggle;
	MenuListAdapter mMenuAdapter;
	String[] title;

	int[] icon;
	MainChambreFragment fragment1 = new MainChambreFragment();
	TarifChambreFragmentItem fragment2 = new TarifChambreFragmentItem();
	ServiceGratuitFragmentItem fragment3 = new ServiceGratuitFragmentItem();
	ConditionChambreFragmentItem fragment4 = new ConditionChambreFragmentItem();
	PolitiqueFragmentItem fragment5 = new PolitiqueFragmentItem();
	QualiteFragmentItem fragment6 = new QualiteFragmentItem();
	private CharSequence mDrawerTitle;
	private CharSequence mTitle;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.drawer_main_chambre);
		mTitle = mDrawerTitle = getTitle();
		title = new String[] { "Réservation", "Tarifs", "Services",
				"Conditions de l'établissement",
				"Politique de confidentialité", "Qualité" };

		icon = new int[] { R.drawable.reservation, R.drawable.tarif,
				R.drawable.service, R.drawable.condition, R.drawable.politiq,
				R.drawable.qualite };

		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		mDrawerList = (ListView) findViewById(R.id.listview_drawer);
		mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow,
				GravityCompat.START);
		mMenuAdapter = new MenuListAdapter(
				MainActivityReservationChambreDrawer.this, title, icon);
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

			ft.replace(R.id.content_frame, fragment1);
			ft.attach(fragment1);
			ft.addToBackStack(null);
			break;
		case 1:
			ft.replace(R.id.content_frame, fragment2);
			break;
		case 2:
			ft.replace(R.id.content_frame, fragment3);
			break;
		case 3:
			ft.replace(R.id.content_frame, fragment4);
			break;
		case 4:
			ft.replace(R.id.content_frame, fragment5);
			break;
		case 5:
			ft.replace(R.id.content_frame, fragment6);
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
