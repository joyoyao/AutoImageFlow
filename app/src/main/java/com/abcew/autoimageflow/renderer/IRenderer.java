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

import android.graphics.Canvas;

public abstract class IRenderer {
    protected int mSpanCount=1;
    protected int locationX;
    protected int locationY;
    protected long addListTime;
    protected int width = 0;
    protected int height = 0;
    public abstract void drawSelf(Canvas canvas);
    public abstract boolean isOut();

    public int getLocationY() {
        return locationY;
    }

    public void setLocationY(int locationY) {
        this.locationY = locationY;
    }

    public int getLocationX() {
        return locationX;
    }

    public void setLocationX(int locationX) {
        this.locationX = locationX;
    }

    public int getmSpanCount() {
        return mSpanCount;
    }

    public void setmSpanCount(int mSpanCount) {
        this.mSpanCount = mSpanCount;
    }

    public long getAddListTime() {
        return addListTime;
    }

    public void setAddListTime(long addListTime) {
        this.addListTime = addListTime;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}