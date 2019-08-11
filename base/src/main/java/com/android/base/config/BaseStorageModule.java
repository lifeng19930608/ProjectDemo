package com.android.base.config;

import android.content.Context;
import android.os.Build;

import java.io.File;

import io.storage.StorageConfiguration;
import io.storage.StorageModule;

/**
 * author  : 指尖的力量
 * date    : 2019-08-09 18:44
 * desc    :
 * modify  :
 * version : 1.0
 */

public class BaseStorageModule implements StorageModule {

    @Override
    public StorageConfiguration applyConfiguration(Context context) {

        String databaseName;
        switch (Environment.getEnv()) {
            default:
            case Environment.ENV_PRODUCT:
                databaseName = "StorageModule";
                break;
            case Environment.ENV_TEST:
                databaseName = "StorageModule_test";
                break;
            case Environment.ENV_DEVELOP:
                databaseName = "StorageModule_develop";
                break;
        }
        File databaseFolder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            databaseFolder = context.getExternalFilesDirs(null)[0];
        } else {
            databaseFolder = context.getExternalFilesDir(null);
        }
        if (databaseFolder != null && databaseFolder.exists()) {
            String path = databaseFolder.getAbsolutePath();
            if (!path.endsWith(File.separator)) {
                path = path + File.separator;
            }
            path = path + "database/";
            databaseFolder = new File(path);
        }
        return new StorageConfiguration.Builder(context)
                .databaseFolder(databaseFolder)
                .logEnabled(true)
                .databaseName(databaseName)
                .build();
    }
}