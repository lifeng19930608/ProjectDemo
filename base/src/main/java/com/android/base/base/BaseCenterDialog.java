package com.android.base.base;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;

import com.android.base.utils.ScreenUtils;

/**
 * author  : 指尖的力量
 * date    : 2019-08-07 19:36
 * desc    :
 * modify  :
 * version : 1.0
 */

public abstract class BaseCenterDialog extends Dialog {

    private BaseActivity activity;

    public BaseCenterDialog(@NonNull BaseActivity activity) {
        super(activity);
        this.activity = activity;
    }

    public BaseCenterDialog(@NonNull Context context, int theme) {
        super(context, theme);
    }

    public BaseCenterDialog(@NonNull Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    public abstract void init();

    public abstract int getLayoutId();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window window = getWindow();
        if (window != null) {
            window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            requestWindowFeature(Window.FEATURE_NO_TITLE);
        }
        setContentView(getLayoutId());
        if (window != null) {
            WindowManager.LayoutParams windowParams = window.getAttributes();
            windowParams.gravity = Gravity.CENTER;
            windowParams.width = ScreenUtils.getScreenWidth(getActivity());
            window.setAttributes(windowParams);
        }
        init();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    public void runOnUIThread(Runnable runnable) {
        getActivity().runOnUiThread(runnable);
    }

    public BaseActivity getActivity() {
        return activity;
    }

}

