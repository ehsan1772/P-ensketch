package com.ehsan.test.again;

import android.hardware.Camera.Parameters;
import android.hardware.Camera.Size;

public interface NeedsCameraParameters {
	
	public void setFrontCameraParameters(Parameters parameters);
	public void setBackCameraParameters(Parameters parameters);
	
	public void setFronCameraBestPictureSize(Size size);
	public void setBackCameraBestPictureSize(Size size);
	
	public void setFronCameraBestPreviewSize(Size size);
	public void setBackCameraBestPreviewSize(Size size);
	
	public void useParameters();

}
