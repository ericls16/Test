package com.wikitude.samples.ls;

import com.wikitude.architect.ArchitectView;
import com.wikitude.sdksamples.R;

/**
 * Created by VIC1 on 2016/10/26.
 */

public class ArDemoActivity extends BaseArchitectCamActivity{

    private final static String TAG="TAG";
    private ArchitectView architectView;

    @Override
    protected int getContentViewId() {
        return R.layout.act_ar_demo;
    }

    @Override
    protected String getARchitectWorldPath() {
        return "cases/3d_moudle/index.html";
    }

}
