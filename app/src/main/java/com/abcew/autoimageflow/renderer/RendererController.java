/*
 * Copyright (C) 2013 Chen Hui <calmer91@gmail.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.abcew.autoimageflow.renderer;


import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Handler;

import com.abcew.autoimageflow.R;
import com.abcew.autoimageflow.Settings;
import com.abcew.autoimageflow.controller.FlowViewController;
import com.abcew.autoimageflow.model.BaseFlow;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageSize;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class RendererController {
    private List<BaseFlow> mDatas;
    private ArrayList<IRenderer> mDrawList;
    private final java.util.Deque<IRenderer> mWaitingItems = new LinkedList<>();

    private HashMap<Integer, Integer> mChannelMap = new HashMap<>();
    private Handler handler;
    private Settings settings;
    private int width = 0;
    private int height = 1037;
    private int minHeight = 0;
    private boolean isAddList = true;
    private int mCurrentPointer = 0;

    public RendererController(Handler handler, Settings settings) {
        this.handler = handler;
        this.settings = settings;
    }

    public void draw(Canvas canvas) {
        if (mDrawList != null&&mDrawList.size()>0) {
            Iterator keys = mDrawList.iterator();
            while (keys.hasNext()){
                IRenderer item = (IRenderer) keys.next();
                if (item.isOut()) {
                    keys.remove();
                } else {
                    item.drawSelf(canvas);
                }
            }
//            for (Iterator<IRenderer> it = mDrawList.iterator(); it.hasNext(); ) {
//                IRenderer item = it.next();
//
//            }
            //先绘制正在播放的弹幕
//            for (int i = 0; i < mDrawMap.size(); i++) {
//                ArrayList<IDanmakuItem> list = mChannelMap.get(i);
//
//            }
        } else {
//            handler.obtainMessage(FlowViewController.PREPARE).sendToTarget();
        }


    }

    public void addImageFlow(List<BaseFlow> mDatas) {
        this.mDatas = mDatas;
    }

    public void firstPrepareData() {
        if (mDrawList == null) {
            mDrawList = new ArrayList<>();
        } else {
            mDrawList.clear();
        }
        mChannelMap.clear();
        for (int i = 0; i < settings.getSpanCount(); i++) {
            mChannelMap.put(i, 0);
        }
        minHeight=0;
        isAddList = true;
        while (isAddList) {
            if (minHeight < height ) {
                VerticalRenderer verticalRenderer = new VerticalRenderer();
                verticalRenderer.setLocationX(0);
                verticalRenderer.setLocationY(minHeight + settings.minHspanH);
                if (mCurrentPointer > mDatas.size()) {
                    mCurrentPointer = 0;
                }
                ImageSize targetSize = new ImageSize(200, 100); // result Bitmap will be fit to this size
                Bitmap bmp = ImageLoader.getInstance().loadImageSync("drawable://" + R.drawable.user_myself_bg, targetSize);
                verticalRenderer.setWidth(200);
                verticalRenderer.setHeight(100);
                verticalRenderer.setBitmap(bmp);
                mDrawList.add(verticalRenderer);
                minHeight=verticalRenderer.getLocationY()+verticalRenderer.getHeight();
//                mChannelMap.put(minKey,verticalRenderer.getLocationY()+verticalRenderer.getHeight());
                mCurrentPointer++;
            } else {
                isAddList = false;
            }

        }

        handler.obtainMessage(FlowViewController.START).sendToTarget();
    }

    public static int getMinHight(HashMap<Integer, Integer> mChannelMap) {
        int key = 0;
        if(mChannelMap.size()==0){
            return -1;
        }
        if(mChannelMap.size()==1){
            return 0;
        }
        for (int i = 1; i < mChannelMap.size(); i++) {
            if (mChannelMap.get(key) > mChannelMap.get(i)) {
                key = i;
            }

        }
        return key;

    }

    public void notifyDispSizeChanged(int width, int height) {
        this.width = width;
        this.height = height;
    }
}