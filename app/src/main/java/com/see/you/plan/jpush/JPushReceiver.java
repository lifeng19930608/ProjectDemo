package com.see.you.plan.jpush;

import android.app.ActivityManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;

import com.android.base.utils.LogUtils;

import java.util.List;

import cn.jpush.android.api.CmdMessage;
import cn.jpush.android.api.CustomMessage;
import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.JPushMessage;
import cn.jpush.android.api.NotificationMessage;
import cn.jpush.android.service.JPushMessageReceiver;

/**
 * author  : 指尖的力量
 * date    : 2019-08-09 15:12
 * desc    :
 * modify  :
 * version : 1.0
 */

public class JPushReceiver extends JPushMessageReceiver {

    private static final String TAG = "JPushReceiver";

    @Override
    public void onMessage(Context context, CustomMessage customMessage) {
        LogUtils.i(TAG, "[onMessage] " + customMessage);
    }

    @Override
    public void onNotifyMessageOpened(Context context, NotificationMessage message) {
        LogUtils.i(TAG, "[onNotifyMessageOpened] " + message);
        LogUtils.i(TAG, "isAppForeground  " + isAppForeground(context));
        if (!isAppForeground(context)) {
            PackageManager packageManager = context.getPackageManager();
            Intent intent = packageManager.getLaunchIntentForPackage("com.see.you.plan");
            Bundle bundle = new Bundle();
            bundle.putString(JPushInterface.EXTRA_NOTIFICATION_TITLE, message.notificationTitle);
            bundle.putString(JPushInterface.EXTRA_ALERT, message.notificationContent);
            if (intent != null) {
                intent.putExtras(bundle);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                context.startActivity(intent);
            }
        }
    }

    @Override
    public void onMultiActionClicked(Context context, Intent intent) {
        LogUtils.i(TAG, "[onMultiActionClicked] 用户点击了通知栏按钮");
    }

    @Override
    public void onNotifyMessageArrived(Context context, NotificationMessage message) {
        LogUtils.i(TAG, "[onNotifyMessageArrived] " + message);
    }

    @Override
    public void onNotifyMessageDismiss(Context context, NotificationMessage message) {
        LogUtils.i(TAG, "[onNotifyMessageDismiss] " + message);
    }

    @Override
    public void onRegister(Context context, String registrationId) {
        LogUtils.i(TAG, "[onRegister] " + registrationId);
    }

    @Override
    public void onConnected(Context context, boolean isConnected) {
        LogUtils.i(TAG, "[onConnected] " + isConnected);
    }

    @Override
    public void onCommandResult(Context context, CmdMessage cmdMessage) {
        LogUtils.i(TAG, "[onCommandResult] " + cmdMessage);
    }

    @Override
    public void onTagOperatorResult(Context context, JPushMessage jPushMessage) {
        super.onTagOperatorResult(context, jPushMessage);
    }

    @Override
    public void onCheckTagOperatorResult(Context context, JPushMessage jPushMessage) {
        super.onCheckTagOperatorResult(context, jPushMessage);
    }

    @Override
    public void onAliasOperatorResult(Context context, JPushMessage jPushMessage) {
        super.onAliasOperatorResult(context, jPushMessage);
    }

    @Override
    public void onMobileNumberOperatorResult(Context context, JPushMessage jPushMessage) {
        super.onMobileNumberOperatorResult(context, jPushMessage);
    }

    private boolean isAppForeground(Context context) {
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Service.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> runningAppProcessInfoList = null;
        if (activityManager != null) {
            runningAppProcessInfoList = activityManager.getRunningAppProcesses();
        }
        if (runningAppProcessInfoList == null) {
            LogUtils.d(TAG, "runningAppProcessInfoList is null!");
            return false;
        }
        for (ActivityManager.RunningAppProcessInfo processInfo : runningAppProcessInfoList) {
            if (processInfo.processName.equals(context.getPackageName())
                    && (processInfo.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND)) {
                return true;
            }
        }
        return false;
    }

}

