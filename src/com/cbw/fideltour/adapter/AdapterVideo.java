package com.cbw.fideltour.adapter;

import java.util.ArrayList;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public abstract class AdapterVideo extends BaseAdapter {

	private ArrayList<?> entries;
	private int R_layout_IdView;
	private Context context;

	public AdapterVideo(Context context, int R_layout_IdView,
			ArrayList<?> entries) {

		super();
		this.entries = entries;
		this.R_layout_IdView = R_layout_IdView;
		this.context = context;

	}

	@Override
	public View getView(int position, View view, ViewGroup parent) {
		if (view == null) {
			LayoutInflater vi = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			view = vi.inflate(R_layout_IdView, null);
		}
		onEntry(entries.get(position), view);
		return view;
	}

	@Override
	public int getCount() {
		return entries.size();
	}

	@Override
	public Object getItem(int position) {
		return entries.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	public abstract void onEntry(Object object, View view);

}