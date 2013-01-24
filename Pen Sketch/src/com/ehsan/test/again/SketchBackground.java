package com.ehsan.test.again;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.view.Gravity;
import android.widget.ProgressBar;
import android.widget.Toast;



public class SketchBackground extends AsyncTask<Object, Integer, Boolean>{
	
	
	public float maincont;
	public MainActivityforpensketch mactivity;
	public Bitmap thefinalresult;
	Bitmap mainimage;
	int result;
	int main;
	int lbrown;
	String filePath; 
	ProgressBar pb;
	Sketchview sv;
	Bitmap returnedimage;
	Intent theintent;
	Intent theintent2;
	boolean error;



	public SketchBackground(Sketchview view, MainActivityforpensketch activity)
	{

		sv = view;
		mactivity = activity;


	}



	@Override
	protected Boolean doInBackground(Object... params) {
		// TODO Auto-generated method stub

		try{

		ByteArrayOutputStream bytes = new ByteArrayOutputStream();
		sv.backgroundscaled.compress(Bitmap.CompressFormat.JPEG, 100, bytes);

		

		String fname = Environment.getExternalStorageDirectory().getPath() + "/PenSketch/" + "PenSketch_" + "sharetemp" + ".jpg";                  

		File f = new File(fname);

		
		try {
			boolean vv = f.createNewFile();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		//write the bytes in file
		FileOutputStream fo = null;
		try {
			fo = new FileOutputStream(f);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			fo.write(bytes.toByteArray());
			fo.flush();
			fo.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		theintent = new Intent(Intent.ACTION_MEDIA_MOUNTED, Uri.fromFile(f));
		
		
		Uri uri = Uri.fromFile(f);
        Intent sendIntent = new Intent(Intent.ACTION_SEND);
        sendIntent.setType("image/*");
        sendIntent.putExtra(Intent.EXTRA_STREAM, uri);
        
        theintent2 = Intent.createChooser(sendIntent, "Share Sketch");
        
        error = false;
         
         return true;
		}
		catch(Throwable b)
		{
			publishProgress(2);
			Toast toast = Toast.makeText(mactivity.context, "Pen Sketch couldn't complete the task! Please let us know if it happened again.", Toast.LENGTH_SHORT);
			toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
			toast.show();
		}
	

		return true;
	}




	@Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		super.onPreExecute();
		sv.setEnabled(false);

	}


	@Override
	protected void onProgressUpdate(Integer... values) {
		// TODO Auto-generated method stub
		super.onProgressUpdate(values);
		error = true;
		mactivity.shareerror();

	}
	@Override
	protected void onPostExecute(Boolean result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
	if (!error)
	{
		mactivity.sharesketch2(theintent, theintent2);
	}
		
		
	}
}
