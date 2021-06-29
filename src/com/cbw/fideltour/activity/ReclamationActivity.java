package com.cbw.fideltour.activity;

import java.util.ArrayList;
import java.util.List;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;
import com.cbw.fideltour.R;
import com.cbw.fideltour.entity.Hotel;
import com.cbw.fideltour.parsing.JSONParser;
import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ReclamationActivity extends Activity {
	
	EditText Message;
	EditText Numch;
	Button annuler;
	Button envoyer;
	JSONParser jsonParser = new JSONParser();
	JSONObject json_data;
	private static String url_reclamation = Hotel.adresse_ip
			+ "EnvoyerReclamation.php";
	private static final String TAG_SUCCESS = "success";
	private static final String TAG_MESSAGE = "message";
	private int success;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.reclamation_layout);

		Message = (EditText) findViewById(R.id.editTextMessage);
		Numch = (EditText) findViewById(R.id.editTextnumch);

		annuler = (Button) findViewById(R.id.Buttonannuler);
		annuler.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {

				Numch.setText("");
				Message.setText("");
			}
		});
		envoyer = (Button) findViewById(R.id.ButtonSend);
		envoyer.setOnClickListener(new View.OnClickListener() {

			public void onClick(View view) {

				if (test()) {

					new CreateNewReclamation().execute();

				}
			}

		});

	}

	public boolean test() {

		boolean test = true;

		if (Numch.length() == 0) {
			Numch.setError("champ obligatoire");
			test = false;
		}
		if (Message.length() == 0) {
			Message.setError("champ obligatoire");
			test = false;
		}
		return test;

	}

	class CreateNewReclamation extends AsyncTask<String, String, String> {

		protected String doInBackground(String... args) {
			String message = Message.getText().toString();
			String numch = Numch.getText().toString();
			String email = ConnexionActivity.client.getE_mail();
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			
			params.add(new BasicNameValuePair("message", message));
			params.add(new BasicNameValuePair("e_mail", email));
			params.add(new BasicNameValuePair("numch", numch));
			JSONObject json = jsonParser.makeHttpRequest(url_reclamation,
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
				Toast.makeText(ReclamationActivity.this, "Reclamation envoyée",
						Toast.LENGTH_SHORT).show();
			}

		}

	}

}
