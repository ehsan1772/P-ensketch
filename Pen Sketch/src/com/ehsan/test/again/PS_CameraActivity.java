package com.ehsan.test.again;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Camera;
import android.hardware.Camera.Size;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class PS_CameraActivity extends Activity implements CameraStarter{
	
	private View cameraOverlay;
	private View pSCameraRoot;
	public boolean camerafront = false;
	public Size thesize;
	PS_CameraView cv;
	PS_CameraView2 cv2;
	 //RelativeLayout mlayout;
	 FrameLayout mlayout;
	
	private PS_CameraInfo cameraInfo;
	private ImageButton btest;
	
	//private Size theSize;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
	    setContentView(R.layout.ps_camera);
	 //   setContentView(R.layout.cameratest);
	 //   cameraOverlay = findViewById(R.id.cameraoverlay);
	 
	    pSCameraRoot = findViewById(R.id.ps_camera_root);
	    btest = (ImageButton)findViewById(R.id.imageButton222);

		//cameraInfo = new PS_CameraInfo(pSCameraRoot, cameraOverlay, PS_CameraActivity.this);
		cameraInfo = new PS_CameraInfo(pSCameraRoot, pSCameraRoot, PS_CameraActivity.this);
		cameraInfo.getCameraPrameters();

		
		btest.setOnClickListener(new OnClickListener(){

			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Log.d("Here!", "Here");
				onResume();
			//	mlayout.bringToFront();
			}});
		super.onCreate(savedInstanceState);
		
		
	}

	@Override
	protected void onStart() {
		Log.d("Here!", "onStart");

		//theSize = cameraInfo.getBestPictureSize2(pSCameraRoot, cameraOverlay, p)
		
		super.onStart();
		

	}
	
	

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		Log.d("Here!", "onResume");
		super.onResume();


	}


	public void startCamera() {






//	relativelayout1.bringToFront();
	
	//DoCamera doCamera = new DoCamera();
	//doCamera.execute();
		
		thesize = cameraInfo.getBackCameraBestPreviewSize();
	//	thesize = new Size(720,960);
		
		thesize.height = 720;
		thesize.width = 960;
		

		
      //  cv = new PS_CameraView(PS_CameraActivity.this, cameraInfo, PS_CameraActivity.this.getWindowManager().getDefaultDisplay());
		
        cv2 = new PS_CameraView2(this);
		
		Log.d("The CV2 Size = ", cv2.getHeight() + " , " + cv2.getWidth());
		
    //    cv2.init();
		
		cv2.mactivity = this;
		
	//	cv.mactivity = this;
	
	//	cv2.setVisibility(View.VISIBLE);

		Log.d("The CV2 Size = ", cv2.getHeight() + " , " + cv2.getWidth());
//RelativeLayout.LayoutParams param = new RelativeLayout.LayoutParams(thesize.height,thesize.width);
		
		Log.d("The Size = ", cameraInfo.getBackCameraBestPreviewSize().height + " , " + cameraInfo.getBackCameraBestPreviewSize().width);
		
		RelativeLayout.LayoutParams param = new RelativeLayout.LayoutParams(720, 960);
		
//		RelativeLayout.LayoutParams param = new RelativeLayout.LayoutParams(cameraInfo.getBackCameraBestPreviewSize().height,cameraInfo.getBackCameraBestPreviewSize().width);
		
	//	RelativeLayout.LayoutParams param = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);



param.addRule(RelativeLayout.CENTER_HORIZONTAL);
//param.topMargin = 130;	

// mlayout = (RelativeLayout) findViewById(R.id.cameralayout);
FrameLayout mlayout = (FrameLayout) findViewById(R.id.preview);

//	cv2.setVisibility(View.VISIBLE);
	
mlayout.addView(cv2, param);

	//	cv.setCamera();
		
		mlayout.bringToFront();
		Log.d("The CV2 Size = ", cv2.getHeight() + " , " + cv2.getWidth());
	//	cv2.start();
	}
	
	private class DoCamera extends AsyncTask {

		@Override
		protected Object doInBackground(Object... arg0) {
			

			return null;
		}
		
	}
	
	

}
