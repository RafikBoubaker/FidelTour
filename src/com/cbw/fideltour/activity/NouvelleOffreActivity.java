package com.cbw.fideltour.activity;



import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.cbw.fideltour.R;
import com.cbw.fideltour.entity.Offre;
import com.cbw.fideltour.entity.OffreM;
import com.cbw.fideltour.parsing.JSONParser;
import com.squareup.picasso.Picasso;

public class NouvelleOffreActivity extends Activity implements OnClickListener {

	public TextView lblMessage, nomOffre, descOffre, prixOffre, delaisOffre;
	public ImageView imgOffre;
	private Button reserver;
	private Offre offre;
	private Bundle extras;
	public static OffreM off;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		/*Locale locale = new Locale("en");
		Locale.setDefault(locale);
		Configuration config = new Configuration();
		config.locale = locale;
		getBaseContext().getResources().updateConfiguration(config,
		      getBaseContext().getResources().getDisplayMetrics());*/
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.nouvelle_offre_layout);
		off= new OffreM();
		
        extras = getIntent().getExtras();
        if(extras!=null){
        	offre=(Offre) extras.get("offre");
        }
		
		imgOffre = (ImageView) findViewById(R.id.imageoff);
		nomOffre = (TextView) findViewById(R.id.nomoff);
		descOffre = (TextView) findViewById(R.id.desoff);
		prixOffre = (TextView) findViewById(R.id.prixoff);
		delaisOffre = (TextView) findViewById(R.id.delaisoff);
		reserver = (Button) findViewById(R.id.btnreserver);

		//Remplissage 
		if(offre!=null){
			Log.i("img load offre", offre.getPath());
			Picasso.with(getApplicationContext()).load(offre.getPath()).resize(220, 150).into(imgOffre);
			nomOffre.setText(offre.getNom_offre());
			descOffre.setText(offre.getDescription_offre());
			prixOffre.setText(String.valueOf(offre.getPrix_offre()));
			off.setNom_offre(nomOffre.getText().toString());
			
			delaisOffre.setText(offre.getDelai_offre());
			off.setDelai_offre(delaisOffre.getText().toString());
			Log.d("delaioffreandroid",delaisOffre.getText().toString());
		}
		
	
		reserver.setOnClickListener(this);

	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
		case R.id.btnreserver:
			Intent i = new Intent(NouvelleOffreActivity.this, ReservationOffreActivity.class);

			 //Toast.makeText(MainActivity.this,extras.getString("delais_offre"),
			 //Toast.LENGTH_SHORT).show();
			String nomOffreRes = extras.getString("nom_offre");
			String urlimgRes = extras.getString("image_offre");
			String delais = extras.getString("delais_offre");
			i.putExtra("url_imgR", urlimgRes);
			i.putExtra("nom_offreR", nomOffreRes);
			i.putExtra("delaisR", delais);
		
			startActivity(i);
			break;
		default:
			break;
		}
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		
		super.onResume();
	}
	
	
}