package com.cbw.fideltour.activity;

import java.util.Calendar;
import com.cbw.fideltour.R;
import com.cbw.fideltour.entity.Commande;
import android.app.Activity;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;

public class CommandeActivity extends Activity {
	EditText heure;
	Button btValider;
	static final int TIME_DIALOG_ID = 1111;
	public int hour, minute;
	public static Commande cmd;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		cmd = new Commande();
		super.onCreate(savedInstanceState);
		setContentView(R.layout.commande_layout);
		
		heure = (EditText) findViewById(R.id.heurecommande);
		
		final Calendar c = Calendar.getInstance();
		hour = c.get(Calendar.HOUR_OF_DAY);
		minute = c.get(Calendar.MINUTE);

		updateTimedeb1(hour, minute);
		heure.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				showDialogdeb1(TIME_DIALOG_ID).show();

			}
		});
		btValider = (Button) findViewById(R.id.buttonValiderCmd);
		btValider.setOnClickListener(new View.OnClickListener() {

			public void onClick(View view) {

				if (test()) {
					Intent i = new Intent(getApplicationContext(),
							MenuCommandeActivity.class);
					startActivity(i);
				}
			}
		});
	}

	protected Dialog showDialogdeb1(int id) {
		switch (id) {
		case TIME_DIALOG_ID:

			return new TimePickerDialog(CommandeActivity.this,
					timePickerListenerdeb1, hour, minute, false);

		}
		return null;
	}

	private TimePickerDialog.OnTimeSetListener timePickerListenerdeb1 = new TimePickerDialog.OnTimeSetListener() {

		@Override
		public void onTimeSet(TimePicker view, int hourOfDay, int minutes) {

			hour = hourOfDay;
			minute = minutes;

			updateTimedeb1(hour, minute);

		}

	};

	private void updateTimedeb1(int hours, int mins) {

		String minutes = "";
		if (mins < 10)
			minutes = "0" + mins;
		else
			minutes = String.valueOf(mins);

		String aTime = new StringBuilder().append(hours).append(':')
				.append(minutes).append(" ").toString();

		heure.setText(aTime);
		CommandeActivity.cmd.setHeure_cmd(heure.getText().toString());
	}

	public boolean test() {

		boolean test = true;

		if (heure.length() == 0) {
			heure.setError("champ obligatoire");
			test = false;
		}
	
		return test;

	}

}
