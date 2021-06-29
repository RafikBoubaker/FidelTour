package com.cbw.fideltour.fragment;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import com.actionbarsherlock.app.SherlockFragment;

import com.cbw.fideltour.R;
import com.cbw.fideltour.adapter.AdapterEquipement;
import com.cbw.fideltour.entity.Equipement;
import com.cbw.fideltour.entity.Hotel;
import com.cbw.fideltour.parsing.HttpHelper;
import com.cbw.fideltour.parsing.JSONParser;
import com.google.gson.Gson;

public class EquipementFragmentItem extends SherlockFragment {
	private static final String TAG_SUCCESS = "success";
	JSONParser jsonParser = new JSONParser();
	JSONArray jObj;
	JSONObject json;
	JSONParser jParser = new JSONParser();
	public Context context;
	JSONArray list = null;
	ArrayList<String> list1;
	private static String url = Hotel.adresse_ip+"ConsulterEquipementSalle.php";
	Equipement equipements[];
	TextView nom_equipement;
	TextView descirption_equipement;
	ImageView imageequipement;
	ListView listViewItems;
	List<Equipement> Equipement;

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		final View V = inflater.inflate(R.layout.fragment_equipement_layout, container,
				false);
		Log.d("test", "tesssst1");

		listViewItems = (ListView) V.findViewById(R.id.list_equipement);
		new listeEquipement().execute();
		Log.d("Tag", "apres");
		return V;
	}

	class listeEquipement extends AsyncTask<String, String, String> {

		@SuppressLint("NewApi")
		protected String doInBackground(String... args) {
			Log.d(TAG_SUCCESS, "msg1");

			HttpClient client = new DefaultHttpClient();
			HttpGet request = new HttpGet(url);

			try {
				Log.d("Tag", "test 1");
				HttpResponse res = client.execute(request);
				String response = HttpHelper.request(res);
				equipements = (new Gson()).fromJson(response, Equipement[].class);
			} catch (Exception ex) {
				Log.d("Tag", "test 2");
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
		AdapterEquipement adapter = new AdapterEquipement(this.getActivity(),
				R.layout.entry_layout_equipement, equipements);
		listViewItems.setAdapter(adapter);

	}

}
