package com.ls.widget.ui.xrecyclerview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.ls.widget.R;
import com.ls.widget.adapter.RvGeneralAdapter;
import com.ls.widget.widget.decoration.DividerGridAllDecoration;
import com.ls.widget.widget.decoration.RecyclerViewItemLine;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by VIC1 on 2016/10/19.
 */

public class RvItemLineActivity extends AppCompatActivity {
    private RecyclerView mListRecyclerView;
    private RecyclerView mGridRecyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rv_item_line);
        initView();
        initData();
    }

    private void initView() {
        mListRecyclerView = (RecyclerView) findViewById(R.id.list);
        mListRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mListRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mListRecyclerView.addItemDecoration(new RecyclerViewItemLine(this,RecyclerViewItemLine.LIST));
//        mListRecyclerView.addItemDecoration(new RecyclerViewItemLine(this,RecyclerViewItemLine.LIST_VERTICAL_ALL));
//        mListRecyclerView.addItemDecoration(new RecyclerViewItemLine(this,RecyclerViewItemLine.LIST_ALL));
        //-------------------------------
        mGridRecyclerView = (RecyclerView) findViewById(R.id.grid);
        mGridRecyclerView.setLayoutManager(new GridLayoutManager(this,4));
        mGridRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mGridRecyclerView.addItemDecoration(new DividerGridAllDecoration(this));
    }

    private void initData() {
        List<String> data = new ArrayList<String>();
        for (int i = 'A'; i < 'z'; i++) {
            data.add("" + (char) i);
        }
        RvGeneralAdapter adapter=new RvGeneralAdapter(data);
        //-----------------------------------
        mListRecyclerView.setAdapter(adapter);
        mGridRecyclerView.setAdapter(adapter);
    }
}
