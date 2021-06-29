package com.cbw.fideltour.adapter;

import com.cbw.fideltour.R;
import com.cbw.fideltour.entity.Hotel;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class AdapterPolitique extends ArrayAdapter<Hotel> {
	Context mContext;
	int layoutResourceId;
	Hotel data[] = null;

	public AdapterPolitique(Context mContext, int layoutResourceId, Hotel[] data) {

		super(mContext, layoutResourceId, data);

		this.layoutResourceId = layoutResourceId;
		this.mContext = mContext;
		this.data = data;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		if (convertView == null) {

			LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
			convertView = inflater.inflate(layoutResourceId, parent, false);
		}

		Hotel objectItem = data[position];

		TextView text_politique = (TextView) convertView
				.findViewById(R.id.politique);
		text_politique.setText(objectItem.politique_de_confidentialite);

		return convertView;
	}

}