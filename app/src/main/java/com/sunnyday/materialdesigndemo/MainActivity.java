package com.sunnyday.materialdesigndemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import com.sunnyday.materialdesigndemo.adapter.MyRecyclerAdapter;
import com.sunnyday.materialdesigndemo.adapter.MyRecyclerAdapterWrapper;


import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private List<String> mList;
    private MyRecyclerAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }

    /**
     * 初始化数据
     */
    private void initData() {
        // recycler view 的数据
        mList = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            mList.add("item" + i);
        }

        mAdapter = new MyRecyclerAdapter(mList, this);
        //给recycler view 设置布局管理器
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // 给recycler view 设置Adapter
        MyRecyclerAdapterWrapper myRecyclerAdapterWrapper = new MyRecyclerAdapterWrapper(mAdapter);

        mRecyclerView.setAdapter(myRecyclerAdapterWrapper);
    }

    /**
     * 初始化view
     */
    private void initView() {
        mRecyclerView = findViewById(R.id.recycle_view);
    }
}
