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



public class Cameraview extends SurfaceView implements SurfaceHolder.Callback
{
	private SurfaceHolder mHolder;
	private Loadbitmap2 bgw2;
	MainActivityforpensketch mactivity;
	private android.hardware.Camera camera;
	private boolean isfront;
	boolean ISFRONT;
	private boolean inPreview;
	private int theheight;
	private int thewidth;
	private float minratio;
	private PictureCallback raw;
	private PictureCallback jpeg;
	private Context mContext;
	private int rotater;
	private int counterrr;
	private Size thepreviewsize;
	private Size thepicsize;
	private List<Size> sizes;
	private int num;
	

	@SuppressWarnings("deprecation")
	void init(){
		  mHolder = getHolder();
		  mHolder.addCallback(this);
		  mHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
		  counterrr = 0;
	}
	

			 
	public Cameraview(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		mContext = context;
		init();
	}


	public Cameraview(Context context, AttributeSet attrs) {
		super(context, attrs);
		mContext = context;
		init();
	}


	@SuppressWarnings("deprecation")
	public Cameraview(Context context) {
		super(context);
		mContext = context;
		init();
	}
	


	@SuppressLint("NewApi")
	public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3) {

		Display display = mactivity.getWindowManager().getDefaultDisplay();
		thepicsize = getBestPictureSize(mactivity.thesize);
		rotater = 0;

        switch (display.getRotation()) {
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
			param.setZoom(0);
		
		camera.setDisplayOrientation(rotater);
		camera.setParameters(param);      
        camera.startPreview();     
        inPreview = true;
	}
	
	

	@TargetApi(9)
	public void surfaceCreated(SurfaceHolder arg0) {
		
		if (!mactivity.camerafront) {
			camera = android.hardware.Camera.open();
			ISFRONT = false;
		}
		else
		{
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
			e.printStackTrace();
		}
		
		
	}
	
	

	public void surfaceDestroyed(SurfaceHolder arg0) {
		camera.stopPreview();
		camera.release();
		inPreview = false;
		camera=null;
	}

	
	private Camera.Size getBestPictureSize(Size previewsize) {
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
	


	
	ShutterCallback myShutterCallback = new ShutterCallback(){ public void onShutter() { }};
	  
	PictureCallback myPictureCallback_RAW = new PictureCallback(){ public void onPictureTaken(byte[] arg0, Camera arg1) { }};
    
	PictureCallback myPictureCallback_JPG = new PictureCallback(){
			 public void onPictureTaken(byte[] arg0, Camera arg1) {
					Object[] oarray = null;
					bgw2 = new Loadbitmap2(arg0, mactivity, ISFRONT);
					bgw2.execute(oarray);
				 	camera.stopPreview();
	 }};
			 
	public void takepic() {
		   camera.takePicture(myShutterCallback, myPictureCallback_RAW, myPictureCallback_JPG);
	}
	

	 
}
