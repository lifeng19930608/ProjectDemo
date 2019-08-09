package com.android.base.widget;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.base.R;

/**
 * author  : 指尖的力量
 * date    : 2019-08-08 16:48
 * desc    : 自定义的全局加载动画
 * modify  :
 * version : 1.0
 */
public class LoadingLayout extends RelativeLayout {

    private ImageView iv_loading;
    private TextView tv_loading;

    public LoadingLayout(Context context) {
        super(context);
    }

    public LoadingLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LoadingLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        View view = getChildAt(0);
        iv_loading = view.findViewById(R.id.iv_loading);
        tv_loading = view.findViewById(R.id.tv_loading);
        if (iv_loading != null) {
            ((AnimationDrawable) iv_loading.getDrawable()).start();
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (iv_loading != null) {
            ((AnimationDrawable) iv_loading.getDrawable()).stop();
        }
    }

    @Override
    public void setVisibility(int visibility) {
        super.setVisibility(visibility);
        if (iv_loading != null) {
            if (visibility == VISIBLE) {
                ((AnimationDrawable) iv_loading.getDrawable()).start();
            } else if (visibility == GONE) {
                ((AnimationDrawable) iv_loading.getDrawable()).stop();
            }
        }
    }

    public void setMessage(String message) {
        if (tv_loading != null) {
            tv_loading.setText(message);
        }
    }

}

