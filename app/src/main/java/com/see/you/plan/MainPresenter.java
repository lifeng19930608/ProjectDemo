package com.see.you.plan;

import com.android.base.base.BaseModel;
import com.android.base.base.BasePresenter;
import com.android.base.moudle.NewVersionBean;
import com.android.base.network.retrofit.ApiCallBack;

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

//    public void loadData(String id) {
//        view.showLoading();
//        onSubscribe(urlApi.loadData(id), new ApiCallBack<BaseModel<MainModel>>() {
//
//            @Override
//            public void onSuccess(BaseModel<MainModel> model) {
//                view.getDataSuccess(model.data);
//            }
//
//            @Override
//            public void onFailure(int code, String message) {
//                view.getDataFail(message);
//            }
//
//            @Override
//            public void onFinish() {
//                view.hideLoading();
//            }
//        });
//    }

    public void version(String version,int pla){
        view.showLoading();
        onSubscribe(urlApi.checkUpdate(version, pla), new ApiCallBack<BaseModel<NewVersionBean>>() {
            @Override
            public void onSuccess(BaseModel<NewVersionBean> model) {
                view.getDataSuccess(model);
            }

            @Override
            public void onFailure(int code, String message) {
                view.getDataFail(message);
            }

            @Override
            public void onFinish() {
                view.hideLoading();
            }
        });
    }

}
