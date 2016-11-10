package com.wikitude.samples.test;

import com.zhy.http.okhttp.callback.FileCallBack;

import java.io.File;

import okhttp3.Call;

/**
 * Created by VIC1 on 2016/11/10.
 */

public class OkHttpUtils {

    String fileDir="assets/files";
    String fileName="temp";

    public void downloadFile(String destFileDir, String destFileName) {
        com.zhy.http.okhttp.OkHttpUtils
                .get()
                .url("http://www.lewei.online/android.jpg")
                .build()
                .execute(new FileCallBack("","android.jpg") {

                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(File response, int id) {

                    }
                });
    }

}
