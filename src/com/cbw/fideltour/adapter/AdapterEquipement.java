package com.cbw.fideltour.adapter;

import com.cbw.fideltour.R;
import com.cbw.fideltour.entity.Equipement;
import com.cbw.fideltour.entity.Hotel;
import com.squareup.picasso.Picasso;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class AdapterEquipement extends ArrayAdapter<Equipement> {
	Context mContext;
	int layoutResourceId;
	Equipement data[] = null;
	ImageView imageequipement;

	public AdapterEquipement(Context mContext, int layoutResourceId,
			Equipement[] data) {

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

		Equipement objectItem = data[position];

		TextView text_nomequipement = (TextView) convertView
				.findViewById(R.id.nomequipement);
		TextView text_descriptionequipement = (TextView) convertView
				.findViewById(R.id.descriptionequipement);

		imageequipement = (ImageView) convertView
				.findViewById(R.id.image_equipement);
		text_nomequipement.setText(objectItem.type_equipement);
		text_descriptionequipement.setText(objectItem.description_equipement);

		String url_img = Hotel.adresse_ip + "images/"
				+ objectItem.getLogo_equipement();
		Picasso.with(mContext).load(url_img).into(imageequipement);
		return convertView;

	}

}
