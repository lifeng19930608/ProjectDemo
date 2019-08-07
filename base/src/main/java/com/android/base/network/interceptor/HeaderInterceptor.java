package com.android.base.network.interceptor;

import android.annotation.SuppressLint;
import android.provider.Settings;

import com.android.base.config.Key;
import com.android.base.utils.ContextUtils;
import com.android.base.utils.SharedPreferencesUtils;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * author  : 指尖的力量
 * date    : 2019-08-07 15:09
 * desc    :
 * modify  :
 * version : 1.0
 */

public class HeaderInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {

        Request request = chain.request();
        String token = SharedPreferencesUtils.getString(Key.PrefKey.TOKEN);

        @SuppressLint("HardwareIds") Request build = request.newBuilder()
                .addHeader("Content-Type", "application/json; charset=UTF-8")
                .addHeader("Authorization", token)
                .addHeader("version", "3.6.1")
                .addHeader("platform", "1")
                .addHeader("onlyId", Settings.System.getString(ContextUtils.get().getContentResolver(), Settings.System.ANDROID_ID))
                .build();

        Response response = chain.proceed(build);
        //这里可以统一处理服务端的错误码以及数据的加密解密操作
        int code = response.code();
        handlerErrorCode(code);
        return response;
    }

    //处理服务器异常状态码
    private void handlerErrorCode(int code) {

    }


}
