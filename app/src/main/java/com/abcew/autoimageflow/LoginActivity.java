package com.abcew.autoimageflow;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.abcew.autoimageflow.model.BaseFlow;

import java.util.ArrayList;
import java.util.List;

/**
 */
public class LoginActivity extends AppCompatActivity{


    private ImageFlowSurfaceView imageflow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        imageflow= (ImageFlowSurfaceView) findViewById(R.id.imageflow);
        List<BaseFlow>  flows=new ArrayList<>();
        BaseFlow  b1=new BaseFlow();
        b1.setUrl(R.mipmap.ic_launcher+"");
        flows.add(b1);
        BaseFlow  b2=new BaseFlow();
        b2.setUrl(R.mipmap.ic_launcher+"");
        flows.add(b2);
        imageflow.addImageFlow(flows);
        imageflow.prepare();
        imageflow.postDelayed(new Runnable() {
            @Override
            public void run() {
//                imageflow.start();
            }
        },1000);
//        imageflow.start();
//        imageflow.getController().getSettings().setmSpanCount();

    }
}

