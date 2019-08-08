package com.android.base.mvp;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.android.base.base.BaseFragment;

/**
 * author  : 指尖的力量
 * date    : 2019-08-08 14:30
 * desc    :
 * modify  :
 * version : 1.0
 */

public abstract class MvpFragment<P extends BasePresenter> extends BaseFragment {

    protected P presenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = createPresenter();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (presenter != null) {
            presenter.detachView();
        }
    }

    protected abstract P createPresenter();

    // 布局的初始化
    protected abstract void initView();

    // 数据的初始化
    protected abstract void initData();

    // 控件的监听
    protected abstract void setListener();

}
