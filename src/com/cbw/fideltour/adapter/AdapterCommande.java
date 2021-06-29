package com.cbw.fideltour.adapter;

import java.util.ArrayList;
import com.cbw.fideltour.R;
import com.cbw.fideltour.entity.Categorie;
import com.cbw.fideltour.entity.Hotel;
import com.cbw.fideltour.entity.SousCategorie;
import com.squareup.picasso.Picasso;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class AdapterCommande extends BaseExpandableListAdapter {

	private Context context;
	private ArrayList<Categorie> categList;
	private ArrayList<Categorie> originalList;

	public AdapterCommande(Context context, ArrayList<Categorie> categList) {
		this.context = context;
		this.categList = new ArrayList<Categorie>();
		this.categList.addAll(categList);
		this.originalList = new ArrayList<Categorie>();
		this.originalList.addAll(categList);
	}

	@Override
	public Object getChild(int groupPosition, int childPosition) {
		ArrayList<SousCategorie> souscategList = categList.get(groupPosition)
				.getSouscat();
		return souscategList.get(childPosition);
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		return childPosition;
	}

	@Override
	public View getChildView(int groupPosition, int childPosition,
			boolean isLastChild, View view, ViewGroup parent) {

		SousCategorie souscateg = (SousCategorie) getChild(groupPosition,
				childPosition);
		if (view == null) {
			LayoutInflater layoutInflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			view = layoutInflater.inflate(R.layout.entry_souscategorie, null);
		}

		TextView nom = (TextView) view.findViewById(R.id.NomSousCateg);
		TextView prix = (TextView) view.findViewById(R.id.PrixSousCateg);

		nom.setText(souscateg.getNom_sous_categ_cmd().trim());
		prix.setText(souscateg.getPrix_unitaire().trim());

		return view;
	}

	@Override
	public int getChildrenCount(int groupPosition) {

		ArrayList<SousCategorie> souscategList = categList.get(groupPosition)
				.getSouscat();
		return souscategList.size();

	}

	@Override
	public Object getGroup(int groupPosition) {
		return categList.get(groupPosition);
	}

	@Override
	public int getGroupCount() {
		return categList.size();
	}

	@Override
	public long getGroupId(int groupPosition) {
		return groupPosition;
	}

	@Override
	public View getGroupView(int groupPosition, boolean isLastChild, View view,
			ViewGroup parent) {

		Categorie categ = (Categorie) getGroup(groupPosition);
		if (view == null) {
			LayoutInflater layoutInflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			view = layoutInflater.inflate(R.layout.entry_menucommande, null);
		}

		TextView nomcat = (TextView) view.findViewById(R.id.NomCategorie);
		ImageView logo = (ImageView) view.findViewById(R.id.imageViewcateg);
		nomcat.setText(categ.getNom_categ_cmd().trim());
		String url_img = Hotel.adresse_ip + "images/" + categ.getLogo_categ();
		Picasso.with(context).load(url_img).into(logo);

		return view;
	}

	@Override
	public boolean hasStableIds() {
		return true;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		return true;
	}

	public void filterData(String query) {

		query = query.toLowerCase();
		Log.v("MyListAdapter", String.valueOf(categList.size()));
		categList.clear();

		if (query.isEmpty()) {
			categList.addAll(originalList);
		} else {

			for (Categorie categ : originalList) {

				ArrayList<SousCategorie> souscatList = categ.getSouscat();
				ArrayList<SousCategorie> newList = new ArrayList<SousCategorie>();
				for (SousCategorie souscat : souscatList) {
					if (souscat.getNom_sous_categ_cmd().toLowerCase()
							.contains(query)
							|| souscat.getPrix_unitaire().toLowerCase()
									.contains(query)) {
						newList.add(souscat);
					}
				}

			}
		}

		Log.v("MyListAdapter", String.valueOf(categList.size()));
		notifyDataSetChanged();

	}
}
