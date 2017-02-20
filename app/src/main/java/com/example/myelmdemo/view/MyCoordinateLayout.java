package com.example.myelmdemo.view;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.MotionEventCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by wjn on 2017/2/17.
 */

public class MyCoordinateLayout extends CoordinatorLayout {
    private float iDownY;
    private float appBarY;
    private boolean isChild = false;

    public MyCoordinateLayout(Context context) {
        super(context);
    }

    public MyCoordinateLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyCoordinateLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (MotionEventCompat.getActionMasked(ev)) {
            case MotionEvent.ACTION_DOWN:
                isChild = false;
                iDownY = ev.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                float moveY = ev.getY() - iDownY;
                if (((MyAppBarLayout) getChildAt(0)).getExpanded() && (moveY > 0)) {
                    isChild = true;
                    ((MyAppBarLayout) getChildAt(0)).onTouchEvent(ev);
                    return true;
                }
                break;
            case MotionEvent.ACTION_UP:
                if(isChild){
                    ((MyAppBarLayout) getChildAt(0)).onTouchEvent(ev);
                    return true;
                }
                break;




        }

        return super.onTouchEvent(ev);
    }


}
