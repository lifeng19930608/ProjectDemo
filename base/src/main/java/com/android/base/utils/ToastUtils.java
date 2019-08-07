package com.android.base.utils;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.MainThread;

/**
 * author  : 指尖的力量
 * date    : 2019-08-06 17:24
 * desc    : 自定义的Toast工具类
 * modify  :
 * version : 1.0
 */

public class ToastUtils {

    private static Toast toast;

    @MainThread
    public static void shortShow(int id) {
        shortRes(id);
    }

    @MainThread
    public static void shortShow(CharSequence text) {
        shortText(text);
    }

    @MainThread
    public static void longShow(int id) {
        longRes(id);
    }

    @MainThread
    public static void longShow(CharSequence text) {
        longText(text);
    }

    // string 资源文件短提示
    @MainThread
    private static void shortRes(int id) {
        if (toast != null) {
            toast.cancel();
        }
        toast = Toast.makeText(ContextUtils.get(), id, Toast.LENGTH_SHORT);
        toast.show();
    }

    // 文本短提示
    @MainThread
    private static void shortText(CharSequence text) {
        if (toast != null) {
            toast.cancel();
        }
        toast = Toast.makeText(ContextUtils.get(), text, Toast.LENGTH_SHORT);
        toast.show();
    }

    // string 资源文件长提示
    @MainThread
    private static void longRes(int id) {
        if (toast != null) {
            toast.cancel();
        }
        toast = Toast.makeText(ContextUtils.get(), id, Toast.LENGTH_LONG);
        toast.show();
    }

    // 文本长提示
    @MainThread
    private static void longText(CharSequence text) {
        if (toast != null) {
            toast.cancel();
        }
        toast = Toast.makeText(ContextUtils.get(), text, Toast.LENGTH_LONG);
        toast.show();
    }

    // 自定义短提示布局
    @MainThread
    public static void shortView(View view) {
        if (toast != null) {
            toast.cancel();
        }
        toast = new Toast(ContextUtils.get());
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(view);
        toast.show();
    }

    //自定义长提示布局
    @MainThread
    public static void longView(View view) {
        if (toast != null) {
            toast.cancel();
        }
        toast = new Toast(ContextUtils.get());
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(view);
        toast.show();
    }

    // 自定义短提示居中布局
    @MainThread
    public static void shortViewCenter(Context context, View view) {
        if (toast != null) {
            toast.cancel();
        }
        if (context != null) {
            toast = new Toast(context);
            toast.setDuration(Toast.LENGTH_SHORT);
            toast.setView(view);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
        }
    }

    // 自定义长提示居中布局
    @MainThread
    public static void longViewCenter(View view) {
        if (toast != null) {
            toast.cancel();
        }
        toast = new Toast(ContextUtils.get());
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(view);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

}
