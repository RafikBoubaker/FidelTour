package com.cbw.fideltour.adapter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import com.cbw.fideltour.entity.Hotel;
import com.cbw.fideltour.entity.Video;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

public class GetUserVideosTask implements Runnable {

	private final Handler replyTo;

	public GetUserVideosTask(Handler replyTo) {
		this.replyTo = replyTo;

	}

	@Override
	public void run() {

		try {

			HttpClient client = new DefaultHttpClient();

			HttpUriRequest request = new HttpGet(Hotel.adresse_ip
					+ "ConsulterVideo.php");

			HttpResponse response = client.execute(request);

			BufferedReader reader = new BufferedReader(new InputStreamReader(
					response.getEntity().getContent(), "UTF-8"));
			String jsonString = reader.readLine();

			JSONObject json = new JSONObject(jsonString);

			json = new JSONObject(json.toString());
			JSONArray jsonRp = new JSONArray("" + json.get("video"));
			// Array where to put into videos
			ArrayList<Video> videosList = new ArrayList<Video>();

			JSONArray jArray1 = new JSONArray("" + jsonRp.getJSONArray(0));
			JSONArray jArray2 = new JSONArray("" + jsonRp.getJSONArray(1));
			JSONArray jArray3 = new JSONArray("" + jsonRp.getJSONArray(2));
			JSONArray jArray4 = new JSONArray("" + jsonRp.getJSONArray(3));
			for (int i = 0; i < jArray1.length(); i++) {

				String title = jArray1.getString(i);

				String image = jArray2.getString(i);

				String prix = jArray3.getString(i);
				String path = jArray4.getString(i);
				videosList.add(new Video(title, image,prix,path));
			}

			Bundle data = new Bundle();
			data.putSerializable("LISTVIDEOS", videosList);
			Message msg = Message.obtain();
			msg.setData(data);
			replyTo.sendMessage(msg);

		} catch (ClientProtocolException e) {
		} catch (IOException e) {
		} catch (JSONException e) {
		}
	}
}
