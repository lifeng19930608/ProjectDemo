package com.android.base.network.retrofit;

import com.android.base.mvp.BaseModel;
import com.android.base.moudle.NewVersionBean;

import io.reactivex.Observable;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * author  : 指尖的力量
 * date    : 2019-08-06 17:44
 * desc    : 网络请求接口
 * modify  :
 * version : 1.0
 */

public interface UrlApi {

    String BASE_URL = "http://112.74.179.118:8088/missyou_app_api/";
    String BASE_URL_H5 = "";

    @POST("VersionNo/findNewVersion")
    Observable<BaseModel<NewVersionBean>> checkUpdate(@Query("version") String version, @Query("platform") int platform);

}
