package com.cbw.fideltour.activity;

import com.cbw.fideltour.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class InviterPersonneActivity extends Activity {
	EditText dest;
	EditText invit;
	Button inviter;
	Button annuler;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.inviter_personne_layout);

		dest = (EditText) findViewById(R.id.editcc);
		invit = (EditText) findViewById(R.id.editmail);
		inviter = (Button) findViewById(R.id.buttoninviter);
		annuler = (Button) findViewById(R.id.Buttonannuler);

		annuler.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				dest.setText("");
				invit.setText("");
			}
		});

		inviter.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {

				sendInvitation();

			}
		});

	}

	public void sendInvitation() {
		String message = invit.getText().toString();
		String to = dest.getText().toString();

		Intent email = new Intent(Intent.ACTION_SEND);
		email.putExtra(Intent.EXTRA_EMAIL, new String[] { to });
		email.putExtra(Intent.EXTRA_TEXT, message);
		email.setType("message/rfc822");
		startActivity(Intent.createChooser(email, "Send mail..."));
		Log.i("Finished sending email...", "");

	}

	public boolean test() {

		boolean test = true;

		if (dest.length() == 0) {
			dest.setError("champ obligatoire");
			test = false;
		}
		if (invit.length() == 0) {
			invit.setError("champ obligatoire");
			test = false;
		}
		return test;
	}

}
