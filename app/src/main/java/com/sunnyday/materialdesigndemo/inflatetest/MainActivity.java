package com.sunnyday.materialdesigndemo.inflatetest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import com.sunnyday.materialdesigndemo.R;
import com.sunnyday.materialdesigndemo.adapter.MyRecyclerAdapter;

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

        startActivity(new Intent(this, TestActivity.class));


    }

    private void initData() {
        mList = new ArrayList<>();

        for (int i = 0; i < 30; i++) {
            mList.add("item" + i);
        }

        mAdapter = new MyRecyclerAdapter(mList, this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter);

    }

    private void initView() {
        mRecyclerView = findViewById(R.id.recycle_view);
    }
}
