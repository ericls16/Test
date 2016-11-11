package com.wikitude.samples;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.hardware.SensorManager;
import android.location.LocationListener;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.widget.Toast;

import com.wikitude.architect.ArchitectView;
import com.wikitude.architect.ArchitectView.ArchitectUrlListener;
import com.wikitude.architect.ArchitectView.CaptureScreenCallback;
import com.wikitude.architect.ArchitectView.SensorAccuracyChangeListener;
import com.wikitude.architect.StartupConfiguration.CameraPosition;
import com.wikitude.sdksamples.R;
import com.zhy.http.okhttp.callback.FileCallBack;

import java.io.File;
import java.io.FileOutputStream;

import okhttp3.Call;

public class SampleCamActivity extends AbstractArchitectCamActivity {

	/**
	 * last time the calibration toast was shown, this avoids too many toast shown when compass needs calibration
	 */
	private long lastCalibrationToastShownTimeMillis = System.currentTimeMillis();

    protected Bitmap screenCapture = null;

    private static final int WIKITUDE_PERMISSIONS_REQUEST_EXTERNAL_STORAGE = 3;

	@Override
	public String getARchitectWorldPath() {
		return getIntent().getExtras().getString(
				MainSamplesListActivity.EXTRAS_KEY_ACTIVITY_ARCHITECT_WORLD_URL);
	}

	@Override
	public String getActivityTitle() {
		return (getIntent().getExtras() != null && getIntent().getExtras().get(
				MainSamplesListActivity.EXTRAS_KEY_ACTIVITY_TITLE_STRING) != null) ? getIntent()
				.getExtras().getString(MainSamplesListActivity.EXTRAS_KEY_ACTIVITY_TITLE_STRING)
				: "Test-World";
	}

	@Override
	public int getContentViewId() {
		return R.layout.sample_cam;
	}

	@Override
	public int getArchitectViewId() {
		return R.id.architectView;
	}
	
	@Override
	public String getWikitudeSDKLicenseKey() {
		return WikitudeSDKConstants.WIKITUDE_SDK_KEY;
	}
	
	@Override
	public SensorAccuracyChangeListener getSensorAccuracyListener() {
		return new SensorAccuracyChangeListener() {
			@Override
			public void onCompassAccuracyChanged( int accuracy ) {
				/* UNRELIABLE = 0, LOW = 1, MEDIUM = 2, HIGH = 3 */
				if ( accuracy < SensorManager.SENSOR_STATUS_ACCURACY_MEDIUM && SampleCamActivity.this != null && !SampleCamActivity.this.isFinishing() && System.currentTimeMillis() - SampleCamActivity.this.lastCalibrationToastShownTimeMillis > 5 * 1000) {
					Toast.makeText( SampleCamActivity.this, R.string.compass_accuracy_low, Toast.LENGTH_LONG ).show();
					SampleCamActivity.this.lastCalibrationToastShownTimeMillis = System.currentTimeMillis();
				}
			}
		};
	}

