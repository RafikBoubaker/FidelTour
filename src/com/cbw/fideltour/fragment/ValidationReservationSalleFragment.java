package com.cbw.fideltour.fragment;

import java.util.ArrayList;
import java.util.List;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;
import com.actionbarsherlock.app.SherlockFragment;
import com.cbw.fideltour.R;
import com.cbw.fideltour.activity.FactureReservationSalleActivity;
import com.cbw.fideltour.activity.MainActivity;
import com.cbw.fideltour.entity.Hotel;
import com.cbw.fideltour.parsing.JSONParser;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ValidationReservationSalleFragment extends SherlockFragment {
	public static boolean test1;
	EditText inputPrenom;
	EditText inputNom;
	EditText inputcin;
	EditText inputmail;
	Button btnValid;
	public Context context;
	JSONParser jsonParser = new JSONParser();
	int success ;
	String val1 = "0";
	String val2 = "0";
	String val3 = "0";
	private static final String TAG_SUCCESS = "success";
	private static final String TAG_MESSAGE = "message";
	private static String url = Hotel.adresse_ip + "ReservationSalle.php";

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View root = inflater.inflate(
				R.layout.validation_reservation_salle_layout, container, false);
		inputcin = (EditText) root.findViewById(R.id.editTextCINSl);
		inputNom = (EditText) root.findViewById(R.id.editTextNomSl);
		inputPrenom = (EditText) root.findViewById(R.id.editTextPrenomSl);
		inputmail = (EditText) root.findViewById(R.id.editTextMailSl);
		btnValid = (Button) root.findViewById(R.id.buttoncontinuer2);

		inputcin.addTextChangedListener(new TextWatcher() {

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
				MainSalleFragment.infSl.setId_passager(inputcin.getText().toString());
			}

		});
		inputNom.addTextChangedListener(new TextWatcher() {

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
				MainSalleFragment.infSl.setNom_passager(inputNom.getText().toString());
			}
		});
		inputPrenom.addTextChangedListener(new TextWatcher() {

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
				MainSalleFragment.infSl.setPrenom_passager(inputPrenom.getText()
						.toString());
			}
		});
		inputmail.addTextChangedListener(new TextWatcher() {

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
				MainSalleFragment.infSl.setE_mail(inputmail.getText().toString());
			}
		});

		btnValid.setOnClickListener(new View.OnClickListener() {

			public void onClick(View view) {
				
				if (test2()) {
					if (MainSalleFragment.infSl.getType_salle1() != null) {
						new AddNewReservationType1().execute();
					}
					if (MainSalleFragment.infSl.getType_salle2() != null) {
						new AddNewReservationType2().execute();
					}
					if (MainSalleFragment.infSl.getType_salle3() != null) {
						new AddNewReservationType3().execute();
					}
					//affichtoast();
				}
			}

		});
		return root;
	}

	private boolean chekmail(String email) {
		boolean test = android.util.Patterns.EMAIL_ADDRESS.matcher(email)
				.matches();
		return (test);
	}
	public void affichtoast()
{
	
	if (val1 == "1" || val2 == "1" || val3 == "1" ) { 
		
	Toast.makeText(getActivity(), "Votre réservation a été crée avec succès!",
						Toast.LENGTH_SHORT).show();
			  
	}
	
}
	public boolean test2() {

		boolean test1 = true;
		if (inputNom.getText().length() == 0) {
			inputNom.setError("champ obligatoire");
			test1 = false;

		}
		if (inputPrenom.getText().length() == 0) {
			inputPrenom.setError("champ obligatoire");
			test1 = false;
		}
		if (inputcin.getText().length() == 0) {
			inputcin.setError("champ obligatoire");
			test1 = false;
		} else if (inputcin.getText().length() != 8) {
			inputcin.setError("votre CIN doit contenir strictement 8 chiffres");
			test1 = false;
		}
		if (inputmail.getText().length() == 0) {
			inputmail.setError("champ obligatoire");
			test1 = false;
		} else if (chekmail(inputmail.getText().toString()) == false) {
			inputmail.setError("mail invalide");
			test1 = false;
		}

		return test1;

	}

	class AddNewReservationType1 extends AsyncTask<String, String, String> {

		protected String doInBackground(String... args) {
			String jour_reservation_salle = MainActivity.dtreserv.getJour_reservation_salle();
			
			String id_passager = MainSalleFragment.infSl.getId_passager();
			String nom_passager = MainSalleFragment.infSl.getNom_passager();
			String prenom_passager = MainSalleFragment.infSl.getPrenom_passager();
			String e_mail = MainSalleFragment.infSl.getE_mail();
			String heure_debut = MainSalleFragment.infSl.getHeure_debut1();
			String type_salle = MainSalleFragment.infSl.getType_salle1();
			String heure_fin = MainSalleFragment.infSl.getHeure_fin1();

			Log.d("type_salle", type_salle);
			Log.d("jour_reservation_salle", jour_reservation_salle);
			Log.d("heure_debut", heure_debut);
			Log.d("heure_fin", heure_fin);
			Log.d("id_passager", id_passager);
			Log.d("nom_passager", nom_passager);
			Log.d("prenom_passager", prenom_passager);
			Log.d("e_mail", e_mail);

			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("jour_reservation_salle",
					jour_reservation_salle));
			params.add(new BasicNameValuePair("heure_debut", heure_debut));
			params.add(new BasicNameValuePair("heure_fin", heure_fin));
			params.add(new BasicNameValuePair("id_passager", id_passager));
			params.add(new BasicNameValuePair("nom_passager", nom_passager));
			params.add(new BasicNameValuePair("prenom_passager",
					prenom_passager));
			params.add(new BasicNameValuePair("e_mail", e_mail));
			params.add(new BasicNameValuePair("type_salle", type_salle));
			JSONObject json = jsonParser.makeHttpRequest(url, "POST", params);

			Log.d("Create Response", json.toString());

			try {
				 success = json.getInt(TAG_SUCCESS);

				if (success == 1) {
					Log.d("Created!", json.toString());
					Intent i = new Intent(getActivity(),
							FactureReservationSalleActivity.class);
					startActivity(i);
					return json.getString(TAG_MESSAGE);
				} else {
					Log.d(" Failure!", json.getString(TAG_MESSAGE));
					return json.getString(TAG_MESSAGE);
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}

			return null;
		}
		protected void onPostExecute(String file_url) {
			if (success == 1) {

				val1 = "1";
			}

		}
	}

	class AddNewReservationType2 extends AsyncTask<String, String, String> {

		protected String doInBackground(String... args) {

			String jour_reservation_salle = MainActivity.dtreserv
					.getJour_reservation_salle();
			String id_passager = MainSalleFragment.infSl.getId_passager();
			String nom_passager = MainSalleFragment.infSl.getNom_passager();
			String prenom_passager = MainSalleFragment.infSl.getPrenom_passager();
			String e_mail = MainSalleFragment.infSl.getE_mail();
			String heure_debut = MainSalleFragment.infSl.getHeure_debut2();
			String type_salle = MainSalleFragment.infSl.getType_salle2();
			String heure_fin = MainSalleFragment.infSl.getHeure_fin2();

			Log.d("type_salle", type_salle);
			Log.d("jour_reservation_salle", jour_reservation_salle);
			Log.d("heure_debut", heure_debut);
			Log.d("heure_fin", heure_fin);
			Log.d("id_passager", id_passager);
			Log.d("nom_passager", nom_passager);
			Log.d("prenom_passager", prenom_passager);
			Log.d("e_mail", e_mail);

			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("jour_reservation_salle",
					jour_reservation_salle));
			params.add(new BasicNameValuePair("heure_debut", heure_debut));
			params.add(new BasicNameValuePair("heure_fin", heure_fin));
			params.add(new BasicNameValuePair("id_passager", id_passager));
			params.add(new BasicNameValuePair("nom_passager", nom_passager));
			params.add(new BasicNameValuePair("prenom_passager",
					prenom_passager));
			params.add(new BasicNameValuePair("e_mail", e_mail));
			params.add(new BasicNameValuePair("type_salle", type_salle));

			JSONObject json = jsonParser.makeHttpRequest(url, "POST", params);

			Log.d("Create Response", json.toString());

			try {
				int success = json.getInt(TAG_SUCCESS);

				if (success == 1) {
					Log.d("Created!", json.toString());
					Intent i = new Intent(getActivity(),
							FactureReservationSalleActivity.class);
					startActivity(i);
					return json.getString(TAG_MESSAGE);
				} else {
					Log.d(" Failure!", json.getString(TAG_MESSAGE));
					return json.getString(TAG_MESSAGE);
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}

			return null;
		}
		protected void onPostExecute(String file_url) {
			if (success == 1) {

				val2 = "1";
			}

		}

	}

	class AddNewReservationType3 extends AsyncTask<String, String, String> {

		protected String doInBackground(String... args) {
			String jour_reservation_salle = MainActivity.dtreserv
					.getJour_reservation_salle();
			String id_passager = MainSalleFragment.infSl.getId_passager();
			String nom_passager = MainSalleFragment.infSl.getNom_passager();
			String prenom_passager = MainSalleFragment.infSl.getPrenom_passager();
			String e_mail = MainSalleFragment.infSl.getE_mail();
			String heure_debut = MainSalleFragment.infSl.getHeure_debut3();
			String type_salle = MainSalleFragment.infSl.getType_salle3();
			String heure_fin = MainSalleFragment.infSl.getHeure_fin3();

			Log.d("type_salle", type_salle);
			Log.d("jour_reservation_salle", jour_reservation_salle);
			Log.d("heure_debut", heure_debut);
			Log.d("heure_fin", heure_fin);
			Log.d("id_passager", id_passager);
			Log.d("nom_passager", nom_passager);
			Log.d("prenom_passager", prenom_passager);
			Log.d("e_mail", e_mail);

			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("jour_reservation_salle",
					jour_reservation_salle));
			params.add(new BasicNameValuePair("heure_debut", heure_debut));
			params.add(new BasicNameValuePair("heure_fin", heure_fin));
			params.add(new BasicNameValuePair("id_passager", id_passager));
			params.add(new BasicNameValuePair("nom_passager", nom_passager));
			params.add(new BasicNameValuePair("prenom_passager",
					prenom_passager));
			params.add(new BasicNameValuePair("e_mail", e_mail));
			params.add(new BasicNameValuePair("type_salle", type_salle));

			JSONObject json = jsonParser.makeHttpRequest(url, "POST", params);

			Log.d("Create Response", json.toString());

			try {
				int success = json.getInt(TAG_SUCCESS);

				if (success == 1) {
					Log.d("Created!", json.toString());
					Intent i = new Intent(getActivity(),
							FactureReservationSalleActivity.class);
					startActivity(i);
					return json.getString(TAG_MESSAGE);
				} else {
					Log.d(" Failure!", json.getString(TAG_MESSAGE));
					return json.getString(TAG_MESSAGE);
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}

			return null;
		}
		protected void onPostExecute(String file_url) {
			if (success == 1) {

				val3 = "1";
			}

		}
	}

}
