package com.cbw.fideltour.map;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
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
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;

public class PlacesAutoCompleteAdapter extends ArrayAdapter<String> implements
		Filterable {
	private ArrayList<String> resultList;
	private static final String LOG_TAG = "ExampleApp";

	private static final String PLACES_API_BASE = "https://maps.googleapis.com/maps/api/place";
	private static final String TYPE_AUTOCOMPLETE = "/autocomplete";
	private static final String OUT_JSON = "/json";
	private static final String origin = " ";
	private static final String destination = " ";

	static final HttpTransport HTTP_TRANSPORT = AndroidHttp
			.newCompatibleTransport();
	static final JsonFactory JSON_FACTORY = new JacksonFactory();

	private static final String PLACES_API_KEY = "AIzaSyDp18EHtMYYl6HX6sqPXkapCHop9UqtYnw";

	public PlacesAutoCompleteAdapter(Context context, int textViewResourceId) {
		super(context, textViewResourceId);
	}

	@Override
	public int getCount() {
		return resultList.size();
	}

	@Override
	public String getItem(int index) {
		return resultList.get(index);
	}

	@Override
	public Filter getFilter() {
		Filter filter = new Filter() {
			@Override
			protected FilterResults performFiltering(CharSequence constraint) {
				FilterResults filterResults = new FilterResults();
				if (constraint != null) {
					// Retrieve the autocomplete results.
					resultList = autocomplete(constraint.toString());

					// Assign the data to the FilterResults
					filterResults.values = resultList;
					filterResults.count = resultList.size();
				}
				return filterResults;
			}

			@Override
			protected void publishResults(CharSequence constraint,
					FilterResults results) {
				if (results != null && results.count > 0) {
					notifyDataSetChanged();
				} else {
					notifyDataSetInvalidated();
				}
			}
		};
		return filter;
	}

	private ArrayList<String> autocomplete(String input) {
		ArrayList<String> resultList = new ArrayList<String>();

		try {

			HttpRequestFactory requestFactory = HTTP_TRANSPORT
					.createRequestFactory(new HttpRequestInitializer() {
						@Override
						public void initialize(HttpRequest request) {
							request.setParser(new JsonObjectParser(JSON_FACTORY));
						}
					});
			GenericUrl url = new GenericUrl(PLACES_API_BASE + TYPE_AUTOCOMPLETE
					+ OUT_JSON);
			url.put("input", input);
			url.put("key", PLACES_API_KEY);
			url.put("sensor", false);
			
			Log.i(LOG_TAG, "URL = "+url.toString()); 

			HttpRequest request = requestFactory.buildGetRequest(url);
			HttpResponse httpResponse = request.execute();
			PlacesResult directionsResult = httpResponse
					.parseAs(PlacesResult.class);

			Log.i(LOG_TAG,"http response : "+directionsResult.status);
			List<Prediction> predictions = directionsResult.predictions;
			for (Prediction prediction : predictions) {
				resultList.add(prediction.description);
				/*resultList.add(prediction.id);
				resultList.addAll(prediction.matched_substrings);
				resultList.add(prediction.reference);
				resultList.addAll(prediction.terms);
				resultList.addAll(prediction.types);*/

			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return resultList;
	}

	public static class PlacesResult {

		@Key("predictions")
		public List<Prediction> predictions;
		@Key("status")
		public String status;
	}

	public static class Prediction {
		@Key("description")
		public String description;

		@Key("id")
		public String id;
		@Key("matched_substrings")
		public List<String> matched_substrings;
		@Key("reference")
		public String reference;
		@Key("terms")
		public List<String> terms;
		@Key("types")
		public List<String> types;
	}

	@SuppressWarnings("unused")
	private class DirectionsFetcher extends AsyncTask<URL, Integer, String> {

		ArrayList<String> resultList = new ArrayList<String>();
		private ArrayList<Marker> mMarkers;
		private List<LatLng> latLngs = new ArrayList<LatLng>();

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
				url.put("origin", origin);
				url.put("destination", destination);
				url.put("sensor", false);

				HttpRequest request = requestFactory.buildGetRequest(url);
				HttpResponse httpResponse = request.execute();
				DirectionsResult directionsResult = httpResponse
						.parseAs(DirectionsResult.class);
				String encodedPoints = directionsResult.routes.get(0).overviewPolyLine.points;

				latLngs = PolyUtil.decode(encodedPoints);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			return null;
		}

		protected void onProgressUpdate(Integer... progress) {
		}

		protected void onPostExecute(String result) {
			removeMarkers();
			for (int i = 0; i < latLngs.size(); i++) {

				MainActivityMap.mMap.addMarker(new MarkerOptions()
						.position(latLngs.get(i)));
			}
		}

		private void removeMarkers() {
			for (Marker marker : mMarkers) {
				marker.remove();
			}
			mMarkers.clear();
		}
	}

	public static class DirectionsResult {
		@Key("routes")
		public List<Route> routes;
	}

	public static class Route {
		@Key("overview_polyline")
		public OverviewPolyLine overviewPolyLine;
	}

	public static class OverviewPolyLine {
		@Key("points")
		public String points;
	}
	/*
	 * public static class DirectionsResult {
	 * 
	 * @Key("routes") public List<Route> routes;
	 * 
	 * @Key("status") public String status; }
	 * 
	 * public static class Route {
	 * 
	 * @Key("overview_polyline") public OverviewPolyLine overviewPolyLine;
	 * 
	 * @Key("bounds") public Bounds bound;
	 * 
	 * @Key("copyrights") public String copyrights;
	 * 
	 * @Key("legs") public List<String> legs;
	 * 
	 * @Key("overview_polyline") public OverviewPolyLine overviewpolyline;
	 * 
	 * @Key("summary") public String summary;
	 * 
	 * @Key("warnings") public List<String> warnings;
	 * 
	 * @Key("waypoint_order") public List<String> waypoint_order;
	 * 
	 * }
	 * 
	 * public static class OverviewPolyLine {
	 * 
	 * @Key("points") public String points; }
	 * 
	 * public static class Bounds {
	 * 
	 * @Key("northeast") public Northeast northeast;
	 * 
	 * @Key("southwest") public Southwest southwest; }
	 * 
	 * public static class Northeast {
	 * 
	 * @Key("lat") public String lat;
	 * 
	 * @Key("lng") public String lng; }
	 * 
	 * public static class Southwest {
	 * 
	 * @Key("lat") public String lat;
	 * 
	 * @Key("lng") public String lng; }
	 */
}