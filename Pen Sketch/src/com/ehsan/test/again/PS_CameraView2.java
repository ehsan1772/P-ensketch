package com.ehsan.test.again;
import java.io.IOException;
import java.util.List;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.PixelFormat;
import android.hardware.Camera;
import android.hardware.Camera.PictureCallback;
import android.hardware.Camera.ShutterCallback;
import android.hardware.Camera.Size;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.Toast;



public class PS_CameraView2 extends SurfaceView implements SurfaceHolder.Callback{
	
	private SurfaceHolder mHolder;
	private Loadbitmap2 bgw2;
	PS_CameraActivity mactivity;
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
	



	@SuppressWarnings("deprecation")
	public PS_CameraView2(Context context) {
		super(context);
		mContext = context;
		

		init();
	}
	


	@SuppressLint("NewApi")
	public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3) {

		Log.d("Surface state = ", "Changed!!");
		Log.d("The CV2 Size = ", this.getHeight() + " , " + this.getWidth());
		
    
		
        camera.startPreview();     
        inPreview = true;
	}
	
	

	@TargetApi(9)
	public void surfaceCreated(SurfaceHolder arg0) {
		
		Log.d("Surface state = ", "Created!!");
		
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
	    		Toast toast = Toast.makeText(mactivity.getBaseContext(), "This feature is not available on your device! Please contact us with your device information.", Toast.LENGTH_LONG);
	    		toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
	    		toast.show();
	    		camera = android.hardware.Camera.open();
	    		ISFRONT = false;
	    		//mactivity.camerafront = false;
			}
			
		}
		
		Display display = mactivity.getWindowManager().getDefaultDisplay();
		rotater = 0;

        switch (display.getRotation()) {
            case Surface.ROTATION_0:
                rotater = 90;
                break;
            case Surface.ROTATION_270:
                rotater = 180;
                break;
        } 
        
		

		
//		if(param.isZoomSupported())
//			param.setZoom(0);
		
		camera.setDisplayOrientation(rotater);
		
		thepicsize = getBestPictureSize(mactivity.thesize);
		Camera.Parameters param = camera.getParameters();
		param.setPreviewSize(mactivity.thesize.width, mactivity.thesize.height);
		param.setPictureSize(thepicsize.width, thepicsize.height);
		camera.setParameters(param);

		try {
			camera.setPreviewDisplay(mHolder);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	//	Camera.Parameters param = camera.getParameters();
	//	Log.d("Preview Size = ", mactivity.thesize.width + " , " + mactivity.thesize.height);
	//	param.setPreviewSize(mactivity.thesize.width, mactivity.thesize.height);
	//	Log.d("Pic Size = ", thepicsize.width + " , " + thepicsize.height);
	//	param.setPictureSize(thepicsize.width, thepicsize.height);
	//	
	//	if(param.isZoomSupported())
	//		param.setZoom(0);
		
	//	camera.setDisplayOrientation(rotater);
	//	camera.setParameters(param);      
     //   camera.startPreview();     
     //   inPreview = true;
	}
	
	

	public void surfaceDestroyed(SurfaceHolder arg0) {
		
		Log.d("Surface state = ", "Destroyed!!");
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
//					Object[] oarray = null;
//					bgw2 = new Loadbitmap2(arg0, mactivity, ISFRONT);
//					bgw2.execute(oarray);
//				 	camera.stopPreview();
	 }};
			 
	public void takepic() {
		   camera.takePicture(myShutterCallback, myPictureCallback_RAW, myPictureCallback_JPG);
	}
	

}
