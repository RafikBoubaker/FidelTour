package com.cbw.fideltour.adapter;

import com.cbw.fideltour.R;
import com.cbw.fideltour.entity.Hotel;
import com.cbw.fideltour.entity.Salle;
import com.squareup.picasso.Picasso;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class AdapterTarifSalle extends ArrayAdapter<Salle> {
	Context mContext;
	int layoutResourceId;
	Salle data[] = null;
	ImageView imagesalle;

	public AdapterTarifSalle(Context mContext, int layoutResourceId,
			Salle[] data) {

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

		Salle objectItem = data[position];

		TextView text_typesalle = (TextView) convertView
				.findViewById(R.id.typesalle);
		TextView text_prixsalle = (TextView) convertView
				.findViewById(R.id.prixsalle);

		imagesalle = (ImageView) convertView.findViewById(R.id.image_salle);
		text_typesalle.setText(objectItem.type_salle);
		text_prixsalle.setText(objectItem.prix_salle+"dt");

		String url_img = Hotel.adresse_ip + "images/"
				+ objectItem.getImage_article();
		Picasso.with(mContext).load(url_img).into(imagesalle);

		return convertView;

	}

}