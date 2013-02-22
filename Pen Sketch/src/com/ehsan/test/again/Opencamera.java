package com.ehsan.test.again;



import android.os.AsyncTask;


public class Opencamera extends AsyncTask<Object, Integer, Boolean>{
	
	
	public MainActivityforpensketch mactivity;
	public boolean isfront;
	android.hardware.Camera.Parameters p;


	public Opencamera(MainActivityforpensketch view, boolean direction)
	{
		mactivity = view;
		isfront = direction;
	}

	
	@Override
	protected Boolean doInBackground(Object... params) {
		// TODO Auto-generated method stub


		android.hardware.Camera cm = android.hardware.Camera.open();
		p = cm.getParameters();
		cm.release();
		cm = null;
		

        return true;	
	}


	@Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		super.onPreExecute();
	}


	@Override
	protected void onProgressUpdate(Integer... values) {
		// TODO Auto-generated method stub
		super.onProgressUpdate(values);
	}
	@Override
	protected void onPostExecute(Boolean result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);

		mactivity.photomodeon(p);

	}
}



