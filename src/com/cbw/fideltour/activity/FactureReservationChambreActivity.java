package com.cbw.fideltour.activity;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
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
import com.cbw.fideltour.R;
import com.cbw.fideltour.adapter.ConnectionDetector;
import com.cbw.fideltour.entity.Hotel;
import com.cbw.fideltour.fragment.MainChambreFragment;
import com.cbw.fideltour.parsing.JSONParser;
import com.lowagie.text.Document;
import com.lowagie.text.Image;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;

public class FactureReservationChambreActivity extends FragmentActivity {
	JSONParser jsonParser = new JSONParser();

	private static String url_devise = "http://www.webservicex.com/CurrencyConvertor.asmx/ConversionRate";
	private static String url = Hotel.adresse_ip
			+ "AffichageFactureChambre.php";
	private TextView b;
	TextView texttype;
	TextView textnbre;
	TextView textprixch;
	TextView textprixp;
	TextView texttotal;
	TextView textnetpayer;
	TextView texttva;
	TextView datereser;
	TextView textcin;
	TextView totalttc;
	TextView totalttceuro;
	TextView totalttcdollar;
	TextView texttotaleuro;
	TextView textnetpayereuro;
	TextView texttotaldollar;
	TextView textnetpayerdollar;
	ImageButton paypal;
	String t6 = "";
	String t7 = "";
	String t8 = "";
	String t9 = "";
	String t10 = "";
	String t11 = "";
	String t12 = "";
	String t13 = "";
	String t2 = "";
String val1;
String val2;
	private String res3;
	private String res4;
	private String res5;
	private String res0;
	private String res1;
	private String res2;
	JSONObject json;
	JSONObject jsonResponse;
	String fact2;
	Boolean isInternetPresent = false;

	ConnectionDetector cd;
	public Handler mhandler;

