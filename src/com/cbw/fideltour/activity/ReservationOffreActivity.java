package com.cbw.fideltour.activity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;
import com.cbw.fideltour.R;
import com.cbw.fideltour.entity.Hotel;
import com.cbw.fideltour.parsing.JSONParser;
import com.squareup.picasso.Picasso;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

public class ReservationOffreActivity extends Activity {
	private static String url_accee = Hotel.adresse_ip
			+ "TestAcceeReservationOffre.php";
	JSONObject json;
	public static ImageView imageview;
	public static TextView textnomoffre;
	public static EditText textdatereservation;
	public static EditText inputcin;
	public static EditText qte;
	Button btnok;
	String nomo;
	String del;
	String dateDelais;
	String nbch;
	Spinner qttt;
	Integer mMonth, mDay, mYear;
	public Context context;
	String champdate1;
	String url_img;
	JSONParser jsonParser = new JSONParser();
	JSONObject json_data;
	private static String url_reservation = Hotel.adresse_ip
			+ "ReservationOffre.php";
	private static final String TAG_SUCCESS = "success";
	private static final String TAG_MESSAGE = "message";
	private int success;
	Bundle extras;
int verif=0;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.reservation_offre_layout);
		imageview = (ImageView) findViewById(R.id.imageoff);
		textnomoffre = (TextView) findViewById(R.id.nomoffres);
		textdatereservation = (EditText) findViewById(R.id.editTextdateresoff);
		inputcin = (EditText) findViewById(R.id.editTextcin);
		btnok = (Button) findViewById(R.id.btnokreservoffre);
		qttt= (Spinner) findViewById(R.id.editnb);
		nomo =NouvelleOffreActivity.off.getNom_offre();
		textnomoffre.setText(nomo);
		qttt.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int pos, long id) {

				nbch = parent.getItemAtPosition(pos).toString();
				

			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {

			}
		});

		btnok.setOnClickListener(new OnClickListener() {

			
			@Override
			public void onClick(View v) {
				try {
					if (test()) {
						new VerifAcce().execute();
					}
				} catch (ParseException e) {
					e.printStackTrace();
				} 
			}
		});
		textdatereservation.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				final Calendar c = Calendar.getInstance();
				mYear = c.get(Calendar.YEAR);
				mMonth = c.get(Calendar.MONTH);
				mDay = c.get(Calendar.DAY_OF_MONTH);
				DatePickerDialog dpd = new DatePickerDialog(
						ReservationOffreActivity.this,
						new DatePickerDialog.OnDateSetListener() {
							@Override
							public void onDateSet(DatePicker view, int year,
									int monthOfYear, int dayOfMonth) {
								if (mYear > year || mMonth > monthOfYear
										&& year == mYear || mDay > dayOfMonth
										&& year == mYear
										&& monthOfYear == mMonth) {

									view.updateDate(mYear, mMonth, mDay);

								}
								else {

									textdatereservation.setText(year + "-"
											+ (monthOfYear + 1) + "-"
											+ dayOfMonth);
									champdate1 = textdatereservation.getText()
											.toString();
									textdatereservation.setError(null);
						}
							}
						}, mYear, mMonth, mDay);
			
				dpd.show();
				
			}
		});

		extras = getIntent().getExtras();

		//if (extras != null) {
			/*if (extras.getString("url_imgR") != null)
				url_img = extras.getString("url_imgR");
			Picasso.with(this).load(url_img).into(imageview);

			if (extras.getString("nom_offreR") != null)
				
				textnomoffre.setText(extras.getString("nom_offreR"));

			if (extras.getString("delaisR") != null)
				dateDelais = extras.getString("delaisR");
*/

	}
	

	@Override
	protected void onResume() {
		super.onResume();

	}

	@Override
	protected void onNewIntent(Intent intent) {
		extras = intent.getExtras();
	}

	@Override
	protected void onDestroy() {

		super.onDestroy();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	protected void onPostExecute(String file_url) {
		Toast.makeText(ReservationOffreActivity.this,
				"Réservation enregistrée", Toast.LENGTH_LONG).show();

	}

	@SuppressLint("SimpleDateFormat")
	public boolean test() throws ParseException {

		boolean test = true;

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date1 = sdf.parse(textdatereservation.getText().toString());
		del=NouvelleOffreActivity.off.getDelai_offre();
		Log.d("testdate", ""+date1);
		Log.d("testdate", ""+del.toString());
		
		Date date2 = sdf.parse(del.toString());
		if (textdatereservation.length() == 0) {
			textdatereservation.setError("Invalid date");
			test = false;
		}
		if (date2.compareTo(date1) <= 0 ) {
			test = false;
			textdatereservation.setError("Invalid date");
		}

		return test;
	}

	class VerifAcce extends AsyncTask<String, String, String> {
		protected String doInBackground(String... args) {

			String cin = inputcin.getText().toString();
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("id_passager", cin));

			try {
				json = jsonParser.makeHttpRequest(url_accee, "POST", params);
				success = json.getInt(TAG_SUCCESS);
				if (success == 1) {
					
				} else {
					
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}

			return null;
		}
		protected void onPostExecute(String file_url) {
			if (success == 1)
			{new CreateNewReservation().execute();
			
			}
			else {
				Toast.makeText(
						ReservationOffreActivity.this,
						"Désolé, vous ne pouvez pas réserver que lorsque vous êtiez dans votre période séjour!",
						Toast.LENGTH_LONG).show();
			}
		
		}
	}

	class CreateNewReservation extends AsyncTask<String, String, String> {

		protected String doInBackground(String... args) {
			String dateReservationOffre = textdatereservation.getText()
					.toString();
			String id = inputcin.getText().toString();
			
			Log.d("id", ""+id);
			Log.d("dateReservationOffre", ""+dateReservationOffre);
			
			String nom_offre = textnomoffre.getText().toString();
			String nbqtte= nbch.toString();
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("id_passager", id));
			params.add(new BasicNameValuePair("dateReservation",
					dateReservationOffre));
			params.add(new BasicNameValuePair("nom_offre", nom_offre));
			params.add(new BasicNameValuePair("qte",  nbqtte));
			JSONObject json = jsonParser.makeHttpRequest(url_reservation,
					"POST", params);

			try {

				success = json.getInt(TAG_SUCCESS);

				if (success == 1) {
					return json.getString(TAG_MESSAGE);

				} else {
					return json.getString(TAG_MESSAGE);

				}
			} catch (JSONException e) {
				e.printStackTrace();
			}

			return null;

		}
		protected void onPostExecute(String file_url) {

			if (success == 1) {
			
				Toast.makeText(
						ReservationOffreActivity.this,
						"Votre réservation a été enregistrée",
						Toast.LENGTH_LONG).show();
			}
			else {
				Toast.makeText(
						ReservationOffreActivity.this,
						"Echec",
						Toast.LENGTH_LONG).show();
			}
		}

			      
		}
	
}