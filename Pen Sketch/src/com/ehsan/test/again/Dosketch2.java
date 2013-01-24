package com.ehsan.test.again;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.AsyncTask;
import android.widget.ProgressBar;

public class Dosketch2 extends AsyncTask<Object, Integer, Boolean>{
	
	
	public float maincont;
	public int strength = 50;
	public Bitmap thefinalresult;
	public Bitmap background;
	float[][] brightness;
	Bitmap myimage;
	int result;
//	public int main;
//	public int lbrown;
	ProgressBar pb;
	Sketchview sv;
	public String target;
	int lblack = Color.rgb(100, 100, 100);
	int dblack = Color.rgb(47, 47, 47);
	int lbrown = Color.rgb(128, 85, 86);
	int dbrown = Color.rgb(86, 28, 29);
	int lblue = Color.rgb(122, 133, 155);
	int dblue = Color.rgb(60, 75, 106);
	int lgreen = Color.rgb(90, 127, 106);
	int dgreen = Color.rgb(37, 65, 50);
	int lcolor;
	int dcolor;
	
	public Dosketch2(Bitmap image, ProgressBar pbar, Sketchview view)
	{
		pb = pbar;
		pb.bringToFront();
		sv = view;
		result = Color.WHITE;
		myimage = image;


		

	}



	@Override
	protected Boolean doInBackground(Object... params) {
		// TODO Auto-generated method stub
		if (target == "Green")
		{
			lcolor = lgreen;
			dcolor = dgreen;
		}
		if (target == "Black")
		{
			lcolor = lblack;
			dcolor = dblack;
		}
		if (target == "Brown")
		{
			lcolor = lbrown;
			dcolor = dbrown;
		}
		if (target == "Blue")
		{
			lcolor = lblue;
			dcolor = dblue;
		}
		
		int[] pixels = new int[myimage.getWidth() * myimage.getHeight()];
		int width = myimage.getWidth();
		myimage.getPixels(pixels, 0, width, 0, 0, width, myimage.getHeight());
		
		for (int i = 0 ; i < myimage.getWidth(); i++ )
		{
			for (int k = 0 ; k < myimage.getHeight(); k++)
			{
//				int px = myimage.getPixel(i, k);
				int px = pixels[i+k*width];
				if (px == dbrown ||
					px == dblue ||
					px == dgreen ||
					px == dblack)
					pixels[i+k*width] = dcolor;
//					myimage.setPixel(i, k, dcolor);
				
				
				if (px == lbrown ||
						px == lblue ||
						px == lgreen ||
						px == lblack)
					pixels[i+k*width] = lcolor;
//						myimage.setPixel(i, k, lcolor);
				
			}
			
//			publishProgress( i );
		}

		myimage.setPixels(pixels, 0, width, 0, 0, width, myimage.getHeight());
		return true;
		
	}




	@Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		super.onPreExecute();
//		pb.setVisibility(View.VISIBLE);
		sv.setEnabled(false);
		pb.setIndeterminate(false);

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
		sv.initialize2(myimage);
	}
}
