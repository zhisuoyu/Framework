package com.zsy.app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import butterknife.BindView;
import zsy.framework.app.BaseActivity;
import zsy.framework.widget.TopBar;

public class MainActivity extends BaseActivity {


    @BindView(R.id.tb) TopBar tb;

    @Override
    public void initContentView() {
        setContentView(R.layout.activity_main);
    }

    @Override
    public void initView() {
        super.initView();
        tb.setOnSubClickListener(new TopBar.OnSubClickListener() {
            @Override
            public void onLeftClick(View view) {
                Log.i("main", "onLeftClick");
            }

            @Override
            public void onRightClick(View view) {
                Log.i("main", "onRightClick");
            }
        });
    }
}
