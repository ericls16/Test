package com.ls.okhttp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by VIC1 on 2016/9/27.
 */

public class ResultActivity extends AppCompatActivity {

    private TextView tvResult;
    private String strResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        initView();
        initListener();
        initData();
    }

    private void initView() {
        tvResult=(TextView) findViewById(R.id.tv_result);
    }

    private void initListener() {

    }
    
    private void initData() {
        strResult=getIntent().getStringExtra("result");
        tvResult.setText(strResult);
    }
}
