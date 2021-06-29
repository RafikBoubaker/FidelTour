package com.cbw.fideltour.activity;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;
import org.json.JSONArray;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import com.cbw.fideltour.R;
import com.cbw.fideltour.entity.Hotel;
import com.cbw.fideltour.parsing.JSONParser;

public class ConsulterFacturesActivity extends Activity {
	private static final String TAG_SUCCESS = "success";
	JSONParser jsonParser = new JSONParser();
	private static String url = Hotel.adresse_ip
			+ "AffichageListeFactureInterne.php";

	private static String url_devise = "http://www.webservicex.com/CurrencyConvertor.asmx/ConversionRate";
	TextView numcmd;
	TextView nbrcmd;
	TextView nom;
	TextView prenom;
	TextView nmsous;
	TextView prixsous;
	TextView prixtotcmd;
	TextView numoffre;
	TextView dateoffre;

	TextView nomoffre;
	TextView prixoffre;
	TextView prixtotoffre;
	TextView numvideo;
	TextView nomvideo;
	TextView prixvideo;

	TextView datevideo;
	TextView prixtotvideo;
	TextView idfact;
	TextView texttotal;
	TextView textnetpayer;
	TextView totalttc;
	TextView totalttceuro;
	TextView totalttcdollar;
	TextView texttotaleuro;
	TextView textnetpayereuro;
	TextView texttotaldollar;
	TextView textnetpayerdollar;
	String t1c = "";
	String t2c = "";
	String t3c = "";
	String t4c = "";
	String t5c = "";

	String t1o = "";
	String t2o = "";
	String t3o = "";
	String t4o = "";
	String t5o = "";

	String t1v = "";
	String t2v = "";
	String t3v = "";
	String t4v = "";
	String t5v = "";

	String tnf = "";
	String tnet = "";

	String tttc = "";
	String tht = "";

	String tnom = "";
	String tprenom = "";
	JSONObject json;
	JSONObject jsons;
	JSONObject jsonc;
	JSONObject jsono;
	JSONObject jsontot;
	JSONObject jsonResponse;
	JSONObject jsonResponseS;
	JSONObject jsonResponseC;
	JSONObject jsonResponseO;
	JSONObject jsonResponsetot;
	String fact2;
	String fact2S;
	String fact2C;
	String fact2O;
	String fact;
	private String res3;
	private String res4;
	private String res5;
	private String res0;
	private String res1;
	private String res2;

	public Handler mhandler;

