package com.android.base.base;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;

import androidx.annotation.CallSuper;

import com.android.base.utils.LogUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.lang.ref.SoftReference;

/**
 * author  : 指尖的力量
 * date    : 2019-08-08 14:37
 * desc    :
 * modify  :
 * version : 1.0
 */

public class BaseComponent implements BaseLifeCycle {

    private MainHandler mainHandler;
    private BaseActivity activity;
    private View rootView;
    private boolean alive;
    private boolean showing;

    public BaseComponent(BaseActivity activity, View rootView) {
        this.activity = activity;
        this.rootView = rootView;
    }

    // 初始化(界面不可见) 在销毁之前只能执行一次
    @CallSuper
    @Override
    public void onInit() {
        if (alive) {
            return;
        }
        EventBus.getDefault().register(this);
        alive = true;
        LogUtils.d(getClass().getSimpleName() + " " + hashCode() + "  onInit");
    }

    // 开始显示
    @CallSuper
    @Override
    public void onShow() {
        showing = true;
        LogUtils.d(getClass().getSimpleName() + " " + hashCode() + "  onShow");
    }

    // 停止显示隐藏
    @CallSuper
    @Override
    public void onHide() {
        showing = false;
        LogUtils.d(getClass().getSimpleName() + " " + hashCode() + "  onHide");
    }

    //销毁
    @CallSuper
    @Override
    public void onDestroy() {
        alive = false;
        EventBus.getDefault().unregister(this);
        if (mainHandler != null) {
            mainHandler.removeCallbacksAndMessages(null);
            mainHandler = null;
        }
        activity = null;
        LogUtils.d(getClass().getSimpleName() + " " + hashCode() + "  onDestroy");
    }

    @Override
    public final boolean isShowing() {
        return showing;
    }

    @Override
    public final boolean isAlive() {
        return activity != null && alive;
    }

    public final BaseActivity getActivity() {
        return activity;
    }

    public final View getRootView() {
        return rootView;
    }

    public final <T extends View> T findViewById(int id) {
        return rootView.findViewById(id);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(BaseEvent event) {

    }

    public void handleMainMessage(Message message) {

    }

    public final MainHandler getMainHandler() {
        if (mainHandler == null || mainHandler.weakReference.get() == null) {
            mainHandler = new MainHandler(this, Looper.getMainLooper());
        }
        return mainHandler;
    }

    public static final class MainHandler extends Handler {

        private final SoftReference<BaseComponent> weakReference;

        private MainHandler(BaseComponent baseComponent, Looper looper) {
            super(looper);
            weakReference = new SoftReference<>(baseComponent);
        }

        @Override
        public void handleMessage(Message msg) {
            BaseComponent baseComponent = weakReference.get();
            if (baseComponent != null && msg != null && baseComponent.isAlive()) {
                baseComponent.handleMainMessage(msg);
            }
        }
    }

}
