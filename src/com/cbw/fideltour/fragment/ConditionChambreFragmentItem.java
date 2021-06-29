package com.cbw.fideltour.fragment;

import java.util.ArrayList;
import java.util.List;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import com.actionbarsherlock.app.SherlockFragment;
import com.cbw.fideltour.R;
import com.cbw.fideltour.adapter.AdapterCondition;
import com.cbw.fideltour.entity.Hotel;
import com.cbw.fideltour.parsing.HttpHelper;
import com.cbw.fideltour.parsing.JSONParser;
import com.google.gson.Gson;

public class ConditionChambreFragmentItem extends SherlockFragment {

	JSONParser jsonParser = new JSONParser();
	JSONArray jObj;
	JSONObject json;
	JSONParser jParser = new JSONParser();
	public Context context;
	JSONArray list = null;
	ArrayList<String> list1;
	private static String url = Hotel.adresse_ip
			+"ConsulterConditionReservationChambre.php";
	Hotel hotel[];
	TextView conditionResCh;
	ListView listViewItems;
	List<Hotel> hotels;

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		final View V = inflater.inflate(R.layout.fragment_condition_layout,
				container, false);
		listViewItems = (ListView) V.findViewById(R.id.listcondition);
		new listecondition().execute();

		return V;
	}

	class listecondition extends AsyncTask<String, String, String> {

		protected String doInBackground(String... args) {
			HttpClient client = new DefaultHttpClient();
			HttpGet request = new HttpGet(url);

			try {

				HttpResponse res = client.execute(request);
				String response = HttpHelper.request(res);
				hotel = (new Gson()).fromJson(response, Hotel[].class);
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
		AdapterCondition adapter = new AdapterCondition(this.getActivity(),
				R.layout.entry_layout_condition, hotel);
		listViewItems.setAdapter(adapter);

	}

}
