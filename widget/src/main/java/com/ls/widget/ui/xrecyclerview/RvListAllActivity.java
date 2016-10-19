package com.ls.widget.ui.xrecyclerview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.ls.widget.R;
import com.ls.widget.adapter.RvGeneralAdapter;
import com.ls.widget.widget.decoration.DividerListItemDecoration;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by VIC1 on 2016/10/19.
 */

public class RvListAllActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rv_list_all);
        initView();
        initData();
    }

    private void initView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.addItemDecoration(new DividerListItemDecoration(this, DividerListItemDecoration.VERTICAL_LIST));
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
