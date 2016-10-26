package com.ls.app;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

/**
 * 陀螺仪
 * Created by VIC1 on 2016/10/25.
 */

public class GyroscopeActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView txtResult;
    private SensorManager sensorManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gyroscope);
        txtResult= (TextView) findViewById(R.id.txt_result);
        initData();
        //传感器管理
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
    }

    @Override
    protected void onResume() {
        super.onResume();
//        sensorManager.registerListener(sensorListener, sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE), SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(sensorListener, sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);
    }

    /**
     * sensorListener
     */
    final SensorEventListener sensorListener = new SensorEventListener() {

        @Override
        public void onSensorChanged(SensorEvent event) {
            if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
                Log.i("ACCELEROMETER", "onSensorChanged");
                float x = event.values[0];
                float y = event.values[1];
                float z = event.values[2];
                Log.i("GYROSCOPE", "X方向加速度为：" + x);
                Log.i("GYROSCOPE", "y方向加速度为：" + y);
                Log.i("GYROSCOPE", "z方向加速度为：" + z);
            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
            //精度发生变化
            Log.i("ACCURACY", "onAccuracyChanged");
        }
    };



    private void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_start:
                break;
        }
    }

    /**
     * 很关键的部分：注意，说明文档中提到，即使activity不可见的时候，感应器依然会继续的工作，测试的时候可以发现，没有正常的刷新频率
     * 也会非常高，所以一定要在onPause方法中关闭触发器，否则讲耗费用户大量电量，很不负责。
     */
    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(sensorListener);
    }
}
