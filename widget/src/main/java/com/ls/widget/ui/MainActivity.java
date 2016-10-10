package com.ls.widget.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.ls.widget.R;
import com.ls.widget.ui.recyclerview.RvMainActivity;

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
            default:
                break;
        }
    }
}
