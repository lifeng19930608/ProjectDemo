package com.android.base.network;

/**
 * author  : 指尖的力量
 * date    : 2019-08-06 18:38
 * desc    : 网络请求返回的状态码
 * modify  :
 * version : 1.0
 */

public class ServerCode {

    public static final int CODE_SUCCESS = 200;//正确的返回值
    public static final int NETWORK_ERROR_404 = 404;//服务器错误
    public static final int NETWORK_ERROR_502 = 502;//服务器错误
    public static final int NETWORK_ERROR_504 = 504;//弱网环境
    public static final int N0_ROUTE_TO_HOST = -1;//服务器错误
    public static final int CONNECT_ERROR = -2;//没有网络
    public static final int JSON_SYNTAX = -3;//json解析失败
    public static final int CONNECT_TIMEOUT = -4;//连接超时
    public static final int UNKNOWN_ERROR = -5;//未知网络异常，后期可以扩充

    public static final String NETWORK_ERROR_1 = "服务器异常,请稍后再试";
    public static final String NETWORK_ERROR_2 = "请检查您的网络连接";
    public static final String NETWORK_ERROR_3 = "数据解析失败";
    public static final String NETWORK_ERROR_4 = "服务器连接超时";
    public static final String NETWORK_ERROR_5 = "未知错误";

}
