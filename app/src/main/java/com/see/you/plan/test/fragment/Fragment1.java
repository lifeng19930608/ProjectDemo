package com.see.you.plan.test.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.android.base.mvp.BaseModel;
import com.android.base.mvp.MvpFragment;
import com.android.base.utils.LogUtils;
import com.android.base.utils.ToastUtils;
import com.see.you.plan.R;

/**
 * author  : 指尖的力量
 * date    : 2019-08-08 15:19
 * desc    :
 * modify  :
 * version : 1.0
 */

public class Fragment1 extends MvpFragment<FragPresenter> implements FragView {

    private Button load;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        initData();
        setListener();
    }

    @Override
    public void showLoading() {
        LogUtils.i("===========", "showLoading");
    }

    @Override
    public void hideLoading() {
        LogUtils.i("===========", "hideLoading");
    }

    @Override
    protected FragPresenter createPresenter() {
        return new FragPresenter(this);
    }

    @Override
    protected void initView() {
        load = findViewById(R.id.load);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void setListener() {
        load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.version("10.0.0", 2);
            }
        });
    }

    @Override
    public void getDataSuccess(BaseModel model) {
        LogUtils.i("===========", model.status + "");
    }

    @Override
    public void getDataFail(String msg) {
        ToastUtils.shortShow(msg);
        LogUtils.i("===========", msg);
    }
}
