package com.ehsan.test.again;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.List;


import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;

import android.graphics.Point;
import android.hardware.Camera;
import android.hardware.Camera.PictureCallback;
import android.hardware.Camera.ShutterCallback;
import android.hardware.Camera.Size;
import android.util.AttributeSet;

import android.view.Display;
import android.view.Gravity;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.Toast;



public class Cameraview extends SurfaceView implements SurfaceHolder.Callback, Runnable
{
	SurfaceHolder mHolder;
	Loadbitmap2 bgw2;
	public MainActivityforpensketch mactivity;
	public android.hardware.Camera camera;
    boolean isfront;
    public boolean ISFRONT;
	boolean inPreview;
	int theheight;
	int thewidth;
	float minratio;
	PictureCallback raw;
	PictureCallback jpeg;
	Context mContext;
	int rotater;
	int counterrr;
	Size thepreviewsize;
	Size thepicsize;
	List<Size> sizes;
	int num;


	


	@SuppressWarnings("deprecation")
	void init()
	{
		  mHolder = getHolder();
		  
		  mHolder.addCallback(this);

		  mHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
		  counterrr = 0;

		  


	}
	

			 
	public Cameraview(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		
		// TODO Auto-generated constructor stub
		mContext = context;
		init();
		
		

	}


	public Cameraview(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		mContext = context;
		init();
 
	}


	@SuppressWarnings("deprecation")
	public Cameraview(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		mContext = context;
		init();

	}
	


	@SuppressLint("NewApi")
	public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub

		
		 Display display = mactivity.getWindowManager().getDefaultDisplay();

		
	//		sizes = camera.getParameters().getSupportedPictureSizes();
		thepicsize = getBestPictureSize(mactivity.thesize);
//		
	


		rotater = 0;

        switch (display.getRotation())
        {
            case Surface.ROTATION_0:
                rotater = 90;
                break;
            case Surface.ROTATION_270:
                rotater = 180;
                break;
        } 
        
		
		Camera.Parameters param = camera.getParameters();
		

		param.setPreviewSize(mactivity.thesize.width, mactivity.thesize.height);

		param.setPictureSize(thepicsize.width, thepicsize.height);
		
		if(param.isZoomSupported())
		{
		param.setZoom(0);
		}
		camera.setDisplayOrientation(rotater);
 
		camera.setParameters(param);      

		
        camera.startPreview();     
        inPreview = true;
		
	}
	
	
	public void showsize()
	{
		 Size s = sizes.get(counterrr);
		String toshow = String.valueOf(s.height) + " , " + String.valueOf(s.width);
		Toast toast2 = Toast.makeText(mactivity, toshow , Toast.LENGTH_LONG);
		toast2.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
	toast2.show();
	counterrr++;
	}

	@TargetApi(9)
	public void surfaceCreated(SurfaceHolder arg0) {
		// TODO Auto-generated method stub
		
		if (!mactivity.camerafront)
		{
//			Object[] oarray = null;
//			Opencamera2 bgw2 = new Opencamera2(this,0);
//			
//			 bgw2.execute(oarray);
		camera = android.hardware.Camera.open();
		ISFRONT = false;
		}
		else
		{
//			Object[] oarray = null;
//			Opencamera2 bgw2 = new Opencamera2(this,1);
//			
//			bgw2.execute(oarray);
			try{
			camera = android.hardware.Camera.open(1);	
			ISFRONT = true;
			}
			catch(Throwable b)
			{
	    		Toast toast = Toast.makeText(mactivity.context, "This feature is not available on your device! Please contact us with your device information.", Toast.LENGTH_LONG);
	    		toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
	    		toast.show();
	    		camera = android.hardware.Camera.open();
	    		ISFRONT = false;
	    		mactivity.camerafront = false;
			}
			
		}

		try {
			camera.setPreviewDisplay(mHolder);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		
	}
	
	public void surfaceCreated2(android.hardware.Camera camerareturned) {
		// TODO Auto-generated method stub
		
		camera = camerareturned;

		try {
			camera.setPreviewDisplay(mHolder);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		
	}
	
	

	public void surfaceDestroyed(SurfaceHolder arg0) {
		// TODO Auto-generated method stub

		camera.stopPreview();
		camera.release();
		inPreview = false;
		camera=null;
	}

	public void run() {
		// TODO Auto-generated method stub
		
	}
	

	
	private Camera.Size getBestPictureSize(Size previewsize)
	{
	        Camera.Size result=null;    
	        double sh,sw,sratio,pw,ph,pratio,rh,rw,rratio = 0;
	        ph = previewsize.height;
	        pw = previewsize.width;
	        pratio = ph/pw;


	        Camera.Parameters p = camera.getParameters();
	        for (Camera.Size size : p.getSupportedPictureSizes()) 
	        {
		        sh = size.height;
		        sw = size.width;
		        sratio = sh/sw;
	        	if (sratio == pratio)
	        	{
	        		result = size;
	        		return result;
	        	}
	        }

	        for (Camera.Size size : p.getSupportedPictureSizes()) {

                if (result==null) {
                    result=size;
    		        rh = size.height;
    		        rw = size.width;
    		        rratio = rh/rw;

                } else {
    		        sh = size.height;
    		        sw = size.width;
    		        sratio = sh/sw;
                	if (Math.abs(sratio - pratio) < Math.abs(rratio - pratio))
                	{
                		result = size;
        		        rh = size.height;
        		        rw = size.width;
        		        rratio = rh/rw;
                	}

                }
            }
        
	    return result;

	}
	


	
//	@SuppressLint({ "NewApi", "NewApi" })
	public void restartPreview(boolean isFront) {

		isfront = isFront;
	    if (inPreview) {
	        camera.stopPreview();
	    }
	    
	    camera.release();
	    camera = null;
	    
	    
	    int camId = Camera.CameraInfo.CAMERA_FACING_BACK;
	    
	    if (isFront) {

	        camera = Camera.open(camId);
	        ISFRONT = false;

	    } else {
	    	try{

	        camera = Camera.open(camId +1);
	    	}
	    	catch (Exception e)
	    	{
	    		camera = Camera.open(camId);
	    	}
	        ISFRONT = true;

	    }
	        
      
			Camera.Parameters param = camera.getParameters();
			mactivity.thesize = mactivity.getBestPictureSize2(mactivity.mlayout.getWidth(), mactivity.mlayout.getHeight(), param);


	}
	
	  ShutterCallback myShutterCallback = new ShutterCallback(){

		  public void onShutter() {
		   // TODO Auto-generated method stub
		  
		  }};
	  
	PictureCallback myPictureCallback_RAW = new PictureCallback(){

		 public void onPictureTaken(byte[] arg0, Camera arg1) {
		  // TODO Auto-generated method stub
		 
		 }};
    
		 PictureCallback myPictureCallback_JPG = new PictureCallback(){

			 public void onPictureTaken(byte[] arg0, Camera arg1) {
			  // TODO Auto-generated method stub
				 

					Object[] oarray = null;
					bgw2 = new Loadbitmap2(arg0, mactivity, ISFRONT);

					
					bgw2.execute(oarray);
						         
				 	camera.stopPreview();

					

			 }};
			 
	public void takepic()
	{


		   camera.takePicture(myShutterCallback,
				     myPictureCallback_RAW, myPictureCallback_JPG);
	}
	

	 
}
