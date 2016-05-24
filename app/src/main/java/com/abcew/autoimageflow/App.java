package com.abcew.autoimageflow;

import android.app.Application;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
/**
 */
public class App extends Application {
    private static ImageLoaderConfiguration imageLoaderConfiguration;
    private static final int MAX_HEAP_SIZE = (int) Runtime.getRuntime().maxMemory();
    public static final int MAX_MEMORY_CACHE_SIZE = MAX_HEAP_SIZE / 6;

    @Override
    public void onCreate() {
        super.onCreate();
        imageLoaderConfiguration =
                new ImageLoaderConfiguration
                        .Builder(this)
                        .diskCacheFileCount(100)
                        .denyCacheImageMultipleSizesInMemory()
                        .threadPoolSize(Thread.NORM_PRIORITY - 1)
                        .writeDebugLogs()
                        .diskCacheSize(80 * 1024 * 1024)
                        .tasksProcessingOrder(QueueProcessingType.FIFO)
                        .memoryCacheSize(MAX_MEMORY_CACHE_SIZE)
                        .build();
        ImageLoader.getInstance().init(imageLoaderConfiguration);
    }

}
