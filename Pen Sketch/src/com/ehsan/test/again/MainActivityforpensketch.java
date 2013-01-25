package com.ehsan.test.again;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.text.DateFormat.Field;
import java.util.Hashtable;
import java.util.List;

import android.R.string;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.ContactsContract.Data;
import android.provider.MediaStore;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.hardware.Camera.Size;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback2;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.SlidingDrawer;
import android.widget.TextView;
import android.widget.Toast;

import com.google.ads.AdRequest;
import com.google.ads.AdSize;
import com.google.ads.AdView;

//import com.komlimobile.*;
//import com.komlimobile.common.*;
//import com.komlimobile.dto.*;
//import com.komlimobile.handler.*;
//import com.komlimobile.sdk.*;

import com.millennialmedia.*;
import com.millennialmedia.android.*;




import com.adwhirl.AdWhirlLayout;
import com.adwhirl.AdWhirlLayout.AdWhirlInterface;
import com.adwhirl.AdWhirlManager;
import com.adwhirl.AdWhirlTargeting;
import com.ehsan.test.again.Sketchview;



@TargetApi(9)
public class MainActivityforpensketch extends Activity implements SurfaceHolder.Callback, OnTouchListener, AdWhirlInterface {
	Button b1;
	Button b2;
	Button b3;
	Button b4;
	Button photob;
	Button photob1;
	Button photob2;
	Button photob3;
	Button cropb;
	Button shareb;
	Button undob;
	Button saveb;
	Button valueb1;
	Button valueb2;
	boolean changethepicture;
	boolean drawcircle;
	Bitmap svpicture;
	boolean down = false;
	ProgressBar pb1;
	boolean tocrop = false;
	RelativeLayout upperlayout;
	SeekBar sbar;
	int strength = 50;
	boolean photom = false;
	boolean camerafront;
	int countersizes = 0;
	Bitmap takenpicture;
	public RelativeLayout mlayout;
	public boolean sketchloaded = false;
	Bitmap scaled;
	backgroundworker1 bgw1;
	Object[] oarray = null;
	int oldstrength;
	Context context;
	Dosketch2 sketch2;
	String color;

	float touchx, touchy;
	Paint ourp;
	float stroke;
	int counter = 0;
	RectF rct;
	int maxsizeforstroke;
    private float mLastTouchX;
    private float mLastTouchY;
    private int mActivePointerId = 0;
    int defaultSQ;
    int thewidth;
    int theheight;

	
	ImageButton ib4;
	RelativeLayout lribbon;
	RelativeLayout lribbon2;
	
	RelativeLayout relativelayout1;
	LinearLayout colorlayout;
	
	SlidingDrawer photosd;
	LinearLayout  photosdlinear;
	
	SlidingDrawer valuesd;
	LinearLayout valuesdlinear;

	
//	AdView adView;
	
	Button b41;
	Button b42;
	
	ImageButton settingib;
	ImageButton photoib;
	ImageView iv;
	ImageView iv4;
//	SurfaceView sv;
	Sketchview sv;
	Cameraview cv;

	Cameraoverlay col;
	OnClickListener l;
	private static final int ref = 0;
	private static final int SELECT_PICTURE = 1;
	Bitmap bm;
	Bitmap bm2;
	Bitmap bm4;
	Bitmap bmp;
	Bitmap bm6;
	View[] setclick;
	
	InputStream input;
	RelativeLayout.LayoutParams layoutParams;
	
//	AdView adView;
	AdWhirlLayout adWhirlLayout;
	RelativeLayout adcontainer;
	
	Canvas canvas2;
	public Size thesize;
	
	boolean cropm = false;
	SketchBackground shsk;
	Opencamera oc;

	// test git
	//test git2
	//this is the change
	
	
//    @Override
//	protected void onStart() {
//		// TODO Auto-generated method stub
//		super.onStart();
//
//		
//	}
//
//
//	@Override
//	protected void onPause() {
//		// TODO Auto-generated method stub
//		super.onPause();
//
//	}


	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
//		Toast toast = Toast.makeText(this, "onRestart", Toast.LENGTH_SHORT);
//		toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
//		toast.show();
		if (photom == true)
		{
			
			photomode("off");
			Bitmap background = BitmapFactory.decodeResource(getResources(), R.drawable.penguins);
			if (sv.backgroundscaled != null)
				background = sv.backgroundscaled;
			sv.initialize2(background);
			mlayout.removeView(cv);

		}
		
		super.onRestart();
	}


//	@Override
//	protected void onStop() {
//		// TODO Auto-generated method stub
//
//
//		super.onStop();
//	}


	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