	@Override
	public ArchitectUrlListener getUrlListener() {
		Log.i("CALL_NATIVE","SampleCamActivity->getUrlListener()");
		return new ArchitectUrlListener() {

			@Override
			public boolean urlWasInvoked(String uriString) {
				Uri invokedUri = Uri.parse(uriString);
				// pressed "More" button on POI-detail panel
				if ("markerselected".equalsIgnoreCase(invokedUri.getHost())) {
					Log.i("CALL_NATIVE","SampleCamActivity->getUrlListener()->urlWasInvoked->markerselected");
					final Intent poiDetailIntent = new Intent(SampleCamActivity.this, SamplePoiDetailActivity.class);
					poiDetailIntent.putExtra(SamplePoiDetailActivity.EXTRAS_KEY_POI_ID, String.valueOf(invokedUri.getQueryParameter("id")) );
					poiDetailIntent.putExtra(SamplePoiDetailActivity.EXTRAS_KEY_POI_TITILE, String.valueOf(invokedUri.getQueryParameter("title")) );
					poiDetailIntent.putExtra(SamplePoiDetailActivity.EXTRAS_KEY_POI_DESCR, String.valueOf(invokedUri.getQueryParameter("description")) );
					SampleCamActivity.this.startActivity(poiDetailIntent);
					return true;
				}
				
				// pressed snapshot button. check if host is button to fetch e.g. 'architectsdk://button?action=captureScreen', you may add more checks if more buttons are used inside AR scene
				else if ("button".equalsIgnoreCase(invokedUri.getHost())) {
					Log.i("CALL_NATIVE","SampleCamActivity->getUrlListener()->urlWasInvoked->button");
					SampleCamActivity.this.architectView.captureScreen(ArchitectView.CaptureScreenCallback.CAPTURE_MODE_CAM_AND_WEBVIEW, new CaptureScreenCallback() {
						
						@Override
						public void onScreenCaptured(final Bitmap screenCapture) {
							if ( ContextCompat.checkSelfPermission(SampleCamActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED ) {
                                SampleCamActivity.this.screenCapture = screenCapture;
                                ActivityCompat.requestPermissions(SampleCamActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, WIKITUDE_PERMISSIONS_REQUEST_EXTERNAL_STORAGE);
                            } else {
                                SampleCamActivity.this.saveScreenCaptureToExternalStorage(screenCapture);
                            }
						}
					});
				}

				//test
				if ("test".equalsIgnoreCase(invokedUri.getHost())) {
					Log.i("CALL_NATIVE","SampleCamActivity->getUrlListener()->urlWasInvoked->test");
					String id=invokedUri.getQueryParameter("id");
					String params="World.loadPoisFromJsonData" + "( " + id + " );";
					architectView.callJavascript(params);
					return true;
				}

				//download file
				if ("download".equalsIgnoreCase(invokedUri.getHost())) {
					String id=invokedUri.getQueryParameter("id");
					Log.i("download","download---download-id="+id);
					goDownLoadFile();
					return true;
				}
				return true;
			}
		};
	}

	private void goDownLoadFile() {
		File file = new File(getCacheDir().getAbsolutePath() + "/android.jpg");
		if(file.exists()){
			Log.i("download","delete");
			file.delete();
		}

		downloadFile("https://github.com/cnlius/images/blob/master/android.jpg", getCacheDir().getAbsolutePath(), "android.jpg");
	}

	public void downloadFile(String url,String destFileDir, String destFileName) {
		com.zhy.http.okhttp.OkHttpUtils
				.get()
				.url(url)
				.build()
				.execute(new FileCallBack(destFileDir,destFileName) {

					@Override
					public void inProgress(float progress, long total, int id) {
						super.inProgress(progress, total, id);
						Log.i("download","download---progress"+progress);
					}

					@Override
					public void onError(Call call, Exception e, int id) {
						Log.i("download","download="+e);
					}

					@Override
					public void onResponse(File response, int id) {
						String params="World.loadPoisFromJsonData" + "( '" + response.getAbsolutePath() + "' );";
						Log.i("download","download---onResponse"+response.getAbsolutePath());
						architectView.callJavascript(params);
					}
				});
	}

	@Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case WIKITUDE_PERMISSIONS_REQUEST_EXTERNAL_STORAGE: {
                if ( grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED ) {
                    this.saveScreenCaptureToExternalStorage(SampleCamActivity.this.screenCapture);
                } else {
                    Toast.makeText(this, "Please allow access to external storage, otherwise the screen capture can not be saved.", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

	@Override
	public ILocationProvider getLocationProvider(final LocationListener locationListener) {
		return new LocationProvider(this, locationListener);
	}
	
	@Override
	public float getInitialCullingDistanceMeters() {
		// you need to adjust this in case your POIs are more than 50km away from user here while loading or in JS code (compare 'AR.context.scene.cullingDistance')
		return ArchitectViewHolderInterface.CULLING_DISTANCE_DEFAULT_METERS;
	}

	@Override
	protected boolean hasGeo() {
		return getIntent().getExtras().getBoolean(
				MainSamplesListActivity.EXTRAS_KEY_ACTIVITY_GEO);
	}

	@Override
	protected boolean hasIR() {
		return getIntent().getExtras().getBoolean(
				MainSamplesListActivity.EXTRAS_KEY_ACTIVITY_IR);
	}

	@Override
	protected CameraPosition getCameraPosition() {
		return CameraPosition.DEFAULT;
	}

    protected void saveScreenCaptureToExternalStorage(Bitmap screenCapture) {
        if ( screenCapture != null ) {
            // store screenCapture into external cache directory
            final File screenCaptureFile = new File(Environment.getExternalStorageDirectory().toString(), "screenCapture_" + System.currentTimeMillis() + ".jpg");

            // 1. Save bitmap to file & compress to jpeg. You may use PNG too
            try {

                final FileOutputStream out = new FileOutputStream(screenCaptureFile);
                screenCapture.compress(Bitmap.CompressFormat.JPEG, 90, out);
                out.flush();
                out.close();

                // 2. create send intent
                final Intent share = new Intent(Intent.ACTION_SEND);
                share.setType("image/jpg");
                share.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(screenCaptureFile));

                // 3. launch intent-chooser
                final String chooserTitle = "Share Snaphot";
                SampleCamActivity.this.startActivity(Intent.createChooser(share, chooserTitle));

            } catch (final Exception e) {
                // should not occur when all permissions are set
                SampleCamActivity.this.runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        // show toast message in case something went wrong
                        Toast.makeText(SampleCamActivity.this, "Unexpected error, " + e, Toast.LENGTH_LONG).show();
                    }
                });
            }
        }
    }

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.e("TEMP","SampleCamActivity->onCreate()");
	}

	@Override
	protected void onPause() {
		super.onPause();
		Log.e("TEMP","SampleCamActivity->onPause()");
	}

}
