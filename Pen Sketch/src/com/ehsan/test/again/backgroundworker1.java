package com.ehsan.test.again;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.AsyncTask;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

public class backgroundworker1 extends AsyncTask<Object, Integer, Boolean>{
	
	
	public float maincont;
	public int strength = 50;
	public Bitmap thefinalresult;
	public Bitmap background;
	float[][] brightness;
	Bitmap mainimage;
	int result;
	public int main;
	public int lbrown;
	ProgressBar pb;
	Sketchview sv;
	float[] brs;
	View[] buttons;
	
//	float strength2;
//	float change;
//	float cont;
//	
//	int sendback;



	public backgroundworker1(Bitmap image, ProgressBar pbar, Sketchview view, String color, View[] b)
	{
		buttons = b;
		brs = new float[4];
		pb = pbar;
		pb.bringToFront();
		sv = view;
		result = Color.WHITE;
		//main = Color.rgb(86, 28, 29);
		//lbrown = Color.rgb(128, 85, 86);
		float contrast = 0;
		float brs2 = 0;
		Color cl = new Color();
		
		float[] brs = new float[100];
		float[] hsv = new float[3];
		
		Bitmap resized = Bitmap.createScaledBitmap(image, 10, 10, false);
		
		for (int l = 0 ; l < 10 ; l++)
		{
			for (int w = 0; w <10; w++)
			{

				Color.colorToHSV(resized.getPixel(l, w), hsv);
				contrast += hsv[2];
				brs[l*w] = hsv[2];
			}
		}
		
		contrast = contrast / 100;
		
		for (int i = 0 ; i < 100; i++)
		{
			brs2 += Math.abs(contrast - brs[i] );
		}
		
		brs2 = brs2 / 100;
		
		
		maincont = brs2 * .3f;
		
		
		mainimage = image;
//		mainimage = Bitmap.createScaledBitmap(image, Math.round(image.getWidth()/3), Math.round(image.getHeight()/3), false);

		if ( color == "Green")
		{
			lbrown = Color.rgb(90, 127, 106);
			main = Color.rgb(37, 65, 50);
		}
		if ( color == "Blue")
		{
			lbrown = Color.rgb(122, 133, 155);
			main = Color.rgb(60, 75, 106);
		}
		if ( color == "Brown")
		{
			lbrown = Color.rgb(128, 85, 86);
			main = Color.rgb(86, 28, 29);
		}
		if ( color == "Black")
		{
			lbrown = Color.rgb(100, 100, 100);
			main = Color.rgb(47, 47, 47);	
		}

//		strength2 = strength;
//		change = maincont * ((strength2-50)/50);
//		cont = maincont - change;
//		
//		sendback = Color.WHITE;

	}

	public int isdark2(int w, int h, Bitmap image)
	{
		float strength2 = strength;
		float change = maincont * ((strength2-50)/50);
		float cont = maincont - change;
		
		int sendback = Color.WHITE;
//		int sendback = Color.argb(255, 0, 0, 0);

		
		if (w<2 || 
			(image.getWidth() - w) < 2 ||
			h<2 ||
			(image.getHeight() - h) < 2)
			return sendback;
		
//		float[] brs = new float[4];

		
		brs[0]= brightness[w-1][h];
		brs[1]= brightness[w+1][h];
		brs[2]= brightness[w][h-1];
		brs[3]= brightness[w][h+1];
		

		
		float max = Math.max(Math.max(brs[0], brs[1]), Math.max(brs[2], brs[3]));
		
		
		float br = brightness[w][h];
		
		if (max - br > cont)
		{
			sendback = main;
			return sendback;
		}
		else
		{
			if (max - br > (cont / 3))
			{
				sendback = lbrown;
				return sendback;
				
			}
		}
		
		
//		sendback = background.getPixel(w, h);
		return sendback;
		
		
		
	}
	
	




