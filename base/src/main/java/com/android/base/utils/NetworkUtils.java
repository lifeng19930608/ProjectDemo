package com.android.base.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;

/**
 * author  : 指尖的力量
 * date    : 2019-08-09 17:13
 * desc    :
 * modify  :
 * version : 1.0
 */

public class NetworkUtils {

    private static final int TYPE_WIFI = 1;
    private static final int TYPE_3G = 2;
    private static final int TYPE_2G = 3;
    private static final int TYPE_4G = 4;
    private static final int TYPE_NONE = 5;


    /**
     * 获取ConnectivityManager
     */
    public static ConnectivityManager getConnManager(Context context) {
        return (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
    }

    /**
     * isNetConnected:function:判断网络连接是否有效（此时可传输数据）。不可以和isNetAvailable混淆,
     * 不管wifi，还是mobile net，只有当前在连接状态（可有效传输数据）才返回true,反之false。
     *
     * @param context
     * @return
     */
    public static boolean isNetConnected(Context context) {
        if (context == null) {
            context = ContextUtils.get();
        }
        NetworkInfo net = getConnManager(context).getActiveNetworkInfo();
        return net != null && net.isConnected();
    }

    /**
     * 判断是否有可用状态的Wifi，以下情况返回false：
     * 1. 设备wifi开关关掉;
     * 2. 已经打开飞行模式；
     * 3. 设备所在区域没有信号覆盖；
     * 4. 设备在漫游区域，且关闭了网络漫游。
     *
     * @param context
     * @return boolean wifi为可用状态（不一定成功连接，即Connected）即返回ture
     */
    public static boolean isWifiAvailable(Context context) {
        NetworkInfo[] nets = getConnManager(context).getAllNetworkInfo();
        if (nets != null) {
            for (NetworkInfo net : nets) {
                if (net.getType() == ConnectivityManager.TYPE_WIFI) {
                    return net.isAvailable();
                }
            }
        }
        return false;
    }

    /**
     * 判断有无可用状态的移动网络，注意关掉设备移动网络直接不影响此函数。
     * 也就是即使关掉移动网络，那么移动网络也可能是可用的(彩信等服务)，即返回true。
     * 以下情况它是不可用的，将返回false：
     * 1. 设备打开飞行模式；
     * 2. 设备所在区域没有信号覆盖；
     * 3. 设备在漫游区域，且关闭了网络漫游。
     *
     * @param context
     * @return boolean
     */
    public static boolean isMobileAvailable(Context context) {
        NetworkInfo[] nets = getConnManager(context).getAllNetworkInfo();
        if (nets != null) {
            for (NetworkInfo net : nets) {
                if (net.getType() == ConnectivityManager.TYPE_MOBILE) {
                    return net.isAvailable();
                }
            }
        }
        return false;
    }

    /**
     * 打印当前各种网络状态
     *
     * @param context
     * @return boolean
     */
    public static boolean printNetworkInfo(Context context) {
        ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null) {
            NetworkInfo[] info = connectivity.getAllNetworkInfo();
            if (info != null) {
                for (int i = 0; i < info.length; i++) {
                    LogUtils.e("NetworkInfo[" + i + "]isAvailable : " + info[i].isAvailable());
                    LogUtils.e("NetworkInfo[" + i + "]isConnected : " + info[i].isConnected());
                    LogUtils.e("NetworkInfo[" + i + "]isConnectedOrConnecting : " + info[i].isConnectedOrConnecting());
                    LogUtils.e("NetworkInfo[" + i + "]: " + info[i]);
                }
                LogUtils.e("\n");
            } else {
                LogUtils.e("getAllNetworkInfo is null");
            }
        }
        return false;
    }

    public static int getNetWorkType(Context context) {
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = null;
        if (manager != null) {
            networkInfo = manager.getActiveNetworkInfo();
        }
        if (networkInfo != null && networkInfo.isConnected()) {
            String type = networkInfo.getTypeName();
            if (type.equalsIgnoreCase("WIFI")) {
                return TYPE_WIFI;
            } else if (type.equalsIgnoreCase("MOBILE")) {
                NetworkInfo mobileInfo = manager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
                if (mobileInfo != null) {
                    switch (mobileInfo.getType()) {
                        case ConnectivityManager.TYPE_MOBILE:// 手机网络
                            switch (mobileInfo.getSubtype()) {
                                case TelephonyManager.NETWORK_TYPE_UMTS:
                                case TelephonyManager.NETWORK_TYPE_EVDO_0:
                                case TelephonyManager.NETWORK_TYPE_EVDO_A:
                                case TelephonyManager.NETWORK_TYPE_HSDPA:
                                case TelephonyManager.NETWORK_TYPE_HSUPA:
                                case TelephonyManager.NETWORK_TYPE_HSPA:
                                case TelephonyManager.NETWORK_TYPE_EVDO_B:
                                case TelephonyManager.NETWORK_TYPE_EHRPD:
                                case TelephonyManager.NETWORK_TYPE_HSPAP:
                                    return TYPE_3G;
                                case TelephonyManager.NETWORK_TYPE_CDMA:
                                case TelephonyManager.NETWORK_TYPE_GPRS:
                                case TelephonyManager.NETWORK_TYPE_EDGE:
                                case TelephonyManager.NETWORK_TYPE_1xRTT:
                                case TelephonyManager.NETWORK_TYPE_IDEN:
                                    return TYPE_2G;
                                case TelephonyManager.NETWORK_TYPE_LTE:
                                    return TYPE_4G;
                                default:
                                    return TYPE_NONE;
                            }
                    }
                }
            }
        }
        return TYPE_NONE;
    }

}
