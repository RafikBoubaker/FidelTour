package com.cbw.fideltour.activity;

import java.util.ArrayList;
import java.util.List;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;
import com.cbw.fideltour.R;
import com.cbw.fideltour.entity.Hotel;
import com.cbw.fideltour.parsing.JSONParser;
import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class HistoriqueReservationActivity extends Activity {
	ImageView image;
	TextView text1;
	TextView text2;
	TextView text3;
	TextView text4;
	String af;
	JSONParser jsonParser = new JSONParser();
	JSONArray jObj;
	JSONObject json;
	JSONParser jParser = new JSONParser();
	public Context context;
	JSONArray list = null;
	ArrayList<String> list1;
	private static String url = Hotel.adresse_ip + "HistoriqueReservation.php";
	String rep1 = "";
	String rep2 = "";
	String rep3 = "";
	String rep4 = "";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.historique_reservation_layout);

		text1 = (TextView) findViewById(R.id.textdate);
		text2 = (TextView) findViewById(R.id.texttype);
		text3 = (TextView) findViewById(R.id.textnbtype);
		text4 = (TextView) findViewById(R.id.textnbjour);
		new AfficherHistorique().execute();

	}

	class AfficherHistorique extends AsyncTask<String, String, String> {

		protected String doInBackground(String... args) {
			String e_mail = ConnexionActivity.client.getE_mail();

			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("e_mail", e_mail));

			try {
				json = jsonParser.makeHttpRequest(url, "POST", params);

				JSONObject jsonResponse = new JSONObject(json.toString());

				af = "" + jsonResponse.get("af");
				JSONArray jsonRp = new JSONArray(af);

				String str1 = "" + jsonRp.getJSONArray(0);
				JSONArray jArray1 = new JSONArray(str1);

				String str2 = "" + jsonRp.getJSONArray(1);
				JSONArray jArray2 = new JSONArray(str2);

				String str3 = "" + jsonRp.getJSONArray(2);
				JSONArray jArray3 = new JSONArray(str3);

				String str4 = "" + jsonRp.getJSONArray(3);
				JSONArray jArray4 = new JSONArray(str4);

				for (int i = 0; i < jArray1.length(); i++) {
					rep1 = rep1.concat("\n" + jArray1.getString(i));

				}

				for (int i = 0; i < jArray2.length(); i++) {
					rep2 = rep2.concat("\n" + jArray2.getString(i));

				}

				for (int i = 0; i < jArray3.length(); i++) {
					rep3 = rep3.concat("\n" + jArray3.getString(i));
				}
				for (int i = 0; i < jArray4.length(); i++) {
					rep4 = rep4.concat("\n" + jArray4.getString(i));
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
			text4.setText(rep4);
			rep1 = "";
			rep2 = "";
			rep3 = "";
			rep4 = "";

		}
	}

}