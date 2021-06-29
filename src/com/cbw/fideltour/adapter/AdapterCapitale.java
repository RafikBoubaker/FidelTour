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

public class AdapterCapitale extends ArrayAdapter<ArticleGuide> {
	Context mContext;
	int layoutResourceId;
	ArticleGuide data[] = null;
	ImageView imagecafe;

	public AdapterCapitale(Context mContext, int layoutResourceId,
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

		TextView text_nomcafe = (TextView) convertView
				.findViewById(R.id.nomcafe);
		TextView text_descriptioncafe = (TextView) convertView
				.findViewById(R.id.descriptioncafe);

		imagecafe = (ImageView) convertView.findViewById(R.id.image_cafe);
		text_nomcafe.setText(objectItem.nom_article_guide);

		text_descriptioncafe.setText(objectItem.description_article_guide);

		String url_img = Hotel.adresse_ip + "images/"
				+ objectItem.getImage_article_guide();
		Picasso.with(mContext).load(url_img).into(imagecafe);

		return convertView;

	}
}
