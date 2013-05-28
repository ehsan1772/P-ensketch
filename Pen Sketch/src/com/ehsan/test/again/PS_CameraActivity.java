package com.ehsan.test.again;

import android.app.Activity;
import android.content.Intent;
import android.hardware.Camera.Size;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

public class PS_CameraActivity extends Activity implements CameraStarter {

	private View cameraOverlay;
	private View pSCameraRoot;
	private View pSCameraView;
	public boolean camerafront = false;
	public Size thesize;
	PS_CameraView cv;
	PS_CameraView2 cv2;
	FrameLayout mlayout;

	public PS_CameraInfo cameraInfo;
	private ImageButton btest;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		setContentView(R.layout.ps_camera);

		pSCameraRoot = findViewById(R.id.ps_camera_root);
		pSCameraView = findViewById(R.id.ps_camera_preview);
		btest = (ImageButton) findViewById(R.id.imageButton222);

		btest.setOnClickListener(new OnClickListener() {

			public void onClick(View arg0) {

				onResume();

			}
		});
		super.onCreate(savedInstanceState);

	}

	@Override
	public void onWindowFocusChanged(boolean hasFocus) {
		// TODO Auto-generated method stub
		super.onWindowFocusChanged(hasFocus);

		cameraInfo = new PS_CameraInfo(pSCameraView,
				PS_CameraActivity.this);
		cameraInfo.getCameraPrameters();
		super.onStart();
	}

	public void startCamera() {
		
		Intent intent = new Intent(this, PS_CameraActivity_onMeasure.class);
		intent.putExtra("com.ehsan.test.again.CameraInfo", cameraInfo);
		startActivity(intent);

//		thesize = cameraInfo.getBackCameraBestPreviewSize();
//
//		cv2 = new PS_CameraView2(this);
//		cv2.mactivity = this;
//	
//	      RelativeLayout.LayoutParams param = new RelativeLayout.LayoutParams(
//	                RelativeLayout.LayoutParams.WRAP_CONTENT,
//	                RelativeLayout.LayoutParams.WRAP_CONTENT);
//
//		param.addRule(RelativeLayout.CENTER_HORIZONTAL);
//
//		FrameLayout mlayout = (FrameLayout) findViewById(R.id.ps_camera_preview);
//
//		mlayout.addView(cv2, param);
//
//		mlayout.bringToFront();

	}

}
