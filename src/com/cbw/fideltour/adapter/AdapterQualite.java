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

public class AdapterQualite extends ArrayAdapter<Hotel> {
	Context mContext;
	int layoutResourceId;
	Hotel data[] = null;

	public AdapterQualite(Context mContext, int layoutResourceId, Hotel[] data) {

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

		TextView text_qualite = (TextView) convertView
				.findViewById(R.id.qualite);
		text_qualite.setText(objectItem.qualite);

		return convertView;
	}

}
