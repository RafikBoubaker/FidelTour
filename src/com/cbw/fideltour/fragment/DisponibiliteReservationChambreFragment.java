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
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class DisponibiliteReservationChambreFragment extends SherlockFragment {
	
	JSONParser jsonParser = new JSONParser();
	JSONArray jObj;
	JSONObject json;
	JSONParser jParser = new JSONParser();
	public Context context;
	JSONArray list = null;
	ArrayList<String> list1;
	private static String url = Hotel.adresse_ip + "DisponibiliteChambre.php";
	String af1;
	TextView text2;
	TextView text3;
	TextView text1;
	TextView textdispo;
	String rep1 = "";
	String rep2 = "";
	String rep3 = "";

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View root = inflater.inflate(R.layout.disponibilite_chambre_layout,
				container, false);

		textdispo = (TextView) root.findViewById(R.id.textViewDispoch);
		text1 = (TextView) root.findViewById(R.id.dispo_chambre1);
		text2 = (TextView) root.findViewById(R.id.dispo_chambre2);
		text3 = (TextView) root.findViewById(R.id.dispo_chambre3);

		new VerifDispo().execute();

		return root;
	}

	class VerifDispo extends AsyncTask<String, String, String> {

		protected String doInBackground(String... args) {
		
			String date_arrivee = MainActivity.dtreserv.getDateArrivee();
			String date_depart = MainActivity.dtreserv.getDateDepart();

			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("date_arrivee", date_arrivee));
			params.add(new BasicNameValuePair("date_depart", date_depart));

			try {
				json = jsonParser.makeHttpRequest(url, "POST", params);

				JSONObject jsonResponse = new JSONObject(json.toString());

				af1 = "" + jsonResponse.get("af");
				JSONArray jsonRp = new JSONArray(af1);
				
				String str1 = "" + jsonRp.getJSONArray(0);
				JSONArray jArray1 = new JSONArray(str1);
				
				String str2 = "" + jsonRp.getJSONArray(1);
				JSONArray jArray2 = new JSONArray(str2);

				String str3 = "" + jsonRp.getJSONArray(2);
				JSONArray jArray3 = new JSONArray(str3);

				for (int i = 0; i < jArray1.length(); i++) {
					rep1 = rep1.concat("\n" + jArray1.getString(i));

				}

				for (int i = 0; i < jArray2.length(); i++) {
					rep2 = rep2.concat("\n" + jArray2.getString(i));

				}
				for (int i = 0; i < jArray3.length(); i++) {
					rep3 = rep3.concat("\n" + jArray3.getString(i));
				}

			} catch (Exception e1) {

				e1.printStackTrace();
			}
			return null;
		}

		@Override
		protected void onPostExecute(String result) {

			super.onPostExecute(result);
			text1.setText(rep1);
			text2.setText(rep2);
			text3.setText(rep3);
			rep1 = "";
			rep2 = "";
			rep3 = "";
		}
	}

}