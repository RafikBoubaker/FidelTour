package com.cbw.fideltour.activity;

import java.util.Locale;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.cbw.fideltour.R;
import com.cbw.fideltour.adapter.MyFragmentPagerAdapter;
import com.cbw.fideltour.entity.DateReserv;
import com.cbw.fideltour.gcm.GcmUtils;

public class MainActivity extends FragmentActivity {
	public static DateReserv dtreserv;
	Locale myLocale;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_main);

		dtreserv = new DateReserv();

		GcmUtils utils = new GcmUtils(this);
		if (utils.checkPlayServices()) {
			utils.registerInBackground();
		} else {
			// lblMessage.setText("Registeration failed");
			Toast.makeText(getApplicationContext(),
					"Google play services not available", Toast.LENGTH_SHORT)
					.show();
		}

		ViewPager pager = (ViewPager) findViewById(R.id.pager);

		FragmentManager fm = getSupportFragmentManager();
		MyFragmentPagerAdapter pagerAdapter = new MyFragmentPagerAdapter(fm);
		pager.setAdapter(pagerAdapter);

	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {

		case R.id.action_Contact:
			Intent intent1 = new Intent(MainActivity.this,
					ContactActivity.class);
			startActivity(intent1);
			return true;

		case R.id.action_login:
			Intent intent2 = new Intent(MainActivity.this,
					ConnexionActivity.class);
			startActivity(intent2);
			return true;
		case R.id.action_fr:
			setLocale("fr");
			finish();
			return true;
		case R.id.action_an:
			setLocale("en");
			finish();
			return true;
		}

		return super.onOptionsItemSelected(item);
	}

	public void setLocale(String lang) {

		myLocale = new Locale(lang);
		Resources res = getResources();
		DisplayMetrics dm = res.getDisplayMetrics();
		Configuration conf = res.getConfiguration();
		conf.locale = myLocale;
		res.updateConfiguration(conf, dm);
		Intent refresh = new Intent(this, MainActivity.class);
		startActivity(refresh);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
}
