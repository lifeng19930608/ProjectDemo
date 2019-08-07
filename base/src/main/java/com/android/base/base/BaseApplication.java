package com.android.base.base;

import android.app.Application;

/**
 * author  : 指尖的力量
 * date    : 2019-08-07 17:04
 * desc    :
 * modify  :
 * version : 1.0
 */

public class BaseApplication extends Application {

    private static volatile BaseApplication baseApplication;

    //单例模式
    public static synchronized BaseApplication getInstance() {
        return baseApplication;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        baseApplication = this;
    }
}
