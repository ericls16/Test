package com.ls.widget.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.ls.widget.R;
import com.ls.widget.ui.xrecyclerview.RvMainActivity;
import com.ls.widget.ui.xswitch.SwitchTestActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.recyclerview:
                startActivity(new Intent(this, RvMainActivity.class));
                break;
            case R.id.switch_test:
                startActivity(new Intent(this, SwitchTestActivity.class));
                break;
            default:
                break;
        }
    }
}
