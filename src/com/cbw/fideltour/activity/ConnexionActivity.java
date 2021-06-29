package com.cbw.fideltour.activity;

import java.util.ArrayList;
import java.util.List;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;
import com.cbw.fideltour.R;
import com.cbw.fideltour.entity.Client;
import com.cbw.fideltour.entity.Hotel;
import com.cbw.fideltour.parsing.JSONParser;
import android.app.ProgressDialog;

import android.os.AsyncTask;

public class ConnexionActivity extends Activity {

	private ProgressDialog pDialog;
	JSONParser jsonParser = new JSONParser();
	EditText login;
	EditText mdp;
	Button valider;
	Button Inscription;
	private int success;
	TextView con;
	TextView mdp_oublie;
	String k = "0";
	public static Client client;
	private static String url = Hotel.adresse_ip + "Login.php";
	private static final String TAG_SUCCESS = "success";

	static{
		Log.i("Static cnx act", "now");
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.connexion_layout);

		client = new Client();
		Log.i("Static cnx act", "init now");
		
		con = (TextView) findViewById(R.id.TextviewCon);
		login = (EditText) findViewById(R.id.editTextlogin);
		mdp = (EditText) findViewById(R.id.editTextmdp);
		valider = (Button) findViewById(R.id.buttonValider);
		Inscription = (Button) findViewById(R.id.buttonInscription);
		mdp_oublie = (TextView) findViewById(R.id.mdpOublie);

		login.addTextChangedListener(new TextWatcher() {

			@Override
			public void beforeTextChanged(CharSequence arg0, int arg1,
					int arg2, int arg3) {

			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {

			}

			@Override
			public void afterTextChanged(Editable s) {
				String e_mail = login.getText().toString();
				ConnexionActivity.client.setE_mail(e_mail);
			}

		});
		mdp_oublie.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				Intent myIntent = new Intent(view.getContext(),
						PasswordResetActivity.class);
				startActivity(myIntent);

			}
		});
		valider.setOnClickListener(new View.OnClickListener() {

			public void onClick(View view) {

				if (test()) {

					new LoginClient().execute();

				}
			}

		});
		Inscription.setOnClickListener(new View.OnClickListener() {

			public void onClick(View view) {
				Intent i = new Intent(getApplicationContext(),
						RegisterActivity.class);
				startActivity(i);

			}

		});

	}

	public boolean test() {

		boolean test = true;

		if (login.length() == 0) {
			login.setError("champ obligatoire");
			test = false;
		}
		if (mdp.length() == 0) {
			mdp.setError("champ obligatoire");
			test = false;
		}
		return test;

	}

	class LoginClient extends AsyncTask<String, String, String> {
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(ConnexionActivity.this);
			pDialog.setMessage("loading..");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(true);
			pDialog.show();
		}

		protected String doInBackground(String... args) {
			String e_mail = login.getText().toString();
			String mot_de_passe = mdp.getText().toString();
			String i = k.toString();

			Log.d("e_mail", e_mail);
			Log.d("mot_de_passe", mot_de_passe);

			Log.d("nombre d'erreur", i);
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("e_mail", e_mail));
			params.add(new BasicNameValuePair("mot_de_passe", mot_de_passe));
			params.add(new BasicNameValuePair("i", i));

			JSONObject json = jsonParser.makeHttpRequest(url, "POST", params);
			Log.d("Create Response", json.toString());
			try {

				Log.d("Create Response", json.toString());
				success = json.getInt(TAG_SUCCESS);

				if (success == 1) {

					Intent partclient = new Intent(getApplicationContext(),
							PartieClientActivity.class);
					startActivity(partclient);
					finish();
				} else {

				}
			} catch (JSONException e) {
				e.printStackTrace();
			}

			return null;
		}

		protected void onPostExecute(String file_url) {

			pDialog.dismiss();

			if (success == 0) {
				Toast.makeText(ConnexionActivity.this,
						"Vérifiez votre e_mail et mot de passe!!", Toast.LENGTH_SHORT)
						.show();
			} else if (success == 2) {
				Toast.makeText(ConnexionActivity.this, "vous êtes bloqué !",
						Toast.LENGTH_SHORT).show();
				valider.setClickable(false);
			}
			k = k + "1";
		}

	}

	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {

		case R.id.action_Contact:
			Intent intent1 = new Intent(ConnexionActivity.this,
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