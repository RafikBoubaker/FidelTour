package com.cbw.fideltour.instachat;

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
import android.os.IBinder;
import android.os.Looper;
import android.os.SystemClock;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.cbw.fideltour.R;
import com.google.android.gms.gcm.GoogleCloudMessaging;

public class GcmIntentService extends IntentService  {
	
	public GcmIntentService() {
        super("GcmIntentService");
    }
	
    private NotificationManager mNotificationManager;
    NotificationCompat.Builder builder;

    String msg ;
    String email ;
    public static final String TAG = "KAKA";
    
    @Override
    public int onStartCommand(Intent intent,int flags, int startId){
    	Log.d(TAG, "onStartCommand");
    	Notify(intent);
    	return super.onStartCommand(intent,flags,startId);
    }
    
    @Override
	public IBinder onBind(Intent intent) {
    	Log.d(TAG, "onBind");
		return null;
	}

    protected void Notify(Intent intent) {
        Bundle extras = intent.getExtras();
        GoogleCloudMessaging gcm = GoogleCloudMessaging.getInstance(this);
        // The getMessageType() intent parameter must be the intent you received
        // in your BroadcastReceiver.
        String messageType = gcm.getMessageType(intent);

        if (!extras.isEmpty()) {  // has effect of unparcelling Bundle
            /*
             * Filter messages based on message type. Since it is likely that GCM will be
             * extended in the future with new message types, just ignore any message types you're
             * not interested in, or that you don't recognize.
             */
            if (GoogleCloudMessaging.MESSAGE_TYPE_SEND_ERROR.equals(messageType)) {
                sendNotification("Send error: " + extras.toString());
            } else if (GoogleCloudMessaging.MESSAGE_TYPE_DELETED.equals(messageType)) {
                sendNotification("Deleted messages on server: " + extras.toString());
            // If it's a regular GCM message, do some work.
            } else if (GoogleCloudMessaging.MESSAGE_TYPE_MESSAGE.equals(messageType)) {
            	
            	msg = intent.getStringExtra(DataProvider.MESSAGE);
                email = intent.getStringExtra(DataProvider.SENDER_EMAIL);
                 
                ContentValues values = new ContentValues(2);
                values.put(DataProvider.COL_MSG, msg);
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
                // Post notification of received message.
                sendNotification("Received: " + extras.toString());
                Log.i(TAG, "Received: " + extras.toString());
                
                
            }
        }
        // Release the wake lock provided by the WakefulBroadcastReceiver.
        GcmBroadcastReceiver.completeWakefulIntent(intent);
        
        
    }

    // Put the message into a notification and post it.
    // This is just one simple example of what you might choose to do with
    // a GCM message.
    private void sendNotification(String msg) {
        mNotificationManager = (NotificationManager)
                this.getSystemService(Context.NOTIFICATION_SERVICE);
Intent mainIntent = new Intent(this, MainActivityChat.class);
        
        
        Cursor c = getContentResolver().query(DataProvider.CONTENT_URI_PROFILE, null, DataProvider.COL_EMAIL+"='"+email+"'", null, null);
        if(c==null||c.getCount()==0){
        	mainIntent.putExtra(DataProvider.COL_EMAIL, email);
        	c.close();
        }
        mainIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        mainIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        Log.d("keke", "sendNotification complete");

        PendingIntent contentIntent = PendingIntent.getActivity(this, 0,
                mainIntent, 0);
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this)
        .setSmallIcon(R.drawable.ic_stat_gcm)
        .setContentTitle("GCM Notification")
        .setStyle(new NotificationCompat.BigTextStyle()
        .bigText(msg))
        .setContentText(msg);
        
        
        mBuilder.setContentIntent(contentIntent);
        mNotificationManager.notify(Constants.NOTIFICATION_ID, mBuilder.build());
    }

	@Override
	protected void onHandleIntent(Intent intent) {
		//Notify(intent);
		
	}
}
