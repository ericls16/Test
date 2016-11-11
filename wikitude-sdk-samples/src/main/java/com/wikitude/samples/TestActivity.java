package com.wikitude.samples;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.wikitude.sdksamples.R;
import com.zhy.http.okhttp.callback.FileCallBack;

import java.io.File;

import okhttp3.Call;

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
                        Log.i("download","download---onResponse");
                    }
                });
    }
}
