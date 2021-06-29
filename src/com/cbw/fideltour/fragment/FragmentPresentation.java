package com.cbw.fideltour.fragment;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import com.cbw.fideltour.R;
import com.cbw.fideltour.adapter.AdapterPresentation;
import com.cbw.fideltour.entity.Hotel;
import com.cbw.fideltour.parsing.HttpHelper;
import com.cbw.fideltour.parsing.JSONParser;
import com.google.gson.Gson;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class FragmentPresentation extends Fragment {
	TextView nom;
	TextView description;
	JSONParser jParser = new JSONParser();
	JSONObject hot;
	public Context context;
	private static String url_hotel = Hotel.adresse_ip+"ConsulterHotel.php";
	ListAdapter adapter;
	private static final String TAG_SUCCESS = "success";
	Hotel hotels[];
	ListView listViewItems;

	public static Fragment newInstance(Context context) {
		FragmentPresentation f = new FragmentPresentation();
		Log.d(TAG_SUCCESS, "msg1");
		return f;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		final ViewGroup V = (ViewGroup) inflater.inflate(
				R.layout.fragment_presentation_layout, null);
		Log.d(TAG_SUCCESS, "msg2");

		listViewItems = (ListView) V.findViewById(R.id.list_hotel);

		new Loadho().execute();
		Log.d("Tag", "apres");
		return V;

	}

	class Loadho extends AsyncTask<String, String, String> {

		protected String doInBackground(String... args) {
			Log.d(TAG_SUCCESS, "msg6");

			HttpClient client = new DefaultHttpClient();
			HttpGet request = new HttpGet(url_hotel);

			try {
				Log.d("Tag", "test 1");
				if (client != null) {
					HttpResponse res = client.execute(request);
					String response = HttpHelper.request(res);
					hotels = (new Gson()).fromJson(response, Hotel[].class);
				}

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
		if (hotels == null) {
			hotels = new Hotel[1];
			hotels[0] = new Hotel();
		}

		AdapterPresentation adapter = new AdapterPresentation(
				this.getActivity(), R.layout.entry_layout_hotel, hotels);
		listViewItems.setAdapter(adapter);
	}
}
