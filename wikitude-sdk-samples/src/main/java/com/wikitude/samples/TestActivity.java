package com.wikitude.samples;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.wikitude.sdksamples.R;

/**
 * Created by VIC1 on 2016/11/7.
 */

public class TestActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        Bundle extras = getIntent().getExtras();
        if (extras != null){
            String name = extras.getString("name");
            Integer age = extras.getInt("age");

            if (name!=null && age!=null)
            {
                Log.i("HTML","name="+name+",age="+age);
            }
        }else{
            //no extras, get over it!!
            Log.i("HTML","name=null,age=null");
        }

    }
}
