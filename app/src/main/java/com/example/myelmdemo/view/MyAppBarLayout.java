package com.example.myelmdemo.view;

import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.support.design.widget.AppBarLayout;
import android.support.v4.view.MotionEventCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.TextView;

import com.example.myelmdemo.DetailsActivity;
import com.example.myelmdemo.R;

/**
 * Created by wjn on 2017/2/17.
 */

public class MyAppBarLayout extends AppBarLayout {
    private static final long ANIMATE_LENGTH = 300;
    private float mFirstY;
    private float mFirstHeight;
    private int iDownY;
    private float mTouchSlop;
    private boolean mIsExpanded;
    private DetailsActivity mActivity;


    public MyAppBarLayout(Context context) {
        super(context);
    }

    public MyAppBarLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
    }

    @Override protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        mFirstY = getY();
        mFirstHeight = getHeight();
        Log.e("firstY",mFirstY+","+"firstHeight"+","+mFirstHeight);
    }

    public void restore(float y) {
        ValueAnimator restoreVa = ValueAnimator.ofFloat(y, mFirstY);
        restoreVa.setInterpolator(new DecelerateInterpolator());
        restoreVa.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override public void onAnimationUpdate(ValueAnimator animation) {
                float y = (float) animation.getAnimatedValue();
                setTranslationY(y);
               
            }
        });
        restoreVa.setDuration(ANIMATE_LENGTH);
        restoreVa.start();
    }

    public float getFirstY() {
        return mFirstY;
    }

    public float getFirstHeight() {
        return mFirstHeight;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                iDownY = (int) event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                float moveY = event.getY()-iDownY;
                setTranslationY(moveY);
                Log.e("move","down");
                break;
            case MotionEvent.ACTION_UP:
                Log.e("up","up");

                float upY = event.getY() - iDownY;
                float currentUpY = getY();
                if(upY<500){
                    restore(upY + currentUpY);
                }else{
                    finish();
                }

                return true;
        }
        return super.onTouchEvent(event);
    }

    private void finish() {
        TranslateAnimation finishTa = new TranslateAnimation(0, 0, 0, 1000);
        finishTa.setDuration(ANIMATE_LENGTH);
        finishTa.setFillAfter(true);
        finishTa.setAnimationListener(new Animation.AnimationListener() {
            @Override public void onAnimationStart(Animation animation) {

            }

            @Override public void onAnimationEnd(Animation animation) {
                mActivity.finish();
            }

            @Override public void onAnimationRepeat(Animation animation) {

            }
        });

        startAnimation(finishTa);
    }

    public void isExpanded(boolean b) {
        this.mIsExpanded = b;
    }
    public boolean getExpanded(){
        return mIsExpanded;
    }

    public void setActivity(DetailsActivity detailsActivity) {
        this.mActivity = detailsActivity;
    }
}
