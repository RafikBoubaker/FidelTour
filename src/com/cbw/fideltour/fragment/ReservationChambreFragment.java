package com.cbw.fideltour.fragment;

import java.util.Calendar;
import com.actionbarsherlock.app.SherlockFragment;
import com.cbw.fideltour.R;
import com.cbw.fideltour.activity.MainActivity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.TextView;

public class ReservationChambreFragment extends SherlockFragment {

	protected static final FragmentManager FragmentManager = null;
	Button setdate1;
	Button setdate2;
	Button bsuivant;
	Dialog dialpicker1;
	Dialog dialpicker2;
	EditText selectdatepick1;
	EditText selectdatepick2;
	DatePicker datepick1;
	DatePicker datepick2;
	Integer month, day, year, nb1, nb2, nb3;
	public static Context context;
	CheckBox type1;
	CheckBox type2;
	CheckBox type3;
	String v1;
	String v2;
	String v3;
	String pension1;
	String pension2;
	String pension3;
	Integer mMonth, mDay, mYear;

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View root = inflater.inflate(R.layout.reservation_chambre_layout, container,
				false);

		selectdatepick1 = (EditText) root.findViewById(R.id.datearr);
		selectdatepick2 = (EditText) root.findViewById(R.id.datedep);

		selectdatepick1.setText(MainActivity.dtreserv.getDateArrivee());
		selectdatepick1.setOnClickListener(new View.OnClickListener() {
			@Override
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

									selectdatepick1.setText(year + "/"
											+ (monthOfYear + 1) + "/"
											+ dayOfMonth);

									selectdatepick1.setError(null);
									MainActivity.dtreserv
											.setDateArrivee(selectdatepick1
													.getText().toString());
								}
							}
						}, mYear, mMonth, mDay);
				dpd.show();
			}
		});

		selectdatepick2.setText(MainActivity.dtreserv.getDateDepart());
		selectdatepick2.setOnClickListener(new OnClickListener() {
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
									selectdatepick2.setText(year + "/"
											+ (monthOfYear + 1) + "/"
											+ dayOfMonth);

									selectdatepick2.setError(null);
									MainActivity.dtreserv
											.setDateDepart(selectdatepick2
													.getText().toString());
								}

							}
						}, mYear, mMonth, mDay);
				dpd.show();
			}
		});

		type1 = (CheckBox) root.findViewById(R.id.checkBoxTypech1);
		type2 = (CheckBox) root.findViewById(R.id.checkBoxTypech2);
		type3 = (CheckBox) root.findViewById(R.id.checkBoxTypech3);

		// **********
		// set type1
		// **********

		type1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {

				if (type1.isChecked()) {

					MainChambreFragment.infCh.setType_chambre1(type1.getText()
							.toString());

					AlertDialogWIndow1 m = new AlertDialogWIndow1()
							.newInstance();

					m.show(getFragmentManager(), "Alert_Dialog");

				}

			}
		});

		// **********
		// set type2
		// **********

		type2.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {

				if (type2.isChecked()) {
					MainChambreFragment.infCh.setType_chambre2(type2.getText()
							.toString());
					AlertDialogWIndow2 m = new AlertDialogWIndow2()
							.newInstance();

					m.show(getFragmentManager(), "Alert_Dialog");
				}
			}
		});
		// **********
		// set type3
		// **********

		type3.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {

				if (type3.isChecked()) {
					MainChambreFragment.infCh.setType_chambre3(type3.getText()
							.toString());
					AlertDialogWIndow3 m = new AlertDialogWIndow3()
							.newInstance();

					m.show(getFragmentManager(), "Alert_Dialog");
				}
			}
		});

		return root;

	}

	public static class AlertDialogWIndow1 extends DialogFragment {
		String nbpension1;
		String nbpension2;
		String nbpension3;
		String pension1;
		String pension2;
		String pension3;
		Spinner spinnernbch;
		String nbch;
		Spinner nbchpension1;
		Spinner nbchpension2;
		Spinner nbchpension3;
		CheckBox checkpension1;
		CheckBox checkpension2;
		CheckBox checkpension3;
		int nb;
		int nbp1;
		int nbp2;
		int nbp3;
		TextView error;
		public static Context context2;

		public AlertDialogWIndow1 newInstance() {
			AlertDialogWIndow1 frag = new AlertDialogWIndow1();
			return frag;
		}

		public Dialog onCreateDialog(Bundle savedInstanceState) {
			AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
			// Get the layout inflater
			final View promptsView = getActivity().getLayoutInflater().inflate(
					R.layout.alert_dialog_window1_layout, null);

			builder.setView(promptsView);
			checkpension1 = (CheckBox) promptsView
					.findViewById(R.id.option1window1);
			checkpension2 = (CheckBox) promptsView
					.findViewById(R.id.option2window1);
			checkpension3 = (CheckBox) promptsView
					.findViewById(R.id.option3window1);
			spinnernbch = (Spinner) promptsView
					.findViewById(R.id.spinnernwindow1);
			nbchpension1 = (Spinner) promptsView
					.findViewById(R.id.spinnertype1window1);
			nbchpension2 = (Spinner) promptsView
					.findViewById(R.id.spinnertype2window1);
			nbchpension3 = (Spinner) promptsView
					.findViewById(R.id.spinnertype3window1);
			Button v = (Button) promptsView.findViewById(R.id.buttonR1);
			error = (TextView) promptsView.findViewById(R.id.Errortype1);

			spinnernbch.setOnItemSelectedListener(new OnItemSelectedListener() {

				@Override
				public void onItemSelected(AdapterView<?> parent, View view,
						int pos, long id) {

					nbch = parent.getItemAtPosition(pos).toString();
					nb = Integer.parseInt(nbch);

				}

				@Override
				public void onNothingSelected(AdapterView<?> arg0) {

				}
			});

			checkpension1.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View arg0) {

					if (checkpension1.isChecked()) {

						pension1 = checkpension1.getText().toString();

					} else {
						nbp1 = 0;
					}

				}
			});
			nbchpension1
					.setOnItemSelectedListener(new OnItemSelectedListener() {

						@Override
						public void onItemSelected(AdapterView<?> parent,
								View view, int pos, long id) {

							nbpension1 = parent.getItemAtPosition(pos)
									.toString();
							nbp1 = Integer.parseInt(nbpension1);

						}

						@Override
						public void onNothingSelected(AdapterView<?> arg0) {

						}
					});

			checkpension2.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View arg0) {

					if (checkpension2.isChecked()) {

						pension2 = checkpension2.getText().toString();

					} else {
						nbp2 = 0;
					}

				}
			});
			nbchpension2
					.setOnItemSelectedListener(new OnItemSelectedListener() {

						@Override
						public void onItemSelected(AdapterView<?> parent,
								View view, int pos, long id) {

							nbpension2 = parent.getItemAtPosition(pos)
									.toString();
							nbp2 = Integer.parseInt(nbpension2);

						}

						@Override
						public void onNothingSelected(AdapterView<?> arg0) {

						}
					});

			checkpension3.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View arg0) {

					if (checkpension3.isChecked()) {

						pension3 = checkpension3.getText().toString();

					} else {
						nbp3 = 0;
					}

				}
			});
			nbchpension3
					.setOnItemSelectedListener(new OnItemSelectedListener() {

						@Override
						public void onItemSelected(AdapterView<?> parent,
								View view, int pos, long id) {

							nbpension3 = parent.getItemAtPosition(pos)
									.toString();
							nbp3 = Integer.parseInt(nbpension3);

						}

						@Override
						public void onNothingSelected(AdapterView<?> arg0) {

						}
					});

			final Dialog dialog = builder.create();
			v.setOnClickListener(new View.OnClickListener() {

				public void onClick(View view) {

					if (nb != nbp1 + nbp2 + nbp3) {
						error.setText("Vérifiez nombre Chambre !");

					} else {
						error.setText("");
						MainChambreFragment.infCh.setPension1_type1(pension1);
						MainChambreFragment.infCh.setPension2_type1(pension2);
						MainChambreFragment.infCh.setPension3_type1(pension3);
						MainChambreFragment.infCh.setNbreCh_pension1_type1(nbpension1);
						MainChambreFragment.infCh.setNbreCh_pension2_type1(nbpension2);
						MainChambreFragment.infCh.setNbreCh_pension3_type1(nbpension3);
						dialog.dismiss();
					}
				}
			});

			return dialog;

		}
	}

	public static class AlertDialogWIndow2 extends DialogFragment {
		String nbpension1;
		String nbpension2;
		String nbpension3;
		String pension1;
		String pension2;
		String pension3;
		Spinner spinnernbch;
		String nbch;
		Spinner nbchpension1;
		Spinner nbchpension2;
		Spinner nbchpension3;
		CheckBox checkpension1;
		CheckBox checkpension2;
		CheckBox checkpension3;
		int nb;
		int nbp1;
		int nbp2;
		int nbp3;
		TextView error;

		public AlertDialogWIndow2 newInstance() {
			AlertDialogWIndow2 frag = new AlertDialogWIndow2();
			return frag;
		}

		@Override
		public Dialog onCreateDialog(Bundle savedInstanceState) {
			AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

			final View promptsView = getActivity().getLayoutInflater().inflate(
					R.layout.alert_dialog_window2_layout, null);

			builder.setView(promptsView);
			checkpension1 = (CheckBox) promptsView
					.findViewById(R.id.option1window2);
			checkpension2 = (CheckBox) promptsView
					.findViewById(R.id.option2window2);
			checkpension3 = (CheckBox) promptsView
					.findViewById(R.id.option3window2);
			spinnernbch = (Spinner) promptsView
					.findViewById(R.id.spinnernwindow2);
			nbchpension1 = (Spinner) promptsView
					.findViewById(R.id.spinnertype1window2);
			nbchpension2 = (Spinner) promptsView
					.findViewById(R.id.spinnertype2window2);
			nbchpension3 = (Spinner) promptsView
					.findViewById(R.id.spinnertype3window2);
			Button v = (Button) promptsView.findViewById(R.id.buttonR2);
			error = (TextView) promptsView.findViewById(R.id.Errortype2);

			spinnernbch.setOnItemSelectedListener(new OnItemSelectedListener() {

				@Override
				public void onItemSelected(AdapterView<?> parent, View view,
						int pos, long id) {

					nbch = parent.getItemAtPosition(pos).toString();
					nb = Integer.parseInt(nbch);

				}

				@Override
				public void onNothingSelected(AdapterView<?> arg0) {

				}
			});

			checkpension1.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View arg0) {

					if (checkpension1.isChecked()) {

						pension1 = checkpension1.getText().toString();

					} else {
						nbp1 = 0;
					}

				}
			});
			nbchpension1
					.setOnItemSelectedListener(new OnItemSelectedListener() {

						@Override
						public void onItemSelected(AdapterView<?> parent,
								View view, int pos, long id) {

							nbpension1 = parent.getItemAtPosition(pos)
									.toString();
							nbp1 = Integer.parseInt(nbpension1);

						}

						@Override
						public void onNothingSelected(AdapterView<?> arg0) {

						}
					});

			checkpension2.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View arg0) {

					if (checkpension2.isChecked()) {

						pension2 = checkpension2.getText().toString();

					} else {
						nbp2 = 0;
					}

				}
			});

			nbchpension2
					.setOnItemSelectedListener(new OnItemSelectedListener() {

						@Override
						public void onItemSelected(AdapterView<?> parent,
								View view, int pos, long id) {

							nbpension2 = parent.getItemAtPosition(pos)
									.toString();
							nbp2 = Integer.parseInt(nbpension2);

						}

						@Override
						public void onNothingSelected(AdapterView<?> arg0) {

						}
					});

			checkpension3.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View arg0) {

					if (checkpension3.isChecked()) {

						pension3 = checkpension3.getText().toString();

					} else {
						nbp3 = 0;
					}

				}
			});

			nbchpension3
					.setOnItemSelectedListener(new OnItemSelectedListener() {

						@Override
						public void onItemSelected(AdapterView<?> parent,
								View view, int pos, long id) {

							nbpension3 = parent.getItemAtPosition(pos)
									.toString();
							nbp3 = Integer.parseInt(nbpension3);

						}

						@Override
						public void onNothingSelected(AdapterView<?> arg0) {

						}
					});

			final Dialog dialog = builder.create();
			v.setOnClickListener(new View.OnClickListener() {

				public void onClick(View view) {

					if (nb != nbp1 + nbp2 + nbp3) {
						error.setText("Vérifiez nombre Chambre !");

					} else {
						error.setText("");

						MainChambreFragment.infCh.setPension1_type2(pension1);
						MainChambreFragment.infCh.setPension2_type2(pension2);
						MainChambreFragment.infCh.setPension3_type2(pension3);
						MainChambreFragment.infCh.setNbreCh_pension1_type2(nbpension1);
						MainChambreFragment.infCh.setNbreCh_pension2_type2(nbpension2);
						MainChambreFragment.infCh.setNbreCh_pension3_type2(nbpension3);
						dialog.dismiss();
					}
				}
			});

			return dialog;

		}
	}

	public static class AlertDialogWIndow3 extends DialogFragment {
		String nbpension1;
		String nbpension2;
		String nbpension3;
		String pension1;
		String pension2;
		String pension3;
		Spinner spinnernbch;
		String nbch;
		Spinner nbchpension1;
		Spinner nbchpension2;
		Spinner nbchpension3;
		CheckBox checkpension1;
		CheckBox checkpension2;
		CheckBox checkpension3;
		int nb;
		int nbp1;
		int nbp2;
		int nbp3;
		TextView error;

		public AlertDialogWIndow3 newInstance() {
			AlertDialogWIndow3 frag = new AlertDialogWIndow3();
			return frag;
		}

		@Override
		public Dialog onCreateDialog(Bundle savedInstanceState) {
			AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

			final View promptsView = getActivity().getLayoutInflater().inflate(
					R.layout.alert_dialog_window3_layout, null);

			builder.setView(promptsView);
			checkpension1 = (CheckBox) promptsView
					.findViewById(R.id.option1window3);
			checkpension2 = (CheckBox) promptsView
					.findViewById(R.id.option2window3);
			checkpension3 = (CheckBox) promptsView
					.findViewById(R.id.option3window3);
			spinnernbch = (Spinner) promptsView
					.findViewById(R.id.spinnernwindow3);
			nbchpension1 = (Spinner) promptsView
					.findViewById(R.id.spinnertype1window3);
			nbchpension2 = (Spinner) promptsView
					.findViewById(R.id.spinnertype2window3);
			nbchpension3 = (Spinner) promptsView
					.findViewById(R.id.spinnertype3window3);
			Button v = (Button) promptsView.findViewById(R.id.buttonR3);
			error = (TextView) promptsView.findViewById(R.id.Errortype3);

			spinnernbch.setOnItemSelectedListener(new OnItemSelectedListener() {

				@Override
				public void onItemSelected(AdapterView<?> parent, View view,
						int pos, long id) {

					nbch = parent.getItemAtPosition(pos).toString();
					nb = Integer.parseInt(nbch);

				}

				@Override
				public void onNothingSelected(AdapterView<?> arg0) {

				}
			});

			checkpension1.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View arg0) {

					if (checkpension1.isChecked()) {

						pension1 = checkpension1.getText().toString();

					} else {
						nbp1 = 0;
					}

				}
			});

			nbchpension1
					.setOnItemSelectedListener(new OnItemSelectedListener() {

						@Override
						public void onItemSelected(AdapterView<?> parent,
								View view, int pos, long id) {

							nbpension1 = parent.getItemAtPosition(pos)
									.toString();
							nbp1 = Integer.parseInt(nbpension1);

						}

						@Override
						public void onNothingSelected(AdapterView<?> arg0) {

						}
					});

			checkpension2.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View arg0) {

					if (checkpension2.isChecked()) {

						pension2 = checkpension2.getText().toString();

					} else {
						nbp2 = 0;
					}

				}
			});

			nbchpension2
					.setOnItemSelectedListener(new OnItemSelectedListener() {

						@Override
						public void onItemSelected(AdapterView<?> parent,
								View view, int pos, long id) {

							nbpension2 = parent.getItemAtPosition(pos)
									.toString();
							nbp2 = Integer.parseInt(nbpension2);

						}

						@Override
						public void onNothingSelected(AdapterView<?> arg0) {

						}
					});

			checkpension3.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View arg0) {

					if (checkpension3.isChecked()) {

						pension3 = checkpension3.getText().toString();

					} else {
						nbp3 = 0;
					}

				}
			});

			nbchpension3
					.setOnItemSelectedListener(new OnItemSelectedListener() {

						@Override
						public void onItemSelected(AdapterView<?> parent,
								View view, int pos, long id) {

							nbpension3 = parent.getItemAtPosition(pos)
									.toString();
							nbp3 = Integer.parseInt(nbpension3);

						}

						@Override
						public void onNothingSelected(AdapterView<?> arg0) {

						}
					});

			final Dialog dialog = builder.create();
			v.setOnClickListener(new View.OnClickListener() {

				public void onClick(View view) {

					if (nb != nbp1 + nbp2 + nbp3) {
						error.setText("Vérifiez nombre Chambre !");

					} else {
						error.setText("");

						MainChambreFragment.infCh.setPension1_type3(pension1);
						MainChambreFragment.infCh.setPension2_type3(pension2);
						MainChambreFragment.infCh.setPension3_type3(pension3);
						MainChambreFragment.infCh.setNbreCh_pension1_type3(nbpension1);
						MainChambreFragment.infCh.setNbreCh_pension2_type3(nbpension2);
						MainChambreFragment.infCh.setNbreCh_pension3_type3(nbpension3);
						dialog.dismiss();
					}
				}
			});

			return dialog;

		}
	}
}