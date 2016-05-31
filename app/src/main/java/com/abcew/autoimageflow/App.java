package com.abcew.autoimageflow;

import android.app.Application;
import android.graphics.Bitmap;

import com.abcew.autoimageflow.utils.LocalDisplay;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
/**
 */
public class App extends Application {
    private static ImageLoaderConfiguration imageLoaderConfiguration;

    public static DisplayImageOptions loadingOptions;
    private static final int MAX_HEAP_SIZE = (int) Runtime.getRuntime().maxMemory();
    public static final int MAX_MEMORY_CACHE_SIZE = MAX_HEAP_SIZE / 6;

    @Override
    public void onCreate() {
        super.onCreate();
        LocalDisplay.init(getApplicationContext());

        loadingOptions = new DisplayImageOptions.Builder()
                .cacheInMemory(true).cacheOnDisk(true).
                        showImageOnFail(R.drawable.user_myself_bg).
                        imageScaleType(ImageScaleType.EXACTLY).
                        bitmapConfig(Bitmap.Config.RGB_565).
                        considerExifParams(true).
                        build();

        imageLoaderConfiguration =
                new ImageLoaderConfiguration
                        .Builder(this)
                        .diskCacheFileCount(100)
                        .denyCacheImageMultipleSizesInMemory()
                        .threadPoolSize(Thread.NORM_PRIORITY - 1)
                        .writeDebugLogs()
                        .defaultDisplayImageOptions(loadingOptions)
                        .diskCacheSize(80 * 1024 * 1024)
                        .tasksProcessingOrder(QueueProcessingType.FIFO)
                        .memoryCacheSize(MAX_MEMORY_CACHE_SIZE)
                        .build();
        ImageLoader.getInstance().init(imageLoaderConfiguration);
    }

}
