package com.cbw.fideltour.adapter;

import com.cbw.fideltour.R;
import com.cbw.fideltour.entity.ArticleGuide;
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

public class AdapterCulture extends ArrayAdapter<ArticleGuide> {
	Context mContext;
	int layoutResourceId;
	ArticleGuide data[] = null;
	ImageView imagetheatre;

	public AdapterCulture(Context mContext, int layoutResourceId,
			ArticleGuide[] data) {

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

		ArticleGuide objectItem = data[position];

		TextView text_nomtheatre = (TextView) convertView
				.findViewById(R.id.nomtheatre);
		TextView text_descriptiontheatre = (TextView) convertView
				.findViewById(R.id.descriptiontheatre);

		imagetheatre = (ImageView) convertView.findViewById(R.id.image_theatre);
		text_nomtheatre.setText(objectItem.nom_article_guide);
		text_descriptiontheatre.setText(objectItem.description_article_guide);

		String url_img = Hotel.adresse_ip + "images/"
				+ objectItem.getImage_article_guide();
		Picasso.with(mContext).load(url_img).into(imagetheatre);

		return convertView;

	}
}
