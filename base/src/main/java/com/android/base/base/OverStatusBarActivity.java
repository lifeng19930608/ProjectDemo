package com.android.base.base;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.android.base.utils.ScreenUtils;

/**
 * author  : 指尖的力量
 * date    : 2019-08-08 15:57
 * desc    : 将布局延伸至状态栏(比如闪屏和广告页面)
 * modify  :
 * version : 1.0
 */

public abstract class OverStatusBarActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ScreenUtils.setStatusBarOverWindow(this, false);
        }
        super.onCreate(savedInstanceState);
    }

}

