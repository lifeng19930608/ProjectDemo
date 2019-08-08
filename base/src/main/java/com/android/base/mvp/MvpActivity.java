package com.android.base.mvp;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.android.base.base.BaseActivity;

/**
 * author  : 指尖的力量
 * date    : 2019-08-07 13:54
 * desc    :
 * modify  :
 * version : 1.0
 */

public abstract class MvpActivity<P extends BasePresenter> extends BaseActivity {

    protected P presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = createPresenter();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (presenter != null) {
            presenter.detachView();
        }
    }

    protected abstract P createPresenter();

    public void showLoading() {
        showProgressDialog();
    }

    public void hideLoading() {
        dismissProgressDialog();
    }

    // 布局的初始化
    protected abstract void initView();

    // 数据的初始化
    protected abstract void initData();

    // 控件的监听
    protected abstract void setListener();
}
