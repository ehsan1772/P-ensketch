package com.ehsan.test.again;

import java.io.Serializable;

import android.app.Activity;
import android.os.Bundle;

public class PS_CameraActivity_onMeasure extends Activity {

	public PS_CameraInfo cameraInfo;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Bundle b = getIntent().getExtras();
		Serializable s = b.getSerializable("com.ehsan.test.again.CameraInfo");
		cameraInfo = (PS_CameraInfo) s;
		this.setContentView(R.layout.ps_camera_onmeasure);
		super.onCreate(savedInstanceState);
	}

	
}