	@SuppressLint("HandlerLeak")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.consulter_factures);
		Log.d("test", "test1");

		nom = (TextView) findViewById(R.id.textnomclientfact);
		prenom = (TextView) findViewById(R.id.textprenomclientfact);
		numcmd = (TextView) findViewById(R.id.textNUmcomd);
		nmsous = (TextView) findViewById(R.id.textnomsouscateg);
		nbrcmd = (TextView) findViewById(R.id.textnbcateg);
		prixsous = (TextView) findViewById(R.id.textPrixunit);
		prixtotcmd = (TextView) findViewById(R.id.textTotalcmd);

		numoffre = (TextView) findViewById(R.id.textnumOffre);
		nomoffre = (TextView) findViewById(R.id.textnomOffre);

		dateoffre = (TextView) findViewById(R.id.textDateOffre);
		prixoffre = (TextView) findViewById(R.id.textprixoffre);
		prixtotoffre = (TextView) findViewById(R.id.textTotalfactOffre);
		numvideo = (TextView) findViewById(R.id.textnumvidefact);
		nomvideo = (TextView) findViewById(R.id.textnomvidefact);
		datevideo = (TextView) findViewById(R.id.textdatevidefact);

		prixvideo = (TextView) findViewById(R.id.textprividfat);
		prixtotvideo = (TextView) findViewById(R.id.texttotalfactvide);

		idfact = (TextView) findViewById(R.id.textViewnumFacture);

		texttotal = (TextView) findViewById(R.id.textTotalligne);
		totalttc = (TextView) findViewById(R.id.texttotalttcfactligne);

		textnetpayer = (TextView) findViewById(R.id.textNetligne);
		texttotaleuro = (TextView) findViewById(R.id.textTotalligneeuro);
		totalttceuro = (TextView) findViewById(R.id.texttotalttcfactligneeuro);
		textnetpayereuro = (TextView) findViewById(R.id.textNetligneeuro);

		texttotaldollar = (TextView) findViewById(R.id.textTotallignedollar);
		totalttcdollar = (TextView) findViewById(R.id.texttotalttcfactlignedollar);
		textnetpayerdollar = (TextView) findViewById(R.id.textNetlignedollar);
	
		new AfficherFactures().execute();
		mhandler = new Handler() {
			public void handleMessage(Message msg) {

				switch (msg.what) {
				case 1:
					texttotaleuro.setText(res3);
					totalttceuro.setText(res4);
					textnetpayereuro.setText(res5);

					break;
				case 2:
					texttotaldollar.setText(res0);
					totalttcdollar.setText(res1);
					textnetpayerdollar.setText(res2);

					break;
				}
			}

		};

	}

	class AfficherFactures extends AsyncTask<String, String, String> {

		protected String doInBackground(String... args) {
			String e_mail = ConnexionActivity.client.getE_mail();
			Log.d(TAG_SUCCESS, "msg2");
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("e_mail", e_mail));
			Log.d(TAG_SUCCESS, "msg3");

			try {
				json = jsonParser.makeHttpRequest(url, "POST", params);

				JSONObject jsonResponse = new JSONObject(json.toString());
				jsonResponse = new JSONObject(json.toString());

				fact2 = "" + jsonResponse.get("af");
				JSONArray jsonRp = new JSONArray(fact2);

				String str0 = "" + jsonRp.getJSONArray(0);
				JSONArray jArray0 = new JSONArray(str0);

				String str1 = "" + jsonRp.getJSONArray(1);
				JSONArray jArray1 = new JSONArray(str1);

				String str2 = "" + jsonRp.getJSONArray(2);
				JSONArray jArray2 = new JSONArray(str2);

				String str3 = "" + jsonRp.getJSONArray(3);
				JSONArray jArray3 = new JSONArray(str3);

				String str5 = "" + jsonRp.getJSONArray(5);
				JSONArray jArray5 = new JSONArray(str5);

				String str6 = "" + jsonRp.getJSONArray(6);
				JSONArray jArray6 = new JSONArray(str6);

				String str7 = "" + jsonRp.getJSONArray(7);
				JSONArray jArray7 = new JSONArray(str7);

				String str8 = "" + jsonRp.getJSONArray(8);
				JSONArray jArray8 = new JSONArray(str8);

				String str10 = "" + jsonRp.getJSONArray(10);
				JSONArray jArray10 = new JSONArray(str10);

				String str11 = "" + jsonRp.getJSONArray(11);
				JSONArray jArray11 = new JSONArray(str11);

				String str12 = "" + jsonRp.getJSONArray(12);
				JSONArray jArray12 = new JSONArray(str12);

				String str13 = "" + jsonRp.getJSONArray(13);
				JSONArray jArray13 = new JSONArray(str13);

				String str19 = "" + jsonRp.getJSONArray(19);
				JSONArray jArray19 = new JSONArray(str19);
				for (int i = 0; i < jArray0.length(); i++) {
					t1c = t1c.concat("\n" + jArray0.getString(i));

				}
				for (int i = 0; i < jArray1.length(); i++) {
					t2c = t2c.concat("\n" + jArray1.getString(i));

				}
				for (int i = 0; i < jArray2.length(); i++) {
					t3c = t3c.concat("\n" + jArray2.getString(i));

				}
				for (int i = 0; i < jArray3.length(); i++) {
					t4c = t4c.concat("\n" + jArray3.getString(i));

				}
				t5c = jsonRp.getString(4);
				for (int i = 0; i < jArray5.length(); i++) {
					t1v = t1v.concat("\n" + jArray5.getString(i));

				}
				for (int i = 0; i < jArray6.length(); i++) {
					t2v = t2v.concat("\n" + jArray6.getString(i));

				}
				for (int i = 0; i < jArray7.length(); i++) {
					t3v = t3v.concat("\n" + jArray7.getString(i));

				}
				for (int i = 0; i < jArray8.length(); i++) {
					t4v = t4v.concat("\n" + jArray8.getString(i));

				}
				t5v = jsonRp.getString(9);
				for (int i = 0; i < jArray10.length(); i++) {
					t1o = t1o.concat("\n" + jArray10.getString(i));

				}
				for (int i = 0; i < jArray11.length(); i++) {
					t2o = t2o.concat("\n" + jArray11.getString(i));

				}
				for (int i = 0; i < jArray12.length(); i++) {
					t3o = t3o.concat("\n" + jArray12.getString(i));

				}
				for (int i = 0; i < jArray13.length(); i++) {
					t4o = t4o.concat("\n" + jArray13.getString(i));

				}
				t5o = jsonRp.getString(14);
				tht = jsonRp.getString(15);

				tnf = jsonRp.getString(16);

				tnom = jsonRp.getString(17);
				tprenom = jsonRp.getString(18);

				tttc = jArray19.getString(0);
				tnet = jArray19.getString(0);

			} catch (Exception e1) {

				e1.printStackTrace();
			}
			return null;
		}

		@Override
		protected void onPostExecute(String result) {
			numcmd.setText(t1c);
			nbrcmd.setText(t2c);
			nmsous.setText(t3c);
			prixsous.setText(t4c);
			prixtotcmd.setText(t5c);
			numoffre.setText(t1o);
			dateoffre.setText(t2o);
			nomoffre.setText(t3o);
			prixoffre.setText(t4o);
			prixtotoffre.setText(t5o);
			numvideo.setText(t1v);
			nomvideo.setText(t3v);
			prixvideo.setText(t4v);
			datevideo.setText(t2v);
			prixtotvideo.setText(t5v);
			idfact.setText(tnf);
			
			texttotal.setText(tht);
			textnetpayer.setText(tnet);
			totalttc.setText(tttc);

			nom.setText(tnom);
			prenom.setText(tprenom);
			super.onPostExecute(result);
			t1c = "";
			t2c = "";
			t3c = "";
			t4c = "";
			t5c = "";
			t1o = "";
			t2o = "";
			t3o = "";
			t4o = "";
			t5o = "";
			t1v = "";
			t2v = "";
			t3v = "";
			t4v = "";
			t5v = "";
			tnf = "";
			tnet = "";
			tht = "";
			tttc = "";
			tnom = "";
			tprenom = "";
		}

	}
	private String convert(String devise1, String devise2, double montant) {
		try {

			HttpParams httpparameters = new BasicHttpParams();
			HttpConnectionParams.setConnectionTimeout(httpparameters, 4000);
			HttpConnectionParams.setSoTimeout(httpparameters, 5000);

			HttpClient httpclient = new DefaultHttpClient(httpparameters);

			HttpPost httppost = new HttpPost(url_devise);
			httppost.setHeader("Content-Type",
					"application/x-www-form-urlencoded");

			List<NameValuePair> params = new ArrayList<NameValuePair>(3);
			params.add(new BasicNameValuePair("FromCurrency", devise1));
			params.add(new BasicNameValuePair("ToCurrency", devise2));

			httppost.setEntity(new UrlEncodedFormEntity(params));

			HttpResponse response = httpclient.execute(httppost);
			InputStream is = response.getEntity().getContent();

			InputStreamReader reader = new InputStreamReader(is, HTTP.UTF_8);

			char[] buf = new char[4096];
			int count;
			StringBuilder sb = new StringBuilder();
			while ((count = reader.read(buf, 0, buf.length)) != -1)

				sb.append(buf, 0, count);
			is.close();

			String res = sb.toString();
			return String.valueOf(Math.round(Double.parseDouble(sb.toString()
					.substring(res.indexOf("/\">") + 3, res.lastIndexOf("<")))
					* montant) + " " + devise2);

		} catch (Exception e) {
			return "No Internet Connection";

		}

	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.action_euro:

			new Thread(new Runnable() {

				@Override
				public void run() {
					double i3 = Double.parseDouble(texttotal.getText()
							.toString());
					double i4 = Double.parseDouble(textnetpayer.getText()
							.toString());

					res3 = convert("TND", "EUR", i3);
					res4 = convert("TND", "EUR", i4);
					res5 = convert("TND", "EUR", i4);
					Log.d("res3", res3);
					Log.d("res4", res4);
					mhandler.sendEmptyMessage(1);

				};
			}).start();
			break;

		case R.id.action_dollar:

			new Thread(new Runnable() {

				@Override
				public void run() {

					double i3 = Double.parseDouble(texttotal.getText()
							.toString());
					double i4 = Double.parseDouble(textnetpayer.getText()
							.toString());

					res0 = convert("TND", "USD", i3);
					res1 = convert("TND", "USD", i4);
					res2 = convert("TND", "USD", i4);
					mhandler.sendEmptyMessage(2);

				};
			}).start();
			break;

		}

		return super.onOptionsItemSelected(item);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main3, menu);
		return true;
	}
}