package com.cbw.fideltour.gcm;

import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.os.Vibrator;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.cbw.fideltour.R;
import com.cbw.fideltour.activity.NouvelleOffreActivity;
import com.cbw.fideltour.entity.Offre;
import com.cbw.fideltour.instachat.DataProvider;
import com.cbw.fideltour.instachat.MessagesFragment;
import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.google.gson.Gson;

public class GcmIntentService extends IntentService {

	public GcmIntentService(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	public static final int NOTIFICATION_ID = 1;
	private static final String TAG = "GCM Intent seRVICE";
	private NotificationManager mNotificationManager;
	NotificationCompat.Builder builder;
	Bundle lesExtra;
	Bundle extras;
	public static final String URL_BASE_PATH = "http://192.168.1.3/Projet_BD_Fidel_Tour/images/";

	public GcmIntentService() {
		super("GcmIntentService");
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		Log.i(TAG, "Received!!");
		extras = intent.getExtras();
		GoogleCloudMessaging gcm = GoogleCloudMessaging.getInstance(this);
		// The getMessageType() intent parameter must be the intent you received
		// in your BroadcastReceiver.
		String messageType = gcm.getMessageType(intent);

		if (!extras.isEmpty()) { // has effect of unparcelling Bundle
			/*
			 * Filter messages based on message type. Since it is likely that
			 * GCM will be extended in the future with new message types, just
			 * ignore any message types you're not interested in, or that you
			 * don't recognize.
			 */
			if (GoogleCloudMessaging.MESSAGE_TYPE_SEND_ERROR
					.equals(messageType)) {
				sendNotification("Send error: " + extras.toString());
			} else if (GoogleCloudMessaging.MESSAGE_TYPE_DELETED
					.equals(messageType)) {
				sendNotification("Deleted messages on server: "
						+ extras.toString());
				// If it's a regular GCM message, do some work.
			} else if (GoogleCloudMessaging.MESSAGE_TYPE_MESSAGE
					.equals(messageType)) {

				// Post notification of received message.
				sendNotification("Received: " + extras.toString());
				Log.i(TAG, "Received: " + extras.toString());

			}
		}
		// Release the wake lock provided by the WakefulBroadcastReceiver.
		GCMBroadcastReceiver.completeWakefulIntent(intent);
	}

	// Put the message into a notification and post it.
	// This is just one simple example of what you might choose to do with
	// a GCM message.
	private void sendNotification(String msg) {
		String messg = extras.getString(DataProvider.MESSAGE);
		String email = extras.getString(DataProvider.SENDER_EMAIL);

		if (messg == null && email == null) {
			Log.i(TAG,"Notif Offre");
			mNotificationManager = (NotificationManager) this
					.getSystemService(Context.NOTIFICATION_SERVICE);

			// Meme que parametres GCM php
			String nomOffre = extras.getString("nomOffre");
			String descOffre = extras.getString("descriptionOffre");
			String prixOffre = extras.getString("prixOffre");
			String urlOffre = extras.getString("path");
			String delaisOffre = extras.getString("delaiOffre");

			Gson obj = new Gson();
			ExtraDate extraDate = obj.fromJson(delaisOffre, ExtraDate.class);
			String[] completeDate = extraDate.date.split(" ");
			if (completeDate.length == 2) {
				delaisOffre = completeDate[0];
			}

			Log.i(TAG, delaisOffre);

			// Activity destination
			Intent i = new Intent(this, NouvelleOffreActivity.class);
			Offre offre = new Offre();
			offre.setDelai_offre(delaisOffre);
			offre.setDescription_offre(descOffre);
			offre.setNom_offre(nomOffre);
			offre.setPrix_offre(prixOffre);
			offre.setPath(URL_BASE_PATH + urlOffre);

			Log.i(TAG, "Revceived raw  = " + msg);

			i.putExtra("offre", offre);
			i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

			int random = (int) (Math.random() * 500);

			PendingIntent contentIntent = PendingIntent.getActivity(this,
					random, i, PendingIntent.FLAG_UPDATE_CURRENT);
			msg = "Nom Offre : " + nomOffre + "\nDescription Offre: "
					+ descOffre + "\nImage Offre: " + urlOffre
					+ "\nPrix Offre: " + prixOffre + "\nDelais Offre: "
					+ delaisOffre;

			NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(
					this)
					.setSmallIcon(R.drawable.ic_launcher)
					.setContentTitle("Nouvelle offre !")
					.setAutoCancel(true)
					.setStyle(
							new NotificationCompat.BigTextStyle().bigText(msg))
					.setContentText(msg);

			mBuilder.setContentIntent(contentIntent);
			mNotificationManager.notify(random, mBuilder.build());

			// Vibreur
			Vibrator vibrate = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
			vibrate.vibrate(new long[] { 0, 850, 50, 850 }, -1);

		} else {
			//Notif message
			Log.i(TAG,"Notif Message");
			ContentValues values = new ContentValues(2);
            values.put(DataProvider.COL_MSG, messg);
            values.put(DataProvider.COL_FROM, email);
            Uri newLine = getApplicationContext().getContentResolver().insert(DataProvider.CONTENT_URI_MESSAGES, values);
            
            Cursor newC = getContentResolver().query(newLine, null, null, null, null);
            
            Log.i("GCM insert count", String.valueOf(newC.getCount())+"|"+newLine.getPath());
            while(newC.moveToNext()){
            	Log.i("GCM insert", String.valueOf(newC.getString(newC.getColumnIndex(DataProvider.COL_MESSAGE))));
            }
           
            
            Handler refresh = new Handler(Looper.getMainLooper());
            refresh.post(new Runnable() {
                public void run()
                {
                	 if(MessagesFragment.adapter!=null){
                     	MessagesFragment.adapter.changeCursor(getContentResolver().query(Uri.withAppendedPath(DataProvider.CONTENT_URI_MESSAGES,""), null, null, null, null));
                     	MessagesFragment.adapter.notifyDataSetChanged();
                     	Log.w("Gcm Service", "updated");
                     	
                     	//c.close();
                     }
                }
            });
            
            // This loop represents the service doing some work.
            Log.i(TAG, "Completed work @ " + SystemClock.elapsedRealtime()+" and inserted message : "+msg+", from: "+email);
            
		}

	}

	private static class ExtraDate {
		String timezone;
		int timezone_type;
		String date;
	}
}
