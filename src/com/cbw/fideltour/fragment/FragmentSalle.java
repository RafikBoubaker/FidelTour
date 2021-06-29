package com.cbw.fideltour.fragment;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import com.cbw.fideltour.R;
import com.cbw.fideltour.adapter.AdapterSalle;
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

public class FragmentSalle extends Fragment {
	TextView nom;
	TextView description;
	JSONParser jParser = new JSONParser();
	public Context context;
	private static String url_salle = Hotel.adresse_ip + "ConsulterSalle.php";
	ListAdapter adapter;

	Article articles[];
	ListView listViewItems;

	public static Fragment newInstance(Context context) {
		FragmentSalle f = new FragmentSalle();

		return f;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		final ViewGroup V = (ViewGroup) inflater.inflate(
				R.layout.fragment_salle_layout, null);

		listViewItems = (ListView) V.findViewById(R.id.list_salle);

		new Loadho().execute();

		return V;

	}

	class Loadho extends AsyncTask<String, String, String> {

		protected String doInBackground(String... args) {

			HttpClient client = new DefaultHttpClient();
			HttpGet request = new HttpGet(url_salle);

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
		AdapterSalle adapter = new AdapterSalle(this.getActivity(),
				R.layout.entry_layout_salle, articles);
		listViewItems.setAdapter(adapter);
	}
}
