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
import android.graphics.Paint;

public class VerticalRenderer extends IRenderer {
    private Bitmap bitmap;
    private Paint pain=new Paint();
    @Override
    public void drawSelf(Canvas canvas) {
        if (bitmap!=null&&!isOut()) {
            canvas.drawBitmap(bitmap, 0, getLocationY(), pain);
            locationY-=2;
        }
    }
    @Override
    public boolean isOut() {
        return locationY<-height?true:false;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }
}