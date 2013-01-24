package com.ehsan.test.again;

import java.io.IOException;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Region;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.Toast;

public class Sketchview extends SurfaceView implements SurfaceHolder.Callback, Runnable
{
	SurfaceHolder mHolder;
	public float x,y,oldx,oldy, text;
	float placex = 0;
	float placey = 0;
	public boolean isdown = false;
	public boolean isdownfinished = false;
	public Bitmap backgroundoriginal;
	public float squaresize = 50;
	public String filename; 
	Button bb, bb2;
	public boolean sketchloading = false;
	public boolean sketchloaded = false;
	public Bitmap beforesketch;
	public Bitmap onresume;

	
	public Thread ourthread = null;
//	boolean osrunning = false;
	boolean isvalidb;
	boolean isvalidb2;
//	boolean initiate1b;
//	Bitmap backgroundscaled;
	int wtd = 0;
    int defaultSQ;

    public Bitmap cropbg;
    float stroke = 0;
    Button scaleb1;
    int alpha = 80;
    Paint yellow;
    
    public Bitmap background;
    public Bitmap backgroundscaled;
    Context mactivitycontext;
    

	
	
	void init()
	{
		  mHolder = getHolder();
		  mHolder.addCallback(this);
		  ourthread = new Thread(this);

		  oldx = oldy = 0;
		  x = y = 0;
		  
	  	yellow = new Paint();
	  	yellow.setStyle(Paint.Style.STROKE);
	  	yellow.setStrokeWidth(5);
	  	yellow.setColor(Color.YELLOW);

	}
    
	public Sketchview(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		mactivitycontext = context;
		// TODO Auto-generated constructor stub
		init();

	}


	public Sketchview(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		mactivitycontext = context;

		init();
 
	}


	@SuppressWarnings("deprecation")
	public Sketchview(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		mactivitycontext = context;
		init();

	}
	


