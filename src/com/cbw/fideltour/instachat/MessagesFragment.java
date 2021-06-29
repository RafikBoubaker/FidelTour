package com.cbw.fideltour.instachat;

import com.cbw.fideltour.R;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ListFragment;
import android.app.LoaderManager;
import android.content.IntentFilter;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.SimpleCursorAdapter;

@SuppressLint("NewApi")
public class MessagesFragment extends ListFragment implements LoaderManager.LoaderCallbacks<Cursor> {
	 
    private OnFragmentInteractionListener mListener;
    public static SimpleCursorAdapter adapter;
    
    
     
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement OnFragmentInteractionListener");
        }
    }   
    
    
 
    @Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		return super.onCreateView(inflater, container, savedInstanceState);
	}



	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Log.i("profileEmail", ChatActivity.profileEmail);

        adapter = new SimpleCursorAdapter(
    			  getActivity(), 
                R.layout.chat_list_item, 
                getActivity().getContentResolver().query(Uri.withAppendedPath(DataProvider.CONTENT_URI_MESSAGES,""), null, null, null, null), 
                new String[]{DataProvider.COL_MSG, DataProvider.COL_AT}, 
                new int[]{R.id.text1, R.id.text2},
                  0);
        adapter.setViewBinder(new SimpleCursorAdapter.ViewBinder() {
             
            @Override
            public boolean setViewValue(View view, Cursor cursor, int columnIndex) {
            	//Log.i("kaka view binder", cursor.toString());
                switch(view.getId()) {
                case R.id.text1:
                    LinearLayout root = (LinearLayout) view.getParent().getParent();
                    
                    if (cursor.getString(cursor.getColumnIndex(DataProvider.COL_FROM)) == null) {
                        root.setGravity(Gravity.RIGHT);
                        root.setPadding(50, 10, 10, 10);
                    } else {
                        root.setGravity(Gravity.LEFT);
                        root.setPadding(10, 10, 50, 10);
                    }
                    break;                  
                }
                return false;
            }
        });
        setListAdapter(adapter);
        
        
        new AsyncTask<Void, Void, Void>() {
        	@Override
        protected Void doInBackground(Void... params) {
        	// TODO Auto-generated method stub
        	while (true) {
        		try {
    				Thread.sleep(2500);
    			} catch (InterruptedException e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    			}
            	publishProgress(null,null);
			}
        	
        }
        @Override
        	protected void onProgressUpdate(Void... values) {
        		// TODO Auto-generated method stub
        		super.onProgressUpdate(values);
        		adapter.notifyDataSetChanged();
        		Log.i("Messg frag","refresh");
        	}
		};
    }  
	
	
 
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Bundle args = new Bundle();
        args.putString(DataProvider.COL_EMAIL, mListener.getProfileEmail());
        getLoaderManager().initLoader(0, args, this);
    }
 
    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }
 
    public interface OnFragmentInteractionListener {
        public String getProfileEmail();
    }

	@Override
	public Loader<Cursor> onCreateLoader(int id, Bundle args) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onLoaderReset(Loader<Cursor> loader) {
		// TODO Auto-generated method stub
		
	}
}