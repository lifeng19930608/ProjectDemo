package com.android.base.utils;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;

/**
 * author  : 指尖的力量
 * date    : 2019-08-08 16:08
 * desc    : 控制键盘的工具类
 * modify  :
 * version : 1.0
 */

public class KeyBoardUtils {

    /**
     * 开启软键盘
     */
    public static void openSoftKeyboard(Context context) {
        if (context != null) {
            InputMethodManager inputManager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
            if (inputManager != null) {
                inputManager.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
            }
        }
    }

    /**
     * 关闭软键盘
     */
    public static void closeSoftKeyboard(Context context) {
        if (context != null) {
            InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
            if (inputMethodManager != null && ((Activity) context).getCurrentFocus() != null) {
                inputMethodManager.hideSoftInputFromWindow(((Activity) context).getCurrentFocus()
                        .getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            }
        }
    }

    /**
     * 关闭软键盘 dialog里面弹出的键盘
     */
    public static void closeKeyboardDialog(Context context, Window window) {
        if (context != null && window != null) {
            View view = window.peekDecorView();//注意：这里要根据EditText位置来获取
            if (view != null) {
                InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
                if (inputMethodManager != null) {
                    inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
                }
            }
        }
    }

}
