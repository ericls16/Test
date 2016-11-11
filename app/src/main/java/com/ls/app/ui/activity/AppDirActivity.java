package com.ls.app.ui.activity;

import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.ls.app.R;

/**
 * Created by VIC1 on 2016/11/11.
 */

public class AppDirActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_app_dir);

        Log.i("FILE_DIR","getFilesDir="+ getFilesDir());
        Log.i("FILE_DIR","getCacheDir="+ getCacheDir());

        Log.i("FILE_DIR","getExternalFilesDir="+ getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS));
        Log.i("FILE_DIR","getExternalFilesDir="+ getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS));
        Log.i("FILE_DIR","getExternalCacheDir="+ getExternalCacheDir());

        Log.i("FILE_DIR","Path="+ Environment.getExternalStorageDirectory());
    }
}
