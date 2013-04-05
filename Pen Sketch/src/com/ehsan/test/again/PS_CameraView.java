package com.ehsan.test.again;

import java.io.IOException;
import java.util.List;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
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
import android.view.ViewGroup;
import android.widget.Toast;

//public class PS_CameraView  extends ViewGroup implements SurfaceHolder.Callback{
public class PS_CameraView  extends SurfaceView implements SurfaceHolder.Callback{
		
	
	private SurfaceHolder mHolder;
	private Loadbitmap2 bgw2;
	MainActivityforpensketch mactivity;
	private android.hardware.Camera camera;
	boolean ISFRONT;
	private int rotater;
	private Size thepicsize;
	
	private PS_CameraInfo cameraInfo;
	private Display display;
	private Context mContext;
//	private SurfaceView mSurfaceView;
	
	@SuppressWarnings("deprecation")
	void init(){
		
//        mSurfaceView = new SurfaceView(mContext);
//        addView(mSurfaceView);
        
		  mHolder = this.getHolder();
		  mHolder.addCallback(this);
		  mHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
		//  cameraOpener();
		//  setCamera();
		  
	}
	

			 
	public PS_CameraView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}


	public PS_CameraView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}


	@SuppressWarnings("deprecation")
	public PS_CameraView(Context context, PS_CameraInfo cameraInfo, Display display) {
		super(context);
		this.cameraInfo = cameraInfo;
		this.display = display;
		this.mContext = context;
		init();
	}
	

//	public void setCamera() {
//		//Display display = mactivity.getWindowManager().getDefaultDisplay();
//		//thepicsize = getBestPictureSize(mactivity.thesize);
//		thepicsize = cameraInfo.getBackCameraBestPictureSize();
//		rotater = 0;
//
//        switch (display.getRotation()) {
//            case Surface.ROTATION_0:
//                rotater = 90;
//                break;
//            case Surface.ROTATION_270:
//                rotater = 180;
//                break;
//        } 
//        
//		try {
//		//	camera = android.hardware.Camera.open();
//			camera.setPreviewDisplay(mHolder);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		
//		Camera.Parameters param = camera.getParameters();
//	//	param.setPreviewSize(mactivity.thesize.width, mactivity.thesize.height);
//	//	param.setPictureSize(thepicsize.width, thepicsize.height);
//		
//		Log.e("Best preview size is:", cameraInfo.getBackCameraBestPreviewSize().width + " , " +  cameraInfo.getBackCameraBestPreviewSize().height);
//		Log.e("Best pic size is:", cameraInfo.getBackCameraBestPictureSize().width + " , " +  cameraInfo.getBackCameraBestPictureSize().height);
//		
//		param.setPreviewSize(cameraInfo.getBackCameraBestPreviewSize().width, cameraInfo.getBackCameraBestPreviewSize().height);
//		param.setPictureSize(cameraInfo.getBackCameraBestPictureSize().width, cameraInfo.getBackCameraBestPictureSize().height);
//		
//		if(param.isZoomSupported())
//			param.setZoom(0);
//		
//		camera.setDisplayOrientation(rotater);
//		camera.setParameters(param);      
//        camera.startPreview();
//	}
	
	@SuppressLint("NewApi")
	public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3) {

		//Display display = mactivity.getWindowManager().getDefaultDisplay();
		//thepicsize = getBestPictureSize(mactivity.thesize);
		thepicsize = cameraInfo.getBackCameraBestPictureSize();
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
	//	param.setPreviewSize(mactivity.thesize.width, mactivity.thesize.height);
	//	param.setPictureSize(thepicsize.width, thepicsize.height);
		
		Log.e("Best preview size is:", cameraInfo.getBackCameraBestPreviewSize().width + " , " +  cameraInfo.getBackCameraBestPreviewSize().height);
		Log.e("Best pic size is:", cameraInfo.getBackCameraBestPictureSize().width + " , " +  cameraInfo.getBackCameraBestPictureSize().height);
		
		param.setPreviewSize(cameraInfo.getBackCameraBestPreviewSize().width, cameraInfo.getBackCameraBestPreviewSize().height);
		param.setPictureSize(cameraInfo.getBackCameraBestPictureSize().width, cameraInfo.getBackCameraBestPictureSize().height);
		
		if(param.isZoomSupported())
			param.setZoom(0);
		
		camera.setDisplayOrientation(rotater);
		camera.setParameters(param);      
        camera.startPreview();
	}
	
	

	@TargetApi(9)
	public void surfaceCreated(SurfaceHolder arg0) {
		

		try {
			camera = android.hardware.Camera.open();
			camera.setPreviewDisplay(mHolder);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	
	private void cameraOpener(){
		if (camera == null)
			camera = android.hardware.Camera.open();
			
	}
	
	

	public void surfaceDestroyed(SurfaceHolder arg0) {
		camera.stopPreview();
		camera.release();
		camera=null;
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



	@Override
	protected void onLayout(boolean arg0, int arg1, int arg2, int arg3, int arg4) {
		// TODO Auto-generated method stub
		
	}
	

	
}
