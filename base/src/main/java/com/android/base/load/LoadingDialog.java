package com.android.base.load;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.android.base.R;
import com.android.base.base.BaseActivity;
import com.android.base.base.BaseCenterDialog;

/**
 * author  : 指尖的力量
 * date    : 2019-08-09 09:52
 * desc    :
 * modify  :
 * version : 1.0
 */

public class LoadingDialog extends BaseCenterDialog {

    private ImageView iv_loading;
    private TextView tv_loading;

    public LoadingDialog(@NonNull BaseActivity activity) {
        super(activity);
    }

    public LoadingDialog(@NonNull Context context, int theme) {
        super(context, theme);
    }

    public LoadingDialog(@NonNull Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    @Override
    public void init() {
        iv_loading = findViewById(R.id.iv_loading);
        tv_loading = findViewById(R.id.tv_loading);

    }

    @Override
    public int getLayoutId() {
        return R.layout.dialog_loading;
    }

    public void setMessage(String message) {
        if (tv_loading != null) {
            tv_loading.setText(message);
        }
    }

    @Override
    public void dismiss() {
        super.dismiss();
        if (iv_loading != null) {
            ((AnimationDrawable) iv_loading.getDrawable()).stop();
        }
    }

    @Override
    public void show() {
        super.show();
        if (iv_loading != null) {
            ((AnimationDrawable) iv_loading.getDrawable()).start();
        }
    }
}
