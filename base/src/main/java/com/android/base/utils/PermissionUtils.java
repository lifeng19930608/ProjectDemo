package com.android.base.utils;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.provider.Settings;
import android.util.ArrayMap;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;

import com.android.base.R;

/**
 * author  : 指尖的力量
 * date    : 2019-08-11 13:23
 * desc    : 动态申请权限工具类
 * modify  :
 * version : 1.0
 */

public class PermissionUtils {

    public static final int REQUEST_FOR_LOCATION_PERMISSION = 1;//定位权限
    public static final int REQUEST_FOR_STORAGE_PERMISSION = 2;//文件存储权限
    public static final int REQUEST_FOR_CAMERA_PERMISSION = 3;//相机权限
    public static final int REQUEST_FOR_AUDIO_PERMISSION = 4;//视屏权限
    public static final int REQUEST_FOR_INSTALL_PERMISSION = 5;//应用安装

    private static volatile PermissionUtils instance;
    private ArrayMap<String, Object> callbackPool;
    private AlertDialog dialog;

    private PermissionUtils() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            callbackPool = new ArrayMap<>();
        }
    }

    public static PermissionUtils getInstance() {
        if (instance == null) {
            synchronized (PermissionUtils.class) {
                if (instance == null) {
                    instance = new PermissionUtils();
                }
            }
        }
        return instance;
    }

    private boolean check(Context context, Type permissionType) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return true;
        }
        switch (permissionType) {
            case LOCATION:
                return ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                        && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED;
            case STORAGE:
                return ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED
                        && ActivityCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED;
            case CAMERA:
                return ActivityCompat.checkSelfPermission(context, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED;
            case AUDIO:
                return ActivityCompat.checkSelfPermission(context, Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_GRANTED;
            case INSTALL:
                return ActivityCompat.checkSelfPermission(context, Manifest.permission.REQUEST_INSTALL_PACKAGES) == PackageManager.PERMISSION_GRANTED;
            default:
                return true;
        }
    }

    public void request(Activity activity, Type permissionType, Callback callback) {
        if (check(activity, permissionType)) {
            if (callback != null)
                callback.onResult(true);
            return;
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            switch (permissionType) {
                case LOCATION:
                    callbackPool.put(String.valueOf(activity.hashCode()) + REQUEST_FOR_LOCATION_PERMISSION, callback);
                    activity.requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION},
                            REQUEST_FOR_LOCATION_PERMISSION);
                    break;
                case STORAGE:
                    callbackPool.put(String.valueOf(activity.hashCode()) + REQUEST_FOR_STORAGE_PERMISSION, callback);
                    activity.requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE},
                            REQUEST_FOR_STORAGE_PERMISSION);
                    break;
                case CAMERA:
                    callbackPool.put(String.valueOf(activity.hashCode()) + REQUEST_FOR_CAMERA_PERMISSION, callback);
                    activity.requestPermissions(new String[]{Manifest.permission.CAMERA},
                            REQUEST_FOR_CAMERA_PERMISSION);
                    break;
                case AUDIO:
                    callbackPool.put(String.valueOf(activity.hashCode()) + REQUEST_FOR_AUDIO_PERMISSION, callback);
                    activity.requestPermissions(new String[]{Manifest.permission.RECORD_AUDIO},
                            REQUEST_FOR_AUDIO_PERMISSION);
                case INSTALL:
                    callbackPool.put(String.valueOf(activity.hashCode()) + REQUEST_FOR_INSTALL_PERMISSION, callback);
                    activity.requestPermissions(new String[]{Manifest.permission.REQUEST_INSTALL_PACKAGES},
                            REQUEST_FOR_INSTALL_PERMISSION);
                    break;
            }
        }
    }

    public void onRequestPermissionsResult(Activity activity, int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_FOR_LOCATION_PERMISSION:
            case REQUEST_FOR_STORAGE_PERMISSION:
            case REQUEST_FOR_CAMERA_PERMISSION:
            case REQUEST_FOR_AUDIO_PERMISSION:
            case REQUEST_FOR_INSTALL_PERMISSION:
                Callback callback = (Callback) callbackPool.get(String.valueOf(activity.hashCode()) + requestCode);
                if (callback != null) {
                    callback.onResult(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED);
                    callbackPool.remove(String.valueOf(activity.hashCode()) + requestCode);
                }
                break;
        }
    }

    public enum Type {
        LOCATION,
        STORAGE,
        CAMERA,
        AUDIO,
        INSTALL
    }

    public interface Callback {
        void onResult(boolean grant);
    }

    //没有获取权限之后的操作
    public void showDialog(final Activity activity) {
        hideDialog();
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle(R.string.permission_title);
        builder.setIcon(R.mipmap.ic_launcher);
        builder.setMessage(R.string.permission_text);
        builder.setPositiveButton(R.string.confirm, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                activity.startActivity(new Intent(Settings.ACTION_SETTINGS));
            }
        });
        builder.setCancelable(false);
        dialog = builder.create();
        dialog.show();
    }

    private void hideDialog() {
        if (dialog != null) {
            dialog.dismiss();
        }
    }

}
