package com.ehsan.test.again;

import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.hardware.Camera.Size;
import android.view.View;

public class PS_CameraInfo implements NeedsCameraParameters{
	
	private View parent;
	private View child;
	
	private Parameters frontParameters;
	private Parameters backParameters;
	
	private Size bestFrontPreviewSize;
	private Size bestFrontPictureSize;
	private Size bestBackPreviewSize;
	private Size bestBackPictureSize;
	
	private CameraStarter cameraStarter;
	
	public PS_CameraInfo(View parent, View child, CameraStarter cameraStarter){
		this.child = child;
		this.parent = parent;
		this.cameraStarter = cameraStarter;
	}
	
	public void getCameraPrameters(){
		CameraParametersManager cPM = new CameraParametersManager(this, parent, child);
		cPM.execute();
	}

	public void setFrontCameraParameters(Parameters parameters) {
		frontParameters = parameters;
	}

	public void setBackCameraParameters(Parameters parameters) {
		backParameters = parameters;
	}

	public void useParameters() {

		cameraStarter.startCamera();
	}

	public void setFronCameraBestPictureSize(Size size) {
		this.bestFrontPictureSize = size;
		
	}

	public void setBackCameraBestPictureSize(Size size) {
		this.bestBackPictureSize = size;
		
	}

	public void setFronCameraBestPreviewSize(Size size) {
		this.bestFrontPreviewSize = size;
		
	}

	public void setBackCameraBestPreviewSize(Size size) {
		this.bestBackPreviewSize = size;
		
	}

	
	public Parameters getFrontCameraParameters() {
		return frontParameters;
	}

	public Parameters getBackCameraParameters() {
		return backParameters;
	}


	public Size getFronCameraBestPictureSize() {
		return bestFrontPictureSize;
		
	}

	public Size getBackCameraBestPictureSize() {
		return bestBackPictureSize;
		
	}

	public Size getFronCameraBestPreviewSize() {
		return bestFrontPreviewSize;
		
	}

	public Size getBackCameraBestPreviewSize() {
		return bestBackPreviewSize;
		
	}
}
