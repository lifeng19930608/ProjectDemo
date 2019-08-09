package com.android.base.base;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import androidx.annotation.CallSuper;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.base.load.LoadingDialog;
import com.android.base.utils.LogUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.lang.ref.SoftReference;

/**
 * author  : 指尖的力量
 * date    : 2019-08-06 17:14
 * desc    : Activity 基类
 * modify  :
 * version : 1.0
 */

public abstract class BaseActivity extends AppCompatActivity {

    private MainHandler mainHandler;
    private boolean alive;
    private boolean foreground;
    private LoadingDialog loadingDialog;
    private int loadNum;//网络请求数量

    @CallSuper
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        alive = true;
        EventBus.getDefault().register(this);
    }

    @CallSuper
    @Override
    protected void onResume() {
        super.onResume();
        foreground = true;
        getActivityTaskInfo();
    }

    @CallSuper
    @Override
    protected void onPause() {
        super.onPause();
        foreground = false;
    }

    @CallSuper
    @Override
    protected void onDestroy() {
        super.onDestroy();
        alive = false;
        if (mainHandler != null) {
            mainHandler.removeCallbacksAndMessages(null);
            mainHandler = null;
        }
        EventBus.getDefault().unregister(this);
    }

    public boolean isAlive() {
        return alive;
    }

    public boolean isForeground() {
        return foreground;
    }

    public void handMainMessage(Message message) {

    }

    public BaseActivity getActivity() {
        return this;
    }

    public final MainHandler getMainHandler() {
        if (mainHandler == null || mainHandler.reference.get() == null) {
            mainHandler = new MainHandler(this, Looper.getMainLooper());
        }
        return mainHandler;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(BaseEvent event) {

    }

    // 避免handler的使用造成内存溢出
    public static final class MainHandler extends Handler {
        private final SoftReference<BaseActivity> reference;

        private MainHandler(BaseActivity activity, Looper looper) {
            super(looper);
            reference = new SoftReference<>(activity);
        }

        @Override
        public void handleMessage(Message message) {
            BaseActivity activity = reference.get();
            if (activity != null && message != null && activity.isAlive()) {
                activity.handMainMessage(message);
            }
        }
    }

    protected void getActivityTaskInfo() {
        ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        ActivityManager.RunningTaskInfo runningTaskInfo = null;
        if (manager != null) {
            runningTaskInfo = manager.getRunningTasks(1).get(0);
        }
        //栈内activity数量
        int numActivities = 0;
        //taskId
        int id = 0;
        ComponentName topActivity = null;
        if (runningTaskInfo != null) {
            numActivities = runningTaskInfo.numActivities;
            id = runningTaskInfo.id;
            topActivity = runningTaskInfo.topActivity;
        }
        //栈顶activity信息
        String className = null;
        if (topActivity != null) {
            className = topActivity.getClassName();
        }
        LogUtils.e("activityTask", "id == " + id + "\n" + "numActivity == " + numActivities + "\n" + "className == " + className);
    }

    public void showProgressDialog() {
        loadNum++;
        //第一个加载动画执行，后续的网络接口不做任何操作
        if (loadingDialog == null) {
            loadingDialog = new LoadingDialog(getActivity());
            loadingDialog.setCancelable(false);
            loadingDialog.show();
        }
    }

    public void dismissProgressDialog() {
        loadNum--;
        if (loadingDialog != null && loadNum == 0) {//此时代表最后一个网络请求已经完成
            loadingDialog.dismiss();
            loadingDialog = null;
        }
    }

}
