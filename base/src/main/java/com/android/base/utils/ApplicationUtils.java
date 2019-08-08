package com.android.base.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.provider.Settings;
import android.text.TextUtils;

import java.util.UUID;

/**
 * author  : 指尖的力量
 * date    : 2019-08-08 09:51
 * desc    : Application工具类
 * modify  :
 * version : 1.0
 */

public class ApplicationUtils {

    private static volatile ApplicationUtils applicationUtils;

    public static ApplicationUtils getInstance() {
        if (applicationUtils == null) {
            synchronized (ApplicationUtils.class) {
                if (applicationUtils == null) {
                    applicationUtils = new ApplicationUtils();
                }
            }
        }
        return applicationUtils;
    }

    private ApplicationUtils() {
        initPackageInfo();
        initApplicationInfo();
        initDeviceId();
    }

    private PackageInfo packageInfo = null;
    private String applicationName;
    private String deviceId;
    private String channel;
    private String installChannel;

    private void initPackageInfo() {
        if (packageInfo != null) {
            return;
        }
        try {
            packageInfo = ContextUtils.get().getPackageManager().getPackageInfo(ContextUtils.get().getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void initInstallChannel() {
        if (!TextUtils.isEmpty(channel)) {
            return;
        }
        ApplicationInfo appInfo;
        try {
            appInfo = ContextUtils.get().getPackageManager().getApplicationInfo(getPackageName(),
                    PackageManager.GET_META_DATA);
            channel = appInfo.metaData.getString("channel");
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void initApplicationInfo() {
        PackageManager packageManager = null;
        ApplicationInfo applicationInfo;
        try {
            packageManager = ContextUtils.get().getPackageManager();
            applicationInfo = packageManager.getApplicationInfo(ContextUtils.get().getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            applicationInfo = null;
        }

        if (applicationInfo != null) {
            applicationName = (String) packageManager.getApplicationLabel(applicationInfo);
        }
    }

    private void initDeviceId() {
        deviceId = Settings.System.getString(ContextUtils.get().getContentResolver(), Settings.System.ANDROID_ID);
    }

    /**
     * 获取包名
     */
    public String getPackageName() {
        initPackageInfo();
        if (packageInfo != null) {
            return packageInfo.packageName;
        }
        return "";
    }

    /**
     * 获取可变UUID
     */
    public String getRandomUUID() {
        UUID uuid = UUID.randomUUID();
        String uniqueId = uuid.toString();
        return uniqueId.replace("-", "");
    }

    /**
     * 获取版本名称
     */
    public String getVersionName() {
        initPackageInfo();
        if (packageInfo != null) {
            return packageInfo.versionName;
        }
        return "";
    }

    /**
     * 获取版本号
     */
    public int getVersionCode() {
        initPackageInfo();
        if (packageInfo != null) {
            return packageInfo.versionCode;
        }
        return 0;
    }

    /**
     * 获取安装时间
     */
    public long getFirstInstallTime() {
        initPackageInfo();
        if (packageInfo != null) {
            return packageInfo.firstInstallTime;
        }
        return 0;
    }

    /**
     * 获取应用程序名称
     */
    public String getAppName() {
        try {
            PackageManager packageManager = ContextUtils.get().getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(
                    ContextUtils.get().getPackageName(), 0);
            int labelRes = packageInfo.applicationInfo.labelRes;
            return ContextUtils.get().getResources().getString(labelRes);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取渠道号
     */
    public String getChannel() {
        if (TextUtils.isEmpty(installChannel)) {
            SharedPreferences sp = ContextUtils.get().getSharedPreferences("appinstallinfo", Context.MODE_PRIVATE);
            String ic = sp.getString("installchannel", null);

            if (TextUtils.isEmpty(ic)) {
                ic = getInstallChannel();
                sp.edit().putString("installchannel", ic).apply();
            }

            installChannel = ic;
        }
        return installChannel;
    }

    /**
     * 获取首次安装的渠道号
     */
    public String getInstallChannel() {
        initInstallChannel();
        return channel == null ? "" : channel;
    }

    /**
     * 获取当前手机系统版本号
     *
     * @return 系统版本号
     */
    public String getSystemVersion() {
        return android.os.Build.VERSION.RELEASE;
    }


    /**
     * 获取获取设备Id
     */
    public String getDeviceId() {
        initDeviceId();
        return deviceId;
    }

    /**
     * 获取手机型号
     *
     * @return 手机型号
     */
    public String getSystemModel() {
        return android.os.Build.MODEL;
    }


    /**
     * 判断是否安装了某个应用
     */
    public String getApplicationName() {
        initApplicationInfo();
        return applicationName;
    }

    public static boolean checkApkExist(Context context, String packageName) {
        if (TextUtils.isEmpty(packageName))
            return false;
        try {
            ApplicationInfo info = context.getPackageManager().getApplicationInfo(packageName, PackageManager.GET_UNINSTALLED_PACKAGES);
            LogUtils.i(info.toString());
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
