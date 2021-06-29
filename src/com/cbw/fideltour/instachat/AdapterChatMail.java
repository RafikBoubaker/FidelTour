package com.cbw.fideltour.instachat;



import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.cbw.fideltour.R;


public class AdapterChatMail extends ArrayAdapter<ChatMail> {
	Context mContext;
	int layoutResourceId;
	ChatMail data[] = null;

	public AdapterChatMail(Context mContext, int layoutResourceId, ChatMail[] data) {

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

		ChatMail objectItem = data[position];

		TextView text_mail = (TextView) convertView
				.findViewById(R.id.listmail);
		text_mail.setText(objectItem.prenom_passager);

		return convertView;
	}

}