//		Toast toast = Toast.makeText(this, "onResume", Toast.LENGTH_SHORT);
//		toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
//		toast.show();
		
		super.onResume();

		if(photom == true)
		{

			RelativeLayout mlayout = (RelativeLayout) findViewById(R.id.relativeLayout0);
			mlayout.removeView(cv);
			cv = new Cameraview(this);
			LayoutParams param = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
			mlayout.addView(cv, param);
			col.bringToFront();
//			((View)adView).bringToFront();
			relativelayout1.bringToFront();
			
		}
		else
			sv.resume();

		
	}


	@Override
	public void finish() {
		// TODO Auto-generated method stub

		super.finish();
	}




	@SuppressWarnings("deprecation")
	@SuppressLint("NewApi")
	@Override
    public void onCreate(Bundle savedInstanceState) {
    	requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first3surfaceview);
        
        
        context = this;
        changethepicture = false;
        Bitmap temp;
        drawcircle = false;
        b1 = (Button) findViewById(R.id.button1);
        b2 = (Button) findViewById(R.id.button2);
        b3 = (Button) findViewById(R.id.button3);
        b4 = (Button) findViewById(R.id.button4);
        photob = (Button) findViewById(R.id.photob);
        photob1 = (Button)findViewById(R.id.photob1);
        photob2 = (Button)findViewById(R.id.photob2);
        photob3 = (Button)findViewById(R.id.photob3);
        valueb1 = (Button)findViewById(R.id.valueb1);
        valueb2 = (Button)findViewById(R.id.valueb2);
        cropb = (Button)findViewById(R.id.cropb);
        shareb = (Button)findViewById(R.id.shareb);
        undob = (Button)findViewById(R.id.undob);
        saveb = (Button)findViewById(R.id.saveb);
        ib4 = (ImageButton) findViewById(R.id.imageButton1);
        pb1 = (ProgressBar)findViewById(R.id.pb1);
        lribbon = (RelativeLayout) findViewById(R.id.lowerribbon);    
        relativelayout1 = (RelativeLayout) findViewById(R.id.relativeLayout1); 
        colorlayout = (LinearLayout) findViewById(R.id.colorlayout); 

        photoib = (ImageButton) findViewById(R.id.imageButton1);
        settingib = (ImageButton) findViewById(R.id.imageButton2);
        
        photosd = (SlidingDrawer) findViewById(R.id.slidingDrawer1);
        photosdlinear = (LinearLayout) findViewById(R.id.photosdLinear);
        
        valuesd = (SlidingDrawer) findViewById(R.id.slidingDrawervalue);
        valuesdlinear = (LinearLayout) findViewById(R.id.photosdLinearv);
        sbar = (SeekBar) findViewById(R.id.seekBarv1);
        
        

        photob3.getBackground().setColorFilter(Color.rgb(40, 40, 40), PorterDuff.Mode.SRC_ATOP);

        upperlayout = (RelativeLayout) findViewById(R.id.upperlayout);
        

  //      sv = new Sketchview(this);
		sv = (Sketchview) findViewById(R.id.surfaceView1);
		sv.setOnTouchListener(this);
		

//        col = new Cameraoverlay(this);
        col = (Cameraoverlay) findViewById(R.id.cameraoverlay);
        col.getHolder().setFormat(PixelFormat.TRANSLUCENT);
//        col.setVisibility(View.INVISIBLE);
//		cv.setVisibility(View.INVISIBLE);
//		sv.setOnTouchListener(this);
		
		
		SurfaceHolder sfh = sv.getHolder();
		sfh.addCallback(this);
		
	//	iv.setImageResource(R.drawable.penguins);
        @SuppressWarnings("deprecation")
		BitmapDrawable bmdr = new BitmapDrawable(getResources(),BitmapFactory.decodeResource(getResources(), R.drawable.penguins));
        Bitmap background = BitmapFactory.decodeResource(getResources(), R.drawable.penguins);
        

		lribbon.setBackgroundColor(Color.DKGRAY);
		
		final RelativeLayout mlayout = (RelativeLayout) findViewById(R.id.relativeLayout0);
		
		// MMAdview Start
		
//		Hashtable<String, String> map = new Hashtable<String, String>();
//	    map.put("age", "30");
//	    map.put("zip", "90210");
//	    map.put("income", "65000");
//	    //include additional metadata or settings in the hashmap
//
//	    com.millennialmedia.android.MMAdView MMMadView = new com.millennialmedia.android.MMAdView(this, "93612", MMAdView.BANNER_AD_BOTTOM, 30, map);
//	    MMMadView.setId(MMAdViewSDK.DEFAULT_VIEWID);
	    
		// MMAdview finished
		
		// Komilmobile starts
		
	//	com.komlimobile.sdk.KomliMobileView kmview = (RelativeLayout) findViewById(R.id);
		
		
		
		// komilmobile ends
	    
		
		//adwhirl starts
	    
		AdWhirlManager.setConfigExpireTimeout(1000*60*5);
		
		
		  // AdWhirlTargeting.setAge(23);
		   
		 //  AdWhirlTargeting.setGender(AdWhirlTargeting.Gender.MALE);
		 //  AdWhirlTargeting.setKeywords("online games gaming");
		 //  AdWhirlTargeting.setPostalCode("75074");
		   AdWhirlTargeting.setTestMode(false);
		
		final AdWhirlLayout adWhirlLayout = new AdWhirlLayout(this,
                "7f37fb4ded8b4c6c97ec686263f24e84");
		   
//			final AdWhirlLayout adWhirlLayout = (AdWhirlLayout) findViewById(R.id.adlayout2);

		   
		layoutParams = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);

		   int diWidth = 320;
		   int diHeight = 52;
		   int density = (int) getResources().getDisplayMetrics().density;
		   
	 
		   adWhirlLayout.setAdWhirlInterface(this);
		//   adWhirlLayout.setMaxWidth((int)(diWidth * density));
		//   adWhirlLayout.setMaxHeight((int)(diHeight * density));
		   
		 
		   layoutParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
//		   layoutParams.addRule(RelativeLayout.ABOVE, R.id.relativeLayout1);

		 


		   adcontainer = (RelativeLayout) findViewById(R.id.adlayout);
		   


   adcontainer.addView(adWhirlLayout, layoutParams);
	//	   adcontainer.addView(adWhirlLayout);
//   adcontainer.invalidate();

//		   mlayout.addView(MMMadView, layoutParams);
	//	   adcontainer.addView(kmview);
 //  adcontainer.bringToFront();
		   
//   mlayout.addView(adWhirlLayout, layoutParams);

//adwhirl finish
		   
		   
		
	    // Create the adView
	    

	    // Lookup your LinearLayout assuming it’s been given
	    // the attribute android:id="@+id/mainLayout"
	    
