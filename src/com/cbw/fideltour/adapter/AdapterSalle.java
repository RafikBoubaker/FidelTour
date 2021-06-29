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

public class AdapterSalle extends ArrayAdapter<Article> {

	Context mContext;
	int layoutResourceId;
	Article data[] = null;
	ImageView imageS;

	public AdapterSalle(Context mContext, int layoutResourceId, Article[] data) {

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

		Article objectItem = data[position];

		TextView text_nomS = (TextView) convertView.findViewById(R.id.textnomS);
		TextView text_descS = (TextView) convertView
				.findViewById(R.id.textdescriptionS);
		imageS = (ImageView) convertView.findViewById(R.id.imageS);
		text_nomS.setText(objectItem.nom_article);
		text_descS.setText(objectItem.description_article);
		String url_img = Hotel.adresse_ip + "images/"
				+ objectItem.getImage_article();
		Picasso.with(mContext).load(url_img).into(imageS);

		return convertView;

	}

}
