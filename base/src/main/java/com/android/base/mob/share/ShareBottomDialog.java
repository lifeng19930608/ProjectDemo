package com.android.base.mob.share;

import android.view.View;
import android.widget.TextView;

import com.android.base.R;
import com.android.base.base.BaseActivity;
import com.android.base.base.BaseBottomDialog;
import com.android.base.mob.listener.MobActionListener;
import com.android.base.utils.ToastUtils;

import java.util.HashMap;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;

/**
 * author  : 指尖的力量
 * date    : 2019-08-02 12:22
 * desc    : 底部分享弹窗
 * modify  :
 * version : 1.0
 */

public class ShareBottomDialog extends BaseBottomDialog implements View.OnClickListener {

    private BaseActivity activity;
    private ShareManager shareManager;
    private MobActionListener mobActionListener;

    private String title = "";//分享的标题
    private String text = "";//分享的内容
    private String url = "";//分享的链接
    private String imageUrl = "";//分享的头像

    public ShareBottomDialog(BaseActivity activity) {
        super(activity);
        this.activity = activity;
    }

    @Override
    public void init() {
        TextView tv_share_sina_weibo = findViewById(R.id.tv_share_sina_weibo);
        TextView tv_share_wechat = findViewById(R.id.tv_share_wechat);
        TextView tv_share_wechat_moments = findViewById(R.id.tv_share_wechat_moments);
        TextView tv_share_qq = findViewById(R.id.tv_share_qq);
        TextView tv_share_qzone = findViewById(R.id.tv_share_qzone);
        TextView tv_share_copy = findViewById(R.id.tv_share_copy);
        tv_share_sina_weibo.setOnClickListener(this);
        tv_share_wechat.setOnClickListener(this);
        tv_share_wechat_moments.setOnClickListener(this);
        tv_share_qq.setOnClickListener(this);
        tv_share_qzone.setOnClickListener(this);
        tv_share_copy.setOnClickListener(this);
        shareManager = new ShareManager();
        shareManager.setPlatformActionListener(new PlatformActionListener() {
            @Override
            public void onComplete(final Platform platform, final int i, final HashMap<String, Object> hashMap) {
                if (activity != null) {
                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (mobActionListener != null) {
                                mobActionListener.onComplete();
                            }
                        }
                    });
                    ToastUtils.shortShow(R.string.share_success);
                }
            }

            @Override
            public void onError(final Platform platform, final int i, final Throwable throwable) {
                if (activity != null) {
                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (mobActionListener != null) {
                                mobActionListener.onError();
                            }
                        }
                    });
                    if (throwable != null && throwable.getCause() == null) {//没有安装对应的客户端
                        ToastUtils.shortShow(R.string.share_error);
                    } else {
                        ToastUtils.shortShow(R.string.share_fail);
                    }
                }
            }

            @Override
            public void onCancel(final Platform platform, final int i) {
                if (activity != null) {
                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (mobActionListener != null) {
                                mobActionListener.onCancel();
                            }
                        }
                    });
                    ToastUtils.shortShow(R.string.share_cancel);
                }
            }
        });
    }

    @Override
    public int getLayoutId() {
        return R.layout.dialog_share;
    }

    public void setMobActionListener(MobActionListener mobActionListener) {
        this.mobActionListener = mobActionListener;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.tv_share_sina_weibo) {
            shareManager.sinaWeiboShare(title, text, url, imageUrl);
        } else if (view.getId() == R.id.tv_share_wechat) {
            shareManager.wechatShare(title, text, url, imageUrl);
        } else if (view.getId() == R.id.tv_share_wechat_moments) {
            shareManager.wechatMomentsShare(title, text, url, imageUrl);
        } else if (view.getId() == R.id.tv_share_qq) {
            shareManager.qqShare(title, text, url, imageUrl);
        } else if (view.getId() == R.id.tv_share_qzone) {
            shareManager.qZoneShare(title, text, url, imageUrl);
        } else if (view.getId() == R.id.tv_share_copy) {
            if (activity != null) {
                shareManager.copyShare(activity, url);
                ToastUtils.shortShow(R.string.copy);
            }
        }
        dismiss();
    }
}
