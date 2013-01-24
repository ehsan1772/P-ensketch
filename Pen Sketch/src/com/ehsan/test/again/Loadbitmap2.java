package com.ehsan.test.again;

import android.R.string;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;


public class Loadbitmap2 extends AsyncTask<Object, Integer, Boolean>{
	
	
	public float maincont;
	public MainActivityforpensketch mactivity;
	public Bitmap thefinalresult;
	public boolean isfront;
	Bitmap mainimage;
	int result;
	int main;
	int lbrown;
	byte[] bt; 
	ProgressBar pb;
	Sketchview sv;
	Bitmap returnedimage;
	Cameraview cv;
	String text;



	public Loadbitmap2(byte[] Path, MainActivityforpensketch view, boolean direction)
	{
//		pb = pbar;
		mactivity = view;
		bt = Path;
		isfront = direction;
		
	}

	


	



	@Override
	protected Boolean doInBackground(Object... params) {
		// TODO Auto-generated method stub



     
         

         BitmapFactory.Options options = new BitmapFactory.Options();
         options.inJustDecodeBounds = true;
         BitmapFactory.decodeByteArray(bt, 0, bt.length, options);

//         BitmapFactory.decodeFile(filePath, options);
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
         returnedimage = BitmapFactory.decodeByteArray(bt, 0, bt.length, options);
         
         
 		Matrix matrix = new Matrix();
 		
 		
 		
 		
// 		 		if (mactivity.cv.ISFRONT)
 		if (isfront)
 		{
 		matrix.postRotate(270);

 		}
 		else
 		{
 			matrix.postRotate(90);

 		}
         
         returnedimage = Bitmap.createBitmap(returnedimage, 0, 0, returnedimage.getWidth(), returnedimage.getHeight(), matrix, true);
         
			int h2= returnedimage.getHeight() - returnedimage.getWidth();
			h2 = Math.round(h2/2);
         
 //        returnedimage = Bitmap.createBitmap(returnedimage,0 , h2, returnedimage.getWidth(), returnedimage.getWidth());
			returnedimage = Bitmap.createBitmap(returnedimage,0 , 0, returnedimage.getWidth(), returnedimage.getWidth());
			

			
         return true;
	

		
	}




	@Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		super.onPreExecute();
//		sv.setEnabled(false);
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

//		mactivity.cv.myPictureCallback_JPG = null;
//		mactivity.cv.myPictureCallback_RAW = null;
//		mactivity.cv.myShutterCallback = null;
//		mactivity.cv = null;
		
		mactivity.cv.ISFRONT = false;
		mactivity.sv.initialize2(returnedimage);
		mactivity.mlayout.removeView(mactivity.cv);


		
	}
}
