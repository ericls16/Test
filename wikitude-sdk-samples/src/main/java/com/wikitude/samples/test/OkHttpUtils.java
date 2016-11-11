package com.wikitude.samples.test;

import android.util.Log;

import com.zhy.http.okhttp.callback.FileCallBack;

import java.io.File;

import okhttp3.Call;

/**
 * Created by VIC1 on 2016/11/10.
 */

public class OkHttpUtils {


    public static void downloadFile(String url,String destFileDir, String destFileName,final DownLoadListener listener) {
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

                    }

                    @Override
                    public void onResponse(File response, int id) {
                        listener.callBack(response,id);
                    }
                });
    }

}
