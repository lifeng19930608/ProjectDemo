package com.android.base.utils;

import android.content.Intent;
import android.os.Bundle;

/**
 * author  : 指尖的力量
 * date    : 2019-08-08 14:46
 * desc    : 页面跳转工具类
 * modify  :
 * version : 1.0
 */

public class IntentUtils {

    /**
     * 无参数跳转
     */
    public static <T> void startActivity(Class<T> cla) {
        Intent intent = new Intent(ContextUtils.get(), cla);
        ContextUtils.get().startActivity(intent);
    }

    /**
     * 带参数跳转
     */
    public static <T> void startActivity(Class<T> cla, Bundle b) {
        Intent intent = new Intent(ContextUtils.get(), cla);
        if (b != null) {
            intent.putExtras(b);
        }
        ContextUtils.get().startActivity(intent);
    }

    /**
     * 清空activity栈
     */
    public static <T> void startActivityClear(Class<T> cla) {
        Intent intent = new Intent(ContextUtils.get(), cla);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        ContextUtils.get().startActivity(intent);
    }

}
