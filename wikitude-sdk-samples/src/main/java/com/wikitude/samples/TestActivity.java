package com.wikitude.samples;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.wikitude.samples.test.DownLoadListener;
import com.wikitude.samples.test.OkHttpUtils;
import com.wikitude.sdksamples.R;

import java.io.File;

/**
 * Created by VIC1 on 2016/11/7.
 */

public class TestActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goDownLoadFile();
            }
        });

    }

    private void goDownLoadFile() {
        OkHttpUtils.downloadFile("http://www.lewei.online/android.jpg", getCacheDir().getAbsolutePath(), "android.jpg", new DownLoadListener() {
            @Override
            public void callBack(File response, int id) {
                Log.i("download","download---complete");
            }
        });
    }
}
