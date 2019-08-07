package com.android.base.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * author  : 指尖的力量
 * date    : 2019-08-07 16:30
 * desc    :
 * modify  :
 * version : 1.0
 */

public class SharedPreferencesUtils {

    private static final String NAME = "SeeYouPlan_config";//工程名称

    private SharedPreferencesUtils() {
    }

    /**
     * 保存字符串
     *
     * @param key
     * @param value
     */
    public static boolean putString(String key, String value) {
        SharedPreferences sharedPreferences = ContextUtils.get().getSharedPreferences(NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        return editor.commit();
    }

    /**
     * 获取字符值
     *
     * @param key
     * @return
     */
    public static String getString(String key) {
        return getString(key, "");
    }

    /**
     * 获取字符值
     *
     * @param key
     * @param defaultValue
     * @return
     */
    public static String getString(String key, String defaultValue) {
        SharedPreferences sharedPreferences = ContextUtils.get().getSharedPreferences(NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(key, defaultValue);
    }

    /**
     * 保存int型
     *
     * @param key
     * @param value
     */
    public static boolean putInt(String key, int value) {
        SharedPreferences sharedPreferences = ContextUtils.get().getSharedPreferences(NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(key, value);
        return editor.commit();
    }

    /**
     * 获取int值
     *
     * @param key
     * @return
     */
    public static int getInt(String key) {
        return getInt(key, 0);
    }

    /**
     * 获取int值
     *
     * @param key
     * @param defaultValue
     * @return
     */
    public static int getInt(String key, int defaultValue) {
        SharedPreferences sharedPreferences = ContextUtils.get().getSharedPreferences(NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getInt(key, defaultValue);
    }

    /**
     * 保存long型
     *
     * @param key
     * @param value
     */
    public static boolean putLong(String key, long value) {
        SharedPreferences sharedPreferences = ContextUtils.get().getSharedPreferences(NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putLong(key, value);
        return editor.commit();
    }

    /**
     * 获取long值
     *
     * @param key
     * @return
     */
    public static long getLong(String key) {
        return getLong(key, 0);
    }

    /**
     * 获取long值
     *
     * @param key
     * @param defaultValue
     * @return
     */
    public static long getLong(String key, long defaultValue) {
        SharedPreferences sharedPreferences = ContextUtils.get().getSharedPreferences(NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getLong(key, defaultValue);
    }

    /**
     * 保存float型
     *
     * @param key
     * @param value
     */
    public static boolean putFloat(String key, float value) {
        SharedPreferences sharedPreferences = ContextUtils.get().getSharedPreferences(NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putFloat(key, value);
        return editor.commit();
    }

    /**
     * 获取float值
     *
     * @param key
     * @return
     */
    public static float getFloat(String key) {
        return getFloat(key, 0);
    }

    /**
     * 获取float值
     *
     * @param key
     * @param defaultValue
     * @return
     */
    public static float getFloat(String key, float defaultValue) {
        SharedPreferences sharedPreferences = ContextUtils.get().getSharedPreferences(NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getFloat(key, defaultValue);
    }

    /**
     * 保存布尔值
     *
     * @param key
     * @param value
     */
    public static boolean putBoolean(String key, boolean value) {
        SharedPreferences sharedPreferences = ContextUtils.get().getSharedPreferences(NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(key, value);
        return editor.commit();
    }

    /**
     * 获取布尔值
     *
     * @param key
     * @return
     */
    public static boolean getBoolean(String key) {
        return getBoolean(key, false);
    }

    /**
     * 获取布尔值
     *
     * @param key
     * @param defaultValue
     * @return
     */
    public static boolean getBoolean(String key, boolean defaultValue) {
        SharedPreferences sharedPreferences = ContextUtils.get().getSharedPreferences(NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean(key, defaultValue);
    }

    public static void clear() {
        SharedPreferences sharedPreferences = ContextUtils.get().getSharedPreferences(NAME, Context.MODE_PRIVATE);
        sharedPreferences.edit().clear().apply();
    }
}
