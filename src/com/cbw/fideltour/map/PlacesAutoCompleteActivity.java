package com.cbw.fideltour.map;

import com.cbw.fideltour.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class PlacesAutoCompleteActivity extends Activity implements
		OnItemClickListener, OnClickListener {
	AutoCompleteTextView from;
	AutoCompleteTextView to;
	String PLACES_API_BASE;
	String TYPE_AUTOCOMPLETE;
	String OUT_JSON;
	String PLACES_API_KEY;
	Button load_btn;
	TextView to_view,from_view;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.directioninput);

		load_btn= (Button) findViewById(R.id.load_directions);
		load_btn.setOnClickListener(this);
		
		to_view = (TextView) findViewById(R.id.to_label);
		from_view = (TextView) findViewById(R.id.from_label);
		
		from = (AutoCompleteTextView) findViewById(R.id.from);
		to = (AutoCompleteTextView) findViewById(R.id.to);

		from.setText(" ");
		to.setText(" ");

		from.setAdapter(new PlacesAutoCompleteAdapter(this,
				android.R.layout.simple_dropdown_item_1line));
		to.setAdapter(new PlacesAutoCompleteAdapter(this,
				android.R.layout.simple_dropdown_item_1line));
		from.setOnItemClickListener(this);
		to.setOnItemClickListener(this);

	}

	public void onItemClick(AdapterView<?> adapterView, View view,
			int position, long id) {
		String str = (String) adapterView.getItemAtPosition(position);
		Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
		case R.id.load_directions:
			Intent i = new Intent(getApplicationContext(), MainActivityMap.class);
			i.putExtra("origin", from.getText().toString());
			i.putExtra("destination", to.getText().toString());
			
			startActivity(i);
			finish();
			break;

		default:
			break;
		}
	}

}
