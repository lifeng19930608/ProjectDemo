package com.android.base.base;

import android.app.Application;

import com.android.base.BuildConfig;
import com.android.base.config.Key;
import com.android.base.utils.ApplicationUtils;
import com.android.base.utils.SharedPreferencesUtils;
import com.mob.MobSDK;
import com.tencent.bugly.crashreport.CrashReport;

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
        init();
    }

    private void init() {
        //腾讯 Bugly
        if (!BuildConfig.DEBUG) {
            CrashReport.UserStrategy strategy = new CrashReport.UserStrategy(this);
            strategy.setAppVersion(ApplicationUtils.getInstance().getVersionName()); //App的版本
            strategy.setAppPackageName(ApplicationUtils.getInstance().getPackageName()); //App的包名
            CrashReport.initCrashReport(this, "e4198ba203", false, strategy);
            CrashReport.setUserId(SharedPreferencesUtils.getString(Key.PrefKey.UID));//用户的id
        }
        //MobSDK 三方登陆分享
        MobSDK.init(this);
    }

}
