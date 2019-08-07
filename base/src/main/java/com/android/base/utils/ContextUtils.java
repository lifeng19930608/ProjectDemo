package com.android.base.utils;

import android.content.Context;

import com.android.base.base.BaseApplication;

/**
 * author  : 指尖的力量
 * date    : 2019-08-07 17:08
 * desc    : 获取整个工程的上下文对象
 * modify  :
 * version : 1.0
 */

public class ContextUtils {
    public static Context get() {
        return BaseApplication.getInstance().getApplicationContext();
    }
}
