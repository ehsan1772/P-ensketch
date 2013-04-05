package com.ehsan.test.again;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup.LayoutParams;
import android.widget.RelativeLayout;
import com.adwhirl.AdWhirlLayout;
import com.adwhirl.AdWhirlLayout.AdWhirlInterface;
import com.adwhirl.AdWhirlManager;
import com.adwhirl.AdWhirlTargeting;

public class TestActivity extends Activity implements AdWhirlInterface{
	
//	AdWhirlLayout
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
	    setContentView(R.layout.justatry);
//	    getIntent().getSerializableExtra("ad");
//	    final MyAd myad = (MyAd) getIntent().getSerializableExtra("ad");
		RelativeLayout adcontainer2 = (RelativeLayout) findViewById(R.id.adlayout2);
		
		AdWhirlManager.setConfigExpireTimeout(1000*60*5);
		

		   AdWhirlTargeting.setTestMode(false);
		
		final AdWhirlLayout adWhirlLayout = new AdWhirlLayout(this,"7f37fb4ded8b4c6c97ec686263f24e84");
		
		final MyAd myad = new MyAd(this,"7f37fb4ded8b4c6c97ec686263f24e84");
		   

		RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);


		   
	 
		   adWhirlLayout.setAdWhirlInterface(this);
		   myad.setAdWhirlInterface(this);
		   
		 
		   layoutParams.addRule(RelativeLayout.CENTER_HORIZONTAL);

		   
		//   adcontainer2 = (RelativeLayout) findViewById(R.id.adlayout);
		   


 //  adcontainer2.addView(adWhirlLayout, layoutParams);
		   adcontainer2.addView(myad, layoutParams);





		super.onCreate(savedInstanceState);
	}

public void adWhirlGeneric() {
	// TODO Auto-generated method stub
	
}

}
