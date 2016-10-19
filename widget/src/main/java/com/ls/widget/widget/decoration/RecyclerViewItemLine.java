package com.ls.widget.widget.decoration;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;

/**
 * RecyclerView分割线
 * Created by VIC1 on 2016/10/19.
 */

public class RecyclerViewItemLine extends RecyclerView.ItemDecoration {

    private Drawable mDivider;
    private static final int[] ATTRS = new int[]{android.R.attr.listDivider};

    public RecyclerViewItemLine(Context context) {
        final TypedArray a = context.obtainStyledAttributes(ATTRS);
        mDivider = a.getDrawable(0);
        a.recycle();
    }
}
