package com.cbw.fideltour.activity;

import java.util.ArrayList;
import java.util.List;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import com.cbw.fideltour.R;
import com.cbw.fideltour.entity.Hotel;
import com.cbw.fideltour.instachat.AfficahgeListMailActivity;
import com.cbw.fideltour.instachat.MainActivityChat;
import com.cbw.fideltour.parsing.JSONParser;
import android.os.AsyncTask;
import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

public class PartieClientActivity extends Activity {
	ImageView imageview;
	ImageView imageviewfb;
	ImageView imageviewgl;
	ImageView imageviewtw;
	ImageView imageviewyt;
	TextView text1;
	TextView text2;
	String rep1 = "";
	String rep2 = "";
	String email;
	String af1;
	Button inviter;
	Button facture;
	private int success;
	Button service;
	Button historique;
	Button chat;
	private static final int SELECT_PICTURE = 1;
	private String selectedImagePath;
	private static final String TAG_SUCCESS = "success";
	JSONParser jsonParser = new JSONParser();
	JSONArray jObj;
	JSONObject json;
	JSONParser jParser = new JSONParser();
	public Context context;
	JSONArray list = null;
	ArrayList<String> list1;
	private static String url = Hotel.adresse_ip + "InfoProfil.php";
	private static String url_accee = Hotel.adresse_ip + "TestAcceeService.php";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.partie_client_layout);
		new AfficherInfoClient().execute();
		imageview = (ImageView) findViewById(R.id.imageViewInfo);
		imageviewfb = (ImageView) findViewById(R.id.buttonfacebook);
		imageviewgl = (ImageView) findViewById(R.id.buttongmail);
		imageviewyt = (ImageView) findViewById(R.id.buttonyoutube);
		imageviewtw = (ImageView) findViewById(R.id.buttontwitter);
		text1 = (TextView) findViewById(R.id.textViewNom);
		text2 = (TextView) findViewById(R.id.textViewPrenom);
		historique = (Button) findViewById(R.id.btreservation);
		inviter = (Button) findViewById(R.id.btinvit);
		facture = (Button) findViewById(R.id.btfacture);
		service = (Button) findViewById(R.id.btServices);
		chat = (Button) findViewById(R.id.btblog);
		imageviewfb.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				String url = "https://www.facebook.com";
				Intent i = new Intent(Intent.ACTION_VIEW);
				i.setData(Uri.parse(url));
				startActivity(i);

			}

		});
		imageviewgl.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				String urlg = "https://plus.google.com";
				Intent g = new Intent(Intent.ACTION_VIEW);
				g.setData(Uri.parse(urlg));
				startActivity(g);
			}

		});
		imageviewtw.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				String urlt = "https://twitter.com";
				Intent t = new Intent(Intent.ACTION_VIEW);
				t.setData(Uri.parse(urlt));
				startActivity(t);
			}

		});
		imageviewyt.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				String urlyt = "https://www.youtube.com";
				Intent e = new Intent(Intent.ACTION_VIEW);
				e.setData(Uri.parse(urlyt));
				startActivity(e);

			}

		});

		service.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				new verifAcce().execute();

			}

		});

		imageview.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setType("image/*");
				intent.setAction(Intent.ACTION_GET_CONTENT);
				startActivityForResult(
						Intent.createChooser(intent, "Select Picture"),
						SELECT_PICTURE);
			}
		});

		facture.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent i = new Intent(PartieClientActivity.this,
						ConsulterFacturesActivity.class);
				startActivity(i);
			}
		});
		chat.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent2 = new Intent(PartieClientActivity.this, AfficahgeListMailActivity.class);
				startActivity(intent2);
			}
		});
		inviter.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent i = new Intent(PartieClientActivity.this,
						InviterPersonneActivity.class);
				startActivity(i);
			}
		});
		historique.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent i = new Intent(PartieClientActivity.this,
						HistoriqueReservationActivity.class);
				startActivity(i);
			}
		});
	}

	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode == RESULT_OK) {
			if (requestCode == SELECT_PICTURE) {
				Uri selectedImageUri = data.getData();
				selectedImagePath = getPath(selectedImageUri);
				System.out.println("Image Path : " + selectedImagePath);
				imageview.setImageURI(selectedImageUri);
			}
		}
	}

	public String getPath(Uri uri) {
		String[] projection = { MediaStore.Images.Media.DATA };
		@SuppressWarnings("deprecation")
		Cursor cursor = managedQuery(uri, projection, null, null, null);
		int column_index = cursor
				.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
		cursor.moveToFirst();
		return cursor.getString(column_index);
	}

	class AfficherInfoClient extends AsyncTask<String, String, String> {

		@SuppressLint("NewApi")
		protected String doInBackground(String... args) {

			Log.i("PartieClient client", String.valueOf(ConnexionActivity.client==null));
			String e_mail = ConnexionActivity.client.getE_mail();

			
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("e_mail", e_mail));

			try {
				json = jsonParser.makeHttpRequest(url, "POST", params);

				JSONObject jsonResponse = new JSONObject(json.toString());

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

			super.onPostExecute(result);
			text1.setText(rep1);
			text2.setText(rep2);

			rep1 = "";
			rep2 = "";

		}
	}

	class verifAcce extends AsyncTask<String, String, String> {
		protected String doInBackground(String... args) {
			String e_mail = ConnexionActivity.client.getE_mail();

			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("e_mail", e_mail));

			try {
				json = jsonParser.makeHttpRequest(url_accee, "POST", params);
				success = json.getInt(TAG_SUCCESS);

				if (success == 1) {

					Intent servclient = new Intent(getApplicationContext(),
							ServiceClientActivity.class);
					startActivity(servclient);

				} else {

				}
			} catch (JSONException e) {
				e.printStackTrace();
			}

			return null;
		}

		protected void onPostExecute(String file_url) {
			if (success == 0) {
				Toast.makeText(PartieClientActivity.this,
						"Vous etes hors séjours", Toast.LENGTH_SHORT).show();
			}
		}
	}

	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {

		case R.id.action_Contact:
			Intent intent1 = new Intent(PartieClientActivity.this,
					ContactActivity.class);
			startActivity(intent1);
			return true;
		}

		return super.onOptionsItemSelected(item);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main2, menu);
		return true;
	}
}
