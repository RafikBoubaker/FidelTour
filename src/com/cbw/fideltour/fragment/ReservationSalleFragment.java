package com.cbw.fideltour.fragment;

import java.util.Calendar;
import com.actionbarsherlock.app.SherlockFragment;
import com.cbw.fideltour.R;
import com.cbw.fideltour.activity.MainActivity;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TimePicker;

public class ReservationSalleFragment extends SherlockFragment {

	EditText heuredebtype1;
	EditText heurefintype1;
	EditText heuredebtype2;
	EditText heurefintype2;
	EditText heuredebtype3;
	EditText heurefintype3;
	EditText joureserv;
	CheckBox types1;
	CheckBox types2;
	CheckBox types3;
	Dialog timepick;
	TimePicker time1;
	Button set1;

	static final int TIME_DIALOG_ID = 1111;
	public int hour, minute;

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View root = inflater.inflate(R.layout.reservation_salle_layout,
				container, false);

		joureserv = (EditText) root.findViewById(R.id.jourReserv);
		heuredebtype1 = (EditText) root.findViewById(R.id.heuredeb1);
		heurefintype1 = (EditText) root.findViewById(R.id.heurefin1);
		heuredebtype2 = (EditText) root.findViewById(R.id.heuredeb2);
		heurefintype2 = (EditText) root.findViewById(R.id.heurefin2);
		heuredebtype3 = (EditText) root.findViewById(R.id.heuredeb3);
		heurefintype3 = (EditText) root.findViewById(R.id.heurefin3);
		types1 = (CheckBox) root.findViewById(R.id.checkBoxTypeSalle1);
		types2 = (CheckBox) root.findViewById(R.id.checkBoxTypeSalle2);
		types3 = (CheckBox) root.findViewById(R.id.checkBoxTypeSalle3);

