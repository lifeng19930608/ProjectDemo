package com.android.base.mob.share;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import java.util.HashMap;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.sina.weibo.SinaWeibo;
import cn.sharesdk.tencent.qq.QQ;
import cn.sharesdk.tencent.qzone.QZone;
import cn.sharesdk.wechat.friends.Wechat;
import cn.sharesdk.wechat.moments.WechatMoments;

/**
 * author  : 指尖的力量
 * date    : 2019-08-02 11:38
 * desc    : 分享管理器 (默认图文链接分享)
 * modify  :
 * version : 1.0
 */

public class ShareManager implements PlatformActionListener {

    private Handler handler = new Handler(Looper.getMainLooper());
    private PlatformActionListener platformActionListener;

    public void setPlatformActionListener(PlatformActionListener platformActionListener) {
        this.platformActionListener = platformActionListener;
    }

    //新浪微博分享
    public void sinaWeiboShare(String title, String text, String url, String imageUrl) {
        Platform.ShareParams shareParams = new Platform.ShareParams();
        shareParams.setTitle(title);
        shareParams.setText(text);
        shareParams.setUrl(url); // 标题的超链接
        shareParams.setImageUrl(imageUrl);
        shareParams.setShareType(Platform.SHARE_WEBPAGE);
        Platform sinaWeibo = ShareSDK.getPlatform(SinaWeibo.NAME);
        sinaWeibo.setPlatformActionListener(this);
        sinaWeibo.share(shareParams);
    }

    //微信好友分享
    public void wechatShare(String title, String text, String url, String imageUrl) {
        Platform.ShareParams shareParams = new Platform.ShareParams();
        shareParams.setTitle(title);
        shareParams.setText(text);
        shareParams.setUrl(url); // 标题的超链接
        shareParams.setImageUrl(imageUrl);
        shareParams.setShareType(Platform.SHARE_WEBPAGE);
        Platform wechat = ShareSDK.getPlatform(Wechat.NAME);
        wechat.setPlatformActionListener(this);
        wechat.share(shareParams);
    }

    //微信朋友圈分享
    public void wechatMomentsShare(String title, String text, String url, String imageUrl) {
        Platform.ShareParams shareParams = new Platform.ShareParams();
        shareParams.setTitle(title);
        shareParams.setText(text);
        shareParams.setUrl(url); // 标题的超链接
        shareParams.setImageUrl(imageUrl);
        shareParams.setShareType(Platform.SHARE_WEBPAGE);
        Platform wechatMoments = ShareSDK.getPlatform(WechatMoments.NAME);
        wechatMoments.setPlatformActionListener(this);
        wechatMoments.share(shareParams);
    }

    //QQ好友分享
    public void qqShare(String title, String text, String url, String imageUrl) {
        Platform.ShareParams shareParams = new Platform.ShareParams();
        shareParams.setTitle(title);
        shareParams.setText(text);
        shareParams.setUrl(url); // 标题的超链接
        shareParams.setImageUrl(imageUrl);
        shareParams.setShareType(Platform.SHARE_WEBPAGE);
        Platform qq = ShareSDK.getPlatform(QQ.NAME);
        qq.setPlatformActionListener(this);
        qq.share(shareParams);
    }

    //QQ空间分享
    public void qZoneShare(String title, String text, String url, String imageUrl) {
        Platform.ShareParams shareParams = new Platform.ShareParams();
        shareParams.setTitle(title);
        shareParams.setText(text);
        shareParams.setUrl(url); // 标题的超链接
        shareParams.setImageUrl(imageUrl);
        shareParams.setShareType(Platform.SHARE_WEBPAGE);
        Platform qZone = ShareSDK.getPlatform(QZone.NAME);
        qZone.setPlatformActionListener(this);
        qZone.share(shareParams);
    }

    //复制链接分享
    public void copyShare(Context context, String url) {
        if (context == null) {
            return;
        }
        //获取剪贴板管理器：
        ClipboardManager cm = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        // 创建普通字符型ClipData
        ClipData mClipData = ClipData.newPlainText("Label", url);
        // 将ClipData内容放到系统剪贴板里。
        if (cm != null) {
            cm.setPrimaryClip(mClipData);
        }
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
