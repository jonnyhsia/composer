package com.jonnyhsia.composer.widget;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * RecyclerView 纵向布局简单分割线
 */
public class DividerVertical extends RecyclerView.ItemDecoration {

    private Paint mPaint;

    /**
     * 是否绘制列表顶部的分割线
     */
    private boolean mDrawListTopDivider;

    /**
     * 是否绘制列表底部的分割线
     */
    private boolean mDrawListBottomDivider;
    private int dividerHeight;

    public DividerVertical(boolean drawTop, boolean drawBottom, int dividerHeight) {
        this(drawTop, drawBottom, dividerHeight, Color.TRANSPARENT);
    }

    public DividerVertical(boolean drawTop, boolean drawBottom, int dividerHeight, int color) {
        this.mDrawListTopDivider = drawTop;
        this.mDrawListBottomDivider = drawBottom;
        this.dividerHeight = dividerHeight;
        mPaint = new Paint();
        mPaint.setColor(color);
        mPaint.setStyle(Paint.Style.FILL);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        int position = parent.getChildLayoutPosition(view);
        if (mDrawListTopDivider && mDrawListBottomDivider) {
            if (position == 0) {
                outRect.set(0, dividerHeight, 0, dividerHeight);
            } else {
                outRect.set(0, 0, 0, dividerHeight);
            }

            return;
        }
        if (mDrawListTopDivider) {
            outRect.set(0, dividerHeight, 0, 0);
            return;
        }
        if (mDrawListBottomDivider) {
            outRect.set(0, 0, 0, dividerHeight);
            return;
        }
        if (position == 0)
            outRect.set(0, 0, 0, 0);
        else
            outRect.set(0, dividerHeight, 0, 0);
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
        final int left = parent.getPaddingLeft();
        final int right = parent.getWidth() - parent.getPaddingRight();

        final int count = parent.getChildCount();
        for (int i = 0; i < count - 1; i++) {
            final View child = parent.getChildAt(i);
            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
            final int top = child.getBottom() + params.bottomMargin
                    + Math.round(child.getTranslationY());
            final int bottom = top + dividerHeight;
            c.drawRect(left, top, right, bottom, mPaint);
        }

        //第一条分割线
        if (mDrawListTopDivider)
            c.drawRect(left
                    , parent.getPaddingTop()
                    , right
                    , parent.getPaddingTop() + dividerHeight
                    , mPaint);
        if (mDrawListBottomDivider)
            c.drawRect(left
                    , parent.getBottom() - parent.getPaddingBottom() - dividerHeight
                    , right
                    , parent.getBottom() - parent.getPaddingBottom()
                    , mPaint);
    }
}
