package com.jonnyhsia.uilib.widget;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;

import me.everything.android.ui.overscroll.OverScrollDecoratorHelper;

public class BounceViewPager extends ViewPager {
    public BounceViewPager(@NonNull Context context) {
        super(context);
    }

    public BounceViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setUpOverScroll();
    }

    private void setUpOverScroll() {
        OverScrollDecoratorHelper.setUpOverScroll(this);
    }
}