		types1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {

				if (types1.isChecked()) {

					MainSalleFragment.infSl.setType_salle1(types1.getText().toString());
				}
			}
		});

		types2.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {

				if (types2.isChecked()) {

					MainSalleFragment.infSl.setType_salle2(types2.getText().toString());
				}
			}
		});

		types3.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {

				if (types3.isChecked()) {

					MainSalleFragment.infSl.setType_salle3(types3.getText().toString());
				}
			}
		});

		joureserv.setText(MainActivity.dtreserv.getJour_reservation_salle());
		final Calendar c = Calendar.getInstance();
		hour = c.get(Calendar.HOUR_OF_DAY);
		minute = c.get(Calendar.MINUTE);

		updateTimedeb1(hour, minute);
		updateTimefin1(hour, minute);
		updateTimedeb2(hour, minute);
		updateTimefin2(hour, minute);
		updateTimedeb3(hour, minute);
		updateTimefin3(hour, minute);

		heuredebtype1.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				showDialogdeb1(TIME_DIALOG_ID).show();

			}
		});
		heurefintype1.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				showDialogfin1(TIME_DIALOG_ID).show();

			}
		});

		heuredebtype2.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				showDialogdeb2(TIME_DIALOG_ID).show();

			}
		});
		heurefintype2.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				showDialogfin2(TIME_DIALOG_ID).show();

			}
		});
		heuredebtype3.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				showDialogdeb3(TIME_DIALOG_ID).show();

			}
		});
		heurefintype3.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				showDialogfin3(TIME_DIALOG_ID).show();

			}
		});
		return root;

	}

	protected Dialog showDialogdeb1(int id) {
		switch (id) {
		case TIME_DIALOG_ID:

			return new TimePickerDialog(getActivity(), timePickerListenerdeb1,
					hour, minute, false);

		}
		return null;
	}

	protected Dialog showDialogfin1(int id) {
		switch (id) {
		case TIME_DIALOG_ID:

			return new TimePickerDialog(getActivity(), timePickerListenerfin1,
					hour, minute, false);

		}
		return null;
	}

	protected Dialog showDialogdeb2(int id) {
		switch (id) {
		case TIME_DIALOG_ID:

			return new TimePickerDialog(getActivity(), timePickerListenerdeb2,
					hour, minute, false);

		}
		return null;
	}

	protected Dialog showDialogfin2(int id) {
		switch (id) {
		case TIME_DIALOG_ID:

			return new TimePickerDialog(getActivity(), timePickerListenerfin2,
					hour, minute, false);

		}
		return null;
	}

	protected Dialog showDialogdeb3(int id) {
		switch (id) {
		case TIME_DIALOG_ID:

			return new TimePickerDialog(getActivity(), timePickerListenerdeb3,
					hour, minute, false);

		}
		return null;
	}

	protected Dialog showDialogfin3(int id) {
		switch (id) {
		case TIME_DIALOG_ID:

			return new TimePickerDialog(getActivity(), timePickerListenerfin3,
					hour, minute, false);

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
	private TimePickerDialog.OnTimeSetListener timePickerListenerfin1 = new TimePickerDialog.OnTimeSetListener() {

		@Override
		public void onTimeSet(TimePicker view, int hourOfDay, int minutes) {

			hour = hourOfDay;
			minute = minutes;

			updateTimefin1(hour, minute);

		}

	};
	private TimePickerDialog.OnTimeSetListener timePickerListenerdeb2 = new TimePickerDialog.OnTimeSetListener() {

		@Override
		public void onTimeSet(TimePicker view, int hourOfDay, int minutes) {

			hour = hourOfDay;
			minute = minutes;

			updateTimedeb2(hour, minute);

		}

	};
	private TimePickerDialog.OnTimeSetListener timePickerListenerfin2 = new TimePickerDialog.OnTimeSetListener() {

		@Override
		public void onTimeSet(TimePicker view, int hourOfDay, int minutes) {

			hour = hourOfDay;
			minute = minutes;

			updateTimefin2(hour, minute);

		}

	};
	private TimePickerDialog.OnTimeSetListener timePickerListenerdeb3 = new TimePickerDialog.OnTimeSetListener() {

		@Override
		public void onTimeSet(TimePicker view, int hourOfDay, int minutes) {

			hour = hourOfDay;
			minute = minutes;

			updateTimedeb3(hour, minute);

		}

	};
	private TimePickerDialog.OnTimeSetListener timePickerListenerfin3 = new TimePickerDialog.OnTimeSetListener() {

		@Override
		public void onTimeSet(TimePicker view, int hourOfDay, int minutes) {

			hour = hourOfDay;
			minute = minutes;

			updateTimefin3(hour, minute);

		}

	};

	@SuppressWarnings("unused")
	private static String utilTime(int value) {

		if (value < 10)
			return "0" + String.valueOf(value);
		else
			return String.valueOf(value);
	}

	private void updateTimedeb1(int hours, int mins) {

		String minutes = "";
		if (mins < 10)
			minutes = "0" + mins;
		else
			minutes = String.valueOf(mins);

		String aTime = new StringBuilder().append(hours).append(':')
				.append(minutes).append(" ").toString();

		heuredebtype1.setText(aTime);
		MainSalleFragment.infSl.setHeure_debut1(heuredebtype1.getText().toString());
	}

	private void updateTimefin1(int hours, int mins) {

		String minutes = "";
		if (mins < 10)
			minutes = "0" + mins;
		else
			minutes = String.valueOf(mins);

		String aTime = new StringBuilder().append(hours).append(':')
				.append(minutes).append(" ").toString();

		heurefintype1.setText(aTime);
		MainSalleFragment.infSl.setHeure_fin1(heurefintype1.getText().toString());
	}

	private void updateTimedeb2(int hours, int mins) {

		String minutes = "";
		if (mins < 10)
			minutes = "0" + mins;
		else
			minutes = String.valueOf(mins);

		String aTime = new StringBuilder().append(hours).append(':')
				.append(minutes).append(" ").toString();

		heuredebtype2.setText(aTime);
		MainSalleFragment.infSl.setHeure_debut2(heuredebtype2.getText().toString());
	}

	private void updateTimefin2(int hours, int mins) {

		String minutes = "";
		if (mins < 10)
			minutes = "0" + mins;
		else
			minutes = String.valueOf(mins);

		String aTime = new StringBuilder().append(hours).append(':')
				.append(minutes).append(" ").toString();

		heurefintype2.setText(aTime);
		MainSalleFragment.infSl.setHeure_fin2(heurefintype2.getText().toString());
	}

	private void updateTimedeb3(int hours, int mins) {

		String minutes = "";
		if (mins < 10)
			minutes = "0" + mins;
		else
			minutes = String.valueOf(mins);
		String aTime = new StringBuilder().append(hours).append(':')
				.append(minutes).append(" ").toString();

		heuredebtype3.setText(aTime);
		MainSalleFragment.infSl.setHeure_debut3(heuredebtype3.getText().toString());
	}

	private void updateTimefin3(int hours, int mins) {

		String minutes = "";
		if (mins < 10)
			minutes = "0" + mins;
		else
			minutes = String.valueOf(mins);

		String aTime = new StringBuilder().append(hours).append(':')
				.append(minutes).append(" ").toString();

		heurefintype3.setText(aTime);
		MainSalleFragment.infSl.setHeure_fin3(heurefintype3.getText().toString());
	}
}
