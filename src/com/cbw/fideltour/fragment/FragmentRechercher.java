package com.cbw.fideltour.fragment;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import com.cbw.fideltour.R;
import com.cbw.fideltour.activity.MainActivity;
import com.cbw.fideltour.activity.MainActivityReservationChambreDrawer;
import com.cbw.fideltour.activity.MainActivityReservationSalleDrawer;
import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;

public class FragmentRechercher extends Fragment {

	Button chercher;

	EditText selectdate1;
	EditText selectdate2;
	EditText selectdate3;

	String champdate1;
	String champdate2;
	String champdate3;
	Integer mMonth, mDay, mYear;
	RadioButton radioChambre;
	RadioButton radioSalle;
	public Context context;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View V = inflater.inflate(R.layout.fragment_rechercher_layout,
				container, false);

		selectdate1 = (EditText) V.findViewById(R.id.bdateArrivee);
		selectdate2 = (EditText) V.findViewById(R.id.bdateDepart);
		selectdate3 = (EditText) V.findViewById(R.id.bjourReserv);
		radioChambre = (RadioButton) V.findViewById(R.id.radioButtonChambre);
		radioSalle = (RadioButton) V.findViewById(R.id.radioButtonSalle);
		chercher = (Button) V.findViewById(R.id.btnSubmit);

		// ***********
		// Date d'arrivée
		// ***********

		selectdate1.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				final Calendar c = Calendar.getInstance();
				mYear = c.get(Calendar.YEAR);
				mMonth = c.get(Calendar.MONTH);
				mDay = c.get(Calendar.DAY_OF_MONTH);
				DatePickerDialog dpd = new DatePickerDialog(getActivity(),
						new DatePickerDialog.OnDateSetListener() {
							@Override
							public void onDateSet(DatePicker view, int year,
									int monthOfYear, int dayOfMonth) {
								if (mYear > year || mMonth > monthOfYear
										&& year == mYear || mDay > dayOfMonth
										&& year == mYear
										&& monthOfYear == mMonth) {

									view.updateDate(mYear, mMonth, mDay);

								}

								else {

									// Display Selected date in textbox
									selectdate1.setText(year + "/"
											+ (monthOfYear + 1) + "/"
											+ dayOfMonth);
									champdate1 = selectdate1.getText()
											.toString();
									selectdate1.setError(null);
									MainActivity.dtreserv
											.setDateArrivee(champdate1);
								}
							}
						}, mYear, mMonth, mDay);
				dpd.show();
			}
		});

		// ***********
		// Date depart
		// ***********
		selectdate2.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				final Calendar c = Calendar.getInstance();
				mYear = c.get(Calendar.YEAR);
				mMonth = c.get(Calendar.MONTH);
				mDay = c.get(Calendar.DAY_OF_MONTH);
				DatePickerDialog dpd = new DatePickerDialog(getActivity(),
						new DatePickerDialog.OnDateSetListener() {
							@Override
							public void onDateSet(DatePicker view, int year,
									int monthOfYear, int dayOfMonth) {
								if (mYear > year || mMonth > monthOfYear
										&& year == mYear || mDay > dayOfMonth
										&& year == mYear
										&& monthOfYear == mMonth) {

									view.updateDate(mYear, mMonth, mDay);

								}

								else {
									selectdate2.setText(year + "/"
											+ (monthOfYear + 1) + "/"
											+ dayOfMonth);
									champdate2 = selectdate2.getText()
											.toString();
									selectdate2.setError(null);
									MainActivity.dtreserv
											.setDateDepart(champdate2);
								}

							}
						}, mYear, mMonth, mDay);
				dpd.show();
			}
		});

		// ***********
		// Jour reservation salle
		// ***********

		selectdate3.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {

				final Calendar c = Calendar.getInstance();
				mYear = c.get(Calendar.YEAR);
				mMonth = c.get(Calendar.MONTH);
				mDay = c.get(Calendar.DAY_OF_MONTH);

				DatePickerDialog dpd = new DatePickerDialog(getActivity(),
						new DatePickerDialog.OnDateSetListener() {
							@Override
							public void onDateSet(DatePicker view, int year,
									int monthOfYear, int dayOfMonth) {
								if (mYear > year || mMonth > monthOfYear
										&& year == mYear || mDay > dayOfMonth
										&& year == mYear
										&& monthOfYear == mMonth) {

									view.updateDate(mYear, mMonth, mDay);

								}

								else {
									selectdate3.setText(year + "/"
											+ (monthOfYear + 1) + "/"
											+ dayOfMonth);
									champdate3 = selectdate3.getText()
											.toString();
									selectdate3.setError(null);
									MainActivity.dtreserv
											.setJour_reservation_salle(champdate3);
								}

							}
						}, mYear, mMonth, mDay);
				dpd.show();
			}
		});

		chercher.setOnClickListener(new View.OnClickListener() {

			public void onClick(View view) {

				// Intent i = new Intent(getActivity(),
				// MainActivityReservationSalle.class);
				// startActivity(i);
				// Toast.makeText(getActivity(),
				// MainActivity.dateRech.toString(), Toast.LENGTH_SHORT).show();

				if (radioChambre.isChecked()) {
					try {
						if (test1()) {
							Intent i1 = new Intent(getActivity(),
									MainActivityReservationChambreDrawer.class);
							startActivity(i1);
						}
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
				if (radioSalle.isChecked()) {
					if (test2()) {
						Intent i2 = new Intent(getActivity(),
								MainActivityReservationSalleDrawer.class);
						startActivity(i2);
					}
				}

			}

		});

		return V;
	}

	@SuppressLint("SimpleDateFormat")
	public boolean test1() throws ParseException {

		boolean test = true;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		Date date1 = sdf.parse(selectdate1.getText().toString());
		Date date2 = sdf.parse(selectdate2.getText().toString());
		if (selectdate1.length() == 0) {
			selectdate1.setError("Invalid date");
			test = false;
		}

		if (selectdate2.length() == 0) {
			selectdate2.setError("Invalid date");
			test = false;
		}
		if (date2.compareTo(date1) <= 0) {
			test = false;
			selectdate1.setError("Invalid date");
			selectdate2.setError("Invalid date");
		} else if (date2.compareTo(date1) > 0) {
			selectdate2.setError(null);
		}
		return test;

	}

	public boolean test2() {

		boolean test = true;

		if (selectdate3.length() == 0) {
			selectdate3.setError("Invalid date");
			test = false;
		}

		return test;
	}

}