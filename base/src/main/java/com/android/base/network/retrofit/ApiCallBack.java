package com.android.base.network.retrofit;

import com.android.base.config.ServerCode;

import io.reactivex.observers.DisposableObserver;
import retrofit2.HttpException;

/**
 * author  : 指尖的力量
 * date    : 2019-08-06 18:29
 * desc    : 封装观察者的回调方法
 * modify  :
 * version : 1.0
 */

public abstract class ApiCallBack<M> extends DisposableObserver<M> {

    public abstract void onSuccess(M model);

    public abstract void onFailure(int code, String message);

    public abstract void onFinish();

    @Override
    public void onNext(M m) {
        onSuccess(m);
    }

    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
        if (e instanceof HttpException) {
            HttpException httpException = (HttpException) e;
            int code = httpException.code();
            String message = httpException.message();
            if (code == 502 || code == 404) {
                message = "服务器异常，请稍后重试";
            } else if (code == 504) {
                message = "网络不给力";
            }
            onFailure(code, message);
        } else {
            onFailure(ServerCode.UNKNOWN_ERROR, "未知错误");
        }
        //ConnectException
        onFinish();

    }

    @Override
    public void onComplete() {
        onFinish();
    }

}
