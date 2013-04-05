package com.ehsan.test.again;

import java.io.Serializable;

import android.app.Activity;

import com.adwhirl.AdWhirlLayout;

public class MyAd extends AdWhirlLayout implements Serializable {

	private static final long serialVersionUID = 7732296906445200855L;

	MyAd(Activity context, String key){
		super(context, key);
	}

}
