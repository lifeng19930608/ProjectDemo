package com.android.base.mvp;

import android.view.View;

import com.android.base.base.BaseActivity;
import com.android.base.base.BaseComponent;

/**
 * author  : 指尖的力量
 * date    : 2019-08-08 15:12
 * desc    :
 * modify  :
 * version : 1.0
 */

public abstract class MvpComponent<P extends BasePresenter> extends BaseComponent {

    protected P presenter;

    public MvpComponent(BaseActivity activity, View rootView) {
        super(activity, rootView);
        presenter = createPresenter();
    }

    protected abstract P createPresenter();

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }

    // 布局的初始化
    protected abstract void initView();

    // 数据的初始化
    protected abstract void initData();

    // 控件的监听
    protected abstract void setListener();
}
