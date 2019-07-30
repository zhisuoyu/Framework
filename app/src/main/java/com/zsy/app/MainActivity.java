package com.zsy.app;

import android.util.Log;

import java.util.Arrays;
import java.util.List;

import zsy.framework.test.TestActivity;

public class MainActivity extends TestActivity {
    @Override
    public List<String> getActions() {
        return Arrays.asList("Test");
    }

    @Override
    public void onItemClick(int position) {
        switch (position) {
            case 0:
                Log.i("main", "position:" + position);
                break;
        }
    }


//    @BindView(R.id.tb) TopBar tb;
//
//    @Override
//    public void initContentView() {
//        setContentView(R.layout.activity_main);
//    }
//
//    @Override
//    public void initView() {
//        super.initView();
//        tb.setOnSubClickListener(new TopBar.OnSubClickListener() {
//            @Override
//            public void onLeftClick(View view) {
//                Log.i("main", "onLeftClick");
//            }
//
//            @Override
//            public void onRightClick(View view) {
//                Log.i("main", "onRightClick");
//            }
//        });
//    }
}
