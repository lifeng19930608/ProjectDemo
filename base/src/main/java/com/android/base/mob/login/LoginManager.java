package com.android.base.mob.login;

import android.os.Handler;
import android.os.Looper;

import java.util.HashMap;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;

/**
 * author  : 指尖的力量
 * date    : 2019-08-02 13:09
 * desc    : 三方登陆管理器
 * modify  :
 * version : 1.0
 */

public class LoginManager implements PlatformActionListener {

    private Handler handler = new Handler(Looper.getMainLooper());
    private PlatformActionListener platformActionListener;

    public void authorize(Platform platform) {
        if (platform == null) {
            return;
        }
        if (platform.isAuthValid()) {
            //删除授权
            platform.removeAccount(true);
        }
        platform.setPlatformActionListener(platformActionListener);
        //设置false表示使用SSO授权方式
        platform.SSOSetting(false);
        platform.showUser(null);
        platform.authorize();
    }

    public void setPlatformActionListener(PlatformActionListener platformActionListener) {
        this.platformActionListener = platformActionListener;
    }

    @Override
    public void onComplete(final Platform platform, final int i, final HashMap<String, Object> hashMap) {
        if (platformActionListener != null) {
            handler.post(new Runnable() {
                @Override
                public void run() {
                    platformActionListener.onComplete(platform, i, hashMap);
                }
            });
        }
    }

    @Override
    public void onError(final Platform platform, final int i, final Throwable throwable) {
        if (platformActionListener != null)
            handler.post(new Runnable() {
                @Override
                public void run() {
                    platformActionListener.onError(platform, i, throwable);
                }
            });
    }

    @Override
    public void onCancel(final Platform platform, final int i) {
        if (platformActionListener != null)
            handler.post(new Runnable() {
                @Override
                public void run() {
                    platformActionListener.onCancel(platform, i);
                }
            });
    }
}
