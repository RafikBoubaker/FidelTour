package com.cbw.fideltour.activity;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import com.cbw.fideltour.R;
import com.cbw.fideltour.adapter.AdapterCommande;
import com.cbw.fideltour.entity.Categorie;
import com.cbw.fideltour.entity.Hotel;
import com.cbw.fideltour.entity.SousCategorie;
import com.cbw.fideltour.parsing.JSONParser;
import com.paypal.android.sdk.T;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.TextView;

public class MenuCommandeActivity extends FragmentActivity {
	static JSONParser jsonParser = new JSONParser();
	private static final String TAG_SUCCESS = "success";
	private static final String TAG_MESSAGE = "message";
	private static String url_cmd = Hotel.adresse_ip
			+ "EnregistrementCommande.php";
	private AdapterCommande listAdapter;
	private ExpandableListView myList;
	private ArrayList<Categorie> continentList = new ArrayList<Categorie>();
	public ArrayList<SousCategorie> sousCategList = new ArrayList<SousCategorie>();
	public static SousCategorie sct;
	List<String> listDataHeader;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		sct = new SousCategorie();

		super.onCreate(savedInstanceState);
		setContentView(R.layout.menu_commande_layout);
		myList = (ExpandableListView) findViewById(R.id.expandableList);
		new loadSomeData().execute();
		myList.setOnChildClickListener(new OnChildClickListener() {

			@Override
			public boolean onChildClick(ExpandableListView parent, View v,
					int groupPosition, int childPosition, long id) {

				Categorie categorie = continentList.get(groupPosition);
				SousCategorie soucategorie = categorie.getSouscat().get(
						childPosition);
				String nomsouscat = soucategorie.getNom_sous_categ_cmd();
				CommandeActivity.cmd.setNomsouscat(nomsouscat);
				DialogSousCat m = new DialogSousCat().newInstance();

				m.show(getFragmentManager(), "Alert_Dialog");

				return false;
			}
		});
	}

	class loadSomeData extends AsyncTask<String, String, String> {

		protected String doInBackground(String... args) {

			HttpClient client = new DefaultHttpClient();
			HttpUriRequest request = new HttpGet(Hotel.adresse_ip
					+ "ConsulterMenu.php");
			try {
				HttpResponse response = client.execute(request);
				BufferedReader reader = new BufferedReader(
						new InputStreamReader(
								response.getEntity().getContent(), "UTF-8"));
				String jsonString = reader.readLine();

				JSONObject json = new JSONObject(jsonString);
				json = new JSONObject(json.toString());
				JSONObject obj = new JSONObject("" + json.get("cat"));
				JSONArray jsonRpCateg = new JSONArray("" + obj.get("p1"));
				JSONArray jsonRpSousCat = new JSONArray("" + obj.get("p2"));

				JSONArray jArray1 = new JSONArray(""
						+ jsonRpCateg.getJSONArray(0));
				JSONArray jArray2 = new JSONArray(""
						+ jsonRpCateg.getJSONArray(1));
				int j = 0;
				final int taille1 = jArray1.length();
				for (int i = 0; i < taille1; i++) {
					String nom_categorie = jArray1.getString(i);
					String logo_categ = jArray2.getString(i);
					JSONArray jArray11 = new JSONArray(""
							+ jsonRpSousCat.getJSONArray(j));
					JSONArray jArray22 = new JSONArray(""
							+ jsonRpSousCat.getJSONArray(j + 1));
					sousCategList = new ArrayList<SousCategorie>();

					for (int k = 0; k < jArray11.length(); k++) {

						String nom_sous_categ_cmd = jArray11.getString(k);
						String prix_unitaire = jArray22.getString(k);

						SousCategorie soucat = new SousCategorie(
								nom_sous_categ_cmd, prix_unitaire);
						sousCategList.add(soucat);

					}

					Categorie cat = new Categorie(nom_categorie, logo_categ,
							sousCategList);
					continentList.add(cat);

					j = j + 2;

				}
			} catch (Exception ex) {

				ex.printStackTrace();
			}
			return null;
		}

		@Override
		protected void onPostExecute(String result) {
			load();
			super.onPostExecute(result);
		}
	}

	public void load() {
		listAdapter = new AdapterCommande(MenuCommandeActivity.this,
				continentList);
		myList.setAdapter(listAdapter);
		int count = listAdapter.getGroupCount();
		for (int i = 0; i < count; i++) {
			myList.expandGroup(i);
		}

	}

	public static class DialogSousCat extends DialogFragment {

		Spinner spinnb;
		Button bt;
		String nbch;

		public DialogSousCat newInstance() {
			DialogSousCat frag = new DialogSousCat();
			return frag;
		}

		@Override
		public Dialog onCreateDialog(Bundle savedInstanceState) {
			AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

			final View promptsView = getActivity().getLayoutInflater().inflate(
					R.layout.dialog_menucommande_layout, null);

			builder.setView(promptsView);
			TextView nom=(TextView) promptsView.findViewById(R.id.nomsouscategdialog);
			nom.setText(CommandeActivity.cmd.getNomsouscat());
			spinnb = (Spinner) promptsView.findViewById(R.id.spinnernbsouscat);
			bt = (Button) promptsView.findViewById(R.id.buttonSoucat);
			spinnb.setOnItemSelectedListener(new OnItemSelectedListener() {

				@Override
				public void onItemSelected(AdapterView<?> parent, View view,
						int pos, long id) {

					nbch = parent.getItemAtPosition(pos).toString();

				}

				@Override
				public void onNothingSelected(AdapterView<?> arg0) {

				}
			});

			final Dialog dialog = builder.create();
			bt.setOnClickListener(new View.OnClickListener() {

				public void onClick(View view) {
					
					if (nbch.toString()!=null) {

						new EnvoieCommande().execute();
						dialog.dismiss();
					}
				}

			});

			return dialog;

		}

		class EnvoieCommande extends AsyncTask<String, String, String> {

			protected String doInBackground(String... args) {
				String souscateg = CommandeActivity.cmd.getNomsouscat();
				String nbre = nbch.toString();
				String heure = CommandeActivity.cmd.getHeure_cmd();

				String email = ConnexionActivity.client.getE_mail();

				Log.d("email", email);
				Log.d("heure", heure);
				Log.d("souscateg", souscateg);
				Log.d("nbre", nbre);
				List<NameValuePair> params = new ArrayList<NameValuePair>();
				params.add(new BasicNameValuePair("nom_sous_categ_cmd",
						souscateg));
				params.add(new BasicNameValuePair("nb_sous_categorie", nbre));
				params.add(new BasicNameValuePair("heure_cmd", heure));

				params.add(new BasicNameValuePair("e_mail", email));
				JSONObject json = jsonParser.makeHttpRequest(url_cmd, "POST",
						params);

				try {
					int success = json.getInt(TAG_SUCCESS);

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
		}

	}

}
