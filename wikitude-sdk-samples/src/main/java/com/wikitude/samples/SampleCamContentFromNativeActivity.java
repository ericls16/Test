package com.wikitude.samples;

import android.os.Bundle;
import android.util.Log;

public class SampleCamContentFromNativeActivity extends SampleCamActivity {
	
	@Override
	protected void onPostCreate( final Bundle savedInstanceState ) {
		super.onPostCreate( savedInstanceState );
		Log.e("TEMP","SampleCamContentFromNativeActivity->onPostCreate");
		this.injectData();
	}

}