package com.see.you.plan.test;

import com.android.base.moudle.LoveLedgerDataBean;
import com.android.base.mvp.BaseModel;
import com.android.base.mvp.BasePresenter;
import com.android.base.moudle.NewVersionBean;
import com.android.base.network.retrofit.ApiCallBack;

import java.util.HashMap;

/**
 * author  : 指尖的力量
 * date    : 2019-08-07 13:22
 * desc    :
 * modify  :
 * version : 1.0
 */

public class MainPresenter extends BasePresenter<MainView> {

    public MainPresenter(MainView mainView) {
        attachView(mainView);
    }

    public void version(String version, int pla) {
        view.showLoading();
        onSubscribe(urlApi.checkUpdate(version, pla), new ApiCallBack<BaseModel<NewVersionBean>>() {
            @Override
            public void onSuccess(BaseModel<NewVersionBean> model) {
                view.getDataSuccess(model, model.data);
            }

            @Override
            public void onFailure(int code, String message) {
                view.getDataFail(code + "   " + message);
            }

            @Override
            public void onFinish() {
                view.hideLoading();
            }
        });
    }

    public void code(int area, String phone, int type) {
        view.showLoading();
        onSubscribe(urlApi.sendVerifyCode(area, phone, type), new ApiCallBack<BaseModel<Object>>() {
            @Override
            public void onSuccess(BaseModel<Object> model) {
                view.getDataSuccess(model, model.data);
            }

            @Override
            public void onFailure(int code, String message) {
                view.getDataFail(code + "   " + message);
            }

            @Override
            public void onFinish() {
                view.hideLoading();
            }
        });
    }

    public void getList(HashMap<String, Object> hashMap) {
        view.showLoading();
        onSubscribe(urlApi.queryLoveLedger(hashMap), new ApiCallBack<BaseModel<LoveLedgerDataBean>>() {
            @Override
            public void onSuccess(BaseModel<LoveLedgerDataBean> model) {
                view.getDataSuccess(model, model.data);
            }

            @Override
            public void onFailure(int code, String message) {
                view.getDataFail(code + "   " + message);
            }

            @Override
            public void onFinish() {
                view.hideLoading();
            }
        });
    }

}
