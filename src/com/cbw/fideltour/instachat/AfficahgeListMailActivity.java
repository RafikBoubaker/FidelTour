package com.cbw.fideltour.instachat;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import com.cbw.fideltour.R;
import com.cbw.fideltour.activity.ConnexionActivity;
import com.cbw.fideltour.parsing.HttpHelper;
import com.cbw.fideltour.parsing.JSONParser;
import com.google.gson.Gson;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class AfficahgeListMailActivity extends Activity{
	private static final String TAG_SUCCESS = "success";
	JSONParser jsonParser = new JSONParser();
	JSONArray jObj;
	JSONObject json;
	JSONParser jParser = new JSONParser();
	public Context context;
	JSONArray list = null;
	ArrayList<String> list1;
	private static String url =  "http://192.168.43.59/Projet_BD_Fidel_Tour/AffichageListeMail.php";
	ChatMail mail[];
	TextView lismail;
	ListView listViewItems;
	List<ChatMail> m;
Context ctx;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_listmail);
		listViewItems = (ListView)findViewById(R.id.listmaillayout);
		new listeMail().execute();
		
		ctx=getApplicationContext();
	}

	class listeMail extends AsyncTask<String, String, String> {

		@SuppressLint("NewApi")
		protected String doInBackground(String... args) {
			Log.d(TAG_SUCCESS, "msg1");
			String e_mail = ConnexionActivity.client.getE_mail();

			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("e_mail", e_mail));

			json = jsonParser.makeHttpRequest(url, "POST", params);


			try {
				Log.d("Tag", "test 1");
				

				json = jsonParser.makeHttpRequest(url, "POST", params);
				//JSONArray jsonRpCateg = new JSONArray(json.get("a"));
				
				//JSONArray js=new JSONArray(""+jsonRpCateg.getJSONArray(0));
				Log.i("JSON String", json.get("a").toString());
				mail = (new Gson()).fromJson(json.get("a").toString(), ChatMail[].class);

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
		AdapterChatMail adapter1 = new AdapterChatMail(AfficahgeListMailActivity.this,
				R.layout.entry_layout_listmail, mail);
		listViewItems.setAdapter(adapter1);
listViewItems.setOnItemClickListener(new OnItemClickListener() {

	@Override
	public void onItemClick(AdapterView<?> pariente, View view,
			int position, long id) {

		ChatMail chosen = (ChatMail) pariente.getItemAtPosition(position);

		String e_mail = chosen.getE_mail();
		Log.i("mail",e_mail);
		 try {
             ContentValues values = new ContentValues(2);
             values.put(DataProvider.COL_NAME, e_mail.substring(0, e_mail.indexOf('@')));
             values.put(DataProvider.COL_EMAIL, e_mail);
             Uri uri = ctx.getContentResolver().insert(DataProvider.CONTENT_URI_PROFILE, values);
             Cursor cPID = ctx.getContentResolver().query(uri,new String[]{DataProvider.COL_ID}, null, null, null);
             cPID.moveToFirst();
             
             Intent i = new Intent(ctx, ChatActivity.class);
             i.putExtra(Common.PROFILE_ID, cPID.getString(0));
             startActivity(i);
             
         } catch (SQLException sqle) {
        	 Log.i("Error", sqle.getMessage());
        	 Cursor cPID = ctx.getContentResolver().query(DataProvider.CONTENT_URI_PROFILE,new String[]{DataProvider.COL_ID}, "email = '"+e_mail+"'", null, null);
             cPID.moveToFirst();
             Intent i = new Intent(ctx, ChatActivity.class);
             i.putExtra(Common.PROFILE_ID, cPID.getString(0));
             startActivity(i);
        	 
         }
        
     }
 });
	
	
	}

}
