package com.abcew.autoimageflow.controller;


import android.graphics.Canvas;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import com.abcew.autoimageflow.Settings;
import com.abcew.autoimageflow.model.BaseFlow;

import java.util.List;

public class FlowViewController extends Handler {
    public static final int START = 1;
    public static final int UPDATE = 2;
    private Thread updateThread;
    private Settings settings;
    private List<BaseFlow> mDatas;


    public FlowViewController(Looper looper, IFlowView view) {
        super(looper);
        settings = new Settings();
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

                break;
            case UPDATE:

                break;
        }
    }

    public void draw(Canvas canvas) {

    }


    public void notifyDispSizeChanged(int width, int height) {

    }

    public void addImageFlow(List<BaseFlow> mDatas) {
        this.mDatas = mDatas;
    }

}
