package com.android.base.network.retrofit;

import com.android.base.config.HttpProtocol;
import com.android.base.moudle.LoveLedgerDataBean;
import com.android.base.mvp.BaseModel;
import com.android.base.moudle.NewVersionBean;

import java.util.HashMap;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * author  : 指尖的力量
 * date    : 2019-08-06 17:44
 * desc    : 网络请求接口
 * modify  :
 * version : 1.0
 */

public interface UrlApi {

    String BASE_URL = HttpProtocol.Domain.getBaseUrl();
    String BASE_URL_H5 = HttpProtocol.Domain.getBaseH5Url();

    @POST("VersionNo/findNewVersion")
    Observable<BaseModel<NewVersionBean>> checkUpdate(@Query("version") String version, @Query("platform") int platform);

    @GET("smsCodes")
    Observable<BaseModel<Object>> sendVerifyCode(@Query("countryMode") int countryMode, @Query("mobile") String mobile, @Query("type") int type);

    /**
     * 我的爱心列表——ok
     */
    @GET("loveLedger/list")
    Observable<BaseModel<LoveLedgerDataBean>> queryLoveLedger(@QueryMap HashMap<String, Object> params);


}
