package com.cbw.fideltour.gcm;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.gcm.GoogleCloudMessaging;


public class GcmUtils {
	private final static int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;

	private static final String TAG = "GCM Utils";
	// Project Number apartir de google developper console
	protected static final String SENDER_ID = "90961496910";

	Context context;

	public GcmUtils(Context ctx) {
		this.context = ctx;
	}

	public boolean checkPlayServices() {
		int resultCode = GooglePlayServicesUtil
				.isGooglePlayServicesAvailable(context);
		if (resultCode != ConnectionResult.SUCCESS) {
			if (GooglePlayServicesUtil.isUserRecoverableError(resultCode)) {
				GooglePlayServicesUtil.getErrorDialog(resultCode,
						(Activity) context, PLAY_SERVICES_RESOLUTION_REQUEST)
						.show();
			} else {
				Log.i(TAG, "This device is not supported.");
				((Activity) context).finish();
			}
			return false;
		}
		return true;
	}

	public void registerInBackground() {
		AsyncTask<Void, Void, Void> task = new AsyncTask<Void, Void, Void>() {

			GoogleCloudMessaging gcm;
			private String regid;

			@Override
			protected Void doInBackground(Void... params) {
				try {
					if (gcm == null) {
						gcm = GoogleCloudMessaging.getInstance(context);
					}
					regid = gcm.register(SENDER_ID);

					// You should send the registration ID to your server over
					// HTTP,
					// so it can use GCM/HTTP or CCS to send messages to your
					// app.
					// The request to your server should be authenticated if
					// your app
					// is using accounts.
					// sendRegistrationIdToBackend();

					// For this demo: we don't need to send it because the
					// device
					// will send upstream messages to a server that echo back
					// the
					// message using the 'from' address in the message.

					// Persist the regID - no need to register again.
					// storeRegistrationId(context, regid);
				} catch (IOException ex) {
					ex.printStackTrace();
					// If there is an error, don't just keep trying to register.
					// Require the user to click a button again, or perform
					// exponential back-off.
				}
				return null;
			}
		};
		try {
			task.execute(null, null).get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
