package com.ls.widget.ui.recyclerview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import com.ls.widget.R;

/**
 * Created by VIC1 on 2016/10/10.
 */

public class RvStyleActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rv_style);
        initView();
        initListener();
        initData();
    }

    private void initView() {
    }
    private void initListener() {
    }
    private void initData() {
    }
}
