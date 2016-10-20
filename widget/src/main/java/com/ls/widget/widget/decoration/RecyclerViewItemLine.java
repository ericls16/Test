package com.ls.widget.widget.decoration;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

/**
 * RecyclerView分割线
 * Created by VIC1 on 2016/10/19.
 */

public class RecyclerViewItemLine extends RecyclerView.ItemDecoration {

    /**分割线类型**/
    public static final int LIST = 1001; //默认分割线：有最下面，没有最上面、左侧和右侧.
    public static final int LIST_VERTICAL_ALL = 1002; //分割线：有最上面和最下面，没有左侧和右侧.
    public static final int LIST_ALL = 1004; //分割线：四周都有.
    public static final int GRID = 2001; //分割线：没有最上面、最下面、左侧和右侧.
    public static final int GRID_VERTICAL_ALL = 2002; //分割线：有最上面和最下面，没有左侧和右侧.
    public static final int GRID_ALL = 2004; //分割线：分割线：四周都有.

    private static final int[] ATTRS = new int[]{
            android.R.attr.listDivider
    };

    private Drawable mDivider;
    private int mDividerType;

    public RecyclerViewItemLine(Context context,int dividerType) {
        final TypedArray a = context.obtainStyledAttributes(ATTRS);
        mDivider = a.getDrawable(0);
        a.recycle();
        setDividerType(dividerType);
    }


    public void setDividerType(int dividerType) {
        if (dividerType != LIST && dividerType != LIST_VERTICAL_ALL && dividerType!=LIST_ALL &&
                dividerType != GRID && dividerType != GRID_VERTICAL_ALL && dividerType != GRID_ALL) {
            throw new IllegalArgumentException("invalid divider type");
        }
        mDividerType=dividerType;
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        if (mDividerType == LIST ||mDividerType==LIST_VERTICAL_ALL) {
            drawVertical(c, parent);
        }else if(mDividerType==LIST_ALL) {
            drawVertical(c, parent);
            drawHorizontal(c, parent);
        }else if(mDividerType==GRID) {
            drawHorizontal(c, parent);
            drawVertical(c, parent);
        }else if(mDividerType==GRID_VERTICAL_ALL) {

        }else if(mDividerType==GRID_ALL) {

        }
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        if (mDividerType == LIST || mDividerType==LIST_VERTICAL_ALL) {
            outRect.set(0, 0, 0, mDivider.getIntrinsicHeight());
        }else if(mDividerType==LIST_ALL) {
            outRect.set(0, 0, mDivider.getIntrinsicWidth(), mDivider.getIntrinsicHeight());
        }else if(mDividerType==GRID) {
            int itemPosition = parent.getChildAdapterPosition(view);
            int spanCount = getSpanCount(parent);
            int childCount = parent.getAdapter().getItemCount();
            if (isLastRaw(parent, itemPosition, spanCount, childCount)) {
                // 如果是最后一行，则不需要绘制底部
                outRect.set(0, 0, mDivider.getIntrinsicWidth(), 0);
            } else if (isLastColum(parent, itemPosition, spanCount, childCount)) {
                // 如果是最后一列，则不需要绘制右边
                outRect.set(0, 0, 0, mDivider.getIntrinsicHeight());
            } else {
                outRect.set(0, 0, mDivider.getIntrinsicWidth(), mDivider.getIntrinsicHeight());
            }

        }else if(mDividerType==GRID_VERTICAL_ALL) {

        }else if(mDividerType==GRID_ALL) {

        }
    }

