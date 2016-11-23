package com.ls.widget.ui;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;

import com.ls.widget.R;
import com.ls.widget.adapter.RvGeneralAdapter;
import com.ls.widget.databinding.LayoutTestBinding;
import com.ls.widget.widget.decoration.DividerListItemDecoration;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by VIC1 on 2016/11/23.
 */

public class TestX extends AppCompatActivity {

    private LayoutTestBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.layout_test);

        //设置布局管理器
        binding.list.setLayoutManager(new LinearLayoutManager(this));
        //设置item增加或移除动画
        binding.list.setItemAnimator(new DefaultItemAnimator());
        //设置自定义分割线
        binding.list.addItemDecoration(new DividerListItemDecoration(this, DividerListItemDecoration.VERTICAL_LIST));
        List<String> data = new ArrayList<String>();
        for (int i = 'A'; i < 'z'; i++) {
            data.add("" + (char) i);
        }
        RvGeneralAdapter adapter=new RvGeneralAdapter(data);
        binding.list.setAdapter(adapter);
    }
}
