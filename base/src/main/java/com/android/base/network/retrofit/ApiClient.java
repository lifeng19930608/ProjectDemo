package com.android.base.network.retrofit;

import android.os.Environment;

import com.android.base.network.interceptor.HeaderInterceptor;
import com.android.base.network.interceptor.NetCacheInterceptor;
import com.android.base.network.interceptor.OfflineCacheInterceptor;
import com.android.base.utils.LogUtils;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * author  : 指尖的力量
 * date    : 2019-08-06 18:03
 * desc    :
 * modify  :
 * version : 1.0
 */

public class ApiClient {

    private static volatile Retrofit retrofit;

    public static Retrofit retrofit() {
        if (retrofit == null) {
            synchronized (ApiClient.class) {
                if (retrofit == null) {
                    OkHttpClient.Builder builder = new OkHttpClient.Builder()
                            .connectTimeout(15, TimeUnit.SECONDS)
                            .readTimeout(30, TimeUnit.SECONDS)
                            .writeTimeout(30, TimeUnit.SECONDS)
                            .addInterceptor(new HeaderInterceptor())//头部信息
                            .cache(new Cache(new File(Environment.getExternalStorageState(), "cache"), 1024 * 1024 * 24))
                            .addNetworkInterceptor(new NetCacheInterceptor())//缓存拦截器
                            .addInterceptor(new OfflineCacheInterceptor());

                    if (LogUtils.DEBUG) {
                        //Log 信息拦截器
                        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
                        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
                        builder.addInterceptor(loggingInterceptor);
                    }
                    OkHttpClient okHttpClient = builder.build();
                    retrofit = new Retrofit.Builder()
                            .baseUrl(UrlApi.BASE_URL)
                            .addConverterFactory(GsonConverterFactory.create())
                            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                            .client(okHttpClient)
                            .build();
                }
            }
        }
        return retrofit;
    }

}
