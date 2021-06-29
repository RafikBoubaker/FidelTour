package com.cbw.fideltour.fragment;

import java.util.ArrayList;
import java.util.List;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;
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
import com.actionbarsherlock.app.SherlockFragment;
import com.cbw.fideltour.R;
import com.cbw.fideltour.activity.FactureReservationChambreActivity;
import com.cbw.fideltour.activity.MainActivity;
import com.cbw.fideltour.entity.Hotel;
import com.cbw.fideltour.parsing.JSONParser;

public class ValidationReservationChambreFragment extends SherlockFragment {

	EditText inputPrenom;
	EditText inputNom;
	EditText inputEmailverif;
	EditText inputmdp;
	EditText inputcin;
	EditText inputmail;
	Button btnconnecter;
	Button btncontinuer;
	public Context context;
	JSONParser jsonParser = new JSONParser();
	String type1;
	String type2;
	String type3;
	String k = "0";
	String val1 = "0";
	String val2 = "0";
	String val3 = "0";
	String val4 = "0";
	String val5 = "0";
	String val6 = "0";
	String val7 = "0";
	String val8 = "0";
	String val9 = "0";
	String v1 = "0";
	String v2 = "0";
	String v3 = "0";
	String v4 = "0";
	String v5 = "0";
	String v6 = "0";
	String v7 = "0";
	String v8 = "0";
	String v9 = "0";
	String l1 = "0";
	String l2 = "0";
	String l3 = "0";
	String l4 = "0";
	String l5 = "0";
	String l6 = "0";
	String l7 = "0";
	String l8 = "0";
	String l9 = "0";
	String verif = "0";
	public static boolean test1;
	private static final String TAG_SUCCESS = "success";
	private static final String TAG_MESSAGE = "message";

	private static String urlconn = Hotel.adresse_ip + "Login.php";
	private static String url = Hotel.adresse_ip + "ReservationChambre.php";
	private static String url_reserv = Hotel.adresse_ip
			+ "ReservationRapideChambre.php";

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View root = inflater.inflate(
				R.layout.validation_reservation_chambre_layout, container,
				false);

		inputcin = (EditText) root.findViewById(R.id.editTextCIN);
		inputNom = (EditText) root.findViewById(R.id.editTextNom);
		inputPrenom = (EditText) root.findViewById(R.id.editTextPrenom);
		inputmail = (EditText) root.findViewById(R.id.editTextMail);
		inputEmailverif = (EditText) root.findViewById(R.id.adrMail);
		inputmdp = (EditText) root.findViewById(R.id.Motdepasse);
		btnconnecter = (Button) root.findViewById(R.id.buttonconnecter);
		btncontinuer = (Button) root.findViewById(R.id.buttoncontinuer);

