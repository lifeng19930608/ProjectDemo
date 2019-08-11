package com.android.base.config;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;

import androidx.annotation.Nullable;

/**
 * author  : 指尖的力量
 * date    : 2019-08-11 21:28
 * desc    : 获取清单文件的 meta-data 工具类
 * modify  :
 * version : 1.0
 */

public class MetaDataUtils {
    /**
     * Get manifest meta-data String
     *
     * @param key          key
     * @param defaultValue default value
     * @return value
     */
    public static String getMetaString(Context context, String key, String defaultValue) {
        String value = defaultValue;
        ApplicationInfo appInfo = getApplicationInfo(context);
        if (appInfo != null) {
            value = appInfo.metaData.getString(key, defaultValue);
        }
        return value;
    }

    /**
     * Get manifest meta-data int
     *
     * @param key          name
     * @param defaultValue default value
     * @return value
     */
    public static int getMetaInt(Context context, String key, int defaultValue) {
        int value = defaultValue;
        ApplicationInfo appInfo = getApplicationInfo(context);
        if (appInfo != null) {
            value = appInfo.metaData.getInt(key, defaultValue);
        }
        return value;
    }

    /**
     * Get manifest meta-data boolean
     *
     * @param key          name
     * @param defaultValue default value
     * @return value
     */
    public static boolean getMetaBoolean(Context context, String key, boolean defaultValue) {
        boolean value = defaultValue;
        ApplicationInfo appInfo = getApplicationInfo(context);
        if (appInfo != null) {
            value = appInfo.metaData.getBoolean(key, defaultValue);
        }
        return value;
    }

    @Nullable
    private static ApplicationInfo getApplicationInfo(Context context) {
        ApplicationInfo appInfo = null;
        try {
            appInfo = context.getApplicationContext().getPackageManager().getApplicationInfo(
                    context.getApplicationContext().getPackageName(), PackageManager.GET_META_DATA);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return appInfo;
    }
}