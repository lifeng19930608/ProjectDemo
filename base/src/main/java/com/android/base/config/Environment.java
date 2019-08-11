package com.android.base.config;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.android.base.BuildConfig;
import com.android.base.utils.ContextUtils;

/**
 * author  : 指尖的力量
 * date    : 2019-08-11 21:29
 * desc    : 环境切换工具类
 * modify  :
 * version : 1.0
 */

public class Environment {

    private static String releaseType = "";
    public static final String RELEASE_TYPE_DEVELOP = "develop";//内部开发版本 可以切换环境
    public static final String RELEASE_TYPE_PRODUCT = "product";//发布版本

    public static final String ENV_DEVELOP = "develop";//开发
    public static final String ENV_TEST = "test";//测试
    public static final String ENV_PRODUCT = "product";//生产
    public static final String NAME = "Base";

    /**
     * 获取app安装的发行类型
     */
    public static String getReleaseType() {
        if (TextUtils.isEmpty(releaseType)) {
            releaseType = MetaDataUtils.getMetaString(ContextUtils.get(), Key.META_DATA.RELEASE_TYPE, RELEASE_TYPE_PRODUCT);
        }
        return releaseType;
    }

    /**
     * 获取app的安装环境
     */
    public static String getEnv() {
        if (getReleaseType().equals(RELEASE_TYPE_DEVELOP)) {
            // 如果是内部发行版本 默认开发环境
            SharedPreferences preferences = ContextUtils.get().getSharedPreferences(
                    NAME, Context.MODE_PRIVATE);
            if (BuildConfig.DEBUG) {
                return preferences.getString(Key.PrefKey.ENVIRONMENT, ENV_DEVELOP);
            } else {
                return preferences.getString(Key.PrefKey.ENVIRONMENT, ENV_TEST);
            }
        } else {
            return ENV_PRODUCT;
        }
    }

    /**
     * 切换app的安装环境
     */
    public static void setEnv(String env) {
        SharedPreferences preferences = ContextUtils.get().getSharedPreferences(
                NAME, Context.MODE_PRIVATE);
        preferences.edit().putString(Key.PrefKey.ENVIRONMENT, env).apply();
    }

    /**
     * 是否显示log信息
     */
    public static boolean isLogEnabled() {
        return BuildConfig.DEBUG || !getReleaseType().equals(RELEASE_TYPE_PRODUCT);
    }

}