    public void drawVertical(Canvas c, RecyclerView parent) {
        final int left = parent.getPaddingLeft();
        final int right = parent.getWidth() - parent.getPaddingRight();

        final int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
            int top = 0;
            int bottom = 0;

            if(mDividerType!=GRID) {

                if (i == 0 && (mDividerType == LIST_VERTICAL_ALL || mDividerType == LIST_ALL)) {
                    //上方垂直分割线
                    top = child.getTop() + params.topMargin;
                    bottom = top + mDivider.getIntrinsicHeight();
                    mDivider.setBounds(left, top, right, bottom);
                    mDivider.draw(c);
                }

                //下方垂直分割线
                top = child.getBottom() + params.bottomMargin;
                bottom = top + mDivider.getIntrinsicHeight();
                mDivider.setBounds(left, top, right, bottom);
                mDivider.draw(c);
            }

            if(mDividerType==GRID){
                final int left2 = child.getLeft() - params.leftMargin;
                final int right2 = child.getRight() + params.rightMargin + mDivider.getIntrinsicWidth();
                top = child.getBottom() + params.bottomMargin;
                bottom = top + mDivider.getIntrinsicHeight();
                mDivider.setBounds(left2, top, right2, bottom);
                mDivider.draw(c);
            }
        }
    }

    public void drawHorizontal(Canvas c, RecyclerView parent) {
        final int top = parent.getPaddingTop();
        final int bottom = parent.getHeight() - parent.getPaddingBottom();

        final int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
            int left = 0;
            int right = 0;

            if(mDividerType!=GRID){
                //左侧垂直分割线
                left = child.getLeft() + params.leftMargin;
                right = left + mDivider.getIntrinsicWidth();
                mDivider.setBounds(left, top, right, bottom);
                mDivider.draw(c);
                //右侧垂直分割线
                left = child.getRight()+params.rightMargin;
                right = left+mDivider.getIntrinsicWidth();
                mDivider.setBounds(left, top, right, bottom);
                mDivider.draw(c);
            }

            if(mDividerType==GRID){
                final int top2 = child.getTop() - params.topMargin;
                final int bottom2 = child.getBottom() + params.bottomMargin;
                //右侧垂直分割线
                left = child.getRight()+params.rightMargin;
                right = left+mDivider.getIntrinsicWidth();
                mDivider.setBounds(left, top2, right, bottom2);
                mDivider.draw(c);
            }

        }
    }

    private boolean isLastColum(RecyclerView parent, int pos, int spanCount, int childCount) {
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            // 如果是最后一列，则不需要绘制右边
            if ((pos + 1) % spanCount == 0) {
                return true;
            }
        } else if (layoutManager instanceof StaggeredGridLayoutManager) {
            int orientation = ((StaggeredGridLayoutManager) layoutManager).getOrientation();
            if (orientation == StaggeredGridLayoutManager.VERTICAL) {
                // 如果是最后一列，则不需要绘制右边
                if ((pos + 1) % spanCount == 0) {
                    return true;
                }
            } else {
                childCount = childCount - childCount % spanCount;
                // 如果是最后一列，则不需要绘制右边
                if (pos >= childCount) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isLastRaw(RecyclerView parent, int pos, int spanCount, int childCount) {
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            childCount = childCount - childCount % spanCount;
            // 如果是最后一行，则不需要绘制底部
            if (pos >= childCount) {
                return true;
            }
        } else if (layoutManager instanceof StaggeredGridLayoutManager) {
            int orientation = ((StaggeredGridLayoutManager) layoutManager).getOrientation();
            // StaggeredGridLayoutManager 且纵向滚动
            if (orientation == StaggeredGridLayoutManager.VERTICAL) {
                childCount = childCount - childCount % spanCount;
                // 如果是最后一行，则不需要绘制底部
                if (pos >= childCount) {
                    return true;
                }
            } else {
                // StaggeredGridLayoutManager 且横向滚动
                // 如果是最后一行，则不需要绘制底部
                if ((pos + 1) % spanCount == 0) {
                    return true;
                }
            }
        }
        return false;
    }

    private int getSpanCount(RecyclerView parent) {
        // 列数
        int spanCount = -1;
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            spanCount = ((GridLayoutManager) layoutManager).getSpanCount();
        } else if (layoutManager instanceof StaggeredGridLayoutManager) {
            spanCount = ((StaggeredGridLayoutManager) layoutManager).getSpanCount();
        }
        return spanCount;
    }
}
