package com.ehsan.test.again;

import android.R.string;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;


public class Loadbitmap extends AsyncTask<Object, Integer, Boolean>{
	
	
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



	public Loadbitmap(String Path, ProgressBar pbar, Sketchview view)
	{
		pb = pbar;
		sv = view;
		filePath = Path;

	}

	


	



	@Override
	protected Boolean doInBackground(Object... params) {
		// TODO Auto-generated method stub



     
         

         BitmapFactory.Options options = new BitmapFactory.Options();
         options.inJustDecodeBounds = true;
         BitmapFactory.decodeFile(filePath, options);
         int imageHeight = options.outHeight;
         int imageWidth = options.outWidth;
         
         int inSampleSize = 0;
         
         if (imageHeight > 640 || imageWidth > 640) 
         {
             if (imageWidth < imageHeight) 
             {
                 inSampleSize = Math.round((float)imageHeight / 640);
             } 
             else 
             {
                 inSampleSize = Math.round((float)imageWidth / 640);
             }
         }
         
         
         options.inSampleSize = inSampleSize;

         options.inJustDecodeBounds = false;
         returnedimage = BitmapFactory.decodeFile(filePath, options);
         
         return true;
	

		
	}




	@Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		super.onPreExecute();
		sv.setEnabled(false);
//		pb.setIndeterminate(false);
//		pb.setVisibility(View.VISIBLE);
	}


	@Override
	protected void onProgressUpdate(Integer... values) {
		// TODO Auto-generated method stub
		super.onProgressUpdate(values);
//		pb.setProgress(values[0]);
//		b.setText(String.valueOf(values[0]));
	}
	@Override
	protected void onPostExecute(Boolean result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
//		pb.setVisibility(View.INVISIBLE);

		sv.setEnabled(true);
		
		
		if (returnedimage != null)
		{
		sv.initialize2(returnedimage);
		if (Math.abs(returnedimage.getWidth() - returnedimage.getHeight()) > 10)	
		mactivity.performcrop();
		}
		else
		{
			Toast toast = Toast.makeText(mactivity.context, "Unfortunately Pen Sketch cannot load the selected file.", Toast.LENGTH_LONG);
			toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
			toast.show();
			
		}
		

		
		
	}
}
