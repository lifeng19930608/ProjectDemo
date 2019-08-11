package com.android.base.network.interceptor;

import com.android.base.utils.ContextUtils;
import com.android.base.utils.NetworkUtils;

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

public class OfflineCacheInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        if (!NetworkUtils.isNetConnected(ContextUtils.get())) {
            int offlineCacheTime = 60;//离线的时候的缓存的过期时间
            request = request.newBuilder()
                    .header("Cache-Control", "public, only-if-cached, max-stale=" + offlineCacheTime)
                    .build();
        }
        return chain.proceed(request);
    }

}
