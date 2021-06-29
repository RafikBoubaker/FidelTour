package com.cbw.fideltour.fragment;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import com.cbw.fideltour.R;
import com.cbw.fideltour.adapter.AdapterPatrimoines;
import com.cbw.fideltour.entity.ArticleGuide;
import com.cbw.fideltour.entity.Hotel;
import com.cbw.fideltour.parsing.HttpHelper;
import com.cbw.fideltour.parsing.JSONParser;
import com.google.gson.Gson;
import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

@SuppressLint("NewApi")
public class PatrimoinesFragment extends Fragment {

	TextView nom;
	TextView description;

	JSONParser jParser = new JSONParser();

	public Context context;
	private static String url_patrimoine = Hotel.adresse_ip
			+ "ConsulterPatrimoines.php";
	ListAdapter adapter;
	ArticleGuide patrimoines[];
	ListView listViewItems;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater
				.inflate(R.layout.fragment_patrimoines_layout, null);
		listViewItems = (ListView) view.findViewById(R.id.list_patrimoine);

		new Loadho().execute();

		return view;

	}

	class Loadho extends AsyncTask<String, String, String> {

		protected String doInBackground(String... args) {
			HttpClient client = new DefaultHttpClient();
			HttpGet request = new HttpGet(url_patrimoine);

			try {
				HttpResponse res = client.execute(request);
				String response = HttpHelper.request(res);
				patrimoines = (new Gson()).fromJson(response,
						ArticleGuide[].class);

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
		AdapterPatrimoines adapter = new AdapterPatrimoines(this.getActivity(),
				R.layout.entry_layout_patrimoine, patrimoines);
		listViewItems.setAdapter(adapter);
	}
}
