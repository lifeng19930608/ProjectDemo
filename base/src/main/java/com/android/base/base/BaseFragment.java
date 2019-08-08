package com.android.base.base;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;

import androidx.annotation.CallSuper;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.base.utils.LogUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.lang.ref.SoftReference;

/**
 * author  : 指尖的力量
 * date    : 2019-08-08 14:18
 * desc    : Fragment 基类
 * modify  :
 * version : 1.0
 */

public class BaseFragment extends Fragment {

    private MainHandler mainHandler;
    private String fragmentName = getClass().getSimpleName();

    @CallSuper
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtils.d(fragmentName + " " + hashCode() + " onCreate");
        EventBus.getDefault().register(this);
    }

    @CallSuper
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        LogUtils.d(fragmentName + " " + hashCode() + " onActivityCreated");
    }

    @CallSuper
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        LogUtils.d(fragmentName + " " + hashCode() + " onDestroyView");
    }

    @CallSuper
    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        if (mainHandler != null) {
            mainHandler.removeCallbacksAndMessages(null);
            mainHandler = null;
        }
        LogUtils.d(fragmentName + " " + hashCode() + " onDestroy");
    }

    public final <T extends View> T findViewById(int id) {
        View view = getView();
        if (view != null) {
            return view.findViewById(id);
        }
        return null;
    }

    public boolean isAlive() {
        return isAdded() && !isDetached();
    }

    public void handleMainMessage(Message message) {

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(BaseEvent event) {
    }

    public MainHandler getMainHandler() {
        if (mainHandler == null || mainHandler.weakReference.get() == null) {
            mainHandler = new MainHandler(this, Looper.getMainLooper());
        }
        return mainHandler;
    }

    private static final class MainHandler extends Handler {
        private final SoftReference<BaseFragment> weakReference;

        private MainHandler(BaseFragment baseFragment, Looper looper) {
            super(looper);
            weakReference = new SoftReference<>(baseFragment);
        }

        @Override
        public void handleMessage(Message msg) {
            BaseFragment baseFragment = weakReference.get();
            if (baseFragment != null && msg != null && baseFragment.isAlive()) {
                baseFragment.handleMainMessage(msg);
            }
        }
    }
}
