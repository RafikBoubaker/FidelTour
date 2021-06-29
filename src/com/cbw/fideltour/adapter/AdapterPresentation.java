package com.cbw.fideltour.adapter;

import java.util.List;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.cbw.fideltour.R;
import com.cbw.fideltour.entity.Hotel;
import com.squareup.picasso.Picasso;

public class AdapterPresentation extends ArrayAdapter<Hotel> {
	Context mContext;
	int layoutResourceId;
	Hotel data[] = null;
	String video_hotel;
	ImageView imageH;
	List<Hotel> hot;

	public AdapterPresentation(Context mContext, int layoutResourceId,
			Hotel[] data) {

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

		TextView text_nomH = (TextView) convertView.findViewById(R.id.textnomH);
		TextView text_descH = (TextView) convertView
				.findViewById(R.id.textdescriptionH);
		imageH = (ImageView) convertView.findViewById(R.id.imageH);

		text_nomH.setText(objectItem.nom_hotel);
		text_descH.setText(objectItem.description_hotel);

		String url_img = Hotel.adresse_ip + "video/"
				+ objectItem.getVideo_hotel();
		Picasso.with(mContext).load(url_img).into(imageH);

		return convertView;
	}
}
