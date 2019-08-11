package com.android.base.config;

/**
 * author  : 指尖的力量
 * date    : 2019-08-11 21:28
 * desc    :
 * modify  :
 * version : 1.0
 */

public class HttpProtocol {

    public static class Domain {
        //正式环境
        private static final String BASE_URL_PRODUCT = "https://app.seeyouplan.com/missyou_app_api/";
        private static final String BASE_H5_URL_PRODUCT = "https://share.seeyouplan.com/shareWeb3.0/#/";
        //测试环境
        private static final String BASE_URL_TEST = "http://112.74.179.118:8088/missyou_app_api/";
        private static final String BASE_H5_URL_TEST = "http://112.74.179.118:8090/shareWeb3.0/#/";
        //开发环境
        private static final String BASE_URL_DEVELOP = "http://192.168.51.141:8088/missyou_app_api/";
        private static final String BASE_H5_URL_DEVELOP = "http://192.168.51.141:8088/shareWeb3.0/#/";

        public static String getBaseUrl() {
            switch (Environment.getEnv()) {
                case Environment.ENV_PRODUCT:
                    return Domain.BASE_URL_PRODUCT;
                case Environment.ENV_TEST:
                    return Domain.BASE_URL_TEST;
                case Environment.ENV_DEVELOP:
                    return Domain.BASE_URL_DEVELOP;
            }
            return Domain.BASE_URL_PRODUCT;
        }

        //获取H5的根路径
        public static String getBaseH5Url() {
            switch (Environment.getEnv()) {
                case Environment.ENV_PRODUCT:
                    return Domain.BASE_H5_URL_PRODUCT;
                case Environment.ENV_TEST:
                    return Domain.BASE_H5_URL_TEST;
                case Environment.ENV_DEVELOP:
                    return Domain.BASE_H5_URL_DEVELOP;
            }
            return Domain.BASE_H5_URL_PRODUCT;
        }
    }

}
