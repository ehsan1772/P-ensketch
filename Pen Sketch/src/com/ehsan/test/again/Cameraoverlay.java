package com.ehsan.test.again;

import java.io.IOException;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.Button;

public class Cameraoverlay extends SurfaceView implements SurfaceHolder.Callback, Runnable
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

	
	public Thread ourthread = null;
	boolean isvalidb;
	boolean isvalidb2;
	int wtd = 0;
    int defaultSQ;

    public Bitmap cropbg;
    float stroke = 0;
    Button scaleb1;
    int alpha = 80;
    Paint yellow;
    
    public Bitmap background;
    public Bitmap backgroundscaled;
    public int h2;
    

	
	
	void init()
	{
		  mHolder = getHolder();
		  mHolder.addCallback(this);
		  
		  	yellow = new Paint();
		  	yellow.setStyle(Paint.Style.FILL);
		  	yellow.setStrokeWidth(5);
		  	yellow.setColor(Color.argb(200, 0, 0, 0));

	}
    
	public Cameraoverlay(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		
		// TODO Auto-generated constructor stub
		init();

	}


	public Cameraoverlay(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		
		init();
 
	}


	@SuppressWarnings("deprecation")
	public Cameraoverlay(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		
		init();

	}
	


	public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub
		
	}

	public void surfaceCreated(SurfaceHolder arg0) {
		// TODO Auto-generated method stub
		
			int w = this.getWidth();
			int h = this.getHeight();
			
			h2= this.getHeight() - this.getWidth();
			h2 = Math.round(h2/2);
			
		  	Canvas cn = mHolder.lockCanvas();
		  	
//			cn.drawColor(Color.BLACK);
		  	
		  	cn.drawRect(0, 0, w, h2, yellow);
		  	cn.drawRect(0, w + h2, w, h, yellow);
		  	
		  	mHolder.unlockCanvasAndPost(cn);
		
	}
	
	public void initialize()
	{
		int w = this.getWidth();
		int h = this.getHeight();
		
		int h2= this.getHeight() - this.getWidth();
		h2 = Math.round(h2/2);
		
	  	Canvas cn = mHolder.lockCanvas();
	  	
//		cn.drawColor(Color.BLACK);
	  	
	  	cn.drawRect(0, 0, w, h2, yellow);
	  	cn.drawRect(0, w + h2, w, h, yellow);
	  	
	  	mHolder.unlockCanvasAndPost(cn);
	}

	public void surfaceDestroyed(SurfaceHolder arg0) {
		// TODO Auto-generated method stub
		
	}

	public void run() {
		// TODO Auto-generated method stub
		
	}
	

	

}
