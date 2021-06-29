package com.cbw.fideltour.fragment;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;
import com.cbw.fideltour.R;
import com.cbw.fideltour.adapter.AdapterChambre;
import com.cbw.fideltour.entity.Article;
import com.cbw.fideltour.entity.Hotel;
import com.cbw.fideltour.parsing.HttpHelper;
import com.cbw.fideltour.parsing.JSONParser;
import com.google.gson.Gson;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class FragmentChambre extends Fragment {
	TextView nom;
	TextView description;
	JSONParser jParser = new JSONParser();
	JSONObject hot;
	public Context context;
	private static String url_chambre = Hotel.adresse_ip
			+ "ConsulterChambre.php";
	ListAdapter adapter;

	Article articles[];

	ListView listViewItems;

	public static Fragment newInstance(Context context) {
		FragmentChambre f = new FragmentChambre();

		return f;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		final ViewGroup V = (ViewGroup) inflater.inflate(
				R.layout.fragment_chambre_layout, null);

		listViewItems = (ListView) V.findViewById(R.id.list_chambre);

		new Loadho().execute();

		return V;

	}

	class Loadho extends AsyncTask<String, String, String> {

		protected String doInBackground(String... args) {

			HttpClient client = new DefaultHttpClient();
			HttpGet request = new HttpGet(url_chambre);

			try {
				HttpResponse res = client.execute(request);
				String response = HttpHelper.request(res);
				articles = (new Gson()).fromJson(response, Article[].class);
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
		AdapterChambre adapter = new AdapterChambre(this.getActivity(),
				R.layout.entry_layout_chambre, articles);
		listViewItems.setAdapter(adapter);
	}
}
