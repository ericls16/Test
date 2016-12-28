package com.ls.widget.ui;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;

import com.ls.widget.R;
import com.ls.widget.databinding.ActivityBadgeBinding;
import com.ls.widget.widget.badgeview.BadgeView;

/**
 * Created by ls on 2016/12/28.
 */

public class BadgeDemo extends AppCompatActivity implements View.OnClickListener{
    private ActivityBadgeBinding mBinding;
    private BadgeView badge;
    private int count=2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding= DataBindingUtil.setContentView(this, R.layout.activity_badge);

        setBadge(badge,count+"");
    }

    private void setBadge(BadgeView badge,String s) {
        if(badge==null){
            badge = new BadgeView(this);
            badge.setTargetView(mBinding.tv);
            badge.setBadgeGravity(Gravity.TOP | Gravity.RIGHT);
            badge.setTextSize(6);
            badge.setPadding(0, 0, 0, 0);
            badge.setHeight(32);
            badge.setWidth(32);
        }
        badge.setText(s);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn:
                count++;
                setBadge(badge,count+"");
                break;
            default:
                break;
        }
    }

    
}
