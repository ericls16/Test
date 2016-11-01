package com.ls.app;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

/**
 * Created by VIC1 on 2016/10/28.
 */

public class CalcStepActivity extends Activity implements View.OnClickListener {

    private TextView txtStepCount;
    private TextView txtStepDetector;
    private SensorManager mSensorManager;
    private Sensor  mStepCount;
    private Sensor  mStepDetector;
    private int stepCount=0;

    private final SensorEventListener sensorListener = new SensorEventListener() {

        @Override
        public void onSensorChanged(SensorEvent event) {
//            float x = event.values[0];
//            float y = event.values[1];
//            float z = event.values[2];

            if (event.sensor.getType() == Sensor.TYPE_STEP_COUNTER) {
                txtStepCount.setText(event.values[0] + "步");
                Log.e("STEP","count="+event.values[0]);
            }

            if (event.sensor.getType() == Sensor.TYPE_STEP_DETECTOR) {
                if (event.values[0] == 1.0) {
                    stepCount++;
                    txtStepDetector.setText(stepCount + "步");
                    Log.e("STEP","detector-1="+event.values[0]);
                }
            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calc_step);
        txtStepCount= (TextView) findViewById(R.id.txt_step_count);
        txtStepDetector= (TextView) findViewById(R.id.txt_step_detector);
        //传感器管理
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_start:
//                mSensorManager.registerListener(sensorListener, mSensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION), SensorManager.SENSOR_DELAY_NORMAL);
                stepCount=0;
                txtStepDetector.setText(stepCount + "步");

                mStepCount = mSensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
                mStepDetector  = mSensorManager.getDefaultSensor(Sensor.TYPE_STEP_DETECTOR);

                mSensorManager.registerListener(sensorListener, mStepDetector, SensorManager.SENSOR_DELAY_FASTEST);
                mSensorManager.registerListener(sensorListener, mStepCount, SensorManager.SENSOR_DELAY_FASTEST);
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mSensorManager.unregisterListener(sensorListener, mStepDetector);
        mSensorManager.unregisterListener(sensorListener, mStepCount);
    }
}
