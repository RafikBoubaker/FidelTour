package com.cbw.fideltour.fragment;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import com.cbw.fideltour.R;
import com.cbw.fideltour.adapter.AdapterCapitale;
import com.cbw.fideltour.entity.ArticleGuide;
import com.cbw.fideltour.entity.Hotel;
import com.cbw.fideltour.parsing.HttpHelper;
import com.cbw.fideltour.parsing.JSONParser;
import com.google.gson.Gson;
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

public class CapitaleFragment extends Fragment {
	TextView nom;
	TextView description;
	JSONParser jParser = new JSONParser();
	public Context context;
	private static String url_cafe = Hotel.adresse_ip + "ConsulterCapitale.php";
	ListAdapter adapter;
	ArticleGuide cafes[];
	ListView listViewItems;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_cafes_layout, null);

		listViewItems = (ListView) view.findViewById(R.id.list_cafe);
		new Loadho().execute();

		return view;

	}

	class Loadho extends AsyncTask<String, String, String> {

		protected String doInBackground(String... args) {
			HttpClient client = new DefaultHttpClient();
			HttpGet request = new HttpGet(url_cafe);

			try {
				HttpResponse res = client.execute(request);
				String response = HttpHelper.request(res);
				cafes = (new Gson()).fromJson(response, ArticleGuide[].class);

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
		AdapterCapitale adapter = new AdapterCapitale(this.getActivity(),
				R.layout.entry_layout_cafe, cafes);
		listViewItems.setAdapter(adapter);
	}
}
