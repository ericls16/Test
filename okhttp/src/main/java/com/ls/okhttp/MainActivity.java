package com.ls.okhttp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private OkHttpClient okHttpClient;
    private Button btnGet;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            String result = "";
            switch (msg.what) {
                /**get同步**/
                case 1:
                    result = msg.obj.toString();
                    break;
                default:
                    break;
            }
            Intent intent = new Intent(MainActivity.this, ResultActivity.class);
            intent.putExtra("result", result);
            startActivity(intent);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initOkhttp();
        initView();
        initListener();
    }

    private void initOkhttp() {
        okHttpClient = new OkHttpClient.Builder().build();
    }

    private void initView() {
        btnGet = (Button) findViewById(R.id.btn_get);
    }

    private void initListener() {
        btnGet.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            /**get同步**/
            case R.id.btn_get:
                getRequestExecute();
                break;
            default:
                break;
        }
    }

    /**
     * get同步
     */
    private void getRequestExecute() {
        Log.i("LOG_RESPONSE", "getRequestExecute");

        //执行请求(Android 4.0后必须在子线程去执行网络请求)
        new Thread(new Runnable() {
            @Override
            public void run() {
                String url = "http://apis.baidu.com/heweather/weather/free?city=beijing";
                Request request = new Request.Builder()
                        .url(url)
                        .header("apikey", "599554fc2b0347003f4d4e59980849b2")
                        .get()
                        .build();
                Call call = okHttpClient.newCall(request);
                Message msg = handler.obtainMessage();
                msg.what = 1;
                try {
                    Response response = call.execute(); //如果网络连接失败此处直接抛异常
                    if (response.isSuccessful()) {
                        Log.i("LOG_RESPONSE", "SUCCESS");
                        msg.obj = response.body().string();
                    } else {
                        Log.i("LOG_RESPONSE", "FAILURE");
                        msg.obj = "请求失败！";
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    msg.obj = "网络连接失败！";
                }
                handler.sendMessage(msg);
            }
        }).start();

    }


}



























