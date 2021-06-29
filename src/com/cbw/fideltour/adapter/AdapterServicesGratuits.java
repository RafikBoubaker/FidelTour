package com.cbw.fideltour.adapter;

import com.cbw.fideltour.entity.Hotel;
import com.cbw.fideltour.entity.ServiceGratuit;
import com.cbw.fideltour.R;
import com.squareup.picasso.Picasso;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class AdapterServicesGratuits extends ArrayAdapter<ServiceGratuit> {
	Context mContext;
	int layoutResourceId;
	ServiceGratuit data[] = null;
	ImageView imageservice;

	public AdapterServicesGratuits(Context mContext, int layoutResourceId,
			ServiceGratuit[] data) {

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
		ServiceGratuit objectItem = data[position];

		TextView text_nomservice = (TextView) convertView
				.findViewById(R.id.nomservice);
		TextView text_descriptionservice = (TextView) convertView
				.findViewById(R.id.descriptionservice);

		imageservice = (ImageView) convertView.findViewById(R.id.imageservice);
		text_nomservice.setText(objectItem.nom_service);
		text_descriptionservice.setText(objectItem.description_service);

		String url_img = Hotel.adresse_ip + "images/"
				+ objectItem.getLogo_service();
		Picasso.with(mContext).load(url_img).into(imageservice);

		return convertView;

	}
}