package zsy.framework.test;


import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import zsy.framework.R;
import zsy.framework.app.BaseActivity;


/**
 * Created by mzs on 2017/4/1.
 */

public abstract class TestActivity extends BaseActivity {

    protected List<String> datas;
    private TestRvAdapter adapter;
    private RecyclerView testRv;

    @Override
    public void initContentView() {
        setContentView(R.layout.activity_test);
    }

    @Override
    public void initData() {
        super.initData();
        datas = getActions();
        adapter = new TestRvAdapter(datas);
    }

    public abstract List<String> getActions();

    public abstract void onItemClick(int position);

    @Override
    public void initView() {
        super.initView();
        testRv =  findViewById(R.id.rv_test);
        testRv.setAdapter(adapter);
        testRv.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void initEvent() {
        super.initEvent();
        adapter.setOnItemClickLsn(new TestRvAdapter.OnItemClickLsn() {
            @Override
            public void onItemClick(int position) {
                TestActivity.this.onItemClick(position);
            }
        });
    }
}
