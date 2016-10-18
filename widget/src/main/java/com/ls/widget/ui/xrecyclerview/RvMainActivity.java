package com.ls.widget.ui.xrecyclerview;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.ls.widget.R;

/**
 * Created by VIC1 on 2016/10/10.
 */

public class RvMainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rv_main);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_general:
                startActivity(new Intent(this,RvListActivity.class));
                break;
            case R.id.btn_style:
                startActivity(new Intent(this,RvGridActivity.class));
                break;
            case R.id.btn_grid2:
                startActivity(new Intent(this,RvGrid2Activity.class));
                break;
            default:
                break;
        }
    }
}