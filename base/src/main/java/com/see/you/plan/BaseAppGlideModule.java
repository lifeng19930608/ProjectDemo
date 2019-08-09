package com.see.you.plan;

import android.content.Context;

import androidx.annotation.NonNull;

import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.load.engine.cache.InternalCacheDiskCacheFactory;
import com.bumptech.glide.load.engine.cache.LruResourceCache;
import com.bumptech.glide.module.AppGlideModule;

/**
 * author  : 指尖的力量
 * date    : 2019-08-09 11:20
 * desc    :
 * modify  :
 * version : 1.0
 */

@GlideModule
public class BaseAppGlideModule extends AppGlideModule {

    @Override
    public boolean isManifestParsingEnabled() {
        return false;
    }

    @Override
    public void applyOptions(@NonNull Context context, @NonNull GlideBuilder builder) {
        int memoryCacheSizeBytes = 1024 * 1024 * 20; // 20 MB
        int diskCacheSizeBytes = 1024 * 1024 * 100;  //100 MB
        builder.setMemoryCache(new LruResourceCache(memoryCacheSizeBytes))
                .setDiskCache(new InternalCacheDiskCacheFactory(context, diskCacheSizeBytes));
        super.applyOptions(context, builder);
    }

}
