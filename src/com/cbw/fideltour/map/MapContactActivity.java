package com.cbw.fideltour.map;

import android.app.Activity;
import android.os.Bundle;

import com.cbw.fideltour.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapContactActivity extends Activity {

	static final LatLng HAMBURG = new LatLng(36.8188100, 10.1659600);

	private GoogleMap map;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.map_contact_layout);
		
		 map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map))
	        .getMap();
		 
		 Marker Ambassadeurs = map.addMarker(new MarkerOptions().position(HAMBURG)
			        .title("Les Ambassadeurs hôtel"));
			    // Move the camera instantly to hamburg with a zoom of 15.
			    map.moveCamera(CameraUpdateFactory.newLatLngZoom(HAMBURG, 15));

			    // Zoom in, animating the camera.
			    map.animateCamera(CameraUpdateFactory.zoomTo(14), 2000, null);
			    map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
	}


}