//		adView = new AdView(this, AdSize.BANNER, "a14fff2a5bbb315");
//		
//		RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
//		
//
//		params.addRule(RelativeLayout.ABOVE, R.id.relativeLayout1);
//		
//
//	    // Add the adView to it
//	    mlayout.addView(adView, params);
//
//	    // Initiate a generic request to load it with an ad
//	    AdRequest adrequest = new AdRequest();
//	    
//	   
//	    
//	    //adrequest.addTestDevice(AdRequest.TEST_EMULATOR);
//	    adView.loadAd(adrequest);
		

		
	    l = new View.OnClickListener() {
			
			@SuppressWarnings("null")
			public void onClick(View v) {
				// TODO Auto-generated method stub
				switch (v.getId()){
				case R.id.button1:
//					colorclicked("Black");
//					break;
					color = "Black";
					if (sv.sketchloaded && oldstrength != strength)
					{

						sv.sketchloading = true;
						sv.backgroundoriginal = sv.backgroundscaled;
						scaled = sv.beforesketch;
						
						pb1 = (ProgressBar)findViewById(R.id.pb1);
						
						pb1.setMax(scaled.getHeight());
						
						mlayout.removeView(pb1);
						
						mlayout.addView(pb1);
						


						

										
						
						bgw1 = new backgroundworker1(scaled, pb1, sv, "Black", setclick);
//						bgw1.lbrown = Color.rgb(100, 100, 100);
//						bgw1.main = Color.rgb(47, 47, 47);
						bgw1.background = BitmapFactory.decodeResource(getResources(), R.drawable.background);
						
						bgw1.strength = strength;
						oldstrength = strength;

						bgw1.execute(oarray);

						break;
					}
					if (sv.sketchloaded == false)
					{

					sv.sketchloading = true;
					sv.backgroundoriginal = sv.backgroundscaled;					
					scaled = sv.backgroundscaled;
								
					pb1 = (ProgressBar)findViewById(R.id.pb1);
					
					pb1.setMax(scaled.getHeight());
					
					mlayout.removeView(pb1);
					
					mlayout.addView(pb1);
						
					
					bgw1 = new backgroundworker1(scaled, pb1, sv, "Black", setclick);
//					bgw1.lbrown = Color.rgb(100, 100, 100);
//					bgw1.main = Color.rgb(47, 47, 47);
					bgw1.background = BitmapFactory.decodeResource(getResources(), R.drawable.background);
					
					bgw1.strength = strength;
					oldstrength = strength;

					bgw1.execute(oarray);

					break;
					}
					else
					{

						sv.sketchloading = true;
						sv.backgroundoriginal = sv.backgroundscaled;					
						scaled = sv.backgroundscaled;
									
						pb1 = (ProgressBar)findViewById(R.id.pb1);
						
						pb1.setMax(scaled.getHeight());
						
						mlayout.removeView(pb1);
						
						mlayout.addView(pb1);
						


						

										
						
						sketch2 = new Dosketch2(scaled, pb1, sv);
						sketch2.target = "Black";

						sketch2.execute(oarray);
					}

					 break;
				case R.id.button2:
					color = "Brown";
					if (sv.sketchloaded && oldstrength != strength)
					{
						sv.sketchloading = true;
						sv.backgroundoriginal = sv.backgroundscaled;
						scaled = sv.beforesketch;
						
						pb1 = (ProgressBar)findViewById(R.id.pb1);
						
						pb1.setMax(scaled.getHeight());
						
						mlayout.removeView(pb1);
						
						mlayout.addView(pb1);
						
						bgw1 = new backgroundworker1(scaled, pb1, sv, "Brown", setclick);
//						bgw1.lbrown = Color.rgb(128, 85, 86);
//						bgw1.main = Color.rgb(86, 28, 29);
						bgw1.background = BitmapFactory.decodeResource(getResources(), R.drawable.background);
						
						bgw1.strength = strength;
						oldstrength = strength;

						bgw1.execute(oarray);
						break;
					}
					
					if (sv.sketchloaded == false)
					{
					sv.sketchloading = true;
					sv.backgroundoriginal = sv.backgroundscaled;					
					scaled = sv.backgroundscaled;
								
					pb1 = (ProgressBar)findViewById(R.id.pb1);
					
					pb1.setMax(scaled.getHeight());
					
					mlayout.removeView(pb1);
					
					mlayout.addView(pb1);
	
					
					bgw1 = new backgroundworker1(scaled, pb1, sv, "Brown", setclick);
//					bgw1.lbrown = Color.rgb(128, 85, 86);
//					bgw1.main = Color.rgb(86, 28, 29);
					bgw1.background = BitmapFactory.decodeResource(getResources(), R.drawable.background);
					
					bgw1.strength = strength;
					oldstrength = strength;

					bgw1.execute(oarray);
					break;
					}
					else
					{
						sv.sketchloading = true;
						sv.backgroundoriginal = sv.backgroundscaled;					
						scaled = sv.backgroundscaled;
									
						pb1 = (ProgressBar)findViewById(R.id.pb1);
						
						pb1.setMax(scaled.getHeight());
						
						mlayout.removeView(pb1);
						
						mlayout.addView(pb1);
						
						sketch2 = new Dosketch2(scaled, pb1, sv);
						sketch2.target = "Brown";

						sketch2.execute(oarray);
					}
					

					break;
				case R.id.button3:
					color = "Blue";
					if (sv.sketchloaded && oldstrength != strength)
					{
						sv.sketchloading = true;
						sv.backgroundoriginal = sv.backgroundscaled;
						scaled = sv.beforesketch;
						
						pb1 = (ProgressBar)findViewById(R.id.pb1);
						
						pb1.setMax(scaled.getHeight());
						
						mlayout.removeView(pb1);
						
						mlayout.addView(pb1);
						
										
						
						bgw1 = new backgroundworker1(scaled, pb1, sv, "Blue", setclick);
//						bgw1.lbrown = Color.rgb(122, 133, 155);
//						bgw1.main = Color.rgb(60, 75, 106);
						bgw1.background = BitmapFactory.decodeResource(getResources(), R.drawable.background);
						
						bgw1.strength = strength;
						oldstrength = strength;

						bgw1.execute(oarray);
						break;
					}
					
					if (sv.sketchloaded == false)
					{
					sv.sketchloading = true;
					sv.backgroundoriginal = sv.backgroundscaled;					
					scaled = sv.backgroundscaled;
								
					pb1 = (ProgressBar)findViewById(R.id.pb1);
					
					pb1.setMax(scaled.getHeight());
					
					mlayout.removeView(pb1);
					
					mlayout.addView(pb1);
									
					
					bgw1 = new backgroundworker1(scaled, pb1, sv, "Blue", setclick);
//					bgw1.lbrown = Color.rgb(122, 133, 155);
//					bgw1.main = Color.rgb(60, 75, 106);
					bgw1.background = BitmapFactory.decodeResource(getResources(), R.drawable.background);
					
					bgw1.strength = strength;
					oldstrength = strength;

					bgw1.execute(oarray);

					break;
					}
					else
					{
						sv.sketchloading = true;
						sv.backgroundoriginal = sv.backgroundscaled;					
						scaled = sv.backgroundscaled;
									
						pb1 = (ProgressBar)findViewById(R.id.pb1);
						
						pb1.setMax(scaled.getHeight());
						
						mlayout.removeView(pb1);
						
						mlayout.addView(pb1);		
						
						sketch2 = new Dosketch2(scaled, pb1, sv);
						sketch2.target = "Blue";

						sketch2.execute(oarray);
					}

						
					 break;
					 
				case R.id.button4:
					color = "Green";
					if (sv.sketchloaded && oldstrength != strength)
					{
						sv.sketchloading = true;
						sv.backgroundoriginal = sv.backgroundscaled;
						scaled = sv.beforesketch;
						
						pb1 = (ProgressBar)findViewById(R.id.pb1);
						
						pb1.setMax(scaled.getHeight());
						
						mlayout.removeView(pb1);
						
						mlayout.addView(pb1);
						
						
						bgw1 = new backgroundworker1(scaled, pb1, sv, "Green", setclick);
//						bgw1.lbrown = Color.rgb(90, 127, 106);
//						bgw1.main = Color.rgb(37, 65, 50);
						bgw1.background = BitmapFactory.decodeResource(getResources(), R.drawable.background);
						
						bgw1.strength = strength;
						oldstrength = strength;

						bgw1.execute(oarray);
						
						break;
					}
					
					if (sv.sketchloaded == false)
					{
						sv.sketchloading = true;
					sv.backgroundoriginal = sv.backgroundscaled;
					
					scaled = sv.backgroundscaled;
					

					
					pb1 = (ProgressBar)findViewById(R.id.pb1);
					
					pb1.setMax(scaled.getHeight());
					
					mlayout.removeView(pb1);
					
					mlayout.addView(pb1);
					


					oarray = null;

									
					
					bgw1 = new backgroundworker1(scaled, pb1, sv, "Green", setclick);
//					bgw1.lbrown = Color.rgb(90, 127, 106);
//					bgw1.main = Color.rgb(37, 65, 50);
					bgw1.background = BitmapFactory.decodeResource(getResources(), R.drawable.background);
					
					bgw1.strength = strength;
					oldstrength = strength;

					pb1.setVisibility(View.VISIBLE);
					bgw1.execute(oarray);
					break;
					}
			
					else
					{
						sv.sketchloading = true;
						sv.backgroundoriginal = sv.backgroundscaled;					
						scaled = sv.backgroundscaled;
									
						pb1 = (ProgressBar)findViewById(R.id.pb1);
						
						pb1.setMax(scaled.getHeight());
						
						mlayout.removeView(pb1);
						
						mlayout.addView(pb1);
						
				
						
						sketch2 = new Dosketch2(scaled, pb1, sv);
						sketch2.target = "Green";

						sketch2.execute(oarray);
					}

					 break;

				case R.id.photob:
					if (cropm == true)
					{	
						
						sv.rotate(-90);
						
					}

					break;
				case R.id.photob3:
					
					if (photosd.isOpened())					
					photosd.close();
					

					break;
				case R.id.photob2:
					
					photosd.close();
					
					scaled = null;
			        sv = new Sketchview(context);
					sv = (Sketchview) findViewById(R.id.surfaceView1);
					sv.sketchloaded = false;
					sv.sketchloading = false;
//					sv.setOnTouchListener(this);
					
					Intent selectpic = new Intent();
					selectpic.setType("image/*");
					selectpic.setAction(Intent.ACTION_GET_CONTENT);
					
					startActivityForResult(Intent.createChooser(selectpic, "Select Picture"), SELECT_PICTURE);
					

					break;
					
				case R.id.photob1:
						

					scaled = null;
			        sv = new Sketchview(context);
					sv = (Sketchview) findViewById(R.id.surfaceView1);
					sv.sketchloaded = false;
					sv.sketchloading = false;
					
					
					photosd.close();
					photomode("on");
			
					break;
					
				case R.id.cropb:
					
					if (cropm)
					{
						sv.isvalidb2 = false;
						Bitmap background = BitmapFactory.decodeResource(getResources(), R.drawable.penguins);
						sv.initialize2(background);
						cropmode("off");
					}
					else{
						
					cropmode("on");
					sv.crop();
					tocrop = true;	
					}
				

					break;
					
				case R.id.saveb:
					
					if (cropm == true)
					{						
						sv.isvalidb2 = false;
						cropmode("off");
						sv.cropit();					
						
					}
					else
					{
						
						if(sv.sketchloaded)
						{
						
						ByteArrayOutputStream bytes = new ByteArrayOutputStream();
						sv.backgroundscaled.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
						
						String filename = filename();
						String fname = Environment.getExternalStorageDirectory().getPath() + "/PenSketch/" + "PenSketch_" + filename + ".jpg";  
						
						File Dir = new File(Environment.getExternalStorageDirectory().getPath() + "/PenSketch");
						if(!Dir.isDirectory())
							Dir.mkdir();
						
						File f = new File(fname);
						
				
						
						try {
							boolean vv = f.createNewFile();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
						//write the bytes in file
						FileOutputStream fo = null;
						try {
							fo = new FileOutputStream(f);
						} catch (FileNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						try {
							fo.write(bytes.toByteArray());
							fo.flush();
							fo.close();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
						sendBroadcast(new Intent(Intent.ACTION_MEDIA_MOUNTED, Uri.parse("file://"+ Environment.getExternalStorageDirectory())));
						Toast toast = Toast.makeText(getApplicationContext(), "Your sketch is now saved on the sdcard", Toast.LENGTH_SHORT);
						toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
						toast.show();
						}

							else
							{
								Toast toast = Toast.makeText(getApplicationContext(), "You haven't drawn a sketch yet!", Toast.LENGTH_SHORT);
								toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
								toast.show();
							}
					}

					

					

					break;
				case R.id.undob:
					
					if (cropm == true)
					{	
						sv.rotate(90);
						break;
					}

					if(photom == true)
					{

			//			cv.restartPreview(camerafront);
						mlayout.removeView(cv);
						
						RelativeLayout.LayoutParams param = new RelativeLayout.LayoutParams(thesize.height,thesize.width);

						

								param.addRule(RelativeLayout.CENTER_HORIZONTAL);
								param.topMargin = col.h2;		
								
								mlayout.addView(cv, param);
								
								   adcontainer.bringToFront();

							    
							    relativelayout1.bringToFront();

						camerafront = !camerafront;
						
						
					}	
					else
					{
							if (sv.beforesketch != null)
							{
								sv.initialize2(sv.beforesketch);
								sv.sketchloaded = false;
							}
					
					}
					break;
				case R.id.imageButton2:
//					cv.showsize();
					if (photom)
					{

						cv.takepic();
//						sv.sketchloaded=false;
						
						
						
						sv.backgroundoriginal = sv.backgroundscaled;
						photomode("off");
						


//						sv.initialize2(takenpicture);
						break;
					}
					if (valuesd.isOpened())					
					valuesd.close();
					else
						valuesd.open();

					break;
				case R.id.valueb1:
					
					//photob.setText(String.valueOf(sbar.getProgress()));
					strength = sbar.getProgress();
					
					if (sv.sketchloaded)
					{

						if (color == "Black")
							b1.performClick();
							
						if (color == "Brown")
							b2.performClick();
							
						if (color == "Blue")
							b3.performClick();
							
						if (color == "Green")
							b4.performClick();
							

						
					}
					
					valuesd.close();

				case R.id.valueb2:
					
					
					valuesd.close();


					break;
				case R.id.imageButton1:
					if (photom == true)
					{
						
						photomode("off");
						Bitmap background = BitmapFactory.decodeResource(getResources(), R.drawable.penguins);
						if (sv.backgroundscaled != null)
							background = sv.backgroundscaled;
						sv.initialize2(background);
						mlayout.removeView(cv);


	
						break;
					}

					
					if (photosd.isOpened())					
					photosd.close();
					else
						photosd.open();
					
	

					break;
				case R.id.shareb:
					if (sv.sketchloaded)
						sharesketch();
					else
					{
						Toast toast = Toast.makeText(getApplicationContext(), "You haven't drawn a sketch yet!", Toast.LENGTH_SHORT);
						toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
						toast.show();
					}

					



					break;
				}

			}
		};
		


		b1.setOnClickListener(l);
		b2.setOnClickListener(l);
		b3.setOnClickListener(l);
		b4.setOnClickListener(l);
		settingib.setOnClickListener(l);
		photoib.setOnClickListener(l);
		photoib.setOnClickListener(l);
		photob.setOnClickListener(l);
		photob1.setOnClickListener(l);
		photob2.setOnClickListener(l);
		photob3.setOnClickListener(l);
		cropb.setOnClickListener(l);
		shareb.setOnClickListener(l);
		undob.setOnClickListener(l);
		saveb.setOnClickListener(l);
		valueb1.setOnClickListener(l);
		valueb2.setOnClickListener(l);
		shareb.setOnClickListener(l);
		
		setclick = new View[17];
		
		setclick[0] = b1;
		setclick[1] = b2;
		setclick[2] = b3;
		setclick[3] = b4;
		setclick[4] = settingib;
		setclick[5] = photoib;
		setclick[6] = photob;
		setclick[7] = photob1;
		setclick[8] = photob2;
		setclick[9] = photob3;
		setclick[10] = cropb;
		setclick[11] = shareb;
		setclick[12] = undob;
		setclick[13] = saveb;
		setclick[14] = valueb1;
		setclick[15] = valueb2;
		setclick[16] = shareb;
		
		
		cropmode("off");
		
   }
    

    void svinitialize(Bitmap theimage)
    {

    	
    	SurfaceHolder sfhl =  sv.getHolder();
    	sfhl.addCallback(this);
        Canvas canvas2 = sfhl.lockCanvas();

        sv.getHolder().unlockCanvasAndPost(canvas2);
    }
    @Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
	

    	
// this part is necessary when using admob
//        if (adView != null) {
//           adView.destroy();
//          }

		super.onDestroy();
        
	}


	public void savejpg(String name, Bitmap bm6)
    {
		// bm6 = BitmapFactory.decodeResource(getResources(), R.drawable.a716);

		Canvas canvas = new Canvas();

		canvas.drawColor(Color.BLACK);
		canvas.drawBitmap(bm6, 0, 0, null);
	
		String name2 = name + ".jpg";
		File file = new File(Environment.getExternalStorageDirectory().toString(), name2);
		
		try {
			FileOutputStream out = new FileOutputStream(file);
			
			bm6.compress(Bitmap.CompressFormat.JPEG, 90, out);
			out.flush();
			out.close();
			
			MediaStore.Images.Media.insertImage(getContentResolver(), file.getAbsolutePath(), file.getName(), file.getName());
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
   

    @Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
    	super.onActivityResult(requestCode, resultCode, data);
    	if (resultCode != RESULT_CANCELED)
    	{
		if (requestCode == ref)
		{
		Bundle extras = data.getExtras();
		bm = (Bitmap) extras.get("data");
		bm2 = iv.getDrawingCache();
		
		FileOutputStream outStream = null;

		
		String extStorageDirectory = Environment.getExternalStorageDirectory().toString();
		File file = new File(extStorageDirectory, "er3.PNG");

		try {
			outStream = new FileOutputStream(file);
			outStream.flush();
			outStream.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		bm.compress(Bitmap.CompressFormat.PNG, 100, outStream);
		
		
		//iv.setImageBitmap(bm);
		}
		
		
		if (requestCode == SELECT_PICTURE && resultCode == RESULT_OK)
		{


			scaled = null;
//	        sv = new Sketchview(this);
//			sv = (Sketchview) findViewById(R.id.surfaceView1);
			sv.setOnTouchListener(this);
			Uri selectedImage = data.getData();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};

            Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String filePath = cursor.getString(columnIndex);
            cursor.close();
            
            File f = new File(filePath);
            
            sv.filename = f.getName().substring(0, f.getName().lastIndexOf('.'));
            
            

        
			
			Loadbitmap bgw2 = new Loadbitmap(filePath, pb1, sv);
			
			bgw2.mactivity = this;


			bgw2.execute(oarray);
			
			sv.sketchloaded = false;
			
					
					
		}
		
    }
	}





	@SuppressLint("NewApi")
	private Bitmap getResizedBitmap(Bitmap bm) {
		// TODO Auto-generated method stub
		
	    Display display = getWindowManager().getDefaultDisplay();
	    Point size = new Point();

	    display.getSize(size);

	int width = bm.getWidth();

	int frameWidth = size.x;

	int height = bm.getHeight();


	float newheight = (frameWidth * height) / width; 


	Bitmap resizedBitmap = Bitmap.createScaledBitmap(bm, frameWidth, (int) newheight, false);

	return resizedBitmap;

	}




	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

	private InputStream OpenHttpConnection(String strURL) throws IOException{
		 InputStream inputStream = null;
		 URL url = new URL(strURL);
		 URLConnection conn = url.openConnection();

		 try{
		  HttpURLConnection httpConn = (HttpURLConnection)conn;
		  httpConn.setRequestMethod("GET");
		  httpConn.connect();

		  if (httpConn.getResponseCode() == HttpURLConnection.HTTP_OK) {
		   inputStream = httpConn.getInputStream();
		  }
		 }
		 catch (Exception ex)
		 {
		 }
		 return inputStream;
		}
	
	
	   private Bitmap LoadImage(String URL, BitmapFactory.Options options)
	   {       
	    Bitmap bitmap = null;
	    InputStream in = null;       
	       try {
	           in = OpenHttpConnection(URL);
	           bitmap = BitmapFactory.decodeStream(in, null, options);
	           in.close();
	       } catch (IOException e1) {
	       }
	       return bitmap;               
	   }


	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		// TODO Auto-generated method stub
		if (changethepicture)
		{
        Canvas canvas2 = sv.getHolder().lockCanvas();

       
        
        float scale = (float)svpicture.getWidth() / (float)sv.getWidth();
              int newwidth = Math.round(svpicture.getWidth()/scale);
              int newheight = Math.round(svpicture.getHeight()/scale);
              Bitmap scaled = Bitmap.createScaledBitmap(svpicture, newwidth, newheight, true);
              
              float wheretodraw = sv.getHeight() / 2;
              float wheretodraw2 = newheight / 2;
              float wheretodraw3 = wheretodraw - wheretodraw2;
              int wtd = Math.round(wheretodraw3);
              
        
        canvas2.drawBitmap(scaled, 0, wtd, null);
        sv.getHolder().unlockCanvasAndPost(canvas2);
        changethepicture = false;
        photosd.close();
		}
		
		
		if (drawcircle)
		{
	        Canvas canvas2 = sv.getHolder().lockCanvas();
	        canvas2.drawARGB(255, 150, 150, 10);
			counter ++;
			b2.setText("no");
			drawcircle = false;
			sv.getHolder().unlockCanvasAndPost(canvas2);

		}
	}


	public void surfaceCreated(SurfaceHolder holder) {
		// TODO Auto-generated method stub


	}


	public void surfaceDestroyed(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		
	}




	public boolean onTouch(View v, MotionEvent event) {
		// TODO Auto-generated method stub
		if (cropm == true)
		{
			
			if (event.getAction() == MotionEvent.ACTION_DOWN)
			{
				
				
				double x1 = Math.abs(event.getX() - sv.placex);
				double y1 = Math.abs(event.getY() - sv.placey);
				double dis = Math.abs(x1 - y1);
				double dis2 = Math.abs(x1 - sv.squaresize);
				double dis3 = Math.abs(y1 - sv.squaresize);
//				
				if (dis < 40 &&
					dis2 < 40 &&
					dis3 < 40 &&
					sv.ourthread.isAlive())
						{	
						//sv.squaresize = sv.squaresize * 1.1;
						down = true;
						}

			}
			if (event.getAction() == MotionEvent.ACTION_MOVE)
			{
				if (down)
				{
					double move1 = Math.pow((mLastTouchX - sv.placex), 2);
					double move2 = Math.pow((mLastTouchY - sv.placey), 2);
					double distance1 = Math.sqrt(move1 + move2);
					
					
					double move3 = Math.pow((event.getX() - sv.placex), 2);
					double move4 = Math.pow((event.getY() - sv.placey), 2);
					double distance2 = Math.sqrt(move3 + move4);
					
					double delta1 = Math.abs(mLastTouchX - event.getX());
					double delta2 = Math.abs(mLastTouchY - event.getY());
					if (delta2 > delta1)
						delta1 = delta2;
					
					if(sv.placex != sv.x || sv.placey!= sv.y)
						delta1 = delta1 / 2;
					
					int min = Math.min(sv.backgroundscaled.getWidth(), sv.backgroundscaled.getHeight());
					
					if (distance1 > distance2 && (sv.squaresize - delta1) > 50)
						sv.squaresize = (float) (sv.squaresize - delta1);
					else
					{
						if ((sv.squaresize*2) < min && (sv.squaresize + delta1) > 50)
						sv.squaresize = (float) (sv.squaresize + delta1);

					}
					

					
				}
			}
			
			

			

		
		
		
        mLastTouchX = event.getX();
        mLastTouchY = event.getY();
        
			if (down == false )
			{

		        sv.x = event.getX();
		        sv.y = event.getY();
			}

			if(event.getAction() == MotionEvent.ACTION_UP)
			{
				down = false;
				if(sv.isdownfinished)
				{
					
					sv.isdown = false;
					
				}
			}
			     
    
        
		return true;
	}
		else
		{
		return false;
		}
	}
	
	
	public void performcrop()
	{
		cropmode("on");
		sv.crop();
		tocrop = true;	
	}
	public void cropmode(String mode)
	{
		if (mode == "on")
		{
			
		sv.setEnabled(true);

		shareb.setVisibility(View.INVISIBLE);
		upperlayout.setVisibility(View.INVISIBLE);
		upperlayout.setBackgroundColor(Color.BLACK);
		relativelayout1.setBackgroundColor(Color.BLACK);
		saveb.setBackgroundResource(R.drawable.ok);
		saveb.setText("Edit");
		cropb.setBackgroundResource(R.drawable.cancel);
		cropb.setText("Cancel");
		photob.setBackgroundResource(R.drawable.rotate1);
		undob.setBackgroundResource(R.drawable.rotate2);
		undob.setText("");
		photob.setText("");
		
		cropm = true;
		}
		if (mode == "off")
		{
			
			sv.setEnabled(false);

			shareb.setVisibility(View.VISIBLE);
			upperlayout.setVisibility(View.VISIBLE);
//			upperlayout.setBackgroundColor(Color.BLACK);
			relativelayout1.setBackgroundColor(Color.BLACK);
			saveb.setBackgroundResource(R.drawable.save3);
			saveb.setText("Save");
			cropb.setBackgroundResource(R.drawable.crop);
			cropb.setText("Edit");
			photob.setBackgroundResource(0);
			undob.setBackgroundResource(R.drawable.undo3);
			undob.setText("Undo");
			photob.setText("");
			pb1.bringToFront();
			
			cropm = false;
			
		}
	}
	
	public void photomodeon(android.hardware.Camera.Parameters p)
	{
		mlayout = (RelativeLayout) findViewById(R.id.relativeLayout0);
		
		
		thesize = getBestPictureSize2(mlayout.getWidth(), mlayout.getHeight(), p);

		
		if (thesize == null)
		{
			Toast toast = Toast.makeText(getApplicationContext(), "This feature is currently not compatible with your device\r\n Send us an email with your phone model\r\n and we will resolve the problem", Toast.LENGTH_LONG);
			toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
			toast.show();
		}

		else
		{
		if (cv == null)
	        cv = new Cameraview(this);
			
			
			cv.mactivity = this;
		

		

        
//		setContentView(R.layout.first3surfaceview);

		cropb.setVisibility(View.INVISIBLE);
		shareb.setVisibility(View.INVISIBLE);
		saveb.setVisibility(View.INVISIBLE);
	colorlayout.setVisibility(View.INVISIBLE);
	sv.setVisibility(View.INVISIBLE);
	cv.setVisibility(View.VISIBLE);
	col.setVisibility(View.VISIBLE);
//
//


	

	RelativeLayout.LayoutParams param = new RelativeLayout.LayoutParams(thesize.height,thesize.width);

//

	param.addRule(RelativeLayout.CENTER_HORIZONTAL);
	param.topMargin = col.h2;		
//	
	mlayout.addView(cv, param);

//	mlayout.removeView(adView);
//	((View)adView).bringToFront();
	   adcontainer.bringToFront();
//adWhirlLayout.bringToFront();

//    mlayout.addView(adView);
//
//	
	settingib.setBackgroundResource(0);
	photoib.setBackgroundResource(0);
//	
//	
	Bitmap ok = BitmapFactory.decodeResource(getResources(), R.drawable.okfullsize);
	Bitmap cancel = BitmapFactory.decodeResource(getResources(), R.drawable.cancelfullsize);
	
	if (android.hardware.Camera.getNumberOfCameras() > 1)
	{
	undob.setBackgroundResource(R.drawable.cameraswitch);
	undob.setText("");
	}
	else
	{
		undob.setVisibility(View.INVISIBLE);
	}
//			
//			
	photoib.setImageBitmap(cancel);
	settingib.setImageBitmap(ok);
//	
//	
//
//	
	relativelayout1.bringToFront();
//	
	photom = true;
		}
	}
	public void opencameraerror()
	{
		oc.cancel(true);
		Toast toast = Toast.makeText(getApplicationContext(), "Pen Sketch couldn't complete the task! Please let us know if it happened again.", Toast.LENGTH_SHORT);
		toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
		toast.show();
	}
	public void photomode(String mode)
	{
		if (mode == "on")
		{
			Object[] oarray = null;
			oc = new Opencamera(this, false);
			
			oc.execute(oarray);
			
		
		}
		
		if (mode == "off")
		{
			
	        

				cropb.setVisibility(View.VISIBLE);
				shareb.setVisibility(View.VISIBLE);
				saveb.setVisibility(View.VISIBLE);
			colorlayout.setVisibility(View.VISIBLE);
			sv.setVisibility(View.VISIBLE);



			mlayout = (RelativeLayout) findViewById(R.id.relativeLayout0);
			

			
			settingib.setBackgroundResource(0);
			photoib.setBackgroundResource(0);
			
			
			Bitmap ok = BitmapFactory.decodeResource(getResources(), R.drawable.setting);
			Bitmap cancel = BitmapFactory.decodeResource(getResources(), R.drawable.photo4);
			
			if (android.hardware.Camera.getNumberOfCameras() > 1)
			{
			undob.setBackgroundResource(R.drawable.undo3);
			undob.setText("Undo");
			}
			else
			{
				undob.setVisibility(View.VISIBLE);
			}
					
					
			photoib.setImageBitmap(cancel);
			settingib.setImageBitmap(ok);
			
			
			relativelayout1.bringToFront();
			
			photosd.bringToFront();
			valuesd.bringToFront();
			pb1.bringToFront();
			
			
			photom = false;
			

			
		}
		

		

	}
	

	
	public android.hardware.Camera.Size getBestPictureSize2(int width, int height, android.hardware.Camera.Parameters p)
	{
		android.hardware.Camera.Size result=null;  
	    	


	        for (android.hardware.Camera.Size size : p.getSupportedPreviewSizes()) {

                if (result==null) {
                    result=size;
                } 
                

        		
                if (size.height == width && size.width <= (height - col.h2))
                {
                	result = size;
                	return result;
                }
                
                if (Math.abs(result.height - width) > Math.abs(size.height - width) &&
                	size.width <= (height - col.h2) &&
                	size.height <= width)
                {
                	result = size;
                	
                }
            }
        
	        if (result.width > (height - col.h2) ||
                	result.height > width)
                	result = null;
	        
	    return result;

	}

	public void restofit()
	{
		settingib.setBackgroundResource(0);
		photoib.setBackgroundResource(0);
		
		
		Bitmap ok = BitmapFactory.decodeResource(getResources(), R.drawable.okfullsize);
		Bitmap cancel = BitmapFactory.decodeResource(getResources(), R.drawable.cancelfullsize);
		undob.setBackgroundResource(R.drawable.cameraswitch);
		undob.setText("");
				
				
		photoib.setImageBitmap(cancel);
		settingib.setImageBitmap(ok);
		
		
//		cv.bringToFront();
		
		
		relativelayout1.bringToFront();
		
		photom = true;
	}
	public void sketcher(int color1, int color2)
	{
		if (sv.sketchloaded == false)
		{
		sv.sketchloading = true;
		sv.backgroundoriginal = sv.backgroundscaled;					
		scaled = sv.backgroundscaled;
					
		pb1 = (ProgressBar)findViewById(R.id.pb1);
		
		pb1.setMax(scaled.getHeight());
		
		mlayout.removeView(pb1);
		
		mlayout.addView(pb1);

						
		
		bgw1 = new backgroundworker1(scaled, pb1, sv, "Brown", setclick);
		bgw1.lbrown = color2;
		bgw1.main = color1;
		bgw1.background = BitmapFactory.decodeResource(getResources(), R.drawable.background);
		
		bgw1.strength = strength;
		oldstrength = strength;

		bgw1.execute(oarray);
		}
		else
		{
			
		}
	}
	

	public String filename()
	{

		android.content.SharedPreferences pref = getSharedPreferences("PenSketchpreferences", 0);
		int counter = pref.getInt("Counter", 0);
		
		if (counter== 0)
		{
			SharedPreferences.Editor editor = pref.edit();
			editor.putInt("Counter", 0);
			editor.commit();
			counter = pref.getInt("Counter", 0);
		}

		counter++;
		
		SharedPreferences.Editor editor = pref.edit();
		
		editor.putInt("Counter", counter);
		editor.commit();
		
		String CS = String.valueOf(counter);
		
		if (counter <100)
		{
			if (counter <10)
				CS = "00" + CS;
			else
				CS = "0" + CS;
		}
		
		
		return CS;
	}
	
	public void sharesketch()
	{

		sv.onresume = sv.backgroundscaled;
		
		
		shsk = new SketchBackground(sv, this);
		Object[] oarray = null;

		
		shsk.execute(oarray);

		
		
	}
	
	public void shareerror()
	{
		shsk.cancel(true);
		Toast toast = Toast.makeText(getApplicationContext(), "Pen Sketch couldn't complete the task! Please let us know if it happened again.", Toast.LENGTH_LONG);
		toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
		toast.show();
	}
	public void sharesketch2(Intent one, Intent two)
	{

		sendBroadcast(one);
        startActivity(two);
		
	}


	public void adWhirlGeneric() {
		// TODO Auto-generated method stub
		
	}
	
	public void setclickable(boolean b)
	{
		b1.setClickable(b);
		b2.setClickable(b);
		b3.setClickable(b);
		b4.setClickable(b);
		settingib.setClickable(b);
		photoib.setClickable(b);
		photoib.setClickable(b);
		photob.setClickable(b);
		photob1.setClickable(b);
		photob2.setClickable(b);
		photob3.setClickable(b);
		cropb.setClickable(b);
		shareb.setClickable(b);
		undob.setClickable(b);
		saveb.setClickable(b);
		valueb1.setClickable(b);
		valueb2.setClickable(b);
		shareb.setClickable(b);
	}
}
	
	


