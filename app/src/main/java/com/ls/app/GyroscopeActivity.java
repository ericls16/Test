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

    private long t1=0;

    //上一次的加速度
    private float lastXa=0f;
    private float lastYa=0f;
    private float lastZa=0f;
    //上一次的速度
    private float vX=0f;
    private float vY=0f;
    private float vZ=0f;
    //上一次的距离
    private double dX;
    private double dY;
    private double dZ;

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
//        sensorManager.registerListener(sensorListener, sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);
    }

    /**
     * sensorListener
     */
    final SensorEventListener sensorListener = new SensorEventListener() {

        @Override
        public void onSensorChanged(SensorEvent event) {
            if (event.sensor.getType() == Sensor.TYPE_LINEAR_ACCELERATION) {
                Log.i("TAG", "onSensorChanged");
                float x = event.values[0];
                float y = event.values[1];
                float z = event.values[2];
                Log.i("TAG", "X方向加速度为：" + x);
                Log.i("TAG", "y方向加速度为：" + y);
                Log.i("TAG", "z方向加速度为：" + z);

                if(Math.abs(x) < 0.1f) {
                    x = 0.0f;
                }
                if(Math.abs(y) < 0.1f) {
                    y = 0.0f;
                }
                if(Math.abs(z) < 0.1f) {
                    z = 0.0f;
                }

                long t2=System.currentTimeMillis();
                double interval= (double)(t2-t1)/1000;

                vX+= (double) (x+lastXa)/2.0f*interval;
                vY+= (double) (y+lastYa)/2.0f*interval;
                vZ+= (double) (z+lastZa)/2.0f*interval;

                dX+=vX*interval;
                dY+=vY*interval;
                dZ+=vZ*interval;

                double s= Math.sqrt(dX*dX+dY*dY+dZ*dZ);

                Log.i("TAG", "vx="+vX+",vy="+vY+",vz="+vZ);
                Log.i("TAG", "dx="+dX+",dy="+dY+",dz="+dZ);
                Log.i("TAG", "时间间隔"+interval);
                Log.i("TAG", "行走距离：" + s);
                txtResult.setText("dx="+dX+"\ndy="+dY+"\ndz="+dZ+"\n路程="+s);

                lastXa=x;
                lastYa=y;
                lastZa=z;
                t1=t2;
                Log.i("TAG", "--------------------------");

            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
            //精度发生变化
            Log.i("TAG", "onAccuracyChanged");
        }
    };



    private void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_start:
                sensorManager.registerListener(sensorListener, sensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION), SensorManager.SENSOR_DELAY_NORMAL);
                t1=System.currentTimeMillis();
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
