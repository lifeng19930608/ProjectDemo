package com.android.base.network.retrofit;

import com.android.base.network.ServerCode;
import com.google.gson.JsonSyntaxException;

import java.net.ConnectException;
import java.net.NoRouteToHostException;
import java.net.SocketTimeoutException;

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
            if (code == ServerCode.NETWORK_ERROR_502 || code == ServerCode.NETWORK_ERROR_404) {
                message = ServerCode.NETWORK_ERROR_1;
            } else if (code == ServerCode.NETWORK_ERROR_504) {
                message = ServerCode.NETWORK_ERROR_2;
            }
            onFailure(code, message);
        } else if (e instanceof NoRouteToHostException) {
            onFailure(ServerCode.N0_ROUTE_TO_HOST, ServerCode.NETWORK_ERROR_1);
        } else if (e instanceof ConnectException) {
            onFailure(ServerCode.CONNECT_ERROR, ServerCode.NETWORK_ERROR_2);
        } else if (e instanceof JsonSyntaxException) {
            onFailure(ServerCode.JSON_SYNTAX, ServerCode.NETWORK_ERROR_3);
        } else if (e instanceof SocketTimeoutException) {
            onFailure(ServerCode.CONNECT_TIMEOUT, ServerCode.NETWORK_ERROR_4);
        } else {
            onFailure(ServerCode.UNKNOWN_ERROR, ServerCode.NETWORK_ERROR_5);
        }
        onFinish();
    }

    @Override
    public void onComplete() {
        onFinish();
    }

}
