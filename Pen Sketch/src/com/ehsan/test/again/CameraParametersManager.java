package com.ehsan.test.again;

import android.hardware.Camera.CameraInfo;
import android.hardware.Camera.Parameters;
import android.hardware.Camera.Size;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.hardware.Camera;

public class CameraParametersManager extends AsyncTask<Object, Object, Object> {
	
	private NeedsCameraParameters client;
	private final static int FRONT_CAMERA_ID = Camera.CameraInfo.CAMERA_FACING_FRONT;
	private final static int BACK_CAMERA_ID = Camera.CameraInfo.CAMERA_FACING_BACK;
	private final static int CAMERA_COUNT = Camera.getNumberOfCameras();
	
	private View parent;
	private View child;
	
	private Parameters frontParameters;
	private Parameters backParameters;
	
	private Size bestFrontPreviewSize;
	private Size bestFrontPictureSize;
	
	private Size bestBackPreviewSize;
	private Size bestBackPictureSize;
	

	
	public CameraParametersManager(NeedsCameraParameters client, View parent, View child) {
		this.client = client;
		this.parent = parent;
		this.child = child;
	}

	@Override
	protected Object doInBackground(Object... arg0) {
		
		
		backParameters = getCameraParameters(BACK_CAMERA_ID);
		frontParameters = getCameraParameters(FRONT_CAMERA_ID);
		
		bestBackPreviewSize = calculateBestPreviewSize(parent, child, backParameters);
		bestFrontPreviewSize = calculateBestPreviewSize(parent, child, frontParameters);
		
		bestFrontPictureSize = calculateBestPictureSize(bestFrontPreviewSize, frontParameters);
		bestBackPictureSize = calculateBestPictureSize(bestBackPreviewSize, backParameters);
		
		return null;
	}

	@Override
	protected void onPostExecute(Object result) {
		
		client.setBackCameraParameters(backParameters);
		client.setFrontCameraParameters(frontParameters);
		
		client.setBackCameraBestPreviewSize(bestBackPreviewSize);
		client.setFronCameraBestPreviewSize(bestFrontPreviewSize);
		
		client.setBackCameraBestPictureSize(bestBackPictureSize);
		client.setFronCameraBestPictureSize(bestFrontPictureSize);
		
		client.useParameters();
		
		super.onPostExecute(result);
	}
	
	private Camera.Size calculateBestPreviewSize(View parent, View child, Parameters p)
	{
		android.hardware.Camera.Size result=null;  
	    double margin = getMargin(child);
	    
	    double idealHeight = parent.getWidth();
	    double idealWidth = parent.getWidth() - margin;


	        for (android.hardware.Camera.Size size : p.getSupportedPreviewSizes()) {

                if (result==null) {
                    result=size;
                } 
                

        		
                if (size.height == idealWidth && size.width <= idealHeight) {
                	result = size;
                	return result;
                }
                
                if (Math.abs(result.height - idealWidth) > Math.abs(size.height - idealWidth) &&
                	size.width <= idealHeight &&
                	size.height <= idealWidth){
                	result = size;
                }
            }
        
	        if (result.width > idealHeight ||
                result.height > idealWidth) {
                result = null;
	        }
	        
	        
	        
	    return result;

	}
	
	private Camera.Size calculateBestPictureSize(Size previewsize, Parameters p) {
        Camera.Size result=null;    
        double sh,sw,sratio,pw,ph,pratio,rh,rw,rratio = 0;
        ph = previewsize.height;
        pw = previewsize.width;
        pratio = ph/pw;


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
	
	private double getMargin(View child){
		
		int height = child.getHeight();
		int width = child.getWidth();
		
		int dif = height - width;
		double result = dif / 2;
		
		return result;
		
	}
	
	private Parameters getCameraParameters(int id){
		
		Camera camera;
		Parameters parameters;
		
		if (CAMERA_COUNT > 0) {
			try{camera = Camera.open(id);
				parameters = camera.getParameters();
				camera.release();
				camera = null;
				return parameters;
		    } catch (Exception e) {
		        Log.e("Back Camera", "failed to open Camera");
		        e.printStackTrace();
		    }
		}
		
		return null;
	}
	
	

}
