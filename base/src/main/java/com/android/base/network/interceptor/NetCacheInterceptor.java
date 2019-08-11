package com.android.base.network.interceptor;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * author  : 指尖的力量
 * date    : 2019-08-11 13:14
 * desc    :
 * modify  :
 * version : 1.0
 */

public class NetCacheInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Response response = chain.proceed(request);
        int onlineCacheTime = 30;//在线的时候的缓存过期时间，如果想要不缓存，直接时间设置为0
        return response.newBuilder()
                .removeHeader("Pragma")
                .header("Cache-Control", "public, max-age=" + onlineCacheTime)
                .build();
    }
}
