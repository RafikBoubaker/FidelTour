package com.cbw.fideltour.map;


import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


import com.cbw.fideltour.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMyLocationChangeListener;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.JsonObjectParser;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.Key;

import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * This shows how to create a simple activity with a map and a marker on the map.
 * <p>
 * Notice how we deal with the possibility that the Google Play services APK is not
 * installed/enabled/updated on a user's device.
 */
public class MainActivityMap extends FragmentActivity {
    /**
     * Note that this may be null if the Google Play services APK is not available.
     */
   public static GoogleMap mMap;
   String origin_extra="",dest_extra="";
   LinearLayout route_info;
   TextView distance_view,duration_view; 

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_mapguide);
        
        route_info = (LinearLayout) findViewById(R.id.route_info_area);
        route_info.setVisibility(View.GONE);
        
        distance_view = (TextView) findViewById(R.id.distance_value);
        duration_view = (TextView) findViewById(R.id.duration_value);
        
        Bundle extra = getIntent().getExtras();
        
        if(extra!=null){
        	origin_extra = extra.getString("origin");
        	dest_extra = extra.getString("destination");
        	
        	if(origin_extra!=null && !origin_extra.trim().isEmpty()&&dest_extra!=null&&!dest_extra.trim().isEmpty()){
        		route_info.setVisibility(View.VISIBLE);
        		DirectionsFetcher fetcher = new DirectionsFetcher();
            	fetcher.execute(null,null,null);
        	}else{
        		Toast.makeText(getApplicationContext(), "Invalid to from", Toast.LENGTH_SHORT).show();
        	}
        	
        	Log.i("Extra deteced ","from : "+origin_extra+", to: "+dest_extra);
        }
        setUpMapIfNeeded();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }

    /**
     * Sets up the map if it is possible to do so (i.e., the Google Play services APK is correctly
     * installed) and the map has not already been instantiated.. This will ensure that we only ever
     * call {@link #setUpMap()} once when {@link #mMap} is not null.
     * <p>
     * If it isn't installed {@link SupportMapFragment} (and
     * {@link com.google.android.gms.maps.MapView MapView}) will show a prompt for the user to
     * install/update the Google Play services APK on their device.
     * <p>
     * A user can return to this FragmentActivity after following the prompt and correctly
     * installing/updating/enabling the Google Play services. Since the FragmentActivity may not
     * have been completely destroyed during this process (it is likely that it would only be
     * stopped or paused), {@link #onCreate(Bundle)} may not be called again so we should call this
     * method in {@link #onResume()} to guarantee that it will be called.
     */
    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                setUpMap();
            }
        }
    }

    /**
     * This is where we can add markers or lines, add listeners or move the camera. In this case, we
     * just add a marker near Africa.
     * <p>
     * This should only be called once and when we are sure that {@link #mMap} is not null.
     */
    private void setUpMap() {
    	mMap.setMyLocationEnabled(true);
       
        mMap.setOnMyLocationChangeListener(new OnMyLocationChangeListener() {
			
			@Override
			public void onMyLocationChange(Location arg0) {
				mMap.addMarker(new MarkerOptions().position(new LatLng(arg0.getLatitude(), arg0.getLongitude())).title("Position Actuelle"));
				
			}
		});
        Log.e("Act","Map loaded");
        Button btn_find = (Button) findViewById(R.id.btn_find);
        
        // Defining button click event listener for the find button
        OnClickListener findClickListener = new OnClickListener() {
            @Override
            public void onClick(View v) {
                // Getting reference to EditText to get the user input location
                EditText etLocation = (EditText) findViewById(R.id.et_location);
 
                // Getting user input location
                String location = etLocation.getText().toString();
 
                if(location!=null && !location.equals("")){
                    new GeocoderTask().execute(location);
                }
            }

			
        };
 
        // Setting button click event listener for the find button
        btn_find.setOnClickListener(findClickListener);
    }
    LatLng latLng;
    MarkerOptions markerOption;
    
 // An AsyncTask class for accessing the GeoCoding Web Service
    private class GeocoderTask extends AsyncTask<String, Void, List<Address>>{
 
        @Override
        protected List<Address> doInBackground(String... locationName) {
            // Creating an instance of Geocoder class
            Geocoder geocoder = new Geocoder(getBaseContext());
            List<Address> addresses = null;
 
            try {
                // Getting a maximum of 3 Address that matches the input text
                addresses = geocoder.getFromLocationName(locationName[0], 3);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return addresses;
        }
 
        @Override
        protected void onPostExecute(List<Address> addresses) {
 
            if(addresses==null || addresses.size()==0){
                Toast.makeText(getBaseContext(), "No Location found", Toast.LENGTH_SHORT).show();
                return;
            }
 
            // Clears all the existing markers on the map
            mMap.clear();
 
            // Adding Markers on Google Map for each matching address
            for(int i=0;i<addresses.size();i++){
 
                Address address = (Address) addresses.get(i);
 
                // Creating an instance of GeoPoint, to display in Google Map
                latLng = new LatLng(address.getLatitude(), address.getLongitude());
 
                String addressText = String.format("%s, %s",
                address.getMaxAddressLineIndex() > 0 ? address.getAddressLine(0) : "",
                address.getCountryName());
 
                markerOption = new MarkerOptions();
                markerOption.position(latLng);
                markerOption.title(addressText);
 
                mMap.addMarker(markerOption);
 
                // Locate the first location
                if(i==0)
                    mMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
            }
        }
    }
    
    private class DirectionsFetcher extends AsyncTask<URL, Integer, String> {

	
		private List<LatLng> latLngs = new ArrayList<LatLng>();
		HttpTransport HTTP_TRANSPORT = AndroidHttp
				.newCompatibleTransport();
		JsonFactory JSON_FACTORY = new JacksonFactory();
		String distance ;
		String duration ;

		protected String doInBackground(URL... urls) {
			try {
				HttpRequestFactory requestFactory = HTTP_TRANSPORT
						.createRequestFactory(new HttpRequestInitializer() {
							@Override
							public void initialize(HttpRequest request) {
								request.setParser(new JsonObjectParser(
										JSON_FACTORY));
							}
						});

				GenericUrl url = new GenericUrl(
						"http://maps.googleapis.com/maps/api/directions/json");
				url.put("origin",origin_extra);
				url.put("destination", dest_extra);
				url.put("sensor", false);
				
				Log.i("Places Acti ","URL = "+url.toString());

				HttpRequest request = requestFactory.buildGetRequest(url);
				HttpResponse httpResponse = request.execute();
				DirectionsResult directionsResult = httpResponse
						.parseAs(DirectionsResult.class);
				
				if(directionsResult.status.equals("OK")){
					String encodedPoints = directionsResult.routes.get(0).overviewPolyLine.points;

					latLngs = PolyUtil.decode(encodedPoints);
					
					distance = directionsResult.routes.get(0).legs.get(0).distance.text;
					duration = directionsResult.routes.get(0).legs.get(0).duration.text;
					
					
							
				}else{
					Toast.makeText(getApplicationContext(), "ERROR : "+directionsResult.status, Toast.LENGTH_SHORT).show();
					Log.e("main", "ERROR : "+directionsResult.status);
					
				}
				
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			return null;
		}

		protected void onProgressUpdate(Integer... progress) {
		}

		protected void onPostExecute(String result) {
			removeMarkers();
			distance_view.setText(distance);
			duration_view.setText(duration);
			
			
			//Marqueur départ
			MainActivityMap.mMap.addMarker(new MarkerOptions()
			.position(latLngs.get(0)).title("Départ"));
			
			//Route
			LatLng[] lines = new LatLng[latLngs.size()];
			latLngs.toArray(lines);
			MainActivityMap.mMap.addPolyline(new PolylineOptions().add(lines));
			
			//marqueur Destination
			MainActivityMap.mMap.addMarker(new MarkerOptions()
			.position(latLngs.get(latLngs.size()-1)).title("Destination"));
		}

		private void removeMarkers() {
			MainActivityMap.mMap.clear();
		}
	}

	public static class DirectionsResult {
		@Key("routes")
		public List<Route> routes;
		@Key("status")
		public String status;
	}

	public static class Route {
		@Key("overview_polyline")
		public OverviewPolyLine overviewPolyLine;
		
		@Key("legs")
		public List<Leg> legs;
	}
	
	public static class Leg{
		@Key("distance")
		public Distance distance;
		
		@Key("duration")
		public Duration duration;
	}
	
	public static class Distance{
		@Key("text")
		public String text;
	}
	
	public static class Duration{
		@Key("text")
		public String text;
	}

	public static class OverviewPolyLine {
		@Key("points")
		public String points;
	}

}

