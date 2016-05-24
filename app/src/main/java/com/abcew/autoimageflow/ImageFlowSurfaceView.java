package com.abcew.autoimageflow;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.PixelFormat;
import android.os.HandlerThread;
import android.os.Looper;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.abcew.autoimageflow.controller.DrawHelper;
import com.abcew.autoimageflow.controller.FlowViewController;
import com.abcew.autoimageflow.controller.IFlowView;
import com.abcew.autoimageflow.model.BaseFlow;
import java.util.List;

/**
 */
public class ImageFlowSurfaceView extends SurfaceView implements SurfaceHolder.Callback,IFlowView {
    private SurfaceHolder mSurfaceHolder;
    private boolean isSurfaceCreated;
    protected int mDrawingThreadType = THREAD_TYPE_NORMAL_PRIORITY;
    private HandlerThread mHandlerThread;
    public ImageFlowSurfaceView(Context context) {
        super(context);
    }
    private FlowViewController controllerHander;

    public ImageFlowSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
         init();
    }

    public ImageFlowSurfaceView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    private void init() {
        setZOrderMediaOverlay(true);
        setWillNotCacheDrawing(true);
        setDrawingCacheEnabled(false);
        setWillNotDraw(true);
        mSurfaceHolder = getHolder();
        mSurfaceHolder.addCallback(this);
        mSurfaceHolder.setFormat(PixelFormat.TRANSPARENT);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        isSurfaceCreated=true;
        Canvas canvas = holder.lockCanvas();
        if (canvas != null) {
            DrawHelper.clearCanvas(canvas);
            holder.unlockCanvasAndPost(canvas);
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        if (controllerHander != null) {
            controllerHander.notifyDispSizeChanged(width, height);
        }
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        isSurfaceCreated=false;
    }

    @Override
    public void prepare() {
        if (controllerHander == null)
            controllerHander = new FlowViewController(getLooper(mDrawingThreadType), this);
    }


    protected Looper getLooper(int type){
        if (mHandlerThread != null) {
            mHandlerThread.quit();
            mHandlerThread = null;
        }

        int priority;
        switch (type) {
            case THREAD_TYPE_MAIN_THREAD:
                return Looper.getMainLooper();
            case THREAD_TYPE_HIGH_PRIORITY:
                priority = android.os.Process.THREAD_PRIORITY_URGENT_DISPLAY;
                break;
            case THREAD_TYPE_LOW_PRIORITY:
                priority = android.os.Process.THREAD_PRIORITY_LOWEST;
                break;
            case THREAD_TYPE_NORMAL_PRIORITY:
            default:
                priority = android.os.Process.THREAD_PRIORITY_DEFAULT;
                break;
        }
        String threadName = "DFM Handler Thread #"+priority;
        mHandlerThread = new HandlerThread(threadName, priority);
        mHandlerThread.start();
        return mHandlerThread.getLooper();
    }


    public void drawImageFlow() {
        if (!isSurfaceCreated)
            return ;
        if (!isShown())
            return ;
        Canvas canvas = mSurfaceHolder.lockCanvas();
        if (canvas != null){
            if (controllerHander != null) {
                controllerHander.draw(canvas);
            }
            if (isSurfaceCreated)
                mSurfaceHolder.unlockCanvasAndPost(canvas);
        }
    }

    public FlowViewController getController() {
        if(controllerHander==null){
            prepare();
        }
        return controllerHander;
    }

    public void setControllerHander(FlowViewController controllerHander) {
        this.controllerHander = controllerHander;
    }

    public void start() {
        if (controllerHander == null) {
            prepare();
        }
        controllerHander.obtainMessage(FlowViewController.START).sendToTarget();
    }
    public void addImageFlow(List<BaseFlow> data){
        if (controllerHander == null) {
            prepare();
        }
        controllerHander.addImageFlow(data);
    }

}
