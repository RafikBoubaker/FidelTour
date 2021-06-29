package com.cbw.fideltour.adapter;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.cbw.fideltour.R;
import com.cbw.fideltour.entity.Chambre;
import com.cbw.fideltour.entity.Hotel;
import com.squareup.picasso.Picasso;

public class AdapterTarifChambre extends ArrayAdapter<Chambre> {
	Context mContext;
	int layoutResourceId;
	Chambre data[] = null;
	ImageView imagech;
	Handler mhandler;

	public AdapterTarifChambre(Context mContext, int layoutResourceId,
			Chambre[] data) {

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

		Chambre objectItem = data[position];

		TextView text_typech = (TextView) convertView.findViewById(R.id.typech);
		TextView text_prixch = (TextView) convertView.findViewById(R.id.prixch);

		imagech = (ImageView) convertView.findViewById(R.id.image_ch);
		Picasso.with(mContext).load("http://i.imgur.com/DvpvklR.png")
				.into(imagech);
		text_typech.setText(objectItem.type_chambre);
		text_prixch.setText(objectItem.prix_chambre + "dt");

		String url_img = Hotel.adresse_ip + "images/"
				+ objectItem.getImage_article();
		Picasso.with(mContext).load(url_img).into(imagech);

		return convertView;

	}

}