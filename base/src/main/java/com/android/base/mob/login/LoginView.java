package com.android.base.mob.login;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.base.R;
import com.android.base.mob.listener.MobActionListener;
import com.android.base.utils.ToastUtils;

import java.util.HashMap;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.sina.weibo.SinaWeibo;
import cn.sharesdk.tencent.qq.QQ;
import cn.sharesdk.wechat.friends.Wechat;

/**
 * author  : 指尖的力量
 * date    : 2019-08-02 14:06
 * desc    : 三方登陆的自定义布局
 * modify  :
 * version : 1.0
 */

public class LoginView extends LinearLayout implements View.OnClickListener {

    private LoginManager loginManager;
    private MobActionListener mobActionListener;

    public LoginView(Context context) {
        super(context);
        init(context);
    }

    public LoginView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public LoginView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(final Context context) {
        addView(LayoutInflater.from(context).inflate(R.layout.layout_login, null), new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        TextView tv_login_sina_weibo = findViewById(R.id.tv_login_sina_weibo);
        TextView tv_login_wechat = findViewById(R.id.tv_login_wechat);
        TextView tv_login_qq = findViewById(R.id.tv_login_qq);
        tv_login_sina_weibo.setOnClickListener(this);
        tv_login_wechat.setOnClickListener(this);
        tv_login_qq.setOnClickListener(this);
        loginManager = new LoginManager();
        loginManager.setPlatformActionListener(new PlatformActionListener() {
            @Override
            public void onComplete(final Platform platform, final int i, final HashMap<String, Object> hashMap) {
                if (context != null) {
                    if (mobActionListener != null) {
                        mobActionListener.onComplete();
                    }
                    ToastUtils.shortShow(R.string.login_success);
                }
            }

            @Override
            public void onError(final Platform platform, final int i, final Throwable throwable) {
                if (context != null) {
                    if (mobActionListener != null) {
                        mobActionListener.onError();
                    }
                    if (throwable != null && throwable.getCause() == null) {//没有安装对应的客户端
                        ToastUtils.shortShow(R.string.login_error);
                    } else {
                        ToastUtils.shortShow(R.string.login_fail);
                    }
                }
            }

            @Override
            public void onCancel(final Platform platform, final int i) {
                if (context != null) {
                    if (mobActionListener != null) {
                        mobActionListener.onCancel();
                    }
                    ToastUtils.shortShow(R.string.login_cancel);
                }
            }
        });
    }

    public void setMobActionListener(MobActionListener mobActionListener) {
        this.mobActionListener = mobActionListener;
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.tv_login_sina_weibo) {
            loginManager.authorize(ShareSDK.getPlatform(SinaWeibo.NAME));
        } else if (view.getId() == R.id.tv_login_wechat) {
            loginManager.authorize(ShareSDK.getPlatform(Wechat.NAME));
        } else if (view.getId() == R.id.tv_login_qq) {
            loginManager.authorize(ShareSDK.getPlatform(QQ.NAME));
        }
    }

}
