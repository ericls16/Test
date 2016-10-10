package com.ls.widget.listener;

import android.view.View;

/**
 * Created by VIC1 on 2016/10/10.
 */

public interface ClickListener {
    void onClick(View view, int position);

    void onLongClick(View view, int position);
}
