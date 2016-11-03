package com.ls.app.ui.activity.home;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.ls.app.R;

/**
 * Created by VIC1 on 2016/11/3.
 */

public class Home1Activity extends AppCompatActivity implements View.OnClickListener{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_home1);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            default:
                break;
        }
    }
}
