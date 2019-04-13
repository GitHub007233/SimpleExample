package com.t123i456x.stylechange;

import android.view.MotionEvent;
import android.view.View;

/**
 * 触摸点击基类
 */
public abstract class BaseTouchListener implements View.OnTouchListener{

    protected boolean hasPerformedActionUp = false;

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            //按钮点击时触发点击效果
            actionDown(v);
            hasPerformedActionUp = true;
            v.getParent().requestDisallowInterceptTouchEvent(true);
        }
        if (event.getAction() == MotionEvent.ACTION_MOVE && hasPerformedActionUp) {
            float offsetX = event.getX();
            float offsetY = event.getY();
            //触摸区域超出view范围时取消点击效果
            if ((offsetX < 0 || offsetY < 0 || offsetX > v.getWidth() || offsetY > v.getHeight())) {
                actionUp(v);
                hasPerformedActionUp = false;
                v.getParent().requestDisallowInterceptTouchEvent(false);
            }
        }
        if (event.getAction() == MotionEvent.ACTION_UP && hasPerformedActionUp) {
            //按钮松开时取消效果
            actionUp(v);
        }
        return false;
    }

    //取消点击效果
    protected abstract void actionUp(View view);

    //点击效果(比如缩放，高亮，替换背景等等)
    protected abstract void actionDown(View view);
}