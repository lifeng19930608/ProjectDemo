package com.android.base.utils;

import android.text.TextUtils;
import android.util.Log;

/**
 * author  : 指尖的力量
 * date    : 2019-08-06 17:18
 * desc    : 日志打印工具类
 * modify  :
 * version : 1.0
 */

public class LogUtils {

    private static final int LOG_MAX_SHOWN_LENGTH = 5000;

    // 控制日志信息是否显示 发布的时候关掉
    public final static boolean DEBUG = true;

    // 默认的日志过滤Tag (使用工程名)
    public final static String DEFAULT_TAG = "====";

    public static void v(Object msg) {
        v(DEFAULT_TAG, String.valueOf(msg));
    }

    public static void i(Object msg) {
        i(DEFAULT_TAG, String.valueOf(msg));
    }

    public static void d(Object msg) {
        d(DEFAULT_TAG, String.valueOf(msg));
    }

    public static void w(Object msg) {
        w(DEFAULT_TAG, String.valueOf(msg));
    }

    public static void e(Object msg) {
        e(DEFAULT_TAG, String.valueOf(msg));
    }


    public static void v(String tag, String msg) {
        if (DEBUG && !TextUtils.isEmpty(tag) && !TextUtils.isEmpty(msg)) {
            String[] logs = handleLogContent(msg);
            for (String log : logs) {
                if (!TextUtils.isEmpty(log)) {
                    Log.v(tag, log);
                }
            }
        }
    }

    public static void i(String tag, String msg) {
        if (DEBUG && !TextUtils.isEmpty(tag) && !TextUtils.isEmpty(msg)) {
            String[] logs = handleLogContent(msg);
            for (String log : logs) {
                if (!TextUtils.isEmpty(log)) {
                    Log.i(tag, log);
                }
            }
        }
    }

    public static void d(String tag, String msg) {
        if (DEBUG && !TextUtils.isEmpty(tag) && !TextUtils.isEmpty(msg)) {
            String[] logs = handleLogContent(msg);
            for (String log : logs) {
                if (!TextUtils.isEmpty(log)) {
                    Log.d(tag, log);
                }
            }
        }
    }

    public static void w(String tag, String msg) {
        if (DEBUG && !TextUtils.isEmpty(tag) && !TextUtils.isEmpty(msg)) {
            String[] logs = handleLogContent(msg);
            for (String log : logs) {
                if (!TextUtils.isEmpty(log)) {
                    Log.w(tag, log);
                }
            }
        }
    }

    public static void e(String tag, String msg) {
        if (DEBUG && !TextUtils.isEmpty(tag) && !TextUtils.isEmpty(msg)) {
            String[] logs = handleLogContent(msg);
            for (String log : logs) {
                if (!TextUtils.isEmpty(log)) {
                    Log.e(tag, log);
                }
            }
        }
    }

    private static String[] handleLogContent(String log) {
        String[] logs;
        if (log.length() <= LOG_MAX_SHOWN_LENGTH) {
            logs = new String[]{log};
        } else {
            int sections = (log.length() / LOG_MAX_SHOWN_LENGTH);
            if (log.length() % LOG_MAX_SHOWN_LENGTH != 0) {
                sections += 1;
            }
            logs = new String[sections];
            for (int i = 0; i < logs.length; i++) {
                int end = ((i + 1) * LOG_MAX_SHOWN_LENGTH);
                if (end > log.length()) {
                    end = log.length();
                }
                logs[i] = log.substring(i * LOG_MAX_SHOWN_LENGTH, end);
            }
        }
        return logs;
    }
}
