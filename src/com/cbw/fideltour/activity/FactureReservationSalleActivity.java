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
import com.cbw.fideltour.fragment.MainSalleFragment;
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

@SuppressLint("HandlerLeak")
public class FactureReservationSalleActivity extends FragmentActivity {

	JSONParser jsonParser = new JSONParser();
	private static String url = Hotel.adresse_ip + "AffichageFactureSalle.php";

	Boolean isInternetPresent = false;

	ConnectionDetector cd;
	private static String url_devise = "http://www.webservicex.com/CurrencyConvertor.asmx/ConversionRate";
	TextView typesl;
	TextView nbh;
	TextView jour;
	TextView prixsl;
	TextView ht;
	TextView ttc;
	TextView netpayee;
	TextView cin;
	TextView b;
	String val1;
	String val2;
	String t1 = "";
	String t2 = "";
	String t3 = "";
	String t4 = "";
	String t5 = "";
	String t6 = "";
	String t7 = "";
	String t8 = "";

	public Handler mhandler;

	TextView totalttceuro;
	TextView totalttcdollar;
	TextView texttotaleuro;
	TextView textnetpayereuro;
	TextView texttotaldollar;
	TextView textnetpayerdollar;
	JSONObject json;
	JSONObject jsonResponse;
	String fact2;
	ImageButton paypal;

	private String res3;
	private String res4;
	private String res5;
	private String res0;
	private String res1;
	private String res2;

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.facture_reservation_salle_layout);
		cd = new ConnectionDetector(getApplicationContext());
		typesl = (TextView) findViewById(R.id.texttypesalle);
		prixsl = (TextView) findViewById(R.id.textPrixsl);
		jour = (TextView) findViewById(R.id.textdatereservsl);
		cin = (TextView) findViewById(R.id.textcinpassagerfacturesl);
		ht = (TextView) findViewById(R.id.textTotalsl);
		ttc = (TextView) findViewById(R.id.texttotalttcfactsl);
		nbh = (TextView) findViewById(R.id.textnbheure);
		netpayee = (TextView) findViewById(R.id.textnetpayersl);
		paypal = (ImageButton) findViewById(R.id.paypalfacsalle);

		texttotaleuro = (TextView) findViewById(R.id.textTotalsleuro);
		totalttceuro = (TextView) findViewById(R.id.texttotalttcfactsleuro);
		textnetpayereuro = (TextView) findViewById(R.id.textnetpayersleuro);

		texttotaldollar = (TextView) findViewById(R.id.textTotalsldollar);
		totalttcdollar = (TextView) findViewById(R.id.texttotalttcfactsldollar);
		textnetpayerdollar = (TextView) findViewById(R.id.textnetpayersldollar);
		b = (TextView) findViewById(R.id.buttonpdf2);

		paypal.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent i = new Intent(FactureReservationSalleActivity.this,
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

		
		new AfficherFacturesl().execute();
		
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
	class AfficherFacturesl extends AsyncTask<String, String, String> {

		protected String doInBackground(String... args) {

			String id_passager = MainSalleFragment.infSl.getId_passager();

			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("id_passager", id_passager));
			try {
				json = jsonParser.makeHttpRequest(url, "POST", params);

				JSONObject jsonResponse = new JSONObject(json.toString());
				jsonResponse = new JSONObject(json.toString());

				fact2 = "" + jsonResponse.get("aff");
				JSONArray jsonRp = new JSONArray(fact2);

				String str5 = "" + jsonRp.getJSONArray(0);
				JSONArray jArray5 = new JSONArray(str5);

				String str6 = "" + jsonRp.getJSONArray(1);
				JSONArray jArray6 = new JSONArray(str6);

				String str7 = "" + jsonRp.getJSONArray(2);
				JSONArray jArray7 = new JSONArray(str7);

				String str9 = "" + jsonRp.getJSONArray(3);
				JSONArray jArray9 = new JSONArray(str9);

				String str10 = "" + jsonRp.getJSONArray(4);
				JSONArray jArray10 = new JSONArray(str10);

				for (int i = 0; i < jArray5.length(); i++) {
					t1 = t1.concat("\n" + jArray5.getString(i));

				}
				for (int i = 0; i < jArray6.length(); i++) {
					t2 = t2.concat("\n" + jArray6.getString(i));

				}
				for (int i = 0; i < jArray7.length(); i++) {
					t3 = t3.concat("\n" + jArray7.getString(i));

				}
				for (int i = 0; i < jArray9.length(); i++) {
					t4 = t4.concat("\n" + jArray9.getString(i));

				}
				for (int i = 0; i < jArray10.length(); i++) {
					t5 = t5.concat("\n" + jArray10.getString(i));

				}
				t6 = jsonRp.getString(5) ;
				t7 = jsonRp.getString(6);
				t8 = jsonRp.getString(7);

			} catch (Exception e1) {

				e1.printStackTrace();
			}
			return null;
		}

		@Override
		protected void onPostExecute(String result) {

			typesl.setText(t1);
			nbh.setText(t2);
			jour.setText(t3);
			prixsl.setText(t4);

			cin.setText(t8);
			val1=t6.toString();
			val2=t7.toString();
			double i3 = Double.parseDouble(t6.toString());
			double i4 = Double.parseDouble(t7.toString());
			DecimalFormat df = new DecimalFormat("########.000");
			String str = df.format(i3);
			String str2 = df.format(i4);
			ht.setText(str);
			netpayee.setText(str2);
			ttc.setText(str2);
			super.onPostExecute(result);
			t1 = "";
			t2 = "";
			t3 = "";
			t4 = "";
			t5 = "";
			t6 = "";
			t7 = "";
			t8 = "";
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
			File file = new File(dir, "sample.pdf");
			FileOutputStream fOut = new FileOutputStream(file);

			PdfWriter.getInstance(doc, fOut);

			// open the document
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
			p3.add("CIN  :  " + cin.getText().toString());
			doc.add(p3);
			Paragraph p1 = new Paragraph(); // to enter value you have to create
											// paragraph and add value in it
											// then paragraph is added into
											// document
			p1.add("  \n");
			doc.add(p1);

			PdfPTable table = new PdfPTable(4);
			table.addCell("type salle");
			table.addCell("nombre d'heure");
			table.addCell("date reservation");
			table.addCell("prix salle");

			// get the value from database
			String type = typesl.getText().toString();
			String nb = nbh.getText().toString();
			String date = jour.getText().toString();
			String prixch = prixsl.getText().toString();
			;
			table.addCell(type);
			table.addCell(nb);
			table.addCell(date);
			table.addCell(prixch);

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
			p4.add("Total HT  : " + ht.getText().toString());
			doc.add(p4);
			Paragraph p5 = new Paragraph(); // to enter value you have to create
											// paragraph and add value in it
											// then paragraph is added into
											// document
			p5.add("Total TTC  : " + ttc.getText().toString());
			doc.add(p5);
			Paragraph p6 = new Paragraph(); // to enter value you have to create
											// paragraph and add value in it
											// then paragraph is added into
											// document
			p6.add("NET à Payée : " + netpayee.getText().toString());
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
			return "erreur lors de l'appel du web service";

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
					Log.d("res3", res3);
					Log.d("res4", res4);
					mhandler.sendEmptyMessage(1);

				};
			}).start();
			/*}
			else {
				showAlertDialog(FactureReservationSalleActivity.this,
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
				showAlertDialog(FactureReservationSalleActivity.this,
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