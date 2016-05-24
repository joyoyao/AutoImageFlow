package com.abcew.autoimageflow;


import android.widget.LinearLayout;

public class Settings {
    public static final int HORIZONTAL = LinearLayout.HORIZONTAL;
    public static final int VERTICAL = LinearLayout.VERTICAL;
    private int mSpanCount = 2;
    private int mOrientation = VERTICAL;  // HORIZONTAL ||VERTICAL
    private boolean isRepeat = true;
    public int getSpanCount() {
        return mSpanCount;
    }

    public void setSpanCount(int mSpanCount) {
        this.mSpanCount = mSpanCount;
    }

    public int getOrientation() {
        return mOrientation;
    }

    public void setOrientation(int orientation) {
        this.mOrientation = orientation;
    }


}