		inputmdp.addTextChangedListener(new TextWatcher() {

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
				MainChambreFragment.infCh.setMdp(inputmdp.getText().toString());
			}

		});

		inputEmailverif.addTextChangedListener(new TextWatcher() {

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
				MainChambreFragment.infCh.setEmailverif(inputEmailverif
						.getText().toString());
			}

		});
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
				MainChambreFragment.infCh.setId_passager(inputcin.getText()
						.toString());
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
				MainChambreFragment.infCh.setNom_passager(inputNom.getText()
						.toString());
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
				MainChambreFragment.infCh.setPrenom_passager(inputPrenom
						.getText().toString());
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
				MainChambreFragment.infCh.setE_mail(inputmail.getText()
						.toString());
			}
		});

		btnconnecter.setOnClickListener(new View.OnClickListener() {

			public void onClick(View view) {
				if (test1()) {
					new LoginClient().execute();
					if (MainChambreFragment.infCh.getType_chambre1() != null) {
						if (MainChambreFragment.infCh.getPension1_type1() != null) {
							new AddNewReservationRapidType1pension1().execute();
						}
						if (MainChambreFragment.infCh.getPension2_type1() != null) {
							new AddNewReservationRapidType1pension2().execute();
						}
						if (MainChambreFragment.infCh.getPension3_type1() != null) {
							new AddNewReservationRapidType1pension3().execute();
						}

					}
					if (MainChambreFragment.infCh.getType_chambre2() != null) {
						if (MainChambreFragment.infCh.getPension1_type2() != null) {
							new AddNewReservationRapidType2pension1().execute();
						}
						if (MainChambreFragment.infCh.getPension2_type2() != null) {
							new AddNewReservationRapidType2pension2().execute();
						}
						if (MainChambreFragment.infCh.getPension3_type2() != null) {
							new AddNewReservationRapidType2pension3().execute();
						}
					}
					if (MainChambreFragment.infCh.getType_chambre3() != null) {
						if (MainChambreFragment.infCh.getPension1_type3() != null) {
							new AddNewReservationRapidType3pension1().execute();
						}
						if (MainChambreFragment.infCh.getPension2_type3() != null) {
							new AddNewReservationRapidType3pension2().execute();
						}
						if (MainChambreFragment.infCh.getPension3_type3() != null) {
							new AddNewReservationRapidType3pension3().execute();
						}
					}
				//	affichtoast2();

				}

			}
		});

		btncontinuer.setOnClickListener(new View.OnClickListener() {

			public void onClick(View view) {

				if (test2()) {
					if (MainChambreFragment.infCh.getType_chambre1() != null) {
						if (MainChambreFragment.infCh.getPension1_type1() != null) {
							new AddNewReservationType1pension1().execute();
						}
						if (MainChambreFragment.infCh.getPension2_type1() != null) {
							new AddNewReservationType1pension2().execute();
						}
						if (MainChambreFragment.infCh.getPension3_type1() != null) {
							new AddNewReservationType1pension2().execute();
						}

					}
					if (MainChambreFragment.infCh.getType_chambre2() != null) {
						if (MainChambreFragment.infCh.getPension1_type2() != null) {
							new AddNewReservationType2pension1().execute();
						}
						if (MainChambreFragment.infCh.getPension2_type2() != null) {
							new AddNewReservationType2pension2().execute();
						}
						if (MainChambreFragment.infCh.getPension3_type2() != null) {
							new AddNewReservationType2pension3().execute();
						}

					}
					if (MainChambreFragment.infCh.getType_chambre3() != null) {
						if (MainChambreFragment.infCh.getPension1_type3() != null) {
							new AddNewReservationType3pension1().execute();
						}
						if (MainChambreFragment.infCh.getPension2_type3() != null) {
							new AddNewReservationType3pension2().execute();
						}
						if (MainChambreFragment.infCh.getPension3_type3() != null) {
							new AddNewReservationType3pension3().execute();
						}

					}
					
					
				}

			}

		});

		return root;
	}

	public void affichtoast() {

		if (val1 == "1" || val2 == "1" || val3 == "1" || val4 == "1"
				|| val5 == "1" || val6 == "1" || val7 == "1" || val8 == "1"
				|| val9 == "1") {

			Toast.makeText(getActivity(),
					"Votre réservation a été crée avec succès!",
					Toast.LENGTH_SHORT).show();

		} else {

			Toast.makeText(getActivity(), "Echec de réservation !",
					Toast.LENGTH_SHORT).show();

		}
	}

	public void affichtoast2() {
		if (v1 == "1" || v2 == "1" || v3 == "1" || v4 == "1" || v5 == "1"
				|| v6 == "1" || v7 == "1" || v8 == "1" || v9 == "1") {
			Toast.makeText(getActivity(),
					"Votre réservation a été crée avec succès!",
					Toast.LENGTH_SHORT).show();

		}

		else {

			Toast.makeText(getActivity(), "Echec de réservation !",
					Toast.LENGTH_SHORT).show();

		}

	}

	public boolean test1() {
		boolean test = true;
		if (inputmdp.getText().length() == 0) {
			inputmdp.setError("champ obligatoire");
			test = false;
		}

		if (inputEmailverif.length() == 0) {
			inputEmailverif.setError("champ obligatoire");
			test = false;
		} else if (chekmail(inputEmailverif.getText().toString()) == false) {
			inputEmailverif.setError("Email non valide");
			test = false;
		}
		return test;
	}

	private boolean chekmail(String email) {
		boolean test = android.util.Patterns.EMAIL_ADDRESS.matcher(email)
				.matches();
		return (test);
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

	class AddNewReservationType1pension1 extends
			AsyncTask<String, String, String> {
		int success;

		protected String doInBackground(String... args) {
			String date_arrivee = MainActivity.dtreserv.getDateArrivee();
			String date_depart = MainActivity.dtreserv.getDateDepart();
			String id_passager = MainChambreFragment.infCh.getId_passager();
			String nom_passager = MainChambreFragment.infCh.getNom_passager();
			String prenom_passager = MainChambreFragment.infCh
					.getPrenom_passager();
			String e_mail = MainChambreFragment.infCh.getE_mail();
			String nbpension = MainChambreFragment.infCh
					.getNbreCh_pension1_type1();
			String type_chambre = MainChambreFragment.infCh.getType_chambre1();
			String nom_pension = MainChambreFragment.infCh.getPension1_type1();

			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("date_arrivee", date_arrivee));
			params.add(new BasicNameValuePair("date_depart", date_depart));
			params.add(new BasicNameValuePair("id_passager", id_passager));
			params.add(new BasicNameValuePair("nom_passager", nom_passager));
			params.add(new BasicNameValuePair("prenom_passager",
					prenom_passager));
			params.add(new BasicNameValuePair("e_mail", e_mail));
			params.add(new BasicNameValuePair("nbpension", nbpension));
			params.add(new BasicNameValuePair("type_chambre", type_chambre));
			params.add(new BasicNameValuePair("nom_pension", nom_pension));

			JSONObject json = jsonParser.makeHttpRequest(url, "POST", params);

			try {
				success = json.getInt(TAG_SUCCESS);

				if (success == 1) {
					Log.d("Created!", json.toString());
					Intent i = new Intent(getActivity(),
							FactureReservationChambreActivity.class);
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

	class AddNewReservationType1pension2 extends
			AsyncTask<String, String, String> {
		int success;

		protected String doInBackground(String... args) {
			String date_arrivee = MainActivity.dtreserv.getDateArrivee();
			String date_depart = MainActivity.dtreserv.getDateDepart();
			String id_passager = MainChambreFragment.infCh.getId_passager();
			String nom_passager = MainChambreFragment.infCh.getNom_passager();
			String prenom_passager = MainChambreFragment.infCh
					.getPrenom_passager();
			String e_mail = MainChambreFragment.infCh.getE_mail();
			String nbpension = MainChambreFragment.infCh
					.getNbreCh_pension2_type1();
			String type_chambre = MainChambreFragment.infCh.getType_chambre1();
			String nom_pension = MainChambreFragment.infCh.getPension2_type1();

			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("date_arrivee", date_arrivee));
			params.add(new BasicNameValuePair("date_depart", date_depart));
			params.add(new BasicNameValuePair("id_passager", id_passager));
			params.add(new BasicNameValuePair("nom_passager", nom_passager));
			params.add(new BasicNameValuePair("prenom_passager",
					prenom_passager));
			params.add(new BasicNameValuePair("e_mail", e_mail));
			params.add(new BasicNameValuePair("nbpension", nbpension));
			params.add(new BasicNameValuePair("type_chambre", type_chambre));
			params.add(new BasicNameValuePair("nom_pension", nom_pension));

			JSONObject json = jsonParser.makeHttpRequest(url, "POST", params);

			Log.d("Create Response", json.toString());

			try {
				success = json.getInt(TAG_SUCCESS);

				if (success == 1) {
					Intent i = new Intent(getActivity(),
							FactureReservationChambreActivity.class);
					startActivity(i);
					Log.d("Created!", json.toString());
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

	class AddNewReservationType1pension3 extends
			AsyncTask<String, String, String> {
		int success;

		protected String doInBackground(String... args) {
			String date_arrivee = MainActivity.dtreserv.getDateArrivee();
			String date_depart = MainActivity.dtreserv.getDateDepart();
			String id_passager = MainChambreFragment.infCh.getId_passager();
			String nom_passager = MainChambreFragment.infCh.getNom_passager();
			String prenom_passager = MainChambreFragment.infCh
					.getPrenom_passager();
			String e_mail = MainChambreFragment.infCh.getE_mail();
			String nbpension = MainChambreFragment.infCh
					.getNbreCh_pension3_type1();
			String type_chambre = MainChambreFragment.infCh.getType_chambre1();
			String nom_pension = MainChambreFragment.infCh.getPension3_type1();
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("date_arrivee", date_arrivee));
			params.add(new BasicNameValuePair("date_depart", date_depart));
			params.add(new BasicNameValuePair("id_passager", id_passager));
			params.add(new BasicNameValuePair("nom_passager", nom_passager));
			params.add(new BasicNameValuePair("prenom_passager",
					prenom_passager));
			params.add(new BasicNameValuePair("e_mail", e_mail));
			params.add(new BasicNameValuePair("nbpension", nbpension));
			params.add(new BasicNameValuePair("type_chambre", type_chambre));
			params.add(new BasicNameValuePair("nom_pension", nom_pension));

			JSONObject json = jsonParser.makeHttpRequest(url, "POST", params);

			Log.d("Create Response", json.toString());

			try {
				success = json.getInt(TAG_SUCCESS);

				if (success == 1) {
					Log.d("Created!", json.toString());
					Intent i = new Intent(getActivity(),
							FactureReservationChambreActivity.class);
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

	class LoginClient extends AsyncTask<String, String, String> {
		private int success;

		protected String doInBackground(String... args) {
			String e_mail = MainChambreFragment.infCh.getEmailverif();
			String mot_de_passe = MainChambreFragment.infCh.getMdp();
			String i = k.toString();

			Log.d("e_mail", e_mail);
			Log.d("mot_de_passe", mot_de_passe);

			Log.d("nombre d'erreur", i);
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("e_mail", e_mail));
			params.add(new BasicNameValuePair("mot_de_passe", mot_de_passe));
			params.add(new BasicNameValuePair("i", i));

			JSONObject json = jsonParser.makeHttpRequest(urlconn, "POST",
					params);
			Log.d("Create Response", json.toString());
			try {

				Log.d("Create Response", json.toString());
				success = json.getInt(TAG_SUCCESS);

				if (success == 1) {

					Log.d("Created!", json.toString());
					return json.getString(TAG_MESSAGE);
				} else {

				}
			} catch (JSONException e) {
				e.printStackTrace();
			}

			return null;
		}

		protected void onPostExecute(String file_url) {

			if (success == 1) {
				verif = "1";
			}
			if (success == 0) {
				Toast.makeText(getActivity(), "Invalid Login Credentials!",
						Toast.LENGTH_SHORT).show();
			} else if (success == 2) {
				Toast.makeText(getActivity(),
						"Esssayer de remplir le formulaire ci-dessous",
						Toast.LENGTH_SHORT).show();
				btnconnecter.setClickable(false);
			}
			k = k + "1";
		}

	}

	class AddNewReservationType2pension1 extends
			AsyncTask<String, String, String> {
		int success;

		protected String doInBackground(String... args) {
			String date_arrivee = MainActivity.dtreserv.getDateArrivee();
			String date_depart = MainActivity.dtreserv.getDateDepart();
			String id_passager = MainChambreFragment.infCh.getId_passager();
			String nom_passager = MainChambreFragment.infCh.getNom_passager();
			String prenom_passager = MainChambreFragment.infCh
					.getPrenom_passager();
			String e_mail = MainChambreFragment.infCh.getE_mail();
			String nbpension = MainChambreFragment.infCh
					.getNbreCh_pension1_type2();
			String type_chambre = MainChambreFragment.infCh.getType_chambre2();
			String nom_pension = MainChambreFragment.infCh.getPension1_type2();

			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("date_arrivee", date_arrivee));
			params.add(new BasicNameValuePair("date_depart", date_depart));
			params.add(new BasicNameValuePair("id_passager", id_passager));
			params.add(new BasicNameValuePair("nom_passager", nom_passager));
			params.add(new BasicNameValuePair("prenom_passager",
					prenom_passager));
			params.add(new BasicNameValuePair("e_mail", e_mail));
			params.add(new BasicNameValuePair("nbpension", nbpension));
			params.add(new BasicNameValuePair("type_chambre", type_chambre));
			params.add(new BasicNameValuePair("nom_pension", nom_pension));

			JSONObject json = jsonParser.makeHttpRequest(url, "POST", params);

			Log.d("Create Response", json.toString());

			try {
				success = json.getInt(TAG_SUCCESS);

				if (success == 1) {
					Log.d("Created!", json.toString());
					Intent i = new Intent(getActivity(),
							FactureReservationChambreActivity.class);
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

				val4 = "1";
			}

		}
	}

	class AddNewReservationType2pension2 extends
			AsyncTask<String, String, String> {
		int success;

		protected String doInBackground(String... args) {
			String date_arrivee = MainActivity.dtreserv.getDateArrivee();
			String date_depart = MainActivity.dtreserv.getDateDepart();
			String id_passager = MainChambreFragment.infCh.getId_passager();
			String nom_passager = MainChambreFragment.infCh.getNom_passager();
			String prenom_passager = MainChambreFragment.infCh
					.getPrenom_passager();
			String e_mail = MainChambreFragment.infCh.getE_mail();
			String nbpension = MainChambreFragment.infCh
					.getNbreCh_pension2_type2();
			String type_chambre = MainChambreFragment.infCh.getType_chambre2();
			String nom_pension = MainChambreFragment.infCh.getPension2_type2();

			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("date_arrivee", date_arrivee));
			params.add(new BasicNameValuePair("date_depart", date_depart));
			params.add(new BasicNameValuePair("id_passager", id_passager));
			params.add(new BasicNameValuePair("nom_passager", nom_passager));
			params.add(new BasicNameValuePair("prenom_passager",
					prenom_passager));
			params.add(new BasicNameValuePair("e_mail", e_mail));
			params.add(new BasicNameValuePair("nbpension", nbpension));
			params.add(new BasicNameValuePair("type_chambre", type_chambre));
			params.add(new BasicNameValuePair("nom_pension", nom_pension));

			JSONObject json = jsonParser.makeHttpRequest(url, "POST", params);

			Log.d("Create Response", json.toString());

			try {
				success = json.getInt(TAG_SUCCESS);

				if (success == 1) {
					Intent i = new Intent(getActivity(),
							FactureReservationChambreActivity.class);
					startActivity(i);
					Log.d("Created!", json.toString());
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

				val5 = "1";
			}

		}
	}

	class AddNewReservationType2pension3 extends
			AsyncTask<String, String, String> {
		int success;

		protected String doInBackground(String... args) {
			String date_arrivee = MainActivity.dtreserv.getDateArrivee();
			String date_depart = MainActivity.dtreserv.getDateDepart();
			String id_passager = MainChambreFragment.infCh.getId_passager();
			String nom_passager = MainChambreFragment.infCh.getNom_passager();
			String prenom_passager = MainChambreFragment.infCh
					.getPrenom_passager();
			String e_mail = MainChambreFragment.infCh.getE_mail();
			String nbpension = MainChambreFragment.infCh
					.getNbreCh_pension3_type2();
			String type_chambre = MainChambreFragment.infCh.getType_chambre2();
			String nom_pension = MainChambreFragment.infCh.getPension3_type2();

			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("date_arrivee", date_arrivee));
			params.add(new BasicNameValuePair("date_depart", date_depart));
			params.add(new BasicNameValuePair("id_passager", id_passager));
			params.add(new BasicNameValuePair("nom_passager", nom_passager));
			params.add(new BasicNameValuePair("prenom_passager",
					prenom_passager));
			params.add(new BasicNameValuePair("e_mail", e_mail));
			params.add(new BasicNameValuePair("nbpension", nbpension));
			params.add(new BasicNameValuePair("type_chambre", type_chambre));
			params.add(new BasicNameValuePair("nom_pension", nom_pension));

			JSONObject json = jsonParser.makeHttpRequest(url, "POST", params);

			Log.d("Create Response", json.toString());

			try {
				success = json.getInt(TAG_SUCCESS);

				if (success == 1) {
					Log.d("Created!", json.toString());
					Intent i = new Intent(getActivity(),
							FactureReservationChambreActivity.class);
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

				val6 = "1";
			}

		}
	}

	class AddNewReservationType3pension1 extends
			AsyncTask<String, String, String> {
		int success;

		protected String doInBackground(String... args) {
			String date_arrivee = MainActivity.dtreserv.getDateArrivee();
			String date_depart = MainActivity.dtreserv.getDateDepart();
			String id_passager = MainChambreFragment.infCh.getId_passager();
			String nom_passager = MainChambreFragment.infCh.getNom_passager();
			String prenom_passager = MainChambreFragment.infCh
					.getPrenom_passager();
			String e_mail = MainChambreFragment.infCh.getE_mail();
			String nbpension = MainChambreFragment.infCh
					.getNbreCh_pension1_type3();
			String type_chambre = MainChambreFragment.infCh.getType_chambre3();
			String nom_pension = MainChambreFragment.infCh.getPension1_type3();

			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("date_arrivee", date_arrivee));
			params.add(new BasicNameValuePair("date_depart", date_depart));
			params.add(new BasicNameValuePair("id_passager", id_passager));
			params.add(new BasicNameValuePair("nom_passager", nom_passager));
			params.add(new BasicNameValuePair("prenom_passager",
					prenom_passager));
			params.add(new BasicNameValuePair("e_mail", e_mail));
			params.add(new BasicNameValuePair("nbpension", nbpension));
			params.add(new BasicNameValuePair("type_chambre", type_chambre));
			params.add(new BasicNameValuePair("nom_pension", nom_pension));

			JSONObject json = jsonParser.makeHttpRequest(url, "POST", params);

			Log.d("Create Response", json.toString());

			try {
				success = json.getInt(TAG_SUCCESS);

				if (success == 1) {
					Log.d("Created!", json.toString());
					Intent i = new Intent(getActivity(),
							FactureReservationChambreActivity.class);
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

				val7 = "1";
			}

		}
	}

	class AddNewReservationType3pension2 extends
			AsyncTask<String, String, String> {
		int success;

		protected String doInBackground(String... args) {
			String date_arrivee = MainActivity.dtreserv.getDateArrivee();
			String date_depart = MainActivity.dtreserv.getDateDepart();
			String id_passager = MainChambreFragment.infCh.getId_passager();
			String nom_passager = MainChambreFragment.infCh.getNom_passager();
			String prenom_passager = MainChambreFragment.infCh
					.getPrenom_passager();
			String e_mail = MainChambreFragment.infCh.getE_mail();
			String nbpension = MainChambreFragment.infCh
					.getNbreCh_pension2_type3();
			String type_chambre = MainChambreFragment.infCh.getType_chambre3();
			String nom_pension = MainChambreFragment.infCh.getPension2_type3();

			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("date_arrivee", date_arrivee));
			params.add(new BasicNameValuePair("date_depart", date_depart));
			params.add(new BasicNameValuePair("id_passager", id_passager));
			params.add(new BasicNameValuePair("nom_passager", nom_passager));
			params.add(new BasicNameValuePair("prenom_passager",
					prenom_passager));
			params.add(new BasicNameValuePair("e_mail", e_mail));
			params.add(new BasicNameValuePair("nbpension", nbpension));
			params.add(new BasicNameValuePair("type_chambre", type_chambre));
			params.add(new BasicNameValuePair("nom_pension", nom_pension));

			JSONObject json = jsonParser.makeHttpRequest(url, "POST", params);
			Log.d("Create Response", json.toString());

			try {
				success = json.getInt(TAG_SUCCESS);

				if (success == 1) {
					Intent i = new Intent(getActivity(),
							FactureReservationChambreActivity.class);
					startActivity(i);
					Log.d("Created!", json.toString());
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

				val8 = "1";
			}

		}
	}

	class AddNewReservationType3pension3 extends
			AsyncTask<String, String, String> {
		int success;

		protected String doInBackground(String... args) {
			String date_arrivee = MainActivity.dtreserv.getDateArrivee();
			String date_depart = MainActivity.dtreserv.getDateDepart();
			String id_passager = MainChambreFragment.infCh.getId_passager();
			String nom_passager = MainChambreFragment.infCh.getNom_passager();
			String prenom_passager = MainChambreFragment.infCh
					.getPrenom_passager();
			String e_mail = MainChambreFragment.infCh.getE_mail();
			String nbpension = MainChambreFragment.infCh
					.getNbreCh_pension3_type3();
			String type_chambre = MainChambreFragment.infCh.getType_chambre3();
			String nom_pension = MainChambreFragment.infCh.getPension3_type3();

			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("date_arrivee", date_arrivee));
			params.add(new BasicNameValuePair("date_depart", date_depart));
			params.add(new BasicNameValuePair("id_passager", id_passager));
			params.add(new BasicNameValuePair("nom_passager", nom_passager));
			params.add(new BasicNameValuePair("prenom_passager",
					prenom_passager));
			params.add(new BasicNameValuePair("e_mail", e_mail));
			params.add(new BasicNameValuePair("nbpension", nbpension));
			params.add(new BasicNameValuePair("type_chambre", type_chambre));
			params.add(new BasicNameValuePair("nom_pension", nom_pension));

			JSONObject json = jsonParser.makeHttpRequest(url, "POST", params);

			Log.d("Create Response", json.toString());

			try {
				success = json.getInt(TAG_SUCCESS);

				if (success == 1) {
					Log.d("Created!", json.toString());
					Intent i = new Intent(getActivity(),
							FactureReservationChambreActivity.class);
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

				val9 = "1";
			}

		}
	}

	class AddNewReservationRapidType1pension1 extends
			AsyncTask<String, String, String> {
		int success;

		protected String doInBackground(String... args) {
			String date_arrivee = MainActivity.dtreserv.getDateArrivee();
			String date_depart = MainActivity.dtreserv.getDateDepart();
			String mot_de_passe = MainChambreFragment.infCh.getMdp();
			String e_mail = MainChambreFragment.infCh.getEmailverif();
			String nbpension = MainChambreFragment.infCh
					.getNbreCh_pension1_type1();
			String type_chambre = MainChambreFragment.infCh.getType_chambre1();
			String nom_pension = MainChambreFragment.infCh.getPension1_type1();

			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("date_arrivee", date_arrivee));
			params.add(new BasicNameValuePair("date_depart", date_depart));
			params.add(new BasicNameValuePair("mot_de_passe", mot_de_passe));

			params.add(new BasicNameValuePair("e_mail", e_mail));
			params.add(new BasicNameValuePair("nbpension", nbpension));
			params.add(new BasicNameValuePair("type_chambre", type_chambre));
			params.add(new BasicNameValuePair("nom_pension", nom_pension));

			JSONObject json = jsonParser.makeHttpRequest(url_reserv, "POST",
					params);
			Log.d("Create Response", json.toString());
			try {
				success = json.getInt(TAG_SUCCESS);

				if (success == 1) {
					Intent i = new Intent(getActivity(),
							FactureReservationChambreActivity.class);
					startActivity(i);
					Log.d("Created!", json.toString());
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

				v1 = "1";
			} else if (success == 3) {

				l1 = "1";
			}

		}
	}

	class AddNewReservationRapidType1pension2 extends
			AsyncTask<String, String, String> {
		int success;

		protected String doInBackground(String... args) {
			String date_arrivee = MainActivity.dtreserv.getDateArrivee();
			String date_depart = MainActivity.dtreserv.getDateDepart();
			String mot_de_passe = MainChambreFragment.infCh.getMdp();
			String e_mail = MainChambreFragment.infCh.getEmailverif();
			String nbpension = MainChambreFragment.infCh
					.getNbreCh_pension2_type1();
			String type_chambre = MainChambreFragment.infCh.getType_chambre1();
			String nom_pension = MainChambreFragment.infCh.getPension2_type1();

			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("date_arrivee", date_arrivee));
			params.add(new BasicNameValuePair("date_depart", date_depart));
			params.add(new BasicNameValuePair("mot_de_passe", mot_de_passe));

			params.add(new BasicNameValuePair("e_mail", e_mail));
			params.add(new BasicNameValuePair("nbpension", nbpension));
			params.add(new BasicNameValuePair("type_chambre", type_chambre));
			params.add(new BasicNameValuePair("nom_pension", nom_pension));

			JSONObject json = jsonParser.makeHttpRequest(url_reserv, "POST",
					params);
			Log.d("Create Response", json.toString());
			try {
				success = json.getInt(TAG_SUCCESS);

				if (success == 1) {
					Log.d("Created!", json.toString());
					Intent i = new Intent(getActivity(),
							FactureReservationChambreActivity.class);
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

				v2 = "1";
			} else if (success == 3) {

				l2 = "1";
			}
		}
	}

	class AddNewReservationRapidType1pension3 extends
			AsyncTask<String, String, String> {
		int success;

		protected String doInBackground(String... args) {
			String date_arrivee = MainActivity.dtreserv.getDateArrivee();
			String date_depart = MainActivity.dtreserv.getDateDepart();
			String mot_de_passe = MainChambreFragment.infCh.getMdp();
			String e_mail = MainChambreFragment.infCh.getEmailverif();
			String nbpension = MainChambreFragment.infCh
					.getNbreCh_pension3_type1();
			String type_chambre = MainChambreFragment.infCh.getType_chambre1();
			String nom_pension = MainChambreFragment.infCh.getPension3_type1();

			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("date_arrivee", date_arrivee));
			params.add(new BasicNameValuePair("date_depart", date_depart));
			params.add(new BasicNameValuePair("mot_de_passe", mot_de_passe));

			params.add(new BasicNameValuePair("e_mail", e_mail));
			params.add(new BasicNameValuePair("nbpension", nbpension));
			params.add(new BasicNameValuePair("type_chambre", type_chambre));
			params.add(new BasicNameValuePair("nom_pension", nom_pension));
			JSONObject json = jsonParser.makeHttpRequest(url_reserv, "POST",
					params);
			Log.d("Create Response", json.toString());
			try {
				success = json.getInt(TAG_SUCCESS);

				if (success == 1) {
					Log.d("Created!", json.toString());
					Intent i = new Intent(getActivity(),
							FactureReservationChambreActivity.class);
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

				v3 = "1";
			} else if (success == 3) {

				l3 = "1";
			}
		}
	}

	class AddNewReservationRapidType2pension1 extends
			AsyncTask<String, String, String> {
		int success;

		protected String doInBackground(String... args) {
			String date_arrivee = MainActivity.dtreserv.getDateArrivee();
			String date_depart = MainActivity.dtreserv.getDateDepart();
			String mot_de_passe = MainChambreFragment.infCh.getMdp();
			String e_mail = MainChambreFragment.infCh.getEmailverif();
			String nbpension = MainChambreFragment.infCh
					.getNbreCh_pension1_type2();
			String type_chambre = MainChambreFragment.infCh.getType_chambre2();
			String nom_pension = MainChambreFragment.infCh.getPension1_type2();

			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("date_arrivee", date_arrivee));
			params.add(new BasicNameValuePair("date_depart", date_depart));
			params.add(new BasicNameValuePair("mot_de_passe", mot_de_passe));
			params.add(new BasicNameValuePair("e_mail", e_mail));
			params.add(new BasicNameValuePair("nbpension", nbpension));
			params.add(new BasicNameValuePair("type_chambre", type_chambre));
			params.add(new BasicNameValuePair("nom_pension", nom_pension));

			JSONObject json = jsonParser.makeHttpRequest(url_reserv, "POST",
					params);
			Log.d("Create Response", json.toString());
			try {
				success = json.getInt(TAG_SUCCESS);

				if (success == 1) {
					Log.d("Created!", json.toString());
					Intent i = new Intent(getActivity(),
							FactureReservationChambreActivity.class);
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

				v4 = "1";
			} else if (success == 3) {

				l4 = "1";
			}
		}
	}

	class AddNewReservationRapidType2pension2 extends
			AsyncTask<String, String, String> {
		int success;

		protected String doInBackground(String... args) {
			String date_arrivee = MainActivity.dtreserv.getDateArrivee();
			String date_depart = MainActivity.dtreserv.getDateDepart();
			String mot_de_passe = MainChambreFragment.infCh.getMdp();
			String e_mail = MainChambreFragment.infCh.getEmailverif();
			String nbpension = MainChambreFragment.infCh
					.getNbreCh_pension2_type2();
			String type_chambre = MainChambreFragment.infCh.getType_chambre2();
			String nom_pension = MainChambreFragment.infCh.getPension2_type2();

			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("date_arrivee", date_arrivee));
			params.add(new BasicNameValuePair("date_depart", date_depart));
			params.add(new BasicNameValuePair("mot_de_passe", mot_de_passe));
			params.add(new BasicNameValuePair("e_mail", e_mail));
			params.add(new BasicNameValuePair("nbpension", nbpension));
			params.add(new BasicNameValuePair("type_chambre", type_chambre));
			params.add(new BasicNameValuePair("nom_pension", nom_pension));

			JSONObject json = jsonParser.makeHttpRequest(url_reserv, "POST",
					params);

			Log.d("Create Response", json.toString());

			try {
				success = json.getInt(TAG_SUCCESS);

				if (success == 1) {
					Log.d("Created!", json.toString());
					Intent i = new Intent(getActivity(),
							FactureReservationChambreActivity.class);
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

				v5 = "1";
			} else if (success == 3) {

				l5 = "1";
			}
		}
	}

	class AddNewReservationRapidType2pension3 extends
			AsyncTask<String, String, String> {
		int success;

		protected String doInBackground(String... args) {
			String date_arrivee = MainActivity.dtreserv.getDateArrivee();
			String date_depart = MainActivity.dtreserv.getDateDepart();
			String mot_de_passe = MainChambreFragment.infCh.getMdp();
			String e_mail = MainChambreFragment.infCh.getEmailverif();
			String nbpension = MainChambreFragment.infCh
					.getNbreCh_pension3_type2();
			String type_chambre = MainChambreFragment.infCh.getType_chambre2();
			String nom_pension = MainChambreFragment.infCh.getPension3_type2();

			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("date_arrivee", date_arrivee));
			params.add(new BasicNameValuePair("date_depart", date_depart));
			params.add(new BasicNameValuePair("mot_de_passe", mot_de_passe));

			params.add(new BasicNameValuePair("e_mail", e_mail));
			params.add(new BasicNameValuePair("nbpension", nbpension));
			params.add(new BasicNameValuePair("type_chambre", type_chambre));
			params.add(new BasicNameValuePair("nom_pension", nom_pension));
			JSONObject json = jsonParser.makeHttpRequest(url_reserv, "POST",
					params);

			Log.d("Create Response", json.toString());
			try {
				success = json.getInt(TAG_SUCCESS);

				if (success == 1) {
					Log.d("Created!", json.toString());
					Intent i = new Intent(getActivity(),
							FactureReservationChambreActivity.class);
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

				v6 = "1";
			} else if (success == 3) {

				l6 = "1";
			}
		}
	}

	class AddNewReservationRapidType3pension1 extends
			AsyncTask<String, String, String> {
		int success;

		protected String doInBackground(String... args) {
			String date_arrivee = MainActivity.dtreserv.getDateArrivee();
			String date_depart = MainActivity.dtreserv.getDateDepart();
			String mot_de_passe = MainChambreFragment.infCh.getMdp();
			String e_mail = MainChambreFragment.infCh.getEmailverif();
			String nbpension = MainChambreFragment.infCh
					.getNbreCh_pension1_type3();
			String type_chambre = MainChambreFragment.infCh.getType_chambre3();
			String nom_pension = MainChambreFragment.infCh.getPension1_type3();

			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("date_arrivee", date_arrivee));
			params.add(new BasicNameValuePair("date_depart", date_depart));
			params.add(new BasicNameValuePair("mot_de_passe", mot_de_passe));

			params.add(new BasicNameValuePair("e_mail", e_mail));
			params.add(new BasicNameValuePair("nbpension", nbpension));
			params.add(new BasicNameValuePair("type_chambre", type_chambre));
			params.add(new BasicNameValuePair("nom_pension", nom_pension));

			JSONObject json = jsonParser.makeHttpRequest(url_reserv, "POST",
					params);

			Log.d("Create Response", json.toString());
			try {
				success = json.getInt(TAG_SUCCESS);

				if (success == 1) {
					Log.d("Created!", json.toString());
					Intent i = new Intent(getActivity(),
							FactureReservationChambreActivity.class);
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

				v7 = "1";
			} else if (success == 3) {

				l7 = "1";
			}
		}
	}

	class AddNewReservationRapidType3pension2 extends
			AsyncTask<String, String, String> {
		int success;

		protected String doInBackground(String... args) {
			String date_arrivee = MainActivity.dtreserv.getDateArrivee();
			String date_depart = MainActivity.dtreserv.getDateDepart();
			String mot_de_passe = MainChambreFragment.infCh.getMdp();
			String e_mail = MainChambreFragment.infCh.getEmailverif();
			String nbpension = MainChambreFragment.infCh
					.getNbreCh_pension2_type3();
			String type_chambre = MainChambreFragment.infCh.getType_chambre3();
			String nom_pension = MainChambreFragment.infCh.getPension2_type3();

			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("date_arrivee", date_arrivee));
			params.add(new BasicNameValuePair("date_depart", date_depart));
			params.add(new BasicNameValuePair("mot_de_passe", mot_de_passe));
			params.add(new BasicNameValuePair("e_mail", e_mail));
			params.add(new BasicNameValuePair("nbpension", nbpension));
			params.add(new BasicNameValuePair("type_chambre", type_chambre));
			params.add(new BasicNameValuePair("nom_pension", nom_pension));

			JSONObject json = jsonParser.makeHttpRequest(url_reserv, "POST",
					params);
			Log.d("Create Response", json.toString());
			try {
				success = json.getInt(TAG_SUCCESS);

				if (success == 1) {
					Log.d("Created!", json.toString());
					Intent i = new Intent(getActivity(),
							FactureReservationChambreActivity.class);
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

				v8 = "1";
			} else if (success == 3) {

				l8 = "1";
			}
		}
	}

	class AddNewReservationRapidType3pension3 extends
			AsyncTask<String, String, String> {
		int success;

		protected String doInBackground(String... args) {
			String date_arrivee = MainActivity.dtreserv.getDateArrivee();
			String date_depart = MainActivity.dtreserv.getDateDepart();
			String mot_de_passe = MainChambreFragment.infCh.getMdp();
			String e_mail = MainChambreFragment.infCh.getEmailverif();
			String nbpension = MainChambreFragment.infCh
					.getNbreCh_pension3_type3();
			String type_chambre = MainChambreFragment.infCh.getType_chambre3();
			String nom_pension = MainChambreFragment.infCh.getPension3_type3();

			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("date_arrivee", date_arrivee));
			params.add(new BasicNameValuePair("date_depart", date_depart));
			params.add(new BasicNameValuePair("mot_de_passe", mot_de_passe));
			params.add(new BasicNameValuePair("e_mail", e_mail));
			params.add(new BasicNameValuePair("nbpension", nbpension));
			params.add(new BasicNameValuePair("type_chambre", type_chambre));
			params.add(new BasicNameValuePair("nom_pension", nom_pension));

			JSONObject json = jsonParser.makeHttpRequest(url_reserv, "POST",
					params);
			Log.d("Create Response", json.toString());
			try {
				success = json.getInt(TAG_SUCCESS);

				if (success == 1) {
					Log.d("Created!", json.toString());
					Intent i = new Intent(getActivity(),
							FactureReservationChambreActivity.class);
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

				v9 = "1";
			} else if (success == 3) {

				l9 = "1";
			}
		}
	}

}