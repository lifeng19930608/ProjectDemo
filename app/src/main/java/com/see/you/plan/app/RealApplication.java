package com.see.you.plan.app;

import com.android.base.base.BaseApplication;

import cn.jpush.android.api.JPushInterface;

/**
 * author  : 指尖的力量
 * date    : 2019-08-09 15:41
 * desc    :
 * modify  :
 * version : 1.0
 */

public class RealApplication extends BaseApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        init();
    }

    private void init() {
        //JPush
        JPushInterface.setDebugMode(false);
        JPushInterface.init(this);
    }
}