	@Override
	protected Boolean doInBackground(Object... params) {
		// TODO Auto-generated method stub
		background = Bitmap.createScaledBitmap(background,mainimage.getWidth(), mainimage.getHeight(), false);
		
		pb.setVisibility(View.VISIBLE);
		Bitmap myimage = Bitmap.createScaledBitmap(mainimage, Math.round(mainimage.getWidth() * .8f), Math.round(mainimage.getHeight() * .8f), false);
//		Bitmap myimage = mainimage;
		float[] hsv = new float[3];
		int temp;
		
		brightness = new float[myimage.getWidth()][myimage.getHeight()];
		
		int[] pixels = new int[myimage.getWidth() * myimage.getHeight()];
		int width = myimage.getWidth();
		myimage.getPixels(pixels, 0, width, 0, 0, width, myimage.getHeight());

		
		for (int w = 0 ; w < myimage.getWidth() ; w++)
		{
			for (int l = 0; l <myimage.getHeight(); l++)
			{
				 
				Color.colorToHSV(pixels[w+l*width], hsv);
//				Color.colorToHSV(myimage.getPixel(w, l), hsv);
				brightness[w][l] = hsv[2];

			}
			publishProgress( w );
		}
		
		

//		Bitmap myimage2 = Bitmap.createBitmap(myimage.getWidth(), myimage.getHeight(), myimage.getConfig());
		Bitmap myimage2 = background;
		int[] bgpixels = new int[myimage2.getWidth() * myimage2.getHeight()];
		int bgwidth = myimage2.getWidth();
		myimage2.getPixels(bgpixels, 0, bgwidth, 0, 0, bgwidth, myimage2.getHeight());
		
		
		int difw = Math.round(background.getWidth() / 10);
		int difh = Math.round(background.getHeight() / 10);
		
		for (int i = 0 ; i < myimage.getWidth(); i++ )
		{
			for (int k = 0 ; k < myimage.getHeight(); k++)
			{
				temp = isdark2(i,k,myimage);
				if (temp != Color.WHITE)
				{
					bgpixels[(i + difw) + (k + difh)*bgwidth]=temp;
				//myimage2.setPixel(i + difw, k + difh, temp);
				}
				
			}
			
			publishProgress( i );
		}
		
//		myimage2.setPixels(bgpixels, 0, bgwidth, 0, 0, bgwidth, myimage2.getHeight());
		publishProgress( 0 );
		
		for (int k = 1; k < myimage2.getHeight()-2; k++)
		{
			for (int i = 1 ; i < myimage2.getWidth()-2; i++)
			{
				if(bgpixels[i+k*bgwidth] == lbrown)
					if(bgpixels[(i-1)+(k-1)*bgwidth] == main ||
					   bgpixels[(i)+(k-1)*bgwidth] == main ||
					   bgpixels[(i+1)+(k-1)*bgwidth] == main ||
					   bgpixels[(i-1)+(k)*bgwidth] == main ||
					   bgpixels[(i+1)+(k)*bgwidth] == main)
					   bgpixels[(i)+(k)*bgwidth] = main;
//				if (myimage2.getPixel(i, k) == lbrown)
//					if (myimage2.getPixel(i-1, k-1) == main ||
//						myimage2.getPixel(i, k-1) == main ||
//						myimage2.getPixel(i+1, k-1) == main ||
//						myimage2.getPixel(i-1, k) == main ||
//						myimage2.getPixel(i+1, k) == main)
//						myimage2.setPixel(i, k, main);
			}
	
		}

		publishProgress((int)( myimage2.getHeight() /4));
for (int k = 1; k < myimage2.getHeight()-2; k++)
{
	for (int i = 1 ; i < myimage2.getWidth()-2; i++)
	{
		int k2 = myimage2.getHeight() - (k+1);
		
		if(bgpixels[i+k2*bgwidth] == lbrown)
			if(bgpixels[(i-1)+(k2+1)*bgwidth] == main ||
			   bgpixels[(i)+(k2+1)*bgwidth] == main ||
			   bgpixels[(i+1)+(k2+1)*bgwidth] == main ||
			   bgpixels[(i+1)+(k2)*bgwidth] == main ||
			   bgpixels[(i-1)+(k2)*bgwidth] == main)
			   bgpixels[(i)+(k2)*bgwidth] = main;
		
//		if (myimage2.getPixel(i, k2) == lbrown)
//			if (myimage2.getPixel(i-1, k2+1) == main ||
//				myimage2.getPixel(i, k2 + 1) == main ||
//				myimage2.getPixel(i+1, k2+1) == main ||
//				myimage2.getPixel(i+1, k2) == main ||
//				myimage2.getPixel(i-1, k2) == main)
//				myimage2.setPixel(i, k2, main);
	}

}
publishProgress((int)( myimage2.getHeight() /2));
for (int i = 1; i < myimage2.getWidth()-2; i++)
{
	for (int k = 1 ; k < myimage2.getHeight()-2; k++)
	{
		if(bgpixels[i+k*bgwidth] == lbrown)
			if(bgpixels[(i-1)+(k-1)*bgwidth] == main ||
			   bgpixels[(i)+(k-1)*bgwidth] == main ||
			   bgpixels[(i+1)+(k-1)*bgwidth] == main ||
			   bgpixels[(i-1)+(k)*bgwidth] == main ||
			   bgpixels[(i+1)+(k)*bgwidth] == main)
			   bgpixels[(i)+(k)*bgwidth] = main;
//		if (myimage2.getPixel(i, k) == lbrown)
//			if (myimage2.getPixel(i-1, k-1) == main ||
//				myimage2.getPixel(i, k-1) == main ||
//				myimage2.getPixel(i+1, k-1) == main ||
//				myimage2.getPixel(i-1, k) == main ||
//				myimage2.getPixel(i+1, k) == main)
//				myimage2.setPixel(i, k, main);
	}

}
publishProgress((int)( myimage2.getHeight() * .75));

for (int i = 1; i < myimage2.getWidth()-2; i++)
{
	for (int k = 1 ; k < myimage2.getHeight()-2; k++)
	{
		int k2 = myimage2.getHeight() - (k+1);
		
		if(bgpixels[i+k2*bgwidth] == lbrown)
			if(bgpixels[(i-1)+(k2+1)*bgwidth] == main ||
			   bgpixels[(i)+(k2+1)*bgwidth] == main ||
			   bgpixels[(i+1)+(k2+1)*bgwidth] == main ||
			   bgpixels[(i+1)+(k2)*bgwidth] == main ||
			   bgpixels[(i-1)+(k2)*bgwidth] == main)
			   bgpixels[(i)+(k2)*bgwidth] = main;
//		if (myimage2.getPixel(i, k2) == lbrown)
//			if (myimage2.getPixel(i-1, k2+1) == main ||
//				myimage2.getPixel(i, k2 + 1) == main ||
//				myimage2.getPixel(i+1, k2+1) == main ||
//				myimage2.getPixel(i+1, k2) == main ||
//				myimage2.getPixel(i-1, k2) == main)
//				myimage2.setPixel(i, k2, main);
	}

}

publishProgress(myimage2.getHeight());
myimage2.setPixels(bgpixels, 0, bgwidth, 0, 0, bgwidth, myimage2.getHeight());
//thefinalresult = Bitmap.createScaledBitmap(myimage2, Math.round(myimage2.getWidth()*3), Math.round(myimage2.getHeight()*3), false);
		thefinalresult = myimage2;
		return true;
		
	}




	@Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		super.onPreExecute();
		
		for (int i = 0; i< buttons.length; i++)
			buttons[i].setClickable(false);
		
		
//		buttons[17].setClickable(false);
		
		pb.setVisibility(View.VISIBLE);
		sv.setEnabled(false);
		pb.setIndeterminate(false);

	}


	@Override
	protected void onProgressUpdate(Integer... values) {
		// TODO Auto-generated method stub
		super.onProgressUpdate(values);
		pb.setProgress(values[0]);
//		b.setText(String.valueOf(values[0]));
	}
	@Override
	protected void onPostExecute(Boolean result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
		
		for (int i = 0; i< buttons.length; i++)
			buttons[i].setClickable(true);
		
//		buttons[17].setClickable(true);
		
		pb.setVisibility(View.INVISIBLE);

		sv.setEnabled(true);
		sv.initialize2(thefinalresult);
	}
}
