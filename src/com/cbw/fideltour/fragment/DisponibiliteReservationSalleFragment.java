package com.cbw.fideltour.fragment;

import java.util.ArrayList;
import java.util.List;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;
import com.actionbarsherlock.app.SherlockFragment;
import com.cbw.fideltour.R;
import com.cbw.fideltour.activity.MainActivity;
import com.cbw.fideltour.entity.Hotel;
import com.cbw.fideltour.parsing.JSONParser;
import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class DisponibiliteReservationSalleFragment extends SherlockFragment {

	JSONParser jsonParser = new JSONParser();
	JSONArray jObj;
	JSONObject json;
	JSONParser jParser = new JSONParser();
	public Context context;
	private static String url = Hotel.adresse_ip+"DisponibiliteSalle.php";
	TextView text1;
	TextView text2;
	TextView textdispo;
	String rep1 = "";
	String rep2 = "";
	String af1;

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View root = inflater.inflate(R.layout.disponibilite_salle_layout,
				container, false);

		textdispo = (TextView) root.findViewById(R.id.textViewDisposl);
		text1 = (TextView) root.findViewById(R.id.dispo_salle);
		text2 = (TextView) root.findViewById(R.id.dispo_sallee);

		new VerifDispo().execute();
		return root;
	}

	class VerifDispo extends AsyncTask<String, String, String> {

		@SuppressLint("NewApi")
		protected String doInBackground(String... args) {

			String jour_reservation_salle = MainActivity.dtreserv
					.getJour_reservation_salle();

			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("jour_reservation_salle",
					jour_reservation_salle));

			try {
				json = jsonParser.makeHttpRequest(url, "POST", params);

				JSONObject jsonResponse = new JSONObject(json.toString());
				jsonResponse = new JSONObject(json.toString());

				af1 = "" + jsonResponse.get("af");
				JSONArray jsonRp = new JSONArray(af1);

				String str1 = "" + jsonRp.getJSONArray(0);
				JSONArray jArray1 = new JSONArray(str1);

				String str2 = "" + jsonRp.getJSONArray(1);
				JSONArray jArray2 = new JSONArray(str2);

				for (int i = 0; i < jArray1.length(); i++) {
					rep1 = rep1.concat("\n" + jArray1.getString(i));

				}

				for (int i = 0; i < jArray2.length(); i++) {
					rep2 = rep2.concat("\n" + jArray2.getString(i));

				}

			} catch (Exception e1) {

				e1.printStackTrace();
			}
			return null;
		}

		@Override
		protected void onPostExecute(String result) {

			text1.setText(rep1);
			text2.setText(rep2);
			super.onPostExecute(result);
			rep1 = "";
			rep2 = "";

		}

	}

}