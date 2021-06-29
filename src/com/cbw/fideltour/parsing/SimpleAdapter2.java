package com.cbw.fideltour.parsing;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;
import android.widget.SimpleAdapter;

public class SimpleAdapter2 extends SimpleAdapter
{

	Activity activity;
	 
	
	public Activity getActivity() {
		return activity;
	}

	public void setActivity(Activity activity) {
		this.activity = activity;
	}

	public SimpleAdapter2(Context context, List<? extends Map<String, ?>> data,
			int resource, String[] from, int[] to) {
		super(context, data, resource, from, to);
		// TODO Auto-generated constructor stub
	}

	@Override
    public void setViewImage(final ImageView v, final String value) {
        super.setViewImage(v, value);
        
        
            new Thread(new Runnable() 
            {
				
				@Override
				public void run() 
				{
					try 
			        {
						
					URL url; 
					url = new URL(value);
		            URLConnection conn = url.openConnection();
		            conn.connect();
		            InputStream is = conn.getInputStream();
		            final Bitmap bm = BitmapFactory.decodeStream(is);
		            
		            
		            activity.runOnUiThread(new Runnable() 
		            {
						
						@Override
						public void run() 
						{
							v.setImageBitmap(bm);
							
						}
					});
		            
				} catch (Exception e) {
		            e.printStackTrace();
		        }
					
				}
			}).start();
        	
            
            
        
    }
}
