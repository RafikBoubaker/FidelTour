package com.cbw.fideltour.activity;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import org.json.JSONException;
import com.cbw.fideltour.R;
import com.cbw.fideltour.entity.Hotel;
import com.cbw.fideltour.parsing.JSONParser;

public class PasswordResetActivity extends Activity {

	private static String KEY_SUCCESS = "success";
	private static String KEY_ERROR = "error";
	private static String KEY_Pass = "mdp";
	JSONParser jsonParser = new JSONParser();
	JSONObject json_data;
	private static String forpassURL = Hotel.adresse_ip + "ForgetMDP.php";
	private ProgressDialog nDialog;
	EditText email;
	TextView alert;
	Button resetpass;
	String pass;
	int success;
	String res;
	String red;
	private static final String TAG_SUCCESS = "success";
	private static final String TAG_MESSAGE = "message";

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.reset_password_layout);

		email = (EditText) findViewById(R.id.forpas);
		alert = (TextView) findViewById(R.id.alert);
		resetpass = (Button) findViewById(R.id.respass);
		resetpass.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				new ProcessRegister().execute();

			}

		});
	}

	class NetCheck extends AsyncTask<String, String, Boolean>

	{
		private ProgressDialog pDialog;

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(PasswordResetActivity.this);
			pDialog.setMessage("Loading..");
			pDialog.setTitle("Checking Network");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(true);
			pDialog.show();
		}

		@Override
		protected Boolean doInBackground(String... params) {
			ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo netInfo = cm.getActiveNetworkInfo();
			if (netInfo != null && netInfo.isConnected()) {
				try {
					URL url = new URL("http://www.google.com");
					HttpURLConnection urlc = (HttpURLConnection) url
							.openConnection();
					urlc.setConnectTimeout(3000);
					urlc.connect();
					if (urlc.getResponseCode() == 200) {
						return true;
					}
				} catch (MalformedURLException e1) {

					e1.printStackTrace();
				} catch (IOException e) {

					e.printStackTrace();
				}
			}
			return false;

		}

		@Override
		protected void onPostExecute(Boolean th) {

			if (th == true) {
				pDialog.dismiss();
				new ProcessRegister().execute();
			} else {
				pDialog.dismiss();
				alert.setText("Error in Network Connection");
			}
		}
	}

	class ProcessRegister extends AsyncTask<String, String, JSONObject> {
		protected void onPreExecute() {
			super.onPreExecute();
			nDialog = new ProgressDialog(PasswordResetActivity.this);
			nDialog.setMessage("Loading..");
			nDialog.setIndeterminate(false);
			nDialog.setCancelable(true);
			nDialog.show();
		}

		@Override
		protected JSONObject doInBackground(String... args) {

			String e_mail = email.getText().toString();
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("e_mail", e_mail));

			try {
				JSONObject json = jsonParser.makeHttpRequest(forpassURL,
						"POST", params);
				success = json.getInt(TAG_SUCCESS);
				if (success == 1) {
					pass = json.getString(KEY_Pass);

					res = json.getString(KEY_SUCCESS);
					red = json.getString(KEY_ERROR);

				} else {

				}
			} catch (JSONException e) {
				e.printStackTrace();
			}

			return null;
		}

		@Override
		protected void onPostExecute(JSONObject json) {

			if (success == 1) {

				sendmailmdp();
				nDialog.dismiss();
			}

			else if (success == 2) {
				alert.setText("Your email does not exist in our database.");
				nDialog.dismiss();

			} else if(success==0){

				alert.setText("Error occured in changing Password");
				nDialog.dismiss();
			}

		}
	}

	public void sendmailmdp() {

		String subject ="Changement du Mot de passe client <<Les Ambassadeurs Hotel>>";
		String message = "Bonjour chèr client,\n\nVotre mot de passe a été changé avec succée. Votre nouveau mot de passe: "
				+"  << "+ pass
				+ " >>  .";

		String to = email.getText().toString();

		Intent email = new Intent(Intent.ACTION_SEND);
		email.putExtra(Intent.EXTRA_EMAIL, new String[] { to });
		// email.putExtra(Intent.EXTRA_EMAIL, new String[] { cc });
		email.putExtra(Intent.EXTRA_SUBJECT, subject);
		email.putExtra(Intent.EXTRA_TEXT, message);

		email.setType("message/rfc822");
		startActivity(Intent.createChooser(email, "Send mail..."));

		Log.i("Finished sending email...", "");

	}

}
