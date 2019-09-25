package com.zhpan.library.custom_view;

import android.content.Context;
import androidx.viewpager.widget.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class MViewPaper extends ViewPager {

    private boolean mDisableSroll = true;

    public MViewPaper(Context context) {
        super(context);
    }

    public MViewPaper(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setDisableScroll(boolean bDisable) {
        mDisableSroll = bDisable;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (mDisableSroll) {
            return false;
        }
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (mDisableSroll) {
            return false;
        }
        return super.onTouchEvent(ev);
    }
}
