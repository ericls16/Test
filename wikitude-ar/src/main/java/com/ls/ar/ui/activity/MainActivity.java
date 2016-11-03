package com.ls.ar.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.ls.ar.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_demo:
                startActivity(new Intent(this,DemoActivity.class));
                break;
            default:
                break;
        }
    }
}