	@SuppressLint("HandlerLeak")
	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.facture_reservation_chambre_layout);

		texttype = (TextView) findViewById(R.id.textTypech);
		textnbre = (TextView) findViewById(R.id.textNbch);
		textprixch = (TextView) findViewById(R.id.textPrixch);
		textprixp = (TextView) findViewById(R.id.textPrixpension);
		texttva = (TextView) findViewById(R.id.textTva);
		texttotal = (TextView) findViewById(R.id.textTotalch);
		totalttc = (TextView) findViewById(R.id.texttotalttcfactch);

		datereser = (TextView) findViewById(R.id.textdatech);
		textnetpayer = (TextView) findViewById(R.id.textNetch);
		textcin = (TextView) findViewById(R.id.textcinpassagerfacture);
		paypal = (ImageButton) findViewById(R.id.paypalfactch);

		texttotaleuro = (TextView) findViewById(R.id.textTotalcheuro);
		totalttceuro = (TextView) findViewById(R.id.texttotalttcfactcheuro);
		textnetpayereuro = (TextView) findViewById(R.id.textNetcheuro);

		texttotaldollar = (TextView) findViewById(R.id.textTotalchdollar);
		totalttcdollar = (TextView) findViewById(R.id.texttotalttcfactchdollar);
		textnetpayerdollar = (TextView) findViewById(R.id.textNetchdollar);
		cd = new ConnectionDetector(getApplicationContext());
		b = (TextView) findViewById(R.id.buttonpdf1);

		paypal.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent i = new Intent(FactureReservationChambreActivity.this,
						PaiementPaypalActivity.class);
				startActivity(i);

			}
		});
		b.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				createPDF();
			}
		});
		//isInternetPresent = cd.isConnectingToInternet();

		// check for Internet status
		//if (isInternetPresent) {
			if (MainChambreFragment.infCh.getE_mail() != null) {
				new AfficherFactureCh().execute();
			} else if (MainChambreFragment.infCh.getEmailverif() != null) {
				new AfficherFactureResrvRapid().execute();

			}
		/*} else {
			// Internet connection is not present
			// Ask user to connect to Internet
			showAlertDialog(FactureReservationChambreActivity.this,
					"No Internet Connection",
					"You don't have internet connection.", false);
		}*/

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

	@SuppressWarnings("deprecation")
	public void showAlertDialog(Context context, String title, String message,
			Boolean status) {
		AlertDialog alertDialog = new AlertDialog.Builder(context).create();

		// Setting Dialog Title
		alertDialog.setTitle(title);

		// Setting Dialog Message
		alertDialog.setMessage(message);

		// Setting alert dialog icon
		alertDialog.setIcon((status) ? R.drawable.success : R.drawable.fail);

		// Setting OK Button
		alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
			}
		});

		// Showing Alert Message
		alertDialog.show();
	}

	class AfficherFactureCh extends AsyncTask<String, String, String> {

		protected String doInBackground(String... args) {

			String e_mail = MainChambreFragment.infCh.getE_mail();

			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("e_mail", e_mail));
			Log.d("e_mail", "" + e_mail);
			try {

				json = jsonParser.makeHttpRequest(url, "POST", params);

				JSONObject jsonResponse = new JSONObject(json.toString());
				jsonResponse = new JSONObject(json.toString());

				fact2 = "" + jsonResponse.get("aff3");
				JSONArray jsonRp = new JSONArray(fact2);

				String str1 = "" + jsonRp.getJSONArray(0);
				JSONArray jArray6 = new JSONArray(str1);

				String str2 = "" + jsonRp.getJSONArray(1);
				JSONArray jArray7 = new JSONArray(str2);

				String str3 = "" + jsonRp.getJSONArray(2);
				JSONArray jArray8 = new JSONArray(str3);

				String str4 = "" + jsonRp.getJSONArray(3);
				JSONArray jArray9 = new JSONArray(str4);

				String str5 = "" + jsonRp.getJSONArray(4);
				JSONArray jArray10 = new JSONArray(str5);

				String str8 = "" + jsonRp.getJSONArray(8);
				JSONArray jArray13 = new JSONArray(str8);
				for (int i = 0; i < jArray6.length(); i++) {
					t6 = t6.concat("\n" + jArray6.getString(i));

				}
				for (int i = 0; i < jArray7.length(); i++) {
					t7 = t7.concat("\n" + jArray7.getString(i));

				}
				for (int i = 0; i < jArray8.length(); i++) {
					t8 = t8.concat("\n" + jArray8.getString(i));

				}
				for (int i = 0; i < jArray9.length(); i++) {
					t9 = t9.concat("\n" + jArray9.getString(i));

				}
				for (int i = 0; i < jArray10.length(); i++) {
					t10 = t10.concat("\n" + jArray10.getString(i));

				}
				for (int i = 0; i < jArray13.length(); i++) {
					t13 = t13.concat("\n" + jArray13.getString(i));

				}
				t11 = jsonRp.getString(5);

				t12 = jsonRp.getString(6);

				t2 = jsonRp.getString(7);

			} catch (Exception e1) {

				e1.printStackTrace();
			}
			return null;
		}

		@Override
		protected void onPostExecute(String result) {

			texttype.setText(t6);
			textnbre.setText(t7);
			textprixch.setText(t8);
			textprixp.setText(t9);
			texttva.setText(t10);
			datereser.setText(t13);
			textcin.setText(t2);
			
			val1=t11.toString();
			val2=t12.toString();
			
			double i3 = Double.parseDouble(t11.toString());
			double i4 = Double.parseDouble(t12.toString());
			DecimalFormat df = new DecimalFormat("########.000");
			String str = df.format(i3);
			String str2 = df.format(i4);
			texttotal.setText(str);
			textnetpayer.setText(str2);
			totalttc.setText(str2);
			
			super.onPostExecute(result);
			t6 = "";
			t7 = "";
			t8 = "";
			t9 = "";
			t10 = "";
			t11 = "";
			t12 = "";
			t13 = "";
		}

	}

	class AfficherFactureResrvRapid extends AsyncTask<String, String, String> {

		protected String doInBackground(String... args) {

			String e_mail = MainChambreFragment.infCh.getEmailverif();

			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("e_mail", e_mail));
			Log.d("e_mail", "" + e_mail);
			try {

				json = jsonParser.makeHttpRequest(url, "POST", params);

				JSONObject jsonResponse = new JSONObject(json.toString());
				jsonResponse = new JSONObject(json.toString());

				fact2 = "" + jsonResponse.get("aff3");
				JSONArray jsonRp = new JSONArray(fact2);

				String str1 = "" + jsonRp.getJSONArray(0);
				JSONArray jArray6 = new JSONArray(str1);

				String str2 = "" + jsonRp.getJSONArray(1);
				JSONArray jArray7 = new JSONArray(str2);

				String str3 = "" + jsonRp.getJSONArray(2);
				JSONArray jArray8 = new JSONArray(str3);

				String str4 = "" + jsonRp.getJSONArray(3);
				JSONArray jArray9 = new JSONArray(str4);

				String str5 = "" + jsonRp.getJSONArray(4);
				JSONArray jArray10 = new JSONArray(str5);

				String str8 = "" + jsonRp.getJSONArray(8);
				JSONArray jArray13 = new JSONArray(str8);
				for (int i = 0; i < jArray6.length(); i++) {
					t6 = t6.concat("\n" + jArray6.getString(i));

				}
				for (int i = 0; i < jArray7.length(); i++) {
					t7 = t7.concat("\n" + jArray7.getString(i));

				}
				for (int i = 0; i < jArray8.length(); i++) {
					t8 = t8.concat("\n" + jArray8.getString(i) + "dt");

				}
				for (int i = 0; i < jArray9.length(); i++) {
					t9 = t9.concat("\n" + jArray9.getString(i) + "dt");

				}
				for (int i = 0; i < jArray10.length(); i++) {
					t10 = t10.concat("\n" + jArray10.getString(i));

				}
				for (int i = 0; i < jArray13.length(); i++) {
					t13 = t13.concat("\n" + jArray13.getString(i));

				}
				t11 = jsonRp.getString(5);

				t12 = jsonRp.getString(6);

				t2 = jsonRp.getString(7);
			} catch (Exception e1) {

				e1.printStackTrace();
			}
			return null;
		}

		@Override
		protected void onPostExecute(String result) {
			texttype.setText(t6);
			textnbre.setText(t7);
			textprixch.setText(t8);
			textprixp.setText(t9);
			texttva.setText(t10);

			datereser.setText(t13);
			textcin.setText(t2);
			
			val1=t11.toString();
			val2=t12.toString();
			
			double i3 = Double.parseDouble(t11.toString());
			double i4 = Double.parseDouble(t12.toString());
			DecimalFormat df = new DecimalFormat("####.0000000");
			String str = df.format(i3);
			String str2 = df.format(i4);
			texttotal.setText(str);
			textnetpayer.setText(str2);
			totalttc.setText(str2);
			
			super.onPostExecute(result);
			t6 = "";
			t7 = "";
			t8 = "";
			t9 = "";
			t10 = "";
			t11 = "";
			t12 = "";
			t13 = "";
		}

	}

	public void createPDF() {

		Document doc = new Document();

		try {
			String path = Environment.getExternalStorageDirectory()
					.getAbsolutePath() + "/droidText";
			File dir = new File(path);
			if (!dir.exists())
				dir.mkdirs();
			Log.d("PDFCreator", "PDF Path: " + path);
			File file = new File(dir, "Facture.pdf");
			FileOutputStream fOut = new FileOutputStream(file);

			PdfWriter.getInstance(doc, fOut);

			doc.open();
			ByteArrayOutputStream stream = new ByteArrayOutputStream();
			Bitmap bitmap = BitmapFactory.decodeResource(getBaseContext()
					.getResources(), R.drawable.ic_logo);
			bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
			Image myImg = Image.getInstance(stream.toByteArray());
			myImg.setAlignment(Image.MIDDLE);
			myImg.scaleAbsoluteHeight(50);
			myImg.scaleAbsoluteWidth(150);

			// add image to document
			doc.add(myImg);

			Paragraph p3 = new Paragraph(); // to enter value you have to create
											// paragraph and add value in it
											// then paragraph is added into
											// document
			p3.add("CIN  : " + textcin.getText().toString());
			doc.add(p3);
			Paragraph p1 = new Paragraph(); // to enter value you have to create
											// paragraph and add value in it
											// then paragraph is added into
											// document
			p1.add("\n");
			doc.add(p1);

			PdfPTable table = new PdfPTable(5);
			table.addCell("type chambre");
			table.addCell("nombre de jours");
			table.addCell("date reservation");
			table.addCell("prix chambre");
			table.addCell("prix pension");

			// get the value from database
			String type = texttype.getText().toString();
			String nb = textnbre.getText().toString();
			String date = datereser.getText().toString();
			String prixch = textprixch.getText().toString();
			String prixpens = textprixp.getText().toString();
			table.addCell(type);
			table.addCell(nb);
			table.addCell(date);
			table.addCell(prixch);
			table.addCell(prixpens);

			// add table into document
			doc.add(table);
			Paragraph p2 = new Paragraph(); // to enter value you have to create
											// paragraph and add value in it
											// then paragraph is added into
											// document
			p2.add("  \n");

			doc.add(p2);
			Paragraph p4 = new Paragraph(); // to enter value you have to create
											// paragraph and add value in it
											// then paragraph is added into
											// document
			p4.add("Total HT  : " + texttotal.getText().toString() + " DT"
					+ "   " + texttotaleuro.getText().toString() + "   "
					+ texttotaldollar.getText().toString());
			doc.add(p4);
			Paragraph p5 = new Paragraph(); // to enter value you have to create
											// paragraph and add value in it
											// then paragraph is added into
											// document
			p5.add("Total TTC  : " + totalttc.getText().toString() + " DT"
					+ "   " + totalttceuro.getText().toString() + "   "
					+ totalttcdollar.getText().toString());
			doc.add(p5);
			Paragraph p6 = new Paragraph(); // to enter value you have to create
											// paragraph and add value in it
											// then paragraph is added into
											// document
			p6.add("NET � Pay�e : " + textnetpayer.getText().toString() + " DT"
					+ "   " + textnetpayereuro.getText().toString() + "   "
					+ textnetpayerdollar.getText().toString());
			doc.add(p6);

			doc.addCreationDate();
			doc.close();
		} catch (Exception e) {
			e.printStackTrace();
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
			
			//if (isInternetPresent) {
			new Thread(new Runnable() {

				@Override
				public void run() {
					double i3 = Double.parseDouble(val1.toString());
					double i4 = Double.parseDouble(val2.toString());

					res3 = convert("TND", "EUR", i3);
					res4 = convert("TND", "EUR", i4);
					res5 = convert("TND", "EUR", i4);
				
					mhandler.sendEmptyMessage(1);

				};
			}).start();
			/*}
			else {
				// Internet connection is not present
				// Ask user to connect to Internet
				showAlertDialog(FactureReservationChambreActivity.this,
						"No Internet Connection",
						"You don't have internet connection.", false);
			}*/

			break;

		case R.id.action_dollar:
			//if (isInternetPresent) {
			new Thread(new Runnable() {

				@Override
				public void run() {

					double i3 = Double.parseDouble(val1.toString());
					double i4 = Double.parseDouble(val2.toString());

					res0 = convert("TND", "USD", i3);
					res1 = convert("TND", "USD", i4);
					res2 = convert("TND", "USD", i4);
					mhandler.sendEmptyMessage(2);

				};
			}).start();
			/*}
			else {
				// Internet connection is not present
				// Ask user to connect to Internet
				showAlertDialog(FactureReservationChambreActivity.this,
						"No Internet Connection",
						"You don't have internet connection.", false);
			}*/

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