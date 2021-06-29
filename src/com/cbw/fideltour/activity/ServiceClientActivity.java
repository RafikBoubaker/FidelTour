package com.cbw.fideltour.activity;

import com.cbw.fideltour.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class ServiceClientActivity extends Activity {
	Button btguide;
	Button btreclamation;
	Button btvideo;
	Button btcommande;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.service_client_layout);
		btguide = (Button) findViewById(R.id.buttonguide);
		btreclamation = (Button) findViewById(R.id.buttonReclamation);
		btvideo = (Button) findViewById(R.id.buttonVideo);
		btcommande = (Button) findViewById(R.id.buttonCommande);
		

		btcommande.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent i = new Intent(ServiceClientActivity.this,
						CommandeActivity.class);
				startActivity(i);
			}
		});
		btguide.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent i = new Intent(ServiceClientActivity.this,
						MainActivityBasaDeConnaissance.class);
				startActivity(i);
			}
		});
		btreclamation.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent i = new Intent(ServiceClientActivity.this,
						ReclamationActivity.class);
				startActivity(i);
			}
		});
		btvideo.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent i = new Intent(ServiceClientActivity.this,
						VideoListeActivity.class);
				startActivity(i);
			}
		});
	}
}
