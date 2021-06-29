package com.cbw.fideltour.activity;

import com.cbw.fideltour.R;
import com.cbw.fideltour.map.MapContactActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ContactActivity extends Activity {
	Button boutonSendE;
	EditText inputNom;
	EditText inputEmail;
	EditText inputRenseignement;
	TextView textView3;
	Button btnAnnuler;
	Button startBtn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.contact_layout);

		inputNom = (EditText) findViewById(R.id.editNom);
		inputEmail = (EditText) findViewById(R.id.editMail);
		inputRenseignement = (EditText) findViewById(R.id.editRenseignement);
		btnAnnuler = (Button) findViewById(R.id.ButtonAnnuler);
		startBtn = (Button) findViewById(R.id.ButtonSend);

		startBtn.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {

				sendrenseignement();

			}
		});

		btnAnnuler.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				inputNom.setText("");
				inputEmail.setText("");
				inputRenseignement.setText("");
			}
		});

		textView3 = (TextView) findViewById(R.id.textView3);
		textView3.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				Intent callIntent = new Intent(Intent.ACTION_CALL);
				callIntent.setData(Uri.parse("tel:25233327"));
				startActivity(callIntent);
			}
		});

	}

	public void sendrenseignement() {

		String subject = "Renseignement";
		String message = inputRenseignement.getText().toString();
		String to = "wiem.jannadi@gmail.com";
		//String cc = inputEmail.getText().toString();
		Intent email = new Intent(Intent.ACTION_SEND);
		email.putExtra(Intent.EXTRA_EMAIL, new String[]{ to});
		//email.putExtra(Intent.EXTRA_EMAIL, new String[] { cc });
		email.putExtra(Intent.EXTRA_SUBJECT, subject);
		email.putExtra(Intent.EXTRA_TEXT, message);

		email.setType("message/rfc822");
		startActivity(Intent.createChooser(email, "Send mail..."));

		Log.i("Finished sending email...", "");

	}

	public boolean test() {

		boolean test = true;

		if (inputNom.length() == 0) {
			inputNom.setError("champ obligatoire");
			test = false;
		}
		if (inputEmail.length() == 0) {
			inputEmail.setError("champ obligatoire");
			test = false;
		}
		if (inputRenseignement.length() == 0) {
			inputRenseignement.setError("champ obligatoire");
			test = false;
		}
		return test;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.action_map:
			Intent intent2 = new Intent(ContactActivity.this,
					MapContactActivity.class);
			startActivity(intent2);
			return true;
		case R.id.action_fb:
			String url = "https://www.facebook.com/lesambassadeurshotel?fref=ts";
			Intent i = new Intent(Intent.ACTION_VIEW);
			i.setData(Uri.parse(url));
			startActivity(i);
			return true;
		case R.id.action_tw:
			String urlt = "https://twitter.com/AmbassadHotel";
			Intent t = new Intent(Intent.ACTION_VIEW);
			t.setData(Uri.parse(urlt));
			startActivity(t);
			return true;
		case R.id.action_yt:
			String urlyt = "https://www.youtube.com/ambassadeurshotel";
			Intent e = new Intent(Intent.ACTION_VIEW);
			e.setData(Uri.parse(urlyt));
			startActivity(e);
			return true;
		case R.id.action_gm:
			String urlg = "https://plus.google.com/u/0/111173323337952303989/posts";
			Intent g = new Intent(Intent.ACTION_VIEW);
			g.setData(Uri.parse(urlg));
			startActivity(g);
			return true;

		}

		return super.onOptionsItemSelected(item);
	}

	public boolean onCreateOptionsMenu(Menu menu) {

		getMenuInflater().inflate(R.menu.main1, menu);
		return true;
	}
}
