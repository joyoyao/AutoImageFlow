package com.abcew.autoimageflow.controller;


import android.graphics.Canvas;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;

import com.abcew.autoimageflow.Settings;
import com.abcew.autoimageflow.model.BaseFlow;
import com.abcew.autoimageflow.renderer.IRenderer;
import com.abcew.autoimageflow.renderer.RendererController;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class FlowViewController extends Handler {
    public static final int START = 1;

    public static final int PREPARE = 3;
    public static final int UPDATE = 2;
    private Thread updateThread;
    private Settings settings;
    volatile boolean mIsQuited = false;
    private IFlowView iFlowView;
    RendererController rendererController;


    public FlowViewController(Looper looper, IFlowView view) {
        super(looper);
        this.iFlowView = view;
        settings = new Settings();
        rendererController=new RendererController(this,settings);
    }

    public Settings getSettings() {
        return settings;
    }

    public void setSettings(Settings settings) {
        this.settings = settings;
    }

    @Override
    public void handleMessage(Message msg) {
        int what = msg.what;
        switch (what) {
            case START:


                updateInNewThread();
                break;
            case UPDATE:

                break;

            case PREPARE:
                rendererController.firstPrepareData();
                break;
        }
    }

    public void draw(Canvas canvas) {
            rendererController.draw(canvas);
    }


    private void updateInNewThread() {
        if (updateThread != null) {
            return;
        }
        updateThread = new Thread("DFM Update") {
            @Override
            public void run() {
                long lastTime = System.currentTimeMillis();
                long dTime = 0;
                while (!mIsQuited) {
                    iFlowView.drawImageFlow();
//                    long startMS = System.currentTimeMillis();
//                    dTime = System.currentTimeMillis() - lastTime;
//                    long diffTime = mFrameUpdateRate - dTime;
//                    if (diffTime > 1) {
//                        SystemClock.sleep(1);
//                        continue;
//                    }
//                    lastTime = startMS;
//                    long d = syncTimer(startMS);
//                    if (d < 0) {
//                        SystemClock.sleep(60 - d);
//                        continue;
//                    }
//                    d = mDanmakuView.drawDanmakus();
//                    if (!mDanmakusVisible) {
//                        waitRendering(INDEFINITE_TIME);
//                    } else if (mRenderingState.nothingRendered && mIdleSleep) {
//                        dTime = mRenderingState.endTime - timer.currMillisecond;
//                        if (dTime > 500) {
//                            notifyRendering();
//                            waitRendering(dTime - 400);
//                        }
//                    }
                }
            }
        };
        updateThread.start();
    }


    public void notifyDispSizeChanged(int width, int height) {
        rendererController.notifyDispSizeChanged(width,height);
    }

    public void addImageFlow(List<BaseFlow> mDatas) {
        rendererController.addImageFlow(mDatas);
    }


}
