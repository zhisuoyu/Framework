package zsy.framework.app;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import zsy.framework.app.api.Init;

public abstract class BaseActivity extends AppCompatActivity implements Init {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        if (AppManager.getInstance().getActivityStack().isEmpty()) {
            onFirstActivityCreate();
        }
        AppManager.getInstance().addActivity(this);
        init();
    }

    @Override
    protected void onStart() {
        super.onStart();
//        AppState.getInstance().setAppRunningForeground(AppUtils.isAppOnForeground(this));
    }

    @Override
    protected void onStop() {
        super.onStop();
//        AppState.getInstance().setAppRunningForeground(AppUtils.isAppOnForeground(this));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        AppManager.getInstance().removeActivity(this);
        if (AppManager.getInstance().getActivityStack().isEmpty()) {
            onAllActivityDestroy();
        }
    }

    protected void onFirstActivityCreate() {
    }

    protected void onAllActivityDestroy() {
    }


    @Override
    public void init() {
        initContentView();
        initInjector();
        initData();
        ButterKnife.bind(this);
        initView();
        initEvent();
    }


    @Override
    public void initData() {

    }

    @Override
    public void initView() {

    }

    @Override
    public void initInjector() {

    }

    @Override
    public void initEvent() {

    }
}
