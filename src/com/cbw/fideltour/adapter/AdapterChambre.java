package com.cbw.fideltour.adapter;

import com.cbw.fideltour.R;
import com.cbw.fideltour.entity.Article;
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

public class AdapterChambre extends ArrayAdapter<Article> {

	Context mContext;
	int layoutResourceId;
	Article data[] = null;
	ImageView imageC;

	public AdapterChambre(Context mContext, int layoutResourceId, Article[] data) {

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

		Article objectItemC = data[position];

		TextView text_nomC = (TextView) convertView.findViewById(R.id.textnomC);
		TextView text_descC = (TextView) convertView
				.findViewById(R.id.textdescriptionC);
		imageC = (ImageView) convertView.findViewById(R.id.imageC);

		text_nomC.setText(objectItemC.nom_article);
		text_descC.setText(objectItemC.description_article);
		String urlim = Hotel.adresse_ip + "images/"
				+ objectItemC.getImage_article();
		Picasso.with(mContext).load(urlim).into(imageC);

		return convertView;
	}
}
