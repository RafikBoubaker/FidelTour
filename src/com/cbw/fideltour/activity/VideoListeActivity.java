package com.cbw.fideltour.activity;

import java.util.ArrayList;
import java.util.List;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;
import com.cbw.fideltour.R;
import com.cbw.fideltour.adapter.AdapterVideo;
import com.cbw.fideltour.adapter.GetUserVideosTask;
import com.cbw.fideltour.entity.Hotel;
import com.cbw.fideltour.entity.Video;
import com.cbw.fideltour.parsing.JSONParser;
import com.squareup.picasso.Picasso;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.PixelFormat;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ToggleButton;
import android.widget.VideoView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout.LayoutParams;

public class VideoListeActivity extends FragmentActivity {
	String idPlayList;
	ListView videosListView;
	VideoView video;
	String URL_VIDEO;
	ToggleButton listExtendButton;
	ToggleButton playerExtendButton;
	Button changeChannelButton;
	int height = 0;
	Context ctx;
	JSONParser jsonParser = new JSONParser();
	private int success;
	JSONObject json_data;
	FrameLayout frame;
	ImageView thumb;
	String nom;
	private static String url_vid = Hotel.adresse_ip + "VoirVideo.php";
	private static final String TAG_SUCCESS = "success";
	private static final String TAG_MESSAGE = "message";

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		ctx = this;
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		getWindow().setFormat(PixelFormat.TRANSLUCENT);
		setContentView(R.layout.videos_layout);
		new Thread(new GetUserVideosTask(responseHandler)).start();
		video = (VideoView) findViewById(R.id.video_player_view);
		videosListView = (ListView) findViewById(R.id.listListView);
		frame = (FrameLayout) findViewById(R.id.framevideo);
		listExtendButton = (ToggleButton) findViewById(R.id.listExtendButton);
		listExtendButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				if (listExtendButton.isChecked()
						&& playerExtendButton.isChecked()) {

					playerExtendButton.performClick();
				}

				if (listExtendButton.isChecked()) {

					video.setVisibility(View.GONE);
					LayoutParams params = (LayoutParams) videosListView
							.getLayoutParams();
					height = params.height;
					params.height = android.view.ViewGroup.LayoutParams.WRAP_CONTENT;
				}

				else {

					video.setVisibility(View.VISIBLE);
					LayoutParams params = (LayoutParams) videosListView
							.getLayoutParams();
					params.height = height;
				}

			}
		});
		playerExtendButton = (ToggleButton) findViewById(R.id.playerExtendButton);

		playerExtendButton.setOnClickListener(new OnClickListener() {

			@SuppressWarnings("deprecation")
			@Override
			public void onClick(View v) {

				if (listExtendButton.isChecked()
						&& playerExtendButton.isChecked()) {

					listExtendButton.performClick();
				}

				if (playerExtendButton.isChecked()) {

					videosListView.setVisibility(View.GONE);

					LayoutParams params = (LayoutParams) frame
							.getLayoutParams();
					height = params.height;
					params.height = android.view.ViewGroup.LayoutParams.FILL_PARENT;
				}

				else {

					videosListView.setVisibility(View.VISIBLE);
					LayoutParams params = (LayoutParams) video
							.getLayoutParams();
					params.height = height;
				}
			}
		});

	}

	protected void onStop() {
		responseHandler = null;
		super.onStop();
	}

	protected void onPause() {
		super.onPause();
	}

	protected void onStart() {
		super.onStart();
	}

	protected void onResume() {
		super.onResume();
	}

	@SuppressLint("HandlerLeak")
	Handler responseHandler = new Handler() {
		public void handleMessage(Message msg) {
			fillListView(msg);
		};
	};

	public void fillListView(Message msg) {

		@SuppressWarnings({ "unchecked", "rawtypes" })
		ArrayList<Video> listVideos = (ArrayList) msg.getData().get(
				"LISTVIDEOS");

		videosListView.setAdapter(new AdapterVideo(getApplicationContext(),
				R.layout.entry_layout_videos, listVideos) {
			@Override
			public void onEntry(Object entry, View view) {

				TextView nomvideo = (TextView) view
						.findViewById(R.id.superiorTextView);

				nomvideo.setText(((Video) entry).getNom_video());
				TextView prix = (TextView) view.findViewById(R.id.prixvideo);

				prix.setText(((Video) entry).getPrix_video() + "dt");

				thumb = (ImageView) view.findViewById(R.id.imageViewE);

				String aux = Hotel.adresse_ip + "images/"
						+ ((Video) entry).getImage_video();

				Picasso.with(ctx).load(aux).into(thumb);

			}
		});
		videosListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> pariente, View view,
					int position, long id) {

				Video chosen = (Video) pariente.getItemAtPosition(position);

				String title = chosen.getNom_video();
				String path = chosen.getPath();
				video.setVideoPath(Hotel.adresse_ip + "video/" + path);
				nom = title;
				video.start();
				
					new ConsulVideo().execute();

			}
		});
	}

	class ConsulVideo extends AsyncTask<String, String, String> {

		protected String doInBackground(String... args) {

			String e_mail = ConnexionActivity.client.getE_mail();
			String nom_video = nom.toString();

			Log.d("nom_video", nom_video);
			Log.d("e_mail", e_mail);

			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("nom_video", nom_video));
			params.add(new BasicNameValuePair("e_mail", e_mail));

			JSONObject json = jsonParser.makeHttpRequest(url_vid, "POST",
					params);

			try {
				success = json.getInt(TAG_SUCCESS);
				Log.d("success", "" + success);
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