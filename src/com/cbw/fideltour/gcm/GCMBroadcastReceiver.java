package com.cbw.fideltour.gcm;

import java.util.ArrayList;
import java.util.List;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;
import com.cbw.fideltour.entity.Hotel;
import com.cbw.fideltour.parsing.JSONParser;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.content.WakefulBroadcastReceiver;
import android.util.Log;

public class GCMBroadcastReceiver extends WakefulBroadcastReceiver {
	String regId;
	JSONParser jsonParser = new JSONParser();
	JSONObject json_data;
	private static String url = Hotel.adresse_ip
			+ "EnregistrementIdRegister.php";
	private static final String TAG_SUCCESS = "success";
	private static final String TAG_MESSAGE = "message";


	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		 // Explicitly specify that GcmIntentService will handle the intent.
        ComponentName comp = new ComponentName(context.getPackageName(),
                GcmIntentService.class.getName());
        // Start the service, keeping the device awake while it is launching.
        if(intent.getExtras().getString("registration_id")!=null){
        	regId = intent.getExtras().getString("registration_id");
        	
        	//Envoyer regId au webservice
            Log.i("reg id ", regId);
             new CreateNewid().execute();
        }
        Log.i("receiver", "Received!! 2");
        
        startWakefulService(context, (intent.setComponent(comp)));
        setResultCode(Activity.RESULT_OK);
	}
	class CreateNewid extends AsyncTask<String, String, String> {

		protected String doInBackground(String... args) {
			String id_register=regId.toString();
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			
			params.add(new BasicNameValuePair("id_register",id_register));
		
			JSONObject json = jsonParser.makeHttpRequest(url,
					"POST", params);
			try {

				int success = json.getInt(TAG_SUCCESS);

				if (success == 1) {

					return json.getString(TAG_MESSAGE);

				} else {
					return json.getString(TAG_MESSAGE);

				}
			} catch (JSONException e) {
				e.printStackTrace();
			}

			return null;

		}
	}

}