	public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub
		
	}

	public void surfaceCreated(SurfaceHolder arg0) {
		// TODO Auto-generated method stub
		
	
		if (backgroundscaled != null)
			initialize2(backgroundscaled);
		else
		{
		Bitmap test = BitmapFactory.decodeResource(getResources(), R.drawable.penguins);
		initialize2(test);
		}
	}

	public void surfaceDestroyed(SurfaceHolder arg0) {
		// TODO Auto-generated method stub
		
	}
	
	public void pause(){
		isvalidb2 = false;
	}
	public void resume(){
		isvalidb2 = true;
		
	}
	
	public void invalid2(float x1, float y1)
	{
		isvalidb2 = true;
		
		

		if (!ourthread.isAlive())
		{
			ourthread = new Thread(this);
			ourthread.start();
		}

	}





	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
	}
	

	public void run() {
		// TODO Auto-generated method stub
		while(isvalidb2)
		{
			
			if(!mHolder.getSurface().isValid())
				continue;
			

			placex = x;
			if (x < squaresize )
				placex = squaresize;

				
			if (backgroundscaled.getWidth() - x < squaresize )
				placex = backgroundscaled.getWidth() - squaresize;
			
			
			placey = y;
			if (y < squaresize + wtd)
				placey = squaresize + wtd;

			if ( y > wtd + backgroundscaled.getHeight() - squaresize)
				placey = wtd + backgroundscaled.getHeight() - squaresize;

			
			
		  	Canvas cn = mHolder.lockCanvas();
		  	cn.drawBitmap(backgroundscaled, 0, wtd, null);
		  	cn.drawARGB(160, 0, 0, 0);

		  	Rect rec1 = new Rect((int)(placex - squaresize), (int)(placey - wtd - squaresize), (int)(placex + squaresize), (int)(placey - wtd + squaresize));
		  	Rect rec2 = new Rect((int)(placex - squaresize), (int)(placey  - squaresize), (int)(placex + squaresize), (int)(placey + squaresize));
		  	cn.drawBitmap(backgroundscaled, rec1, rec2, null);

		  	
		  	cn.drawCircle((int)(placex - squaresize), (int)(placey - squaresize), 2, yellow);
		  	cn.drawCircle((int)(placex - squaresize), (int)(placey + squaresize), 2, yellow);
		  	cn.drawCircle((int)(placex + squaresize), (int)(placey - squaresize), 2, yellow);
		  	cn.drawCircle((int)(placex + squaresize), (int)(placey + squaresize), 2, yellow);

		  	
			mHolder.unlockCanvasAndPost(cn);
		}
		
		
	}
	

	public Bitmap initialize(Bitmap image)
	{
		
		
		background = image;

		backgroundscaled = getResizedBitmap(image, 1);
		
		
		
		Canvas cn = mHolder.lockCanvas();
		
		cn.drawBitmap(backgroundscaled, 0, wtd, null);

		mHolder.unlockCanvasAndPost(cn);
		
		return backgroundscaled;
	}
	
	public void camerascreen()
	{
		
		

		
		
		
		Canvas cn = mHolder.lockCanvas();
		
		
		

		

		
		android.hardware.Camera camera = android.hardware.Camera.open();
		try {
			camera.setPreviewDisplay(mHolder);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		camera.startPreview();
		
		mHolder.unlockCanvasAndPost(cn);

//		
		

	}
	
	public void crop()
	{
		
		
		int smaller = 0;
		
		
		
		float wheretodraw = this.getHeight() / 2;
		  float wheretodraw2 = backgroundscaled.getHeight() / 2;
		  float wheretodraw3 = wheretodraw - wheretodraw2;
		  wtd = Math.round(wheretodraw3);
		
	  	Canvas cn = mHolder.lockCanvas();
	  	cn.drawBitmap(backgroundscaled, 0, wtd, null);
	  	placex = backgroundscaled.getWidth()/2;
	  	placey = (backgroundscaled.getHeight()/2) + wtd;
	  	cn.drawARGB(160, 0, 0, 0);
	  	
	  	
		if (backgroundscaled.getHeight() < backgroundscaled.getWidth())
			smaller = backgroundscaled.getHeight();
		else
			smaller = backgroundscaled.getWidth();
		
		squaresize = smaller/3;
		
		
	  	Rect rec1 = new Rect((int)(placex - squaresize), (int)(placey - wtd - squaresize), (int)(placex + squaresize), (int)(placey - wtd + squaresize));
	  	Rect rec2 = new Rect((int)(placex - squaresize), (int)(placey  - squaresize), (int)(placex + squaresize), (int)(placey + squaresize));
	  	cn.drawBitmap(backgroundscaled, rec1, rec2, null);

	  	
	  	cn.drawCircle((int)(placex - squaresize), (int)(placey - squaresize), 2, yellow);
	  	cn.drawCircle((int)(placex - squaresize), (int)(placey + squaresize), 2, yellow);
	  	cn.drawCircle((int)(placex + squaresize), (int)(placey - squaresize), 2, yellow);
	  	cn.drawCircle((int)(placex + squaresize), (int)(placey + squaresize), 2, yellow);
	  	


	  	
		mHolder.unlockCanvasAndPost(cn);
		
		x = placex;
		y = placey;
		
		isvalidb2 = true;
		
		

		if (!ourthread.isAlive())
		{
			ourthread = new Thread(this);
			ourthread.start();
		}
	}
	
	public void cropit()
	{

		
		float scale1 = background.getWidth();
		float scale2 = backgroundscaled.getWidth();
		float scale3 = scale1/scale2;
		float squaresize2; 
		squaresize2 = (squaresize) * (scale3);
		
			
		float placex2 = (placex - squaresize);
		float placey2 = (placey - wtd - squaresize);
		placey2 = placey2 * scale3;

		placex2 = (placex2 * scale3);
		
		if ((int)(placey2) + (2 * (int)squaresize2) > background.getHeight() || placey2 <0)
		{
			placey2 = 1;
			squaresize2 = (background.getHeight() / 2) -4;
		}
		if ((int)(placex2) + (2 * (int)squaresize2) > background.getWidth() || placex2 <0)
		{
			placex2 = 1;
			squaresize2 = (background.getWidth() / 2) -4;
		}
		
		int sq3 = 2 * (int)squaresize2;
		
		if ( sq3 > background.getWidth() || sq3 > background.getHeight())
		{
			if (background.getWidth() > background.getHeight())
				sq3 = background.getHeight();
			else
				sq3 = background.getWidth();
		}

		
		   Bitmap finalized = Bitmap.createBitmap(background,(int)(placex2) , (int)(placey2), sq3, sq3);
		
		initialize2(finalized);

	}
	
	public Bitmap initialize2(Bitmap image)
	{
		

		
		Canvas cn = mHolder.lockCanvas();
		
		cn.drawColor(Color.BLACK);
		
		background = image;
		
		try{
		backgroundscaled = getResizedBitmap(image, 1);

		}
		catch (Throwable b)
		{

		}
		
		cn.drawBitmap(backgroundscaled, 0, wtd, null);

		mHolder.unlockCanvasAndPost(cn);
		
		if (!sketchloading)
		{
			beforesketch = getResizedBitmap(background, 1);
			sketchloaded = false;
		}
		else
		{
			sketchloaded = true;
		}
		return backgroundscaled;
	}
	
	public Bitmap initialize3()
	{
		
	
		Canvas cn = mHolder.lockCanvas();
		

		
	  	yellow = new Paint();
	  	yellow.setStyle(Paint.Style.FILL);
	  	yellow.setStrokeWidth(5);
	  	yellow.setColor(Color.argb(160, 0, 0, 0));
	  	
	  	cn.drawColor(yellow.getColor());

		mHolder.unlockCanvasAndPost(cn);
		
		return backgroundscaled;
	}
	

	public void initialize1()
	{
		
		Bitmap background = BitmapFactory.decodeResource(getResources(), R.drawable.penguins);
		Bitmap precropbg = BitmapFactory.decodeResource(getResources(), R.drawable.cropbg);
		cropbg = getResizedBitmap(precropbg, 2);
		backgroundscaled = getResizedBitmap(background, 1);
		
		Canvas cn = mHolder.lockCanvas();
		
		cn.drawBitmap(backgroundscaled, 0, wtd, null);

		mHolder.unlockCanvasAndPost(cn);
		
	//	postInvalidate();
	}
	
	public Bitmap getResizedBitmap(Bitmap bm, float scale) {
		Bitmap resizedBitmap;
		
try{
	int width = bm.getWidth();

	int frameWidth = (int)(this.getWidth());

	int height = bm.getHeight();

//	if (width > height)
//	{
	float newheight = (frameWidth * height) / width; 
	
	frameWidth = (int)(frameWidth * scale);
	newheight = newheight * scale;
	
	resizedBitmap = Bitmap.createScaledBitmap(bm, frameWidth, (int) newheight, false);
	float wheretodraw = this.getHeight() / 2;
	  float wheretodraw2 = newheight / 2;
	  float wheretodraw3 = wheretodraw - wheretodraw2;
	  wtd = Math.round(wheretodraw3);
}
catch(Throwable b)
{
	Toast toast = Toast.makeText(mactivitycontext, "Pen Sketch couldn't complete the task! Please let us know if it happened again.", Toast.LENGTH_LONG);
	toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
	toast.show();
	
	resizedBitmap = backgroundscaled;
}



	return resizedBitmap;

	}
	
	public void rotate(float angle)
	{

	Matrix matrix = new Matrix();
	matrix.postRotate(angle);
	
	Bitmap rotated = Bitmap.createBitmap(backgroundscaled, 0, 0, backgroundscaled.getWidth(), backgroundscaled.getHeight(), matrix, true);
	this.initialize2(rotated);
	}
	


}
