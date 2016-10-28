package com.wikitude.samples.ls;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.wikitude.architect.ArchitectView;
import com.wikitude.architect.StartupConfiguration;
import com.wikitude.sdksamples.R;

import java.io.IOException;

import static android.content.ContentValues.TAG;

/**
 * Abstract activity which handles live-cycle events.
 * Feel free to extend from this activity when setting up your own AR-Activity 
 *
 */
public abstract class BaseArchitectCamActivity extends Activity {

	/**
	 * holds the Wikitude SDK AR-View, this is where camera, markers, compass, 3D models etc. are rendered
	 */
	protected ArchitectView architectView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(getContentViewId());
		//请确保AR视图只对支持的设备开放
		if(ArchitectView.isDeviceSupported(this)){
			Log.i(TAG,"device support ar view");
			architectView = (ArchitectView)findViewById(R.id.architectView);
			//Geo = 1       Tracking2D = 2       Geo+Tracking2D = 3
			final StartupConfiguration config = new StartupConfiguration(Constants.WIKITUDE_SDK_KEY, 1, StartupConfiguration.CameraPosition.DEFAULT);
			try {
				this.architectView.onCreate(config);
			} catch (RuntimeException rex) {
				this.architectView = null;
			}
		}
	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState){
		super.onPostCreate(savedInstanceState);
		if (architectView!=null ) {
			// call mandatory live-cycle method of architectView
			this.architectView.onPostCreate();
			try {
				architectView.load(getARchitectWorldPath());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * abstract methods
	 * @return
     */
	protected abstract int getContentViewId();
	protected abstract String getARchitectWorldPath();
}