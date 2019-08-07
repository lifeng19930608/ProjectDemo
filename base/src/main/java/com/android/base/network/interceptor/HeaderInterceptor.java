package com.android.base.network.interceptor;

import android.annotation.SuppressLint;

import com.android.base.config.Key;
import com.android.base.utils.SharedPreferencesUtils;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

import static android.os.Build.SERIAL;

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
                .addHeader("onlyId", SERIAL)
                .build();

        Response response = chain.proceed(build);
        int code = response.code();
        handlerErrorCode(code);
        return response;
    }

    //处理服务器异常状态码
    private void handlerErrorCode(int code) {

    }


}
