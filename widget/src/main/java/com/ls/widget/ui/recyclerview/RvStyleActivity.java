package com.ls.widget.ui.recyclerview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.ls.widget.R;
import com.ls.widget.adapter.RvGeneralAdapter;
import com.ls.widget.widget.DividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

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
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        //设置布局管理器
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        //设置item增加或移除动画
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        //设置自定义分割线
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
    }
    private void initListener() {
    }
    private void initData() {
        List<String> data = new ArrayList<String>();
        for (int i = 'A'; i < 'z'; i++) {
            data.add("" + (char) i);
        }
        RvGeneralAdapter adapter=new RvGeneralAdapter(data);
        mRecyclerView.setAdapter(adapter);
    }
}
