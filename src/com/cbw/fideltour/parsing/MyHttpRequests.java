package com.cbw.fideltour.parsing;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import android.os.AsyncTask;

public class MyHttpRequests {
	
	
	
	public static String get(final String url){
		String res= "";

		AsyncTask<Void, Void, String> a = new AsyncTask<Void, Void, String>() {
			@Override
			protected String doInBackground(Void... params) {
				HttpClient client = new DefaultHttpClient();
		        HttpGet request = new HttpGet(url);
		        try{
		            HttpResponse response = client.execute(request);
		            return (HttpHelper.request(response));
		        }catch(Exception ex){ex.printStackTrace();}
				return null;
			}
		};
		
		try {res =  a.execute().get();
		System.out.println(res);} 
		catch (Exception e) {e.printStackTrace();} 
		
		return res;
	}

	
	/**
	 * type: xml | json
	 * content: xml or json text 
	 */
	public static String post(final String url ,final String content , final String type){
		String res= "";

		AsyncTask<Void, Void, String> a = new AsyncTask<Void, Void, String>() {
			@Override
			protected String doInBackground(Void... params) {
				HttpClient client = new DefaultHttpClient();
				HttpPost post = new HttpPost(url);
				StringEntity entity;
				
				//verifier si xml ou json post
				String contentType = "";
				if(type.equals("json")) contentType = "application/json";
				else contentType = "text/xml";
				
				try {
					entity = new StringEntity(content, "UTF-8");
					entity.setContentType(contentType);
					post.setEntity(entity);
					
					HttpResponse response = client.execute(post);
		            return (HttpHelper.request(response));
				} catch (Exception e) {e.printStackTrace();}
 
				return null;
			}
		};
		
		try {res =  a.execute().get();} 
		catch (Exception e) {e.printStackTrace();} 
		
		return res;
	}


	public HttpResponse execute(HttpPost httpPost) {
		// TODO Auto-generated method stub
		return null;
	}


}
