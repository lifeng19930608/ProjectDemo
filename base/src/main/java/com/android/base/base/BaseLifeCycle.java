package com.android.base.base;

/**
 * author  : 指尖的力量
 * date    : 2019-08-08 14:38
 * desc    : 界面分离组件的生命状态
 * modify  :
 * version : 1.0
 */

public interface BaseLifeCycle {

    void onInit();

    void onShow();

    void onHide();

    void onDestroy();

    boolean isShowing();

    boolean isAlive();
}
