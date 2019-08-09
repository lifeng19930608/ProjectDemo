package com.android.base.glide;

import android.content.Context;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.SimpleTarget;
import com.see.you.plan.GlideApp;

import java.io.File;

import jp.wasabeef.glide.transformations.BlurTransformation;

/**
 * author  : 指尖的力量
 * date    : 2019-08-09 11:21
 * desc    : 网络图片加载工具
 * modify  :
 * version : 1.0
 */

public class GlideUtils {

    /**
     * 直接加载图片的方法 （没有占位图没有错误页面）
     */
    public static void loadImage(Context context, String url, ImageView imageView) {
        String imageUrl = url;
        if (TextUtils.isEmpty(imageUrl)) {
            imageUrl = "";
        }
        if (context != null) {
            GlideApp.with(context)
                    .load(imageUrl)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(imageView);
        }
    }

    /**
     * 加载图片 （只显示错误图片，没有占位图片）
     */
    public static void loadImage(Context context, String url, ImageView imageView, int errorId) {
        String imageUrl = url;
        if (TextUtils.isEmpty(imageUrl)) {
            imageUrl = "";
        }
        if (context != null) {
            GlideApp.with(context)
                    .load(imageUrl)
                    .error(errorId)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(imageView);
        }
    }

    /**
     * 加载图片 （占位图片和错误图片一致）
     */
    public static void loadImage(Context context, String url, int placeHolderId, ImageView imageView) {
        String imageUrl = url;
        if (TextUtils.isEmpty(imageUrl)) {
            imageUrl = "";
        }
        if (context != null) {
            GlideApp.with(context)
                    .load(imageUrl)
                    .placeholder(placeHolderId)
                    .error(placeHolderId)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(imageView);
        }
    }

    /**
     * 加载图片 （占位图片和错误图片不一致）
     */
    public static void loadImage(Context context, String url, ImageView imageView, int placeHolderId, int errorId) {
        String imageUrl = url;
        if (TextUtils.isEmpty(imageUrl)) {
            imageUrl = "";
        }
        if (context != null) {
            GlideApp.with(context)
                    .load(imageUrl)
                    .placeholder(placeHolderId)
                    .error(errorId)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(imageView);
        }
    }

    /**
     * 加载圆形图片 （没有占位图片和错误图片）
     */
    public static void loadCircleImage(Context context, String url, ImageView imageView) {
        String imageUrl = url;
        if (TextUtils.isEmpty(imageUrl)) {
            imageUrl = "";
        }
        if (context != null) {
            GlideApp.with(context)
                    .load(imageUrl)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .circleCrop()
                    .into(imageView);
        }
    }

    /**
     * 加载圆形图片 （只显示错误图片）
     */
    public static void loadCircleImage(Context context, String url, int errorId, ImageView imageView) {
        String imageUrl = url;
        if (TextUtils.isEmpty(imageUrl)) {
            imageUrl = "";
        }
        if (context != null) {
            GlideApp.with(context)
                    .load(imageUrl)
                    .error(errorId)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .circleCrop()
                    .into(imageView);
        }
    }

    /**
     * 加载圆形图片 （占位图片和错误图片一致）
     */
    public static void loadCircleImage(Context context, String url, ImageView imageView, int placeHolderId) {
        String imageUrl = url;
        if (TextUtils.isEmpty(imageUrl)) {
            imageUrl = "";
        }
        if (context != null) {
            GlideApp.with(context)
                    .load(imageUrl)
                    .placeholder(placeHolderId)
                    .error(placeHolderId)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .circleCrop()
                    .into(imageView);
        }
    }

    /**
     * 加载圆形图片 （占位图片和错误图片不一致）
     */
    public static void loadCircleImage(Context context, String url, ImageView imageView, int placeHolderId, int errorId) {
        String imageUrl = url;
        if (TextUtils.isEmpty(imageUrl)) {
            imageUrl = "";
        }
        if (context != null) {
            GlideApp.with(context)
                    .load(imageUrl)
                    .placeholder(placeHolderId)
                    .error(errorId)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .circleCrop()
                    .into(imageView);
        }
    }

    /**
     * 加载普通的图片 高斯模糊处理 （占位图片和错误图片一致）
     */
    public static void loadVagueImage(Context context, String url, ImageView imageView, int placeHolderId, int radius) {
        String imageUrl = url;
        if (TextUtils.isEmpty(imageUrl)) {
            imageUrl = "";
        }
        if (context != null) {
            GlideApp.with(context)
                    .load(imageUrl)
                    .placeholder(placeHolderId)
                    .error(placeHolderId)
                    .transform(new BlurTransformation(radius))
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(imageView);
        }
    }

    /**
     * 加载普通的图片 高斯模糊处理 （占位图片和错误图片一致）
     */
    public static void loadVagueImage(Context context, String url, ImageView imageView, int placeHolderId, int errorId, int radius) {
        String imageUrl = url;
        if (TextUtils.isEmpty(imageUrl)) {
            imageUrl = "";
        }
        if (context != null) {
            GlideApp.with(context)
                    .load(imageUrl)
                    .placeholder(placeHolderId)
                    .error(errorId)
                    .transform(new BlurTransformation(radius))
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(imageView);
        }
    }

    /**
     * 加载圆角图片 (可单独设置某个角圆角)
     * 使用圆角的时候又一个条件，控件的大小一定要小于原图片尺寸大小
     */
    public static void loadRoundImage(Context context, String url, ImageView imageView, int errorId, int radius, GlideRoundedCornersTransform.CornerType type) {
        String imageUrl = url;
        if (TextUtils.isEmpty(imageUrl)) {
            imageUrl = "";
        }
        if (context != null) {
            GlideApp.with(context)
                    .load(imageUrl)
                    .error(errorId)
                    .transform(new GlideRoundedCornersTransform(radius, type))
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(imageView);
        }
    }

    /**
     * 加载普通的图片 本地文件
     */
    public static void loadImage(Context context, File file, ImageView imageView) {
        if (context != null) {
            GlideApp.with(context)
                    .load(file)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(imageView);
        }
    }

    /**
     * 加载普通的图片 本地文件 （错误图片和占位图片不一致）
     */
    public static void loadImage(Context context, File file, ImageView imageView, int placeHolder, int error) {
        if (context != null) {
            GlideApp.with(context)
                    .load(file)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .placeholder(placeHolder)
                    .error(error)
                    .into(imageView);
        }
    }

    /**
     * 加载Bitmap图片
     */
    public static void loadFitCenterCircleBitmap(Context context, String url, SimpleTarget<Bitmap> bitmapSimpleTarget) {
        GlideApp.with(context)
                .asBitmap()
                .load(url)
                .fitCenter()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .circleCrop()
                .into(bitmapSimpleTarget);
    }

}
