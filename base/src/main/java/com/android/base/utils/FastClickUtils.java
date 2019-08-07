package com.android.base.utils;

/**
 * author  : 指尖的力量
 * date    : 2019-08-07 16:29
 * desc    : 屏蔽用户的快速点击
 * modify  :
 * version : 1.0
 */

public class FastClickUtils {

    private static long lastClickTimeSpecific;

    private static String sign = "";

    public static boolean isFastDoubleOperate() {
        return isFastDoubleClick(500);
    }

    public static boolean isFastDoubleOperate(long interval) {
        return isFastDoubleClick(interval, "");
    }

    public static boolean isFastDoubleClick() {
        return isFastDoubleClick(500);
    }

    public static boolean isFastDoubleClick(long interval) {
        return isFastDoubleClick(interval, "");
    }

    /**
     * 针对单个控件的快速点击的屏蔽操作
     */
    public static boolean isFastDoubleClick(String tag) {
        return isFastDoubleClick(500, tag);
    }

    public static boolean isFastDoubleClick(long interval, String tag) {
        long time_start = System.currentTimeMillis();
        long time_end = time_start - lastClickTimeSpecific;
        if (0 < time_end && time_end < interval && sign.equals(tag)) {//1秒内按钮无效，这样可以控制快速点击，自己调整频率
            LogUtils.w("你点击的太快了！");
            return true;
        }
        sign = tag;
        lastClickTimeSpecific = time_start;
        return false;
    }
}
