package com.cbw.fideltour.activity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;
import com.cbw.fideltour.R;
import com.cbw.fideltour.entity.Hotel;
import com.cbw.fideltour.parsing.JSONParser;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class RegisterActivity extends Activity {

	private ProgressDialog pDialog;
	final Context context = this;
	JSONParser jsonParser = new JSONParser();
	JSONObject json_data;
	Dialog picker1;
	DatePicker datep1;
	EditText inputCin;
	EditText inputPrenom;
	EditText inputNom;
	EditText inputEmail;
	EditText inputNumtel;
	EditText datenaissance;
	EditText lieunaissance;
	EditText inputmdp;
	EditText inputmdp2;
	Button btnOK;
	Button btnAn;
	RadioButton radioMale;
	RadioButton radioFemale;
	private int success;
	String sexe;
	String champdate1;
	Integer mMonth, mDay, mYear;

	private static String url_inscription_client = Hotel.adresse_ip
			+ "Register.php";
	private static final String TAG_SUCCESS = "success";
	private static final String TAG_MESSAGE = "message";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register_layout);

		inputCin = (EditText) findViewById(R.id.inputCin);
		btnAn = (Button) findViewById(R.id.btnAnnulerInscrip);
		inputEmail = (EditText) findViewById(R.id.inputemail);
		inputNumtel = (EditText) findViewById(R.id.inputtel);
		inputmdp = (EditText) findViewById(R.id.inputmdp);
		inputmdp2 = (EditText) findViewById(R.id.inputmdp2);
		lieunaissance = (EditText) findViewById(R.id.inputlieunaissance);
		datenaissance = (EditText) findViewById(R.id.inputdatenaissance);
		radioMale = (RadioButton) findViewById(R.id.radioMale);
		radioFemale = (RadioButton) findViewById(R.id.radioFemale);

		datenaissance.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				final Calendar c = Calendar.getInstance();
				mYear = c.get(Calendar.YEAR);
				mMonth = c.get(Calendar.MONTH);
				mDay = c.get(Calendar.DAY_OF_MONTH);
				DatePickerDialog dpd = new DatePickerDialog(context,
						new DatePickerDialog.OnDateSetListener() {
							@Override
							public void onDateSet(DatePicker view, int year,
									int monthOfYear, int dayOfMonth) {
								datenaissance.setText(year + "/"
										+ (monthOfYear + 1) + "/" + dayOfMonth);
								champdate1 = datenaissance.getText().toString();
								datenaissance.setError(null);
								MainActivity.dtreserv
										.setDateArrivee(champdate1);

							}
						}, mYear, mMonth, mDay);
				dpd.show();
			}
		});
		btnOK = (Button) findViewById(R.id.btnOK);
		btnOK.setOnClickListener(new View.OnClickListener() {

			public void onClick(View view) {

				if (test()) {

					new CreateNewClient().execute();

				}
			}

		});
		btnAn.setOnClickListener(new View.OnClickListener() {

			public void onClick(View view) {

				inputCin.setText("");
				inputEmail.setText("");
				inputNumtel.setText("");
				inputmdp.setText("");
				inputmdp2.setText("");
				lieunaissance.setText("");
				datenaissance.setText("");

			}

		});

	}

	public boolean test() {

		boolean test = true;
		if (inputCin.length() == 0) {
			inputCin.setError("champ obligatoire");
			test = false;
		}

		if (inputNumtel.length() == 0) {
			inputNumtel.setError("champ obligatoire");
			test = false;
		}
		if (inputEmail.length() == 0) {
			inputEmail.setError("champ obligatoire");
			test = false;
		} else if (chekmail(inputEmail.getText().toString()) == false) {
			inputEmail.setError("Email non valide");
			test = false;
		}

		if (lieunaissance.length() == 0) {
			lieunaissance.setError("champ obligatoire");
			test = false;
		}
		if (datenaissance.length() == 0) {
			datenaissance.setError("champ obligatoire");
			test = false;
		}
		if (inputmdp.length() == 0) {
			inputmdp.setError("champ obligatoire");
			test = false;
		}
		if (inputmdp2.getText().toString()
				.equals(inputmdp.getText().toString()) == false) {
			inputmdp2.setError("Mot de passe incorrecte");
			test = false;
		}
		return test;

	}

	private boolean chekmail(String email) {
		boolean test = android.util.Patterns.EMAIL_ADDRESS.matcher(email)
				.matches();
		return (test);
	}

	class CreateNewClient extends AsyncTask<String, String, String> {
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(RegisterActivity.this);
			pDialog.setMessage("Ajout Client..");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(true);
			pDialog.show();
		}

		protected String doInBackground(String... args) {
			String cin = inputCin.getText().toString();
			String e_mail = inputEmail.getText().toString();
			String telephone_client = inputNumtel.getText().toString();
			String lieu_naissance = lieunaissance.getText().toString();
			String date_naissance = datenaissance.getText().toString();
			String mot_de_passe = inputmdp.getText().toString();
			if (radioMale.isChecked()) {
				sexe = radioMale.getText().toString();

			} else if (radioFemale.isChecked()) {
				sexe = radioFemale.getText().toString();
			}
Log.d("cin",cin);
Log.d("e_mail",e_mail);
Log.d("sexe",sexe);
Log.d("telephone_client",telephone_client);
Log.d("lieu_naissance",lieu_naissance);
Log.d("date_naissance",date_naissance);

Log.d("mot_de_passe",mot_de_passe);
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("cin", cin));
			params.add(new BasicNameValuePair("mot_de_passe", mot_de_passe));
			params.add(new BasicNameValuePair("sexe", sexe));
			params.add(new BasicNameValuePair("date_naissance", date_naissance));
			params.add(new BasicNameValuePair("lieu_naissance", lieu_naissance));
			params.add(new BasicNameValuePair("telephone_client",
					telephone_client));
			params.add(new BasicNameValuePair("e_mail", e_mail));

			JSONObject json = jsonParser.makeHttpRequest(
					url_inscription_client, "POST", params);

			try {
				success = json.getInt(TAG_SUCCESS);
				Log.d("success",""+success);
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

			pDialog.dismiss();
			if (success == 1) {
				Toast.makeText(context, "Inscriptions séussi",
						Toast.LENGTH_SHORT).show();
			}
		}

	}

	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {

		case R.id.action_Contact:
			Intent intent1 = new Intent(RegisterActivity.this,
					ContactActivity.class);
			startActivity(intent1);
			return true;
		}

		return super.onOptionsItemSelected(item);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main2, menu);
		return true;
	}

}
